package com.adobe.prj.dao;

import com.adobe.prj.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbcImpl implements ProductDao{


    @Override
    public void addProduct(Product product) throws PersistenceException {
        Connection con = null;
        String SQL = "INSERT INTO products (id, name, price) VALUES (0, ?, ?)";
        try {
            con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("unable to add product!!!", e);
        } finally {
            DBUtil.closeConnection(con);
        }
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Connection con = null;
        String SQL = "SELECT id, name, price FROM products";
        try {
            con = DBUtil.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")));
            }
        } catch (SQLException e) {
//            throw new FetchException("unable to get products!!!", e);
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(con);
        }
        return products;
    }

    @Override
    public Product getProduct(int id) {
        return null;
    }
}
