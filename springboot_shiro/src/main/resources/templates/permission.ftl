<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<body>
      <#--  <@shiro.user>
        欢迎[<@shiro.principal/>]登录，<a href="/logout.shtml">退出</a>
        </@shiro.user>-->
<form action="logout" method="post">
        <table>
            <tr>
                <td><a href="add">新增</a></td>
            </tr>
            <tr>
                <td><a href="update">修改</a></td>
            </tr>
            <tr>
                <td><a href="delete">删除</a></td>
            </tr>
            <tr>
                <td><a href="select">查询</a></td>
            </tr>
            <tr>
                <td><input type="submit" value="注销"></td>
                <td></td>
            </tr>
        </table>
    </form>

</body>
</html>