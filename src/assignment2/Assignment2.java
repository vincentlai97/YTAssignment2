/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                ++lineNumber;

                if (values.length == 0)
                    System.err.println("Line " + lineNumber + " is empty!");

                switch (values[0]) {

                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(filePath + " not found.");
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        }
    }

    protected void addCourseStudent(String[] values) {
        if (values.length != 19)
            throw new IllegalArgumentException("Line should contain 19 values");
    }
}
