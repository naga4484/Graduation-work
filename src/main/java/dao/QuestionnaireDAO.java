package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Questionnaire;
import bean.QuestionnaireOption;
import tool.DatabaseConnection;

public class QuestionnaireDAO {

    // アンケートと選択肢を保存するメソッド
    public void saveQuestionnaireWithOptions(Questionnaire questionnaire, List<String> options) throws SQLException {
        Connection conn = null;
        PreparedStatement psQuestionnaire = null;
        PreparedStatement psOption = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // トランザクションの開始

            // アンケートを保存
            String sqlQuestionnaire = "INSERT INTO Questionnaire (QUESTIONNAIRE_ID, TITLE, QUESTIONNAIRE, USER_ID, QUESTIONNAIRE_DATE) " +
                                      "VALUES (?, ?, ?, ?, ?)";
            psQuestionnaire = conn.prepareStatement(sqlQuestionnaire);
            psQuestionnaire.setString(1, questionnaire.getQuestionnaireId());
            psQuestionnaire.setString(2, questionnaire.getTitle());
            psQuestionnaire.setString(3, questionnaire.getQuestionnaire());
            psQuestionnaire.setInt(4, questionnaire.getUserId());
            psQuestionnaire.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            psQuestionnaire.executeUpdate();

            // 選択肢を保存
            String sqlOption = "INSERT INTO Questionnaire_option (QUESTIONNAIRE_ID, OPTION_NUMBER, OPTION_CONTENT) " +
                               "VALUES (?, ?, ?)";
            psOption = conn.prepareStatement(sqlOption);

            int optionNumber = 1;
            for (String option : options) {
                psOption.setString(1, questionnaire.getQuestionnaireId());
                psOption.setInt(2, optionNumber++);
                psOption.setString(3, option);
                psOption.addBatch();
            }
            psOption.executeBatch();

            conn.commit(); // トランザクションのコミット
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // エラー時にロールバック
            }
            System.out.println("エラーが発生しました: " + e.getMessage());
            throw e;
        } finally {
            if (psQuestionnaire != null) psQuestionnaire.close();
            if (psOption != null) psOption.close();
            if (conn != null) conn.close();
        }
    }

    // アンケートの選択肢を取得するメソッド
    public List<QuestionnaireOption> getQuestionnaireOptions(String questionnaireId) throws SQLException {
        List<QuestionnaireOption> options = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "SELECT OPTION_NUMBER, OPTION_CONTENT " +
                         "FROM Questionnaire_option WHERE QUESTIONNAIRE_ID = ? ORDER BY OPTION_NUMBER";
            ps = conn.prepareStatement(sql);
            ps.setString(1, questionnaireId);
            rs = ps.executeQuery();

            while (rs.next()) {
                QuestionnaireOption option = new QuestionnaireOption();
                option.setOptionNumber(rs.getInt("OPTION_NUMBER"));
                option.setOptionContent(rs.getString("OPTION_CONTENT"));
                options.add(option);
            }
        } catch (SQLException e) {
            System.out.println("SQLエラー: " + e.getMessage());
            throw new SQLException("選択肢の取得中にエラーが発生しました");
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return options;
    }

    // アンケートタイトルを取得するメソッド
    public String getQuestionnaireTitle(String questionnaireId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String title = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "SELECT TITLE FROM Questionnaire WHERE QUESTIONNAIRE_ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, questionnaireId);
            rs = ps.executeQuery();

            if (rs.next()) {
                title = rs.getString("TITLE");
            }
        } catch (SQLException e) {
            System.out.println("SQLエラー: " + e.getMessage());
            throw new SQLException("アンケートタイトルの取得中にエラーが発生しました");
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return title;
    }

    // アンケート内容を取得するメソッド
    public String getQuestionnaireContent(String questionnaireId) throws SQLException {
        String content = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT QUESTIONNAIRE FROM Questionnaire WHERE QUESTIONNAIRE_ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, questionnaireId);
            rs = ps.executeQuery();

            if (rs.next()) {
                content = rs.getString("QUESTIONNAIRE");
            }
        } catch (SQLException e) {
            System.out.println("SQLエラー: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return content;
    }

    // 全てのアンケートを取得するメソッド
    public List<Questionnaire> getAllQuestionnaires() throws SQLException {
        List<Questionnaire> questionnaireList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "SELECT QUESTIONNAIRE_ID, TITLE, QUESTIONNAIRE, USER_ID, QUESTIONNAIRE_DATE FROM Questionnaire";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Questionnaire questionnaire = new Questionnaire();
                questionnaire.setQuestionnaireId(rs.getString("QUESTIONNAIRE_ID"));
                questionnaire.setTitle(rs.getString("TITLE"));
                questionnaire.setQuestionnaire(rs.getString("QUESTIONNAIRE"));
                questionnaire.setUserId(rs.getInt("USER_ID"));
                questionnaire.setQuestionnaireDate(rs.getDate("QUESTIONNAIRE_DATE"));
                questionnaireList.add(questionnaire);
            }

            if (questionnaireList.isEmpty()) {
                System.out.println("アンケートデータが存在しません");
            } else {
                System.out.println("取得したアンケート件数: " + questionnaireList.size());
            }
        } catch (SQLException e) {
            System.out.println("SQLエラー: " + e.getMessage());
            throw new SQLException("データ取得中にエラーが発生しました");
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return questionnaireList;
    }

    // 全アンケートの選択肢を取得するメソッド
    public Map<String, List<QuestionnaireOption>> getAllQuestionnaireOptions() throws SQLException {
        Map<String, List<QuestionnaireOption>> optionsMap = new HashMap<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "SELECT QUESTIONNAIRE_ID, OPTION_NUMBER, OPTION_CONTENT FROM Questionnaire_option";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String questionnaireId = rs.getString("QUESTIONNAIRE_ID");
                QuestionnaireOption option = new QuestionnaireOption();
                option.setOptionNumber(rs.getInt("OPTION_NUMBER"));
                option.setOptionContent(rs.getString("OPTION_CONTENT"));

                optionsMap.computeIfAbsent(questionnaireId, k -> new ArrayList<>()).add(option);
            }
        } catch (SQLException e) {
            System.out.println("SQLエラー: " + e.getMessage());
            throw new SQLException("全選択肢の取得中にエラーが発生しました");
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return optionsMap;
    }

    // 指定されたアンケートIDがユーザーによって回答済みかをチェックするメソッド
    public boolean isAnswered(String questionnaireId, int userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isAnswered = false;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT 1 FROM Questionnaire_answer WHERE QUESTIONNAIRE_ID = ? AND USER_ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, questionnaireId);
            ps.setInt(2, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                isAnswered = true; // 回答済み
            }
        } catch (SQLException e) {
            System.out.println("SQLエラー: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return isAnswered;
    }


    // 回答を保存するメソッド
    public void saveAnswer(String questionnaireId, int userId, int selectedOption) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "INSERT INTO Questionnaire_answer (QUESTIONNAIRE_ID, OPTION_NUMBER, USER_ID) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, questionnaireId);
            ps.setInt(2, selectedOption);
            ps.setInt(3, userId);
            ps.executeUpdate();

            // デバッグ用ログ
            System.out.println("回答が保存されました: [QUESTIONNAIRE_ID=" + questionnaireId + ", OPTION_NUMBER=" + selectedOption + ", USER_ID=" + userId + "]");
        } catch (SQLException e) {
            System.out.println("SQLエラー: " + e.getMessage());
            throw new SQLException("回答の保存中にエラーが発生しました");
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        
        
    }
 // 全てのアンケートを取得し、回答済み情報を追加するメソッド
    public List<Questionnaire> getAllQuestionnairesWithAnswers(int userId) throws SQLException {
        List<Questionnaire> questionnaireList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "SELECT q.QUESTIONNAIRE_ID, q.TITLE, q.QUESTIONNAIRE, q.USER_ID, q.QUESTIONNAIRE_DATE, " +
                         "       CASE WHEN qa.USER_ID IS NOT NULL THEN 1 ELSE 0 END AS IS_ANSWERED " +
                         "FROM Questionnaire q " +
                         "LEFT JOIN Questionnaire_answer qa ON q.QUESTIONNAIRE_ID = qa.QUESTIONNAIRE_ID AND qa.USER_ID = ? " +
                         "WHERE q.QUESTIONNAIRE_ID NOT IN (SELECT QUESTIONNAIRE_ID FROM User_Hidden_Questionnaires WHERE USER_ID = ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Questionnaire questionnaire = new Questionnaire();
                questionnaire.setQuestionnaireId(rs.getString("QUESTIONNAIRE_ID"));
                questionnaire.setTitle(rs.getString("TITLE"));
                questionnaire.setQuestionnaire(rs.getString("QUESTIONNAIRE"));
                questionnaire.setUserId(rs.getInt("USER_ID"));
                questionnaire.setQuestionnaireDate(rs.getDate("QUESTIONNAIRE_DATE"));
                questionnaire.setAnswered(rs.getInt("IS_ANSWERED") == 1);

                questionnaireList.add(questionnaire);
            }

            if (questionnaireList.isEmpty()) {
                System.out.println("アンケートデータが存在しません");
            }
        } catch (SQLException e) {
            System.out.println("SQLエラー: " + e.getMessage());
            throw new SQLException("データ取得中にエラーが発生しました");
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return questionnaireList;

    }
    public void hideQuestionnaireForUser(int userId, String questionnaireId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "INSERT INTO User_Hidden_Questionnaires (USER_ID, QUESTIONNAIRE_ID) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, questionnaireId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLエラー: " + e.getMessage());
            throw new SQLException("非表示設定の保存中にエラーが発生しました");
        } finally {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
    }

    public List<Questionnaire> getFilteredQuestionnaires(int userId) throws SQLException {
        List<Questionnaire> questionnaireList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "SELECT q.QUESTIONNAIRE_ID, q.TITLE, q.QUESTIONNAIRE, q.USER_ID, q.QUESTIONNAIRE_DATE, " +
                         "       CASE WHEN qa.USER_ID IS NOT NULL THEN 1 ELSE 0 END AS IS_ANSWERED " +
                         "FROM Questionnaire q " +
                         "LEFT JOIN Questionnaire_answer qa ON q.QUESTIONNAIRE_ID = qa.QUESTIONNAIRE_ID AND qa.USER_ID = ? " +
                         "WHERE q.QUESTIONNAIRE_ID NOT IN (SELECT QUESTIONNAIRE_ID FROM User_Hidden_Questionnaires WHERE USER_ID = ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Questionnaire questionnaire = new Questionnaire();
                questionnaire.setQuestionnaireId(rs.getString("QUESTIONNAIRE_ID"));
                questionnaire.setTitle(rs.getString("TITLE"));
                questionnaire.setQuestionnaire(rs.getString("QUESTIONNAIRE"));
                questionnaire.setUserId(rs.getInt("USER_ID"));
                questionnaire.setQuestionnaireDate(rs.getDate("QUESTIONNAIRE_DATE"));
                questionnaire.setAnswered(rs.getInt("IS_ANSWERED") == 1);

                questionnaireList.add(questionnaire);
            }
        } catch (SQLException e) {
            System.out.println("SQLエラー: " + e.getMessage());
            throw new SQLException("アンケート取得中にエラーが発生しました");
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return questionnaireList;
    }
    
 // アンケート結果を取得するメソッド
    public List<Integer> getAnswerCounts(String questionnaireId) throws SQLException {
        List<Integer> answerCounts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();

            String sql = "SELECT o.OPTION_NUMBER, COUNT(a.OPTION_NUMBER) AS COUNT " +
                         "FROM Questionnaire_option o " +
                         "LEFT JOIN Questionnaire_answer a " +
                         "ON o.QUESTIONNAIRE_ID = a.QUESTIONNAIRE_ID AND o.OPTION_NUMBER = a.OPTION_NUMBER " +
                         "WHERE o.QUESTIONNAIRE_ID = ? " +
                         "GROUP BY o.OPTION_NUMBER " +
                         "ORDER BY o.OPTION_NUMBER";
            ps = conn.prepareStatement(sql);
            ps.setString(1, questionnaireId);
            rs = ps.executeQuery();

            while (rs.next()) {
                answerCounts.add(rs.getInt("COUNT")); // 結果が0の場合もCOUNT(0)で値を返す
            }

        } catch (SQLException e) {
            System.out.println("SQLエラー: " + e.getMessage());
            throw new SQLException("アンケート結果の取得中にエラーが発生しました");
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return answerCounts;
    }





}
