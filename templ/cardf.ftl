<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/assert/style.css">
    <title>CardItem</title>
</head>
<body>

<div action="/auth" method="post" total="">
    <a href="/logout" >Log out</a>

<div id="header">
    <h2 class="header">You (${name}) added good in card</h2>
</div>
    <div class="container">
        <div class="flex-container">
            <span class="flex-item" name="id">ID</span>
            <span class="flex-item" name="name">PRODUCT</span>
            <span class="flex-item" name="price">PRICE</span>
            <span class="flex-item" name="amount">QUANTITY</span>
            <span class="flex-item" name="amount">ADD</span>
            <span class="flex-item" name="amount">SUB</span>
            <span class="flex-item" name="amount">DELETE</span>
</div>

            <#list card as listItem>

            <div class="flex-container">
                <span class="flex-item">${listItem.id}</span>
               <#-- <span>${listItem.user_id}</span>-->
                <span class="flex-item">${listItem.pname}</span>
                <span class="flex-item">${listItem.price}</span>
                <span class="flex-item">${listItem.quantity}</span>

                    <form class="flex-item" <#--action="/cardOp"--> action="/cardf" method = "post">
                            <input type="hidden" value="${listItem.id}" name="id">
                        <input type="hidden" value="sum" name="action">
                            <button <#--op="sum"--> type="submit">ADD</button>
                        </form>
                    <form class="flex-item" <#--action="/cardOp"-->action="/cardf" method = "post">
                        <input type="hidden" value="${listItem.id}" name="id">
                        <input type="hidden" value="sub" name="action">
                        <button type="submit" >SUB</button>
                    </form>
                    <form class="flex-item" <#--action="/cardOp"-->action="/cardf" method = "post">
                        <input type="hidden" value="${listItem.id}" name="id">
                        <input type="hidden" value="delete" name="action">
                        <button<#-- href="cardf"-->>DELETE</button>
                    </form>
            </div>

            </#list>

    </div>
    <div class="flex-container">
<span class="flex-item1 shopping"><a href="/listauth">Continue shopping</a></span>
        <span class="flex-item1">Total:</span><span>${summ}</span>
        <span class="flex-item1">${qntSum}</span>
<span class="flex-item1">
    <form action="" method="get">
        <button class="color-button" type="submit">CHECKOUT</button>
    </form>
</span>
</span>
</div>
</div>

</body>
</html>