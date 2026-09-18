package electricbus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class RouteManagement extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public RouteManagement() {

        setTitle("Electric Bus Management System - Route Management");
        setSize(1250, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));

        // ================= HEADER =================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(139, 92, 246));
        header.setPreferredSize(new Dimension(1250, 115));

        JLabel title = new JLabel("ROUTE MANAGEMENT");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));

        JButton backButton = new JButton("Back to Dashboard");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(37, 99, 235));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(230, 55));

        backButton.addActionListener(e -> {
            dispose();

            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
        });

        header.add(title, BorderLayout.WEST);
        header.add(backButton, BorderLayout.EAST);

        // ================= CONTENT =================
        JPanel content = new JPanel(new BorderLayout(15, 15));
        content.setBackground(new Color(245, 247, 250));
        content.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        );

        JLabel heading = new JLabel("Available Routes");
        heading.setFont(new Font("Arial", Font.BOLD, 30));
        heading.setForeground(new Color(30, 41, 59));

        content.add(heading, BorderLayout.NORTH);

        // ================= TABLE =================
        String[] columns = {
                "ID",
                "Route Name",
                "Start Point",
                "Destination",
                "Distance (KM)",
                "Estimated Time"
        };

        model = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);

        table.setFont(new Font("Arial", Font.PLAIN, 17));
        table.setRowHeight(45);
        table.setSelectionBackground(new Color(219, 234, 254));
        table.setSelectionForeground(Color.BLACK);
        table.setGridColor(new Color(180, 180, 180));

        // Table Header
        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        table.getTableHeader().setBackground(
                new Color(37, 99, 235)
        );

        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setPreferredSize(
                new Dimension(100, 45)
        );

        JScrollPane scrollPane = new JScrollPane(table);

        content.add(scrollPane, BorderLayout.CENTER);

        // ================= BOTTOM =================
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(245, 247, 250));

        JButton refreshButton = new JButton("Refresh Routes");

        refreshButton.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        refreshButton.setForeground(Color.WHITE);
        refreshButton.setBackground(
                new Color(16, 185, 129)
        );

        refreshButton.setFocusPainted(false);
        refreshButton.setBorderPainted(false);
        refreshButton.setPreferredSize(
                new Dimension(210, 55)
        );

        refreshButton.addActionListener(e -> loadRoutes());

        bottomPanel.add(refreshButton);

        content.add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(content, BorderLayout.CENTER);

        add(mainPanel);

        // Load data automatically
        loadRoutes();
    }

    // ================= LOAD ROUTES =================
    private void loadRoutes() {

        model.setRowCount(0);

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = DBConnection.getConnection();

            String sql =
                    "SELECT route_id, route_name, start_point, " +
                    "destination, distance_km, estimated_time " +
                    "FROM route";

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {

                Object[] row = {

                    rs.getInt("route_id"),

                    rs.getString("route_name"),

                    rs.getString("start_point"),

                    rs.getString("destination"),

                    rs.getDouble("distance_km"),

                    rs.getString("estimated_time")
                };

                model.addRow(row);
            }

        } catch (SQLException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error:\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

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

                RouteManagement routeManagement =
                        new RouteManagement();

                routeManagement.setVisible(true);
            }
        });
    }
}