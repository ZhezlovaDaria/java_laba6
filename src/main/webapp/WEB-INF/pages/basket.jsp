<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 22.04.2020
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Корзина</title>
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

    <h2>Товары в корзине</h2>
    <form action="/shop_war/basket" method="POST">
        <table>
            <tr>
                <th>ID</th>
                <th>Наименование</th>
                <th>Цена</th>
                <th>Количество</th>
                <th>Сумма</th>
                <th>Действие</th>
            </tr>
            <c:forEach var="goods" items="${basketMap}">
                <c:set var="total"
                       value="${total + goods.key.price * goods.value}"></c:set>
                <tr>
                    <td>${goods.key.id} </td>
                    <td>${goods.key.name}</td>
                    <td>${goods.key.price}</td>
                    <td><input type="number"id="quantity" name="quantity" min="1" max="10" value="${goods.value}"></td>
                    <td>${goods.key.price * goods.value}</td>
                    <td><a href="/shop_war/basket/delete/${goods.key.id}">Убрать</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="6" align="right">Sum</td>
                <td>${total}</td>
            </tr>
        </table>
        <c:if test="${!empty basketMap}">
                <input type="submit" value="Пересчитать">
        </c:if>
    </form>
    <c:if test="${!empty buyer.name && !empty basketMap}">
        <h2>Оформление</h2>
        <a href="/shop_war/order/confirm">Оформить заказ</a>
    </c:if>

    <c:if test="${empty buyer.name}">
        <h2>Заполните профиль!</h2>
    </c:if>
    <c:if test="${empty basketMap}">
        <h2>Добавьте товары в корзину</h2>
    </c:if>

</body>
</html>
