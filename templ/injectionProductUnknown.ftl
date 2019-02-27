<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ShopList</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/assert/style.css">
</head>
<body>
<div class="container">
<h1 class="title">Products</h1>
</div>
<nav>
    <div class="container">
<ul class="">
    <li><a href="/auth">Authorisation</a></li>
    <li><a href="/reg">Login</a></li>
</ul>
    </div>
</nav>

<div class="container">
<div class="flex-container">
    <span class="flex-item">ID</span>
    <span class="flex-item">PRODUCT</span>
    <span class="flex-item">PRICE</span>
    <span class="flex-item">ADD</span>
</div>
    <#list product as listItem>
        <div class="flex-container">
    <span class="flex-item">${listItem.id}</span>
    <span class="flex-item">${listItem.quantity}</span>
    <span class="flex-item">${listItem.price}</span>
    <form class="flex-item" action="/auth" method = "post">
        <input type="hidden" name="add" >
        <button name="sum" type="submit">ADD</button>
    </form>
</div>
    </#list>
</div>

</body>
</html>