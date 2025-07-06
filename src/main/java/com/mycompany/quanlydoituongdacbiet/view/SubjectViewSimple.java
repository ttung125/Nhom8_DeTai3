/*
 * View đơn giản để quản lý môn thi
 */
package com.mycompany.quanlydoituongdacbiet.view;

import com.mycompany.quanlydoituongdacbiet.entity.Subject;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * View đơn giản để quản lý môn thi
 * 
 * @author PC
 */
public class SubjectViewSimple extends JFrame {
    
    private JTextField txtSubjectId;
    private JTextField txtSubjectName;
    private JTextField txtSubjectCode;
    private JTextField txtExamTime;
    private JTextField txtMaxScore;
    private JTextArea txtDescription;
    private JTextField txtSearchName;
    private JTextField txtMinTime;
    private JTextField txtMaxTime;
    
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnSearch;
    private JButton btnSearchTime;
    private JButton btnSort;
    private JButton btnBack;
    
    private JTable tableSubjects;
    private DefaultTableModel tableModel;
    private JLabel lblStatus;
    private JLabel lblTotal;
    private JLabel lblAvgTime;
    
    public SubjectViewSimple() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        setTitle("Quản lý môn thi");
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
        
        JLabel title = new JLabel("QUẢN LÝ MÔN THI");
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
            "Thông tin môn thi",
            0, 0, new Font("Times New Roman", Font.BOLD, 16), Color.WHITE));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Row 0: Subject ID
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(createLabel("Mã môn:"), gbc);
        gbc.gridx = 1;
        txtSubjectId = new JTextField(15);
        txtSubjectId.setEditable(false); // Auto-generate
        panel.add(txtSubjectId, gbc);
        
        // Row 1: Subject Name
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(createLabel("Tên môn:"), gbc);
        gbc.gridx = 1;
        txtSubjectName = new JTextField(15);
        panel.add(txtSubjectName, gbc);
        
        // Row 2: Subject Code
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(createLabel("Mã code:"), gbc);
        gbc.gridx = 1;
        txtSubjectCode = new JTextField(15);
        panel.add(txtSubjectCode, gbc);
        
        // Row 3: Exam Time
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(createLabel("Thời gian thi (phút):"), gbc);
        gbc.gridx = 1;
        txtExamTime = new JTextField(15);
        panel.add(txtExamTime, gbc);
        
        // Row 4: Max Score
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(createLabel("Điểm tối đa:"), gbc);
        gbc.gridx = 1;
        txtMaxScore = new JTextField(15);
        txtMaxScore.setText("10.0"); // Default value
        panel.add(txtMaxScore, gbc);
        
        // Row 5: Description
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(createLabel("Mô tả:"), gbc);
        gbc.gridx = 1;
        txtDescription = new JTextArea(3, 15);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescription);
        panel.add(scrollDesc, gbc);
        
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
        
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);
        
        // Search panel
        JPanel searchPanel = createSearchPanel();
        gbc.gridy = 7;
        panel.add(searchPanel, gbc);
        
        // Back button
        btnBack = createButton("Quay lại");
        gbc.gridy = 8;
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
        panel.add(createLabel("Tên môn:"), gbc);
        gbc.gridx = 1;
        txtSearchName = new JTextField(10);
        panel.add(txtSearchName, gbc);
        gbc.gridx = 2;
        btnSearch = createSmallButton("Tìm");
        panel.add(btnSearch, gbc);
        
        // Search by time range
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(createLabel("Thời gian từ:"), gbc);
        gbc.gridx = 1;
        txtMinTime = new JTextField(5);
        panel.add(txtMinTime, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(createLabel("đến:"), gbc);
        gbc.gridx = 1;
        txtMaxTime = new JTextField(5);
        panel.add(txtMaxTime, gbc);
        gbc.gridx = 2;
        btnSearchTime = createSmallButton("Tìm");
        panel.add(btnSearchTime, gbc);
        
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
            "Danh sách môn thi",
            0, 0, new Font("Times New Roman", Font.BOLD, 16), Color.WHITE));
        
        String[] columnNames = {
            "Mã môn", "Tên môn", "Mã code", "Thời gian (phút)", 
            "Điểm tối đa", "Mô tả"
        };
        
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableSubjects = new JTable(tableModel);
        tableSubjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableSubjects.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        tableSubjects.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
        
        JScrollPane scrollPane = new JScrollPane(tableSubjects);
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
        
        lblAvgTime = new JLabel("Thời gian TB: 0");
        lblAvgTime.setForeground(Color.WHITE);
        lblAvgTime.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        
        panel.add(lblStatus);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(lblTotal);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(lblAvgTime);
        
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
    public Subject getSubjectInfo() {
        try {
            String subjectId = txtSubjectId.getText().trim();
            String subjectName = txtSubjectName.getText().trim();
            String subjectCode = txtSubjectCode.getText().trim().toUpperCase();
            int examTime = Integer.parseInt(txtExamTime.getText().trim());
            double maxScore = Double.parseDouble(txtMaxScore.getText().trim());
            String description = txtDescription.getText().trim();
            
            if (subjectName.isEmpty() || subjectCode.isEmpty()) {
                showMessage("Vui lòng nhập đầy đủ thông tin bắt buộc!");
                return null;
            }
            
            Subject subject = new Subject();
            if (!subjectId.isEmpty()) {
                subject.setSubjectCode(subjectId);
            }
            subject.setSubjectName(subjectName);
            subject.setSubjectCode(subjectCode);
            subject.setDuration(examTime);
            subject.setMaxScore(maxScore);
            subject.setDescription(description);
            
            return subject;
            
        } catch (NumberFormatException e) {
            showMessage("Vui lòng nhập đúng định dạng số cho thời gian và điểm!");
            return null;
        } catch (Exception e) {
            showMessage("Lỗi khi lấy thông tin: " + e.getMessage());
            return null;
        }
    }
    
    public void showSubjectInfo(Subject subject) {
        if (subject != null) {
            txtSubjectId.setText(subject.getSubjectCode());
            txtSubjectName.setText(subject.getSubjectName());
            txtSubjectCode.setText(subject.getSubjectCode());
            txtExamTime.setText(String.valueOf(subject.getDuration()));
            txtMaxScore.setText(String.valueOf(subject.getMaxScore()));
            txtDescription.setText(subject.getDescription());
        }
    }
    
    public void showListSubjects(List<Subject> subjects) {
        tableModel.setRowCount(0);
        for (Subject subject : subjects) {
            Object[] row = {
                subject.getSubjectCode(),
                subject.getSubjectName(),
                subject.getSubjectCode(),
                subject.getDuration(),
                subject.getMaxScore(),
                subject.getDescription()
            };
            tableModel.addRow(row);
        }
    }
    
    public void showStatistics(int total, double avgTime) {
        lblTotal.setText("Tổng: " + String.format("%,d", total));
        lblAvgTime.setText("Thời gian TB: " + String.format("%.1f phút", avgTime));
    }
    
    public void clearForm() {
        txtSubjectId.setText("");
        txtSubjectName.setText("");
        txtSubjectCode.setText("");
        txtExamTime.setText("");
        txtMaxScore.setText("10.0");
        txtDescription.setText("");
        txtSearchName.setText("");
        txtMinTime.setText("");
        txtMaxTime.setText("");
    }
    
    public String getSearchName() {
        return txtSearchName.getText().trim();
    }
    
    public int getMinTime() {
        try {
            return Integer.parseInt(txtMinTime.getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public int getMaxTime() {
        try {
            return Integer.parseInt(txtMaxTime.getText().trim());
        } catch (NumberFormatException e) {
            return 999;
        }
    }
    
    public int getSelectedRow() {
        return tableSubjects.getSelectedRow();
    }
    
    public String getSelectedSubjectCode() {
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
    
    public void showSubjectView() {
        setVisible(true);
    }
    
    // Listener methods
    public void addAddSubjectListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }
    
    public void addEditSubjectListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }
    
    public void addDeleteSubjectListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }
    
    public void addSearchListener(ActionListener listener) {
        btnSearch.addActionListener(listener);
    }
    
    public void addSearchTimeListener(ActionListener listener) {
        btnSearchTime.addActionListener(listener);
    }
    
    public void addSortListener(ActionListener listener) {
        btnSort.addActionListener(listener);
    }
    
    public void addBackListener(ActionListener listener) {
        btnBack.addActionListener(listener);
    }
    
    public void addTableSelectionListener(javax.swing.event.ListSelectionListener listener) {
        tableSubjects.getSelectionModel().addListSelectionListener(listener);
    }
}
