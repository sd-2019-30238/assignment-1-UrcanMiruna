package dao;


import com.sun.deploy.util.OrderedHashSet;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.sun.scenario.effect.impl.prism.PrReflectionPeer;
import model.Order;
import model.Product;
import model.UserAccount;

import javax.jws.soap.SOAPBinding;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderAccess {
    private Connection connect(){
        String url = "jdbc:sqlite:C:/Users/Miruna Urcan/Desktop/SQLite/deals.db";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);

        }catch (SQLException d){
            System.out.println(d.getMessage());
        }
        return conn;
    }

    public void selectOrders(List<Order> orders){
        String sql = "SELECT id,\n" +
                "       username,\n" +
                "       idProduct,\n" +
                "       state,\n" +
                "       amountOrdered\n" +
                "  FROM Orders;\n";
        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){

                UserAccess userAccess = new UserAccess();
                UserAccount user = userAccess.userbyUsername(rs.getString("username"));
                ProductAccess productAccess = new ProductAccess();
                Product product= productAccess.productById(rs.getInt("id"));
                orders.add(new Order(user, product, rs.getString("state"), rs.getInt("amountOrdered")));
            }

        }catch (SQLException d){
            System.out.println(d.getMessage());
        }
    }

    public void insertOrder(Order order){
        String sql = "INSERT INTO Orders (\n" +
                "                       id,\n" +
                "                       username,\n" +
                "                       idProduct,\n" +
                "                       state,\n" +
                "                       amountOrdered\n" +
                "                   )\n" +
                "                   VALUES (\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?\n" +
                "                   );\n";
        try(Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,order.getId());
            pstmt.setString(2, order.getUser().getUsername());
            pstmt.setInt(3, order.getProduct().getId());
            pstmt.setString(4, order.getState());
            pstmt.setInt(5, order.getAmountOrdered());
            pstmt.executeUpdate();
            System.out.println("order added");

        }catch (SQLException d){
            System.out.println(d.getMessage());
        }
    }

    public void updateOrder(int id, Order order){
        String sql = "UPDATE Orders\n" +
                "   SET id = ?,\n" +
                "       username = ?,\n" +
                "       idProduct = ?,\n" +
                "       state = ?,\n" +
                "       amountOrdered = ?\n" +
                " WHERE id = '?";
        try(Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,order.getId());
            pstmt.setString(2,order.getUser().getUsername());
            pstmt.setInt(3,order.getProduct().getId());
            pstmt.setString(4, order.getState());
            pstmt.setInt(5, order.getAmountOrdered());
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
        }catch (SQLException d){
            System.out.println(d.getMessage());
        }
    }

    public void selectOrdersByUser(List<Order> orders, UserAccount user){
        String sql = "SELECT id,\\n\" +\n" +
                "                \"       username,\\n\" +\n" +
                "                \"       idProduct,\\n\" +\n" +
                "                \"       state,\\n\" +\n" +
                "                \"       amountOrdered\\n\" +\n" +
                "                \"  FROM Orders;\\n" +
                "                   WHERE username = " +user.getUsername();
        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){

                UserAccess userAccess = new UserAccess();
                UserAccount user1 = userAccess.userbyUsername(rs.getString("username"));
                ProductAccess productAccess = new ProductAccess();
                Product product= productAccess.productById(rs.getInt("idProduct"));
                orders.add(new Order(user, product, rs.getString("state"), rs.getInt("amountOrdered")));
            }

        }catch (SQLException d){
            System.out.println(d.getMessage());
        }

    }
    public static void main(String args[]){
        OrderAccess orderAccess = new OrderAccess();
        List<Order> list = new ArrayList<>();
        orderAccess.selectOrders(list);
        for(Order ord: list){
            System.out.println(ord.toString());
        }

        UserAccess userAccess = new UserAccess();
        List<UserAccount> userAccounts = new ArrayList<>();
        userAccess.selectUser(userAccounts);

        ProductAccess productAccess = new ProductAccess();
        List<Product> products = new ArrayList<>();
        productAccess.selectProduct(products);


        orderAccess.insertOrder(new Order(userAccounts.get(2), products.get(2), "delivering", 2));

        orderAccess.selectOrders(list);
        for(Order ord: list){
            System.out.println(ord.toString());
        }
    }

}

