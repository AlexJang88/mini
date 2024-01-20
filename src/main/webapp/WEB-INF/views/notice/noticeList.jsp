<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>    
    
<head>
		
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>MiNi</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="/resources/assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="/resources/css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <!-- Responsive navbar-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="/notice/noticeList">MiNi</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item"><a class="nav-link" href="/notice/noticeList">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="/notice/noticeList">공지사항</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">로그인</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Page header with logo and tagline-->
        <header class="py-5 bg-light border-bottom mb-4">
            <div class="container">
                <div class="text-center my-5">
                    <h1 class="fw-bolder">공 지 사 항</h1>
                </div>
            </div>
        </header>
        <!-- Page content-->
                    <!-- Featured blog post-->
                    <c:if test="${count==0}">
                    	<div class="card mb-4">
	                        <div class="card-body">
	                            <div class="small text-muted">
	                            </div>
	                            공지글이 없습니다.
	                        </div>
                   		 </div>
                    </c:if>
                    <c:if test="${count>0}">
                    <c:forEach var="dto" items="${list}">
	                    <div class="card mb-4">
	                        <div class="card-body">
	                            <div class="small text-muted">
	                            	<fmt:formatDate value="${dto.reg_date}" type="date" dateStyle="short" />
	                            </div>
	                            <a href="/notice/noticeContent?noticeNum=${dto.noticeNum}&pageNum=${pageNum}&ref=${dto.ref}">
	                            	<img src="/resources/file/notice/${dto.filename}">
	                            	<h2 class="card-title">${dto.noticeTitle}
	                            		<c:if test="${dto.isFile>0}">
	                            			<img src="/resources/file/notice/fileimg.png" width="30"/>
	                            		</c:if>
	                            	</h2>
	                            </a>
	                        </div>
	                    </div>
                    </c:forEach>
                    </c:if>
                    <c:if test="${grade==10}">
                      <button class="btn btn-primary"  type="button" onclick="window.location='/notice/noticeWriteForm'">공지글작성</button>
                    </c:if>
                    <!-- Nested row for non-featured blog posts-->
                 
                    <!-- Pagination-->
                    <c:if test="${count>0}">
	                    <nav aria-label="Pagination">
	                        <hr class="my-0" />
	                        <ul class="pagination justify-content-center my-4">
	                            <c:if test="${startPage>10}">
	                            	<li class="page-item"><a class="page-link" href="/notice/noticeList?pageNum=${startPage-10}">Newer</a></li>
	                            </c:if>
	                            <c:forEach var="i" begin="${startPage}" end="${endPage}">
	                            	<li class="page-item"><a class="page-link" href="/notice/noticeList?pageNum=${i}">${i}</a></li>
	                            </c:forEach>
	                            <c:if test="${endPage<pageCount}">
	                            	<li class="page-item"><a class="page-link" href="/notice/noticeList?pageNum=${startPage+10}">Older</a></li>
	                        	</c:if>
	                        </ul>
	                    </nav>
                    </c:if>
                              
                    
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="/resources/js/scripts.js"></script>
    </body>