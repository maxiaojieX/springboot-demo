<#--<!DOCTYPE>-->
<#--<html>-->
<#--<head>-->
    <#--<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />-->
<#--</head>-->

<#--<body>-->
<#--<div id="chat" style="width: 100%;height: 500px;background-color: #999999">-->

<#--</div>-->
<#--<div>-->
    <#--请输入你的名字:<input type="text" id="name">-->
    <#--<input type="text" id="content">-->
    <#--<button id="sub">发送</button>-->
<#--</div>-->
<#--<script src="/static/js/jQuery/jquery-2.2.3.min.js" type="text/javascript"></script>-->
<#--<script>-->

    <#--var ws = new WebSocket("ws://172.16.70.38:8888");-->
    <#--var accountName = "匿名";-->

    <#--/**-->
     <#--* 接收消息-->
     <#--* @param json-->
     <#--*/-->
    <#--ws.onmessage = function (json) {-->
        <#--var newMessage = json.data;-->
        <#--console.log(json.data)-->
        <#--var message1 = newMessage.substring(0,newMessage.lastIndexOf("#"));-->
        <#--var message2 = newMessage.substring(newMessage.lastIndexOf("#")+1);-->

        <#--var html = '<p class="text-muted">'+ message2 + ': '+ message1 +'</p>';-->
        <#--$(html).appendTo("#chat");-->

    <#--}-->

    <#--/**-->
     <#--* 按钮发送消息-->
     <#--*/-->
    <#--$("#sub").click(function () {-->
        <#--var imessage = $("#content").val();-->
        <#--var name = $("#name").val();-->
        <#--if(name != null && name != "") {-->
            <#--accountName = name;-->
        <#--}-->
        <#--var websocketMessage = imessage+"#"+accountName;-->
        <#--$("#content").val("");-->
        <#--ws.send(websocketMessage);-->
    <#--});-->

    <#--$(document).keypress(function(e) {-->
        <#--var eCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;-->
        <#--var imessage = $("#content").val();-->

        <#--if (eCode == 13 && imessage != null && imessage != ""){-->

            <#--var websocketMessage = imessage+"#"+accountName;-->
            <#--$("#content").val("");-->
            <#--ws.send(websocketMessage);-->

        <#--}-->
    <#--});-->

<#--</script>-->
<#--</body>-->
<#--</html>-->
