import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class paginaPrincipal extends JFrame {
    private CardLayout cardLayout;
    private JPanel contenedor;

    private String personajeSeleccionado = "Ichigo";

    public paginaPrincipal() {
        setTitle("Sociedad de Almas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        contenedor.add(crearPanelMenu(), "MENU");
        contenedor.add(crearPanelInstrucciones(), "INST");
        contenedor.add(crearPanelRegistro(), "REG");

        add(contenedor);
        cardLayout.show(contenedor, "MENU");
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
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(45, 36, 30));
        JLabel titulo = new JLabel();
        titulo.setText("SOUL SOCIETY");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(fuentePropia().deriveFont(50f));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(0, 70, 800, 80);
        panel.add(titulo);

        JButton botonIniciar = new JButton();
        botonIniciar.setText("INICIAR");
        botonIniciar.setBounds(300, 250, 200, 50);
        botonIniciar.setFont(fuentePropia().deriveFont(16f));
        botonIniciar.setFocusable(false);
        botonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contenedor, "REG");
            }
        });
        panel.add(botonIniciar);

        JButton botonInstrucciones = new JButton();
        botonInstrucciones.setText("INSTRUCCIONES");
        botonInstrucciones.setBounds(300, 330, 200, 50);
        botonInstrucciones.setFont(fuentePropia().deriveFont(16f));
        botonInstrucciones.setFocusable(false);
        botonInstrucciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contenedor, "INST");
            }
        });
        panel.add(botonInstrucciones);

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
        panel.add(botonSalir);

        return panel;
    }

    private JPanel crearPanelRegistro() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(26, 95, 119));

        JPanel cuadro = new JPanel(null);
        cuadro.setBackground(new Color(26, 95, 119));
        cuadro.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        cuadro.setBounds(80, 60, 640, 450);
        panel.add(cuadro);

        JLabel labelNombre = new JLabel("NOMBRE DEL JUGADOR:");
        labelNombre.setFont(new Font("Quicksand", Font.BOLD, 15));
        labelNombre.setForeground(Color.WHITE);
        labelNombre.setBounds(30, 40, 220, 30);
        cuadro.add(labelNombre);

        JTextField campoNombre = new JTextField();
        campoNombre.setBounds(30, 75, 250, 30);
        cuadro.add(campoNombre);

        JLabel labelPersonaje = new JLabel("SELECCIONA TU PERSONAJE:");
        labelPersonaje.setForeground(Color.WHITE);
        labelPersonaje.setFont(new Font("Quicksand", Font.BOLD, 15));
        labelPersonaje.setBounds(30, 130, 280, 30);
        cuadro.add(labelPersonaje);

        JLabel imgIchigo = crearImagenPersonaje("juego/src/imagenes/ichigo.png", 30, 170);
        JLabel imgRukia = crearImagenPersonaje("juego/src/imagenes/rukia.png", 220, 170);
        JLabel imgRenji = crearImagenPersonaje("juego/src/imagenes/renji.png", 410, 170);
        cuadro.add(imgIchigo);
        cuadro.add(imgRukia);
        cuadro.add(imgRenji);

        JButton botonIchigo = new JButton("Ichigo");
        JButton botonRukia = new JButton("Rukia");
        JButton botonRenji = new JButton("Renji");
        botonIchigo.setBounds(30, 290, 100, 40);
        botonRukia.setBounds(220, 290, 100, 40);
        botonRenji.setBounds(410, 290, 100, 40);

        botonIchigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personajeSeleccionado = "Ichigo";
                marcarBoton(botonIchigo, botonRukia, botonRenji);
            }
        });

        botonRukia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personajeSeleccionado = "Rukia";
                marcarBoton(botonRukia, botonIchigo, botonRenji);
            }
        });

        botonRenji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personajeSeleccionado = "Renji";
                marcarBoton(botonRenji, botonIchigo, botonRukia);
            }
        });
        cuadro.add(botonIchigo);
        cuadro.add(botonRukia);
        cuadro.add(botonRenji);

        JButton botonEmpezar = new JButton("EMPEZAR BATALLA");
        botonEmpezar.setBounds(220, 370, 200, 50);
        botonEmpezar.addActionListener(e -> {
            String nombre = campoNombre.getText().trim().isEmpty() ? "x" : campoNombre.getText();

        });
        cuadro.add(botonEmpezar);

        return panel;
    }

    private JLabel crearImagenPersonaje(String ruta, int x, int y) {
        JLabel label = new JLabel();
        label.setBounds(x, y, 110, 115);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            ImageIcon icono = new ImageIcon(ruta);
            Image imagen = icono.getImage().getScaledInstance(100, 115, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(imagen));
        } catch (Exception ex) {
            label.setText("?");
            label.setForeground(Color.WHITE);
        }
        return label;
    }

    private void marcarBoton(JButton sel, JButton o1, JButton o2) {
        sel.setBackground(Color.orange);
        o1.setBackground(null);
        o2.setBackground(null);
    }

    private JPanel crearPanelInstrucciones() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(45, 45, 45));
        JButton volver = new JButton("VOLVER");
        volver.setBounds(300, 420, 180, 40);
        volver.addActionListener(e -> cardLayout.show(contenedor, "MENU"));
        panel.add(volver);
        return panel;
    }





    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new paginaPrincipal().setVisible(true));
    }
}