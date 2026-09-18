package electricbus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class DriverManagement extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public DriverManagement() {

        setTitle("Electric Bus Management System - Driver Management");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // ================= HEADER =================

        JPanel header = new JPanel();
        header.setBackground(new Color(16, 185, 129));
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(1000, 90));

        JLabel title = new JLabel("DRIVER MANAGEMENT");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));

        JButton backButton = new JButton("Back to Dashboard");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(37, 99, 235));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(190, 45));

        backButton.addActionListener(e -> {
            dispose();

            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
        });

        header.add(title, BorderLayout.WEST);
        header.add(backButton, BorderLayout.EAST);

        // ================= MAIN PANEL =================

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        );

        JLabel heading = new JLabel("Registered Drivers");
        heading.setFont(new Font("Arial", Font.BOLD, 26));
        heading.setForeground(new Color(30, 41, 59));

        mainPanel.add(heading, BorderLayout.NORTH);

        // ================= TABLE =================

        String[] columns = {
                "ID",
                "Driver Name",
                "Phone",
                "License Number",
                "Status"
        };

        model = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);

        table.setFont(new Font("Arial", Font.PLAIN, 15));
        table.setRowHeight(38);
        table.setGridColor(new Color(200, 200, 200));
        table.setSelectionBackground(new Color(219, 234, 254));
        table.setSelectionForeground(Color.BLACK);

        // Header style
        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        table.getTableHeader().setBackground(
                new Color(37, 99, 235)
        );

        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setPreferredSize(
                new Dimension(0, 42)
        );

        JScrollPane scrollPane = new JScrollPane(table);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // ================= BOTTOM =================

        JPanel bottomPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT)
        );

        bottomPanel.setBackground(
                new Color(245, 247, 250)
        );

        JButton refreshButton = new JButton("Refresh Drivers");

        refreshButton.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        refreshButton.setForeground(Color.WHITE);
        refreshButton.setBackground(
                new Color(16, 185, 129)
        );

        refreshButton.setFocusPainted(false);
        refreshButton.setBorderPainted(false);
        refreshButton.setPreferredSize(
                new Dimension(180, 45)
        );

        refreshButton.addActionListener(e -> loadDrivers());

        bottomPanel.add(refreshButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // ================= ADD TO FRAME =================

        add(header, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        // Load database data
        loadDrivers();
    }

    // =====================================================
    // LOAD DRIVERS FROM DATABASE
    // =====================================================

    private void loadDrivers() {

        model.setRowCount(0);

        String sql =
                "SELECT driver_id, driver_name, phone, " +
                "license_number, driver_status " +
                "FROM driver ORDER BY driver_id";

        try {

            Connection con = DBConnection.getConnection();

            if (con == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed!",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Object[] row = {

                        rs.getInt("driver_id"),

                        rs.getString("driver_name"),

                        rs.getString("phone"),

                        rs.getString("license_number"),

                        rs.getString("driver_status")
                };

                model.addRow(row);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error:\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                DriverManagement driverManagement =
                        new DriverManagement();

                driverManagement.setVisible(true);
            }
        });
    }
}