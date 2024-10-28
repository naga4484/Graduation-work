package bean;

public class Timetable implements java.io.Serializable {
    
    private String timetable_id;
    private String subject_id;
    private String subject_color;
    private String timetable_hour;
    private String teacher_id;
    private String data;
    private String class_id;
    
    public String getTimetable_id() {
        return timetable_id;
    }
    public String getSubject_id() {
        return subject_id;
    }
    public String getSubject_color() {
        return subject_color;
    }
    public String getTimetable_hour() {
        return timetable_hour;
    }
    public String getTeacher_id() {
        return teacher_id;
    }
    public String getData() {
        return data;
    }
    public String getClass_id() {
		return class_id;
	}

    public void setTimetable_id(String timetable_id) {
        this.timetable_id = timetable_id;
    }
    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }
    public void setSubject_color(String subject_color) {
        this.subject_color = subject_color;
    }
    public void setTimetable_hour(String timetable_hour) {
        this.timetable_hour = timetable_hour;
    }
    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setClass_id(String class_id) {
		this.class_id=class_id;
	}
}
