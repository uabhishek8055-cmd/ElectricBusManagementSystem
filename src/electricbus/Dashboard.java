package electricbus;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Dashboard extends JFrame {

    private JPanel mainPanel;

    public Dashboard() {

        setTitle("Electric Bus Management System - Dashboard");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));

        // =========================================================
        // HEADER
        // =========================================================

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(31, 73, 125));
        header.setPreferredSize(new Dimension(1200, 90));

        JLabel title = new JLabel("ELECTRIC BUS MANAGEMENT SYSTEM");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(new EmptyBorder(0, 35, 0, 0));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(new Color(70, 130, 180));
        logoutButton.setFocusPainted(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setPreferredSize(new Dimension(130, 50));

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int choice = JOptionPane.showConfirmDialog(
                        Dashboard.this,
                        "Do you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    dispose();

                    try {
                        Login login = new Login();
                        login.setVisible(true);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        header.add(title, BorderLayout.WEST);
        header.add(logoutButton, BorderLayout.EAST);

        // =========================================================
        // WELCOME
        // =========================================================

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(new Color(245, 247, 250));
        welcomePanel.setBorder(new EmptyBorder(25, 35, 15, 35));

        JLabel welcome = new JLabel("Dashboard");
        welcome.setFont(new Font("Arial", Font.BOLD, 32));
        welcome.setForeground(new Color(31, 73, 125));

        JLabel subTitle = new JLabel(
                "Manage buses, drivers, routes, charging stations and trips"
        );
        subTitle.setFont(new Font("Arial", Font.PLAIN, 17));
        subTitle.setForeground(new Color(80, 80, 80));

        welcomePanel.add(welcome);
        welcomePanel.add(Box.createVerticalStrut(5));
        welcomePanel.add(subTitle);

        // =========================================================
        // BUTTON PANEL
        // =========================================================

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 25, 25));
        buttonPanel.setBackground(new Color(245, 247, 250));
        buttonPanel.setBorder(new EmptyBorder(15, 35, 30, 35));

        // ---------------------------------------------------------
        // BUS MANAGEMENT
        // ---------------------------------------------------------

        JButton busButton = createButton(
                "Bus Management",
                new Color(52, 152, 219)
        );

        busButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    BusManagement bus = new BusManagement();
                    bus.setVisible(true);
                    dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            Dashboard.this,
                            "Unable to open Bus Management.\n"
                            + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // ---------------------------------------------------------
        // DRIVER MANAGEMENT
        // ---------------------------------------------------------

        JButton driverButton = createButton(
                "Driver Management",
                new Color(155, 89, 182)
        );

        driverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    DriverManagement driver = new DriverManagement();
                    driver.setVisible(true);
                    dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            Dashboard.this,
                            "Unable to open Driver Management.\n"
                            + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // ---------------------------------------------------------
        // ROUTE MANAGEMENT
        // ---------------------------------------------------------

        JButton routeButton = createButton(
                "Route Management",
                new Color(142, 68, 173)
        );

        routeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {

                    RouteManagement route = new RouteManagement();

                    route.setVisible(true);

                    dispose();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            Dashboard.this,
                            "Unable to open Route Management.\n"
                            + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    ex.printStackTrace();
                }
            }
        });

        // ---------------------------------------------------------
        // CHARGING STATION
        // ---------------------------------------------------------

        JButton chargingButton = createButton(
                "Charging Station",
                new Color(26, 188, 156)
        );

        chargingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {

                    ChargingStation charging =
                            new ChargingStation();

                    charging.setVisible(true);

                    dispose();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            Dashboard.this,
                            "Unable to open Charging Station.\n"
                            + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    ex.printStackTrace();
                }
            }
        });

        // ---------------------------------------------------------
        // TRIP MANAGEMENT
        // ---------------------------------------------------------

        JButton tripButton = createButton(
                "Trip Management",
                new Color(39, 174, 96)
        );

        tripButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {

                    /*
                     * IMPORTANT:
                     * This is the connection for Trip Management.
                     */

                    TripManagement trip =
                            new TripManagement();

                    trip.setVisible(true);

                    /*
                     * Close Dashboard after opening
                     * Trip Management.
                     */
                    dispose();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            Dashboard.this,
                            "Unable to open Trip Management.\n"
                            + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    ex.printStackTrace();
                }
            }
        });

        // ---------------------------------------------------------
        // REFRESH / DATABASE
        // ---------------------------------------------------------

        JButton databaseButton = createButton(
                "Database Status",
                new Color(230, 126, 34)
        );

        databaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Connection con = null;

                try {

                    con = DBConnection.getConnection();

                    if (con != null && !con.isClosed()) {

                        JOptionPane.showMessageDialog(
                                Dashboard.this,
                                "Database Connected Successfully!",
                                "Database Status",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                Dashboard.this,
                                "Database Connection Failed!",
                                "Database Status",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            Dashboard.this,
                            "Database Error:\n" + ex.getMessage(),
                            "Database Status",
                            JOptionPane.ERROR_MESSAGE
                    );

                    ex.printStackTrace();

                } finally {

                    try {
                        if (con != null) {
                            con.close();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // =========================================================
        // ADD BUTTONS
        // =========================================================

        buttonPanel.add(busButton);
        buttonPanel.add(driverButton);
        buttonPanel.add(routeButton);
        buttonPanel.add(chargingButton);
        buttonPanel.add(tripButton);
        buttonPanel.add(databaseButton);

        // =========================================================
        // CENTER PANEL
        // =========================================================

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(245, 247, 250));

        centerPanel.add(welcomePanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);

        // =========================================================
        // FOOTER
        // =========================================================

        JPanel footer = new JPanel();
        footer.setBackground(new Color(31, 73, 125));
        footer.setPreferredSize(new Dimension(1200, 45));

        JLabel footerText = new JLabel(
                "Electric Bus Management System | Java + MySQL"
        );

        footerText.setForeground(Color.WHITE);
        footerText.setFont(new Font("Arial", Font.PLAIN, 14));

        footer.add(footerText);

        // =========================================================
        // ADD EVERYTHING
        // =========================================================

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // =============================================================
    // CREATE DASHBOARD BUTTON
    // =============================================================

    private JButton createButton(String text, Color color) {

        JButton button = new JButton(text);

        button.setFont(new Font("Arial", Font.BOLD, 21));
        button.setForeground(Color.WHITE);
        button.setBackground(color);

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        return button;
    }

    // =============================================================
    // MAIN
    // =============================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    public void run() {

                        Dashboard dashboard =
                                new Dashboard();

                        dashboard.setVisible(true);
                    }
                }
        );
    }
}