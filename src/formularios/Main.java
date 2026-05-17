import javax.swing.*;

public class Main {
    public static void main(String[] args) {


        JFrame frame = new JFrame("Sociedad de Almas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        paginaPrincipal inicio = new paginaPrincipal();
        frame.setContentPane(inicio.getContenedorPrincipalInicio());
        frame.setVisible(true);


        /* String url = "jdbc:mysql://localhost:3306/sociedad_almas";
        String user = "root";
        String password = "";
        String usarSelect = "select *from personaje where nombre_personaje = ? ";

        try {
            Connection conexion = DriverManager.getConnection(url, user, password);

            PreparedStatement select = conexion.prepareStatement(usarSelect);
            select.setString(1, "ichigo");
            ResultSet nuevo = select.executeQuery();
            while (nuevo.next()) {
                System.out.println( nuevo.getInt(1) + "-" +
                        nuevo.getString(2));
            }
            nuevo.close();
            select.close();
            conexion.close();

        } catch (SQLException ex) {
            System.out.println("Error al guardar partida: ");

        } */
    }
}


