<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ShopList</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/assert/style.css">
</head>
<body>
<div class="jumbotron">
    <div class="container">
        <h1>Products</h1>
    </div>
</div>

<div class="container">
    <div class="header">
        <#if registered??>
            <a class="active nav nav-pills" href="/cardf">CARD </a>
            <a class="nav nav-pills" href="/logout">LogOut</a>

        <#else>
            <a class="active nav nav-pills" href="/auth">Authorisation</a>
            <a class="nav nav-pills" href="/reg">Login</a>

        </#if>
    </div>
</div>

<div class="">
    <div class="container">
        <div class="flex-container">
            <span class="flex-item">ID</span>
            <span class="flex-item">PRODUCT</span>
            <span class="flex-item">PRICE</span>
            <#if registered??>
                <span class="flex-item">ADD</span>
            <#else>
                <div class="flex-item">AUTH/LOGIN</div>
            </#if>
        </div>

        <#list products as listItem>
            <div class="flex-container">
                <span class="flex-item">${listItem.id}</span>
                <span class="flex-item">${listItem.name}</span>
                <span class="flex-item">${listItem.price}</span>
                <#if registered??>
                    <div class="flex-item">
                        <form action="/shop" method="post">
                            <input type="hidden" value="${listItem.id}" name="id">
                            <button name="add" type="submit">ADD</button>
                        </form>
                    </div>
                <#else>
                    <div class="flex-item"><a href="/auth">Auth/</a>
                        <a href="/reg">Login</a></div>
                </#if>

            </div>
        </#list>
    </div>
</div>

</body>
</html>