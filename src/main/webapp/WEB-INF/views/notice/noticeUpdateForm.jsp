<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
	crossorigin="anonymous"></script>

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>MiNi</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon"
	href="/resources/assets/favicon.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="/resources/css/styles.css" rel="stylesheet" />
</head>
<body>
	<!-- Responsive navbar-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="/notice/noticeList">MiNi</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link"
						href="/notice/noticeList">Home</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/notice/noticeList">공지사항</a></li>
					<li class="nav-item"><a class="nav-link" href="#!">로그인</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Page header with logo and tagline-->
	<header class="py-5 bg-light border-bottom mb-4">
		<div class="container">
			<div class="text-center my-5">
				<h1 class="fw-bolder">공지글 작성</h1>
			</div>
		</div>
	</header>
	<!-- Page content-->
	<form action="/notice/noticeUpdatePro" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="noticeNum" value="${dto.noticeNum}">
		<input type="hidden" name="ref" value="${dto.ref}">
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">제목</label> 
				<input name="noticeTitle" type="text" class="form-control"
				id="exampleFormControlInput1" value="${dto.noticeTitle}">
		</div>
		<div class="mb-3">
			<label for="exampleFormControlInput2" class="form-label">파일업로드</label>
			<input type="file" class="form-control" id="exampleFormControlInput2"
				name="file" multiple="multiple">
		</div>
		<div class="mb-3">
			<label for="exampleFormControlTextarea1" class="form-label">글내용</label>
			<textarea class="form-control" id="exampleFormControlTextarea1"
				rows="5" name="noticeContent">${dto.noticeContent}</textarea>
		</div>
		<div class="d-grid gap-2">
			<button class="btn btn-secondary" type="submit">수정</button>
		</div>
	</form>
	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; MiNi
				Website 2023</p>
		</div>
	</footer>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="/resources/js/scripts.js"></script>
</body>