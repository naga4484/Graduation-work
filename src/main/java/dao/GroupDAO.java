package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bean.Group;

public class GroupDAO extends DAO {
	
	//グループの取得(一括)
	public List<Group> list_group() throws Exception {
		List<Group> list_group = new ArrayList<>();
		Group group = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from `Group`");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	group = new Group();
        	group.setGroup_id(rs.getString("group_id"));
        	group.setGroup_name(rs.getString("group_name"));
        	group.setAccept_user(rs.getString("accept_user"));
        	group.setUser_id(rs.getInt("user_id"));
        	list_group.add(group);
        }
        return list_group;
	}
	
	//グループの取得(名前検索)
	public Group search_group_name(String group_name) throws Exception { 
		Group group = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from `Group` where group_name=?");
        st.setString(1, group_name);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	group = new Group();
        	group.setGroup_id(rs.getString("group_id"));
        	group.setGroup_name(rs.getString("group_name"));
        	group.setAccept_user(rs.getString("accept_user"));
        	group.setUser_id(rs.getInt("user_id"));
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return group;
	}
	
	
	//グループの取得(id検索)
	public Group search_group_id(String group_id) throws Exception { 
		Group group = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from `Group` where group_id=?");
        st.setString(1, group_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
        	group = new Group();
        	group.setGroup_id(rs.getString("group_id"));
        	group.setGroup_name(rs.getString("group_name"));
        	group.setAccept_user(rs.getString("accept_user"));
        	group.setUser_id(rs.getInt("user_id"));
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return group;
	}
	//グループの作成
	public int group_create(String group_id,String group_name,String accept_user,int user_id) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into `Group` values(?, ?, ?, ?)");
		st.setString(1, group_id);
		st.setString(2, group_name);
		st.setString(3, accept_user);
		st.setInt(4, user_id);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	
	//ここからはグーグルチャット機能のDAO
	//グループチャット取得(グループID検索)
	public List<Group> search_group_chat_id(String group_id) throws Exception { 
		List<Group> list_group = new ArrayList<>();
		Group group = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select Gc.chat_id,Gc.group_id,Gc.user_id,Gc.chat_content,STR_TO_DATE(chat_date, '%Y年%m月%d日%H時%i分%s秒') AS chat_date,Ua.name from Group_chat AS Gc JOIN User_account AS Ua  ON Gc.user_id = Ua.user_id  where group_id=? ORDER BY STR_TO_DATE(chat_date, '%Y年%m月%d日%H時%i分%s秒') Desc");
        st.setString(1, group_id);
        ResultSet rs = st.executeQuery();
        SimpleDateFormat date = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat time = new SimpleDateFormat("kk時mm分ss秒");
        

        while (rs.next()) {
        	group = new Group();
        	group.setChat_id(Integer.parseInt(rs.getString("chat_id")));
        	group.setGroup_id(rs.getString("group_id"));
        	group.setUser_id(rs.getInt("user_id"));
        	group.setChat_content(rs.getString("chat_content"));
        	group.setChat_date(date.format(rs.getDate("chat_date")) + time.format(rs.getTime("chat_date")));
        	group.setName(rs.getString("name"));
        	list_group.add(group);
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return list_group;
	}
	//グループチャットの登録
	public int chat_insert(String group_id,String chat_content,int user_id,String chat_date) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"insert into `Group_chat`(group_id,chat_content,user_id,chat_date) values(?, ?, ?, ?)");
		st.setString(1, group_id);
		st.setString(2, chat_content);
		st.setInt(3, user_id);
		st.setString(4, chat_date);
		int line=st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
	
	public List<Group> search_group_file(String group_id) throws Exception { 
		List<Group> list_group = new ArrayList<>();
		Group group = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select S.share_id,S.share_item_path,S.group_id,S.user_id,U.name from Share AS S INNER JOIN User_account AS U ON S.user_id = U.user_id where group_id=?");
        st.setString(1, group_id);
        ResultSet rs = st.executeQuery();
        

        while (rs.next()) {
        	group = new Group();
        	group.setShare_id(Integer.parseInt(rs.getString("share_id")));
        	group.setGroup_id(rs.getString("group_id"));
        	group.setShare_item_path(rs.getString("share_item_path"));
        	group.setUser_id(Integer.parseInt(rs.getString("user_id")));
        	group.setName(rs.getString("name"));
        	list_group.add(group);
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return list_group;
	}
	//共有ファイルの登録
		public int group_file_insert(String group_id,String path,int user_id) throws Exception {
			Connection con=getConnection();

			PreparedStatement st=con.prepareStatement(
				"insert into Share(group_id,share_item_path,user_id) values(?, ?, ?)");
			st.setString(1, group_id);
			st.setString(2, path);
			st.setInt(3, user_id);
			int line=st.executeUpdate();

			st.close();
			con.close();
			return line;
		}
}