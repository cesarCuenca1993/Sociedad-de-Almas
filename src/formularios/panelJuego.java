import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.*;
import java.util.ArrayList;


public class panelJuego {

    private JPanel panelPrincipal;
    private JPanel cabecera;
    private JPanel panelJuego;
    private String nombreJugador, seleccionadoPersonaje;
    private JLabel labelNombreJugador, labelVida, labelPuntos, labelTiempo, labelAvatar;
    private Timer timerJuego;
    private int seconds = 0;
    private int velocidad = 15;
    private int vidaJugador = 100;
    private boolean mirandoDerechaAvatar = true;
    private int puntos = 0;
    private ArrayList<JLabel> cantidadKidoJugador = new ArrayList<>();

    private ArrayList<JLabel> labelListaHollow = new ArrayList<>();
    private ArrayList<JLabel> ataqueZero = new ArrayList<>();
    private Timer movimientoHollow;
    private Timer tiempoAparecenHollows;
    private int velocidadHollow = 8;
    private int velocidadZero = 8;

    private JLabel jefeFinal;
    private int vidaJefe = 1000;
    private int contadorSegundos = 0;
    private Timer timerAtaqueJefe;
    private Font nuevaFuente;

    private JButton botonPausa;
    private String rutaSpriteSeleccionado;
    private Timer tiempoApareceJefeFinal;
    private boolean juegoTerminado = false;
    private boolean ganador = false;
    private  Timer timerDisparoHollow;

