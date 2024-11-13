package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Group;

public class GroupDAO extends DAO {
	
	
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
}