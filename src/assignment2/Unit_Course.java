/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.util.Arrays;

/**
 *
 * @author YT
 */
public class Unit_Course extends Unit {
    private String id;
    private int level;
    private double[] assignmentMarks;
    private double[] labMarks;
    private double finalExamMark;

    public Unit_Course(String id, int level) {
        this.id = id;
        this.level = level;
        assignmentMarks = new double[2];
        labMarks = new double[12];
    }

    @Override
    public String getEnrolmentType() {
        return "C";
    }

    @Override
    public double getOverallMark() {
        double overallMark = 0.0;
        overallMark = (assignmentMarks[0] + assignmentMarks[1]) * 0.2 + getBest10LabMark() * 0.2 + finalExamMark * 0.4;
        return overallMark;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public void setAssignmentMark(int index, double mark) throws IndexOutOfBoundsException {
        assignmentMarks[index] = mark;
    }

    public void setLabMark(int week, double mark) throws IndexOutOfBoundsException {
        labMarks[week] = mark;
    }

    public void setFinalExamMark(double finalExamMark) {
        this.finalExamMark = finalExamMark;
    }

    private double getBest10LabMark() {
        double[] sortedLabMarks = labMarks.clone();
        Arrays.sort(sortedLabMarks);
        double best10LabMark = 0.0;
        for (int i = 2; i < 12; i++) {
            best10LabMark += sortedLabMarks[i];
        }
        return best10LabMark * 0.2;
    }
}