    public JPanel getPanelJuego() {
        return panelJuego;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public panelJuego(String nombreJugador, String seleccionadoPersonaje) {

        this.nombreJugador = nombreJugador;
        this.seleccionadoPersonaje = seleccionadoPersonaje;

        panelPrincipal = new JPanel();
        panelPrincipal.setPreferredSize(new Dimension(800, 600));
        panelPrincipal.setSize(new Dimension(800, 600));
        panelPrincipal.setLayout(null);
        panelPrincipal.setOpaque(false);

        crearCabecera();
        jugarPanelJuego();

    }

    private void jugarPanelJuego() {
        panelJuego = new JPanel();
        panelJuego.setSize(panelPrincipal.getWidth(), panelPrincipal.getHeight() - cabecera.getSize().height);
        panelJuego.setLayout(null);
        panelJuego.setBackground(new Color(58, 95, 119));
        panelJuego.setLocation(0, cabecera.getHeight());

        rutaSpriteSeleccionado = "src/imagenes/" + seleccionadoPersonaje.toLowerCase() + ".png";
        labelAvatar = crearSprite(rutaSpriteSeleccionado, 0, 200);
        panelJuego.add(labelAvatar);

        panelJuego.addKeyListener(new panelJuegoListener());

        tiempoAparecenHollows = new Timer(4000, new TimerAparecenHollows());
        tiempoAparecenHollows.setInitialDelay(0);
        tiempoAparecenHollows.start();


        tiempoApareceJefeFinal = new Timer(1000, new tiempoApareceJefeFinal());
        tiempoApareceJefeFinal.start();

        movimientoHollow = new Timer(80, new TimerMovimientoHollow());
        movimientoHollow.start();

        panelPrincipal.add(panelJuego);


    }

    private class panelJuegoListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int posicionAvatarX = labelAvatar.getX();
            int posicionAvatarY = labelAvatar.getY();

            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    mirandoDerechaAvatar = true;
                    if (posicionAvatarX < 700) posicionAvatarX += velocidad;
                    break;
                case KeyEvent.VK_LEFT:
                    mirandoDerechaAvatar = false;
                    if (posicionAvatarX > 0) posicionAvatarX -= velocidad;
                    break;
                case KeyEvent.VK_UP:
                    if (posicionAvatarY > 0) posicionAvatarY -= velocidad;
                    break;
                case KeyEvent.VK_DOWN:
                    if (posicionAvatarY < 380) posicionAvatarY += velocidad;
                    break;
                case KeyEvent.VK_A:
                    lanzarKidoJugador(panelJuego);
            }
            labelAvatar.setLocation(posicionAvatarX, posicionAvatarY);
            girarImagenAvatar(rutaSpriteSeleccionado);

        }
    }

    public class TimerAparecenHollows implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JLabel nuevoHollow = crearSprite("src/imagenes/hollow.png", 700, 200);
            labelListaHollow.add(nuevoHollow);
            panelJuego.add(nuevoHollow);
            panelJuego.repaint();

            timerDisparoHollow = new Timer(1000, ev -> {
                if (labelListaHollow.contains(nuevoHollow)) {
                    lanzarZero(panelJuego, nuevoHollow);
                    System.out.println("zero");
                } else {
                    ((Timer) ev.getSource()).stop();
                }
            });
            timerDisparoHollow.start();
        }
    }

    public class tiempoApareceJefeFinal implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            contadorSegundos++;
            if (contadorSegundos == 60) {
                aparecerJefeFinal();
                ((Timer) e.getSource()).stop();
            }
        }
    }

    public class TimerMovimientoHollow implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < labelListaHollow.size(); i++) {
                JLabel hollowActual = labelListaHollow.get(i);
                moverHollowIA(hollowActual);
                boolean mirarDerecha = hollowActual.getX() < labelAvatar.getX();
                girarImagenHollow(hollowActual, mirarDerecha, "src/imagenes/hollow.png");
            }
            if (jefeFinal != null) {
                moverHollowIA(jefeFinal);
                boolean jefeMiraDerecha = jefeFinal.getX() < labelAvatar.getX();
                girarImagenHollow(jefeFinal, jefeMiraDerecha, "src/imagenes/ichigoBankai.png");
            }
            actualizarZeros(panelJuego);
            actualizarKidoJugador(panelJuego);

        }
    }

    private void crearCabecera() {
        Fuente fuentePropia = new Fuente();
        nuevaFuente = fuentePropia.usarFuentePropia();

        cabecera = new JPanel(new BorderLayout());
        cabecera.setBackground(new Color(26, 26, 26));

        cabecera.setBounds(0, 0, 800, 80);

        JPanel informacionIzquierda = new JPanel(new GridLayout(2, 2, 60, 4));
        informacionIzquierda.setBackground(new Color(20, 20, 20));
        informacionIzquierda.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));

        labelNombreJugador = new JLabel("Jugador: " + nombreJugador);
        labelNombreJugador.setForeground(Color.WHITE);
        labelNombreJugador.setFont(nuevaFuente.deriveFont(15f));

        labelVida = new JLabel("Vida: 100");
        labelVida.setForeground(new Color(80, 200, 80));
        labelVida.setFont(nuevaFuente.deriveFont(15f));

        JLabel labelPersonaje = new JLabel("Personaje: " + seleccionadoPersonaje);
        labelPersonaje.setFont(nuevaFuente.deriveFont(15f));
        labelPersonaje.setForeground(new Color(180, 180, 255));

        informacionIzquierda.add(labelNombreJugador);
        informacionIzquierda.add(labelVida);
        informacionIzquierda.add(labelPersonaje);

        JPanel timerCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 25));
        timerCentro.setBackground(new Color(20, 20, 20));

        labelTiempo = new JLabel("0 Segundos");
        labelTiempo.setForeground(new Color(255, 100, 100));
        labelTiempo.setFont(nuevaFuente.deriveFont(15f));

        botonPausa = new JButton("pausa");
        botonPausa.setFocusPainted(false);
        botonPausa.setFont(nuevaFuente.deriveFont(15f));

        timerJuego = new Timer(1000, new TimerPrincipalActionListener());
        timerJuego.start();

        timerCentro.add(labelTiempo);
        timerCentro.add(botonPausa);

        botonPausa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (botonPausa.getText().equals("pausa")) {

                    botonPausa.setText("reanudar");
                    movimientoHollow.stop();
                    tiempoAparecenHollows.stop();
                    tiempoApareceJefeFinal.stop();
                    timerJuego.stop();
                    timerDisparoHollow.stop();

                } else {
                    timerJuego.start();
                    botonPausa.setText("pausa");
                    movimientoHollow.start();
                    tiempoAparecenHollows.start();
                    tiempoApareceJefeFinal.start();
                    timerDisparoHollow.start();
                    panelJuego.requestFocusInWindow();

                }
            }
        });

        JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        derecha.setBackground(new Color(20, 20, 20));

        labelPuntos = new JLabel("puntos: 0   ");
        labelPuntos.setFont(nuevaFuente.deriveFont(15f));
        labelPuntos.setForeground(new Color(255, 215, 0));
        derecha.add(labelPuntos);

        cabecera.add(informacionIzquierda, BorderLayout.WEST);
        cabecera.add(timerCentro, BorderLayout.CENTER);
        cabecera.add(derecha, BorderLayout.EAST);

        panelPrincipal.add(cabecera);


    }

    public class TimerPrincipalActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds++;
            labelTiempo.setText(seconds + " Segundos");

        }
    }


    private void aparecerJefeFinal() {
        jefeFinal = crearSprite("src/imagenes/ichigoBankai.png", 600, 150);
        jefeFinal.setSize(150, 200);
        jefeFinal.setText("vida Jefe: 1000");
        jefeFinal.setForeground(Color.white);
        panelJuego.add(jefeFinal);

        timerAtaqueJefe = new Timer(1000, e -> {
            if (jefeFinal != null && jefeFinal.getParent() != null) {
                lanzarZero(panelJuego, jefeFinal);
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        timerAtaqueJefe.start();

        panelJuego.repaint();
    }

    private void lanzarKidoJugador(JPanel panelJuego) {
        JLabel kido = new JLabel();
        kido.setOpaque(true);
        kido.setBackground(Color.CYAN);
        kido.setBounds(labelAvatar.getX() + 40, labelAvatar.getY() + 40, 12, 12);
        int direccion = mirandoDerechaAvatar ? 1 : -1;
        kido.putClientProperty("direccion", direccion);
        cantidadKidoJugador.add(kido);
        panelJuego.add(kido);
        panelJuego.repaint();
    }

    private void actualizarKidoJugador(JPanel panelJuego) {
        for (int i = 0; i < cantidadKidoJugador.size(); i++) {
            JLabel nuevoKido = cantidadKidoJugador.get(i);
            int dir = (int) nuevoKido.getClientProperty("direccion");
            nuevoKido.setLocation(nuevoKido.getX() + (5 * dir), nuevoKido.getY());

            boolean kidoEliminado = false;

            for (int j = 0; j < labelListaHollow.size(); j++) {
                JLabel nuevoHollow = labelListaHollow.get(j);
                if (nuevoKido.getBounds().intersects(nuevoHollow.getBounds())) {
                    puntos += 10;
                    labelPuntos.setText("Puntos: " + puntos);
                    panelJuego.remove(nuevoHollow);
                    labelListaHollow.remove(j);
                    panelJuego.remove(nuevoKido);
                    cantidadKidoJugador.remove(i);
                    kidoEliminado = true;
                    System.out.println("¡Hollow eliminado!");
                    panelJuego.repaint();
                    break;
                }
            }

            if (!kidoEliminado && jefeFinal != null) {
                if (nuevoKido.getBounds().intersects(jefeFinal.getBounds())) {
                    vidaJefe -= 5;
                    puntos += 10;
                    labelPuntos.setText("Puntos: " + puntos);
                    panelJuego.remove(nuevoKido);
                    cantidadKidoJugador.remove(i);
                    i--;
                    kidoEliminado = true;
                    if (vidaJefe <= 0 && !juegoTerminado) {
                        juegoTerminado = true;
                        ganador = true;
                        panelJuego.remove(jefeFinal);

                        if (vidaJugador > 50) {
                            puntos += 1500;
                            labelPuntos.setText("Puntos: " + puntos);
                        }
                        guardarPartida(ganador);
                        JOptionPane.showMessageDialog(null, "¡FIN DEL JUEGO!" + "\nNombre: " + nombreJugador +
                                "\nPersonaje: " + seleccionadoPersonaje + "\nPuntos: " + puntos + "\nVida jugaador: " + vidaJugador + "\nGanador: " + ganador);
                        terminaraJuego();
                    }
                    panelJuego.repaint();
                }
            }

            if (!kidoEliminado) {
                if (nuevoKido.getX() > 800 || nuevoKido.getX() < -20) {
                    panelJuego.remove(nuevoKido);
                    cantidadKidoJugador.remove(i);
                    i--;
                }
            }
        }
    }

    private void moverHollowIA(JLabel hollow) {
        int hollowPosicionX = hollow.getX();
        int hollowPosicionY = hollow.getY();
        int pX = labelAvatar.getX();
        int pY = labelAvatar.getY();

        if (hollowPosicionX < pX) hollowPosicionX += velocidadHollow;
        else if (hollowPosicionX > pX) hollowPosicionX -= velocidadHollow;

        if (hollowPosicionY < pY) hollowPosicionY += velocidadHollow;
        else if (hollowPosicionY > pY) hollowPosicionY -= velocidadHollow;

        hollow.setLocation(hollowPosicionX, hollowPosicionY);
    }

    private JLabel crearSprite(String rutaImagen, int posicionX, int posicionY) {
        JLabel imagenSprite = new JLabel();
        ImageIcon icon = new ImageIcon(rutaImagen);
        Image img = icon.getImage().getScaledInstance(42, 60, Image.SCALE_SMOOTH);
        imagenSprite.setIcon(new ImageIcon(img));
        imagenSprite.setBounds(posicionX, posicionY, 80, 90);

        return imagenSprite;
    }

    private void lanzarZero(JPanel panelJuego, JLabel hollowQueDispara) {
        JLabel zero = new JLabel();
        zero.setOpaque(true);
        zero.setBackground(Color.WHITE);
        zero.setBounds(hollowQueDispara.getX() + 40, hollowQueDispara.getY() + 40, 15, 15);
        int direccion = (hollowQueDispara.getX() < labelAvatar.getX()) ? 1 : -1;
        zero.putClientProperty("direccion", direccion);
        ataqueZero.add(zero);
        panelJuego.add(zero, 0);
        panelJuego.revalidate();
        panelJuego.repaint();
    }

    private void actualizarZeros(JPanel panelJuego) {
        for (int i = 0; i < ataqueZero.size(); i++) {
            JLabel nuevoZero = ataqueZero.get(i);
            int dir = (int) nuevoZero.getClientProperty("direccion");
            nuevoZero.setLocation(nuevoZero.getX() + (velocidadZero * dir), nuevoZero.getY());

            if (nuevoZero.getBounds().intersects(labelAvatar.getBounds())) {
                vidaJugador -= 10;

                panelJuego.remove(nuevoZero);
                ataqueZero.remove(i);

                if (vidaJugador >= 70  && vidaJugador <= 100) {
                    labelVida.setForeground(Color.green);
                    labelVida.setText("Vida: " + vidaJugador);
                }else if (vidaJugador >= 40 && vidaJugador <= 60) {
                    labelVida.setForeground(Color.orange);
                    labelVida.setText("Vida: " + vidaJugador);
                } else {
                    labelVida.setForeground(Color.red);
                    labelVida.setText("Vida: " + vidaJugador);
                }

                if (vidaJugador <= 0 && !juegoTerminado) {

                    ganador = false;
                    juegoTerminado = true;

                    guardarPartida(ganador);
                    JOptionPane.showMessageDialog(null, "¡FIN DEL JUEGO!" + "\nNombre: " + nombreJugador +
                            "\nPersonaje: " + seleccionadoPersonaje + "\nPuntos: " + puntos + "\nVida jugaador: " + vidaJugador + "\nGanador: " + ganador);
                    terminaraJuego();
                }
                continue;
            }

            if (nuevoZero.getX() > 800 || nuevoZero.getX() < -20) {
                panelJuego.remove(nuevoZero);
                ataqueZero.remove(i);
            }
        }
        panelJuego.repaint();
    }

    private void terminaraJuego() {
        movimientoHollow.stop();
        tiempoAparecenHollows.stop();
        timerJuego.stop();

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelJuego);
        frame.setContentPane(new paginaPrincipal().getContenedorPrincipalInicio());
        frame.revalidate();
        frame.repaint();
    }


    private void girarImagenAvatar(String rutaSpriteSeleccionado) {
        Image img = new ImageIcon(rutaSpriteSeleccionado).getImage();
        BufferedImage bidireccional = new BufferedImage(80, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D imagenGrafica2D = bidireccional.createGraphics();
        if (mirandoDerechaAvatar) {
            imagenGrafica2D.drawImage(img, 0, 0, 80, 100, null);
        } else {
            imagenGrafica2D.drawImage(img, 80, 0, -80, 100, null);
        }
        imagenGrafica2D.dispose();
        labelAvatar.setIcon(new ImageIcon(bidireccional));

    }

    private void girarImagenHollow(JLabel label, boolean mirandoDerecha, String rutaImagen) {
        ImageIcon iconoOriginal = new ImageIcon(rutaImagen);
        if (iconoOriginal.getIconWidth() == -1) return;
        Image img = iconoOriginal.getImage();
        BufferedImage bi = new BufferedImage(label.getWidth(), label.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        if (mirandoDerecha) {
            g2d.drawImage(img, 0, 0, label.getWidth(), label.getHeight(), null);
        } else {
            g2d.drawImage(img, label.getWidth(), 0, -label.getWidth(), label.getHeight(), null);
        }
        g2d.dispose();
        label.setIcon(new ImageIcon(bi));
    }

    private void guardarPartida(boolean ganador) {
        String url = "jdbc:mysql://localhost:3306/sociedad_almas";
        String user = "root";
        String password = "";


        int idPersonajesBaseDatos = 0;
        if (seleccionadoPersonaje.equals("Ichigo")) {
            idPersonajesBaseDatos = 1;
        } else if (seleccionadoPersonaje.equals("Urahara")) {
            idPersonajesBaseDatos = 2;
        } else if (seleccionadoPersonaje.equals("Renji")) {
            idPersonajesBaseDatos = 3;
        }

        try {
            Connection conexion = DriverManager.getConnection(url, user, password);

            String sqlPersonaje = "INSERT  INTO personaje (id_personaje, nombre_personaje) VALUES (?, ?)";
            PreparedStatement pst1 = conexion.prepareStatement(sqlPersonaje);
            pst1.setInt(1, idPersonajesBaseDatos);
            pst1.setString(2, seleccionadoPersonaje);
            pst1.executeUpdate();
            pst1.close();

            String sqlUsuario = "INSERT INTO usuario (nombre_usuario, id_personaje) VALUES (?, ?)";
            PreparedStatement pst2 = conexion.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            pst2.setString(1, nombreJugador);
            pst2.setInt(2, idPersonajesBaseDatos);
            pst2.executeUpdate();

            int idUsuario = 0;
            ResultSet rs = pst2.getGeneratedKeys();
            if (rs.next()) {
                idUsuario = rs.getInt(1);
            }
            pst2.close();

            String sqlPartida = "INSERT INTO partida (id_usuario, id_personaje_seleccionado, puntuacion, vida_restante, ganador) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst3 = conexion.prepareStatement(sqlPartida);
            pst3.setInt(1, idUsuario);
            pst3.setInt(2, idPersonajesBaseDatos);
            pst3.setInt(3, puntos);
            pst3.setInt(4, vidaJugador);
            pst3.setBoolean(5, ganador);
            pst3.executeUpdate();
            pst3.close();

            conexion.close();
            System.out.println("¡Partida guardada correctamente!");

        } catch (Exception ex) {
            System.out.println("Error al guardar partida: ");

        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Panel Juego");
        frame.setContentPane(new panelJuego("sdf", "ds").panelPrincipal); // ← fix principal
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}