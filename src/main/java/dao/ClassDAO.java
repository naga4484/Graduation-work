package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Class_num;

public class ClassDAO extends DAO {
	
	
	//クラス一覧の取得
	public List<Class_num> getallclass() 
	throws Exception {
		List<Class_num> classList = new ArrayList<>(); 
        Class_num class_num;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Class_num");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            class_num = new Class_num();
            class_num.setClass_id(rs.getString("class_id"));
            class_num.setClass_num(rs.getString("class_num"));
            class_num.setGrade(rs.getString("grade"));
            classList.add(class_num); 
        }

        rs.close(); 
        st.close();
        con.close(); 
        
        return classList;
	}
}

    
    