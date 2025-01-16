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
        PreparedStatement st = con.prepareStatement("SELECT * FROM Student_account");
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
        PreparedStatement st = con.prepareStatement("SELECT * FROM Teacher_account");
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
        PreparedStatement st = con.prepareStatement("SELECT * FROM Student_account WHERE student_id=? AND password=?");
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
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM Teacher_account WHERE teacher_id=? AND password=?"
        );
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
        PreparedStatement st = con.prepareStatement("SELECT * FROM Student_account WHERE student_id=?");
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

        PreparedStatement st = con.prepareStatement("INSERT INTO student_account VALUES (?, ?, ?, ?, ?, ?, ?)");
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
 // 教師登録機能
    public int teacher_registration(String teacher_id, String password, String class_id, String name,String email) throws Exception {
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("INSERT INTO teacher_account VALUES (?, ?, ?, ?, ?, ?, ?)");
        st.setString(1, teacher_id);
        st.setString(2, name);
        st.setString(3, password);
        st.setString(4, email);
        st.setString(5, class_id);
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
        PreparedStatement st = con.prepareStatement("SELECT * FROM Student_account WHERE name=?");
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
    public String getAccountTypeByEmail(String email) throws Exception {
        String accountType = null;

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT " +
            "CASE WHEN student_id IS NOT NULL THEN 'student' " +
            "WHEN teacher_id IS NOT NULL THEN 'teacher' " +
            "ELSE NULL END AS account_type " +
            "FROM (" +
            "    SELECT student_id, NULL AS teacher_id FROM Student_account " +
            "    WHERE address = ? " +
            "    UNION ALL " +
            "    SELECT NULL AS student_id, teacher_id FROM Teacher_account " +
            "    WHERE address = ?" +
            ") AS account_data"
        );
        st.setString(1, email);
        st.setString(2, email);

        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            accountType = rs.getString("account_type");
        }

        rs.close();
        st.close();
        con.close();

        return accountType; // "student", "teacher", または null
    }


    // 学生クラス検索機能
    public List<Studentaccount> student_search_class(String class_id) throws Exception {
        List<Studentaccount> studentaccountlist = new ArrayList<>();
        Studentaccount account = null;

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM Student_account WHERE class_id=?");
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

    // パスワードリセット機能関連のメソッド、メールアドレスの存在確認
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
        PreparedStatement st = con.prepareStatement(
            "INSERT INTO Verification_code (email, code, expiration_time) VALUES (?, ?, NOW() + INTERVAL 10 MINUTE)"
        );
        st.setString(1, email);
        st.setString(2, code);
        st.executeUpdate();

        st.close();
        con.close();
    }

    // 確認コードの検証
    public boolean verify_code(String email, String code) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT COUNT(*) FROM Verification_code WHERE email = ? AND code = ? AND expiration_time > NOW()"
        );
        st.setString(1, email);
        st.setString(2, code);
        ResultSet rs = st.executeQuery();

        rs.next();
        boolean isValid = rs.getInt(1) > 0; // 正しいコードがあればtrue
        st.close();
        con.close();
        return isValid;
    }

    // パスワードの更新(メールアドレスを参照 (学生))
    public void update_password_stu_mail(String email, String newPassword) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("UPDATE Student_account SET password = ? WHERE address = ?");
        st.setString(1, newPassword);
        st.setString(2, email);
        st.executeUpdate();

        st.close();
        con.close();
    }
 // パスワードの更新(メールアドレスを参照 (教師))
    public void update_password_tch_mail(String email, String newPassword) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("UPDATE Teacher_account SET password = ? WHERE address = ?");
        st.setString(1, newPassword);
        st.setString(2, email);
        st.executeUpdate();

        st.close();
        con.close();
    }
    
    // パスワードの更新(教師)
    public void update_password_stu(String student_id, String newPassword) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("UPDATE Student_account SET password = ? WHERE student_id = ?");
        st.setString(1, newPassword);
        st.setString(2, student_id);
        st.executeUpdate();

        st.close();
        con.close();
    }

    // パスワードの更新(教師)
    public void update_password_tch(String teacher_id, String newPassword) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("UPDATE Teacher_account SET password = ? WHERE teacher_id = ?");
        st.setString(1, newPassword);
        st.setString(2, teacher_id);
        st.executeUpdate();

        st.close();
        con.close();
    }

    // 確認コードを削除するメソッド
    public void delete_verification_code(String email) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("DELETE FROM Verification_code WHERE email = ?");
        st.setString(1, email);
        st.executeUpdate();

        st.close();
        con.close();
    }

    // メールアドレスが登録されているかどうかの確認
    //sql文を追加した
    public boolean isEmailRegistered(String email) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT COUNT(*) FROM (" +
            "SELECT address FROM Student_account " +
            "UNION ALL " +
            "SELECT address FROM Teacher_account" +
            ") AS All_accounts WHERE address = ?"
        );
        st.setString(1, email);
        ResultSet rs = st.executeQuery();

        rs.next();
        boolean exists = rs.getInt(1) > 0; // カウントが1以上なら存在する

        // デバッグログを追加
        System.out.println("Email check result for " + email + ": " + exists);

        st.close();
        con.close();
        return exists;
    }


    // ユーザーIDからメールアドレスを取得するメソッド
    public String getEmailByUserId(String userId, String accountKind) throws Exception {
        String email = null;
        Connection con = getConnection();

        // 学生と教師の区別をして、それぞれのテーブルからメールアドレスを取得
        String query;
        if ("学生".equals(accountKind)) {
            query = "SELECT address FROM Student_account WHERE student_id = ?";
        } else if ("教師".equals(accountKind)) {
            query = "SELECT address FROM Teacher_account WHERE teacher_id = ?";
        } else {
            return null; // これは無効なアカウントタイプの場合
        }

        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, userId);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            email = rs.getString("address");
        }

        st.close();
        con.close();
        return email;
    }

    // 学生ニックネームの変更
    public void tch_upd_nick(String teacher_id, String nickname) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("UPDATE Teacher_account SET nickname = ? WHERE teacher_id = ?");

        st.setString(1, nickname);
        st.setString(2, teacher_id);
        st.executeUpdate();

        st.close();
        con.close();
    }

    // 教師ニックネームの変更
    public void stu_upd_nick(String student_id, String nickname) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("UPDATE Student_account SET nickname = ? WHERE student_id = ?");

        st.setString(1, nickname);
        st.setString(2, student_id);
        st.executeUpdate();

        st.close();
        con.close();
    }
