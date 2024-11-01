package bean;

public class Timetable_template implements java.io.Serializable {
    
    private String template_id;
    private String subject_id;
    private String timetable_hour;
    private String teacher_id;
    private String template_name;
    
    public String getTemplate_id() {
        return template_id;
    }
    public String getSubject_id() {
        return subject_id;
    }
    public String getTimetable_hour() {
        return timetable_hour;
    }
    public String getTeacher_id() {
        return teacher_id;
    }
    public String getTemplate_name() {
        return template_name;
    }
    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }
    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }
    public void setTimetable_hour(String timetable_hour) {
        this.timetable_hour = timetable_hour;
    }
    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }
    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }
}
