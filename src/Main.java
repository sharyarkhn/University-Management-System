package ProjectWithSherry;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//i have store one jar file for connection
//an other jar file for calender
//an other for Dbutils
//faculty == updatefac
//student == updatestu

public class Main extends JFrame implements ActionListener {

    Main() {

        setLayout(new BorderLayout());
        setSize(1540, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // ================= SIDEBAR =================
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(250, 850));
        sidebar.setBackground(new Color(13 , 68 , 89));
        add(sidebar, BorderLayout.WEST);

        // ================= LOGO =================
        ImageIcon logoIcon = new ImageIcon(ClassLoader.getSystemResource("user9.png")); // replace with your logo
        Image logoImg = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center logo
        logoLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0)); // spacing below logo
        sidebar.add(logoLabel);
        sidebar.add(Box.createVerticalStrut(25));



        // ================= BUTTON PANEL =================
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 0, 0)); // stacked buttons, no vertical gap
        buttonPanel.setBackground(new Color(13 , 68 , 89));
        sidebar.add(buttonPanel);

        Font font = new Font("Tahoma", Font.BOLD, 16);

        // ================= NEW INFORMATION =================
        JButton newInfoBtn = new JButton("        New Information");
        newInfoBtn.setFont(font);
        newInfoBtn.setForeground(Color.WHITE); //////////////////////////////////////
        newInfoBtn.setBackground(new Color(13 , 68 , 89));
        newInfoBtn.setHorizontalAlignment(SwingConstants.LEFT);
        newInfoBtn.setFocusPainted(false);
        newInfoBtn.setBorder(null);
        newInfoBtn.setPreferredSize(new Dimension(250, 35));
        buttonPanel.add(newInfoBtn);

        JPopupMenu newInfoMenu = new JPopupMenu();
        JMenuItem facultyInfo = new JMenuItem("New Faculty Information");
        facultyInfo.addActionListener(this);
        newInfoMenu.add(facultyInfo);
        JMenuItem studentInfo = new JMenuItem("New Student Information");
        studentInfo.addActionListener(this);
        newInfoMenu.add(studentInfo);
        newInfoBtn.addActionListener(e -> newInfoMenu.show(newInfoBtn, newInfoBtn.getWidth(), 0));

        // ================= VIEW DETAILS =================
        JButton detailsBtn = new JButton("        View Details");
        detailsBtn.setFont(font);
        detailsBtn.setForeground(Color.WHITE);
        detailsBtn.setBackground(new Color(13 , 68 , 89));
        detailsBtn.setHorizontalAlignment(SwingConstants.LEFT);
        detailsBtn.setFocusPainted(false);
        detailsBtn.setBorder(null);
        detailsBtn.setPreferredSize(new Dimension(300, 40));
        buttonPanel.add(detailsBtn);
