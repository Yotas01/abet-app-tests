package edu.javeriana.abetapptests.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDTO {

    private Long courseId;
    private Integer number;
    private String name;
    private Map<Integer,String> sections;
    private Map<Long,String> raes;
    private List<Float> cdioList;

    public CourseDTO() {
        this.cdioList = new ArrayList<>();
        this.sections =  new HashMap<>();
        this.raes = new HashMap<>();
    }

    public String parseToJsonRequiredFields(){
        return "{\"number\":" + this.number + ",\"name\":\"" + this.name + "\"}";
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, String> getSections() {
        return sections;
    }

    public void setSections(Map<Integer, String> sections) {
        this.sections = sections;
    }

    public Map<Long, String> getRaes() {
        return raes;
    }

    public void setRaes(Map<Long, String> raes) {
        this.raes = raes;
    }

    public List<Float> getCdioList() {
        return cdioList;
    }

    public void setCdioList(List<Float> cdioList) {
        this.cdioList = cdioList;
    }
}
