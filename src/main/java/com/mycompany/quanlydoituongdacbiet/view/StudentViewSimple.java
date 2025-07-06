/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.view;

import com.mycompany.quanlydoituongdacbiet.entity.Student;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * View đơn giản để quản lý thí sinh
 * 
 * @author PC
 */
public class StudentViewSimple extends JFrame {
    
    private JTextField txtStudentId;
    private JTextField txtFullName;
    private JDateChooser dateChooser;
    private JComboBox<String> cmbGender;
    private JTextField txtAddress;
    private JTextField txtPhoneNumber;
    private JTextField txtEmail;
    private JComboBox<String> cmbExamBlock;
    private JTextField txtSearchName;
    private JTextField txtMinAge;
    private JTextField txtMaxAge;
    
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnSearch;
    private JButton btnSearchAge;
    private JButton btnSort;
    private JButton btnBack;
    
    private JTable tableStudents;
    private DefaultTableModel tableModel;
    private JLabel lblStatus;
    private JLabel lblTotal;
    private JLabel lblMale;
    private JLabel lblFemale;
    
    private SimpleDateFormat dateFormat;
    
    public StudentViewSimple() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        setTitle("Quản lý thí sinh");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLayout(new BorderLayout());
        
        // Set background
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/Lovepik_com-500330964-blue-blazed-background.jpg"));
        background.setLayout(new BorderLayout());
        
        // Main panel with transparent background
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        
        // Header
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Content
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        
        // Form panel
        JPanel formPanel = createFormPanel();
        contentPanel.add(formPanel, BorderLayout.WEST);
        
        // Table panel
        JPanel tablePanel = createTablePanel();
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Status panel
        JPanel statusPanel = createStatusPanel();
        mainPanel.add(statusPanel, BorderLayout.SOUTH);
        
