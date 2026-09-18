package electricbus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class BusManagement extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public BusManagement() {

        setTitle("Electric Bus Management - Bus Management");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ================= HEADER =================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(15, 30, 55));
        header.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("BUS MANAGEMENT");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));

        JButton backButton = new JButton("← Back");
        backButton.setBackground(new Color(52, 152, 219));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);

        backButton.addActionListener(e -> dispose());

        header.add(title, BorderLayout.WEST);
        header.add(backButton, BorderLayout.EAST);

        // ================= BUTTON PANEL =================
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        buttonPanel.setBackground(new Color(245, 247, 250));

        JButton addButton = new JButton("＋ Add Bus");
        JButton refreshButton = new JButton("⟳ Refresh");
        JButton deleteButton = new JButton("✕ Delete");

        styleButton(addButton, new Color(46, 204, 113));
        styleButton(refreshButton, new Color(52, 152, 219));
        styleButton(deleteButton, new Color(231, 76, 60));

        buttonPanel.add(addButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);

        // ================= TABLE =================
        model = new DefaultTableModel();

        model.setColumnIdentifiers(new String[]{
                "ID",
                "Bus Number",
                "Model",
                "Battery Capacity",
                "Battery %",
                "Seats",
                "Status"
        });

        table = new JTable(model);
        table.setRowHeight(32);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(52, 73, 94));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // ================= MAIN =================
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        // ================= ACTIONS =================

        addButton.addActionListener(e -> addBus());

        refreshButton.addActionListener(e -> loadBuses());

        deleteButton.addActionListener(e -> deleteBus());

        // Load data
        loadBuses();
    }

    // ================= BUTTON STYLE =================

    private void styleButton(JButton button, Color color) {

        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    // ================= LOAD BUSES =================

    private void loadBuses() {

        model.setRowCount(0);

        String sql =
                "SELECT bus_id, bus_number, model, battery_capacity, " +
                "battery_percentage, total_seats, bus_status " +
                "FROM bus";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("bus_id"),
                        rs.getString("bus_number"),
                        rs.getString("model"),
                        rs.getDouble("battery_capacity"),
                        rs.getDouble("battery_percentage"),
                        rs.getInt("total_seats"),
                        rs.getString("bus_status")
                });
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= ADD BUS =================

    private void addBus() {

        JTextField busNumber =
                new JTextField();

        JTextField busModel =
                new JTextField();

        JTextField battery =
                new JTextField();

        JTextField batteryPercent =
                new JTextField();

        JTextField seats =
                new JTextField();

        JComboBox<String> status =
                new JComboBox<>(new String[]{
                        "Available",
                        "Charging",
                        "Maintenance"
                });

        JPanel panel = new JPanel(
                new GridLayout(6, 2, 10, 10)
        );

        panel.add(new JLabel("Bus Number:"));
        panel.add(busNumber);

        panel.add(new JLabel("Model:"));
        panel.add(busModel);

        panel.add(new JLabel("Battery Capacity:"));
        panel.add(battery);

        panel.add(new JLabel("Battery %:"));
        panel.add(batteryPercent);

        panel.add(new JLabel("Total Seats:"));
        panel.add(seats);

        panel.add(new JLabel("Status:"));
        panel.add(status);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add New Bus",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        if (busNumber.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Bus Number."
            );

            return;
        }

        String sql =
                "INSERT INTO bus " +
                "(bus_number, model, battery_capacity, " +
                "battery_percentage, total_seats, bus_status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, busNumber.getText().trim());
            ps.setString(2, busModel.getText().trim());
            ps.setDouble(3, Double.parseDouble(battery.getText()));
            ps.setDouble(4, Double.parseDouble(batteryPercent.getText()));
            ps.setInt(5, Integer.parseInt(seats.getText()));
            ps.setString(6, status.getSelectedItem().toString());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Bus Added Successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadBuses();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numeric values.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= DELETE BUS =================

    private void deleteBus() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a bus first.",
                    "Select Bus",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int busId =
                Integer.parseInt(
                        table.getValueAt(selectedRow, 0).toString()
                );

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this bus?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String sql =
                "DELETE FROM bus WHERE bus_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, busId);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Bus Deleted Successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadBuses();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            BusManagement window =
                    new BusManagement();

            window.setVisible(true);
        });
    }
}