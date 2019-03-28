package dao;


import model.StaffAccount;
import model.UserAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAccess {

    private Connection connect(){
        String url = "jdbc:sqlite:C:/Users/Miruna Urcan/Desktop/SQLite/deals.db";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
        return conn;
    }
    public void selectUser(List<UserAccount> staff){
        String sql = "SELECT id,\n" +
                "       name,\n" +
                "       age,\n" +
                "       address,\n" +
                "       username,\n" +
                "       password\n" +
                "  FROM UserAccount;\n";
        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                staff.add(new UserAccount(
                        rs.getString("name"), rs.getInt("age"),
                        rs.getString("address"), rs.getString("username"), rs.getString("password")));
            }

        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
    }

    public void insertUser(UserAccount st){
        String sql = "INSERT INTO UserAccount (\n" +
                "                             id,\n" +
                "                             name,\n" +
                "                             age,\n" +
                "                             address,\n" +
                "                             username,\n" +
                "                             password\n" +
                "                         )\n" +
                "                         VALUES (\n" +
                "                             ?,\n" +
                "                             ?,\n" +
                "                             ?,\n" +
                "                             ?,\n" +
                "                             ?,\n" +
                "                             ?\n" +
                "                         );\n";
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,st.getId());
            pstmt.setString(2,st.getName());
            pstmt.setInt(3, st.getAge());
            pstmt.setString(4, st.getAddress());
            pstmt.setString(5, st.getUsername());
            pstmt.setString(6, st.getPassword());
            pstmt.executeUpdate();
            System.out.println("User member added");

        }catch(SQLException d)
        {
            System.out.println(d.getMessage());
        }
    }

    public void updateUser(int id, UserAccount st){
        String sql = "UPDATE UserAccount\n" +
                "   SET id = ?,\n" +
                "       name = ?,\n" +
                "       age = ?,\n" +
                "       address = ?,\n" +
                "       username = ?,\n" +
                "       password = ?\n" +
                " WHERE id = ?";
        try(Connection con = this.connect();
            PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1,st.getId());
            pstmt.setString(2,st.getName());
            pstmt.setInt(3, st.getAge());
            pstmt.setString(4, st.getAddress());
            pstmt.setString(5, st.getUsername());
            pstmt.setString(6, st.getPassword());
            pstmt.setInt(7, id);
            pstmt.executeUpdate();
            System.out.println("User member updated");
        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
    }

    public void deleteUser(int id){
        String sql = "DELETE FROM UserAccount\n" +
                "      WHERE id = ?";
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            System.out.println("User member deleted");
        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
    }
    public UserAccount userbyId(int id){
        String sql = "SELECT * FROM UserAccount\n"+
                "    WHERE id = " + id;
        UserAccount user=null;
        try(Connection conn = this.connect();
           Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                user =new UserAccount(rs.getInt("id"),
                        rs.getString("name"), rs.getInt("age"),
                        rs.getString("address"), rs.getString("username"), rs.getString("password"));
            }

        }catch(SQLException d) {
            System.out.println(d.getMessage());
        }
        return user;
    }
    public UserAccount userbyUsername(String id){
        UserAccount user = null;
        UserAccess userAccess = new UserAccess();
        List<UserAccount> list = new ArrayList<>();
        userAccess.selectUser(list);
        for(UserAccount userAccount:list){
            if(userAccount.getUsername().equals(id)){
                user=userAccount;
            }
        }
        return user;
    }



    public static void main(String[] args){
       UserAccess user = new UserAccess();
       List<UserAccount> list = new ArrayList<>();
       user.selectUser(list);
      // user.insertUser(new UserAccount("Maria", 47, "Cluj", "sorin@yahoo.com", "anca"));
        //user.updateUser(2, new UserAccount(2, "ANca", 47, "Cj 45", "JustAnca", "7894"));
        //user.deleteUser(2);

       user.selectUser(list);
        System.out.println(user.userbyUsername("anca@yahoo.com").toString());
        //System.out.println(user.userbyId(2).getName());

    }
}
