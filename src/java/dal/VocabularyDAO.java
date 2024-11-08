/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Vocabulary;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class VocabularyDAO extends DBContext {

    // Lấy danh sách tất cả từ vựng
    public List<Vocabulary> findAll() {
        List<Vocabulary> vocabularies = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [dbo].[Vocab]";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Vocabulary vocab = new Vocabulary();
                vocab.setId(rs.getInt("id"));
                vocab.setEnglishWord(rs.getString("englishWord"));
                vocab.setVietnameseWord(rs.getString("vietnameseWord"));
                vocab.setType(rs.getString("type"));
                vocab.setStatus(rs.getString("status"));
                vocab.setDate(rs.getTimestamp("date")); // Convert Timestamp to Date
                vocabularies.add(vocab);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vocabularies;
    }

    public Vocabulary findByEnglishWord(String englishWord) {
        Vocabulary vocabulary = null;
        String sql = "SELECT * FROM [dbo].[Vocab] WHERE englishWord = ?"; // Giả sử tên bảng là Vocabulary
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, englishWord);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                vocabulary = new Vocabulary();
                vocabulary.setId(rs.getInt("id"));
                vocabulary.setEnglishWord(rs.getString("englishWord"));
                vocabulary.setVietnameseWord(rs.getString("vietnameseWord"));
                vocabulary.setType(rs.getString("type"));
                vocabulary.setStatus(rs.getString("status"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (vocabulary.getStatus().equalsIgnoreCase("pending")) {
            vocabulary = new Vocabulary();
        }
        return vocabulary;
    }

    // Tìm kiếm từ vựng theo ID
    public Vocabulary findById(int id) {
        try {
            String sql = "SELECT *FROM [dbo].[Vocab] WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Vocabulary vocab = new Vocabulary();
                vocab.setId(rs.getInt("id"));
                vocab.setEnglishWord(rs.getString("englishWord"));
                vocab.setVietnameseWord(rs.getString("vietnameseWord"));
                vocab.setType(rs.getString("type"));
                return vocab;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm từ vựng mới vào bảng
    public void addVocabulary(Vocabulary vocab) {
        String sql = "INSERT INTO [dbo].[Vocab] (englishWord, vietnameseWord, type, status, date) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, vocab.getEnglishWord());
            pstmt.setString(2, vocab.getVietnameseWord());
            pstmt.setString(3, vocab.getType());
            pstmt.setString(4, vocab.getStatus());
            pstmt.setTimestamp(5, new java.sql.Timestamp(new Date().getTime())); // Set thời gian hiện tại
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isVocabularyDuplicate(String englishWord, String type) {
        boolean isDuplicate = false;
        try {
            // SQL query to check if both the English word and type already exist in the same record
            String sql = "SELECT COUNT(*) FROM [dbo].[Vocab] WHERE englishWord = ? AND type = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, englishWord);
            statement.setString(2, type);

            ResultSet resultSet = statement.executeQuery();

            // Check the result
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    isDuplicate = true;
                }
            }

            // Close the resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDuplicate;
    }

    // Cập nhật thông tin từ vựng
    public void updateVocabulary(Vocabulary vocab) {
        try {
            String sql = "UPDATE [dbo].[Vocab] SET [englishWord] = ?,[vietnameseWord] = ?,[type] =? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vocab.getEnglishWord());
            statement.setString(2, vocab.getVietnameseWord());
            statement.setString(3, vocab.getType());
            statement.setInt(4, vocab.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa từ vựng theo ID
    public void deleteVocabulary(int id) {
        try {
            String sql = "DELETE FROM [dbo].[Vocab] WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateStatus(int id, String status) {
        try {
            String sql = "UPDATE [dbo].[Vocab] SET status = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, status);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Vocabulary> findByStatus(String status) {
        List<Vocabulary> vocabularies = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [dbo].[Vocab] WHERE status = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            ResultSet rs = statement.executeQuery();

            // Use a while loop to iterate through all results
            while (rs.next()) {
                Vocabulary vocab = new Vocabulary();
                vocab.setId(rs.getInt("id"));
                vocab.setEnglishWord(rs.getString("englishWord"));
                vocab.setVietnameseWord(rs.getString("vietnameseWord"));
                vocab.setType(rs.getString("type"));
                vocab.setStatus(rs.getString("status"));
                vocabularies.add(vocab);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vocabularies;
    }

    public static void main(String[] args) {
        System.out.println(new VocabularyDAO().findByStatus("pending"));
    }
}
