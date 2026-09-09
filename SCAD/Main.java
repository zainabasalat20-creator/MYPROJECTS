import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

class Flight {
    int flightNumber;
    String from, to, departureTime, arrivalTime, day;

    public Flight(int flightNumber, String from, String to, String departureTime, String arrivalTime, String day) {
        this.flightNumber = flightNumber;
        this.from = from;
        this.to = to;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.day = day;
    }
}

class User {
    String name, email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

public class AirlineApp {
    private JFrame frame;
    private JPanel loginPanel, mainPanel;
    private JTextField nameField, emailField;
    private JButton loginButton, registerButton, viewFlightsButton, bookFlightButton, logoutButton;
    private JTextArea flightsArea;

    private HashMap<String, User> users = new HashMap<>();
    private ArrayList<Flight> flights = new ArrayList<>();
    private User loggedInUser = null;

    public AirlineApp() {
        frame = new JFrame("Airline Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new CardLayout());

        createLoginPanel();
        createMainPanel();
        addFlights();

        frame.add(loginPanel, "Login");
        frame.add(mainPanel, "Main");

        showLoginPanel();
        frame.setVisible(true);
    }

    private void createLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(Color.DARK_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameField = new JTextField(15);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailField = new JTextField(15);

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        setButtonStyle(loginButton);
        setButtonStyle(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(loginButton, gbc);
        gbc.gridx = 1;
        loginPanel.add(registerButton, gbc);

        loginButton.addActionListener(e -> handleLogin());
        registerButton.addActionListener(e -> handleRegister());
    }

    private void createMainPanel() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.DARK_GRAY);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.GRAY);
        JLabel title = new JLabel("Welcome to Airline Management");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(title);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.DARK_GRAY);
        viewFlightsButton = new JButton("View Flights");
        bookFlightButton = new JButton("Book Flight");
        logoutButton = new JButton("Logout");
        setButtonStyle(viewFlightsButton);
        setButtonStyle(bookFlightButton);
        setButtonStyle(logoutButton);
        buttonPanel.add(viewFlightsButton);
        buttonPanel.add(bookFlightButton);
        buttonPanel.add(logoutButton);

        flightsArea = new JTextArea(10, 30);
        flightsArea.setEditable(false);
        flightsArea.setBackground(Color.LIGHT_GRAY);
        flightsArea.setForeground(Color.BLACK);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(new JScrollPane(flightsArea), BorderLayout.SOUTH);

        viewFlightsButton.addActionListener(e -> showFlights());
        bookFlightButton.addActionListener(e -> bookFlight());
        logoutButton.addActionListener(e -> {
            loggedInUser = null;
            showLoginPanel();
        });
    }

    private void setButtonStyle(JButton button) {
        button.setPreferredSize(new Dimension(100, 25));
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);
    }

    private void handleLogin() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        if (users.containsKey(email) && users.get(email).name.equalsIgnoreCase(name)) {
            loggedInUser = users.get(email);
            JOptionPane.showMessageDialog(frame, "Login Successful!");
            showMainPanel();
        } else {
            JOptionPane.showMessageDialog(frame, "User not found. Please register first.");
        }
    }

    private void handleRegister() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        if (!users.containsKey(email)) {
            User user = new User(name, email);
            users.put(email, user);
            loggedInUser = user;
            JOptionPane.showMessageDialog(frame, "Registration Successful!");
            showMainPanel();
        } else {
            JOptionPane.showMessageDialog(frame, "User already exists. Please login.");
        }
    }

    private void showFlights() {
        flightsArea.setText("Available Flights:\n");
        for (Flight flight : flights) {
            flightsArea.append("Flight " + flight.flightNumber + ": " + flight.from + " to " + flight.to +
                    " | " + flight.departureTime + " - " + flight.arrivalTime + " (" + flight.day + ")\n");
        }
    }

    private void bookFlight() {
        if (loggedInUser == null) return;

        String input = JOptionPane.showInputDialog(frame, "Enter Flight Number to Book:");
        try {
            int num = Integer.parseInt(input);
            boolean found = false;
            for (Flight f : flights) {
                if (f.flightNumber == num) {
                    JOptionPane.showMessageDialog(frame, "Flight " + num + " booked successfully!");
                    found = true;
                    break;
                }
            }
            if (!found) JOptionPane.showMessageDialog(frame, "Flight not found!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Invalid input!");
        }
    }

    private void showLoginPanel() {
        CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
        cl.show(frame.getContentPane(), "Login");
        nameField.setText("");
        emailField.setText("");
    }

    private void showMainPanel() {
        CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
        cl.show(frame.getContentPane(), "Main");
    }

    private void addFlights() {
        flights.add(new Flight(101, "Pakistan", "USA", "10:00 AM", "8:00 PM", "Monday"));
        flights.add(new Flight(102, "Pakistan", "Canada", "11:00 AM", "9:00 PM", "Tuesday"));
        flights.add(new Flight(103, "Pakistan", "Dubai", "12:00 PM", "6:00 PM", "Wednesday"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AirlineApp::new);
    }
}
