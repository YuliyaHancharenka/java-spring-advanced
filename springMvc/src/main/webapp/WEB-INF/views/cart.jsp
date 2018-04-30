<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Cart</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">

    <a href="?lang=en_EN">EN</a> | <a href="?lang=ru_RU">RU</a>
<br>
    <c:forEach items="${goods}" var="good">
        <form method="post" action="${contextPath}/cart/edit/${good.id}">
            <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
            name: <input type="text" name="name" disabled value="${good.name}">
            price: <input type="number" name="price" disabled value="${good.price}">
            quantity: <input type="number" name="quantity" value="${good.quantity}">
            total: $${good.quantity * good.price}
            <a href="${contextPath}/cart/delete/${good.id}" href="#">[x]</a>
            <input type="submit" value="edit">
        </form>


    </c:forEach>

    <br>
    <h1>Order book</h1>
    <form method="post" action="${contextPath}/cart/add">
        <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
        id: <input type="number" name="id"> <br>
        name: <input type="text" name="name"> <br>
        price: <input type="number" name="price"> <br>
        quantity: <input type="number" name="quantity"> <br>
        <input type="submit">
    </form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

<script>

</script>


</body>
</html>
