<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>CardItem</title>
</head>
<body>

<div>

<div id="">
    <h2>You added good in card</h2>
    <div>
        <ul>
            <span name="id">ID</span>
            <span name="name">PRODUCT</span>
            <span name="price">PRICE</span>
            <span name="amount">QUANTITY</span>
            <#list card as listItem>
                <li>${listItem.id}:${listItem.user_id}:${listItem.commodity_id}:${listItem.quantity}
                    <div>
                        <form action="/card" method = "post">
                            <button name="add" type="submit" >ADD</button>
                            <button name="sub" type="submit" >SUB</button>
                            <span name="sum">SUM</span>
                        </form>
                    </div></li>
            </#list>
        </ul>
    </div>

<div><a href="listauth">Continue shopping</a></div>
<div name="total">Total:
<div>
    <form action="" method="get">
        <button type="submit">CHECKOUT</button>
    </form>
</div>
</div>
</div>
</div>

</body>
</html>