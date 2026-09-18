package electricbus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TripManagement extends JFrame {

    private JTextField txtTripId;
    private JTextField txtBusId;
    private JTextField txtDriverId;
    private JTextField txtRouteId;
    private JTextField txtDepartureDate;
    private JTextField txtDepartureTime;
    private JTextField txtArrivalDate;
    private JTextField txtArrivalTime;

    private JComboBox<String> cmbStatus;

    private JTable tripTable;
    private DefaultTableModel tableModel;

    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnRefresh;
    private JButton btnDashboard;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public TripManagement() {

        setTitle("Electric Bus Management System - Trip Management");

        setSize(1450, 850);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        createUI();

        loadTrips();
    }

    // =========================================================
    // CREATE UI
    // =========================================================

    private void createUI() {

        getContentPane().setLayout(new BorderLayout());

        // =====================================================
        // HEADER
        // =====================================================

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(new Color(31, 73, 125));

        header.setPreferredSize(new Dimension(1450, 100));

        JLabel title = new JLabel("TRIP MANAGEMENT");

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font("Arial", Font.BOLD, 32)
        );

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        0, 30, 0, 0
                )
        );

        btnDashboard = new JButton("← Dashboard");

        btnDashboard.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        btnDashboard.setForeground(Color.WHITE);

        btnDashboard.setBackground(
                new Color(52, 152, 219)
        );

        btnDashboard.setFocusPainted(false);

        btnDashboard.setBorderPainted(false);

        btnDashboard.setPreferredSize(
                new Dimension(200, 100)
        );

        header.add(title, BorderLayout.CENTER);

        header.add(btnDashboard, BorderLayout.EAST);

        // =====================================================
        // FORM
        // =====================================================

        JPanel formPanel = new JPanel(
                new GridBagLayout()
        );

        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 15, 8, 15);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        Font labelFont =
                new Font("Arial", Font.BOLD, 17);

        Font fieldFont =
                new Font("Arial", Font.PLAIN, 17);

        // =====================================================
        // TRIP ID
        // =====================================================

        JLabel lblTripId =
                new JLabel("Trip ID");

        lblTripId.setFont(labelFont);

        txtTripId =
                new JTextField();

        txtTripId.setFont(fieldFont);

        // =====================================================
        // BUS ID
        // =====================================================

        JLabel lblBusId =
                new JLabel("Bus ID");

        lblBusId.setFont(labelFont);

        txtBusId =
                new JTextField();

        txtBusId.setFont(fieldFont);

        // =====================================================
        // DRIVER ID
        // =====================================================

        JLabel lblDriverId =
                new JLabel("Driver ID");

        lblDriverId.setFont(labelFont);

        txtDriverId =
                new JTextField();

        txtDriverId.setFont(fieldFont);

        // =====================================================
        // ROUTE ID
        // =====================================================

        JLabel lblRouteId =
                new JLabel("Route ID");

        lblRouteId.setFont(labelFont);

        txtRouteId =
                new JTextField();

        txtRouteId.setFont(fieldFont);

        // =====================================================
        // DEPARTURE DATE
        // =====================================================

        JLabel lblDepartureDate =
                new JLabel("Departure Date");

        lblDepartureDate.setFont(labelFont);

        txtDepartureDate =
                new JTextField();

        txtDepartureDate.setFont(fieldFont);

        txtDepartureDate.setToolTipText(
                "yyyy-MM-dd"
        );

        // =====================================================
        // DEPARTURE TIME
        // =====================================================

        JLabel lblDepartureTime =
                new JLabel("Departure Time");

        lblDepartureTime.setFont(labelFont);

        txtDepartureTime =
                new JTextField();

        txtDepartureTime.setFont(fieldFont);

        txtDepartureTime.setToolTipText(
                "HH:mm"
        );

        // =====================================================
        // ARRIVAL DATE
        // =====================================================

        JLabel lblArrivalDate =
                new JLabel("Arrival Date");

        lblArrivalDate.setFont(labelFont);

        txtArrivalDate =
                new JTextField();

        txtArrivalDate.setFont(fieldFont);

        txtArrivalDate.setToolTipText(
                "yyyy-MM-dd"
        );

        // =====================================================
        // ARRIVAL TIME
        // =====================================================

        JLabel lblArrivalTime =
                new JLabel("Arrival Time");

        lblArrivalTime.setFont(labelFont);

        txtArrivalTime =
                new JTextField();

        txtArrivalTime.setFont(fieldFont);

        txtArrivalTime.setToolTipText(
                "HH:mm"
        );

        // =====================================================
        // STATUS
        // =====================================================

        JLabel lblStatus =
                new JLabel("Trip Status");

        lblStatus.setFont(labelFont);

        cmbStatus =
                new JComboBox<String>();

        cmbStatus.addItem("Scheduled");
        cmbStatus.addItem("Running");
        cmbStatus.addItem("Completed");
        cmbStatus.addItem("Cancelled");

        cmbStatus.setFont(fieldFont);

        // =====================================================
        // ROW 1
        // =====================================================

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;

        formPanel.add(
                lblTripId,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 0.3;

        formPanel.add(
                txtTripId,
                gbc
        );

        gbc.gridx = 2;
        gbc.weightx = 0.2;

        formPanel.add(
                lblBusId,
                gbc
        );

        gbc.gridx = 3;
        gbc.weightx = 0.3;

        formPanel.add(
                txtBusId,
                gbc
        );

        // =====================================================
        // ROW 2
        // =====================================================

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                lblDriverId,
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                txtDriverId,
                gbc
        );

        gbc.gridx = 2;

        formPanel.add(
                lblRouteId,
                gbc
        );

        gbc.gridx = 3;

        formPanel.add(
                txtRouteId,
                gbc
        );

        // =====================================================
        // ROW 3
        // =====================================================

        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(
                lblDepartureDate,
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                txtDepartureDate,
                gbc
        );

        gbc.gridx = 2;

        formPanel.add(
                lblDepartureTime,
                gbc
        );

        gbc.gridx = 3;

        formPanel.add(
                txtDepartureTime,
                gbc
        );

        // =====================================================
        // ROW 4
        // =====================================================

        gbc.gridx = 0;
        gbc.gridy = 3;

        formPanel.add(
                lblArrivalDate,
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                txtArrivalDate,
                gbc
        );

        gbc.gridx = 2;

        formPanel.add(
                lblArrivalTime,
                gbc
        );

        gbc.gridx = 3;

        formPanel.add(
                txtArrivalTime,
                gbc
        );

        // =====================================================
        // ROW 5
        // =====================================================

        gbc.gridx = 0;
        gbc.gridy = 4;

        formPanel.add(
                lblStatus,
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                cmbStatus,
                gbc
        );

        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        buttonPanel.setBackground(Color.WHITE);

        btnAdd =
                createButton(
                        "Add Trip",
                        new Color(39, 174, 96)
                );

        btnUpdate =
                createButton(
                        "Update Trip",
                        new Color(52, 152, 219)
                );

        btnDelete =
                createButton(
                        "Delete Trip",
                        new Color(231, 76, 60)
                );

        btnRefresh =
                createButton(
                        "Refresh",
                        new Color(230, 126, 34)
                );

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        formPanel.add(
                buttonPanel,
                gbc
        );

        // =====================================================
        // TABLE
        // =====================================================

        String[] columns = {

                "Trip ID",
                "Bus ID",
                "Driver ID",
                "Route ID",
                "Departure",
                "Arrival",
                "Status"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        tripTable =
                new JTable(tableModel);

        tripTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        tripTable.setRowHeight(35);

        tripTable.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        tripTable.getTableHeader().setBackground(
                new Color(31, 73, 125)
        );

        tripTable.getTableHeader().setForeground(
                Color.WHITE
        );

        tripTable.setSelectionBackground(
                new Color(180, 210, 240)
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        tripTable
                );

        // =====================================================
        // CENTER PANEL
        // =====================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout()
                );

        centerPanel.setBackground(
                new Color(245, 247, 250)
        );

        centerPanel.add(
                formPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // ADD TO FRAME
        // =====================================================

        add(
                header,
                BorderLayout.NORTH
        );

        add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // BUTTON EVENTS
        // =====================================================

        btnAdd.addActionListener(
                new ActionListener() {

                    public void actionPerformed(
                            ActionEvent e) {

                        addTrip();
                    }
                }
        );

        btnUpdate.addActionListener(
                new ActionListener() {

                    public void actionPerformed(
                            ActionEvent e) {

                        updateTrip();
                    }
                }
        );

        btnDelete.addActionListener(
                new ActionListener() {

                    public void actionPerformed(
                            ActionEvent e) {

                        deleteTrip();
                    }
                }
        );

        btnRefresh.addActionListener(
                new ActionListener() {

                    public void actionPerformed(
                            ActionEvent e) {

                        loadTrips();
                    }
                }
        );

        btnDashboard.addActionListener(
                new ActionListener() {

                    public void actionPerformed(
                            ActionEvent e) {

                        goToDashboard();
                    }
                }
        );

        // =====================================================
        // TABLE CLICK
        // =====================================================

        tripTable.addMouseListener(
                new MouseAdapter() {

                    public void mouseClicked(
                            MouseEvent e) {

                        int row =
                                tripTable.getSelectedRow();

                        if (row >= 0) {

                            fillFieldsFromTable(row);
                        }
                    }
                }
        );
    }

    // =========================================================
    // CREATE BUTTON
    // =========================================================

    private JButton createButton(
            String text,
            Color color) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                color
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setPreferredSize(
                new Dimension(
                        150,
                        48
                )
        );

        return button;
    }

    // =========================================================
    // ADD TRIP
    // =========================================================

    private void addTrip() {

        String busText =
                txtBusId.getText().trim();

        String driverText =
                txtDriverId.getText().trim();

        String routeText =
                txtRouteId.getText().trim();

        String departureDate =
                txtDepartureDate.getText().trim();

        String departureTime =
                txtDepartureTime.getText().trim();

        String arrivalDate =
                txtArrivalDate.getText().trim();

        String arrivalTime =
                txtArrivalTime.getText().trim();

        String status =
                cmbStatus.getSelectedItem().toString();

        // -----------------------------------------------------
        // VALIDATION
        // -----------------------------------------------------

        if (busText.length() == 0 ||
                driverText.length() == 0 ||
                routeText.length() == 0 ||
                departureDate.length() == 0 ||
                departureTime.length() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill Bus ID, Driver ID, Route ID, Departure Date and Departure Time.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int busId;
        int driverId;
        int routeId;

        try {

            busId =
                    Integer.parseInt(busText);

            driverId =
                    Integer.parseInt(driverText);

            routeId =
                    Integer.parseInt(routeText);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Bus ID, Driver ID and Route ID must be numbers.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Timestamp departureTimestamp;
        Timestamp arrivalTimestamp = null;

        try {

            departureTimestamp =
                    convertToTimestamp(
                            departureDate,
                            departureTime
                    );

            if (arrivalDate.length() > 0 &&
                    arrivalTime.length() > 0) {

                arrivalTimestamp =
                        convertToTimestamp(
                                arrivalDate,
                                arrivalTime
                        );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid date/time.\n\n"
                    + "Date: yyyy-MM-dd\n"
                    + "Time: HH:mm\n\n"
                    + "Example:\n"
                    + "2026-09-16\n"
                    + "10:30",
                    "Date/Time Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // -----------------------------------------------------
        // DATABASE
        // -----------------------------------------------------

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con =
                    DBConnection.getConnection();

            if (con == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Database Connection Failed!",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            String sql =
                    "INSERT INTO trip "
                    + "(bus_id, driver_id, route_id, "
                    + "departure_time, arrival_time, trip_status) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            ps =
                    con.prepareStatement(sql);

            ps.setInt(1, busId);

            ps.setInt(2, driverId);

            ps.setInt(3, routeId);

            ps.setTimestamp(
                    4,
                    departureTimestamp
            );

            if (arrivalTimestamp != null) {

                ps.setTimestamp(
                        5,
                        arrivalTimestamp
                );

            } else {

                ps.setNull(
                        5,
                        Types.TIMESTAMP
                );
            }

            ps.setString(
                    6,
                    status
            );

            int result =
                    ps.executeUpdate();

            if (result > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Trip Added Successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

                loadTrips();
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error:\n"
                    + ex.getMessage(),
                    "SQL Error",
                    JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();

        } finally {

            try {

                if (ps != null) {
                    ps.close();
                }

            } catch (Exception e) {
            }
        }
    }

    // =========================================================
    // UPDATE TRIP
    // =========================================================

    private void updateTrip() {

        String tripText =
                txtTripId.getText().trim();

        if (tripText.length() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a trip from the table first.",
                    "Update Trip",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int tripId;

        try {

            tripId =
                    Integer.parseInt(tripText);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Trip ID.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        int busId;
        int driverId;
        int routeId;

        try {

            busId =
                    Integer.parseInt(
                            txtBusId.getText().trim()
                    );

            driverId =
                    Integer.parseInt(
                            txtDriverId.getText().trim()
                    );

            routeId =
                    Integer.parseInt(
                            txtRouteId.getText().trim()
                    );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Bus ID, Driver ID and Route ID must be numbers.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Timestamp departureTimestamp;
        Timestamp arrivalTimestamp = null;

        try {

            departureTimestamp =
                    convertToTimestamp(
                            txtDepartureDate.getText().trim(),
                            txtDepartureTime.getText().trim()
                    );

            String arrivalDate =
                    txtArrivalDate.getText().trim();

            String arrivalTime =
                    txtArrivalTime.getText().trim();

            if (arrivalDate.length() > 0 &&
                    arrivalTime.length() > 0) {

                arrivalTimestamp =
                        convertToTimestamp(
                                arrivalDate,
                                arrivalTime
                        );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid date/time format.\nUse yyyy-MM-dd and HH:mm.",
                    "Date/Time Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        String status =
                cmbStatus.getSelectedItem().toString();

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE trip SET "
                    + "bus_id=?, "
                    + "driver_id=?, "
                    + "route_id=?, "
                    + "departure_time=?, "
                    + "arrival_time=?, "
                    + "trip_status=? "
                    + "WHERE trip_id=?";

            ps =
                    con.prepareStatement(sql);

            ps.setInt(1, busId);

            ps.setInt(2, driverId);

            ps.setInt(3, routeId);

            ps.setTimestamp(
                    4,
                    departureTimestamp
            );

            if (arrivalTimestamp != null) {

                ps.setTimestamp(
                        5,
                        arrivalTimestamp
                );

            } else {

                ps.setNull(
                        5,
                        Types.TIMESTAMP
                );
            }

            ps.setString(
                    6,
                    status
            );

            ps.setInt(
                    7,
                    tripId
            );

            int result =
                    ps.executeUpdate();

            if (result > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Trip Updated Successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

                loadTrips();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Trip ID not found.",
                        "Update Error",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error:\n"
                    + ex.getMessage(),
                    "SQL Error",
                    JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();

        } finally {

            try {

                if (ps != null) {
                    ps.close();
                }

            } catch (Exception e) {
            }
        }
    }

    // =========================================================
    // DELETE TRIP
    // =========================================================

    private void deleteTrip() {

        String tripText =
                txtTripId.getText().trim();

        if (tripText.length() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a trip from the table first.",
                    "Delete Trip",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int tripId;

        try {

            tripId =
                    Integer.parseInt(tripText);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Trip ID.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete Trip ID "
                        + tripId
                        + "?",
                        "Delete Trip",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con =
                    DBConnection.getConnection();

            String sql =
                    "DELETE FROM trip "
                    + "WHERE trip_id=?";

            ps =
                    con.prepareStatement(sql);

            ps.setInt(
                    1,
                    tripId
            );

            int result =
                    ps.executeUpdate();

            if (result > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Trip Deleted Successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

                loadTrips();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Trip ID not found.",
                        "Delete Error",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error:\n"
                    + ex.getMessage(),
                    "SQL Error",
                    JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();

        } finally {

            try {

                if (ps != null) {
                    ps.close();
                }

            } catch (Exception e) {
            }
        }
    }

    // =========================================================
    // LOAD TRIPS
    // =========================================================

    private void loadTrips() {

        tableModel.setRowCount(0);

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con =
                    DBConnection.getConnection();

            if (con == null) {

                return;
            }

            String sql =
                    "SELECT trip_id, bus_id, driver_id, "
                    + "route_id, departure_time, "
                    + "arrival_time, trip_status "
                    + "FROM trip "
                    + "ORDER BY trip_id";

            ps =
                    con.prepareStatement(sql);

            rs =
                    ps.executeQuery();

            SimpleDateFormat format =
                    new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm"
                    );

            while (rs.next()) {

                int tripId =
                        rs.getInt("trip_id");

                int busId =
                        rs.getInt("bus_id");

                int driverId =
                        rs.getInt("driver_id");

                int routeId =
                        rs.getInt("route_id");

                Timestamp departure =
                        rs.getTimestamp(
                                "departure_time"
                        );

                Timestamp arrival =
                        rs.getTimestamp(
                                "arrival_time"
                        );

                String status =
                        rs.getString(
                                "trip_status"
                        );

                String departureText = "";

                String arrivalText = "";

                if (departure != null) {

                    departureText =
                            format.format(departure);
                }

                if (arrival != null) {

                    arrivalText =
                            format.format(arrival);
                }

                tableModel.addRow(
                        new Object[] {

                                tripId,
                                busId,
                                driverId,
                                routeId,
                                departureText,
                                arrivalText,
                                status
                        }
                );
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load trips.\n"
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();

        } finally {

            try {

                if (rs != null) {
                    rs.close();
                }

            } catch (Exception e) {
            }

            try {

                if (ps != null) {
                    ps.close();
                }

            } catch (Exception e) {
            }
        }
    }

    // =========================================================
    // TABLE ROW → FIELDS
    // =========================================================

    private void fillFieldsFromTable(int row) {

        txtTripId.setText(
                tableModel.getValueAt(
                        row, 0
                ).toString()
        );

        txtBusId.setText(
                tableModel.getValueAt(
                        row, 1
                ).toString()
        );

        txtDriverId.setText(
                tableModel.getValueAt(
                        row, 2
                ).toString()
        );

        txtRouteId.setText(
                tableModel.getValueAt(
                        row, 3
                ).toString()
        );

        String departure =
                tableModel.getValueAt(
                        row, 4
                ).toString();

        String arrival =
                tableModel.getValueAt(
                        row, 5
                ).toString();

        if (departure.length() > 0) {

            String[] parts =
                    departure.split(" ");

            txtDepartureDate.setText(
                    parts[0]
            );

            if (parts.length > 1) {

                txtDepartureTime.setText(
                        parts[1]
                );
            }
        }

        if (arrival.length() > 0) {

            String[] parts =
                    arrival.split(" ");

            txtArrivalDate.setText(
                    parts[0]
            );

            if (parts.length > 1) {

                txtArrivalTime.setText(
                        parts[1]
                );
            }

        } else {

            txtArrivalDate.setText("");
            txtArrivalTime.setText("");
        }

        String status =
                tableModel.getValueAt(
                        row, 6
                ).toString();

        cmbStatus.setSelectedItem(
                status
        );
    }

    // =========================================================
    // DATE TIME CONVERTER
    // =========================================================

    private Timestamp convertToTimestamp(
            String date,
            String time) throws Exception {

        SimpleDateFormat format =
                new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm"
                );

        format.setLenient(false);

        Date parsed =
                format.parse(
                        date + " " + time
                );

        return new Timestamp(
                parsed.getTime()
        );
    }

    // =========================================================
    // CLEAR FIELDS
    // =========================================================

    private void clearFields() {

        txtTripId.setText("");

        txtBusId.setText("");

        txtDriverId.setText("");

        txtRouteId.setText("");

        txtDepartureDate.setText("");

        txtDepartureTime.setText("");

        txtArrivalDate.setText("");

        txtArrivalTime.setText("");

        cmbStatus.setSelectedItem(
                "Scheduled"
        );

        tripTable.clearSelection();
    }

    // =========================================================
    // DASHBOARD
    // =========================================================

    private void goToDashboard() {

        dispose();

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

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    public void run() {

                        TripManagement tm =
                                new TripManagement();

                        tm.setVisible(true);
                    }
                }
        );
    }
}