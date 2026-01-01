package org.heriberto.app.repository.implementations;

import org.heriberto.app.data.ContextDB;
import org.heriberto.app.models.User;
import org.heriberto.app.repository.interfaces.IUserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository<User> {

    @Override
    public List<User> allUser() {

        List<User> users = new ArrayList<>();
        String query = "select * from users";

        try(
                Connection connection = ContextDB.getConnection();
                PreparedStatement pStmt = connection.prepareStatement(query);
                ResultSet rs = pStmt.executeQuery();
                ) {
            while(rs.next()){
                User user = querySelection(rs);
                users.add(user);
            }
        } catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User byId(Long id) {

        String query = "select * from users where id_user=?";
        User user = null;
        try (
                Connection connection = ContextDB.getConnection();
                PreparedStatement pStmt = connection.prepareStatement(query)
                ){
            pStmt.setLong(1, id);
            try (ResultSet rs = pStmt.executeQuery()){
                if(rs.next()){
                    user = querySelection(rs);
                }

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void createUser(User user) {

        String query;
        if (user.getId() != null && user.getId() > 0) {
            query = "update users set username=?, password=?, email=?     where id_user=?";
        } else {
            query = "insert into users(username, password, email) values(?, ?, ?)";
        }

        try (
                Connection connection = ContextDB.getConnection();
                PreparedStatement pStmt = connection.prepareStatement(query);
                ){
            pStmt.setString(1, user.getUsername());
            pStmt.setString(2, user.getPassword());
            pStmt.setString(3, user.getEmail());

            if (user.getId() != null && user.getId() > 0) {
                pStmt.setLong(4, user.getId());
            }
            pStmt.executeUpdate();
        } catch (SQLException sql){
            throw new RuntimeException(sql);
        }
    }

    @Override
    public void remove(Long id) {
        String query = "delete from users where id_user=?";
        try (
                Connection connection = ContextDB.getConnection();
                PreparedStatement pStmt  = connection.prepareStatement(query)
                ){
            pStmt.setLong(1, id);
            pStmt.executeUpdate();
        } catch (SQLException sql){
            throw new RuntimeException(sql);
        }
    }

    private User querySelection(ResultSet rs) throws SQLException{
        User user = new User();
        user.setId(rs.getLong("id_user"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));

        return user;
    }
}
