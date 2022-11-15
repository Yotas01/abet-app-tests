package edu.javeriana.abetapptests.entities;

import java.util.List;

public class CDIODTO {
    private String description;
    private Float number;
    private List<Integer> outcomes;
    private List<String> courses;

    public String createJsonRequiredFields(){
        return "{\"number\":"+this.number+",\"description\":\""+this.description+"\"}";
    }

    public CDIODTO() {
    }

    public Float getNumber() {
        return number;
    }

    public void setNumber(Float number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<Integer> outcomes) {
        this.outcomes = outcomes;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
