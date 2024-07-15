/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

/**
 *
 * @author YT
 */
public class Research extends Unit {
    private double proposalMark;
    private double finalDissertationMark;

    public Research() {
    }

    @Override
    public String getEnrolmentType() {
        return "R";
    }

    @Override
    public double getOverallMark() {
        return proposalMark * 0.35 + finalDissertationMark * 0.65;
    }

    public void setProposalMark(double proposalMark) {
        this.proposalMark = proposalMark;
    }

    public void setFinalDissertationMark(double finalDissertationMark) {
        this.finalDissertationMark = finalDissertationMark;
    }
}
