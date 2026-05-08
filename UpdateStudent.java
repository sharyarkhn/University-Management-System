package ProjectWithSherry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateStudent extends JFrame implements ActionListener {

    // ========== ENCAPSULATION: private fields ==========
    private JTextField textAddress, textPhone, textemail, textAadhar, textcourse, textbranch;
    private JLabel empText, textName, textfather, textdob, textM10, textM12;
    private JButton submit, cancel;
    private Choice cEMPID;

    // ========== GETTERS ==========
    public String getAddress()   { return textAddress.getText(); }
    public String getPhone()     { return textPhone.getText(); }
    public String getEmail()     { return textemail.getText(); }
    public String getAadhar()    { return textAadhar.getText(); }
    public String getCourse()    { return textcourse.getText(); }
    public String getBranch()    { return textbranch.getText(); }
    public String getRollNo()    { return empText.getText(); }

    // ========== SETTERS (used by fetchData to populate display labels) ==========
    public void setNameText(String v)     { textName.setText(v); }
    public void setFatherText(String v)   { textfather.setText(v); }
    public void setDobText(String v)      { textdob.setText(v); }
    public void setAddressText(String v)  { textAddress.setText(v); }
    public void setPhoneText(String v)    { textPhone.setText(v); }
    public void setEmailText(String v)    { textemail.setText(v); }
    public void setClassX(String v)       { textM10.setText(v); }
    public void setClassXII(String v)     { textM12.setText(v); }
    public void setAadharText(String v)   { textAadhar.setText(v); }
    public void setRollNoText(String v)   { empText.setText(v); }
    public void setCourseText(String v)   { textcourse.setText(v); }
    public void setBranchText(String v)   { textbranch.setText(v); }

    // ================= ROUND PANEL =================
    class RoundPanel extends JPanel {
        private int cornerRadius;
        public RoundPanel(int radius) { this.cornerRadius = radius; setOpaque(false); }
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

    // ================= ROUND BUTTON =================
    class RoundButton extends JButton {
        public RoundButton(String label) {
            super(label); setContentAreaFilled(false); setFocusPainted(false);
            setBorderPainted(false); setOpaque(false); setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            super.paintComponent(g); g2.dispose();
        }
    }

    UpdateStudent() {
        setSize(1200, 900); setLocation(250, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setLayout(null);
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("white.png"));
        setIconImage(ic.getImage()); getContentPane().setBackground(Color.WHITE);

        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("updatestudent.png"));
        Image img1 = icon1.getImage().getScaledInstance(400, 60, Image.SCALE_SMOOTH);
        JLabel imageLabel1 = new JLabel(new ImageIcon(img1)); imageLabel1.setBounds(380, -10, 400, 70); add(imageLabel1);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("updateStudent1.png"));
        Image img = icon.getImage().getScaledInstance(840, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img)); imageLabel.setBounds(155, 65, 840, 200); add(imageLabel);

        RoundPanel formPanel = new RoundPanel(50);
        formPanel.setLayout(null); formPanel.setBackground(new Color(13, 68, 89));
        formPanel.setBounds(150, 280, 850, 430); add(formPanel);

        formPanel.add(createLabel("Search Roll NO: ", 290, 30));
        cEMPID = new Choice(); cEMPID.setBounds(440, 35, 80, 30); formPanel.add(cEMPID);

        try {
            // ========== PreparedStatement for loading dropdown ==========
            DatabaseConnection c = new DatabaseConnection();
            PreparedStatement ps = c.prepare("SELECT rollno FROM student");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { cEMPID.add(rs.getString("rollno")); }
            rs.close(); ps.close();
        } catch (Exception e) { e.printStackTrace(); }

        formPanel.add(createLabel("Name", 70, 80));           textName    = createDisplayLabel(200, 80, formPanel);
        formPanel.add(createLabel("Father Name", 470, 80));   textfather  = createDisplayLabel(610, 80, formPanel);
        formPanel.add(createLabel("Roll Number", 70, 130));   empText     = createDisplayLabel(200, 130, formPanel);
        formPanel.add(createLabel("Date of Birth", 470, 130));textdob     = createDisplayLabel(610, 130, formPanel);
        formPanel.add(createLabel("Address", 70, 180));       textAddress = createField(200, 180, formPanel);
        formPanel.add(createLabel("Phone", 470, 180));        textPhone   = createField(610, 180, formPanel);
        formPanel.add(createLabel("Email", 70, 230));         textemail   = createField(200, 230, formPanel);
        formPanel.add(createLabel("Class X (%)", 470, 230));  textM10     = createDisplayLabel(610, 230, formPanel);
        formPanel.add(createLabel("Class XII (%)", 70, 280)); textM12     = createDisplayLabel(200, 280, formPanel);
        formPanel.add(createLabel("CNIC", 470, 280));         textAadhar  = createField(610, 280, formPanel);
        formPanel.add(createLabel("Course", 70, 330));        textcourse  = createField(200, 330, formPanel);
        formPanel.add(createLabel("Branch", 470, 330));       textbranch  = createField(610, 330, formPanel);

        fetchData();
        cEMPID.addItemListener(e -> fetchData());

        submit = new RoundButton("Update");
        submit.setBounds(275, 380, 120, 35); submit.setBackground(Color.WHITE);
        submit.setForeground(new Color(13, 68, 89)); submit.setFont(new Font("Tahoma", Font.BOLD, 14));
        submit.addActionListener(this); formPanel.add(submit);

        cancel = new RoundButton("Cancel");
        cancel.setBounds(435, 380, 120, 35); cancel.setBackground(Color.WHITE);
        cancel.setForeground(new Color(13, 68, 89)); cancel.setFont(new Font("Tahoma", Font.BOLD, 14));
        cancel.addActionListener(this); formPanel.add(cancel);

        setVisible(true);
    }

    private void fetchData() {
        try {
            // ========== PreparedStatement: fixes SQL injection ==========
            DatabaseConnection c = new DatabaseConnection();
            PreparedStatement ps = c.prepare("SELECT * FROM student WHERE rollno = ?");
            ps.setString(1, cEMPID.getSelectedItem());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Using setters — encapsulated write access
                setNameText(rs.getString("name"));
                setFatherText(rs.getString("fname"));
                setDobText(rs.getString("dob"));
                setAddressText(rs.getString("address"));
                setPhoneText(rs.getString("phone"));
                setEmailText(rs.getString("email"));
                setClassX(rs.getString("class_x"));
                setClassXII(rs.getString("class_xii"));
                setAadharText(rs.getString("aadhar"));
                setRollNoText(rs.getString("rollno"));
                setCourseText(rs.getString("course"));
                setBranchText(rs.getString("branch"));
            }
            rs.close(); ps.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    JLabel createLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text); lbl.setBounds(x, y, 150, 30);
        lbl.setForeground(Color.WHITE); lbl.setFont(new Font("serif", Font.BOLD, 18)); return lbl;
    }
    JLabel createDisplayLabel(int x, int y, JPanel panel) {
        JLabel lbl = new JLabel(); lbl.setBounds(x, y, 150, 30);
        lbl.setForeground(Color.WHITE); lbl.setFont(new Font("serif", Font.PLAIN, 18));
        panel.add(lbl); return lbl;
    }
    JTextField createField(int x, int y, JPanel panel) {
        JTextField tf = new JTextField(); tf.setBounds(x, y, 150, 30); panel.add(tf); return tf;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            try {
                // ========== PreparedStatement: fixes SQL injection ==========
                DatabaseConnection c = new DatabaseConnection();
                PreparedStatement ps = c.prepare(
                        "UPDATE student SET address=?, phone=?, email=?, course=?, branch=?, aadhar=? WHERE rollno=?");
                ps.setString(1, getAddress());
                ps.setString(2, getPhone());
                ps.setString(3, getEmail());
                ps.setString(4, getCourse());
                ps.setString(5, getBranch());
                ps.setString(6, getAadhar());
                ps.setString(7, getRollNo());
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(null, "Student Details Updated Successfully");
                setVisible(false);
            } catch (Exception ex) { ex.printStackTrace(); }
        } else { setVisible(false); }
    }

    public static void main(String[] args) { new UpdateStudent(); }
}
