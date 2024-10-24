package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Studentaccount;
import bean.Teacheraccount;

public class AccountDAO extends DAO {

    // 学生アカウント一覧取得機能
    public List<Studentaccount> student_list() throws Exception {
        List<Studentaccount> studentaccountlist = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from Student_account");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Studentaccount account = new Studentaccount();
            account.setStudent_id(rs.getString("student_id"));
            account.setName(rs.getString("name"));
            account.setClass_id(rs.getString("class_id"));
            account.setPassword(rs.getString("password"));
            account.setAddress(rs.getString("address"));
            account.setNickname(rs.getString("nickname"));
            account.setIcon(rs.getString("icon"));
            account.setAccount_kind("学生");
            studentaccountlist.add(account);
        }

        st.close();
        con.close();

        return studentaccountlist;
    }

    // 教師アカウント一覧取得機能
    public List<Teacheraccount> teacher_list() throws Exception {
        List<Teacheraccount> teacheraccountlist = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("select * from Teacher_account");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Teacheraccount account = new Teacheraccount();
            account.setTeacher_id(rs.getString("teacher_id"));
            account.setName(rs.getString("name"));
            account.setClass_id(rs.getString("class_id"));
            account.setPassword(rs.getString("password"));
            account.setAddress(rs.getString("address"));
            account.setNickname(rs.getString("nickname"));
            account.setIcon(rs.getString("icon"));
            account.setAccount_kind("教師");
            teacheraccountlist.add(account);
        }

        st.close();
        con.close();

        return teacheraccountlist;
    }

    // 学生アカウントの検索機能
    public Studentaccount student_search(String student_id, String password) throws Exception {
        Studentaccount account = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Student_account where student_id=? and password=?");
        st.setString(1, student_id);
        st.setString(2, password);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            account = new Studentaccount();
            account.setStudent_id(rs.getString("student_id"));
            account.setName(rs.getString("name"));
            account.setClass_id(rs.getString("class_id"));
            account.setPassword(rs.getString("password"));
            account.setAddress(rs.getString("address"));
            account.setNickname(rs.getString("nickname"));
            account.setIcon(rs.getString("icon"));
            account.setAccount_kind("学生");
        }

        st.close();
        con.close();
        return account;
    }

    // 教師アカウントの検索機能
    public Teacheraccount teacher_search(String teacher_id, String password) throws Exception {
        Teacheraccount account = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Teacher_account where teacher_id=? and password=?");
        st.setString(1, teacher_id);
        st.setString(2, password);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            account = new Teacheraccount();
            account.setTeacher_id(rs.getString("teacher_id"));
            account.setName(rs.getString("name"));
            account.setClass_id(rs.getString("class_id"));
            account.setPassword(rs.getString("password"));
            account.setAddress(rs.getString("address"));
            account.setNickname(rs.getString("nickname"));
            account.setIcon(rs.getString("icon"));
            account.setAccount_kind("教師");
        }

        st.close();
        con.close();
        return account;
    }

    // 学生番号による検索機能
    public List<Studentaccount> student_search_no(String student_id) throws Exception {
        List<Studentaccount> student_no_list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Student_account where student_id=?");
        st.setString(1, student_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Studentaccount account = new Studentaccount();
            account.setStudent_id(rs.getString("student_id"));
            account.setName(rs.getString("name"));
            account.setClass_id(rs.getString("class_id"));
            account.setPassword(rs.getString("password"));
            account.setAddress(rs.getString("address"));
            account.setNickname(rs.getString("nickname"));
            account.setIcon(rs.getString("icon"));
            account.setAccount_kind("学生");
            student_no_list.add(account);
        }

        st.close();
        con.close();
        return student_no_list;
    }

    // 学生登録機能
    public int student_registration(String student_id, String password, String class_id, String name) throws Exception {
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("insert into student_account values(?, ?, ?, ?, ?, ?, ?)");
        st.setString(1, student_id);
        st.setString(2, name);
        st.setString(3, class_id);
        st.setString(4, password);
        st.setString(5, "");
        st.setString(6, "");
        st.setString(7, "");
        int line = st.executeUpdate();

        st.close();
        con.close();
        return line;
    }

    // 学生氏名検索機能
    public List<Studentaccount> student_search_name(String name) throws Exception {
        List<Studentaccount> studentaccountlist = new ArrayList<>();
        Studentaccount account = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Student_account where name=?");
        st.setString(1, name);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            account = new Studentaccount();
            account.setStudent_id(rs.getString("student_id"));
            account.setName(rs.getString("name"));
            account.setClass_id(rs.getString("class_id"));
            account.setPassword(rs.getString("password"));
            account.setAddress(rs.getString("address"));
            account.setNickname(rs.getString("nickname"));
            account.setIcon(rs.getString("icon"));
            account.setAccount_kind("学生");
            studentaccountlist.add(account);
        }

        st.close();
        con.close();
        return studentaccountlist;
    }

    // 学生クラス検索機能
    public List<Studentaccount> student_search_class(String class_id) throws Exception {
        List<Studentaccount> studentaccountlist = new ArrayList<>();
        Studentaccount account = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("select * from Student_account where class_id=?");
        st.setString(1, class_id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            account = new Studentaccount();
            account.setStudent_id(rs.getString("student_id"));
            account.setName(rs.getString("name"));
            account.setClass_id(rs.getString("class_id"));
            account.setPassword(rs.getString("password"));
            account.setAddress(rs.getString("address"));
            account.setNickname(rs.getString("nickname"));
            account.setIcon(rs.getString("icon"));
            account.setAccount_kind("学生");
            studentaccountlist.add(account);

        }

        st.close();
        con.close();
        return studentaccountlist;
    }

    // パスワードリセット機能関連のメソッド
    // メールアドレスの存在確認
    public boolean email_exists(String email) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM Student_account WHERE address = ?");
        st.setString(1, email);
        ResultSet rs = st.executeQuery();

        rs.next();
        boolean exists = rs.getInt(1) > 0; // カウントが1以上なら存在する
        st.close();
        con.close();
        return exists;
    }

    // 確認コードの保存
    public void store_verification_code(String email, String code) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("INSERT INTO Verification_code (email, code, expiration_time) VALUES (?, ?, NOW() + INTERVAL 10 MINUTE)");
        st.setString(1, email);
        st.setString(2, code);
        st.executeUpdate();

        st.close();
        con.close();
    }

    // 確認コードの検証
    public boolean verify_code(String email, String code) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM Verification_code WHERE email = ? AND code = ? AND expiration_time > NOW()");
        st.setString(1, email);
        st.setString(2, code);
        ResultSet rs = st.executeQuery();

        rs.next();
        boolean isValid = rs.getInt(1) > 0; // 正しいコードがあればtrue
        st.close();
        con.close();
        return isValid;
    }

    // パスワードの更新
    public void update_password(String email, String newPassword) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("UPDATE Student_account SET password = ? WHERE address = ?");
        st.setString(1, newPassword);
        st.setString(2, email);
        st.executeUpdate();

        st.close();
        con.close();
    }
}
