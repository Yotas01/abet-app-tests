package edu.javeriana.abetapptests.entities;

import java.util.List;

public class SectionAssessmentToolDTO {
    private Long id;
    private Integer courseNumber;
    private Integer sectionNumber;
    private Long raeId;
    private Long assessmentToolId;
    private Integer totalStudents;
    private Integer semester;
    private boolean draft;
    private List<SectionPerformanceIndicatorDTO> sectionPerformanceIndicators;

    public SectionAssessmentToolDTO() {
    }

    public SectionAssessmentToolDTO(Long id, Integer courseNumber, Integer sectionNumber, Long raeId, Long assessmentToolId, Integer totalStudents, Integer semester, boolean draft, List<SectionPerformanceIndicatorDTO> sectionPerformanceIndicators) {
        this.id = id;
        this.courseNumber = courseNumber;
        this.sectionNumber = sectionNumber;
        this.raeId = raeId;
        this.assessmentToolId = assessmentToolId;
        this.totalStudents = totalStudents;
        this.semester = semester;
        this.draft = draft;
        this.sectionPerformanceIndicators = sectionPerformanceIndicators;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Long getRaeId() {
        return raeId;
    }

    public void setRaeId(Long raeId) {
        this.raeId = raeId;
    }

    public Integer getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(Integer sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public Long getAssessmentToolId() {
        return assessmentToolId;
    }

    public void setAssessmentToolId(Long assessmentToolId) {
        this.assessmentToolId = assessmentToolId;
    }

    public Integer getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Integer totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public List<SectionPerformanceIndicatorDTO> getSectionPerformanceIndicators() {
        return sectionPerformanceIndicators;
    }

    public void setSectionPerformanceIndicators(List<SectionPerformanceIndicatorDTO> sectionPerformanceIndicators) {
        this.sectionPerformanceIndicators = sectionPerformanceIndicators;
    }
}
