package edu.javeriana.abetapptests.entities;

import java.util.ArrayList;
import java.util.List;

public class SectionReview {
    private Integer courseNumber;
    private Integer sectionNumber;
    private Integer semester;
    private List<SectionAssessmentToolDTO> sectionAssessmentTools;

    public SectionReview() {
        this.sectionAssessmentTools = new ArrayList<>();
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Integer getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(Integer sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public List<SectionAssessmentToolDTO> getSectionAssessmentTools() {
        return sectionAssessmentTools;
    }

    public void setSectionAssessmentTools(List<SectionAssessmentToolDTO> sectionAssessmentTools) {
        this.sectionAssessmentTools = sectionAssessmentTools;
    }

    public void addSectionAssessmentTool(SectionAssessmentToolDTO sectionAssessmentTool){
        sectionAssessmentTools.add(sectionAssessmentTool);
    }

    public void removeSectionAssessmentTool(SectionAssessmentToolDTO sectionAssessmentTool){
        sectionAssessmentTools.remove(sectionAssessmentTool);
    }
}
