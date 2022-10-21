package model;

public class Patient implements Comparable<Patient> {

    private String id;
    private String name;
    private char genre;
    private String[] birthDate;
    private int priority;
    private boolean isInQueue;

    public Patient(String id, String name, char genre, String birthDate, int priority){
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.birthDate = birthDate.split("/");
        this.priority = priority;
        this.isInQueue = false;
    }

    public boolean isInQueue() {
        return isInQueue;
    }

    public void setInQueue(boolean inQueue) {
        isInQueue = inQueue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGenre() {
        return genre;
    }

    public void setGenre(char genre) {
        this.genre = genre;
    }

    public String[] getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate.split("/");
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Patient o) {
        return this.name.compareTo(o.getName());
    }
}
