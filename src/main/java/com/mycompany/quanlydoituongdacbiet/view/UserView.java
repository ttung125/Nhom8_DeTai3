package com.mycompany.quanlydoituongdacbiet.view;

import com.mycompany.quanlydoituongdacbiet.action.ManagerExamScore;
import com.mycompany.quanlydoituongdacbiet.entity.ExamScore;
import com.mycompany.quanlydoituongdacbiet.entity.User;
import com.mycompany.quanlydoituongdacbiet.controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.border.TitledBorder;

public class UserView extends JFrame {

    private JTextField txtStudentId;
    private JButton btnSearch;
    private JButton btnClear;
    private JButton btnLogout;
    private JPanel scoresPanel;

    private final DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private final ManagerExamScore manager = new ManagerExamScore();

    public UserView(User user) {
        setTitle("Tra cứu điểm - " + user.getUserName());
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/background.jpg"));
        background.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tiêu đề
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JLabel title1 = new JLabel("BỘ GIÁO DỤC VÀ ĐÀO TẠO");
        title1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        title1.setForeground(Color.YELLOW);
        title1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title2 = new JLabel("TRANG THÔNG TIN KỲ THI QUỐC GIA");
        title2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        title2.setForeground(Color.YELLOW);
        title2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("TRA CỨU ĐIỂM THI THEO MÃ THÍ SINH");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

// Thêm các tiêu đề vào headerPanel
        headerPanel.add(title1);
        headerPanel.add(title2);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 40))); // Khoảng cách
        headerPanel.add(titleLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Panel nội dung (input + kết quả)
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);

        // Panel nhập mã thí sinh
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setOpaque(false);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblId = new JLabel("Mã thí sinh:");
        lblId.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblId.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(lblId, gbc);

        txtStudentId = new JTextField(15);
        txtStudentId.setText(user.getUserName());
        txtStudentId.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtStudentId.setPreferredSize(new Dimension(200, 35));
        txtStudentId.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        gbc.gridx = 1;
        inputPanel.add(txtStudentId, gbc);

        btnSearch = new JButton("Tra cứu");
        btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnSearch.setBackground(new Color(0, 123, 255));
        btnSearch.setForeground(Color.WHITE);
        gbc.gridx = 2;
        inputPanel.add(btnSearch, gbc);

        btnClear = new JButton("Làm mới");
        btnClear.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnClear.setBackground(new Color(108, 117, 125));
        btnClear.setForeground(Color.WHITE);
        gbc.gridx = 3;
        inputPanel.add(btnClear, gbc);

        btnLogout = new JButton("Đăng xuất");
        btnLogout.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnLogout.setBackground(new Color(220, 53, 69)); // Bootstrap danger color
        btnLogout.setForeground(Color.WHITE);
        gbc.gridx = 4;
        inputPanel.add(btnLogout, gbc);

        contentPanel.add(inputPanel, BorderLayout.NORTH);

        // Panel kết quả
        scoresPanel = new JPanel();
        scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.Y_AXIS));
        scoresPanel.setOpaque(false);
        scoresPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JScrollPane scrollPane = new JScrollPane(scoresPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        contentPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        background.add(mainPanel);
        add(background);

        // Xử lý sự kiện
        btnSearch.addActionListener(e -> traCuuDiem());
        btnClear.addActionListener(e -> clearData());
        btnLogout.addActionListener(e -> logout());
    }

    private void traCuuDiem() {

        String studentId = txtStudentId.getText().trim();
        if (studentId.isEmpty()) {
            showMessage("Vui lòng nhập mã thí sinh!");
            return;
        }

        List<ExamScore> scores = manager.findByStudent(studentId);
        showScoreList(scores);
    }

    private void showScoreList(List<ExamScore> scores) {
        scoresPanel.removeAll();
        scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.Y_AXIS));

        if (scores == null || scores.isEmpty()) {
            // Panel cho thông báo không có dữ liệu
            JPanel noDataPanel = new JPanel();
            noDataPanel.setOpaque(false);
            noDataPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));

            JLabel noData = new JLabel("Không tìm thấy dữ liệu điểm thi");
            noData.setFont(new Font("Segoe UI", Font.BOLD, 20));
            noData.setForeground(new Color(220, 53, 69)); // Bootstrap danger color
            noData.setHorizontalAlignment(SwingConstants.CENTER);

            noDataPanel.add(noData);
            scoresPanel.add(noDataPanel);
        } else {
            ExamScore first = scores.get(0);

            // Header thông tin học sinh
            JPanel headerPanel = createHeaderPanel(first);
            scoresPanel.add(headerPanel);

            // Thêm khoảng cách
            scoresPanel.add(Box.createVerticalStrut(20));

            // Panel chứa điểm các môn
            JPanel scoresContainer = createScoresContainer(scores);
            scoresPanel.add(scoresContainer);

            // Thêm khoảng cách
            scoresPanel.add(Box.createVerticalStrut(25));

            // Panel thống kê
            JPanel statsPanel = createStatsPanel(scores);
            scoresPanel.add(statsPanel);
        }

        scoresPanel.revalidate();
        scoresPanel.repaint();
    }

    private JPanel createHeaderPanel(ExamScore first) {
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        headerPanel.setBackground(new Color(52, 58, 64, 200));

        JLabel lblName = new JLabel("Họ và tên: " + first.getFullName());
        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        lblName.setForeground(new Color(0, 0, 0));

        JLabel lblId = new JLabel("Mã thí sinh: " + first.getStudentId());
        lblId.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblId.setForeground(new Color(0, 0, 0));

        headerPanel.add(lblName, BorderLayout.WEST);
        headerPanel.add(lblId, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createScoresContainer(List<ExamScore> scores) {
        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);

        // Tiêu đề
        JLabel titleLabel = new JLabel("BẢNG ĐIỂM CHI TIẾT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        container.add(titleLabel, BorderLayout.NORTH);

        // Grid điểm
        JPanel grid = new JPanel(new GridLayout(0, 3, 15, 15));
        grid.setOpaque(false);
        grid.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        for (ExamScore score : scores) {
            JPanel scoreItem = createScoreItem(score);
            grid.add(scoreItem);
        }

        container.add(grid, BorderLayout.CENTER);
        return container;
    }

    private JPanel createScoreItem(ExamScore score) {
        JPanel scoreItem = new JPanel();
        scoreItem.setLayout(new BoxLayout(scoreItem, BoxLayout.Y_AXIS));
        scoreItem.setBackground(new Color(255, 255, 255, 250));
        scoreItem.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(206, 212, 218), 2, true),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));

        // Tên môn học
        JLabel lblSubject = new JLabel(score.getSubjectCode());
        lblSubject.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSubject.setForeground(new Color(52, 58, 64));
        lblSubject.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Điểm số
        JLabel lblScore = new JLabel(decimalFormat.format(score.getScore()));
        lblScore.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblScore.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Màu sắc dựa trên điểm
        if (score.getScore() >= 8.0) {
            lblScore.setForeground(new Color(40, 167, 69)); // Xanh lá đậm
        } else if (score.getScore() >= 6.5) {
            lblScore.setForeground(new Color(23, 162, 184)); // Xanh dương
        } else if (score.getScore() >= 5.0) {
            lblScore.setForeground(new Color(255, 193, 7)); // Vàng
        } else {
            lblScore.setForeground(new Color(220, 53, 69)); // Đỏ
        }

        scoreItem.add(lblSubject);
        scoreItem.add(Box.createVerticalStrut(8));
        scoreItem.add(lblScore);

        return scoreItem;
    }

    private JPanel createStatsPanel(List<ExamScore> scores) {
        JPanel statsPanel = new JPanel();
        statsPanel.setOpaque(false);
        statsPanel.setLayout(new GridLayout(1, 3, 20, 0));
        statsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(255, 255, 255), 2),
                        "THỐNG KÊ TỔNG QUAN",
                        TitledBorder.CENTER,
                        TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 16),
                        Color.WHITE
                ),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        long passed = scores.stream().filter(s -> s.getScore() >= 5.0).count();
        double avg = scores.stream().mapToDouble(ExamScore::getScore).average().orElse(0);

        // Tổng số môn
        JPanel totalPanel = createStatItem("Tổng môn", String.valueOf(scores.size()), new Color(108, 117, 125));

        // Môn đạt
        JPanel passedPanel = createStatItem("Môn đạt", String.valueOf(passed), new Color(40, 167, 69));

        // Điểm trung bình
        Color avgColor = avg >= 8.0 ? new Color(40, 167, 69)
                : avg >= 6.5 ? new Color(23, 162, 184)
                        : avg >= 5.0 ? new Color(255, 193, 7) : new Color(220, 53, 69);
        JPanel avgPanel = createStatItem("Trung bình", decimalFormat.format(avg), avgColor);

        statsPanel.add(totalPanel);
        statsPanel.add(passedPanel);
        statsPanel.add(avgPanel);

        return statsPanel;
    }

    private JPanel createStatItem(String label, String value, Color valueColor) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel labelText = new JLabel(label, SwingConstants.CENTER);
        labelText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        labelText.setForeground(new Color(0, 0, 0));
        labelText.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel valueText = new JLabel(value, SwingConstants.CENTER);
        valueText.setFont(new Font("Segoe UI", Font.BOLD, 20));
        valueText.setForeground(valueColor);
        valueText.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(labelText);
        panel.add(Box.createVerticalStrut(8));
        panel.add(valueText);

        return panel;
    }

    private void clearData() {
        txtStudentId.setText("");
        scoresPanel.removeAll();
        scoresPanel.revalidate();
        scoresPanel.repaint();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn đăng xuất?",
            "Xác nhận đăng xuất",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            System.out.println("🔒 Đăng xuất khỏi UserView...");
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView);
            loginController.showLoginView();
            this.dispose(); // Đóng cửa sổ hiện tại
            System.out.println("✅ Quay về màn hình đăng nhập!");
        }
    }
}
