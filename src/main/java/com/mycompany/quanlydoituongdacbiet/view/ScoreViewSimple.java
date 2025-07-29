/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.view;

import com.mycompany.quanlydoituongdacbiet.entity.ExamScore;
import com.mycompany.quanlydoituongdacbiet.entity.Student;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * View đơn giản để quản lý điểm thi
 * 
 * @author PC
 */
public class ScoreViewSimple extends JFrame {
    
    private JTextField txtStudentId;
    private JTextField txtFullName;
    private JComboBox<String> cmbExamBlock;
    private JComboBox<String> cmbSubjectCode;
    private JTextField txtScore;
    private JDateChooser dateChooser;
    private JComboBox<String> cmbStatus;
    private JTextField txtSearchStudentId;
    private JTextField txtSearchSubject;
    private JTextField txtMinScore;
    private JTextField txtMaxScore;
    
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnSearch;
    private JButton btnSearchScore;
    private JButton btnSort;
    private JButton btnBack;
    
    private JTable tableScores;
    private DefaultTableModel tableModel;
    private JLabel lblStatus;
    private JLabel lblTotal;
    private JLabel lblPassed;
    private JLabel lblFailed;
    private JLabel lblAverage;
    
    private final SimpleDateFormat dateFormat;
    private final DecimalFormat decimalFormat;

