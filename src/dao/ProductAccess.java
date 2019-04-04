package dao;


import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductAccess {
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
    public void selectProduct(List<Product> list){
        String sql = "SELECT id,\n" +
                "       name,\n" +
                "       description,\n" +
                "       amount,\n" +
                "       price,\n" +
                "       type\n" +
                "  FROM Product";
        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next())
            {
                list.add(new Product(rs.getInt("id"),
                        rs.getString("name"), rs.getString("description"),
                        rs.getInt("amount"), rs.getFloat("price"), rs.getString("type")));
            }

        }catch(SQLException d){
            System.out.println(d.getMessage());
        }

    }

    public void insertProduct(Product p1){
        String sql = "INSERT INTO Product (\n" +
                "                        id,\n" +
                "                        name,\n" +
                "                        description,\n" +
                "                        amount,\n" +
                "                        price,\n" +
                "                        type\n" +
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
            pstmt.setInt(1,p1.getId());
            pstmt.setString(2,p1.getName());
            pstmt.setString(3,p1.getDescription());
            pstmt.setInt(4, p1.getAmount());
            pstmt.setFloat(5,p1.getPrice());
            pstmt.setString(6, p1.getType());
            pstmt.executeUpdate();
            System.out.println("product added");
        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
    }

    public void updateProduct(int id, Product p1){
        String sql = "UPDATE Product\n" +
                "   SET id = ?,\n" +
                "       name = ?,\n" +
                "       description = ?,\n" +
                "       amount = ?,\n" +
                "       price = ?,\n" +
                "       type = ?\n" +
                " WHERE id = ? ";
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,p1.getId());
            pstmt.setString(2,p1.getName());
            pstmt.setString(3,p1.getDescription());
            pstmt.setInt(4, p1.getAmount());
            pstmt.setFloat(5,p1.getPrice());
            pstmt.setString(6, p1.getType());
            pstmt.setInt(7, id);
            pstmt.executeUpdate();
            System.out.println("Product updated");
        }catch(SQLException d){
            System.out.println(d.getMessage());
        }

    }
    public void deleteProduct(int id){
        String sql = "DELETE FROM Product\n" +
                "      WHERE id = ?";
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            System.out.println("Product deleted");
        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
    }
    public Product productById(int id){
        String sql = "SELECT * FROM Product"+
                "      WHERE id = "+id;
        Product product = null;
        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next())
            {
                product =new Product(rs.getInt("id"),rs.getString("name"), rs.getString("description"),
                        rs.getInt("amount"), rs.getFloat("price"), rs.getString("type"));

            }

        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
        return product;
    }
    public Product productByName(String name){
        String sql = "SELECT id,\n" +
                "       name,\n" +
                "       description,\n" +
                "       amount,\n" +
                "       price,\n" +
                "       type\n" +
                "  FROM Product\n" +
                "  WHERE name = "+name;
        Product product = null;
        try(Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next())
            {
                product =new Product(rs.getInt("id"),rs.getString("name"), rs.getString("description"),
                        rs.getInt("amount"), rs.getFloat("price"), rs.getString("type"));

            }

        }catch(SQLException d){
            System.out.println(d.getMessage());
        }
        return product;
    }


    public static void main(String[] args){
        ProductAccess pa = new ProductAccess();
        List<Product> list = new ArrayList<>();
       // pa.updateProduct(2, new Product(2,"bed", "queen size", 1,10.0f,"sleep"));
        //pa.deleteProduct(1);
        pa.selectProduct(list);
        for(Product p:list){
            System.out.println(p.toString());
        }

       //pa.insertProduct(new Product("chair", "queen size", 1,230.0f,"office"));
        for(Product p:list){
            System.out.println(p.toString());
        }
        //System.out.println(pa.productById(2).getName());
        System.out.println(pa.productByName("bed"));
    }

}
