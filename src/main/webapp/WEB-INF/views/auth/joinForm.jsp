<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Photogram</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
        integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
</head>

<body>
    <div class="container">
        <main>
            <section class="login">
 
                <article class="login__form__container">
                    <div class="login__form">
                        <h1><img src="/images/logo.jpg" alt=""></h1>
                     
                        <form class="login__input" action="/auth/join" method="post"> 
                            <input type="text" name="username" placeholder="유저네임">
                            <input type="password" name="password"  placeholder="패스워드">
                            <input type="email"  name="email"  placeholder="이메일">
                            <input type="name"  name="name"  placeholder="이름">
                            <button>가입</button>
                        </form>

                 
                    </div>
                    <div class="login__register">
                        <span>계정이 있으신가요?</span>
                        <a href="/auth/loginForm">로그인</a>
                    </div>
              
                </article>
            </section>
        </main>
    </div>
</body>

</html>