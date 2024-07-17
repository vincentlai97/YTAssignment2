/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
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
                        break;
                    case 3:
                        break;
                    case 4:
                        OutputStudentsDetails(students);
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
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

    private void OutputStudentsDetails(ArrayList<Student> students) {
        System.out.println("Students' details:");
        for (Student student : students) {
            student.reportGrade();
            System.out.println();
        }
    }
}
