import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class paginaInstruccion {

    private JPanel panelInstruccion;
    private Font nuevaFuente;
    private JButton botonVolver;
    private JLabel etiquetaMostrarInstruccion;

    public JPanel getPanelInstruccion() {
        return this.panelInstruccion;
    }

    public paginaInstruccion() {
        panelInstruccion = new JPanel(null);
        panelInstruccion.setPreferredSize(new Dimension(800, 600));
        panelInstruccion.setSize(new Dimension(800, 600));
        panelInstruccion.setBackground(new Color(58, 95, 119));
        mostrarInstrucciones();
    }

    public void mostrarInstrucciones() {

        Fuente fuentePropia = new Fuente();
        nuevaFuente = fuentePropia.usarFuentePropia();

        etiquetaMostrarInstruccion = new JLabel();
        etiquetaMostrarInstruccion.setFont(nuevaFuente.deriveFont(16F));
        etiquetaMostrarInstruccion.setForeground(Color.white);
        etiquetaMostrarInstruccion.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaMostrarInstruccion.setBounds(100, 50, 500, 350);
        etiquetaMostrarInstruccion.setText(
                "<html>" +
                        "<b>INSTRUCCIONES:</b><br>" +
                        "- Usa las flechas del teclado para moverte.<br><br>" +
                        "&nbsp;&nbsp;Arriba &uarr;<br>" +
                        "&nbsp;&nbsp;Abajo &darr;<br>" +
                        "&nbsp;&nbsp;Izquierda &larr;<br>" +
                        "&nbsp;&nbsp;Derecha &rarr;<br><br>" +
                        "- Presiona la tecla <b>'a'</b> para lanzar Kido.<br>" +
                        "- Derrota a todos los enemigos para ganar." +
                        "</html>"
        );
        panelInstruccion.add(etiquetaMostrarInstruccion);

        botonVolver = new JButton("VOLVER");
        botonVolver.setBounds(300, 450, 200, 50);
        botonVolver.setFont(nuevaFuente.deriveFont(16F));
        botonVolver.setFocusable(false);
        botonVolver.addActionListener(new BotonVolverListener());
        panelInstruccion.add(botonVolver);
    }

    private class BotonVolverListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            paginaPrincipal menuInicio = new paginaPrincipal();

            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(botonVolver);
            frame.setContentPane(menuInicio.getContenedorPrincipalInicio());
            frame.revalidate();
            frame.repaint();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Instrucciones");
        frame.setContentPane(new paginaInstruccion().panelInstruccion);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}