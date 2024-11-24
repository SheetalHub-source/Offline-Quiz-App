/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author priya
 */
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import project.Connectionprovider;

public class ViewResults extends javax.swing.JFrame {

    private JTable resultsTable;
    private JScrollPane scrollPane;
    private JLabel titleLabel;
    private JButton backButton;

    public ViewResults(String rollNo) {
        initComponents();
        loadResults(rollNo);
    }

    private void initComponents() {
        // Initialize components
        setTitle("View Quiz Results");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        titleLabel = new JLabel("Quiz Results");
        titleLabel.setFont(new java.awt.Font("Algerian", 1, 36));
        titleLabel.setBounds(300, 20, 300, 50);
        add(titleLabel);

        resultsTable = new JTable();
        scrollPane = new JScrollPane(resultsTable);
        scrollPane.setBounds(50, 100, 700, 400);
        add(scrollPane);

        backButton = new JButton("Back");
        backButton.setBounds(350, 520, 100, 30);
        backButton.addActionListener(e -> {
            setVisible(false);
            new index().setVisible(true); // Go back to main menu
        });
        add(backButton);
    }

    private void loadResults(String rollNo) {
        try {
            Connection con = Connectionprovider.getCon();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM quiz_results WHERE roll_no = ?");
            ps.setString(1, rollNo);
            ResultSet rs = ps.executeQuery();
            resultsTable.setModel(DbUtils.resultSetToTableModel(rs)); // Populate the table with results
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading results: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Example usage
        SwingUtilities.invokeLater(() -> new ViewResults("10001").setVisible(true));
    }
}