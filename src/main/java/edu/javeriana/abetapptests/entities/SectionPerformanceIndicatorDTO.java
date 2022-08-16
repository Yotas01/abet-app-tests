package edu.javeriana.abetapptests.entities;


public class SectionPerformanceIndicatorDTO {
    private Long id;
    private Long sectionAssessmentToolId;
    private Long performanceIndicatorId;
    private Integer exemplary;
    private Integer competent;
    private Integer below;
    private boolean draft;

    public SectionPerformanceIndicatorDTO() {
    }

    public SectionPerformanceIndicatorDTO(Long id, Long sectionAssessmentToolId, Long performanceIndicatorId, Integer exemplary, Integer competent, Integer below, boolean draft) {
        this.id = id;
        this.sectionAssessmentToolId = sectionAssessmentToolId;
        this.performanceIndicatorId = performanceIndicatorId;
        this.exemplary = exemplary;
        this.competent = competent;
        this.below = below;
        this.draft = draft;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSectionAssessmentToolId() {
        return sectionAssessmentToolId;
    }

    public void setSectionAssessmentToolId(Long sectionAssessmentToolId) {
        this.sectionAssessmentToolId = sectionAssessmentToolId;
    }

    public Long getPerformanceIndicatorId() {
        return performanceIndicatorId;
    }

    public void setPerformanceIndicatorId(Long performanceIndicatorId) {
        this.performanceIndicatorId = performanceIndicatorId;
    }

    public Integer getExemplary() {
        return exemplary;
    }

    public void setExemplary(Integer exemplary) {
        this.exemplary = exemplary;
    }

    public Integer getCompetent() {
        return competent;
    }

    public void setCompetent(Integer competent) {
        this.competent = competent;
    }

    public Integer getBelow() {
        return below;
    }

    public void setBelow(Integer below) {
        this.below = below;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }
}
