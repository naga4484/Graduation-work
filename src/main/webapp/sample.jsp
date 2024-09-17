<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,javax.naming.*,javax.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DBの接続確認</title>
</head>
<body>
<%
Context context = new InitialContext();	
DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/test_db");
try(Connection db = ds.getConnection()){
	//SELECT
	PreparedStatement ps = db.prepareStatement("SELECT * FROM sample");
	ResultSet rs = ps.executeQuery();
	System.out.println(rs);
	while (rs.next()){
%>
	<tr>
		<td><%=rs.getInt("sample_id") %></td>
	</tr>
<%	
	}
}
%>

データベースへの接続に成功しました。うんち
</body>
</html>