package com.example.templatemodule2.essential;

import java.util.ArrayList;

public class Event {
    public static int eventCounter = 1; // Tracks number of events
    public String eventCode;
    public String name;
    public String description;
    public String headerImage;
    public String location;
    public String dateTime;
    public int capacity;
    public String cost;
    public static int eventCount;

    public ArrayList<String> registeredStudentNames;
    public ArrayList<String> registeredStudentEmails;

    public Event(String name, String description, String headerImage,
                 String location, String dateTime, int capacity, String cost) {
        this.name = name;
        this.description = description;
        this.headerImage = headerImage.isEmpty() ? "default.jpg" : headerImage;
        this.location = location;
        this.dateTime = dateTime;
        this.capacity = capacity;
        this.cost = cost;
        this.eventCode = generateEventCode();  // ✅ Auto-generate event code

        this.registeredStudentNames = new ArrayList<>();
        this.registeredStudentEmails = new ArrayList<>();
    }

    // ✅ Auto-generates event codes in "EV001" format
    private String generateEventCode() {
        return String.format("EV%03d", eventCounter++); // EV001, EV002, EV003...
    }

    public String getEventCode() { return eventCode; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getHeaderImage() { return headerImage; }
    public String getLocation() { return location; }
    public String getDate() { return this.dateTime; }
    public int getCapacity() { return capacity; }
    public String getCost() { return cost; }

    public ArrayList<String> getRegisteredStudentNames() { return registeredStudentNames; }
    public ArrayList<String> getRegisteredStudentEmails() { return registeredStudentEmails; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setHeaderImage(String headerImage) { this.headerImage = headerImage; }
    public void setLocation(String location) { this.location = location; }
    public void setDate(String date) { this.dateTime = date; }
    public void setCost(String cost) { this.cost = cost; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public void addStudent(String studentName, String studentEmail) {
        if (registeredStudentNames.size() < capacity) {
            registeredStudentNames.add(studentName);
            registeredStudentEmails.add(studentEmail);
        }
    }

    @Override
    public String toString() {
        return name + " (Code: " + eventCode + ") - " + dateTime + " - " + location +
                "\nCapacity: " + capacity + " | Cost: " + cost;
    }

    public int getRegisteredCount() {
        return registeredStudentNames.size();
    }
}
