import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ToDoListApp extends JFrame {
    private JComboBox<String> taskTypeComboBox;
    private JTextField dateTimeField;
    private JComboBox<Priority> priorityComboBox;
    private JTextField celebrantNameField;
    private JTextField locationField;
    private JTextField participantsField;
    private JTextField departureAirportField;
    private JTextField arrivalAirportField;
    private JTextField flightTimeField;

    // Labels corresponding to fields
    private JLabel celebrantNameLabel;
    private JLabel locationLabel;
    private JLabel participantsLabel;
    private JLabel departureAirportLabel;
    private JLabel arrivalAirportLabel;
    private JLabel flightTimeLabel;

    public ToDoListApp() {
        super("To-Do List App");

        taskTypeComboBox = new JComboBox<>(new String[]{"Birthday", "Business Meeting", "Flight"});
        taskTypeComboBox.setSelectedItem("Birthday");
        dateTimeField = new JTextField();
        priorityComboBox = new JComboBox<>(Priority.values());
        celebrantNameField = new JTextField();
        locationField = new JTextField();
        participantsField = new JTextField();
        departureAirportField = new JTextField();
        arrivalAirportField = new JTextField();
        flightTimeField = new JTextField();

        celebrantNameLabel = new JLabel("Celebrant Name:");
        locationLabel = new JLabel("Location:");
        participantsLabel = new JLabel("Participants:");
        departureAirportLabel = new JLabel("Departure Airport:");
        arrivalAirportLabel = new JLabel("Arrival Airport:");
        flightTimeLabel = new JLabel("Flight Time:");

        taskTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFieldsForSelectedTaskType();
            }
        });

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTask();
            }
        });

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Task Type:"));
        panel.add(taskTypeComboBox);
        panel.add(new JLabel("Date and Time (dd.MM.yyyy HH:mm):"));
        panel.add(dateTimeField);
        panel.add(new JLabel("Priority:"));
        panel.add(priorityComboBox);
        panel.add(celebrantNameLabel);
        panel.add(celebrantNameField);
        panel.add(locationLabel);
        panel.add(locationField);
        panel.add(participantsLabel);
        panel.add(participantsField);
        panel.add(departureAirportLabel);
        panel.add(departureAirportField);
        panel.add(arrivalAirportLabel);
        panel.add(arrivalAirportField);
        panel.add(flightTimeLabel);
        panel.add(flightTimeField);
        panel.add(saveButton);

        add(panel);

        showFieldsForSelectedTaskType();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showFieldsForSelectedTaskType() {
        String selectedTaskType = (String) taskTypeComboBox.getSelectedItem();

        // Hide all fields and labels initially
        hideAllFieldsAndLabels();

        // Show fields and labels based on the selected task type
        switch (selectedTaskType) {
            case "Birthday":
                celebrantNameLabel.setVisible(true);
                celebrantNameField.setVisible(true);
                break;
            case "Business Meeting":
                locationLabel.setVisible(true);
                locationField.setVisible(true);
                participantsLabel.setVisible(true);
                participantsField.setVisible(true);
                break;
            case "Flight":
                departureAirportLabel.setVisible(true);
                departureAirportField.setVisible(true);
                arrivalAirportLabel.setVisible(true);
                arrivalAirportField.setVisible(true);
                flightTimeLabel.setVisible(true);
                flightTimeField.setVisible(true);
                break;
        }
    }

    private void hideAllFieldsAndLabels() {
        celebrantNameLabel.setVisible(false);
        locationLabel.setVisible(false);
        participantsLabel.setVisible(false);
        departureAirportLabel.setVisible(false);
        arrivalAirportLabel.setVisible(false);
        flightTimeLabel.setVisible(false);

        celebrantNameField.setVisible(false);
        locationField.setVisible(false);
        participantsField.setVisible(false);
        departureAirportField.setVisible(false);
        arrivalAirportField.setVisible(false);
        flightTimeField.setVisible(false);
    }

    private void saveTask() {
        // Implement the logic to save the task
        String selectedTaskType = (String) taskTypeComboBox.getSelectedItem();

        // Check if all required fields are filled
        if (!areAllFieldsFilled(selectedTaskType)) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop execution if fields are not filled
        }

        // Validate date format
        Date taskDate;
        try {
            taskDate = parseDate(dateTimeField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please enter a valid date.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop execution if date format is invalid
        }

        // Create a task object based on the selected task type and save it
        try {
            switch (selectedTaskType) {
                case "Birthday":
                    BirthdayTask birthdayTask = new BirthdayTask(taskDate,
                            (Priority) priorityComboBox.getSelectedItem(), celebrantNameField.getText());
                    System.out.println(birthdayTask);
                    break;
                case "Business Meeting":
                    BusinessMeetingTask meetingTask = new BusinessMeetingTask(taskDate,
                            (Priority) priorityComboBox.getSelectedItem(), locationField.getText(),
                            participantsField.getText());
                    System.out.println(meetingTask);
                    break;
                case "Flight":
                    FlightTask flightTask = new FlightTask(taskDate,
                            (Priority) priorityComboBox.getSelectedItem(), departureAirportField.getText(),
                            arrivalAirportField.getText(), flightTimeField.getText());
                    System.out.println(flightTask);
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred while processing the task. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Helper method to check if all required fields are filled
    private boolean areAllFieldsFilled(String taskType) {
        switch (taskType) {
            case "Birthday":
                return !celebrantNameField.getText().trim().isEmpty();
            case "Business Meeting":
                return !locationField.getText().trim().isEmpty() && !participantsField.getText().trim().isEmpty();
            case "Flight":
                return !departureAirportField.getText().trim().isEmpty()
                        && !arrivalAirportField.getText().trim().isEmpty()
                        && !flightTimeField.getText().trim().isEmpty();
            default:
                return true; // Default to true for unknown task types
        }
    }

    // Helper method to parse and validate the date
    private Date parseDate(String dateStr) throws Exception {
        // Implement the logic to parse the date
        // You may use SimpleDateFormat or another date parsing method
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm"); // Adjust the format as needed
        dateFormat.setLenient(false); // Disable lenient parsing

        return dateFormat.parse(dateStr); // This will throw ParseException if the date is invalid
    }

}