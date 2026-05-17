import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class paginaRegistro {

    private JPanel contenedorPaginaRegistro;
    private JPanel contenedorSecundarioRegistro;
    private Font nuevaFuente;
    private JButton botonIchigo;
    private JButton botonUrahara;
    private JButton botonRenji;
    private JButton botonEmpezar;
    private String personajeSeleccionado;
    private JTextField etiquetaInsertarNombre;

    public JPanel getContenedorPaginaRegistro() {
        return contenedorPaginaRegistro;
    }

    public String getPersonajeSeleccionado() {
        return personajeSeleccionado;
    }

    public JTextField getEtiquetaInsertarNombre() {
        return etiquetaInsertarNombre;
    }

    public paginaRegistro() {
        contenedorPaginaRegistro = new JPanel(null);
        contenedorPaginaRegistro.setPreferredSize(new Dimension(800, 600));
        contenedorPaginaRegistro.setSize(new Dimension(800, 600));
        contenedorPaginaRegistro.setBackground(new Color(58, 95, 119));

        mostrarRegistro();
    }

    private void mostrarRegistro() {
        Fuente fuentePropia = new Fuente();
        nuevaFuente = fuentePropia.usarFuentePropia();

        contenedorSecundarioRegistro = new JPanel(null);
        contenedorSecundarioRegistro.setBackground(new Color(58, 95, 119));
        contenedorSecundarioRegistro.setBounds(100, 50, 640, 450);
        contenedorPaginaRegistro.add(contenedorSecundarioRegistro);

        JLabel etiquetaNombreJugador = new JLabel("NOMBRE JUGADOR");
        etiquetaNombreJugador.setFont(nuevaFuente.deriveFont(16f));
        etiquetaNombreJugador.setForeground(Color.WHITE);
        etiquetaNombreJugador.setBounds(30, 40, 220, 30);
        contenedorSecundarioRegistro.add(etiquetaNombreJugador);

        etiquetaInsertarNombre = new JTextField();
        etiquetaInsertarNombre.setBounds(30, 75, 250, 30);
        etiquetaInsertarNombre.setFont(nuevaFuente.deriveFont(16f));
        contenedorSecundarioRegistro.add(etiquetaInsertarNombre);

        JLabel etiquetaPersonaje = new JLabel("SELECCIONA TU PERSONAJE:");
        etiquetaPersonaje.setForeground(Color.WHITE);
        etiquetaPersonaje.setFont(nuevaFuente.deriveFont(16f));
        etiquetaPersonaje.setBounds(30, 130, 280, 30);
        contenedorSecundarioRegistro.add(etiquetaPersonaje);

        JLabel etiquetaImagenIchigo = crearImagenPersonaje("src/imagenes/ichigo.png", 20, 170);
        JLabel etiquetaImagenRukia = crearImagenPersonaje("src/imagenes/urahara.png", 210, 170);
        JLabel etiquetaImagenRenji = crearImagenPersonaje("src/imagenes/renji.png", 390, 170);
        contenedorSecundarioRegistro.add(etiquetaImagenIchigo);
        contenedorSecundarioRegistro.add(etiquetaImagenRukia);
        contenedorSecundarioRegistro.add(etiquetaImagenRenji);

        botonIchigo = new JButton("Ichigo");
        botonIchigo.setFont(nuevaFuente.deriveFont(16f));
        botonIchigo.setFocusable(false);
        botonIchigo.setBounds(30, 290, 100, 40);

        botonUrahara = new JButton("Urahara");
        botonUrahara.setFont(nuevaFuente.deriveFont(16f));
        botonUrahara.setFocusable(false);
        botonUrahara.setBounds(220, 290, 100, 40);

        botonRenji = new JButton("Renji");
        botonRenji.setFont(nuevaFuente.deriveFont(16f));
        botonRenji.setFocusable(false);
        botonRenji.setBounds(410, 290, 100, 40);

        botonIchigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personajeSeleccionado = "Ichigo";
                marcarBotonSeleccion(botonIchigo, botonUrahara, botonRenji);
            }
        });

        botonUrahara.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personajeSeleccionado = "Urahara";
                marcarBotonSeleccion(botonUrahara, botonIchigo, botonRenji);
            }
        });

        botonRenji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personajeSeleccionado = "Renji";
                marcarBotonSeleccion(botonRenji, botonIchigo, botonUrahara);
            }
        });

        contenedorSecundarioRegistro.add(botonIchigo);
        contenedorSecundarioRegistro.add(botonUrahara);
        contenedorSecundarioRegistro.add(botonRenji);

        botonEmpezar = new JButton("EMPEZAR BATALLA");
        botonEmpezar.setBounds(190, 370, 200, 50);
        botonEmpezar.setFocusable(false);
        botonEmpezar.setFont(nuevaFuente.deriveFont(16f));
        botonEmpezar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = etiquetaInsertarNombre.getText().trim();

                if (nombreUsuario.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "¡Cuidado! El nombre no puede estar vacío para iniciar la batalla.",
                            "Campo Requerido",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (personajeSeleccionado == null) {
                    JOptionPane.showMessageDialog(null,
                            "¡Selecciona un personaje antes de comenzar!",
                            "Campo Requerido",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                panelJuego jugar = new panelJuego(nombreUsuario, personajeSeleccionado);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(botonEmpezar);
                frame.setContentPane(jugar.getPanelPrincipal());
                frame.revalidate();
                frame.repaint();
                jugar.getPanelJuego().requestFocusInWindow();
            }
        });
        contenedorSecundarioRegistro.add(botonEmpezar);
    }

    private JLabel crearImagenPersonaje(String ruta, int posicionX, int posicionY) {
        JLabel labelImagenSprite = new JLabel();
        labelImagenSprite.setBounds(posicionX, posicionY, 110, 115);
        labelImagenSprite.setHorizontalAlignment(SwingConstants.CENTER);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image imagenSprite = toolkit.getImage(ruta).getScaledInstance(42, 60, Image.SCALE_SMOOTH);
        labelImagenSprite.setIcon(new ImageIcon(imagenSprite));
        contenedorSecundarioRegistro.add(labelImagenSprite);
        return labelImagenSprite;
    }

    private void marcarBotonSeleccion(JButton personajeSeleccionado, JButton personajeNoSeleccionado1, JButton personajeNoSeleccionado2) {
        personajeSeleccionado.setBackground(Color.yellow);
        personajeNoSeleccionado1.setForeground(new Color(26, 26, 26));
        personajeNoSeleccionado1.setBackground(new Color(240, 236, 236));
        personajeNoSeleccionado2.setForeground(new Color(26, 26, 26));
        personajeNoSeleccionado2.setBackground(new Color(240, 236, 236));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro");
        frame.setContentPane(new paginaRegistro().contenedorPaginaRegistro);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}