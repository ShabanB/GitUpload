package com.example.templatemodule2;


import com.example.templatemodule2.essential.facuilty;
import com.example.templatemodule2.essential.search;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.filechooser.*;

import javafx.scene.image.ImageView;


public class facuiltyController implements Initializable {
    search searcher = new search();
    int searchMode = 0; //0 for fac 1 for student
    boolean firstEntry = false;
    @FXML
    public Label userTypeLabel;
    @FXML
    public Label subLabel;
    @FXML
    public TextField searchTextField;
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
    public Parent group;
    @FXML
    public Button searchButton;
    @FXML
    public Button editButton;
    @FXML
    public TextField nameBox;
    @FXML
    public TextField degreeBox;
    @FXML
    public TextField researchBox;
    @FXML
    public TextField expertiseBox;
    @FXML
    public TextField coursesOfferedBox;
    @FXML
    public TextField emailBox;
    @FXML
    public TextField officeLocationBox;
    @FXML
    public Label name2Label;
    @FXML
    public Label degreeLabel;
    @FXML
    public Label researchLabel;
    @FXML
    public Label expertiseLabel;
    @FXML
    public Label coursesLabel;
    @FXML
    public Label emailLabel;
    @FXML
    public Label officeLabel;
    @FXML
    public Parent feildsGroup;
    @FXML
    public Button changeImageButton;
    @FXML
    public Button addButton;
    @FXML
    public ImageView imageBox;

    String tempImagePath = new String();











    @FXML
    public Label[] infoList = new Label[14];


    public facuiltyController() {

    }

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

        for (int i = 0; i < 14; i++)
        {
            infoList[i].setText(i + "");
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
        userTypeLabel.setText("hello");
    }

    @FXML
    protected void onSearchButton()
    {
        String[] newString = new String[main.facuilties.length];
        String[] placeString = new String[main.facuilties.length];
        for(int i = 0; i < main.facuilties.length; i++)
        {
            placeString[i] = main.facuilties[i].getName();
        }



        newString = searcher.multiSearch(searchTextField.getText(), placeString);
        for (int i = 0; i < infoList.length; i++)
        {
            infoList[i].setText(newString[i]);
        }
    }
    @FXML
    protected void onFacuiltyListButton()
    {
        //region visability
        subLabel.setLayoutX(176);
        subLabel.setLayoutY(132);
        searchTextField.setVisible(true);
        searchButton.setVisible(true);
        group.setVisible(true);
        subLabel.setText("Facuilty List");
        editButton.setVisible(true);
        feildsGroup.setVisible(false);
        addButton.setText("Add");
        //endregion

        for(int i = 0; i < 14; i++)
        {
            if(main.facuilties[i].getName().equals("")) {
                infoList[i].setText("");
            }
            else
            {
                infoList[i].setText("Name: " + main.facuilties[i].getName() + " Degree: " + main.facuilties[i].degree + "  Office Location: " + main.facuilties[i].officeLocation + "    Email: " + main.facuilties[i].getEmail());
            }
        }
        searchMode = 0;
    }


    @FXML
    protected void onStudentListButton()
    {
        //region visability
        subLabel.setLayoutX(387);
        subLabel.setLayoutY(145);
        searchTextField.setVisible(false);
        searchButton.setVisible(false);
        group.setVisible(false);
        subLabel.setText("Add Facuilty");
        editButton.setVisible(false);
        feildsGroup.setVisible(true);
        //endregion
    }

    @FXML
    protected void onChangeImageButton() throws IOException {
        JFileChooser fileChoose = new JFileChooser();
        fileChoose.showOpenDialog(null);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
        fileChoose.setFileFilter(filter);
        File f =  fileChoose.getSelectedFile();
        String s = new String();
        s = f.getAbsolutePath();
        Image image  = new Image(s);
        imageBox.setImage(image);
        tempImagePath = s;
    }

