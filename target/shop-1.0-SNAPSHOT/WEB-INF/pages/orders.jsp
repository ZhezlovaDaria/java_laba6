<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 28.04.2020
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="/shop_war/goods">Товары</a>
    <a href="/shop_war/basket">Корзина</a>
    <a href="/shop_war/orders">Заказы</a>
    <c:if test="${empty buyer.name}">
        <a href="/shop_war/addProfile">Профиль</a>
    </c:if>
    <c:if test="${!empty buyer.name}">
        <a href="/shop_war/profile">Профиль</a>
    </c:if>

    <h2>Все заказы</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Почта</th>
            <th>Адрес</th>
            <th>Товары</th>
            <th>Количество</th>
            <th>Сумма</th>
            <th>Телефон</th>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.id} </td>
                <td>${order.name}</td>
                <td>${order.email}</td>
                <td>${order.address}</td>
                <td>${order.goods}</td>
                <td>${order.quantity}</td>
                <td>${order.sum}</td>
                <td>${order.phone}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
