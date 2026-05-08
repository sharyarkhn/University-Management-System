package ProjectWithSherry;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// ================= ROUND BUTTON =================
class RoundButtonMarks extends JButton {
    public RoundButtonMarks(String label) {
        super(label);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed())
            g2.setColor(getBackground().darker());
        else
            g2.setColor(getBackground());

        g2.fillRoundRect(0,0,getWidth(),getHeight(),20,20);
        super.paintComponent(g);
        g2.dispose();
    }
}

// ================= MAIN CLASS =================
public class EnterMarks extends JFrame implements ActionListener {

    Choice choicerollno;
    JComboBox<String> comboBox;
    JTextField sub1, sub2, sub3, sub4, sub5;
    JTextField mrk1, mrk2, mrk3, mrk4, mrk5;
    JButton submit, cancel;

    EnterMarks() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(13,68,89));
        topPanel.setBounds(0,0,1200,30);
        add(topPanel);

        // Images
        ImageIcon I1 = new ImageIcon(ClassLoader.getSystemResource("whitee.png"));
        Image I2 = I1.getImage().getScaledInstance(450,600,Image.SCALE_SMOOTH);
        JLabel img1 = new JLabel(new ImageIcon(I2));
        img1.setBounds(30,55,500,660);
        img1.setLayout(null);
        add(img1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("exams.png"));
        Image i2 = i1.getImage().getScaledInstance(500,660,Image.SCALE_SMOOTH);
        JLabel img = new JLabel(new ImageIcon(i2));
        img.setBounds(540,50,500,660);
        add(img);

        // Roll Number with Prepared Statement Fix
        JLabel lblrollno = new JLabel("Select Roll Number");
        lblrollno.setBounds(150,135,200,30);
        lblrollno.setFont(new Font("Tahoma",Font.BOLD,18));
        lblrollno.setForeground(new Color(13,68,89));
        img1.add(lblrollno);

        choicerollno = new Choice();
        choicerollno.setBounds(150,165,200,20);
        img1.add(choicerollno);

        try {
            DatabaseConnection c = new DatabaseConnection();
            // FIXED: Using Prepared Statement here
            PreparedStatement ps = c.prepare("SELECT rollno FROM student");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                choicerollno.add(rs.getString("rollno"));
            }
            rs.close();
            ps.close();
        } catch(Exception e){ e.printStackTrace(); }

        // Semester
        JLabel lblsem = new JLabel("Select Semester");
        lblsem.setBounds(150,195,200,30);
        lblsem.setFont(new Font("Tahoma",Font.BOLD,18));
        lblsem.setForeground(new Color(13,68,89));
        img1.add(lblsem);

        String semester[]={"1st Semester","2nd Semester","3rd Semester","4th Semester","5th Semester","6th Semester","7th Semester","8th Semester"};
        comboBox = new JComboBox<>(semester);
        comboBox.setBounds(150,225,200,25);
        img1.add(comboBox);
        comboBox.addActionListener(e -> setSubjects());

        // Headers
        JLabel lblSubject = new JLabel("Subject Name");
        lblSubject.setBounds(95,265,200,30);
        lblSubject.setFont(new Font("Tahoma",Font.BOLD,20));
        img1.add(lblSubject);

        JLabel lblMarks = new JLabel("Marks");
        lblMarks.setBounds(355,265,200,30);
        lblMarks.setFont(new Font("Tahoma",Font.BOLD,20));
        img1.add(lblMarks);

        // Fields
        sub1=createSubjectField(50,300,img1); sub2=createSubjectField(50,350,img1);
        sub3=createSubjectField(50,400,img1); sub4=createSubjectField(50,450,img1);
        sub5=createSubjectField(50,500,img1);

        mrk1=createMarksField(330,300,img1); mrk2=createMarksField(330,350,img1);
        mrk3=createMarksField(330,400,img1); mrk4=createMarksField(330,450,img1);
        mrk5=createMarksField(330,500,img1);

        // Buttons
        submit = createBtn("Submit", 115, 560, img1);
        cancel = createBtn("Cancel", 265, 560, img1);

        setSubjects();
        setSize(1200,900);
        setLocation(250,0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private RoundButtonMarks createBtn(String txt, int x, int y, JLabel p) {
        RoundButtonMarks b = new RoundButtonMarks(txt);
        b.setBounds(x,y,135,32);
        b.setBackground(new Color(13,68,89));
        b.setForeground(Color.WHITE);
        b.addActionListener(this);
        p.add(b);
        return b;
    }

    JTextField createSubjectField(int x,int y,JLabel panel){
        JTextField tf=new JTextField();
        tf.setBounds(x,y,240,30);
        tf.setBackground(new Color(13,68,89));
        tf.setFont(new Font("Tahoma", Font.BOLD, 14));
        tf.setForeground(Color.WHITE);
        tf.setEditable(false);
        panel.add(tf);
        return tf;
    }

    JTextField createMarksField(int x,int y,JLabel panel){
        JTextField tf=new JTextField();
        tf.setBounds(x,y,120,30);
        tf.setBackground(new Color(13,68,89));
        tf.setForeground(Color.WHITE);
        panel.add(tf);
        return tf;
    }

    public void setSubjects(){
        String sem=(String)comboBox.getSelectedItem();
        if(sem.equals("1st Semester")){
            sub1.setText("Programming Fundamentals"); sub2.setText("Calculus"); sub3.setText("Physics"); sub4.setText("English"); sub5.setText("ICT");
        } else if(sem.equals("2nd Semester")){
            sub1.setText("OOP"); sub2.setText("Discrete Mathematics"); sub3.setText("Digital Logic"); sub4.setText("Communication Skills"); sub5.setText("Pakistan Studies");
        } else {
            sub1.setText("Data Structures"); sub2.setText("Database Systems"); sub3.setText("Software Engineering"); sub4.setText("Linear Algebra"); sub5.setText("Probability");
        }
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==submit){
            try{
                DatabaseConnection c = new DatabaseConnection();

                // Subjects Insertion
                PreparedStatement ps1 = c.prepare("INSERT INTO subject VALUES (?, ?, ?, ?, ?, ?, ?)");
                ps1.setString(1, choicerollno.getSelectedItem());
                ps1.setString(2, (String) comboBox.getSelectedItem());
                ps1.setString(3, sub1.getText());
                ps1.setString(4, sub2.getText());
                ps1.setString(5, sub3.getText());
                ps1.setString(6, sub4.getText());
                ps1.setString(7, sub5.getText());
                ps1.executeUpdate();
                ps1.close();

                // Marks Insertion
                PreparedStatement ps2 = c.prepare("INSERT INTO marks VALUES (?, ?, ?, ?, ?, ?, ?)");
                ps2.setString(1, choicerollno.getSelectedItem());
                ps2.setString(2, (String) comboBox.getSelectedItem());
                ps2.setString(3, mrk1.getText());
                ps2.setString(4, mrk2.getText());
                ps2.setString(5, mrk3.getText());
                ps2.setString(6, mrk4.getText());
                ps2.setString(7, mrk5.getText());
                ps2.executeUpdate();
                ps2.close();

                JOptionPane.showMessageDialog(null, "Marks Inserted Successfully");
                setVisible(false);
            } catch(Exception ex){ ex.printStackTrace(); }
        } else if(e.getSource()==cancel){
            setVisible(false);
        }
    }

    public static void main(String[] args){ new EnterMarks(); }
}