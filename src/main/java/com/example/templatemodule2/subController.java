package com.example.templatemodule2;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;




public class subController implements Initializable {
    boolean firstEntry = false;
    @FXML
    public Label userTypeLabel;
    @FXML
    public TextField searchTextField;

    private boolean subjectsInitialized = false;

    @FXML
    private MenuItem adminMenuItem;

    @FXML private Button addSubjectButton;

    @FXML private Button editSubjectButton;

    @FXML private Button deleteSubjectButton;

    @FXML
    public Label labelOne;
    @FXML
    public Label labelTwo;
    @FXML
    public Label labelThree;
    @FXML
    public Label labelFour;
    @FXML
    public Label labelFive;
    @FXML
    public Label labelSix;
    @FXML
    public Label labelSeven;
    @FXML
    public Label labelEight;
    @FXML
    public Label labelNine;
    @FXML
    public Label labelTen;
    @FXML
    public Label labelEleven;
    @FXML
    public Label labelTweleve;
    @FXML
    public Label labelThirteen;
    @FXML
    public Label labelFourteen;


    @FXML
    public Label[] infoList = new Label[14];

    public subController() {

    }

    @FXML
    private TextField subjectCodeField;  // For entering subject code

    @FXML
    private TextField subjectNameField;  // For entering subject name

    @FXML
    private Label statusLabel;  // For showing success/error messages

    private int subjectCount = 0;  // Track number of subjects

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        infoList[0] = labelOne;
        infoList[1] = labelTwo;
        infoList[2] = labelThree;
        infoList[3] = labelFour;
        infoList[4] = labelFive;
        infoList[5] = labelSix;
        infoList[6] = labelSeven;
        infoList[7] = labelEight;
        infoList[8] = labelNine;
        infoList[9] = labelTen;
        infoList[10] = labelEleven;
        infoList[11] = labelTweleve;
        infoList[12] = labelThirteen;
        infoList[13] = labelFourteen;

        subjectCount = 0;

        for (int i = 0; i < 14; i++)
        {
            infoList[i].setText(i + "");
        }

        // Hide admin buttons by default
        addSubjectButton.setVisible(false);
        editSubjectButton.setVisible(false);
        deleteSubjectButton.setVisible(false);
        subjectCodeField.setVisible(false);
        subjectNameField.setVisible(false);


        // When mouse clicks CodeField, and it says "enter subject code" it clears the text
    subjectCodeField.setText("Enter Subject Code");
    subjectCodeField.setOnMouseClicked(event -> {
        if (subjectCodeField.getText().equals("Enter Subject Code")) {
            subjectCodeField.clear();
        }
    });

    // When mouse clicks NameField, and it says "enter subject name" it clears the text
    subjectNameField.setText("Enter Subject Name");
    subjectNameField.setOnMouseClicked(event -> {
        if (subjectNameField.getText().equals("Enter Subject Name")) {
            subjectNameField.clear();
        }
    });

        if (main.userType == 's') {
            adminMenuItem.setVisible(false); // Hides Admin option for students
        }

    }


    @FXML
    protected void onSubjectManagmentButton() throws IOException {
        Stage currentStage = (Stage) userTypeLabel.getScene().getWindow();
        main.switchScene(currentStage, "subjectManagment");
    }
    @FXML
    protected void onCourseManagmentButton() throws IOException {
        Stage currentStage = (Stage) userTypeLabel.getScene().getWindow();
        main.switchScene(currentStage, "courseManagment");
    }
    @FXML
    protected void onStudentManagmentButton() throws IOException {
        Stage currentStage = (Stage) userTypeLabel.getScene().getWindow();
        main.switchScene(currentStage, "studentManagment");
    }
    @FXML
    protected void onFacuiltyManagmentButton() throws IOException {
        Stage currentStage = (Stage) userTypeLabel.getScene().getWindow();
        main.switchScene(currentStage, "facuiltyManagment");
    }
    @FXML
    protected void onEventManagmentButton() throws IOException {
        Stage currentStage = (Stage) userTypeLabel.getScene().getWindow();
        main.switchScene(currentStage, "eventManagment");
    }

    @FXML
    protected void onFeatureButtonOne()
    {
        if(!subjectsInitialized) {
            labelOne.setText("MATH001-Mathematics");
            labelTwo.setText("ENG101-English");
            labelThree.setText("ENGG1420-Programming");
            labelFour.setText("CS201-Computer Science");
            labelFive.setText("CHEM200-Chemistry");
            labelSix.setText("BIO300-Biology");
            labelSeven.setText("ENGG402-Engineering");
            labelEight.setText("HIST101-History");
            labelNine.setText("MUSIC102-Music");
            labelTen.setText("PSYCHO100-Psychology");
            labelEleven.setText("PHYS1010-Physics");
            labelTweleve.setText("ENGG1210-Mechanics");
            labelThirteen.setText("MATH1210-Calculus Two");
            labelFourteen.setText("");

            subjectsInitialized = true;
        }


        resetSubjectList();
    }

    @FXML
