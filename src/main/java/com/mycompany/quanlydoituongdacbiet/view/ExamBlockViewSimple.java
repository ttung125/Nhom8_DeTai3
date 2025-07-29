/*
 * View đơn giản để quản lý khối thi
 */
package com.mycompany.quanlydoituongdacbiet.view;

import com.mycompany.quanlydoituongdacbiet.entity.ExamBlock;
import com.mycompany.quanlydoituongdacbiet.util.XMLUtil;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * View đơn giản để quản lý khối thi
 * 
 * @author PC
 */
public class ExamBlockViewSimple extends JFrame {
    
    private JTextField txtBlockId;
    private JTextField txtBlockName;
    private JTextField txtBlockCode;
    private JTextArea txtDescription;
    private JList<String> listAvailableSubjects;
    private JList<String> listSelectedSubjects;
    private DefaultListModel<String> availableSubjectsModel;
    private DefaultListModel<String> selectedSubjectsModel;
    private JTextField txtSearchName;
    private JTextField txtMinSubjects;
    private JTextField txtMaxSubjects;
    
    
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnSearch;
    private JButton btnSearchSubjects;
    private JButton btnSort;
    private JButton btnBack;
    private JButton btnAddSubject;
    private JButton btnRemoveSubject;

    
    private JTable tableExamBlocks;
    private DefaultTableModel tableModel;
    private JLabel lblStatus;
    private JLabel lblTotal;
    private JLabel lblAvgSubjects;
    
    // Available subjects for selection
    /*private final String[] ALL_SUBJECTS = {
        "Toán", "Vật Lý", "Hóa Học", "Sinh Học", "Ngữ Văn", 
        "Tiếng Anh", "Lịch Sử", "Địa Lý", "GDCD","Tiếng Trung"
    };*/
    private List<String> allSubjects = new ArrayList<>();
    
    public ExamBlockViewSimple() {
        initComponents();
        loadSubjectsFromFile("data/Subjects.xml"); // đường dẫn tới file XML của bạn
        setLocationRelativeTo(null);
    }
    
    public void loadSubjectsFromFile(String filePath) {
        allSubjects = XMLUtil.readSubjectNamesFromFile(filePath);

        availableSubjectsModel.clear();
        selectedSubjectsModel.clear(); // Xóa môn đã chọn khi reload

        for (String subject : allSubjects) {
        availableSubjectsModel.addElement(subject);
        }
    }

    private void initComponents() {
        setTitle("QUẢN LÝ KHỐI THI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 800);
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
        
        JLabel title = new JLabel("QUẢN LÝ KHỐI THI");
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
            "Thông tin khối thi",
            0, 0, new Font("Times New Roman", Font.BOLD, 16), Color.WHITE));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Row 0: Block ID
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(createLabel("Mã khối:"), gbc);
        gbc.gridx = 1;
        txtBlockId = new JTextField(15);
        txtBlockId.setEditable(false); // Auto-generate
        panel.add(txtBlockId, gbc);
        
        // Row 1: Block Name
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(createLabel("Tên khối:"), gbc);
        gbc.gridx = 1;
        txtBlockName = new JTextField(15);
        panel.add(txtBlockName, gbc);
        
        // Row 2: Block Code
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(createLabel("Mã code:"), gbc);
        gbc.gridx = 1;
        txtBlockCode = new JTextField(15);
        panel.add(txtBlockCode, gbc);
        
        // Row 3: Subject selection
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(createSubjectSelectionPanel(), gbc);
        gbc.gridwidth = 1;
        