    public ScoreViewSimple() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        decimalFormat = new DecimalFormat("#,##0.00");
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        setTitle("QUẢN LÝ ĐIỂM THI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLayout(new BorderLayout());
        
        // Set background
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/background.jpg"));
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
        
        JLabel title = new JLabel("QUẢN LÝ ĐIỂM THI");
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
            "Thông tin điểm thi",
            0, 0, new Font("Times New Roman", Font.BOLD, 16), Color.WHITE));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Row 0: Student ID
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(createLabel("Mã thí sinh:"), gbc);
        gbc.gridx = 1;
        txtStudentId = new JTextField(15);
        panel.add(txtStudentId, gbc);
        
        // Row 1: Full Name
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(createLabel("Họ và tên:"), gbc);
        gbc.gridx = 1;
        txtFullName = new JTextField(15);
        panel.add(txtFullName, gbc);
        
        // Row 2: Exam Block
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(createLabel("Khối thi:"), gbc);
        gbc.gridx = 1;
        cmbExamBlock = new JComboBox<>(Student.getExamBlockOptions());
        panel.add(cmbExamBlock, gbc);
        
        // Row 3: Subject
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(createLabel("Môn Thi:"), gbc);
        gbc.gridx = 1;
        cmbSubjectCode = new JComboBox<>(ExamScore.getSubjectOptions());
        panel.add(cmbSubjectCode, gbc);
        
        // Row 4: Score
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(createLabel("Điểm số:"), gbc);
        gbc.gridx = 1;
        txtScore = new JTextField(15);
        panel.add(txtScore, gbc);
        
        // Row 5: Exam Date
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(createLabel("Ngày thi:"), gbc);
        gbc.gridx = 1;
        dateChooser = new JDateChooser();
        dateChooser.setPreferredSize(new Dimension(150, 25));
        dateChooser.setDateFormatString("dd/MM/yyyy");
        panel.add(dateChooser, gbc);
        
        // Row 6: Status
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(createLabel("Trạng thái:"), gbc);
        gbc.gridx = 1;
        cmbStatus = new JComboBox<>(ExamScore.getStatusOptions());
        panel.add(cmbStatus, gbc);
        
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
        
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);
        
        // Search panel
        JPanel searchPanel = createSearchPanel();
        gbc.gridy = 8;
        panel.add(searchPanel, gbc);
        
        // Back button
        btnBack = createButton("Quay lại");
        gbc.gridy = 9;
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
        
        // Search by student ID
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(createLabel("Mã HS:"), gbc);
        gbc.gridx = 1;
        txtSearchStudentId = new JTextField(8);
        panel.add(txtSearchStudentId, gbc);
        
        // Search by subject
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(createLabel("Môn:"), gbc);
        gbc.gridx = 1;
        txtSearchSubject = new JTextField(8);
        panel.add(txtSearchSubject, gbc);
        gbc.gridx = 2;
        btnSearch = createSmallButton("Tìm");
        panel.add(btnSearch, gbc);
        
        // Search by score range
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(createLabel("Điểm từ:"), gbc);
        gbc.gridx = 1;
        txtMinScore = new JTextField(5);
        panel.add(txtMinScore, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(createLabel("đến:"), gbc);
        gbc.gridx = 1;
        txtMaxScore = new JTextField(5);
        panel.add(txtMaxScore, gbc);
        gbc.gridx = 2;
        btnSearchScore = createSmallButton("Tìm");
        panel.add(btnSearchScore, gbc);
        
        // Sort button
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 3;
        btnSort = createSmallButton("Sắp xếp theo điểm");
        panel.add(btnSort, gbc);
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2), 
            "Danh sách điểm thi",
            0, 0, new Font("Times New Roman", Font.BOLD, 16), Color.WHITE));
        
        String[] columnNames = {
            "Mã thí sinh","Họ Và Tên","Khối Thi" ,"Môn thi", "Điểm số", "Ngày thi",  "Trạng thái"
        };
        
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableScores = new JTable(tableModel);
        tableScores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableScores.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        tableScores.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
        
        JScrollPane scrollPane = new JScrollPane(tableScores);
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
        
        lblPassed = new JLabel("Đạt: 0");
        lblPassed.setForeground(Color.GREEN);
        lblPassed.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        
        lblFailed = new JLabel("Không đạt: 0");
        lblFailed.setForeground(Color.RED);
        lblFailed.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        
        lblAverage = new JLabel("Trung bình: 0.00");
        lblAverage.setForeground(Color.YELLOW);
        lblAverage.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        
        panel.add(lblStatus);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(lblTotal);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(lblPassed);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(lblFailed);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(lblAverage);
        
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
    public ExamScore getExamScoreInfo() {
        try {
            String studentId = txtStudentId.getText().trim();
            String fullName = txtFullName.getText().trim();
            String examBlock = (String) cmbExamBlock.getSelectedItem();
            String subjectCode = (String) cmbSubjectCode.getSelectedItem();
            String scoreText = txtScore.getText().trim();
            Date examDate = dateChooser.getDate();
            String status = (String) cmbStatus.getSelectedItem();
            
            if (studentId.isEmpty() || scoreText.isEmpty() || examDate == null) {
                showMessage("Vui lòng nhập đầy đủ thông tin bắt buộc!");
                return null;
            }
            double score = Double.parseDouble(scoreText);
            if (score < 0 || score > 10) {
                showMessage("Điểm số phải từ 0 đến 10!");
                return null;
            }
            
            ExamScore examScore = new ExamScore();
            examScore.setStudentId(studentId);
            examScore.setFullName(fullName);
            examScore.setExamBlock(examBlock);
            examScore.setSubjectCode(subjectCode);
            examScore.setScore(score);
            examScore.setExamDate(examDate);
            examScore.setStatus(status);
            
            return examScore;
            
        } catch (NumberFormatException e) {
            showMessage("Điểm số phải là số!");
            return null;
        } catch (Exception e) {
            showMessage("Lỗi khi lấy thông tin: " + e.getMessage());
            return null;
        }
    }

    /**
     *
     * @param listener
     */
    public void addStudentIdFieldListener(java.awt.event.KeyListener listener) {
    txtStudentId.addKeyListener(listener);
    }

    public void showExamScoreInfo(ExamScore examScore) {
        if (examScore != null) {
            txtStudentId.setText(examScore.getStudentId());
            txtFullName.setText(examScore.getFullName());
            cmbExamBlock.setSelectedItem(examScore.getExamBlock());
            cmbSubjectCode.setSelectedItem(examScore.getSubjectCode());
            txtScore.setText(String.valueOf(examScore.getScore()));
            dateChooser.setDate(examScore.getExamDate());
            cmbStatus.setSelectedItem(examScore.getStatus());
        }
    }
    
    public void showListExamScores(List<ExamScore> examScores) {
        tableModel.setRowCount(0);
        for (ExamScore score : examScores) {
            Object[] row = {
                score.getStudentId(),
                score.getFullName(),      
                score.getExamBlock(), 
                score.getSubjectCode(),
                decimalFormat.format(score.getScore()),
                dateFormat.format(score.getExamDate()),
                score.getStatus()
            };
            tableModel.addRow(row);
        }
    }
    
    public void showStatistics(int total, long passed, long failed, double average) {
        lblTotal.setText("Tổng: " + String.format("%,d", total));
        lblPassed.setText("Đạt: " + String.format("%,d", passed));
        lblFailed.setText("Không đạt: " + String.format("%,d", failed));
        lblAverage.setText("Trung bình: " + decimalFormat.format(average));
    }
    
    public void clearForm() {
        txtStudentId.setText("");
        txtFullName.setText(""); 
        cmbExamBlock.setSelectedIndex(0); 
        cmbSubjectCode.setSelectedIndex(0);
        txtScore.setText("");
        dateChooser.setDate(null);
        cmbStatus.setSelectedIndex(0);
        txtSearchStudentId.setText("");
        txtSearchSubject.setText("");
        txtMinScore.setText("");
        txtMaxScore.setText("");
    }
    
    public String getSearchStudentId() {
        return txtSearchStudentId.getText().trim();
    }
    
    public String getSearchSubject() {
        return txtSearchSubject.getText().trim();
    }
    
    public double getMinScore() {
        try {
            return Double.parseDouble(txtMinScore.getText().trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    
    public double getMaxScore() {
        try {
            return Double.parseDouble(txtMaxScore.getText().trim());
        } catch (NumberFormatException e) {
            return 10.0;
        }
    }
    
    public int getSelectedRow() {
        return tableScores.getSelectedRow();
    }
    
    public String getSelectedStudentId() {
        int row = getSelectedRow();
        if (row >= 0) {
            return (String) tableModel.getValueAt(row, 0);
        }
        return null;
    }
    
    public String getSelectedSubjectCode() {
        int row = getSelectedRow();
        if (row >= 0) {
            return (String) tableModel.getValueAt(row, 3);
        }
        return null;
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
        lblStatus.setText(message);
    }
    
    public void showScoreView() {
        setVisible(true);
    }
    
    public String getStudentId() {
        return txtStudentId.getText().trim();
    }

    public void setFullName(String name) {
        txtFullName.setText(name);
    }

    public void setExamBlock(String block) {
        cmbExamBlock.setSelectedItem(block);
    }
    
    public void updateExamBlockOptions(List<String> blocks) {
        cmbExamBlock.removeAllItems();
        for (String block : blocks) {
        cmbExamBlock.addItem(block);
        }
    }
    

    public void updateSubjectOptions(List<String> subjects) {
        cmbSubjectCode.removeAllItems();
        for (String subject : subjects) {
        cmbSubjectCode.addItem(subject);
        }
    }      
    
    // Listener methods
    public void addAddExamScoreListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }
    
    public void addEditExamScoreListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }
    
    public void addDeleteExamScoreListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }
    
    public void addSearchListener(ActionListener listener) {
        btnSearch.addActionListener(listener);
    }
    
    public void addSearchScoreListener(ActionListener listener) {
        btnSearchScore.addActionListener(listener);
    }
    
    public void addSortListener(ActionListener listener) {
        btnSort.addActionListener(listener);
    }
    
    public void addBackListener(ActionListener listener) {
        btnBack.addActionListener(listener);
    }
    
    public void addTableSelectionListener(javax.swing.event.ListSelectionListener listener) {
        tableScores.getSelectionModel().addListSelectionListener(listener);
    }
}
