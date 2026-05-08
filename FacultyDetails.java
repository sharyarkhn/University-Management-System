package ProjectWithSherry;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Reusing the Custom Round Button for design consistency
class RoundButtonFaculty extends JButton {
    public RoundButtonFaculty(String label) {
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

public class FacultyDetails extends JFrame implements ActionListener {

    Choice choice;
    JTable table;
    JButton search, print, update, add, cancel;

    FacultyDetails() {
        setTitle("Faculty Details");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        try {
            ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("white.png"));
            setIconImage(ic.getImage());
        } catch (Exception e) {}

        // Decorative Panels
        JPanel topPanel01 = new JPanel();
        topPanel01.setBackground(new Color(13, 68, 89));
        topPanel01.setBounds(0, 50, 20, 1000);
        add(topPanel01);

        JPanel topPanel2 = new JPanel();
        topPanel2.setBackground(new Color(13, 68, 89));
        topPanel2.setBounds(0, 130, 1200, 5);
        add(topPanel2);

        JPanel topPanel1 = new JPanel();
        topPanel1.setBackground(new Color(13, 68, 89));
        topPanel1.setBounds(0, 150, 1200, 5);
        add(topPanel1);

        // Header
        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(13, 68, 89));
        topPanel.setBounds(0, 0, 1200, 60);
        add(topPanel);

        JLabel titleLabel = new JLabel("Faculty Details", JLabel.CENTER);
        titleLabel.setBounds(0, 10, 1200, 40);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        topPanel.add(titleLabel);

        // Search Area
        JLabel heading = new JLabel("Search by Employee ID:");
        heading.setBounds(35, 80, 200, 25);
        heading.setFont(new Font("Tahoma", Font.BOLD, 16));
        heading.setForeground(new Color(13, 68, 89));
        add(heading);

        choice = new Choice();
        choice.setBounds(235, 82, 150, 25);
        add(choice);

        // Fetch IDs using PreparedStatement
        try {
            DatabaseConnection c = new DatabaseConnection();
            PreparedStatement ps = c.prepare("SELECT empId FROM teacher");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                choice.add(resultSet.getString("empId"));
            }
            ps.close();
        } catch (Exception e) { e.printStackTrace(); }

        // Buttons
        search = createBtn("Search", 710);
        add = createBtn("Add", 830);
        update = createBtn("Update", 940);
        cancel = createBtn("Cancel", 1050);

        // Table Area
        table = new JTable();
        table.setRowHeight(30);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setGridColor(new Color(13, 68, 89));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Load initial table data
        refreshTable("SELECT * FROM teacher", null);

        JScrollPane js = new JScrollPane(table);
        js.setBounds(20, 130, 1135, 400);
        js.getViewport().setBackground(Color.WHITE);
        js.setBorder(BorderFactory.createEmptyBorder());
        add(js);

        setSize(1200, 900);
        setLocation(204, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private RoundButtonFaculty createBtn(String label, int x) {
        RoundButtonFaculty btn = new RoundButtonFaculty(label);
        btn.setBounds(x, 80, 100, 25);
        btn.setBackground(new Color(13, 68, 89));
        btn.setForeground(Color.WHITE);
        btn.addActionListener(this);
        add(btn);
        return btn;
    }

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
            refreshTable("SELECT * FROM teacher WHERE empId = ?", choice.getSelectedItem());
        } else if (e.getSource() == add) {
            setVisible(false);
            new Faculty(); // Ensure you have a Faculty.java class
        } else if (e.getSource() == update) {
            JOptionPane.showMessageDialog(null, "Update Logic Here");
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new FacultyDetails();
    }
}