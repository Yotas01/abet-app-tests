package edu.javeriana.abetapptests.entities;


public class PerformanceIndicatorDTO {

    private Long PerformanceIndicatorId;
    private String description;
    private Double percentage;
    private Long assessmentTool;

    public PerformanceIndicatorDTO() {
    }

    public Long getPerformanceIndicatorId() {
        return PerformanceIndicatorId;
    }

    public void setPerformanceIndicatorId(Long performanceIndicatorId) {
        PerformanceIndicatorId = performanceIndicatorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Long getAssessmentTool() {
        return assessmentTool;
    }

    public void setAssessmentTool(Long assessmentTool) {
        this.assessmentTool = assessmentTool;
    }
}
