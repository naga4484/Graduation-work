package bean;

public class Subject implements java.io.Serializable {
	
	private String subject_id;
	private String subject_name;
	private String class_id;
	private int total_unit;
	private String subject_color;
	
	public String getSubject_id() {
		return subject_id;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public String getClass_id() {
		return class_id;
	}
	public int getTotal_unit() {
		return total_unit;
	}
	public String getSubject_color() {
		return subject_color;
	}
	public void setSubject_id(String subject_id) {
		this.subject_id=subject_id;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name=subject_name;
	}
	public void setClass_id(String class_id) {
		this.class_id=class_id;
	}
	public void setTotal_unit(int total_unit) {
		this.total_unit=total_unit;
	}
	public void setSubject_color(String subject_color) {
		this.subject_color=subject_color;
	}
}