<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 20.04.2020
  Time: 15:32
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
        <a href="/shop_war//profile">Профиль</a>
    </c:if>

    <c:url value="/goods/addGoods" var="var"/>

    <form action="${var}" method="POST">
        <label for="name">Товар</label>
        <input type="text" name="name" id="name" required autocomplete="off">

        <label for="price">Цена</label>
        <input type="text" pattern="\d+(\.\d{2})?" name="price" id="price" required autocomplete="off">

        <input type="submit" value="Добавить товар">
    </form>
</body>
</html>
