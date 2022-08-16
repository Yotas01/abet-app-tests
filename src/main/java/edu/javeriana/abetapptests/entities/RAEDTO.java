package edu.javeriana.abetapptests.entities;

import java.util.ArrayList;
import java.util.List;

public class RAEDTO {

    private Long raeId;
    private String description;
    private Long courseId;
    private List<Float> cdioList;
    private List<AssessmentToolDTO> assessmentTools;

    public RAEDTO() {
        this.assessmentTools = new ArrayList<>();
        this.cdioList = new ArrayList<>();
    }

    public List<AssessmentToolDTO> getAssessmentTools() {
        return assessmentTools;
    }

    public void setAssessmentTools(List<AssessmentToolDTO> assessmentTools) {
        this.assessmentTools = assessmentTools;
    }

    public List<Float> getCdioList() {
        return cdioList;
    }

    public void setCdioList(List<Float> cdioList) {
        this.cdioList = cdioList;
    }

    public Long getRaeId() {
        return raeId;
    }

    public void setRaeId(Long raeId) {
        this.raeId = raeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long  getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
