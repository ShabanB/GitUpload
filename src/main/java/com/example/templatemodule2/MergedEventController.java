package com.example.templatemodule2;

import com.example.templatemodule2.essential.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.File;

public class MergedEventController {

    // Admin Panel Elements
    @FXML private VBox adminPanel;
    @FXML private ListView<String> eventListView;
    @FXML private Button addEventButton, editEventButton, deleteEventButton, manageRegistrationsButton;

    // User Panel Elements
    @FXML private VBox userPanel;
    @FXML private ListView<String> registeredEventListView;
    @FXML private Button viewEventDetailsButton, registerEventButton, viewRegisteredEventsButton;

    // Shared Event List
    public static ArrayList<Event> eventList = new ArrayList<>();
    private ArrayList<String> registeredEvents = new ArrayList<>();

    @FXML
    public void initialize() {
        updateEventListView();
    }

    @FXML
    public Label label;

    private void updateEventListView() {
        eventListView.getItems().clear();


        //Collections.sort(eventList, Comparator.comparing(event -> LocalDate.parse(event.getDate())));

        for (Event event : eventList) {
            String capacityDisplay = "Capacity: " + event.getRegisteredCount() + " / " + event.getCapacity();
            String eventDetails = event.getName() + " | Date: " + event.getDate() + " | " + capacityDisplay;
            eventListView.getItems().add(eventDetails);
        }

        eventListView.setCellFactory(lv -> createStyledListCell());
    }

    private ListCell<String> createStyledListCell() {
        return new ListCell<>() {
            @Override
            protected void updateItem(String eventDetails, boolean empty) {
                super.updateItem(eventDetails, empty);
                if (empty || eventDetails == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String[] parts = eventDetails.split("\\|");
                    String name = parts[0].trim();
                    String date = parts[1].trim();
                    String capacity = parts[2].trim();

                    Text nameText = new Text(name);
                    nameText.setFill(Color.BLUE);
                    nameText.setStyle("-fx-font-weight: bold;");

                    Text dateText = new Text(" | " + date);
                    dateText.setFill(Color.RED);

                    Text capacityText = new Text(" | " + capacity);
                    capacityText.setFill(Color.GREEN);

                    // Combine in an HBox
                    HBox hbox = new HBox(nameText, dateText, capacityText);
                    hbox.setSpacing(5);

                    setGraphic(hbox);
                }
            }
        };
    }