protected void onAdminSelected() {
    addSubjectButton.setVisible(true);
    editSubjectButton.setVisible(true);
    deleteSubjectButton.setVisible(true);
    subjectCodeField.setVisible(true);
    subjectNameField.setVisible(true);
    // Subject List stays visible
        onFeatureButtonOne();
}


    //code to reset subject list when infolist has nothing in it
    private void resetSubjectList() {
        for (Label label : infoList) {
            if (label != null) {
                label.setVisible(true);  // Show all subjects again
            }
        }
    }

    @FXML
    protected void onSearchButton()
    {
        //prevents case sensitivity and extra spaces issues
        String searchText = searchTextField.getText().trim().toLowerCase();
        //search bar empty show all courses
        if (searchText.isEmpty()) {
            resetSubjectList();
            return;
        }
        //for loop for looping each label inside infolist
            for (Label label : infoList) {
                if (label != null && label.getText().toLowerCase().contains(searchText)) {
                    label.setVisible(true);  // Show matching subjects
                } else {
                    label.setVisible(false); // Hide non-matching subjects
                }
            }

    }

    //admin features
    @FXML protected void onAddSubject() {
        String newCode = subjectCodeField.getText().trim().toUpperCase();
        String newName = subjectNameField.getText().trim();
        if (newCode.isEmpty() || newName.isEmpty()) {
            statusLabel.setText("Please enter both Subject Code and Name");
            return;
    }

        for (Label label : infoList) {
            if (label.getText().contains(newCode)) {
                statusLabel.setText("Subject Code already exists");
                return;
            }
        }

        // Find an empty label slot to add a new subject
        for (int i = 0; i < infoList.length; i++) {
            if (infoList[i] != null && infoList[i].getText().isEmpty()) { // Look for an empty spot
                infoList[i].setText(newCode + "-" + newName);
                infoList[i].setVisible(true);
                statusLabel.setText("Subject Added Successfully");
                return;
            }
        }

        // Fallback text
        statusLabel.setText("No more space for new subjects");
}
    //Edit Subject
    @FXML protected void onEditSubject() {
        String searchText = searchTextField.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            statusLabel.setText("Enter a Subject Code to Edit");
            return;
        }

        for (Label label : infoList) {
            if (label != null && label.getText().toLowerCase().contains(searchText)) {
                label.setText(subjectCodeField.getText().trim().toUpperCase() + "-" + subjectNameField.getText().trim());
                statusLabel.setText("Subject Edited Successfully");
                resetSubjectList(); //reset list after edit
                return;

            }
        }
        //fallback text
        statusLabel.setText("Subject Not Found");
    }

    //Delete Subject
    @FXML protected void onDeleteSubject() {
        String searchCode = subjectCodeField.getText().trim().toUpperCase();
        if (searchCode.isEmpty()) {
            statusLabel.setText("Enter a Subject Code to Delete");
            return;
        }

        for (Label label : infoList) { // loops through labels in list, label not empty, has text, code matches
        if (label != null && !label.getText().isEmpty() && label.getText().startsWith(searchCode + "-")) {
            label.setText("");   // Clear the text
            label.setVisible(false); // Hide the label
            statusLabel.setText("Subject Deleted Successfully"); //display message
            return;
            }
        }
        //fallback text
        statusLabel.setText("Subject Not Found");
    }

}