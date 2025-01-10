package bean;

public class Group implements java.io.Serializable {
	
	private String group_id;
	private String group_name;
	private String accept_user;
	private int user_id;
	private int chat_id;
	private int share_id;
	private String chat_content;
	private String chat_date;
	private String name;
	private String share_item_path;
	
	public String getGroup_id() {
		return group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public String getAccept_user() {
		return accept_user;
	}
	public int getUser_id() {
		return user_id;
	}
	public int getChat_id() {
		return chat_id;
	}
	public int getShare_id() {
		return share_id;
	}
	public String getChat_content() {
		return chat_content;
	}
	public String getChat_date() {
		return chat_date;
	}
	public String getName() {
		return name;
	}
	public String getShare_item_path() {
		return share_item_path;
	}
	
	
	public void setGroup_id(String group_id) {
		this.group_id=group_id;
	}
	public void setGroup_name(String group_name) {
		this.group_name=group_name;
	}
	public void setAccept_user(String accept_user) {
		this.accept_user=accept_user;
	}
	public void setUser_id(int user_id) {
		this.user_id=user_id;
	}
	public void setChat_id(int chat_id) {
		this.chat_id=chat_id;
	}
	public void setShare_id(int share_id) {
		this.share_id=share_id;
	}
	public void setChat_content(String chat_content) {
		this.chat_content=chat_content;
	}
	public void setChat_date(String chat_date) {
		this.chat_date=chat_date;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setShare_item_path(String share_item_path) {
		this.share_item_path=share_item_path;
	}
}