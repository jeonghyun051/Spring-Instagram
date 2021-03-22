<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<%@ include file="../layout/header.jsp" %>

    <div class="container">
        <main>
            <section class="upload">
                <div class="upload-top">
                    <a href="home.html" class="">
                        <img src="/images/logo.jpg" alt="">
                    </a>
                    <p>사진 업로드</p>
                </div>
                <div class="upload-form">
                    <input type="file" name="file" id="imageFileOpenInput" accept="image/*">
                    <div class="upload-img">
                        <img src="/images/profile.jpeg" alt="">
                    </div>
                    <div class="upload-form-detail">

                        <input type="text" placeholder="누워있는중">
                        <button class="cta blue">업로드</button>
                    </div>
                </div>
            </section>
        </main>
    </div>

    <%@ include file="../layout/footer.jsp" %>