package com.adobe.orderapp.repo;

import com.adobe.orderapp.dto.NameAndPrice;
import com.adobe.orderapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    // Projections
    // select * from products where qty = ?
    public List<Product> findByQuantity(int qty);

    // select * from products where price >= low and price <= high
    public List<Product> findByPriceBetween(double low, double high);

    // select * from products where name like %name%
    public List<Product> findByNameLike(String name);

    // custom queries can be used

    @Query("from Product where price >= :l and price <= :h")
//    @Query(value = "select * from products where price >=: l and price <= :h", nativeQuery = true)
    public List<Product> fetchByRange(@Param("l") double low,@Param("h") double high);

//    @Query("select name, price from Product")
//    public List<Object[]> getNameAndPrice();

    @Query("select new com.adobe.orderapp.dto.NameAndPrice(name, price) from Product")
    public List<NameAndPrice> getNameAndPrice();

    @Modifying // for INSERT, DELETE and UPDATE
    @Query("update Product set price = :pr where id = :id")
    public void updateProduct(@Param("id") int id, @Param("pr") double price);
}
