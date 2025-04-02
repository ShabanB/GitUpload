package com.example.templatemodule2;

import com.example.templatemodule2.essential.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.ArrayList;


public class main extends Application {
    static public char userType = 'u';
    public static facuilty[] facuilties = new facuilty[100];
    public static course[] courses = new course[100];
    public static Event[] events = new Event[100];
    public static subject[] subjects = new subject[100];
    public static student[] students = new student[100];
    public static int facCounter = 0;

    public static int mainCounter = 0;
    public static void switchScene (Stage stage,  String s) throws IOException {
        FXMLLoader subManagment = new FXMLLoader(main.class.getResource("subModule.fxml"));
        FXMLLoader courManagment = new FXMLLoader(main.class.getResource("courseModule.fxml"));
        FXMLLoader studManagment = new FXMLLoader(main.class.getResource("studModule.fxml"));
        FXMLLoader facManagment = new FXMLLoader(main.class.getResource("facModule.fxml"));
        FXMLLoader evenManagment = new FXMLLoader(main.class.getResource("mergedEventModule.fxml"));
       // FXMLLoader logModule = new FXMLLoader(main.class.getResource("logModule.fxml"));

        Scene subManagmentScene = new Scene(subManagment.load());
        Scene courManagmentScene = new Scene(courManagment.load());
        //Scene studManagmentScene = new Scene(studManagment.load());
        Scene facManagmentScene = new Scene(facManagment.load());
        Scene evenManagmentScene = new Scene(evenManagment.load());

        //Scene logModuleScene = new Scene(logModule.load());

        if (s == "subjectManagment")
        {
            stage.setTitle("subjectManagment");
            stage.setScene(subManagmentScene);
            stage.show();
        }
        if (s == "courseManagment")
        {
            stage.setTitle("courseManagment");
            stage.setScene(courManagmentScene);
            stage.show();
        }
       /* if (s == "studentManagement")
        {
            stage.setTitle("studentManagement");
            stage.setScene(studManagment);
            stage.show();
        }*/
        if (s == "facuiltyManagment")
        {
            stage.setTitle("facuiltyManagment");
            stage.setScene(facManagmentScene);
            stage.show();
        }
        if (s == "eventManagment")
        {
            stage.setTitle("eventManagment");
            stage.setScene(evenManagmentScene);
            stage.show();
        }
//testing
    }


    @Override
    public void start(Stage stage) throws IOException, InvalidFormatException {

        course emptyCourse = new course("", "", "", "", "", "", "", "");
        //student[] emptyStudent = new student[100];
        subject[] emptySubject = new subject[10];
        ArrayList<String> emptyStudent = new ArrayList<String>();
        for (int i = 0; i < 100; i++)
        {
            Event e = new Event("","","","","",0,"");
            events[i] = e;
            course c = new course("", "", "", "", "", "", "", "");
            courses[i] = c;
            facuilty f = new facuilty();
            facuilties[i] = f;
            subject s = new subject();
            subjects[i] = s;
            student st = new student();
            students[i] = st;


            courses[i].name = i+"";
            facuilties[i].setName("");
            facuilties[i].setEmail("");
            facuilties[i].researchInterest = "";
            facuilties[i].officeLocation = "";
            facuilties[i].degree = "";
            facuilties[i].intital();
        }
        courses[0].name = "calc";

        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("facModule.fxml"));
        facuiltyController controller = new facuiltyController();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("moduleName");
        stage.setScene(scene);
        stage.show();

