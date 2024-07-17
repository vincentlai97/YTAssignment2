/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author YT
 */
public class Student {
    private String firstName;
    private String lastName;
    private long studentNumber;
    private LocalDate dateOfBirth;

    // default constructor
    public Student() {
        this.firstName = "";
        this.lastName = "";
        this.studentNumber = 0;
        this.dateOfBirth = null;
    }

    public Student(String firstName, String lastName, long studentNumber, String dateOfBirth)
            throws DateTimeParseException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        setDateOfBirth(dateOfBirth);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(long studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setDateOfBirth(String dateOfBirth) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);
    }

    public void reportGrade() {
        System.out.println("There is no grade here.");
    }

    public boolean equals(Student other) {
        return this.studentNumber == other.studentNumber;
    }

    public void info() {
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Date of Birth: " + getDateOfBirth());
        System.out.println("Student Number: " + studentNumber);
    }
}
