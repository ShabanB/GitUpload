package com.example.templatemodule2;


import com.example.templatemodule2.essential.course;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;

import java.io.IOException;
import java.util.ResourceBundle;

public class courseAddController implements Initializable {
    public TextField courseNameText;
    public TextField courseCodeText;
    public TextField capacityText;
    public TextField secNumText;
    public TextField lecTimeText;
    public TextField teacherText;
    public TextField examText;
    public TextField roomNumberText;
    public Button submitCourseButton;
    public Button courseBackButton;
    String name, code, section, teacher, lecture, exam,room;
    String capacity;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*main.courses[0] = new course("1","Calculus 1","1","30","Mon/Wed 9-11AM", "2025-12-15 9:00", "101", "Dr. Alan Turing");
        main.courses[1] = new course("2","Literature Basics", "1", "25","Tue/Wed 10-12PM", "2025-12-16 10:00", "102", "Prof. Emily Bronte");
        main.courses[2] = new course("3", "Introduction to Programming", "1","42","Tue/Thu 12-2 PM", "2025-12-16 10:00", "102", "Prof. Bahar Nozari");
        main.courses[3] = new course("4","Introduction to Chemistry", "1","50","Mon/Thu 3-4 PM","2025-12-14 4:00","201","Dr. Lucka Lucku");
        main.courses[4] = new course("5","Introduction to French","1","25","Tue/Thur 4:30-5:30","2025-12-13 10:00","202","Dr. Lakyn Copeland");
        main.courses[5] = new course("6","Water Resources","1","50","Mon/Fri 9:00-10:30 AM","2025-12-01 9:00", "203", "Dr. Albozr Gharabaghi");*/
    }

    @FXML
    protected void onSubmitCourseButton() {
        name = courseNameText.getText();
        code = courseCodeText.getText();
        capacity = capacityText.getText();
        section = secNumText.getText();
        teacher = teacherText.getText();
        lecture = lecTimeText.getText();
        exam = examText.getText();
        room = roomNumberText.getText();


        course addedCourse = new course(code,name,section,capacity,lecture,exam,room,teacher);
        main.courses[course.courseCounter] = addedCourse;
        course.courseCounter++;

        courseNameText.setText("");
        courseCodeText.setText("");
        capacityText.setText("");
        secNumText.setText("");
        teacherText.setText("");
        lecTimeText.setText("");
        examText.setText("");
        roomNumberText.setText("");
    }

    /*
    @FXML
    public static ArrayList<Course> getCourseList(){
        return courseList;
    }*/

    @FXML
    protected void onCourseBackButton() throws IOException {
        Stage currentStage = (Stage) courseNameText.getScene().getWindow();
        FXMLLoader dataPage = new FXMLLoader(main.class.getResource("courseModule.fxml"));
        Scene dataPageScene = new Scene(dataPage.load());
        currentStage.setTitle("dataPageScene");
        currentStage.setScene(dataPageScene);
        currentStage.show();
    }

}