        File f = new File("C:\\Users\\aidan\\Downloads\\UMS_Data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(f);


        Sheet courseSheet = workbook.getSheet("Courses");
        Sheet facultiesSheet = workbook.getSheet("Faculties");
        Sheet eventSheet = workbook.getSheet("Events");
        Sheet subjectSheet = workbook.getSheet("Subjects");
        Sheet studentSheet = workbook.getSheet("Students ");
        for (int i = 0; i < 100; i++)
        {
            //region CourseFill
            if (courseSheet.getRow(i+1).getCell(2) == null)
            {
                courses[i].code = "";
            }
            else
            {
                courses[i].code = courseSheet.getRow(i+1).getCell(2).toString();
                if (courseSheet.getRow(i+1).getCell(2).toString() != "") {
                    course.courseCounter++;
                }
            }

            if (courseSheet.getRow(i+1).getCell(1) == null)
            {
                courses[i].name = "";
            }
            else
            {
                courses[i].name = courseSheet.getRow(i+1).getCell(1).toString();
            }

            if (courseSheet.getRow(i+1).getCell(3) == null)
            {
                courses[i].sectionNumber = "";
            }
            else
            {
                courses[i].sectionNumber = courseSheet.getRow(i+1).getCell(3).toString();
            }

            if (courseSheet.getRow(i+1).getCell(4) == null)
            {
                courses[i].capacity = "";
            }
            else
            {
                courses[i].capacity = courseSheet.getRow(i+1).getCell(4).toString();
            }

            if (courseSheet.getRow(i+1).getCell(5) == null)
            {
                courses[i].lecTime = "";
            }
            else
            {
                courses[i].lecTime = courseSheet.getRow(i+1).getCell(5).toString();
            }

            if (courseSheet.getRow(i+1).getCell(6) == null)
            {
                courses[i].examDate = "";
            }
            else
            {
                courses[i].examDate = courseSheet.getRow(i+1).getCell(6).toString();
            }

            if (courseSheet.getRow(i+1).getCell(7) == null)
            {
                courses[i].location = "";
            }
            else
            {
                courses[i].location = courseSheet.getRow(i+1).getCell(7).toString();
            }

            if (courseSheet.getRow(i+1).getCell(8) == null)
            {
                courses[i].teacher = "";
            }
            else
            {
                courses[i].teacher = courseSheet.getRow(i+1).getCell(8).toString();
            }
            //endregion
        }
        for (int i = 0; i < 100; i++) {
            //region facFill
            if (facultiesSheet.getRow(i + 1) != null) {
                if (facultiesSheet.getRow(i + 1).getCell(0) == null) {
                    facuilties[i].facID = "";
                } else {
                    facuilties[i].facID = facultiesSheet.getRow(i + 1).getCell(0).toString();
                    if (facuilties[i].facID != "") {
                        facuilty.facCounter++;
                    }
                }

                if (facultiesSheet.getRow(i + 1).getCell(1) == null) {
                    facuilties[i].setName("");
                } else {
                    facuilties[i].setName(facultiesSheet.getRow(i + 1).getCell(1).toString());
                }

                if (facultiesSheet.getRow(i + 1).getCell(2) == null) {
                    facuilties[i].degree = "";
                } else {
                    facuilties[i].degree = facultiesSheet.getRow(i + 1).getCell(2).toString();
                }

                if (facultiesSheet.getRow(i + 1).getCell(3) == null) {
                    facuilties[i].researchInterest = "";
                } else {
                    facuilties[i].researchInterest = facultiesSheet.getRow(i + 1).getCell(3).toString();
                }

                if (facultiesSheet.getRow(i + 1).getCell(4) == null) {
                    facuilties[i].setEmail("");
                } else {
                    facuilties[i].setEmail(facultiesSheet.getRow(i + 1).getCell(4).toString());
                }

                if (facultiesSheet.getRow(i + 1).getCell(5) == null) {
                    facuilties[i].officeLocation = "";
                } else {
                    facuilties[i].officeLocation = facultiesSheet.getRow(i + 1).getCell(5).toString();
                }


                if (facultiesSheet.getRow(i + 1).getCell(6) == null) {
                    facuilties[i].coursesOffered[0] = emptyCourse;
                } else {
                    for (int j = 0; j < courses.length; j++) {
                        if (courses[j].name == facultiesSheet.getRow(i + 1).getCell(6).toString()) {
                            facuilties[i].coursesOffered[0] = courses[j];
                        }
                    }
                }

                if (facultiesSheet.getRow(i + 1).getCell(7) == null) {
                    facuilties[i].password = "";
                } else {
                    facuilties[i].password = facultiesSheet.getRow(i + 1).getCell(7).toString();
                }
            } else {
                facuilties[i].facID = "";
                facuilties[i].setName("");
                facuilties[i].degree = "";
                facuilties[i].researchInterest = "";
                facuilties[i].setEmail("");
                facuilties[i].officeLocation = "";
                facuilties[i].coursesOffered[0] = emptyCourse;
                facuilties[i].password = "";
            }
            //endregion
        }
        for (int i = 0; i < 100; i++) {
            //region subjectFill

            if (subjectSheet.getRow(i + 1) != null) {
                if (subjectSheet.getRow(i + 1).getCell(0) == null) {
                    subjects[i].code = "";
                } else {
                    subjects[i].code = subjectSheet.getRow(i + 1).getCell(0).toString();
                }

                if (subjectSheet.getRow(i + 1).getCell(1) == null) {
                    subjects[i].name = "";
                } else {
                    subjects[i].name = subjectSheet.getRow(i + 1).getCell(1).toString();
                }
            } else {
                subjects[i].code = "";
                subjects[i].name = "";
            }
            //endregion
        }
        for (int i = 0; i < 100; i++) {
            //region studentFill

            if (studentSheet.getRow(i + 1) != null) {
                if (studentSheet.getRow(i + 1).getCell(0) == null) {
                    students[i].studentid = "";
                } else {
                    students[i].studentid = studentSheet.getRow(i + 1).getCell(0).toString();
                }

                if (studentSheet.getRow(i + 1).getCell(1) == null) {
                    students[i].setName("");
                } else {
                    students[i].setName(studentSheet.getRow(i + 1).getCell(1).toString());
                }

                if (studentSheet.getRow(i + 1).getCell(2) == null) {
                    students[i].adress = "";
                } else {
                    students[i].adress = studentSheet.getRow(i + 1).getCell(2).toString();
                }

                if (studentSheet.getRow(i + 1).getCell(3) == null) {
                    students[i].telephone = "";
                } else {
                    students[i].telephone = studentSheet.getRow(i + 1).getCell(3).toString();
                }

                if (studentSheet.getRow(i + 1).getCell(4) == null) {
                    students[i].setEmail("");
                } else {
                    students[i].setEmail(studentSheet.getRow(i + 1).getCell(4).toString());
                }

                if (studentSheet.getRow(i + 1).getCell(5) == null) {
                    students[i].level = "";
                } else {
                    students[i].level = studentSheet.getRow(i + 1).getCell(5).toString();
                }

                if (studentSheet.getRow(i + 1).getCell(6) == null) {
                    students[i].currentSemester = "";
                } else {
                    students[i].currentSemester = studentSheet.getRow(i + 1).getCell(6).toString();
                }

                if (studentSheet.getRow(i + 1).getCell(7) == null) {
                    students[i].photo = "";
                } else {
                    students[i].photo = studentSheet.getRow(i + 1).getCell(7).toString();
                }

                if (studentSheet.getRow(i + 1).getCell(8) == null) {
                    students[i].Subjects = emptySubject;
                } else {
                    String tempString = studentSheet.getRow(i + 1).getCell(8).toString();
                    String tempStringTwo = "";
                    int counter = 0;
                    int counterTwo = 0;

                    if (tempString.contains(",")) {
                        for (int j = 0; j < tempString.length(); j++) {
                            if (tempString.charAt(j) == ',') {
                                for (int k = counter; k < j; k++) {
                                    if (tempString.charAt(k) == ',')
                                    {
                                        tempStringTwo += "";
                                    }
                                    else {
                                        tempStringTwo += tempString.charAt(k);
                                    }
                                    if (k == j - 1) {
                                        for (int l = 0; l < subjects.length; l++) {
                                            if (tempStringTwo.equals(subjects[l].code)) {
                                                students[i].Subjects[counterTwo] = subjects[l];
                                                counterTwo++;
                                                tempStringTwo = "";
                                                break;
                                            }
                                        }
                                        counter = j;
                                    }
                                }
                            }
                        }
                    } else {
                        for (int l = 0; l < subjects.length; l++) {
                            if (tempString == subjects[l].code) {
                                students[i].Subjects[0] = subjects[l];
                                break;
                            }
                        }
                    }

                }

                if (studentSheet.getRow(i + 1).getCell(9) == null) {
                    students[i].title = "";
                } else {
                    students[i].title = studentSheet.getRow(i + 1).getCell(9).toString();
                }

                if (studentSheet.getRow(i + 1).getCell(10) == null) {
                    students[i].completionPercentage = "";
                } else {
                    students[i].completionPercentage = studentSheet.getRow(i + 1).getCell(10).toString();
                }

                if (studentSheet.getRow(i + 1).getCell(11) == null) {
                    students[i].password = "";
                } else {
                    students[i].password = studentSheet.getRow(i + 1).getCell(11).toString();
                }


            } else {
                students[i].studentid = "";
                students[i].setName("");
                students[i].adress = "";
                students[i].telephone = "";
                students[i].setEmail("");
                students[i].level = "";
                students[i].currentSemester = "";
                students[i].photo = "";
                students[i].Subjects = emptySubject;
                students[i].title = "";
                students[i].password = "";
            }

            //endregion
        }
        for (int i = 0; i < 100; i++) {
            //region eventFill
            if (eventSheet.getRow(i + 1) != null) {
                if (eventSheet.getRow(i + 1).getCell(0) == null) {
                    events[i].eventCode = "";
                } else {
                    events[i].eventCode = eventSheet.getRow(i + 1).getCell(0).toString();
                }
                if (eventSheet.getRow(i + 1).getCell(1) == null) {
                    events[i].name = "";
                } else {
                    events[i].name = eventSheet.getRow(i + 1).getCell(1).toString();
                }
                if (eventSheet.getRow(i + 1).getCell(2) == null) {
                    events[i].description = "";
                } else {
                    events[i].description = eventSheet.getRow(i + 1).getCell(2).toString();
                }
                if (eventSheet.getRow(i + 1).getCell(3) == null) {
                    events[i].location = "";
                } else {
                    events[i].location = eventSheet.getRow(i + 1).getCell(3).toString();
                }
                if (eventSheet.getRow(i + 1).getCell(4) == null) {
                    events[i].dateTime = "";
                } else {
                    events[i].dateTime = eventSheet.getRow(i + 1).getCell(4).toString();
                }
                if (eventSheet.getRow(i + 1).getCell(5) == null) {
                    events[i].capacity = 0;
                } else {
                    events[i].capacity = (int) Double.parseDouble((eventSheet.getRow(i + 1).getCell(5).toString()));
                }
                if (eventSheet.getRow(i + 1).getCell(6) == null) {
                    events[i].cost = "";
                } else {
                    events[i].cost = (eventSheet.getRow(i + 1).getCell(6).toString());
                }
                if (eventSheet.getRow(i + 1).getCell(7) == null) {
                    events[i].headerImage = "";
                } else {
                    events[i].headerImage = (eventSheet.getRow(i + 1).getCell(7).toString());
                }
                if (eventSheet.getRow(i + 1).getCell(8) == null) {
                    events[i].registeredStudentNames = emptyStudent;
                } else {
                    String s = eventSheet.getRow(i + 1).getCell(8).toString();
                    String tempString = "";
                    int counter = 0;
                    for (int j = 0; j < eventSheet.getRow(i + 1).getCell(8).toString().length(); j++) {
                        if (s.charAt(j) == ',') {
                            for (int k = counter; k < j; k++) {
                                tempString += s.charAt(k);
                                if (k == j - 1) {
                                    events[i].registeredStudentNames.add(tempString);
                                    tempString = "";
                                    counter = k+2;
                                }
                            }
                        }
                    }
                }
            MergedEventController.eventList.add(events[i]);
            }
            else
            {
                events[i].eventCode = "";
                events[i].name = "";
                events[i].description = "";
                events[i].location = "";
                events[i].dateTime = "";
                events[i].capacity = 0;
                events[i].cost = "";
                events[i].headerImage = "";
                events[i].registeredStudentNames = emptyStudent;
                MergedEventController.eventList.add(events[i]);
            }

            //endregion
        }
    workbook.close();
    }

