package bean;

import java.io.Serializable;

public class Calendar implements Serializable {
	private int calendar_id;
    private int user_id;
    private String calender_date;
    private String setting_date;
    private String schedule_content;

    public int getUser_id() {
        return user_id;
    }
    public int getCalendar_id() {
        return calendar_id;
    }
    public String getCalender_date() {
        return calender_date;
    }
    public String getSetting_date() {
        return setting_date;
    }
    public String getSchedule_content() {
        return schedule_content;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public void setCalendar_id(int calendar_id) {
        this.calendar_id = calendar_id;
    }
    public void setCalender_date(String calender_date) {
        this.calender_date = calender_date;
    }
    public void setSetting_date(String setting_date) {
        this.setting_date = setting_date;
    }
    public void setSchedule_content(String schedule_content) {
        this.schedule_content = schedule_content;
    }

}
