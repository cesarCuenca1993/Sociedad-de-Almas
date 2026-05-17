import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fuente {
    private Font fuentePropia;



    public Font getFuentePropia() {
        return this.fuentePropia;
    }

    public Font usarFuentePropia() {
        Font fuentePersonalizada;
        try {
            File fontFile = new File("src/Fuentes/Quicksand-SemiBold.ttf");

            fuentePersonalizada = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fuentePersonalizada);

        } catch (IOException | FontFormatException e) {
            System.out.println("Error al cargar fuente propia, asi que se usara Arial");
            fuentePersonalizada = new Font("Arial", Font.BOLD, 12);

        }
        return fuentePersonalizada;
    }
}
