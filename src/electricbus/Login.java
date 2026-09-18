package electricbus;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {

        setTitle("Electric Bus Management System");

        setSize(850, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

    

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(
                new Color(245, 250, 248)
        );

        
        JPanel leftPanel = new JPanel();

        leftPanel.setPreferredSize(
                new Dimension(350, 500)
        );

        leftPanel.setBackground(
                new Color(20, 105, 85)
        );

        leftPanel.setLayout(null);

       

        JLabel busIcon = new JLabel("⚡");

        busIcon.setBounds(
                135, 60, 100, 70
        );

        busIcon.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        55
                )
        );

        busIcon.setForeground(Color.WHITE);

        leftPanel.add(busIcon);

        // Electric Bus

        JLabel busTitle =
                new JLabel("ELECTRIC BUS");

        busTitle.setBounds(
                70, 140, 250, 40
        );

        busTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        busTitle.setForeground(Color.WHITE);

        leftPanel.add(busTitle);

        

        JLabel systemTitle =
                new JLabel("MANAGEMENT SYSTEM");

        systemTitle.setBounds(
                65, 180, 270, 30
        );

        systemTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        systemTitle.setForeground(
                new Color(190, 255, 225)
        );

        leftPanel.add(systemTitle);

       

        JLabel description =
                new JLabel(
                        "<html><center>"
                        + "Smart & Sustainable<br>"
                        + "Electric Transportation<br>"
                        + "Management"
                        + "</center></html>"
                );

        description.setBounds(
                60, 245, 280, 90
        );

        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        description.setForeground(Color.WHITE);

        leftPanel.add(description);

      

        JLabel bottomText =
                new JLabel(
                        "Clean • Smart • Green"
                );

        bottomText.setBounds(
                90, 410, 220, 30
        );

        bottomText.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        bottomText.setForeground(
                new Color(180, 255, 220)
        );

        leftPanel.add(bottomText);

        
        JPanel rightPanel = new JPanel();

        rightPanel.setBackground(Color.WHITE);

        rightPanel.setLayout(null);

        // Welcome

        JLabel loginTitle =
                new JLabel("Welcome Back!");

        loginTitle.setBounds(
                85, 55, 300, 40
        );

        loginTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        loginTitle.setForeground(
                new Color(20, 105, 85)
        );

        rightPanel.add(loginTitle);

      

        JLabel loginSubTitle =
                new JLabel("Admin Login");

        loginSubTitle.setBounds(
                88, 100, 200, 25
        );

        loginSubTitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        loginSubTitle.setForeground(
                Color.GRAY
        );

        rightPanel.add(loginSubTitle);

        

        JLabel usernameLabel =
                new JLabel("Username");

        usernameLabel.setBounds(
                85, 145, 150, 25
        );

        usernameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        usernameLabel.setForeground(
                new Color(50, 50, 50)
        );

        rightPanel.add(usernameLabel);

        usernameField =
                new JTextField();

        usernameField.setBounds(
                85, 172, 330, 38
        );

        usernameField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        rightPanel.add(usernameField);

        

        JLabel passwordLabel =
                new JLabel("Password");

        passwordLabel.setBounds(
                85, 225, 150, 25
        );

        passwordLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        passwordLabel.setForeground(
                new Color(50, 50, 50)
        );

        rightPanel.add(passwordLabel);

        passwordField =
                new JPasswordField();

        passwordField.setBounds(
                85, 252, 330, 38
        );

        passwordField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        rightPanel.add(passwordField);

       

        JButton loginButton =
                new JButton("LOGIN");

        loginButton.setBounds(
                85, 315, 155, 42
        );

        loginButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        loginButton.setForeground(
                Color.WHITE
        );

        loginButton.setBackground(
                new Color(20, 150, 110)
        );

        loginButton.setFocusPainted(false);

        rightPanel.add(loginButton);

        
        JButton clearButton =
                new JButton("CLEAR");

        clearButton.setBounds(
                260, 315, 155, 42
        );

        clearButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        clearButton.setForeground(
                Color.WHITE
        );

        clearButton.setBackground(
                new Color(90, 100, 105)
        );

        clearButton.setFocusPainted(false);

        rightPanel.add(clearButton);

        

        JLabel footer =
                new JLabel(
                        "© Electric Bus Management System"
                );

        footer.setBounds(
                130, 410, 250, 25
        );

        footer.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        footer.setForeground(
                Color.GRAY
        );

        rightPanel.add(footer);

        
        loginButton.addActionListener(
                new ActionListener() {

                    public void actionPerformed(
                            ActionEvent e) {

                        login();
                    }
                }
        );

        

        clearButton.addActionListener(
                new ActionListener() {

                    public void actionPerformed(
                            ActionEvent e) {

                        usernameField.setText("");

                        passwordField.setText("");

                        usernameField.requestFocus();
                    }
                }
        );

        

        passwordField.addActionListener(
                new ActionListener() {

                    public void actionPerformed(
                            ActionEvent e) {

                        login();
                    }
                }
        );


        mainPanel.add(
                leftPanel,
                BorderLayout.WEST
        );

        mainPanel.add(
                rightPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    
    private void login() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        
        if (username.length() == 0 ||
                password.length() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password!",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }



        String sql =
                "SELECT * FROM admin "
                + "WHERE username = ? "
                + "AND password = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

           

            con =
                    DBConnection.getConnection();

            if (con == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed!",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            
            ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    username
            );

            ps.setString(
                    2,
                    password
            );

            rs =
                    ps.executeQuery();

            

            if (rs.next()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login Successful!",
                        "Welcome",
                        JOptionPane.INFORMATION_MESSAGE
                );

                

                Dashboard dashboard =
                        new Dashboard();

                dashboard.setVisible(true);

                
                this.dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Username or Password!",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error:\n"
                    + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();

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

            
            try {

                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
            }
        }
    }

    

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    public void run() {

                        Login login =
                                new Login();

                        login.setVisible(true);
                    }
                }
        );
    }
}