<%--
  Created by IntelliJ IDEA.
  User: banuprakash
  Date: 25/06/25
  Time: 2:51 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Products</title>
    <style>
        table, th, td {
            border: 1px solid red;
        }
    </style>
</head>
<body>
    <h1>Product List Page - list.jsp</h1>
    <table >
        <tr>
            <th>ID</th> <th>Name</th> <th>Price</th> <th>Delete</th>
        </tr>
        <c:forEach var="product" items="${products}">
          <tr>
              <td>${product.id}</td>
              <td>${product.name}</td>
              <td>${product.price}</td>
              <td><button>Delete</button></td>
          </tr>
        </c:forEach>
    </table>
</body>
</html>
