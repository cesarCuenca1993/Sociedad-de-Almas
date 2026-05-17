import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class paginaPrincipal {

    private JPanel contenedorPrincipalInicio;
    private JPanel panelIniciarMenu;
    private JLabel labelTituloInicio;
    private Font nuevaFuente;
    private JButton botonIniciar;
    private JButton botonInstrucciones;

    public JPanel getContenedorPrincipalInicio() {
        return contenedorPrincipalInicio;
    }

    public paginaPrincipal() {
        contenedorPrincipalInicio.setPreferredSize(new Dimension(800, 600));
        contenedorPrincipalInicio.setSize(new Dimension(800, 600));
        contenedorPrincipalInicio.setLayout(null);
        mostrarMenu();
    }

    private void mostrarMenu() {
        Fuente fuentePropia = new Fuente();
        nuevaFuente = fuentePropia.usarFuentePropia();
        panelIniciarMenu = new JPanel(null);
        panelIniciarMenu.setLocation(0, 30);
        panelIniciarMenu.setSize(800, 600);
        panelIniciarMenu.setBackground(new Color(58, 95, 119));
        contenedorPrincipalInicio.add(panelIniciarMenu);

        labelTituloInicio = new JLabel();
        labelTituloInicio.setText("Sociedad de Almas");
        labelTituloInicio.setFont(nuevaFuente.deriveFont(50F));
        labelTituloInicio.setForeground(Color.white);
        labelTituloInicio.setHorizontalAlignment(SwingConstants.CENTER);
        labelTituloInicio.setBounds(0, 70, 800, 60);
        panelIniciarMenu.add(labelTituloInicio);

        botonIniciar = new JButton("INICIAR");
        botonIniciar.setBounds(300, 200, 200, 50);
        botonIniciar.setFont(nuevaFuente.deriveFont(16F));
        botonIniciar.setFocusable(false);
        panelIniciarMenu.add(botonIniciar);

        botonIniciar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                paginaRegistro registro = new paginaRegistro();

                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(botonIniciar);
                frame.setContentPane(registro.getContenedorPaginaRegistro());
                frame.revalidate();
                frame.repaint();
            }
        });


        botonInstrucciones = new JButton("INSTRUCCIONES");
        botonInstrucciones.setBounds(300, 280, 200, 50);
        botonInstrucciones.setFont(nuevaFuente.deriveFont(16f));
        botonInstrucciones.setFocusable(false);

        botonInstrucciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paginaInstruccion instruccion = new paginaInstruccion();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(botonInstrucciones);
                frame.setContentPane(instruccion.getPanelInstruccion());
                frame.revalidate();
                frame.repaint();
            }
        });
        panelIniciarMenu.add(botonInstrucciones);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Inicio");
        frame.setContentPane(new paginaPrincipal().contenedorPrincipalInicio);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


    }
}