//13 , 68 , 89
        JPopupMenu detailsMenu = new JPopupMenu();
        JMenuItem facultydetails = new JMenuItem("View Faculty Details");
        facultydetails.addActionListener(this);
        detailsMenu.add(facultydetails);
        JMenuItem studentdetails = new JMenuItem("View Student Details");
        studentdetails.addActionListener(this);
        detailsMenu.add(studentdetails);
        detailsBtn.addActionListener(e -> detailsMenu.show(detailsBtn, detailsBtn.getWidth(), 0));

        // ================= APPLY LEAVE =================
        JButton leaveBtn = new JButton("        Apply Leave");
        leaveBtn.setFont(font);
        leaveBtn.setForeground(Color.WHITE);
        leaveBtn.setBackground(new Color(13 , 68 , 89));
        leaveBtn.setHorizontalAlignment(SwingConstants.LEFT);
        leaveBtn.setFocusPainted(false);
        leaveBtn.setBorder(null);
        leaveBtn.setPreferredSize(new Dimension(300, 40));
        buttonPanel.add(leaveBtn);

        JPopupMenu leaveMenu = new JPopupMenu();
        JMenuItem facultyLeave = new JMenuItem("Faculty Leave");
        facultyLeave.addActionListener(this);
        leaveMenu.add(facultyLeave);
        JMenuItem studentLeave = new JMenuItem("Student Leave");
        studentLeave.addActionListener(this);
        leaveMenu.add(studentLeave);
        leaveBtn.addActionListener(e -> leaveMenu.show(leaveBtn, leaveBtn.getWidth(), 0));

        // ================= LEAVE DETAILS =================
        JButton leaveDetailsBtn = new JButton("        Leave Details");
        leaveDetailsBtn.setFont(font);
        leaveDetailsBtn.setForeground(Color.WHITE);
        leaveDetailsBtn.setBackground(new Color(13 , 68 , 89));
        leaveDetailsBtn.setHorizontalAlignment(SwingConstants.LEFT);
        leaveDetailsBtn.setFocusPainted(false);
        leaveDetailsBtn.setBorder(null);
        leaveDetailsBtn.setPreferredSize(new Dimension(300, 40));
        buttonPanel.add(leaveDetailsBtn);

        JPopupMenu leaveDetailsMenu = new JPopupMenu();
        JMenuItem facultyleaveDetails = new JMenuItem("Faculty Leave Details");
        facultyleaveDetails.addActionListener(this);
        leaveDetailsMenu.add(facultyleaveDetails);
        JMenuItem studentleaveDetails = new JMenuItem("Student Leave Details");
        studentleaveDetails.addActionListener(this);
        leaveDetailsMenu.add(studentleaveDetails);
        leaveDetailsBtn.addActionListener(e -> leaveDetailsMenu.show(leaveDetailsBtn, leaveDetailsBtn.getWidth(), 0));

        // ================= EXAMINATION =================
        JButton examBtn = new JButton("        Examination");
        examBtn.setFont(font);
        examBtn.setForeground(Color.WHITE);
        examBtn.setBackground(new Color(13 , 68 , 89));
        examBtn.setHorizontalAlignment(SwingConstants.LEFT);
        examBtn.setFocusPainted(false);
        examBtn.setBorder(null);
        examBtn.setPreferredSize(new Dimension(300, 40));
        buttonPanel.add(examBtn);

        JPopupMenu examMenu = new JPopupMenu();
        JMenuItem ExaminationDetails = new JMenuItem("Examination Results");
        ExaminationDetails.addActionListener(this);
        examMenu.add(ExaminationDetails);
        JMenuItem EnterMarks = new JMenuItem("Enter Marks");
        EnterMarks.addActionListener(this);
        examMenu.add(EnterMarks);
        examBtn.addActionListener(e -> examMenu.show(examBtn, examBtn.getWidth(), 0));

        // ================= UPDATE DETAILS =================
        JButton updateBtn = new JButton("        Update Details");
        updateBtn.setFont(font);
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setBackground(new Color(13 , 68 , 89));
        updateBtn.setHorizontalAlignment(SwingConstants.LEFT);
        updateBtn.setFocusPainted(false);
        updateBtn.setBorder(null);
        updateBtn.setPreferredSize(new Dimension(300, 40));
        buttonPanel.add(updateBtn);

        JPopupMenu updateMenu = new JPopupMenu();
        JMenuItem updatefacultyinfo = new JMenuItem("Update Faculty Details");
        updatefacultyinfo.addActionListener(this);
        updateMenu.add(updatefacultyinfo);
        JMenuItem updatestudentinfo = new JMenuItem("Update Student Details");
        updatestudentinfo.addActionListener(this);
        updateMenu.add(updatestudentinfo);
        updateBtn.addActionListener(e -> updateMenu.show(updateBtn, updateBtn.getWidth(), 0));

        // ================= FEE =================
        JButton feeBtn = new JButton("        Fee Details");
        feeBtn.setFont(font);
        feeBtn.setForeground(Color.WHITE);
        feeBtn.setBackground(new Color(13 , 68 , 89));
        feeBtn.setHorizontalAlignment(SwingConstants.LEFT);
        feeBtn.setFocusPainted(false);
        feeBtn.setBorder(null);
        feeBtn.setPreferredSize(new Dimension(300, 40));
        buttonPanel.add(feeBtn);

        JPopupMenu feeMenu = new JPopupMenu();
