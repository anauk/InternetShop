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
<div class="header" >
<ul class="nav nav-pills">
    <li class="active"><a  class="" href="/cardf" >CARD </a></li>
<li><a class="" href="/logout">LogOut</a></li>
</ul>
</div>
</div>

<div class="jumbotron">
    <div class="container">
        <h1>Products</h1>
    </div>
</div>

<div class="">
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
            <span class="flex-item"><#--${listItem.name}--></span>
            <span class="flex-item">${listItem.price}</span>

                <form class="flex-item" action="/cardf" method = "post">
                    <input type="hidden" value="${listItem.id}">
                    <a href="listauth" name="sum" type="submit">ADD</a>
                </form>

            </div>
    </#list>
    </div>
</div>

</body>
</html>