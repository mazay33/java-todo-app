import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    private JLabel celebrantNameLabel;
    private JLabel locationLabel;
    private JLabel participantsLabel;
    private JLabel departureAirportLabel;
    private JLabel arrivalAirportLabel;
    private JLabel flightTimeLabel;

    public ToDoListApp() {
        super("To-Do List App");

        initializeUI();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeUI() {
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

        taskTypeComboBox.addActionListener(e -> showFieldsForSelectedTaskType());

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveTask());

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
    }

    private void showFieldsForSelectedTaskType() {
        String selectedTaskType = (String) taskTypeComboBox.getSelectedItem();
        hideAllFieldsAndLabels();

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
        String selectedTaskType = (String) taskTypeComboBox.getSelectedItem();

        if (dateTimeField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter date and time.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!areAllFieldsFilled(selectedTaskType)) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date taskDate;
        try {
            taskDate = parseDate(dateTimeField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please enter a valid date.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Task task = createTask(selectedTaskType, taskDate, (Priority) priorityComboBox.getSelectedItem());
            System.out.println(task);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error processing the task: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Task createTask(String selectedTaskType, Date taskDate, Priority priority) throws Exception {
        switch (selectedTaskType) {
            case "Birthday":
                return new BirthdayTask(taskDate, priority, celebrantNameField.getText());
            case "Business Meeting":
                return new BusinessMeetingTask(taskDate, priority, locationField.getText(), participantsField.getText());
            case "Flight":
                return new FlightTask(taskDate, priority, departureAirportField.getText(),
                        arrivalAirportField.getText(), flightTimeField.getText());
            default:
                throw new IllegalArgumentException("Unknown task type: " + selectedTaskType);
        }
    }

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
                return true;
        }
    }

    private Date parseDate(String dateStr) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        dateFormat.setLenient(false);

            return dateFormat.parse(dateStr);
    }
}