        // Row 4: Description
        gbc.gridx = 0; gbc.gridy = 4;
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
        
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);
        
        // Search panel
        JPanel searchPanel = createSearchPanel();
        gbc.gridy = 6;
        panel.add(searchPanel, gbc);
        
        // Back button
        btnBack = createButton("Quay lại");
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(btnBack, gbc);
        
        return panel;
    }
    
    private JPanel createSubjectSelectionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE, 1), 
            "Chọn môn thi",
            0, 0, new Font("Times New Roman", Font.BOLD, 14), Color.WHITE));
        
        // Available subjects
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);
        leftPanel.add(createLabel("Môn có sẵn:"), BorderLayout.NORTH);
        
        availableSubjectsModel = new DefaultListModel<>();
        for (String subject : allSubjects) {
            availableSubjectsModel.addElement(subject);
        }

        listAvailableSubjects = new JList<>(availableSubjectsModel);
        listAvailableSubjects.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollAvailable = new JScrollPane(listAvailableSubjects);
        scrollAvailable.setPreferredSize(new Dimension(150, 100));
        leftPanel.add(scrollAvailable, BorderLayout.CENTER);
        
        // Control buttons
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        centerPanel.setOpaque(false);
        btnAddSubject = createArrowButton(">");
        btnRemoveSubject = createArrowButton("<");
        centerPanel.add(btnAddSubject);
        centerPanel.add(btnRemoveSubject);
        
        // Selected subjects
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.add(createLabel("Môn đã chọn:"), BorderLayout.NORTH);
        
        selectedSubjectsModel = new DefaultListModel<>();
        listSelectedSubjects = new JList<>(selectedSubjectsModel);
        listSelectedSubjects.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollSelected = new JScrollPane(listSelectedSubjects);
        scrollSelected.setPreferredSize(new Dimension(150, 100));
        rightPanel.add(scrollSelected, BorderLayout.CENTER);
        
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);
        
        // Add button listeners
        btnAddSubject.addActionListener(e -> moveSubjects(listAvailableSubjects, availableSubjectsModel, selectedSubjectsModel));
        btnRemoveSubject.addActionListener(e -> moveSubjects(listSelectedSubjects, selectedSubjectsModel, availableSubjectsModel));
        
        return panel;
    }
    
    private void moveSubjects(JList<String> sourceList, DefaultListModel<String> sourceModel, DefaultListModel<String> targetModel) {
        List<String> selectedValues = sourceList.getSelectedValuesList();
        for (String value : selectedValues) {
            sourceModel.removeElement(value);
            targetModel.addElement(value);
        }
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
        panel.add(createLabel("Tên khối:"), gbc);
        gbc.gridx = 1;
        txtSearchName = new JTextField(10);
        panel.add(txtSearchName, gbc);
        gbc.gridx = 2;
        btnSearch = createSmallButton("Tìm");
        panel.add(btnSearch, gbc);
        
        // Search by subject count range
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(createLabel("Số môn từ:"), gbc);
        gbc.gridx = 1;
        txtMinSubjects = new JTextField(5);
        panel.add(txtMinSubjects, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(createLabel("đến:"), gbc);
        gbc.gridx = 1;
        txtMaxSubjects = new JTextField(5);
        panel.add(txtMaxSubjects, gbc);
        gbc.gridx = 2;
        btnSearchSubjects = createSmallButton("Tìm");
        panel.add(btnSearchSubjects, gbc);
        
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
            "Danh sách khối thi",
            0, 0, new Font("Times New Roman", Font.BOLD, 16), Color.WHITE));
        
        String[] columnNames = {
            "Mã khối", "Tên khối", "Mã code", "Số môn", 
            "Danh sách môn", "Mô tả"
        };
        
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableExamBlocks = new JTable(tableModel);
        tableExamBlocks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableExamBlocks.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        tableExamBlocks.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
        
        JScrollPane scrollPane = new JScrollPane(tableExamBlocks);
        scrollPane.setPreferredSize(new Dimension(800, 400));
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
        
        lblAvgSubjects = new JLabel("Số môn TB: 0");
        lblAvgSubjects.setForeground(Color.WHITE);
        lblAvgSubjects.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        
        panel.add(lblStatus);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(lblTotal);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(lblAvgSubjects);
        
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
    
    private JButton createArrowButton(String text) {
        JButton button = createButton(text);
        button.setFont(new Font("Times New Roman", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(50, 25)); // nhỏ hơn
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }


    
    // Getter methods for form data
    public ExamBlock getExamBlockInfo() {
        try {
            String blockId = txtBlockId.getText().trim();
            String blockName = txtBlockName.getText().trim();
            String blockCode = txtBlockCode.getText().trim().toUpperCase();
            String description = txtDescription.getText().trim();
            
            if (blockName.isEmpty() || blockCode.isEmpty()) {
                showMessage("Vui lòng nhập đầy đủ thông tin bắt buộc!");
                return null;
            }
            
            // Get selected subjects
            List<String> subjects = new ArrayList<>();
            for (int i = 0; i < selectedSubjectsModel.size(); i++) {
                subjects.add(selectedSubjectsModel.getElementAt(i));
            }
            
            if (subjects.isEmpty()) {
                showMessage("Vui lòng chọn ít nhất một môn thi!");
                return null;
            }
            
            ExamBlock examBlock = new ExamBlock();
            if (!blockId.isEmpty()) {
                examBlock.setBlockCode(blockId);
            }
            examBlock.setBlockName(blockName);
            examBlock.setBlockCode(blockCode);
            examBlock.setSubjects(subjects);
            examBlock.setDescription(description);
            
            return examBlock;
            
        } catch (Exception e) {
            showMessage("Lỗi khi lấy thông tin: " + e.getMessage());
            return null;
        }
    }
    
    public void showExamBlockInfo(ExamBlock examBlock) {
        if (examBlock != null) {
            txtBlockId.setText(examBlock.getBlockCode());
            txtBlockName.setText(examBlock.getBlockName());
            txtBlockCode.setText(examBlock.getBlockCode());
            txtDescription.setText(examBlock.getDescription());
            
            // Reset subject lists
            selectedSubjectsModel.clear();
            availableSubjectsModel.clear();
            
            // Add selected subjects
            List<String> subjects = examBlock.getSubjects();
            if (subjects != null) {
                for (String subject : subjects) {
                    selectedSubjectsModel.addElement(subject);
                }
            }
            
            // Add remaining available subjects
            for (String subject : allSubjects) {
                if (!subjects.contains(subject)) {
                    availableSubjectsModel.addElement(subject);
                }
            }
        }
    }
    
    public void showListExamBlocks(List<ExamBlock> examBlocks) {
        tableModel.setRowCount(0);
        for (ExamBlock examBlock : examBlocks) {
            String subjectList = String.join(", ", examBlock.getSubjects());
            Object[] row = {
                examBlock.getBlockCode(),
                examBlock.getBlockName(),
                examBlock.getBlockCode(),
                examBlock.getTotalSubjects(),
                subjectList,
                examBlock.getDescription()
            };
            tableModel.addRow(row);
        }
    }
    
    public void showStatistics(int total, double avgSubjects) {
        lblTotal.setText("Tổng: " + String.format("%,d", total));
        lblAvgSubjects.setText("Số môn TB: " + String.format("%.1f", avgSubjects));
    }
    
    public void clearForm() {
        txtBlockId.setText("");
        txtBlockName.setText("");
        txtBlockCode.setText("");
        txtDescription.setText("");
        txtSearchName.setText("");
        txtMinSubjects.setText("");
        txtMaxSubjects.setText("");
        
        // Reset subject lists
        selectedSubjectsModel.clear();
        availableSubjectsModel.clear();
        for (String subject : allSubjects) {
            availableSubjectsModel.addElement(subject);
        }
    }
    
    public String getSearchName() {
        return txtSearchName.getText().trim();
    }
    
    public int getMinSubjects() {
        try {
            return Integer.parseInt(txtMinSubjects.getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public int getMaxSubjects() {
        try {
            return Integer.parseInt(txtMaxSubjects.getText().trim());
        } catch (NumberFormatException e) {
            return 10;
        }
    }
    
    public int getSelectedRow() {
        return tableExamBlocks.getSelectedRow();
    }
    
    public String getSelectedExamBlockCode() {
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
    
    public void showExamBlockView() {
        setVisible(true);
    }
    
    // Listener methods
    public void addAddExamBlockListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }
    
    public void addEditExamBlockListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }
    
    public void addDeleteExamBlockListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }
    
    public void addSearchListener(ActionListener listener) {
        btnSearch.addActionListener(listener);
    }
    
    public void addSearchSubjectsListener(ActionListener listener) {
        btnSearchSubjects.addActionListener(listener);
    }
    
    public void addSortListener(ActionListener listener) {
        btnSort.addActionListener(listener);
    }
    
    public void addBackListener(ActionListener listener) {
        btnBack.addActionListener(listener);
    }
    
    public void addTableSelectionListener(javax.swing.event.ListSelectionListener listener) {
        tableExamBlocks.getSelectionModel().addListSelectionListener(listener);
    }
}