        background.add(mainPanel);
        add(background);
    }
    
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);
        
        JLabel title = new JLabel("QUẢN LÝ THÍ SINH");
        title.setFont(new Font("Times New Roman", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        panel.add(title);
        
        return panel;
    }
    
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2), 
            "Thông tin thí sinh",
            0, 0, new Font("Times New Roman", Font.BOLD, 16), Color.WHITE));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Row 0: Student ID
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(createLabel("Mã thí sinh:"), gbc);
        gbc.gridx = 1;
        txtStudentId = new JTextField(15);
        txtStudentId.setEditable(false); // Auto-generate
        panel.add(txtStudentId, gbc);
        
        // Row 1: Full Name
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(createLabel("Họ và tên:"), gbc);
        gbc.gridx = 1;
        txtFullName = new JTextField(15);
        panel.add(txtFullName, gbc);
        
        // Row 2: Birth Date
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(createLabel("Ngày sinh:"), gbc);
        gbc.gridx = 1;
        dateChooser = new JDateChooser();
        dateChooser.setPreferredSize(new Dimension(150, 25));
        dateChooser.setDateFormatString("dd/MM/yyyy");
        panel.add(dateChooser, gbc);
        
        // Row 3: Gender
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(createLabel("Giới tính:"), gbc);
        gbc.gridx = 1;
        cmbGender = new JComboBox<>(Student.getGenderOptions());
        panel.add(cmbGender, gbc);
        
        // Row 4: Address
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(createLabel("Địa chỉ:"), gbc);
        gbc.gridx = 1;
        txtAddress = new JTextField(15);
        panel.add(txtAddress, gbc);
        
        // Row 5: Phone
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(createLabel("Số điện thoại:"), gbc);
        gbc.gridx = 1;
        txtPhoneNumber = new JTextField(15);
        panel.add(txtPhoneNumber, gbc);
        
        // Row 6: Email
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(createLabel("Email:"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(15);
        panel.add(txtEmail, gbc);
        
        // Row 7: Exam Block
        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(createLabel("Khối thi:"), gbc);
        gbc.gridx = 1;
        cmbExamBlock = new JComboBox<>(Student.getExamBlockOptions());
        panel.add(cmbExamBlock, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        buttonPanel.setOpaque(false);
        
        btnAdd = createButton("Thêm");
        btnEdit = createButton("Sửa");
        btnDelete = createButton("Xóa");
        btnClear = createButton("Làm mới");
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        
        gbc.gridx = 0; gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);
        
        // Search panel
        JPanel searchPanel = createSearchPanel();
        gbc.gridy = 9;
        panel.add(searchPanel, gbc);
        
        // Back button
        btnBack = createButton("Quay lại");
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(btnBack, gbc);
        
        return panel;
    }
    
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE, 1), 
            "Tìm kiếm",
            0, 0, new Font("Times New Roman", Font.BOLD, 14), Color.WHITE));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Search by name
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(createLabel("Tên:"), gbc);
        gbc.gridx = 1;
        txtSearchName = new JTextField(10);
        panel.add(txtSearchName, gbc);
        gbc.gridx = 2;
        btnSearch = createSmallButton("Tìm");
        panel.add(btnSearch, gbc);
        
        // Search by age range
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(createLabel("Tuổi từ:"), gbc);
        gbc.gridx = 1;
        txtMinAge = new JTextField(5);
        panel.add(txtMinAge, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(createLabel("đến:"), gbc);
        gbc.gridx = 1;
        txtMaxAge = new JTextField(5);
        panel.add(txtMaxAge, gbc);
        gbc.gridx = 2;
        btnSearchAge = createSmallButton("Tìm");
        panel.add(btnSearchAge, gbc);
        
        // Sort button
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 3;
        btnSort = createSmallButton("Sắp xếp theo tên");
        panel.add(btnSort, gbc);
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2), 
            "Danh sách thí sinh",
            0, 0, new Font("Times New Roman", Font.BOLD, 16), Color.WHITE));
        
        String[] columnNames = {
            "Mã thí sinh", "Họ và tên", "Ngày sinh", "Tuổi", 
            "Giới tính", "Địa chỉ", "Điện thoại", "Email", "Khối thi"
        };
        
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableStudents = new JTable(tableModel);
        tableStudents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableStudents.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        tableStudents.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
        
        JScrollPane scrollPane = new JScrollPane(tableStudents);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createStatusPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);
        
        lblStatus = new JLabel("Sẵn sàng");
        lblStatus.setForeground(Color.WHITE);
        lblStatus.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        
        lblTotal = new JLabel("Tổng: 0");
        lblTotal.setForeground(Color.WHITE);
        lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 14));
        
        lblMale = new JLabel("Nam: 0");
        lblMale.setForeground(Color.WHITE);
        lblMale.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        
        lblFemale = new JLabel("Nữ: 0");
        lblFemale.setForeground(Color.WHITE);
        lblFemale.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        
        panel.add(lblStatus);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(lblTotal);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(lblMale);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(lblFemale);
        
        return panel;
    }
    
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        return label;
    }
    
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Times New Roman", Font.BOLD, 14));
        button.setBackground(new Color(0, 0, 139));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    private JButton createSmallButton(String text) {
        JButton button = createButton(text);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        button.setPreferredSize(new Dimension(80, 25));
        return button;
    }
    
    // Getter methods for form data
    public Student getStudentInfo() {
        try {
            String studentId = txtStudentId.getText().trim();
            String fullName = txtFullName.getText().trim();
            Date birthDate = dateChooser.getDate();
            String gender = (String) cmbGender.getSelectedItem();
            String address = txtAddress.getText().trim();
            String phoneNumber = txtPhoneNumber.getText().trim();
            String email = txtEmail.getText().trim();
            String examBlock = (String) cmbExamBlock.getSelectedItem();
            
            if (fullName.isEmpty() || birthDate == null) {
                showMessage("Vui lòng nhập đầy đủ thông tin bắt buộc!");
                return null;
            }
            
            Student student = new Student();
            if (!studentId.isEmpty()) {
                student.setStudentId(studentId);
            }
            student.setFullName(fullName);
            student.setBirthDate(birthDate);
            student.setGender(gender);
            student.setAddress(address);
            student.setPhoneNumber(phoneNumber);
            student.setEmail(email);
            student.setExamBlock(examBlock);
            
            return student;
            
        } catch (Exception e) {
            showMessage("Lỗi khi lấy thông tin: " + e.getMessage());
            return null;
        }
    }
    
    public void showStudentInfo(Student student) {
        if (student != null) {
            txtStudentId.setText(student.getStudentId());
            txtFullName.setText(student.getFullName());
            dateChooser.setDate(student.getBirthDate());
            cmbGender.setSelectedItem(student.getGender());
            txtAddress.setText(student.getAddress());
            txtPhoneNumber.setText(student.getPhoneNumber());
            txtEmail.setText(student.getEmail());
            cmbExamBlock.setSelectedItem(student.getExamBlock());
        }
    }
    
    public void showListStudents(List<Student> students) {
        tableModel.setRowCount(0);
        for (Student student : students) {
            Object[] row = {
                student.getStudentId(),
                student.getFullName(),
                dateFormat.format(student.getBirthDate()),
                student.getAge(),
                student.getGender(),
                student.getAddress(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.getExamBlock()
            };
            tableModel.addRow(row);
        }
    }
    
    public void showStatistics(int total, long male, long female) {
        lblTotal.setText("Tổng: " + String.format("%,d", total));
        lblMale.setText("Nam: " + String.format("%,d", male));
        lblFemale.setText("Nữ: " + String.format("%,d", female));
    }
    
    public void clearForm() {
        txtStudentId.setText("");
        txtFullName.setText("");
        dateChooser.setDate(null);
        cmbGender.setSelectedIndex(0);
        txtAddress.setText("");
        txtPhoneNumber.setText("");
        txtEmail.setText("");
        cmbExamBlock.setSelectedIndex(0);
        txtSearchName.setText("");
        txtMinAge.setText("");
        txtMaxAge.setText("");
    }
    
    public String getSearchName() {
        return txtSearchName.getText().trim();
    }
    
    public int getMinAge() {
        try {
            return Integer.parseInt(txtMinAge.getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public int getMaxAge() {
        try {
            return Integer.parseInt(txtMaxAge.getText().trim());
        } catch (NumberFormatException e) {
            return 100;
        }
    }
    
    public int getSelectedRow() {
        return tableStudents.getSelectedRow();
    }
    
    public String getSelectedStudentId() {
        int row = getSelectedRow();
        if (row >= 0) {
            return (String) tableModel.getValueAt(row, 0);
        }
        return null;
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
        lblStatus.setText(message);
    }
    
    public void showStudentView() {
        setVisible(true);
    }
    
    // Listener methods
    public void addAddStudentListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }
    
    public void addEditStudentListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }
    
    public void addDeleteStudentListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }
    
    public void addSearchListener(ActionListener listener) {
        btnSearch.addActionListener(listener);
    }
    
    public void addSearchAgeListener(ActionListener listener) {
        btnSearchAge.addActionListener(listener);
    }
    
    public void addSortListener(ActionListener listener) {
        btnSort.addActionListener(listener);
    }
    
    public void addBackListener(ActionListener listener) {
        btnBack.addActionListener(listener);
    }
    
    public void addTableSelectionListener(javax.swing.event.ListSelectionListener listener) {
        tableStudents.getSelectionModel().addListSelectionListener(listener);
    }
}
