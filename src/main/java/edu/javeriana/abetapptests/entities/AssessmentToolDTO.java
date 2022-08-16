package edu.javeriana.abetapptests.entities;

import java.util.ArrayList;
import java.util.List;

public class AssessmentToolDTO {

    private Long assessmentToolId;
    private String description;
    private Double value;
    private String rae;
    private List<PerformanceIndicatorDTO> performanceIndicators;

    public AssessmentToolDTO() {
        this.performanceIndicators = new ArrayList<>();
    }

    public Long getAssessmentToolId() {
        return assessmentToolId;
    }

    public void setAssessmentToolId(Long assessmentToolId) {
        this.assessmentToolId = assessmentToolId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getRae() {
        return rae;
    }

    public void setRae(String rae) {
        this.rae = rae;
    }

    public List<PerformanceIndicatorDTO> getPerformanceIndicators() {
        return performanceIndicators;
    }

    public void setPerformanceIndicators(List<PerformanceIndicatorDTO> performanceIndicators) {
        this.performanceIndicators = performanceIndicators;
    }
}
