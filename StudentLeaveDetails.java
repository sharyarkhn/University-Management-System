package ProjectWithSherry;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class RoundButtonDetails extends JButton {
    public RoundButtonDetails(String label) {
        super(label);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isPressed()) g2.setColor(getBackground().darker());
        else g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
        g2.dispose();
    }
}

public class StudentLeaveDetails extends JFrame implements ActionListener {

    Choice choiceRollNo;
    JTable table;
    JButton search, cancel, print;

    StudentLeaveDetails() {
        setTitle("Student Leave Records");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        try {
            ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("white.png"));
            setIconImage(ic.getImage());
        } catch (Exception e) { /* Icon optional */ }

        // ================= DECORATIVE PANELS =================
        JPanel topPanelp = new JPanel();
        topPanelp.setBackground(new Color(13, 68, 89));
        topPanelp.setBounds(780, 130, 3, 670);
        add(topPanelp);

        JPanel topPanelp2 = new JPanel();
        topPanelp2.setBackground(new Color(13, 68, 89));
        topPanelp2.setBounds(400, 130, 3, 670);
        add(topPanelp2);

        JPanel topPanel01 = new JPanel();
        topPanel01.setBackground(new Color(13, 68, 89));
        topPanel01.setBounds(1120, 50, 20, 1000);
        add(topPanel01);

        JPanel topPanel0 = new JPanel();
        topPanel0.setBackground(new Color(13, 68, 89));
        topPanel0.setBounds(0, 50, 20, 1000);
        add(topPanel0);

        JPanel topPanel2 = new JPanel();
        topPanel2.setBackground(new Color(13, 68, 89));
        topPanel2.setBounds(0, 130, 1200, 5);
        add(topPanel2);

        JPanel topPanel1 = new JPanel();
        topPanel1.setBackground(new Color(13, 68, 89));
        topPanel1.setBounds(0, 150, 1200, 5);
        add(topPanel1);

        // ================= TOP BAR =================
        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(13, 68, 89));
        topPanel.setBounds(0, 0, 1200, 60);
        add(topPanel);

        // Fixed: Use CENTER alignment instead of manual spaces
        JLabel titleLabel = new JLabel("Student Leave Records", SwingConstants.CENTER);
        titleLabel.setBounds(0, 10, 1200, 40);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        topPanel.add(titleLabel);

        // ================= SEARCH AREA =================
        JLabel heading = new JLabel("Search by Roll Number:");
        heading.setBounds(35, 80, 200, 25);
        heading.setFont(new Font("Tahoma", Font.BOLD, 16));
        heading.setForeground(new Color(13, 68, 89));
        add(heading);

        choiceRollNo = new Choice();
        choiceRollNo.setBounds(235, 82, 150, 25);
        add(choiceRollNo);

        // Fetch Roll Numbers using PreparedStatement
        try {
            DatabaseConnection c = new DatabaseConnection();
            PreparedStatement ps = c.prepare("SELECT rollno FROM student");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                choiceRollNo.add(resultSet.getString("rollno"));
            }
            ps.close();
        } catch (Exception e) { e.printStackTrace(); }

        // ================= BUTTONS =================
        search = new RoundButtonDetails("Search");
        search.setBounds(850, 80, 100, 25);
        search.setBackground(new Color(13, 68, 89));
        search.setForeground(Color.WHITE);
        search.addActionListener(this);
        add(search);

        cancel = new RoundButtonDetails("Cancel");
        cancel.setBounds(1000, 80, 100, 25);
        cancel.setBackground(new Color(13, 68, 89));
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        // ================= TABLE AREA =================
        table = new JTable();
        table.setRowHeight(30);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setGridColor(new Color(13, 68, 89));

        // Initial table load using PreparedStatement
        refreshTable("SELECT * FROM studentleave", null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 130, 1145, 700);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);

        setSize(1200, 900);
        setLocation(220, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Helper method to refresh table data safely
    private void refreshTable(String query, String param) {
        try {
            DatabaseConnection c = new DatabaseConnection();
            PreparedStatement ps = c.prepare(query);
            if (param != null) {
                ps.setString(1, param);
            }
            ResultSet resultSet = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            ps.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            refreshTable("SELECT * FROM studentleave WHERE rollno = ?", choiceRollNo.getSelectedItem());
        } else if (e.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentLeaveDetails();
    }
}