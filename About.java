package ProjectWithSherry;
import javax.swing.*;
import java.awt.*;

public class About extends JFrame {
    About(){
        JPanel custom2DPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Cleans the panel before drawing
                // Cast to Graphics2D for advanced features

                Graphics2D g2d = (Graphics2D) g;
                // Smooth out edges (Anti-aliasing)


//                Turning on "HD Mode" (RenderingHints)
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                setRenderingHint,The command to change a graphics setting.
//                KEY_ANTIALIASING,The specific category (Edge Smoothing).
//                VALUE_ANTIALIAS_ON,The instruction to turn it on for better quality.

                // Draw a solid background
                g2d.setColor(Color.WHITE);

                g2d.fillRect(0, 0, getWidth(), getHeight());//it is used to fill the entire panel background
//                so you don't have a messy or "empty" screen.

                // Example of 2D drawing: A blue circle
                g2d.setColor(new Color(13, 68, 89));
                g2d.fillRoundRect(160, 120, 800, 450, 40, 40);//this is used
//                to set the curves proper.
            }
        };
        custom2DPanel.setLayout(null);// to add it in on proper size instead of by default
        custom2DPanel.setBounds(0, 0, 1540, 850);//since we are using nul layout we say here
//       to put that rounded panel on this panel
        add(custom2DPanel);


        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("user9.png"));
        Image img = icon.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setBounds(500, 190, 130, 130);
        custom2DPanel.add(imageLabel);

        JLabel lblSearch = new JLabel("UNIVERSITY MANAGEMENT SYSTEM");
        lblSearch.setBounds(285, 250, 700, 200);
        lblSearch.setFont(new Font("Arial", Font.BOLD, 30));
        lblSearch.setForeground(Color.WHITE);
        custom2DPanel.add(lblSearch);

        JLabel text = new JLabel("Shahariyar Haider");
        text.setBounds(450, 340, 400, 80);
        text.setFont(new Font("Arial", Font.PLAIN, 25));
        text.setForeground(Color.WHITE);
        custom2DPanel.add(text);

        JLabel text3 = new JLabel("\u2709\uFE0F shahariyar.bscsf25@iba-suk.edu.pk");
        text3.setBounds(435, 470, 500, 100);
        text3.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        text3.setForeground(Color.WHITE);
        custom2DPanel.add(text3);

        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("white.png"));
        setIconImage(ic.getImage());
        setSize(1130, 900);
        setLocation(250, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
    public static void main(String[] args) {
        new About();
    }
}
