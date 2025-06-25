package com.adobe.prj.web;

import com.adobe.prj.dao.PersistenceException;
import com.adobe.prj.dao.ProductDao;
import com.adobe.prj.dao.ProductDaoJdbcImpl;
import com.adobe.prj.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// http://localhost:8080/products

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    // GET
    // req and res objects are injected by the servlet engine --> DI
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter(); // character stream to client [browser]
        resp.setContentType("text/html"); // MIME "image/gif", "application/pdf"
        ProductDao productDao = new ProductDaoJdbcImpl();
        List<Product> products = productDao.getProducts();
        out.print("<html>");
        out.print("<body>");
        out.print("<table>");
        out.print("<tr>");
            out.print("<th>ID</th><th>Name</th><th>Price</th>");
        out.print("</tr>");
        for(Product p : products) {
            out.print("<tr>");
                out.print("<td>" + p.getId() + " </td>");
                out.print("<td>" + p.getName() + " </td>");
                out.print("<td>" + p.getPrice() + " </td>");
            out.print("</tr>");
        }
        out.print("</table></body></html>");
    }

    // POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        product.setName(req.getParameter("name"));
        product.setPrice(Double.parseDouble(req.getParameter("price")));

        ProductDao productDao = new ProductDaoJdbcImpl();

        try {
            productDao.addProduct(product);
            resp.sendRedirect("index.jsp?msg=Product added!!!");
        } catch (PersistenceException e) {
//            throw new RuntimeException(e);
            resp.sendRedirect("index.jsp?msg=" + e.getMessage());
        }
    }
}
