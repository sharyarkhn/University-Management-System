package ProjectWithSherry;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    // ========== ENCAPSULATION: private fields ==========
    private JTextField textFieldName;
    private JPasswordField passwordField;
    private JButton login;

    // ========== GETTERS ==========
    public String getUsername() { return textFieldName.getText(); }
    public String getPassword() { return new String(passwordField.getPassword()); }

    Login() {
        setTitle("University Management System");
        setSize(1540, 850);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("user.png"));
        setIconImage(ic.getImage());

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setBounds(0, 0, 1540, 850);
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        int cardWidth = 700, cardHeight = 450;
        int cardX = (1400 - cardWidth) / 2, cardY = (720 - cardHeight) / 2;

        JPanel cardPanel = new JPanel();
        cardPanel.setBounds(cardX, cardY, cardWidth, cardHeight);
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setLayout(null);
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        backgroundPanel.add(cardPanel);

        JPanel topPanel1 = new JPanel(); topPanel1.setLayout(null);
        topPanel1.setBackground(new Color(13, 68, 89)); topPanel1.setBounds(0, 0, 1400, 13);
        backgroundPanel.add(topPanel1);

        JPanel topPanel2 = new JPanel(); topPanel2.setLayout(null);
        topPanel2.setBackground(new Color(13, 68, 89)); topPanel2.setBounds(0, 730, 1400, 13);
        backgroundPanel.add(topPanel2);

        JPanel topPanel3 = new JPanel(); topPanel3.setLayout(null);
        topPanel3.setBackground(new Color(13, 68, 89)); topPanel3.setBounds(0, 0, 15, 1000);
        backgroundPanel.add(topPanel3);

        JPanel topPanel4 = new JPanel(); topPanel4.setLayout(null);
        topPanel4.setBackground(new Color(13, 68, 89)); topPanel4.setBounds(1355, 0, 15, 1000);
        backgroundPanel.add(topPanel4);

        JLabel titleLabel = new JLabel("UNIVERSITY MANAGEMENT SYSTEM");
        titleLabel.setBounds(50, 80, 240, 30);
        titleLabel.setForeground(new Color(13, 68, 89));
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardPanel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Secure Login Portal");
        subtitleLabel.setBounds(40, 110, 240, 20);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        subtitleLabel.setForeground(new Color(13, 68, 89));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardPanel.add(subtitleLabel);

        JLabel titleLabel1 = new JLabel("Hey there!!");
        titleLabel1.setBounds(420, 80, 240, 30);
        titleLabel1.setForeground(Color.WHITE);
        titleLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        cardPanel.add(titleLabel1);

        JLabel titleLabel2 = new JLabel("Begin your amazing journey by");
        titleLabel2.setBounds(420, 130, 240, 30);
        titleLabel2.setForeground(Color.WHITE);
        titleLabel2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        cardPanel.add(titleLabel2);

        JLabel titleLabel3 = new JLabel("      an account with us today");
        titleLabel3.setBounds(420, 155, 240, 30);
        titleLabel3.setForeground(Color.WHITE);
        titleLabel3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        cardPanel.add(titleLabel3);

        JLabel labelName = new JLabel("Username");
        labelName.setBounds(40, 160, 280, 25);
        labelName.setForeground(new Color(13, 68, 89));
        labelName.setFont(new Font("Arial", Font.PLAIN, 13));
        cardPanel.add(labelName);

        textFieldName = new JTextField();
        textFieldName.setBounds(40, 180, 280, 35);
        textFieldName.setFont(new Font("Arial", Font.PLAIN, 13));
        textFieldName.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        cardPanel.add(textFieldName);

        JLabel labelpass = new JLabel("Password");
        labelpass.setBounds(40, 230, 280, 25);
        labelpass.setForeground(new Color(13, 68, 89));
        labelpass.setFont(new Font("Arial", Font.PLAIN, 13));
        cardPanel.add(labelpass);

        passwordField = new JPasswordField();
        passwordField.setBounds(40, 250, 280, 35);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 13));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        cardPanel.add(passwordField);

        login = new JButton("Login") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isPressed() || getModel().isRollover()
                        ? new Color(0x072942) : new Color(13, 68, 89));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 30);
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Arial", Font.BOLD, 14));
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(getText(), (getWidth() - fm.stringWidth(getText())) / 2,
                        (getHeight() + fm.getAscent() - fm.getDescent()) / 2);
                g2.dispose();
            }
        };
        login.setBounds(80, 320, 200, 45);
        login.setFont(new Font("Arial", Font.BOLD, 14));
        login.setForeground(Color.WHITE);
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.setFocusPainted(false);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.addActionListener(this);
        cardPanel.add(login);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("looogo.png"));
        Image img = icon.getImage().getScaledInstance(700, 450, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setBounds(0, 0, 700, 450);
        cardPanel.add(imageLabel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            // Using getters — encapsulated access
            String username = getUsername();
            String password = getPassword();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter username and password",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // ========== PreparedStatement: fixes SQL injection in Login ==========
                DatabaseConnection c = new DatabaseConnection();
                PreparedStatement ps = c.prepare(
                        "SELECT * FROM login WHERE username = ? AND password = ?");
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet resultSet = ps.executeQuery();

                if (resultSet.next()) {
                    setVisible(false);
                    new Main();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
                resultSet.close();
                ps.close();
            } catch (Exception E) {
                E.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
