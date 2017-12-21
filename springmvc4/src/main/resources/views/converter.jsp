<%--
  Created by IntelliJ IDEA.
  User: yaohongwu
  Date: 2017/12/21
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HttpMessageConverter demo</title>
</head>
<body>
<div id="resp"></div><input type="button" onclick="req();" value="请求"/>
<script src="assets/js/jquery.js" type="text/javascript"></script>
<script>
    function req(){
        $.ajax({
            url: "convert",
            data: "1-wangyunfei", //1
            type:"POST",
            contentType:"application/x-wisely", //2
            success: function(data){
                $("#resp").html(data);
            }
        });
    }

</script>
</body>
</html>
