package bean;

import java.io.Serializable;

public class Attendance implements Serializable {
    private String attendance_id;
    private String student_id;
    private String attendance_kind_id;
    private String attendance_date;
    private String class_id;
    private String note;

    public String getAttendance_id() {
        return attendance_id;
    }

    public void setAttendance_id(String attendance_id) {
        this.attendance_id = attendance_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getAttendance_kind_id() {
        return attendance_kind_id;
    }

    public void setAttendance_kind_id(String attendance_kind_id) {
        this.attendance_kind_id = attendance_kind_id;
    }

    public String getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(String attendance_date) {
        this.attendance_date = attendance_date;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