    @Override
    public void stop() throws IOException, InvalidFormatException {
        File excelFile = new File("C:\\Users\\aidan\\Downloads\\UMS_Data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        Sheet facultiesSheet = workbook.getSheet("Faculties");
        Sheet studentSheet = workbook.getSheet("Students ");
        Sheet subjectSheet = workbook.getSheet("Subjects");
        Sheet courseSheet = workbook.getSheet("Courses");
        Sheet eventSheet = workbook.getSheet("Events");
        //region facDeposit
        for (int i = 0; i<facuilties.length;i ++) {

            Row row = facultiesSheet.createRow(i+1);
            Cell cell = row.createCell(0);
            cell.setCellValue(facuilties[i].facID);

            cell = row.createCell(1);
            cell.setCellValue(facuilties[i].getName());

            cell = row.createCell(2);
            cell.setCellValue(facuilties[i].degree);

            cell = row.createCell(3);
            cell.setCellValue(facuilties[i].researchInterest);

            cell = row.createCell(4);
            cell.setCellValue(facuilties[i].getEmail());

            cell = row.createCell(5);
            cell.setCellValue(facuilties[i].officeLocation);

            cell = row.createCell(6);
            cell.setCellValue(facuilties[i].coursesOffered[0].name);

            cell = row.createCell(7);
            cell.setCellValue(facuilties[i].password);
        }
        //endregion

        //region studentSheet
        for (int i = 0; i < students.length; i ++)
        {
            Row row = studentSheet.createRow(i+1);
            Cell cell = row.createCell(0);
            cell.setCellValue(students[i].studentid);

            cell = row.createCell(1);
            cell.setCellValue(students[i].getName());

            cell = row.createCell(2);
            cell.setCellValue(students[i].adress);

            cell = row.createCell(3);
            cell.setCellValue(students[i].telephone);

            cell = row.createCell(4);
            cell.setCellValue(students[i].getEmail());

            cell = row.createCell(5);
            cell.setCellValue(students[i].level);

            cell = row.createCell(6);
            cell.setCellValue(students[i].currentSemester);

            cell = row.createCell(7);
            cell.setCellValue(students[i].photo);

            cell = row.createCell(8);
            String tempString = "";
            for (int j = 0; j < students[i].Subjects.length; j++)
            {
                if (students[i].Subjects[j] == null)
                {
                    tempString += "";
                }
                else {
                    if (j == students[i].Subjects.length - 1) {
                        tempString += students[i].Subjects[j].code;
                    } else {
                        tempString += students[i].Subjects[j].code + ",";
                    }
                }
            }
            cell.setCellValue(tempString);

            cell = row.createCell(9);
            cell.setCellValue(students[i].title);

            cell = row.createCell(10);
            cell.setCellValue(students[i].completionPercentage);

            cell = row.createCell(11);
            cell.setCellValue(students[i].password);
        }

        //endregion

        //region subDeposit
        for (int i = 0; i<subjects.length;i ++) {

            Row row = subjectSheet.createRow(i+1);
            Cell cell = row.createCell(0);
            cell.setCellValue(subjects[i].code);

            cell = row.createCell(1);
            cell.setCellValue(subjects[i].name);
        }


        //endregion

        //region courseDeposit
        for (int i = 0; i<courses.length;i ++) {

            Row row = courseSheet.createRow(i+1);
            Cell cell = row.createCell(2);
            cell.setCellValue(courses[i].code);

            cell = row.createCell(1);
            cell.setCellValue(courses[i].name);

            cell = row.createCell(3);
            cell.setCellValue(courses[i].sectionNumber);

            cell = row.createCell(4);
            cell.setCellValue(courses[i].capacity);

            cell = row.createCell(5);
            cell.setCellValue(courses[i].lecTime);

            cell = row.createCell(6);
            cell.setCellValue(courses[i].examDate);

            cell = row.createCell(7);
            cell.setCellValue(courses[i].teacher);
        }


        //endregion

        //region eventDeposit
        for (int i = 0; i < MergedEventController.eventList.size(); i++)
        {
            events[i] = MergedEventController.eventList.get(i);
        }

        for (int i = 0; i<events.length;i ++) {

            Row row = subjectSheet.createRow(i+1);
            Cell cell = row.createCell(0);
            cell.setCellValue(events[i].eventCode);

            cell = row.createCell(1);
            cell.setCellValue(events[i].name);

            cell = row.createCell(2);
            cell.setCellValue(events[i].description);

            cell = row.createCell(3);
            cell.setCellValue(events[i].location);

            cell = row.createCell(4);
            cell.setCellValue(events[i].capacity);

            cell = row.createCell(5);
            cell.setCellValue(events[i].cost);

            cell = row.createCell(6);
            cell.setCellValue(events[i].headerImage);

            cell = row.createCell(7);
            String tempString = "";
            for (int j = 0; j < events[i].registeredStudentNames.size(); j++) {
                tempString += (events[i].registeredStudentNames.get(j) + ",");
            }
            cell.setCellValue(tempString);
        }



        //endregion



        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\aidan\\ExcelTestingTwo\\src\\main\\resources\\WorkbookThree.xlsx");
        workbook.write(fileOut);
        workbook.close();
        fileOut.close();
    }


    public static void main(String[] args) {
        launch();
    }
}

