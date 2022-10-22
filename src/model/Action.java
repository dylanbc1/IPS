package model;

public class Action {

    private String whichAction;
    private Patient whichPatient;
    private int lab;

    public Action(String whichAction, Patient whichPatient, int lab){
        this.whichAction = whichAction;
        this.whichPatient = whichPatient;
        this.lab = lab;
    }

    public String getWhichAction() {
        return whichAction;
    }

    public int getLab() {
        return lab;
    }

    public void setLab(int lab) {
        this.lab = lab;
    }

    public void setWhichAction(String whichAction) {
        this.whichAction = whichAction;
    }

    public Patient getWhichPatient() {
        return whichPatient;
    }

    public void setWhichPatient(Patient whichPatient) {
        this.whichPatient = whichPatient;
    }
}
