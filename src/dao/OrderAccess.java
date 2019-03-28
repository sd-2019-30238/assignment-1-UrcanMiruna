package dao;

import model.Order;
import model.Product;
import model.StaffAccount;
import model.UserAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderAccess {

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

    public void selectOrders(List<Order> orders){
        String sql = "SELECT id,\n" +
                "       idUser,\n" +
                "       idStaff,\n" +
                "       idProduct,\n" +
                "       state,\n" +
                "       amountOrdered\n" +
                "  FROM [Order]\n";
        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next())
            {

                UserAccess usa = new UserAccess();
                StaffAccess sta = new StaffAccess();
                ProductAccess pr = new ProductAccess();
                orders.add(new Order(rs.getInt("id"), usa.userbyId(rs.getInt("idUser")),
                        sta.StaffById(rs.getInt("idStaff")), pr.productById(rs.getInt("idProduct")),
                        rs.getString("state"), rs.getInt("amountOrdered")));

            }

        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
    }
    public void insertOrder(Order order){
        String sql = "INSERT INTO [Order] (\n" +
                "                        id,\n" +
                "                        idUser,\n" +
                "                        idStaff,\n" +
                "                        idProduct,\n" +
                "                        state,\n" +
                "                        amountOrdered\n" +
                "                    )\n" +
                "                    VALUES (\n" +
                "                        ?,\n" +
                "                        ?,\n" +
                "                        ?,\n" +
                "                        ?,\n" +
                "                        ?,\n" +
                "                        ?\n" +
                "                    );\n";
        try(Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, order.getId());
            pstmt.setInt(2, order.getUser().getId());
            pstmt.setInt(3, order.getStaff().getId());
            pstmt.setInt(4, order.getProduct().getId());
            pstmt.setString(5, order.getState());
            pstmt.setFloat(6, order.getAmountOrdered());
        }catch (SQLException d){
            System.out.println(d.getMessage());
        }
    }

    public void updateOrder(int id, Order order){
        String sql = "UPDATE [Order]\n" +
                "   SET id = ?,\n" +
                "       idUser = ?,\n" +
                "       idStaff = ?,\n" +
                "       idProduct = ?,\n" +
                "       state = ?,\n" +
                "       amountOrdered = ?\n" +
                " WHERE id = ?";
        try(Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,order.getId());
            pstmt.setInt(2,order.getUser().getId());
            pstmt.setInt(3,order.getStaff().getId());
            pstmt.setInt(4,order.getProduct().getId());
            pstmt.setString(5, order.getState());
            pstmt.setFloat(6,order.getAmountOrdered());
            pstmt.setInt(7,id);
        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
    }
    public void updateStateOrder(Order order, String newState){
        String sql = "UPDATE [Order]\n" +
                "   SET state = ?\n" +
                " WHERE id = ? AND \n" +
                "       idUser = ? AND \n" +
                "       idStaff = ? AND \n" +
                "       idProduct = ? AND \n" +
                "       state = ? AND \n" +
                "       amountOrdered = ?;\n";
        try(Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,newState);
            pstmt.setInt(2,order.getId());
            pstmt.setInt(3, order.getUser().getId());
            pstmt.setInt(4, order.getStaff().getId());
            pstmt.setInt(5, order.getProduct().getId());
            pstmt.setString(6, order.getState());
            pstmt.setFloat(7, order.getAmountOrdered());
        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
    }

    public static void main(String[] args){
        OrderAccess ord = new OrderAccess();
        List<Order> list= new ArrayList<>();

        ord.selectOrders(list);
        for(Order o:list){
            System.out.println(o.getId()+"  "+o.getUser().getName()+"  "+o.getStaff().getName()+"  "+o.getProduct().getName());
        }

    }
}
