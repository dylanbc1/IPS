package model;

public class Patient {

    private String id;
    private String name;
    private char genre;
    private String[] birthDate;
    private int priority;

    public Patient(String id, String name, char genre, String birthDate, int priority){
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.birthDate = birthDate.split("/");
        this.priority = priority;
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
}
