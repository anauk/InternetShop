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

<div id="header">
    <h2 class="header">You added good in card</h2>
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

                    <form class="flex-item" action="/cardOp" method = "post">
                            <input type="hidden" id="${listItem.id}">
                            <a href="/cardf" op="sum" type="submit">ADD</a>
                        </form>
                    <form class="flex-item" action="/cardOp" method = "post">
                        <input type="hidden" id="${listItem.id}" >
                        <a href="/cardf" op="sub" type="submit" >SUB</a>
                    </form>
                    <form class="flex-item" action="/cardOp" method = "post">
                        <input type="hidden" id="${listItem.id}">
                        <a href="cardf" op="delete">DELETE</a>
                    </form>
            </div>

            </#list>

    </div>
    <div class="flex-container">
<div class="flex-item1 shopping"><a href="/listauth">Continue shopping</a></div>
<div class="flex-item1" name="total"><Total:<span>$</span>
<div class="flex-item1">
    <form action="" method="get">
        <button class="color-button" type="submit">CHECKOUT</button>
    </form>
</div>
</div>
</div>
</div>

</body>
</html>