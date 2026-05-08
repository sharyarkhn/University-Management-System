package ProjectWithSherry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentFeeForm extends JFrame implements ActionListener {
    Choice CrollNumber;
    JComboBox<String> courseBox, departmentBox, semesterBox;
    JLabel totalAmount, textName, textfName;
    JButton pay, update, back;

    // ================= ROUND BUTTON CUSTOM CLASS =================
    class RoundButton extends JButton {
        public RoundButton(String label) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            super.paintComponent(g);
            g2.dispose();
        }
    }

    StudentFeeForm(){
        setSize(1200, 900);
        setLocation(250, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Ensure white.png exists in your resource path
        try {
            ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("white.png"));
            setIconImage(ic.getImage());
        } catch (Exception e) { System.out.println("Icon not found"); }

        getContentPane().setBackground(Color.WHITE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(13, 68, 89));
        topPanel.setBounds(0, 0, 1200, 60);
        add(topPanel);

        JLabel titleLabel = new JLabel("Student Fee Form", JLabel.CENTER);
        titleLabel.setBounds(0, 10, 1200, 40);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        topPanel.add(titleLabel);

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("fee.png"));
        Image i2 = i1.getImage().getScaledInstance(900, 600, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(100, 90, 900, 600);
        img.setLayout(null);
        add(img);

        int labelX = 500;
        int fieldX = 670;

        // Roll Number
        JLabel roolNumber = new JLabel("Select Roll Number");
        roolNumber.setBounds(labelX, 60, 150, 20);
        roolNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        img.add(roolNumber);

        CrollNumber = new Choice();
        CrollNumber.setBounds(fieldX, 60, 180, 25);
        img.add(CrollNumber);

        try {
            DatabaseConnection c = new DatabaseConnection();
            // FIXED: Using getStatement() instead of direct field access
            ResultSet rs = c.getStatement().executeQuery("select * from student");
            while (rs.next()){
                CrollNumber.add(rs.getString("rollno"));
            }
        } catch (Exception e){ e.printStackTrace(); }

        // Name
        JLabel name = new JLabel("Name");
        name.setBounds(labelX, 110, 150, 20);
        name.setForeground(new Color(13,68,89));
        name.setFont(new Font("Tahoma", Font.BOLD, 14));
        img.add(name);

        textName = new JLabel();
        textName.setBounds(fieldX, 110, 180, 20);
        textName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        img.add(textName);

        // Father Name
        JLabel fname = new JLabel("Father Name");
        fname.setBounds(labelX, 160, 150, 20);
        fname.setForeground(new Color(13,68,89));
        fname.setFont(new Font("Tahoma", Font.BOLD, 14));
        img.add(fname);

        textfName = new JLabel();
        textfName.setBounds(fieldX, 160, 180, 20);
        textfName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        img.add(textfName);

        // Course
        JLabel lblCourse = new JLabel("Course");
        lblCourse.setBounds(labelX, 210, 150, 20);
        lblCourse.setForeground(new Color(13,68,89));
        lblCourse.setFont(new Font("Tahoma", Font.BOLD, 14));
        img.add(lblCourse);

        String course[] = {"BBA" , "BSAF", "BSE" ,"BSBA", "BSCS", "BSAI" ,"BSSE" , "BSEE", "BSCSE" ,"BS-MATH","BSMC" , "BSPE", "B.ED"};
        courseBox = new JComboBox<>(course);
        courseBox.setBounds(fieldX, 210, 180, 25);
        courseBox.setBackground(Color.WHITE);
        img.add(courseBox);

        // Branch
        JLabel lblBranch = new JLabel("Branch");
        lblBranch.setBounds(labelX, 260, 150, 20);
        lblBranch.setForeground(new Color(13,68,89));
        lblBranch.setFont(new Font("Tahoma", Font.BOLD, 14));
        img.add(lblBranch);

        String department[] = { "Computer Science","Business Administration","Education","Electrical Engineering","Mathematics","Accounting & Finance","Physical Education","Media & Communication", "Technology"};
        departmentBox = new JComboBox<>(department);
        departmentBox.setBounds(fieldX, 260, 180, 25);
        departmentBox.setBackground(Color.WHITE);
        img.add(departmentBox);

        // Semester
        JLabel lblSemester = new JLabel("Semester");
        lblSemester.setBounds(labelX, 310, 150, 20);
        lblSemester.setForeground(new Color(13,68,89));
        lblSemester.setFont(new Font("Tahoma", Font.BOLD, 14));
        img.add(lblSemester);

        String semester[] = {"semester1", "semester2", "semester3","semester4","semester5", "semester6", "semester7","semester8"};
        semesterBox = new JComboBox<>(semester);
        semesterBox.setBounds(fieldX, 310, 180, 25);
        img.add(semesterBox);

        // Total Payable
        JLabel total = new JLabel("Total Payable");
        total.setBounds(labelX, 360, 150, 20);
        total.setForeground(new Color(13,68,89));
        total.setFont(new Font("Tahoma", Font.BOLD, 14));
        img.add(total);

        totalAmount = new JLabel("0");
        totalAmount.setBounds(fieldX, 360, 180, 20);
        totalAmount.setFont(new Font("Tahoma", Font.BOLD, 16));
        totalAmount.setForeground(new Color(13, 68, 89));
        img.add(totalAmount);

        fetchDetails();
        CrollNumber.addItemListener(ie -> fetchDetails());

        // Buttons
        update = new RoundButton("Update");
        update.setBounds(500, 450, 110, 35);
        update.setBackground(new Color(13, 68, 89));
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        img.add(update);

        pay = new RoundButton("Pay");
        pay.setBounds(620, 450, 110, 35);
        pay.setBackground(new Color(13, 68, 89));
        pay.setForeground(Color.WHITE);
        pay.addActionListener(this);
        img.add(pay);

        back = new RoundButton("Back");
        back.setBounds(740, 450, 110, 35);
        back.setBackground(new Color(13, 68, 89));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        img.add(back);

        setVisible(true);
    }

    private void fetchDetails() {
        try {
            DatabaseConnection c = new DatabaseConnection();
            PreparedStatement ps = c.prepare("SELECT * FROM student WHERE rollno = ?");
            ps.setString(1, CrollNumber.getSelectedItem());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                textName.setText(rs.getString("name"));
                textfName.setText(rs.getString("fname"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update){
            String course = (String) courseBox.getSelectedItem();
            String semester = (String) semesterBox.getSelectedItem();
            try {
                DatabaseConnection c = new DatabaseConnection();
                PreparedStatement ps2 = c.prepare("SELECT * FROM fee WHERE course = ?");
                ps2.setString(1, course);
                ResultSet rs = ps2.executeQuery();
                if(rs.next()) {
                    totalAmount.setText(rs.getString(semester));
                }
                rs.close(); ps2.close();
            } catch (Exception E) { E.printStackTrace(); }
        } else if (e.getSource() == pay) {
            try {
                DatabaseConnection c = new DatabaseConnection();
                PreparedStatement ps = c.prepare("INSERT INTO feecollege VALUES (?, ?, ?, ?, ?)");
                ps.setString(1, CrollNumber.getSelectedItem());
                ps.setString(2, (String) courseBox.getSelectedItem());
                ps.setString(3, (String) departmentBox.getSelectedItem());
                ps.setString(4, (String) semesterBox.getSelectedItem());
                ps.setString(5, totalAmount.getText());
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null , "Fee Submitted Successfully");
                setVisible(false);
            } catch (Exception E) { E.printStackTrace(); }
        } else if (e.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentFeeForm();
    }
}