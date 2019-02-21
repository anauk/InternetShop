<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ShopList</title>
</head>
<body>
<h1>Products</h1>
<div><a href="/logout">LogOut</a></div>
<form action="/card" method = "post">
    <button type="submit" >CARD</button>
</form>
<ul>
    <span>ID</span>
    <span>PRODUCT</span>
    <span>PRICE</span>
    <#list product as listItem>
        <li>${listItem.id}:${listItem.name}:${listItem.price}
            <div>
                <form action="/card" method = "post">
                    <button type="submit" >ADD</button>
                </form>
            </div></li>
    </#list>
</ul>
</body>
</html>