package dao;

        import model.StaffAccount;

        import javax.xml.transform.Result;
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;

public class StaffAccess {

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
    public void selectStaff(List<StaffAccount> staff){
        String sql = "SELECT id,\n" +
                "       name,\n" +
                "       age,\n" +
                "       address,\n" +
                "       username,\n" +
                "       password\n" +
                "  FROM StaffAccount;\n";
        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                staff.add(new StaffAccount(
                        rs.getString("name"), rs.getInt("age"),
                        rs.getString("address"), rs.getString("username"), rs.getString("password")));
            }

        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
    }

    public void insertStaff(StaffAccount st){
        String sql = "INSERT INTO StaffAccount (\n" +
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
            System.out.println("Staff member added");

        }catch(SQLException d)
        {
            System.out.println(d.getMessage());
        }
    }

    public void updateStaff(int id, StaffAccount st){
        String sql = "UPDATE StaffAccount\n" +
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
            System.out.println("Staff member updated");
        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
    }

    public void deleteStaff(int id){
        String sql = "DELETE FROM StaffAccount\n" +
                "      WHERE id = ?";
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            System.out.println("Staff member deleted");
        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
    }

    public StaffAccount StaffById(int id){
        String sql = "SELECT * FROM StaffAccount \n"+
                "       WHERE id = " +id;
        StaffAccount staff = null;
        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                staff = new StaffAccount( rs.getInt("id"),
                        rs.getString("name"), rs.getInt("age"),
                        rs.getString("address"), rs.getString("username"), rs.getString("password"));
            }

        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
        return staff;
    }

    public StaffAccount StaffByUsername(String username){
        String sql = "SELECT * FROM StaffAccount \n"+
                "       WHERE username = " +username;
        StaffAccount staff = null;
        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                staff = new StaffAccount(
                        rs.getString("name"), rs.getInt("age"),
                        rs.getString("address"), rs.getString("username"), rs.getString("password"));
            }

        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
        return staff;
    }

    public static void main(String[] args){
        StaffAccess st = new StaffAccess();
        List<StaffAccount> list = new ArrayList<>();
        //st.updateStaff(2,new StaffAccount(2, "Maria", 43, "Brasov 3", "mariabv", "1234"));
        //st.deleteStaff(1);
        st.selectStaff(list);
        for(StaffAccount s: list){
            System.out.println(s.toString());
        }
        System.out.println(st.StaffById(2).getName());
    }
}
