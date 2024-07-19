/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author YT
 */
public class Assignment2 {
    private ArrayList<Student> students = new ArrayList<>();
    private boolean sorted = false;

    public static void main(String[] args) {
        new Assignment2().main();
    }

    protected void main() {
        Scanner sc = new Scanner(System.in);

        addFile("students.csv");

        while (true) {
            printMenu();

            try {
                int option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1:
                        return;
                    case 2:
                        addFile(sc);
                        break;
                    case 3:
                        removeStudent(sc);
                        break;
                    case 4:
                        OutputStudentsDetails(students);
                        break;
                    case 5:
                        displayPerformanceForCourseWork();
                        break;
                    case 6:
                        reportStudentGrade(sc);
                        break;
                    case 7:
                        sortStudents();
                        break;
                    case 8:
                        displaySortedStudentsToFile();
                        break;
                    default:
                        throw new InputMismatchException();
                }
            } catch (InputMismatchException ex) {
                System.out.println("Option is invalid.");
                sc.nextLine();
            }
        }
    }

    protected static void printMenu() {
        System.out.println("Enter option:");
        System.out.println("1. Quit");
        System.out.println("2. Add file");
        System.out.println("3. Remove student");
        System.out.println("4. Output students' details");
        System.out.println("5. Display course work students' performance");
        System.out.println("6. Report student's grade");
        System.out.println("7. Sort students");
        System.out.println("8. Output sorted students to a file");
    }

    protected void addFile(String filePath) {
        sorted = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                ++lineNumber;

                if (values.length == 0) {
                    System.err.println("Line " + lineNumber + " is empty!");
                    continue;
                }

                switch (values[0]) {
                    case "C":
                        try {
                            students.add(parseCourseStudent(values));
                        } catch (Exception ex) {
                            System.err.println("Line " + lineNumber + ": " + ex.getMessage());
                        }
                        break;
                    case "R":
                        try {
                            students.add(parseResearchStudent(values));
                        } catch (Exception ex) {
                            System.err.println("Line " + lineNumber + ": " + ex.getMessage());
                        }
                        break;
                    default:
                        System.err.println("Line " + lineNumber + " has wrong unit identifier!");
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(filePath + " not found.");
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        }
    }

    protected Student_Course parseCourseStudent(String[] values)
            throws IllegalArgumentException, DateTimeParseException {
        if (values.length != 22)
            throw new IllegalArgumentException("Course student entry should contain 22 values!");

        Unit_Course unit;
        try {
            unit = new Unit_Course(values[5], Integer.parseInt(values[6]));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Value for level at index 6 should be int!");
        }

        for (int i = 0; i < 2; ++i) {
            try {
                unit.setAssignmentMark(i, Double.parseDouble(values[7 + i]));
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException(
                        "Value for assignment " + (i + 1) + " at index " + (7 + i) + " should be double!");
            }
        }

        for (int i = 0; i < 12; ++i) {
            try {
                unit.setLabMark(i, Double.parseDouble(values[9 + i]));
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException(
                        "Value for lab " + (i + 1) + " at index " + (9 + i) + " should be double!");
            }
        }

        try {
            unit.setFinalExamMark(Double.parseDouble(values[21]));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                    "Value for final exam at index 21 should be double!");
        }

        Student_Course student;
        try {
            student = new Student_Course(values[1], values[2], Long.parseLong(values[3]), values[4], unit);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                    "Value for student number at index 3 should be long!");
        } catch (DateTimeParseException ex) {
            throw ex;
        }

        return student;
    }

    protected Student_Research parseResearchStudent(String[] values)
            throws IllegalArgumentException, DateTimeParseException {
        if (values.length != 7)
            throw new IllegalArgumentException("Research student entry should contain 7 values!");

        Research research;
        research = new Research();

        try {
            research.setProposalMark(Double.parseDouble(values[5]));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                    "Value for proposal at index 5 should be double!");
        }

        try {
            research.setFinalDissertationMark(Double.parseDouble(values[6]));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                    "Value for final dissertation at index 6 should be double!");
        }

        Student_Research student;
        try {
            student = new Student_Research(values[1], values[2], Long.parseLong(values[3]), values[4], research);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                    "Value for student number at index 3 should be long!");
        } catch (DateTimeParseException ex) {
            throw ex;
        }

        return student;
    }

    private void addFile(Scanner sc) {
        System.out.print("Enter file name: ");
        String fileName = sc.nextLine();
        addFile(fileName);
    }

    private void removeStudent(Scanner sc) {
        System.out.println("Enter the student number (ID) of the student you want to remove:");
        long studentNumberToRemove = sc.nextLong();

        // Find the student with the specified student number
        Student studentToRemove = null;
        for (Student student : students) {
            if (student.getStudentNumber() == studentNumberToRemove) {
                studentToRemove = student;
                break;
            }
        }

        if (studentToRemove == null) {
            System.out.println("Student with ID " + studentNumberToRemove + " not found.");
            return;
        }

        // Confirm removal with the user
        System.out.println("Do you want to remove the following student?");
        System.out.println("Student Number: " + studentToRemove.getStudentNumber());
        System.out.println("Name: " + studentToRemove.getFirstName() + " " + studentToRemove.getLastName());
        System.out.println("Enter 'yes' to confirm removal, or esc to cancel:");
        String confirmation = sc.next();

        if (confirmation.equalsIgnoreCase("yes")) {
            students.remove(studentToRemove);
            System.out.println("Student is removed successfully.");
        } else {
            System.out.println("Removal is canceled.");
        }
    }

    private void OutputStudentsDetails(ArrayList<Student> students) {
        System.out.println("Students' details:");
        int index = 0;
        for (Student student : students) {
            System.out.println(++index + ".");
            student.reportGrade();
        }
    }

    public void displayPerformanceForCourseWork() {
        // Filter and count only course work students
        int totalCourseWorkStudents = 0;
        double sumOverallMarks = 0;
        int aboveOrEqualAverage = 0;
        int belowAverage = 0;

        for (Student student : students) {
            if (student instanceof Student_Course) {
                totalCourseWorkStudents++;

                Student_Course studentCourse = (Student_Course) student;
                Unit_Course unit = studentCourse.getUnit();
                double overallMark = unit.getOverallMark();
                sumOverallMarks += overallMark;
            }
        }

        if (totalCourseWorkStudents == 0) {
            System.out.println("No course work students found.");
            return;
        }

        double averageOverallMark = (double) sumOverallMarks / totalCourseWorkStudents;

        // Count students above or equal to average and below average
        for (Student student : students) {
            if (student instanceof Student_Course) {
                Student_Course studentCourse = (Student_Course) student;
                Unit_Course unit = studentCourse.getUnit();
                double overallMark = unit.getOverallMark();

                if (overallMark >= averageOverallMark) {
                    aboveOrEqualAverage++;
                } else {
                    belowAverage++;
                }
            }
        }

        // Display results
        System.out.println("Performance for Course Work Students:");
        System.out.println("Total Course Work Students: " + totalCourseWorkStudents);
        System.out.println("Average Overall Mark: " + averageOverallMark);
        System.out.println("Students with Overall Mark >= Average: " + aboveOrEqualAverage);
        System.out.println("Students with Overall Mark < Average: " + belowAverage);
    }

    public void reportStudentGrade(Scanner sc) {
        // Ask for student number (ID) input
        System.out.println("Enter student number (ID) to report grade:");
        long studentNumber = sc.nextLong();

        boolean found = false;

        // Search for the student in the ArrayList
        for (Student student : students) {
            if (student.getStudentNumber() == studentNumber) {
                found = true;
                student.reportGrade(); // Call reportGrade() to display grade information
                break;
            }
        }

        // If student not found, display error message
        if (!found) {
            System.out.println("Student with ID " + studentNumber + " not found.");
        }
    }

    public void sortStudents() {
        int n = students.size();

        // Perform insertion sort on students based on student number (ID)
        for (int i = 1; i < n; i++) {
            Student key = students.get(i);
            long keyStudentNumber = key.getStudentNumber();
            int j = i - 1;

            // Move elements of students[0..i-1], that are greater than key, to one position
            // ahead of their current position
            while (j >= 0 && students.get(j).getStudentNumber() > keyStudentNumber) {
                students.set(j + 1, students.get(j));
                j--;
            }

            students.set(j + 1, key);
        }

        sorted = true;
        System.out.println("Students sorted");
    }

    public void displaySortedStudentsToFile() {
        if (!sorted) {
            System.out.println("Students ArrayList is not sorted. Please sort the list first.");
            return;
        }

        String csvFileName = "sorted_students.csv";

        try (FileWriter writer = new FileWriter(csvFileName)) {
            // Write CSV header
            writer.write("Student Number,First Name,Last Name,Enrollment Type\n");

            // Write sorted students data to CSV file
            for (Student student : students) {
                String enrollmentType = "";
                if (student instanceof Student_Course) {
                    enrollmentType = "C"; // Course work student
                } else if (student instanceof Student_Research) {
                    enrollmentType = "R"; // Research student
                }

                // Format student details into CSV format
                String csvLine = String.format("%d,%s,%s,%s\n", student.getStudentNumber(), student.getFirstName(),
                        student.getLastName(), enrollmentType);
                writer.write(csvLine);
            }

            System.out.println("Sorted students data has been successfully written to " + csvFileName);

        } catch (Exception e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
