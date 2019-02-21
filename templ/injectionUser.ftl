<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>
<h1>Users</h1>
<h3>Card</h3>
<ul>
    <#list users as listItem>
        <li>${listItem.id}:${listItem.name}</li>
    </#list>
</ul>
</body>
</html>