package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Questionnaire;
import bean.QuestionnaireAnswer;
import tool.DatabaseConnection;

public class QuestionnaireDAO {

    // アンケートと回答を保存するメソッド
    public void saveQuestionnaire(Questionnaire questionnaire, List<QuestionnaireAnswer> answers) throws SQLException {
        Connection conn = null;
        PreparedStatement psQuestionnaire = null;
        PreparedStatement psCheckAnswer = null;
        PreparedStatement psInsertAnswer = null;

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

            // 回答を保存
            String sqlCheckAnswer = "SELECT COUNT(*) FROM Questionnaire_answer WHERE QUESTIONNAIRE_ID = ? AND USER_ID = ?";
            String sqlInsertAnswer = "INSERT INTO Questionnaire_answer (QUESTIONNAIRE_ID, QUESTIONNAIRE_CONTENT, USER_ID) VALUES (?, ?, ?)";

            for (QuestionnaireAnswer answer : answers) {
                // 重複チェック
                psCheckAnswer = conn.prepareStatement(sqlCheckAnswer);
                psCheckAnswer.setString(1, questionnaire.getQuestionnaireId());
                psCheckAnswer.setInt(2, answer.getUserId());
                ResultSet rs = psCheckAnswer.executeQuery();
                rs.next();
                if (rs.getInt(1) == 0) { // 重複がない場合のみ挿入
                    psInsertAnswer = conn.prepareStatement(sqlInsertAnswer);
                    psInsertAnswer.setString(1, questionnaire.getQuestionnaireId());
                    psInsertAnswer.setString(2, answer.getQuestionnaireContent());
                    psInsertAnswer.setInt(3, answer.getUserId());
                    psInsertAnswer.executeUpdate();
                }
                rs.close();
                psCheckAnswer.close();
            }

            conn.commit(); // トランザクションのコミット
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // エラー時にロールバック
            }
            System.out.println("エラーが発生しました: " + e.getMessage());
            throw e;
        } finally {
            // リソースの解放
            if (psQuestionnaire != null) psQuestionnaire.close();
            if (psCheckAnswer != null) psCheckAnswer.close();
            if (psInsertAnswer != null) psInsertAnswer.close();
            if (conn != null) conn.close();
        }
    }

    // 全てのアンケートを取得するメソッド
    public List<Questionnaire> getAllQuestionnaires() throws SQLException {
        List<Questionnaire> questionnaireList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();

            // SQL クエリ
            String sql = "SELECT QUESTIONNAIRE_ID, TITLE, QUESTIONNAIRE, USER_ID, QUESTIONNAIRE_DATE FROM Questionnaire";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            System.out.println("SQL 実行完了: " + sql);

            // 結果をリストに格納
            while (rs.next()) {
                Questionnaire questionnaire = new Questionnaire();
                questionnaire.setQuestionnaireId(rs.getString("QUESTIONNAIRE_ID"));
                questionnaire.setTitle(rs.getString("TITLE"));
                questionnaire.setQuestionnaire(rs.getString("QUESTIONNAIRE"));
                questionnaire.setUserId(rs.getInt("USER_ID"));
                questionnaire.setQuestionnaireDate(rs.getDate("QUESTIONNAIRE_DATE"));
                questionnaireList.add(questionnaire);

                // デバッグ: データを確認
                System.out.println("取得データ: ID=" + rs.getString("QUESTIONNAIRE_ID") + 
                                   ", タイトル=" + rs.getString("TITLE") +
                                   ", 作成日=" + rs.getDate("QUESTIONNAIRE_DATE"));
            }
        } catch (SQLException e) {
            System.out.println("SQLエラー: " + e.getMessage());
            throw new SQLException("データ取得中にエラーが発生しました: " + e.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        System.out.println("最終的な取得データ数: " + questionnaireList.size());
        return questionnaireList;
    }

    // 指定したアンケートIDの詳細を取得するメソッド
    public Questionnaire getQuestionnaireById(String questionnaireId) throws SQLException {
        Questionnaire questionnaire = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();

            // 特定のアンケート詳細を取得するSQL
            String sql = "SELECT QUESTIONNAIRE_ID, TITLE, QUESTIONNAIRE, USER_ID, QUESTIONNAIRE_DATE FROM Questionnaire WHERE QUESTIONNAIRE_ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, questionnaireId);
            rs = ps.executeQuery();

            if (rs.next()) {
                questionnaire = new Questionnaire();
                questionnaire.setQuestionnaireId(rs.getString("QUESTIONNAIRE_ID"));
                questionnaire.setTitle(rs.getString("TITLE"));
                questionnaire.setQuestionnaire(rs.getString("QUESTIONNAIRE"));
                questionnaire.setUserId(rs.getInt("USER_ID"));
                questionnaire.setQuestionnaireDate(rs.getDate("QUESTIONNAIRE_DATE"));
            }
        } catch (SQLException e) {
            System.out.println("SQLエラー: " + e.getMessage());
            throw new SQLException("指定されたアンケートの取得中にエラーが発生しました。");
        } finally {
            // リソースの解放
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return questionnaire;
    }
}