//  学生IDのみの検索機能
  public Studentaccount student_search_id(String student_id) throws Exception {
      Studentaccount account = null;

      Connection con = getConnection();
      PreparedStatement st = con.prepareStatement("SELECT * FROM Student_account WHERE student_id=?");
      st.setString(1, student_id);
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
//  教師IDのみの検索機能
  public Teacheraccount teacher_search_id(String teacher_id) throws Exception {
      Teacheraccount account = null;

      Connection con = getConnection();
      PreparedStatement st = con.prepareStatement("SELECT * FROM Teacher_account WHERE teacher_id=?");
      st.setString(1, teacher_id);
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
//  生徒のメールアドレス変更
  public void stu_upd_add(String student_id, String address) throws Exception {
	  Connection con = getConnection();
      PreparedStatement st = con.prepareStatement("UPDATE Student_account SET address = ? WHERE student_id = ?");
      
      st.setString(1, address);
      st.setString(2, student_id);
      st.executeUpdate();

      st.close();
      con.close();
  }
  
//  教師のメアド変更
  public void tch_upd_add(String teacher_id,String address) throws Exception{
	  Connection con = getConnection();
	  PreparedStatement st = con.prepareStatement("UPDATE Teacher_ACCOUNT SET ADdRESS = ? WHERE teacher_ID = ?");
	  
	  st.setString(1, address);
      st.setString(2, teacher_id);
      st.executeUpdate();


      st.close();
      con.close();
  }
//  メールアドレスによるメールアドレスの照合
  public boolean email_check(String email) throws Exception {
	    try (Connection con = getConnection();
	         PreparedStatement st = con.prepareStatement(
	             "SELECT address FROM ( SELECT address FROM student_account UNION SELECT address FROM teacher_account) AS all_addresses WHERE address = ?"
	         )) {
	        
	        st.setString(1, email);
	        ResultSet rs = st.executeQuery();
	        
	        boolean exists = rs.next(); // 結果があれば true, なければ false
	        
	        return exists;
	    }
	}

}