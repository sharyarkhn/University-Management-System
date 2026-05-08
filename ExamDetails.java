package ProjectWithSherry;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class RoundButtonExam extends JButton {
    public RoundButtonExam(String label) {
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

public class ExamDetails extends JFrame implements ActionListener {

    JTextField search;
    JButton result, back;
    JTable table;

    ExamDetails() {
        setTitle("Exam Details");
        getContentPane().setBackground(new Color(13, 68, 89));
        setLayout(null);

        // Header Image
        try {
            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("border.png"));
            Image img = icon.getImage().getScaledInstance(500, 60, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(img));
            imageLabel.setBounds(320, 10, 500, 70);
            add(imageLabel);
        } catch (Exception e) { System.out.println("border.png missing"); }

        // Search Bar Container
        JPanel topPanel2 = new JPanel();
        topPanel2.setLayout(null);
        topPanel2.setOpaque(false);
        topPanel2.setBounds(145, 90, 800, 75);
        add(topPanel2);

        JLabel lblSearch = new JLabel("Search Roll Number:");
        lblSearch.setBounds(35, 22, 200, 30);
        lblSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblSearch.setForeground(new Color(13, 68, 89));
        topPanel2.add(lblSearch);

        search = new JTextField();
        search.setBounds(210, 22, 200, 30);
        search.setFont(new Font("Tahoma", Font.PLAIN, 18));
        search.setBackground(new Color(13, 68, 89));
        search.setForeground(Color.WHITE);
        topPanel2.add(search);

        result = new RoundButtonExam("Result");
        result.setBounds(500, 22, 120, 30);
        result.setBackground(new Color(13, 68, 89));
        result.setForeground(Color.WHITE);
        result.addActionListener(this);
        topPanel2.add(result);

        back = new RoundButtonExam("Back");
        back.setBounds(630, 22, 120, 30);
        back.setBackground(new Color(13, 68, 89));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        topPanel2.add(back);

        // Background Image for Search Bar
        try {
            ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("border4.png"));
            Image img2 = icon2.getImage().getScaledInstance(800, 75, Image.SCALE_SMOOTH);
            JLabel imageLabel2 = new JLabel(new ImageIcon(img2));
            imageLabel2.setBounds(0, 0, 800, 75);
            topPanel2.add(imageLabel2);
        } catch (Exception e) { System.out.println("border4.png missing"); }

        // Table Area
        table = new JTable();
        table.setRowHeight(30);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setGridColor(new Color(13, 68, 89));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Load Table Data using Prepared Statement
        loadTableData();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(25, 200, 1100, 600);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);

        // Row Selection Logic
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                // Ensure index exists. Column 0 is usually rollno in 'marks' table
                search.setText(table.getModel().getValueAt(row, 0).toString());
            }
        });

        // Frame Settings
        try {
            ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("white.png"));
            setIconImage(ic.getImage());
        } catch (Exception e) {}

        setSize(1200, 900);
        setLocation(200, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadTableData() {
        try {
            DatabaseConnection c = new DatabaseConnection();
            PreparedStatement ps = c.prepare("SELECT * FROM marks");
            ResultSet resultSet = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            resultSet.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == result) {
            if (search.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select or enter a Roll Number");
            } else {
                setVisible(false);
                // Passing the roll number to the Marks class
                new Marks(search.getText());
            }
        } else if (e.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new ExamDetails();
    }
}