    @FXML
    protected void onAddButton()
    {
        if (addButton.getText().equals("Add")) {
            facuilty f = new facuilty();
            f.setName(nameBox.getText());
            nameBox.setText("");

            f.researchInterest = researchBox.getText();
            researchBox.setText("");

            f.degree = degreeBox.getText();
            degreeBox.setText("");

            f.officeLocation = officeLocationBox.getText();
            officeLocationBox.setText("");

            f.setEmail(emailBox.getText());
            emailBox.setText("");

            String s = new String();
            String sArray[] = new String[10];
            for (int i = 0; i < sArray.length; i++) {
                sArray[i] = "";
            }

            int sCount = 0;
            int lastIndex = 0;
            s = coursesOfferedBox.getText();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ',') {
                    sArray[sCount] = "";
                    for (int j = lastIndex; j < i; j++) {
                        sArray[sCount] += s.charAt(j) + "";
                    }
                    sCount++;
                    lastIndex = i + 1;
                }
            }
            f.intital();
            for (int i = 0; i < sArray.length; i++) {
                for (int j = 0; j < main.courses.length; j++) {
                    if (sArray[i].equals(main.courses[j].name)) {
                        f.coursesOffered[f.courseCounter] = main.courses[j];
                        break;
                    }
                }
            }
            coursesOfferedBox.setText("");
            main.facuilties[facuilty.facCounter] = f;
            facuilty.facCounter++;
        }
        else if (addButton.getText().equals("Save"))
        {
            int index = 0;
            facuilty f = new facuilty();
            for(int i = 0; i < main.facuilties.length; i++)
            {
                if (main.facuilties[i].getName().equals(nameBox.getText()) && main.facuilties[i].getEmail().equals(emailBox.getText()))
                {
                    index = i;
                }
            }

            f.setName(nameBox.getText());
            nameBox.setText("");

            f.researchInterest = researchBox.getText();
            researchBox.setText("");

            f.degree = degreeBox.getText();
            degreeBox.setText("");

            f.officeLocation = officeLocationBox.getText();
            officeLocationBox.setText("");

            f.setEmail(emailBox.getText());
            emailBox.setText("");

            String s = new String();
            String sArray[] = new String[10];
            for (int i = 0; i < sArray.length; i++) {
                sArray[i] = "";
            }

            int sCount = 0;
            int lastIndex = 0;
            s = coursesOfferedBox.getText();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ',') {
                    sArray[sCount] = "";
                    for (int j = lastIndex; j < i; j++) {
                        sArray[sCount] += s.charAt(j) + "";
                    }
                    sCount++;
                    lastIndex = i + 1;
                }
            }
            f.intital();
            for (int i = 0; i < sArray.length; i++) {
                for (int j = 0; j < main.courses.length; j++) {
                    if (sArray[i].equals(main.courses[j].name)) {
                        f.coursesOffered[f.courseCounter] = main.courses[j];
                        break;
                    }
                }
            }
            main.facuilties[index] = f;
        }
    }

    //region editLabels
    public void onlabelOne()
    {
        if (infoList[0].equals(""))
        {

        }else {
            fillFeilds(main.facuilties[0]);

            subLabel.setLayoutX(387);
            subLabel.setLayoutY(145);
            searchTextField.setVisible(false);
            searchButton.setVisible(false);
            group.setVisible(false);
            subLabel.setText("Edit Facuilty");
            editButton.setVisible(false);
            feildsGroup.setVisible(true);
            addButton.setText("Save");
        }
    }
    public void onlabelTwo()
    {
        if (infoList[0].equals(""))
        {

        }else {
            fillFeilds(main.facuilties[1]);

            subLabel.setLayoutX(387);
            subLabel.setLayoutY(145);
            searchTextField.setVisible(false);
            searchButton.setVisible(false);
            group.setVisible(false);
            subLabel.setText("Edit Facuilty");
            editButton.setVisible(false);
            feildsGroup.setVisible(true);
            addButton.setText("Save");
        }
    }
    public void onlabelThree()
    {
        if (infoList[0].equals(""))
        {

        }else {
            fillFeilds(main.facuilties[2]);

            subLabel.setLayoutX(387);
            subLabel.setLayoutY(145);
            searchTextField.setVisible(false);
            searchButton.setVisible(false);
            group.setVisible(false);
            subLabel.setText("Edit Facuilty");
            editButton.setVisible(false);
            feildsGroup.setVisible(true);
            addButton.setText("Save");
        }
    }
    public void onlabelFour()
    {
        if (infoList[0].equals(""))
        {

        }else {
            fillFeilds(main.facuilties[3]);

            subLabel.setLayoutX(387);
            subLabel.setLayoutY(145);
            searchTextField.setVisible(false);
            searchButton.setVisible(false);
            group.setVisible(false);
            subLabel.setText("Edit Facuilty");
            editButton.setVisible(false);
            feildsGroup.setVisible(true);
            addButton.setText("Save");
        }
    }
    public void onlabelFive()
    {
        if (infoList[0].equals(""))
        {

        }else {
            fillFeilds(main.facuilties[4]);

            subLabel.setLayoutX(387);
            subLabel.setLayoutY(145);
            searchTextField.setVisible(false);
            searchButton.setVisible(false);
            group.setVisible(false);
            subLabel.setText("Edit Facuilty");
            editButton.setVisible(false);
            feildsGroup.setVisible(true);
            addButton.setText("Save");
        }
    }
    public void onlabelSix()
    {
        if (infoList[0].equals(""))
        {

        }else {
            fillFeilds(main.facuilties[5]);

            subLabel.setLayoutX(387);
            subLabel.setLayoutY(145);
            searchTextField.setVisible(false);
            searchButton.setVisible(false);
            group.setVisible(false);
            subLabel.setText("Edit Facuilty");
            editButton.setVisible(false);
            feildsGroup.setVisible(true);
            addButton.setText("Save");
        }
    }
    public void onlabelSeven()
    {
        if (infoList[0].equals(""))
        {

        }else {
            fillFeilds(main.facuilties[6]);

            subLabel.setLayoutX(387);
            subLabel.setLayoutY(145);
            searchTextField.setVisible(false);
            searchButton.setVisible(false);
            group.setVisible(false);
            subLabel.setText("Edit Facuilty");
            editButton.setVisible(false);
            feildsGroup.setVisible(true);
            addButton.setText("Save");
        }
    }
    public void onlabelEight()
    {
        if (infoList[0].equals(""))
        {

        }else {
            fillFeilds(main.facuilties[7]);

            subLabel.setLayoutX(387);
            subLabel.setLayoutY(145);
            searchTextField.setVisible(false);
            searchButton.setVisible(false);
            group.setVisible(false);
            subLabel.setText("Edit Facuilty");
            editButton.setVisible(false);
            feildsGroup.setVisible(true);
            addButton.setText("Save");
        }
    } public void onlabelNine()
    {
        if (infoList[0].equals(""))
        {

        }else {
            fillFeilds(main.facuilties[8]);

            subLabel.setLayoutX(387);
            subLabel.setLayoutY(145);
            searchTextField.setVisible(false);
            searchButton.setVisible(false);
            group.setVisible(false);
            subLabel.setText("Edit Facuilty");
            editButton.setVisible(false);
            feildsGroup.setVisible(true);
            addButton.setText("Save");
        }
    }
    public void onlabelTen()
    {
        if (infoList[0].equals(""))
        {

        }else {
            fillFeilds(main.facuilties[9]);

            subLabel.setLayoutX(387);
            subLabel.setLayoutY(145);
            searchTextField.setVisible(false);
            searchButton.setVisible(false);
            group.setVisible(false);
            subLabel.setText("Edit Facuilty");
            editButton.setVisible(false);
            feildsGroup.setVisible(true);
            addButton.setText("Save");
        }
    }
    public void onlabelEleven()
    {
        if (infoList[0].equals(""))
        {

        }else {
            fillFeilds(main.facuilties[10]);

            subLabel.setLayoutX(387);
            subLabel.setLayoutY(145);
            searchTextField.setVisible(false);
            searchButton.setVisible(false);
            group.setVisible(false);
            subLabel.setText("Edit Facuilty");
            editButton.setVisible(false);
            feildsGroup.setVisible(true);
            addButton.setText("Save");
        }
    }
    public void onlabelTwelve()
    {
        if (infoList[0].equals(""))
        {

        }else {
            fillFeilds(main.facuilties[11]);

            subLabel.setLayoutX(387);
            subLabel.setLayoutY(145);
            searchTextField.setVisible(false);
            searchButton.setVisible(false);
            group.setVisible(false);
            subLabel.setText("Edit Facuilty");
            editButton.setVisible(false);
            feildsGroup.setVisible(true);
            addButton.setText("Save");
        }
    }
    public void onlabelThirteen()
    {
        if (infoList[0].equals(""))
        {

        }else {
            fillFeilds(main.facuilties[12]);

            subLabel.setLayoutX(387);
            subLabel.setLayoutY(145);
            searchTextField.setVisible(false);
            searchButton.setVisible(false);
            group.setVisible(false);
            subLabel.setText("Edit Facuilty");
            editButton.setVisible(false);
            feildsGroup.setVisible(true);
            addButton.setText("Save");
        }
    }public void onlabelFourteen()
    {
        if (infoList[0].equals(""))
        {

        }else {
            fillFeilds(main.facuilties[13]);

            subLabel.setLayoutX(387);
            subLabel.setLayoutY(145);
            searchTextField.setVisible(false);
            searchButton.setVisible(false);
            group.setVisible(false);
            subLabel.setText("Edit Facuilty");
            editButton.setVisible(false);
            feildsGroup.setVisible(true);
            addButton.setText("Save");
        }
    }

    //endregion

    public void fillFeilds(facuilty f)
    {
        nameBox.setText(f.getName());

        researchBox.setText(f.researchInterest);

        degreeBox.setText(f.degree);

        officeLocationBox.setText(f.officeLocation);

        emailBox.setText(f.getEmail());

        String s = "";
        for(int i = 0; i < 3; i++)
        {
            s += (f.coursesOffered[i].name + ",");
        }

        coursesOfferedBox.setText(s);
    }
}
