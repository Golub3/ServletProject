
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>already logged</title>
</head>
<body>


<div class="container">
    <h1><div class="lock"><div class="top"></div><div class="bottom"></div>
    </div></h1><p>You are already logged !</p>
</div>


<style>

    @import url("https://fonts.googleapis.com/css?family=Comfortaa");
    * {
        box-sizing: border-box;
    }

    body,
    html {
        margin: 0;
        padding: 0;
        height: 100%;
        overflow: hidden;
    }

    body {
        background-color: #a74006;
        font-family: sans-serif;
    }

    .container {
        z-index: 1;
        position: absolute;
        top: 50%;
        left: 50%;
        -webkit-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
        text-align: center;
        padding: 10px;
        min-width: 300px;
    }
    .container div {
        display: inline-block;
    }
    .container .lock {
        opacity: 1;
    }
    .container h1 {
        font-family: 'Comfortaa', cursive;
        font-size: 100px;
        text-align: center;
        color: #eee;
        font-weight: 100;
        margin: 0;
    }
    .container p {
        color: #fff;
    }

    .lock {
        transition: 0.5s ease;
        position: relative;
        overflow: hidden;
        opacity: 0;
    }
    .lock.generated {
        -webkit-transform: scale(0.5);
        transform: scale(0.5);
        position: absolute;
        -webkit-animation: 2s move linear;
        animation: 2s move linear;
        -webkit-animation-fill-mode: forwards;
        animation-fill-mode: forwards;
    }
    .lock ::after {
        content: '';
        background: #a74006;
        opacity: 0.3;
        display: block;
        position: absolute;
        height: 100%;
        width: 50%;
        top: 0;
        left: 0;
    }
    .lock .bottom {
        background: #D68910;
        height: 40px;
        width: 60px;
        display: block;
        position: relative;
        margin: 0 auto;
    }
    .lock .top {
        height: 60px;
        width: 50px;
        border-radius: 50%;
        border: 10px solid #fff;
        display: block;
        position: relative;
        top: 30px;
        margin: 0 auto;
    }
    .lock .top::after {
        padding: 10px;
        border-radius: 50%;
    }


</style>
</body>
</html>
