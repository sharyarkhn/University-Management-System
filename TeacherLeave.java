package ProjectWithSherry;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// ================= ROUND BUTTON =================
class RoundButtonTeacher extends JButton {
    public RoundButtonTeacher(String label) {
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
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g);
        g2.dispose();
    }
}

// ================= ROUND PANEL =================
class RoundPanelTeacher extends JPanel {
    private int radius;
    public RoundPanelTeacher(int radius) {
        this.radius = radius;
        setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.dispose();
    }
}

public class TeacherLeave extends JFrame implements ActionListener {
    Choice choiceEmpID, choTime;
    JDateChooser selDate;
    JButton submit, cancel;

    TeacherLeave() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Icon Fix
        try {
            ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("white.png"));
            setIconImage(ic.getImage());
        } catch (Exception e) { System.out.println("Icon white.png not found"); }

        // ================= TOP BAR =================
        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(13, 68, 89));
        topPanel.setBounds(50, 30, 1000, 60);
        add(topPanel);

        JLabel titleLabel = new JLabel("Apply Teacher Leave", JLabel.CENTER);
        titleLabel.setBounds(0, 10, 1000, 40);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        topPanel.add(titleLabel);

        // ================= CENTER CARD =================
        RoundPanelTeacher cardPanel = new RoundPanelTeacher(50);
        cardPanel.setLayout(null);
        cardPanel.setBackground(new Color(13, 68, 89));
        cardPanel.setBounds(250, 300, 600, 350);
        add(cardPanel);

        // Image Placeholder
        try {
            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("facultyLeave.png"));
            Image img = icon.getImage().getScaledInstance(620, 200, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(img));
            imageLabel.setBounds(235, 100, 620, 200);
            add(imageLabel);
        } catch (Exception e) { System.out.println("facultyLeave.png not found"); }

        // ================= FORM ELEMENTS =================
        JLabel lblEmpID = new JLabel("Search Employee ID");
        lblEmpID.setBounds(70, 50, 200, 30);
        lblEmpID.setForeground(Color.WHITE);
        lblEmpID.setFont(new Font("Tahoma", Font.BOLD, 16));
        cardPanel.add(lblEmpID);

        choiceEmpID = new Choice();
        choiceEmpID.setBounds(280, 50, 250, 30);
        cardPanel.add(choiceEmpID);

        JLabel lbldate = new JLabel("Leave Date");
        lbldate.setBounds(70, 120, 200, 30);
        lbldate.setForeground(Color.WHITE);
        lbldate.setFont(new Font("Tahoma", Font.BOLD, 16));
        cardPanel.add(lbldate);

        selDate = new JDateChooser();
        selDate.setBounds(280, 120, 250, 20);
        cardPanel.add(selDate);

        JLabel timeLabel = new JLabel("Time Duration");
        timeLabel.setBounds(70, 190, 200, 30);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        cardPanel.add(timeLabel);

        choTime = new Choice();
        choTime.setBounds(280, 190, 250, 30);
        choTime.add("Full Day");
        choTime.add("Half Day");
        cardPanel.add(choTime);

        // ================= BUTTONS =================
        submit = new RoundButtonTeacher("Submit");
        submit.setBounds(160, 260, 120, 35);
        submit.setBackground(Color.WHITE);
        submit.setForeground(new Color(13, 68, 89));
        submit.setFont(new Font("Tahoma", Font.BOLD, 14));
        submit.addActionListener(this);
        cardPanel.add(submit);

        cancel = new RoundButtonTeacher("Cancel");
        cancel.setBounds(310, 260, 120, 35);
        cancel.setBackground(Color.WHITE);
        cancel.setForeground(new Color(13, 68, 89));
        cancel.setFont(new Font("Tahoma", Font.BOLD, 14));
        cancel.addActionListener(this);
        cardPanel.add(cancel);

        // Database Fetch for Teachers - FIXED with PreparedStatement
        try {
            DatabaseConnection c = new DatabaseConnection();
            PreparedStatement ps = c.prepare("SELECT empId FROM teacher");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                choiceEmpID.add(resultSet.getString("empId"));
            }
            resultSet.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(1200, 900);
        setLocation(250, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String empID = choiceEmpID.getSelectedItem();
            String date = ((JTextField) selDate.getDateEditor().getUiComponent()).getText();
            String time = choTime.getSelectedItem();

            if (date.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a date");
                return;
            }

            try {
                DatabaseConnection c = new DatabaseConnection();
                PreparedStatement ps = c.prepare("INSERT INTO teacherleave VALUES (?, ?, ?)");
                ps.setString(1, empID);
                ps.setString(2, date);
                ps.setString(3, time);
                ps.executeUpdate();
                ps.close();

                JOptionPane.showMessageDialog(null, "Leave Confirmed");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new TeacherLeave();
    }
}