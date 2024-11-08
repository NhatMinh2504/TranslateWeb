/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class AccountDAO extends DBContext {

    public Account login(String username, String email, String password) {
        try {
            String sql = "SELECT *FROM [dbo].[Account] where username= ? and email=? and password=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setEmail(rs.getString("email"));
                account.setRoleId(rs.getInt("roleId"));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> findAll() throws SQLException {

        ArrayList<Account> list = new ArrayList<>();
        String sql = "SELECT *from [dbo].[Account]";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setUsername(rs.getNString("username"));
                a.setEmail(rs.getNString("email"));
                a.setPassword(rs.getNString("password"));
                a.setRoleId(rs.getInt("roleId"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;

    }

    public Account findById(int id) {
        try {
            String sql = "SELECT *from [dbo].[Account] WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setEmail(rs.getString("email"));
                account.setRoleId(rs.getInt("roleId"));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức thêm tài khoản mới
    public void addAccount(Account account) {
        try {
            String sql = "INSERT INTO dbo.Account (username, password, email, roleId) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getEmail());
            statement.setInt(4, 2);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức cập nhật thông tin tài khoản
    public void updateAccount(Account account) {
        try {
            String sql = "UPDATE [dbo].[Account] SET username = ?, password = ?, email = ?, roleId = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getEmail());
            statement.setInt(4, account.getRoleId());
            statement.setInt(5, account.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức xóa tài khoản theo ID
    public void deleteAccount(int id) {
        try {
            String sql = "DELETE FROM [dbo].[Account] WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        for (Account a : new AccountDAO().findAll()) {
            System.out.println(a);
        }
    }

    public Account getAccountByUsernameOrEmail(String username, String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean accountExists(String username, String email) {
    try {
        String sql = "SELECT COUNT(*) FROM [dbo].[Account] WHERE username = ? OR email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, email);
        ResultSet rs = statement.executeQuery();
        
        if (rs.next()) {
            return rs.getInt(1) > 0; // Trả về true nếu có ít nhất một tài khoản tồn tại
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Trả về false nếu không có tài khoản nào
}

}
