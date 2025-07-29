package com.mycompany.quanlydoituongdacbiet.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private JButton btnChooseSpecialPerson;
    private JButton btnChooseResidents;
    private JButton btnChooseSubjects;
    private JButton btnChooseExamBlocks;
    private JButton btnLookupScore;
    private JButton btnLogout;
    private JLabel backgroundLabel;
    private JLabel titleLabel;

    public MainView() {
        setTitle("Hệ thống quản lý điểm thi đại học");
        setSize(960, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(null); // Use null layout for manual positioning

        initComponents();
    }

    private void initComponents() {
        // Tiêu đề
        titleLabel = new JLabel("Hệ thống quản lý điểm thi đại học", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(180, 30, 600, 50);
        add(titleLabel);

        // Nút quản lý thí sinh
        btnChooseResidents = createButton("Quản lý thí sinh");
        btnChooseResidents.setBounds(60, 120, 387, 150);
        add(btnChooseResidents);

        // Nút quản lý điểm thi
        btnChooseSpecialPerson = createButton("Quản lý điểm thi");
        btnChooseSpecialPerson.setBounds(502, 120, 387, 150);
        add(btnChooseSpecialPerson);

        // Nút quản lý môn thi
        btnChooseSubjects = createButton("Quản lý môn thi");
        btnChooseSubjects.setBounds(60, 300, 387, 150);
        add(btnChooseSubjects);

        // Nút quản lý khối thi
        btnChooseExamBlocks = createButton("Quản lý khối thi");
        btnChooseExamBlocks.setBounds(502, 300, 387, 150);
        add(btnChooseExamBlocks);


        // Nút đăng xuất
        btnLogout = new JButton("Đăng xuất");
        btnLogout.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        btnLogout.setBackground(new Color(153, 0, 0));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setBounds(380, 580, 200, 50);
        btnLogout.setBorder(null);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnLogout);

        // Ảnh nền
        JLabel background = new JLabel();
        ImageIcon backgroundIcon = new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/background.jpg");
        background.setIcon(backgroundIcon);
        background.setBounds(0, 0, 960, 750); // Set kích thước full frame
        add(background);
    }

    private JButton createButton(String text) {
        JButton button = new JButton("<html><div style='text-align: center;'>" + text + "<br></div>");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        button.setBackground(new Color(0, 0, 102));
        button.setForeground(Color.WHITE);
        button.setBorder(null);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        return button;
    }

    // Các phương thức thêm listener
    public void addChooseScoreManagementListener(ActionListener listener) {
        btnChooseSpecialPerson.addActionListener(listener);
    }

    public void addChooseStudentListener(ActionListener listener) {
        btnChooseResidents.addActionListener(listener);
    }

    public void addChooseSubjectsListener(ActionListener listener) {
        btnChooseSubjects.addActionListener(listener);
    }

    public void addChooseExamBlocksListener(ActionListener listener) {
        btnChooseExamBlocks.addActionListener(listener);
    }

    public void addLookupScoreListener(ActionListener listener) {
        btnLookupScore.addActionListener(listener);
    }

    public void addChooseSpecialPersonListener(ActionListener listener) {
        btnChooseSpecialPerson.addActionListener(listener);
}

    public void addChooseResidentsListener(ActionListener listener) {
        btnChooseResidents.addActionListener(listener);
}


    public void addLogoutListener(ActionListener listener) {
        btnLogout.addActionListener(listener);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView view = new MainView();
            view.setVisible(true);
        });
    }
    
}
