package bean;

public class Class_num implements java.io.Serializable {

	private String class_id;
	private String class_num;
	private String grade;
	

	// データを取得するメソッドを定義
	public String getClass_id() {
		return class_id;
	}
	public String getClass_num() {
		return class_num;
	}
	public String getGrade() {
		return grade;
	}
	
	// データを設定するメソッドを定義
	public void setClass_id(String class_id) {
		this.class_id=class_id;
	}
	public void setClass_num(String class_num) {
		this.class_num=class_num;
	}
	public void setGrade(String grade) {
		this.grade=grade;
	}
}