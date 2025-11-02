import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    int width = 360;
    int height = 640;

    private Logic logic;
    private Image backgroundImage; // 🏞️ background image

    // constructor
    public View(Logic logic) {
        this.logic = logic;
        setPreferredSize(new Dimension(width, height));

        // 🖼️ load background
        backgroundImage = new ImageIcon(getClass().getResource("assets/neraka_background.jpeg")).getImage();

        setFocusable(true);
        addKeyListener(logic);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 🏞️ gambar background di layar (isi penuh jendela)
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

        // gambar elemen-elemen game
        draw(g);

        // tampilkan skor di pojok kiri atas
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.setColor(Color.WHITE);
        g.drawString("Score: " + logic.getScore(), 20, 40);

        // tampilkan teks "Game Over" dan instruksi
        if (logic.isGameOver()) {
            String text1 = "GAME OVER";
            String text2 = "Press R to Restart";
            String text3 = "Press M to Menu";

            g.setFont(new Font("Arial", Font.BOLD, 40));
            FontMetrics fm1 = g.getFontMetrics();
            int textWidth1 = fm1.stringWidth(text1);
            int x1 = (getWidth() - textWidth1) / 2;
            int y1 = getHeight() / 2 - 20;

            g.setColor(Color.RED);
            g.drawString(text1, x1, y1);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            FontMetrics fm2 = g.getFontMetrics();
            int textWidth2 = fm2.stringWidth(text2);
            int x2 = (getWidth() - textWidth2) / 2;
            int y2 = y1 + 40;

            g.setColor(Color.WHITE);
            g.drawString(text2, x2, y2);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            FontMetrics fm3 = g.getFontMetrics();
            int textWidth3 = fm3.stringWidth(text3);
            int x3 = (getWidth() - textWidth3) / 2;
            int y3 = y2 + 40;

            g.setColor(Color.WHITE);
            g.drawString(text3, x3, y3);
        }
    }

    public void draw(Graphics g) {
        // gambar player
        Player player = logic.getPlayer();
        if (player != null) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(),
                    player.getWidth(), player.getHeight(), null);
        }

        // gambar semua pipa
        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null) {
            for (Pipe pipe : pipes) {
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(),
                        pipe.getWidth(), pipe.getHeight(), null);
            }
        }
    }
}
