<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 28.04.2020
  Time: 16:31
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

    <h2>Информация о заказе</h2>
    <form action="/shop_war/order/confirm" method="POST">
        <table>
            <tr>
                <th>ID</th>
                <th>Наименование</th>
                <th>Цена</th>
                <th>Количество</th>
                <th>Сумма</th>
            </tr>
            <c:forEach var="goods" items="${basketMap}">
                <c:set var="total"
                       value="${total + goods.key.price * goods.value}"></c:set>
                <tr>
                    <td>${goods.key.id} </td>
                    <td><input type="text" name="goods" value="${goods.key.name}" readonly></td>
                    <td><input type="text" name="price" value="${goods.key.price}" readonly></td>
                    <td><input type="text" name="value" value="${goods.value}" readonly></td>
                    <td>${goods.key.price * goods.value}</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="6" align="right">Sum</td>
                <td><input type="text" name="total" value="${total}" readonly></td>
            </tr>
        </table>
        <h2>Информация о покупателе</h2>
        <table>
            <tr>
                <td>Имя</td>
                <td><input type="text" name="name" value="${buyer.name}" readonly></td>
            </tr>
            <tr>
            <tr>
                <td>Почта</td>
            <td><input type="text" name="email" value="${buyer.email}" readonly></td>
            </tr>
            <tr>
                <td>Телефон</td>
                <td><input type="text" name="phone" value="${buyer.phone}" readonly></td>
            </tr>
            <tr>
                <td>Адрес</td>
                <td><input type="text" name="address" value="${buyer.address}" readonly></td>
            </tr>
        </table>
        <c:if test="${!empty buyer && !empty basketMap}">
            <input type="submit" value="Оформить">
        </c:if>
    </form>
</body>
</html>
