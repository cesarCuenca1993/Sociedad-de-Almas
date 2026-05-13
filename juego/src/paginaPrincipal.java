import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class paginaPrincipal extends JFrame {
    private final CardLayout cardLayout;
    private JPanel contenedorPrincipalInicio;
    private PanelJuego empezarJuego;
    private String personajeSeleccionado;

    public paginaPrincipal() {
        setTitle("Sociedad de Almas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        contenedorPrincipalInicio = new JPanel(cardLayout);

        contenedorPrincipalInicio.add(crearPanelMenu(), "MENU");
        contenedorPrincipalInicio.add(crearPanelInstrucciones(), "INST");
        contenedorPrincipalInicio.add(crearPanelRegistro(), "REG");

        add(contenedorPrincipalInicio);
        cardLayout.show(contenedorPrincipalInicio, "MENU");
    }

    private Font fuentePropia() {
        Font fuentePersonalizada;
        try {
            File fontFile = new File("juego/src/fuentes/Quicksand-SemiBold.ttf");

            fuentePersonalizada = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fuentePersonalizada);

        } catch (IOException | FontFormatException e) {
            System.out.println("Error al cargar fuente propia, asi que se usara Arial");
            fuentePersonalizada = new Font("Arial", Font.BOLD, 12);

        }
        return fuentePersonalizada;
    }

    private JPanel crearPanelMenu() {
        JPanel contenedorMenu = new JPanel(null);
        contenedorMenu.setBackground(new Color(58, 95, 119));
        JLabel etiquetaTitulo = new JLabel();
        etiquetaTitulo.setText("SOUL SOCIETY");
        etiquetaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaTitulo.setFont(fuentePropia().deriveFont(50f));
        etiquetaTitulo.setForeground(Color.WHITE);
        etiquetaTitulo.setBounds(0, 70, 800, 80);
        contenedorMenu.add(etiquetaTitulo);

        JButton botonIniciar = new JButton();
        botonIniciar.setText("INICIAR");
        botonIniciar.setBounds(300, 250, 200, 50);
        botonIniciar.setFont(fuentePropia().deriveFont(16f));
        botonIniciar.setFocusable(false);
        botonIniciar.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(paginaPrincipal.this.contenedorPrincipalInicio, "REG");
            }
        });
        contenedorMenu.add(botonIniciar);

        JButton botonInstrucciones = new JButton();
        botonInstrucciones.setText("INSTRUCCIONES");
        botonInstrucciones.setBounds(300, 330, 200, 50);
        botonInstrucciones.setFont(fuentePropia().deriveFont(16f));
        botonInstrucciones.setFocusable(false);
        botonInstrucciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(paginaPrincipal.this.contenedorPrincipalInicio, "INST");
            }
        });
        contenedorMenu.add(botonInstrucciones);

        JButton botonSalir = new JButton();
        botonSalir.setText("SALIR");
        botonSalir.setFont(fuentePropia().deriveFont(16f));
        botonSalir.setFocusable(false);
        botonSalir.setBounds(300, 410, 200, 50);
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        contenedorMenu.add(botonSalir);

        return contenedorMenu;
    }

    private JPanel crearPanelRegistro() {
        JPanel contenedorPrincipalRegistro = new JPanel(null);
        contenedorPrincipalRegistro.setBackground(new Color(58, 95, 119));

        JPanel contenedorSecundarioRegistro = new JPanel(null);
        contenedorSecundarioRegistro.setBackground(new Color(58, 95, 119));
        contenedorSecundarioRegistro.setBounds(100, 50, 640, 450);
        contenedorPrincipalRegistro.add(contenedorSecundarioRegistro);

        JLabel etiquetaNombre = new JLabel();
        etiquetaNombre.setText("NOMBRE JUGADOR");
        etiquetaNombre.setFont(fuentePropia().deriveFont(16f));
        etiquetaNombre.setForeground(Color.WHITE);
        etiquetaNombre.setBounds(30, 40, 220, 30);
        contenedorSecundarioRegistro.add(etiquetaNombre);

        JTextField etiquetaInsertarNombre = new JTextField();
        etiquetaInsertarNombre.setBounds(30, 75, 250, 30);
        etiquetaInsertarNombre.setFont(fuentePropia().deriveFont(16f));
        contenedorSecundarioRegistro.add(etiquetaInsertarNombre);

        JLabel etiquetaPersonaje = new JLabel("SELECCIONA TU PERSONAJE:");
        etiquetaPersonaje.setForeground(Color.WHITE);
        etiquetaPersonaje.setFont(fuentePropia().deriveFont(16f));
        etiquetaPersonaje.setBounds(30, 130, 280, 30);
        contenedorSecundarioRegistro.add(etiquetaPersonaje);

        JLabel etiquetaImagenIchigo = crearImagenPersonaje("juego/src/imagenes/ichigo.png", 20, 170);
        JLabel etiquetaImagenRukia = crearImagenPersonaje("juego/src/imagenes/urahara.png", 210, 170);
        JLabel etiquetaImagenRenji = crearImagenPersonaje("juego/src/imagenes/renji.png", 390, 170);

        contenedorSecundarioRegistro.add(etiquetaImagenIchigo);
        contenedorSecundarioRegistro.add(etiquetaImagenRukia);
        contenedorSecundarioRegistro.add(etiquetaImagenRenji);

        JButton botonIchigo = new JButton();
        botonIchigo.setText("Ichigo");
        botonIchigo.setFont(fuentePropia().deriveFont(16f));
        botonIchigo.setFocusable(false);
        botonIchigo.setBounds(30, 290, 100, 40);

        JButton botonRukia = new JButton();
        botonRukia.setText("Urahara");
        botonRukia.setFont(fuentePropia().deriveFont(16f));
        botonRukia.setFocusable(false);
        botonRukia.setBounds(220, 290, 100, 40);

        JButton botonRenji = new JButton("Renji");
        botonRenji.setText("Renji");
        botonRenji.setFont(fuentePropia().deriveFont(16f));
        botonRenji.setFocusable(false);
        botonRenji.setBounds(410, 290, 100, 40);

        botonIchigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personajeSeleccionado = "Ichigo";
                marcarBotonParaSeleccion(botonIchigo, botonRukia, botonRenji);
            }
        });

        botonRukia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personajeSeleccionado = "Urahara";
                marcarBotonParaSeleccion(botonRukia, botonIchigo, botonRenji);
            }
        });

        botonRenji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personajeSeleccionado = "Renji";
                marcarBotonParaSeleccion(botonRenji, botonIchigo, botonRukia);
            }
        });
        contenedorSecundarioRegistro.add(botonIchigo);
        contenedorSecundarioRegistro.add(botonRukia);
        contenedorSecundarioRegistro.add(botonRenji);

        JButton botonEmpezar = new JButton();
        botonEmpezar.setText("EMPEZAR BATALLA");
        botonEmpezar.setBounds(190, 370, 200, 50);
        botonEmpezar.setFocusable(false);
        botonEmpezar.setFont(fuentePropia().deriveFont(16f));

        botonEmpezar.addActionListener(e -> {
            String nombreUsuario = etiquetaInsertarNombre.getText().trim();
            if (nombreUsuario.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "¡Cuidado! El nombreUsuario no puede estar vacío para iniciar la batalla.",
                        "Campo Requerido",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            empezarJuego = new PanelJuego(nombreUsuario, personajeSeleccionado);
            contenedorPrincipalInicio.add(empezarJuego, "JUEGO");
            cardLayout.show(contenedorPrincipalInicio, "JUEGO");
            empezarJuego.requestFocusInWindow();
        });
        contenedorSecundarioRegistro.add(botonEmpezar);
        return contenedorPrincipalRegistro;
    }

    private JLabel crearImagenPersonaje(String ruta, int posicionX, int posicionY) {

        JLabel etiquetaImagenSprite = new JLabel();
        etiquetaImagenSprite.setBounds(posicionX, posicionY, 110, 115);
        etiquetaImagenSprite.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon iconoSprite = new ImageIcon(ruta);
        Image imagenSprite = iconoSprite.getImage().getScaledInstance(60, 80, Image.SCALE_SMOOTH);
        etiquetaImagenSprite.setIcon(new ImageIcon(imagenSprite));

        return etiquetaImagenSprite;
    }

    private void marcarBotonParaSeleccion(JButton seleccionado, JButton noSeleccionado1, JButton noSeleccionado2) {
        seleccionado.setBackground(Color.yellow);
        noSeleccionado1.setForeground(new Color(26, 26, 26));
        noSeleccionado1.setBackground(new Color(240, 236, 236));
        noSeleccionado2.setForeground(new Color(26, 26, 26));
        noSeleccionado2.setBackground(new Color(240, 236, 236));
    }

    private JPanel crearPanelInstrucciones() {
        JPanel contenedorPrincipalInstrucciones = new JPanel(null);
        contenedorPrincipalInstrucciones.setBackground(new Color(45, 45, 45));

        JPanel contenedorSecundarioInstrucciones = new JPanel(null);
        contenedorSecundarioInstrucciones.setBackground(new Color(58, 95, 119));
        contenedorSecundarioInstrucciones.setBounds(100, 50, 600, 350);
        contenedorPrincipalInstrucciones.add(contenedorSecundarioInstrucciones);

        JTextArea etiquetaMostrarInstruccion = new JTextArea();
        etiquetaMostrarInstruccion.setText("Tu texto largo aquí." +
                "asdyfhjhkgj" +
                "erstyui" +
                "ewrtuyiuiop" +
                "455u56ot76to" +
                "6o7t76o677p76o67o76 67o67o67 5o67p6p w367..");
        etiquetaMostrarInstruccion.setBounds(20, 20, 560, 310);
        etiquetaMostrarInstruccion.setOpaque(false);          // Para que se vea el fondo azul del cuadro
        etiquetaMostrarInstruccion.setEditable(false);
        etiquetaMostrarInstruccion.setFocusable(false);
        etiquetaMostrarInstruccion.setForeground(Color.WHITE);
        etiquetaMostrarInstruccion.setFont(fuentePropia().deriveFont(16f));
        etiquetaMostrarInstruccion.setLineWrap(true);
        etiquetaMostrarInstruccion.setWrapStyleWord(true);
        contenedorSecundarioInstrucciones.add(etiquetaMostrarInstruccion);

        JButton botonVolver = new JButton("VOLVER");
        botonVolver.setBounds(300, 450, 180, 40);
        botonVolver.addActionListener(e -> cardLayout.show(contenedorPrincipalInicio, "MENU"));
        contenedorPrincipalInstrucciones.add(botonVolver);
        return contenedorPrincipalInstrucciones;
    }

    // ================= PANTALLA JUEGO CORREGIDA CON HOLLOW =================
    public class PanelJuego extends JPanel {
        private String nombreJugador, seleccionadoPersonaje;
        private JLabel labelNombreJugador, labelVida, labelPuntos, labelTiempo, labelAvatar,labelEnergiaEspiritual;
        private Timer timer; // Timer del HUD
        private int seconds = 0;
        private int velocidad = 15;


        // Variables del Villano y Poderes
        private boolean mirandoDerechaAvatar = true;
        private ArrayList<JLabel> labelListaHollow =new ArrayList<>();
        private ArrayList<JLabel> ataqueZero = new ArrayList<>();
        private Timer movimientoHollow;
        private Timer tiempoAparecenHollows;
        private int velocidadHollow = 3;
        private int velocidadZero = 7;
        private int vidaJugador = 100;
        private int energiaEspiritual = 0;
        private int puntos =0;
        private ArrayList<JLabel> cantidadKidoJugador = new ArrayList<>();

        public PanelJuego(String nombreJugador, String seleccionadoPersonaje) {
            this.nombreJugador = nombreJugador;
            this.seleccionadoPersonaje = seleccionadoPersonaje;

            setLayout(new BorderLayout());
            setBackground(new Color(30, 30, 30));
            setFocusable(true);

            add(crearCabecera(), BorderLayout.NORTH);

            JPanel contenedorAreaPelea = new JPanel(null);
            contenedorAreaPelea.setOpaque(false);
            add(contenedorAreaPelea, BorderLayout.CENTER);

            String rutaSpriteSeleccionado = "juego/src/imagenes/" + seleccionadoPersonaje.toLowerCase() + ".png";
            labelAvatar = crearSprite(rutaSpriteSeleccionado, 0, 200);
            contenedorAreaPelea.add(labelAvatar);

            this.addKeyListener(new KeyAdapter() {
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
                            if (energiaEspiritual == 0) {
                                lanzarKidoJugador(contenedorAreaPelea);
                                labelEnergiaEspiritual.setText("Energía: " + energiaEspiritual); // Actualizamos el texto
                            } else {
                                System.out.println("No tienes suficiente energía espiritual");
                            }
                            break;

                    }
                    labelAvatar.setLocation(posicionAvatarX, posicionAvatarY);
                    girarImagenAvatar(rutaSpriteSeleccionado);
                }
            });

            tiempoAparecenHollows = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JLabel nuevoHollow = crearSprite("juego/src/imagenes/hollow.png", 700, 200);

                    labelListaHollow.add(nuevoHollow);
                    contenedorAreaPelea.add(nuevoHollow);

                    contenedorAreaPelea.repaint();

                    Timer disparoIndividual = new Timer(2500, ev -> {
                        if (labelListaHollow.contains(nuevoHollow)) {
                            lanzarZero(contenedorAreaPelea, nuevoHollow);
                        } else {
                            ((Timer)ev.getSource()).stop();
                            System.out.println("Timer de disparo detenido: el Hollow ya no existe.");
                        }
                    });
                    disparoIndividual.start();
                }
            });
            tiempoAparecenHollows.setInitialDelay(0);
            tiempoAparecenHollows.start();

            movimientoHollow = new Timer(50, e -> {

                for (int i = 0; i < labelListaHollow.size(); i++) {
                    JLabel hollowActual = labelListaHollow.get(i);

                    moverHollowIA(hollowActual);

                    boolean mirarDerecha = hollowActual.getX() < labelAvatar.getX();

                    girarImagenHollow(hollowActual, mirarDerecha);
                }
                actualizarZeros(contenedorAreaPelea);
                actualizarKidoJugador(contenedorAreaPelea);
            });
            movimientoHollow.start();
        }private void lanzarKidoJugador(JPanel contenedorAreaJuego) {
            JLabel kido = new JLabel();
            kido.setOpaque(true);
            kido.setBackground(Color.CYAN);


            kido.setBounds(labelAvatar.getX() + 40, labelAvatar.getY() + 40, 12, 12);

            int direccion = mirandoDerechaAvatar ? 1 : -1;
            kido.putClientProperty("direccion", direccion);

            cantidadKidoJugador.add(kido);
            contenedorAreaJuego.add(kido);
            contenedorAreaJuego.repaint();

        }private void actualizarKidoJugador(JPanel area) {
            for (int i = 0; i < cantidadKidoJugador.size(); i++) {
                JLabel nuevoKido = cantidadKidoJugador.get(i);
                int dir = (int) nuevoKido.getClientProperty("direccion");

                nuevoKido.setLocation(nuevoKido.getX() + (10 * dir), nuevoKido.getY());

                for (int j = 0; j < labelListaHollow.size(); j++) {
                    JLabel nuevoHollow = labelListaHollow.get(j);
                    if (nuevoKido.getBounds().intersects(nuevoHollow.getBounds())) {
                      puntos += 10;
                      labelPuntos.setText("Puntos: " + puntos);
                        area.remove(nuevoHollow);
                        labelListaHollow.remove(j);

                        area.remove(nuevoKido);
                        cantidadKidoJugador.remove(i);

                        System.out.println("¡Hollow eliminado!");
                        area.repaint();
                        break;
                    }
                }

                if (nuevoKido != null && (nuevoKido.getX() > 800 || nuevoKido.getX() < -20)) {
                    area.remove(nuevoKido);
                    cantidadKidoJugador.remove(i);
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

            Image img = icon.getImage().getScaledInstance(80, 90, Image.SCALE_SMOOTH);
            imagenSprite.setIcon(new ImageIcon(img));

            imagenSprite.setBounds(posicionX, posicionY, 80, 90);
            return imagenSprite;
        }

        private void lanzarZero(JPanel area, JLabel hollowQueDispara) {
            JLabel zero = new JLabel();
            zero.setOpaque(true);
            zero.setBackground(Color.WHITE);
            zero.setBounds(hollowQueDispara.getX() + 40, hollowQueDispara.getY() + 40, 15, 15);

            // Calculamos dirección: si el hollow está a la izquierda del avatar, dispara a la derecha
            int direccion = (hollowQueDispara.getX() < labelAvatar.getX()) ? 1 : -1;
            zero.putClientProperty("direccion", direccion);

            ataqueZero.add(zero);
            area.add(zero);
            area.repaint();
          area.add(zero);
        }

        private void actualizarZeros(JPanel area) {
            for (int i = 0; i < ataqueZero.size(); i++) {
                JLabel b = ataqueZero.get(i);

                // RECUPERAMOS LA DIRECCIÓN (por defecto 1 si falla)
                int dir = (int) b.getClientProperty("direccion");

                // Aplicamos la dirección a la velocidad
                b.setLocation(b.getX() + (velocidadZero * dir), b.getY());

                // Colisión con el Jugador
                if (b.getBounds().intersects(labelAvatar.getBounds())) {
                    vidaJugador -= 10;
                    labelVida.setText("Vida: " + vidaJugador);
                    // ... resto de tu lógica de muerte

                    area.remove(b);
                    ataqueZero.remove(i);
                    if (vidaJugador <= 0) {
                        detenerTodo();
                        JOptionPane.showMessageDialog(null, "¡FIN DEL JUEGO!");
                        cardLayout.show(contenedorPrincipalInicio, "MENU");
                    }
                    continue;
                }

                // Eliminar si sale por la derecha O por la izquierda
                if (b.getX() > 800 || b.getX() < -20) {
                    area.remove(b);
                    ataqueZero.remove(i);
                }
            }
            area.repaint();
        }

        private void detenerTodo() {
            if (timer != null) timer.stop();

            if (tiempoAparecenHollows != null) tiempoAparecenHollows.stop();

            if (movimientoHollow != null) movimientoHollow.stop();

            this.setFocusable(false);


        }


        private void girarImagenAvatar(String rutaSpriteSeleccionado) {

                String ruta = rutaSpriteSeleccionado;
                Image img = new ImageIcon(ruta).getImage();
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

        private void girarImagenHollow(JLabel hollow, boolean mirandoDerecha) {
            String ruta = "juego/src/imagenes/hollow.png";
            ImageIcon iconoOriginal = new ImageIcon(ruta);
            if (iconoOriginal.getIconWidth() == -1) return;

            Image img = iconoOriginal.getImage();
            BufferedImage bi = new BufferedImage(80, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bi.createGraphics();

            if (mirandoDerecha) {
                g2d.drawImage(img, 0, 0, 80, 100, null);
            } else {
                g2d.drawImage(img, 80, 0, -80, 100, null);
            }

            g2d.dispose();
            hollow.setIcon(new ImageIcon(bi));
        }


        private JPanel crearCabecera() {
            JPanel cabecera = new JPanel(new BorderLayout());
            cabecera.setBackground(new Color(20, 20, 20));
            cabecera.setPreferredSize(new Dimension(800, 80));

            JPanel informacionIzquierda = new JPanel(new GridLayout(2, 2, 60, 4));
            informacionIzquierda.setBackground(new Color(20, 20, 20));
            informacionIzquierda.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));

            labelNombreJugador = new JLabel();
            labelNombreJugador.setText("Jugador: " + nombreJugador);
            labelNombreJugador.setForeground(Color.WHITE);
            labelNombreJugador.setFont(fuentePropia().deriveFont(15f));

            labelVida = new JLabel();
            labelVida.setText("Vida: 100");
            labelVida.setForeground(new Color(80, 200, 80));
            labelVida.setFont(fuentePropia().deriveFont(15f));


            JLabel labelPersonaje = new JLabel();
            labelPersonaje.setText("Personaje: " + seleccionadoPersonaje);
            labelPersonaje.setFont(fuentePropia().deriveFont(15f));
            labelPersonaje.setForeground(new Color(180, 180, 255));

            labelEnergiaEspiritual = new JLabel();
            labelEnergiaEspiritual.setText("E.E: 0");
            labelEnergiaEspiritual.setFont(fuentePropia().deriveFont(15f));
            labelEnergiaEspiritual.setForeground(new Color(100, 200, 255));

            informacionIzquierda.add(labelNombreJugador);
            informacionIzquierda.add(labelVida);
            informacionIzquierda.add(labelPersonaje);
            informacionIzquierda.add(labelEnergiaEspiritual);

            JPanel timerCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 25));
            timerCentro.setBackground(new Color(20, 20, 20));

            labelTiempo = new JLabel("0 Segundos");
            labelTiempo.setForeground(new Color(255, 100, 100));
            labelTiempo.setFont(fuentePropia().deriveFont(15f));

            JButton botonPausa = new JButton();
            botonPausa.setText("pausa");
            botonPausa.setFont(fuentePropia().deriveFont(15f));
            timer = new Timer(1000, e -> {
                seconds++;
                labelTiempo.setText(seconds + " Segundos");
            });
            timer.start();

            botonPausa.addActionListener(e -> {
                if (timer.isRunning()) {
                    timer.stop();
                    botonPausa.setText("reanudar");
                } else {
                    timer.start();
                    botonPausa.setText("pausa");
                    this.requestFocusInWindow();
                }
            });

            timerCentro.add(labelTiempo);
            timerCentro.add(botonPausa);

            JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
            derecha.setBackground(new Color(20, 20, 20));
            labelPuntos = new JLabel("puntos: 0");
            labelPuntos.setFont(fuentePropia().deriveFont(15f));
            labelPuntos.setForeground(new Color(255, 215, 0));
            derecha.add(labelPuntos);

            cabecera.add(informacionIzquierda, BorderLayout.WEST);
            cabecera.add(timerCentro, BorderLayout.CENTER);
            cabecera.add(derecha, BorderLayout.EAST);
            return cabecera;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new paginaPrincipal().setVisible(true));
    }
}