package com.example.templatemodule2.essential;

public class course extends subject{
    public String name;
    public String code;
    public String sectionNumber;
    public String teacherName;
    public String  capacity;
    public String lecTime; //formatted Daay Hour:Min 24 hour time
    public String examDate;// /M/D/Y H:M w4 hour time
    public String location;
    public String teacher;
    public String subjectCode;
    public static int courseCounter = 0;


    public course(String courseCode, String courseName, String section, String capacity, String lecture, String finalExam, String location, String teacher) {
        this.code = courseCode;
        this.name = courseName;
        this.sectionNumber = section;
        this.capacity = capacity;
        this.lecTime = lecture;
        this.examDate = finalExam;
        this.location = location;
        this.teacher = teacher;
    }


    public String getCourseName(){
        return name;
    }

    public String getCourseCode(){
        return code;
    }

    public String getCourseSection(){
        return sectionNumber;
    }

    public String getCourseCapacity(){
        return capacity;
    }

    public String getCourseLecture(){
        return lecTime;
    }

    public String getCourseFinal(){
        return examDate;
    }

    public String getCourseLocation(){
        return location;
    }

    public String getCourseTeacher(){
        return teacher;
    }



    public String displayInfo() {
        String printer = "";

        printer += name.toUpperCase() + " (" + code + ")\n";
        printer += "\nSection: " + sectionNumber;
        printer += "\nCapacity: " + capacity;
        printer += "\nLecture: " + lecTime;
        printer += "\nFinal Exam: " + examDate;
        printer += "\nLocation: Room " + location;
        printer += "\nTeacher: " + teacher;

        return printer;
    }

    public void wipe ()
    {
        name = "";
        code = "";
        sectionNumber= "";
        teacherName= "";
        capacity= "";
        lecTime= ""; //formatted Daay Hour:Min 24 hour time
        examDate= "";// /M/D/Y H:M w4 hour time
        location= "";
        teacher= "";
    }
}
