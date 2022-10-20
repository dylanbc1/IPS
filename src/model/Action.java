package model;

public class Action {

    private String whichAction;
    private Patient whichPatient;

    public Action(String whichAction, Patient whichPatient){
        this.whichAction = whichAction;
        this.whichPatient = whichPatient;
    }

    public String getWhichAction() {
        return whichAction;
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
