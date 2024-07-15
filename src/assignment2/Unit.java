/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

/**
 *
 * @author YT
 */
public abstract class Unit {
    public String getEnrolmentType() {
        return "NA";
    }

    public abstract double getOverallMark();

    public String getFinalGrade() {
        if (getEnrolmentType().equals("NA"))
            return "NA";

        double overallMark = getOverallMark();
        if (overallMark >= 80)
            return "HD";
        else if (overallMark >= 70)
            return "D";
        else if (overallMark >= 60)
            return "C";
        else if (overallMark >= 50)
            return "P";
        else
            return "N";
    }

}
