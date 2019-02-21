<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ShopList</title>
</head>
<body>
<h1>Products</h1>
<div><a href="/auth">Authorisation</a>
<a href="/reg">Login</a></div>
<ul>
    <span>ID</span>
    <span>PRODUCT</span>
    <span>PRICE</span>
    <#list product as listItem>
        <li>${listItem.id}:${listItem.name}:${listItem.price}
            <div>
                <a href="/auth">ADD</a>
            </div></li>
    </#list>
</ul>
</body>
</html>