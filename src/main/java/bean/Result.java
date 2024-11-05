package bean;

import java.io.Serializable;

public class Result implements Serializable {
    private String grade_id;
    private String student_id;
    private String class_id;
    private String semester;
    private int result;
    private String evaluation;
    private String comment;
    private String teacher_id;
    private String subject_id;
    private String submissions_id;
    private String grade;

    
    public String getGrade_id() {
        return grade_id;
    }
    public String getStudent_id() {
        return student_id;
    }
    public String getClass_id() {
        return class_id;
    }
    public String getSemester() {
        return semester;
    }
    public int getResult() {
        return result;
    }
    public String getEvaluation() {
        return evaluation;
    }
    public String getComment() {
        return comment;
    }
    public String getTeacher_id() {
        return teacher_id;
    }
    public String getSubject_id() {
        return subject_id;
    }
    public String getSubmissions_id() {
        return submissions_id;
    }
    public String getGrade() {
        return grade;
    }



    public void setGrade_id(String grade_id) {
        this.grade_id = grade_id;
    }
    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public void setResult(int result) {
        this.result = result;
    }
    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }
    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }
    public void setSubmissions_id(String submissions_id) {
        this.submissions_id = submissions_id;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