//        JMenuItem feestructure = new JMenuItem("Fee Structure");
//        feestructure.addActionListener(this);
//        feeMenu.add(feestructure);
        JMenuItem feeForm = new JMenuItem("Student Fee Form");
        feeForm.addActionListener(this);
        feeMenu.add(feeForm);
        feeBtn.addActionListener(e -> feeMenu.show(feeBtn, feeBtn.getWidth(), 0));

        // ================= UTILITY =================
        JButton utilityBtn = new JButton("        Utility");
        utilityBtn.setFont(font);
        utilityBtn.setForeground(Color.WHITE);
        utilityBtn.setBackground(new Color(13 , 68 , 89));
        utilityBtn.setHorizontalAlignment(SwingConstants.LEFT);
        utilityBtn.setFocusPainted(false);
        utilityBtn.setBorder(null);
        utilityBtn.setPreferredSize(new Dimension(300, 40));
        buttonPanel.add(utilityBtn);

        JPopupMenu utilityMenu = new JPopupMenu();
        JMenuItem ca = new JMenuItem("Calculator");
        ca.addActionListener(this);
        utilityMenu.add(ca);
        JMenuItem Notepad = new JMenuItem("Notepad");
        Notepad.addActionListener(this);
        utilityMenu.add(Notepad);
        utilityBtn.addActionListener(e -> utilityMenu.show(utilityBtn, utilityBtn.getWidth(), 0));

        // ================= ABOUT =================
        JButton aboutBtn = new JButton("        About");
        aboutBtn.setFont(font);
        aboutBtn.setForeground(Color.WHITE);
        aboutBtn.setBackground(new Color(13 , 68 , 89));
        aboutBtn.setHorizontalAlignment(SwingConstants.LEFT);
        aboutBtn.setFocusPainted(false);
        aboutBtn.setBorder(null);
        aboutBtn.setPreferredSize(new Dimension(300, 40));
        buttonPanel.add(aboutBtn);

        JPopupMenu aboutMenu = new JPopupMenu();
        JMenuItem About = new JMenuItem("About");
        About.addActionListener(this);
        aboutMenu.add(About);
        aboutBtn.addActionListener(e -> aboutMenu.show(aboutBtn, aboutBtn.getWidth(), 0));

        // ================= EXIT =================
        JButton exitBtn = new JButton("        Exit");
        exitBtn.setFont(font);
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setBackground(new Color(13 , 68 , 89));
        exitBtn.setHorizontalAlignment(SwingConstants.LEFT);
        exitBtn.setFocusPainted(false);
        exitBtn.setBorder(null);
        exitBtn.setPreferredSize(new Dimension(300, 40));
        buttonPanel.add(exitBtn);

        JPopupMenu exitMenu = new JPopupMenu();
        JMenuItem Exit = new JMenuItem("Exit");
        Exit.addActionListener(this);
        exitMenu.add(Exit);
        exitBtn.addActionListener(e -> exitMenu.show(exitBtn, exitBtn.getWidth(), 0));

        // ================= BACKGROUND =================

        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("Doc2.png"));
        Image bgImg = bgIcon.getImage().getScaledInstance(1120, 748, Image.SCALE_SMOOTH);
        JLabel bgLabel = new JLabel(new ImageIcon(bgImg));
        add(bgLabel, BorderLayout.CENTER);


        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("user.png"));
        setIconImage(ic.getImage());
        setTitle("University Management System");
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sm = e.getActionCommand();
        if (sm.equals("Exit")){
            System.exit(15);
        } else if (sm.equals("Calculator")){
            try {
                Runtime.getRuntime().exec("calc.exe");
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (sm.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (sm.equals("New Faculty Information")) {
            new Faculty();
        }else if (sm.equals("New Student Information")){
            new Student();
        } else if (sm.equals("View Faculty Details")) {
            new FacultyDetails();
        } else if (sm.equals("View Student Details")) {
            new StudentDetails();
        } else if (sm.equals("Faculty Leave")) {
            new TeacherLeave();
        } else if (sm.equals("Student Leave")) {
            new StudentLeave();
        } else if (sm.equals("Faculty Leave Details")) {
            new TeacherLeaveDetails();
        } else if (sm.equals("Student Leave Details")) {
            new StudentLeaveDetails();
        } else if (sm.equals("Update Faculty Details")) {
            new UpdateFaculty();
        } else if (sm.equals("Update Student Details")) {
            new UpdateStudent();
        } else if (sm.equals("Enter Marks")) {
            new EnterMarks();
        } else if (sm.equals("Examination Results")) {
            new ExamDetails();
        }else if (sm.equals("Student Fee Form")){
            new StudentFeeForm();
        } else if (sm.equals("About")) {
            new About();
        }
    }
    public static void main(String[] args) {
        new Main();
    }
}