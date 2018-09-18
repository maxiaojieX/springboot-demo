<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>
<div class="code" id="weiboBtn">
    <img src="/static/images/weiboBtn.png">
</div>
<button id="gitBtn">Github Login</button>
<div class="code" id="githubBtn" style="width: 24px;height: 24px">
    <img src="/static/images/github.png" height="24px" width="24px">
</div>
<script src="/static/js/jQuery/jquery-2.2.3.min.js" type="text/javascript"></script>
<script>
    $(function () {

        $("#weiboBtn").click(function () {
            window.open("https://api.weibo.com/oauth2/authorize?client_id=******&redirect_uri=http://127.0.0.1:8083/weibo_oauth")
        });
        $("#gitBtn").click(function () {
            window.open("https://github.com/login/oauth/authorize?client_id=***&redirect_uri=http://127.0.0.1/github_oauth")
        });
    });
</script>
</body>
</html>
