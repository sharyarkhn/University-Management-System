package ProjectWithSherry;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.util.Random;

public class Student extends JFrame implements ActionListener {

    // ========== ENCAPSULATION: private fields ==========
    private JTextField textName, textfather, textAddress, textPhone,
            textemail, textM10, textM12, CNIC;
    private JLabel empText;
    private JDateChooser cdob;
    private JComboBox<String> courseBox, departmentBox;
    private JButton submit, cancel;
    private Random ran = new Random();
    private long f4 = Math.abs((ran.nextLong() % 9000L) + 1000L);

    // ========== GETTERS ==========
    public String getStudentName()    { return textName.getText(); }
    public String getFatherName()     { return textfather.getText(); }
    public String getRollNumber()     { return empText.getText(); }
    public String getAddress()        { return textAddress.getText(); }
    public String getPhone()          { return textPhone.getText(); }
    public String getEmail()          { return textemail.getText(); }
    public String getClassX()         { return textM10.getText(); }
    public String getClassXII()       { return textM12.getText(); }
    public String getCnic()           { return CNIC.getText(); }
    public String getSelectedCourse() { return (String) courseBox.getSelectedItem(); }
    public String getSelectedBranch() { return (String) departmentBox.getSelectedItem(); }
    public String getDob()            { return ((JTextField) cdob.getDateEditor().getUiComponent()).getText(); }

    // ================= ROUND PANEL CUSTOM CLASS =================
    class RoundPanel extends JPanel {
        private int cornerRadius;
        public RoundPanel(int radius) {
            this.cornerRadius = radius;
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            g2.dispose();
        }
    }

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

    Student() {
        setSize(1200, 900);
        setLocation(250, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("studenttoplogo.png"));
        Image img1 = icon1.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
        JLabel imageLabel1 = new JLabel(new ImageIcon(img1));
        imageLabel1.setBounds(380, -10, 400, 70);
        add(imageLabel1);

        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("white.png"));
        setIconImage(ic.getImage());
        getContentPane().setBackground(Color.WHITE);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("studentL.png"));
        Image img = icon.getImage().getScaledInstance(850, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setBounds(150, 60, 850, 200);
        add(imageLabel);

        RoundPanel formPanel = new RoundPanel(50);
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(13, 68, 89));
        formPanel.setBounds(150, 280, 850, 430);
        add(formPanel);

        formPanel.add(createLabel("Name", 70, 40));
        textName = createField(200, 40, formPanel);

        formPanel.add(createLabel("Father Name", 470, 40));
        textfather = createField(610, 40, formPanel);

        formPanel.add(createLabel("Roll Number", 70, 90));
        empText = new JLabel("1409" + f4);
        empText.setBounds(200, 90, 150, 30);
        empText.setForeground(Color.WHITE);
        empText.setFont(new Font("serif", Font.BOLD, 18));
        formPanel.add(empText);

        formPanel.add(createLabel("Date of Birth", 470, 90));
        cdob = new JDateChooser();
        cdob.setBounds(610, 90, 150, 30);
        formPanel.add(cdob);

        formPanel.add(createLabel("Address", 70, 140));
        textAddress = createField(200, 140, formPanel);

        formPanel.add(createLabel("Phone", 470, 140));
        textPhone = createField(610, 140, formPanel);

        formPanel.add(createLabel("Email", 70, 190));
        textemail = createField(200, 190, formPanel);

        formPanel.add(createLabel("Class X (%)", 470, 190));
        textM10 = createField(610, 190, formPanel);

        formPanel.add(createLabel("Class XII (%)", 70, 240));
        textM12 = createField(200, 240, formPanel);

        formPanel.add(createLabel("CNIC", 470, 240));
        CNIC = createField(610, 240, formPanel);

        formPanel.add(createLabel("Course", 70, 290));
        String course[] = {"BBA", "BSAF", "BSE", "BSBA", "BBA", "BSCS", "BSAI", "CSAI", "BSSE", "BSEE", "BSCSE", "BS-MATH", "BSMC", "BSPE", "B.ED"};
        courseBox = new JComboBox<>(course);
        courseBox.setBounds(200, 290, 150, 30);
        formPanel.add(courseBox);

        formPanel.add(createLabel("Branch", 470, 290));
        String department[] = {"Computer Science", "Business Administration", "Education", "Electrical Engineering", "Mathematics", "Accounting & Finance", "Physical Education", "Media & Communication", "Technology"};
        departmentBox = new JComboBox<>(department);
        departmentBox.setBounds(610, 290, 150, 30);
        formPanel.add(departmentBox);

        submit = new RoundButton("Submit");
        submit.setBounds(275, 360, 120, 35);
        submit.setBackground(Color.WHITE);
        submit.setForeground(new Color(13, 68, 89));
        submit.setFont(new Font("Tahoma", Font.BOLD, 14));
        submit.addActionListener(this);
        formPanel.add(submit);

        cancel = new RoundButton("Cancel");
        cancel.setBounds(435, 360, 120, 35);
        cancel.setBackground(Color.WHITE);
        cancel.setForeground(new Color(13, 68, 89));
        cancel.setFont(new Font("Tahoma", Font.BOLD, 14));
        cancel.addActionListener(this);
        formPanel.add(cancel);

        setVisible(true);
    }

    JLabel createLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 150, 30);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("serif", Font.BOLD, 18));
        return lbl;
    }

    JTextField createField(int x, int y, JPanel panel) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, 150, 30);
        panel.add(tf);
        return tf;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            // Using getters — separation of concerns (data accessed via encapsulated getters)
            String name     = getStudentName();
            String fname    = getFatherName();
            String rollno   = getRollNumber();
            String dob      = getDob();
            String address  = getAddress();
            String phone    = getPhone();
            String email    = getEmail();
            String x        = getClassX();
            String xii      = getClassXII();
            String cnic     = getCnic();
            String course   = getSelectedCourse();
            String branch   = getSelectedBranch();

            try {
                // ========== PreparedStatement: fixes SQL injection ==========
                DatabaseConnection c = new DatabaseConnection();
                PreparedStatement ps = c.prepare(
                        "INSERT INTO student VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, name);
                ps.setString(2, fname);
                ps.setString(3, rollno);
                ps.setString(4, dob);
                ps.setString(5, address);
                ps.setString(6, phone);
                ps.setString(7, email);
                ps.setString(8, x);
                ps.setString(9, xii);
                ps.setString(10, cnic);
                ps.setString(11, course);
                ps.setString(12, branch);
                ps.executeUpdate();
                ps.close();

                JOptionPane.showMessageDialog(null, "Student Details Inserted");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Student();
    }
}
