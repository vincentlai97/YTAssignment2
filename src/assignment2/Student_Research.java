/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

/**
 *
 * @author YT
 */
public class Student_Research extends Student {
    private Research unit;

    public Student_Research(String firstName, String lastName, long studentNumber, String dateOfBirth, Research unit) {
        super(firstName, lastName, studentNumber, dateOfBirth);
        this.unit = unit;
    }

    @Override
    public void reportGrade() {
        System.out.println("Enrolment Type: " + unit.getEnrolmentType());
        System.out.println("    First Name: " + getFirstName());
        System.out.println("     Last Name: " + getLastName());
        System.out.println("Student Number: " + getStudentNumber());
        System.out.println(" Date of Birth: " + getDateOfBirth());
        System.out.println("  Overall Mark: " + unit.getOverallMark());
        System.out.println("   Final Grade: " + unit.getFinalGrade());
    }
}
