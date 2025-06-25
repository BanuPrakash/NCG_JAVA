package com.adobe.prj.dao;

import com.adobe.prj.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public Product getProduct(int id) {
        return null;
    }
}
