package bean;

public class Submissions implements java.io.Serializable {

    private int submissions_id;   // SUBMISSIONS_ID
    private String name;            // NAME
    private String save_path;        // SAVE_PATH
    private String create_date;      // CREATE_DATE (Stringとして保持)
    private String class_id;         // CLASS_ID
    private String subject_id;       // SUBJECT_ID
    private String student_id;

    // データを取得するメソッドを定義
    public int getSubmissions_id() {
        return submissions_id;
    }

    public String getName() {
        return name;
    }

    public String getSave_path() {
        return save_path;
    }

    public String getCreate_date() {
        return create_date;
    }

    public String getClass_id() {
        return class_id;
    }

    public String getSubject_id() {
        return subject_id;
    }
    public String getStudent_id() {
		return student_id;
	}

    // データを設定するメソッドを定義
    public void setSubmissions_id(int submissions_id) {
        this.submissions_id = submissions_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSave_path(String save_path) {
        this.save_path = save_path;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }
    public void setStudent_id(String student_id) {
		this.student_id=student_id;
	}
}
