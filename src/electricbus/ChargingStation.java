package electricbus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ChargingStation extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ChargingStation() {

        setTitle("Electric Bus Management System - Charging Stations");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(245, 158, 11));
        header.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("CHARGING STATION MANAGEMENT");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 26));

        JButton backButton = new JButton("Back to Dashboard");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(30, 64, 175));
        backButton.setFocusPainted(false);

        backButton.addActionListener(e -> {
            dispose();
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
        });

        header.add(title, BorderLayout.WEST);
        header.add(backButton, BorderLayout.EAST);

        mainPanel.add(header, BorderLayout.NORTH);

        // Heading
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(new Color(245, 247, 250));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel heading = new JLabel("Available Charging Stations");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setForeground(new Color(31, 41, 55));

        centerPanel.add(heading, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Station Name");
        model.addColumn("Location");
        model.addColumn("Charger Type");
        model.addColumn("Total Chargers");
        model.addColumn("Available Chargers");
        model.addColumn("Status");

        table = new JTable(model);

        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(35);
        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );
        table.getTableHeader().setBackground(
                new Color(30, 64, 175)
        );
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Refresh button
        JButton refreshButton = new JButton("Refresh Stations");
        refreshButton.setFont(new Font("Arial", Font.BOLD, 15));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setBackground(new Color(16, 185, 129));
        refreshButton.setFocusPainted(false);

        refreshButton.addActionListener(e -> loadStations());

        JPanel bottomPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT)
        );
        bottomPanel.setBackground(new Color(245, 247, 250));
        bottomPanel.add(refreshButton);

        centerPanel.add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);

        // Load database data
        loadStations();
    }

    // ================= LOAD STATIONS =================

    private void loadStations() {

        model.setRowCount(0);

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = DBConnection.getConnection();

            String sql =
                    "SELECT station_id, station_name, location, " +
                    "charger_type, total_chargers, available_chargers " +
                    "FROM charging_station";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("station_id");

                String stationName =
                        rs.getString("station_name");

                String location =
                        rs.getString("location");

                String chargerType =
                        rs.getString("charger_type");

                int total =
                        rs.getInt("total_chargers");

                int available =
                        rs.getInt("available_chargers");

                String status;

                if (available > 0) {
                    status = "Available";
                } else {
                    status = "Full";
                }

                model.addRow(new Object[] {
                        id,
                        stationName,
                        location,
                        chargerType,
                        total,
                        available,
                        status
                });
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();

        } finally {

            try {
                if (rs != null)
                    rs.close();

                if (ps != null)
                    ps.close();

                if (con != null)
                    con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                ChargingStation station =
                        new ChargingStation();

                station.setVisible(true);
            }
        });
    }
}