import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu {
    private JPanel mainPanel;
    private JButton startButton;
    private JButton exitButton;
    private Image backgroundImage;

    public StartMenu(JFrame frame) {
        // === Load gambar background ===
        backgroundImage = new ImageIcon(getClass().getResource("assets/neraka_background.jpeg")).getImage();

        // === Panel utama dengan gambar ===
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Gambar background memenuhi layar
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // === Komponen UI ===
        JLabel title = new JLabel("Flappy Bird");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.WHITE);
        title.setOpaque(false);

        startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // === Spasi dan tata letak ===
        mainPanel.add(Box.createVerticalStrut(200));
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(startButton);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(exitButton);

        // === Tombol Start ===
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();

                Logic logic = new Logic();
                View view = new View(logic);
                logic.setView(view);
                frame.add(view);
                frame.revalidate();
                frame.repaint();
                view.requestFocusInWindow();
            }
        });

        // === Tombol Exit ===
        exitButton.addActionListener(e -> System.exit(0));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
