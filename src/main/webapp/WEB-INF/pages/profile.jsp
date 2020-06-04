<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 21.04.2020
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Данные пользователя</title>
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

    <h2>Данные пользователя</h2>
    <table>
        <tr>
            <td>Имя</td>
            <td>${buyer.name}</td>
        </tr>
        <tr>
        <tr>
            <td>Почта</td>
            <td>${buyer.email}</td>
        </tr>
        <tr>
            <td>Телефон</td>
            <td>${buyer.phone}</td>
        </tr>
        <tr>
            <td>Адрес</td>
            <td>${buyer.address}</td>
        </tr>
    </table>
    <a href="/shop_war/addProfile">Назад</a>
</body>
</html>