    @FXML
    protected void onAddEvent() {
        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("Add Event");
        dialog.setHeaderText("Enter Event Details");

        TextField nameField = new TextField();
        TextField descriptionField = new TextField();
        TextField locationField = new TextField();
        DatePicker datePicker = new DatePicker();
        ComboBox<String> timePicker = createTimePicker();
        TextField capacityField = new TextField();
        TextField costField = new TextField();

        Label imageLabel = new Label("No Image Selected");
        Button selectImageButton = new Button("Select Image");

        selectImageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Event Image");
            File selectedFile = fileChooser.showOpenDialog(dialog.getOwner());
            if (selectedFile != null) {
                imageLabel.setText(selectedFile.getAbsolutePath());
            }
        });

        VBox content = new VBox(10);
        content.getChildren().addAll(
                new Label("Event Name:"), nameField,
                new Label("Description:"), descriptionField,
                new Label("Location:"), locationField,
                new Label("Date:"), datePicker,
                new Label("Time:"), timePicker,
                new Label("Capacity:"), capacityField,
                new Label("Cost:"), costField,
                selectImageButton, imageLabel
        );

        dialog.getDialogPane().setContent(content);

        ButtonType addButton = new ButtonType("Add Event", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == addButton) {
                LocalDate selectedDate = datePicker.getValue();
                String selectedTime = timePicker.getValue();

                return new String[]{
                        nameField.getText(), descriptionField.getText(),
                        imageLabel.getText().equals("No Image Selected") ? "default.jpg" : imageLabel.getText(),
                        locationField.getText(),
                        selectedDate != null ? selectedDate.toString() + " " + selectedTime : "",
                        capacityField.getText(), costField.getText()
                };
            }
            return null;
        });

        Optional<String[]> result = dialog.showAndWait();
        result.ifPresent(data -> {
            try {
                int capacity = Integer.parseInt(data[5].trim());
                Event newEvent = new Event(data[0], data[1], data[2], data[3],
                        data[4], capacity, data[6]);

                eventList.add(newEvent);
                updateEventListView();
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid capacity format! Must be a number.");
            }
        });
    }



    @FXML
    protected void onEditEvent() {
        int selectedIndex = eventListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            showAlert("Error", "Select an event to edit!");
            return;
        }

        Event selectedEvent = eventList.get(selectedIndex);

        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("Edit Event");
        dialog.setHeaderText("Modify Event Details");

        TextField nameField = new TextField(selectedEvent.getName());
        TextField descriptionField = new TextField(selectedEvent.getDescription());
        TextField locationField = new TextField(selectedEvent.getLocation());


        /*String[] dateTimeParts = selectedEvent.getDate().split(" ");
        DatePicker datePicker = new DatePicker(LocalDate.parse(dateTimeParts[0]));
        ComboBox<String> timePicker = createTimePicker();
        if (dateTimeParts.length > 1) {
            timePicker.setValue(dateTimeParts[1]);
        }*/

        TextField capacityField = new TextField(String.valueOf(selectedEvent.getCapacity()));
        TextField costField = new TextField(selectedEvent.getCost());

        Label imageLabel = new Label(selectedEvent.getHeaderImage());
        ImageView previewImage = new ImageView();
        previewImage.setFitWidth(150);
        previewImage.setPreserveRatio(true);

        File currentImageFile = new File(selectedEvent.getHeaderImage());
        if (currentImageFile.exists()) {
            previewImage.setImage(new Image(currentImageFile.toURI().toString()));
        }

        Button selectImageButton = new Button("Change Image");
        selectImageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Event Image");
            File selectedFile = fileChooser.showOpenDialog(dialog.getOwner());
            if (selectedFile != null) {
                imageLabel.setText(selectedFile.getAbsolutePath());
                previewImage.setImage(new Image(selectedFile.toURI().toString()));
            }
        });

        VBox content = new VBox(10);
        content.getChildren().addAll(
                new Label("Event Name:"), nameField,
                new Label("Description:"), descriptionField,
                new Label("Location:"), locationField,
                //new Label("Date:"), datePicker,
                //new Label("Time:"), timePicker,
                new Label("Capacity:"), capacityField,
                new Label("Cost:"), costField,
                selectImageButton, imageLabel, previewImage
        );

        dialog.getDialogPane().setContent(content);

        ButtonType updateButton = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButton, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == updateButton) {
                return new String[]{
                        nameField.getText(), descriptionField.getText(),
                        imageLabel.getText(), locationField.getText(),
                       // datePicker.getValue() + " " + timePicker.getValue(),
                        capacityField.getText(), costField.getText()
                };
            }
            return null;
        });

        Optional<String[]> result = dialog.showAndWait();
        result.ifPresent(data -> {
            try {
                selectedEvent.setName(data[0]);
                selectedEvent.setDescription(data[1]);
                selectedEvent.setHeaderImage(data[2]);
                selectedEvent.setLocation(data[3]);
                selectedEvent.setDate(data[4]);
                selectedEvent.setCapacity(Integer.parseInt(data[5]));
                selectedEvent.setCost(data[6]);

                updateEventListView(); // Refresh UI
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid capacity format! Must be a number.");
            }
        });
    }



    @FXML
    protected void onDeleteEvent() {
        int selectedIndex = eventListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            showAlert("Error", "Select an event to delete!");
            return;
        }

        eventList.remove(selectedIndex);
        updateEventListView();
    }



    @FXML
    protected void onManageRegistrations() {
        int selectedIndex = eventListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            showAlert("Error", "Select an event to manage registrations!");
            return;
        }

        Event selectedEvent = eventList.get(selectedIndex);

        // Create a pop-up window
        Stage registrationStage = new Stage();
        registrationStage.setTitle("Registered Students for " + selectedEvent.getName());

        // Layout
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 10; -fx-alignment: center;");

        Label titleLabel = new Label("Registered Students for " + selectedEvent.getName());
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        ListView<String> studentListView = new ListView<>();


        ArrayList<String> studentNames = selectedEvent.getRegisteredStudentNames();
        ArrayList<String> studentEmails = selectedEvent.getRegisteredStudentEmails();

        if (studentNames.isEmpty()) {
            studentListView.getItems().add("No students registered yet.");
        } else {
            for (int i = 0; i < main.students.length; i++) {
                studentListView.getItems().add(main.students[i].getName() + " - " + main.students[i].getEmail());
            }
        }

        layout.getChildren().addAll(titleLabel, studentListView);

        // Create scene
        Scene scene = new Scene(layout, 350, 400);
        registrationStage.setScene(scene);

        // Show window
        registrationStage.show();
    }



    private ComboBox<String> createTimePicker() {
        ComboBox<String> timePicker = new ComboBox<>();
        String[] times = {
                "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM",
                "12:00 PM", "01:00 PM", "02:00 PM", "03:00 PM",
                "04:00 PM", "05:00 PM", "06:00 PM", "07:00 PM"
        };
        timePicker.getItems().addAll(times);
        timePicker.setValue("10:00 AM");
        return timePicker;
    }



    // ==================================== USER FUNCTIONALITY ==================================== //

    @FXML
    protected void onViewEvents() {
        int selectedIndex = eventListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            showAlert("Error", "Select an event to view!");
            return;
        }

        Event selectedEvent = eventList.get(selectedIndex);

        // Create new pop-up window
        Stage eventStage = new Stage();
        eventStage.setTitle("Event Details");

        // Create VBox to arrange UI elements
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 10; -fx-alignment: center;");

        // Load and display the updated header image
        ImageView eventImage = new ImageView();
        eventImage.setFitWidth(250);
        eventImage.setPreserveRatio(true);

        File imageFile = new File(selectedEvent.getHeaderImage());
        if (imageFile.exists()) {
            try {
                Image image = new Image(imageFile.toURI().toString());
                eventImage.setImage(image);
            } catch (Exception e) {
                System.out.println("Error loading image: " + e.getMessage());
            }
        }

        // Event details as labels
        Label nameLabel = new Label("Event Name: " + selectedEvent.getName());
        Label codeLabel = new Label("Event Code: " + selectedEvent.getEventCode());
        Label descriptionLabel = new Label("Description: " + selectedEvent.getDescription());
        Label locationLabel = new Label("Location: " + selectedEvent.getLocation());
        Label dateTimeLabel = new Label("Date & Time: " + selectedEvent.getDate());
        Label capacityLabel = new Label("Capacity: " + selectedEvent.getCapacity());
        Label costLabel = new Label("Cost: " + selectedEvent.getCost());

        // Add image first, then details
        layout.getChildren().add(eventImage);
        layout.getChildren().addAll(
                nameLabel, codeLabel, descriptionLabel, locationLabel,
                dateTimeLabel, capacityLabel, costLabel
        );

        // Create scene
        Scene scene = new Scene(layout, 350, 500);
        eventStage.setScene(scene);
        eventStage.show();
    }



    @FXML
    protected void onRegisterEvent() {
        String selectedEvent = eventListView.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            showAlert("Error", "Select an event to register!");
            return;
        }

        // Create a dialog for user input
        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("Register for Event");
        dialog.setHeaderText("Enter Your Details");

        // Input fields for name and email
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");

        VBox content = new VBox(10);
        content.getChildren().addAll(
                new Label("Name:"), nameField,
                new Label("Email:"), emailField
        );
        dialog.getDialogPane().setContent(content);

        // Set buttons
        ButtonType registerButton = new ButtonType("Register", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(registerButton, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == registerButton) {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();

                if (name.isEmpty() || email.isEmpty()) {
                    showAlert("Error", "Both name and email are required!");
                    return null;
                }

                if (!isValidEmail(email)) {
                    showAlert("Error", "Invalid email format! Please enter a valid email.");
                    return null;
                }

                return new String[]{name, email};
            }
            return null;
        });

        Optional<String[]> result = dialog.showAndWait();
        result.ifPresent(userData -> {
            String studentName = userData[0];
            String studentEmail = userData[1];

            for (Event event : eventList) { // Search for the selected event
                if (event.getName().equalsIgnoreCase(selectedEvent.split(" ")[0])) {
                    if (event.getRegisteredCount() >= event.getCapacity()) {
                        showAlert("Error", "Event is full! No more registrations allowed.");
                        return;
                    }

                    // Add student name and email separately
                    event.addStudent(studentName, studentEmail);

                    showAlert("Success", "You have registered for: " + event.getName());
                    registeredEvents.add(event.getName()); // Add to registered list
                    updateEventListView(); // Refresh UI
                    return;
                }
            }
            showAlert("Error", "Event not found!");
        });
    }



    // Checks if the email format is valid using a regular expression.
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }



    @FXML
    protected void onViewRegisteredEvents() {
        registeredEventListView.getItems().clear();
        registeredEventListView.getItems().addAll(registeredEvents);
    }



    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void onBackButton() throws IOException {
        Stage currentStage = (Stage) label.getScene().getWindow();
        main.switchScene(currentStage, "facuiltyManagment");
    }
}
