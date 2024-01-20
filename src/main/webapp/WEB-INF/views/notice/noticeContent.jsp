<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>    
    
<head>
		<c:set var="id" value="${sessionScope.memId}" />
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
				    <div class="card mb-4">
                        <div class="card-body">
                            <div class="small text-muted"><fmt:formatDate value="${dto.reg_date}" type="date" dateStyle="short" />// 조회수 : ${dto.readCount}</div>
                            <h2 class="card-title">${dto.noticeTitle}</h2>
                            <p class="card-text">
                            <c:if test="${fileNames!=null && fileNames!=''}">
                            	<c:forEach var="fdto" items="${fileNames}">
                            파일명 :  <a href="/resources/file/notice/${fdto.filename}">${fdto.filename}</a>
                            		<a href="/notice/deleteFileUnit?filenum=${fdto.num}&ref=${fdto.boardNum}&pageNum=${pageNum}">
                            			<img src="/resources/file/notice/delete.png" width="30">
                            		</a>
                            		<br />
                            	</c:forEach>
                            </c:if> 
                            <br />${dto.noticeContent}</p>
                        </div>
                    </div>
                      <input type="button" class="btn btn-primary" value="공지글 수정" onclick="window.location=href='/notice/noticeUpdateForm?noticeNum=${dto.noticeNum}&ref=${dto.ref}'">		
					  <input type="button" class="btn btn-primary" value="공지글 삭제" onclick="window.location=href='/notice/deleteNotice?ref=${dto.ref}'">
                    <c:if test="${grade>0}">
					  <form action="/notice/noticeReviewPro" method="post"> 
					  <div class="mb-3">
					   		<input type="hidden" name="writer" value="${id}"/>
					   		<input type="hidden" name="noticeTitle" value="${dto.noticeTitle}" />
					   		<input type="hidden" name="ref" value="${dto.ref}" />
					   		<input type="hidden" name="pageNum" value="${pageNum}" />
							<textarea class="form-control" id="exampleFormControlTextarea1"
								rows="3" name="noticeContent"></textarea>
							<button class="btn btn-primary"  type="submit">댓글작성</button>	
						</div>                   
                      </form>
                      
                    </c:if>
				    <c:if test="${rcount>0}">
					    <ul class="list-group">
						  <c:forEach var="review" items="${reviews}">					     
						  	<li class="list-group-item">${review.noticeContent} 
						  		<br />
						  		${review.writer} / 
						  		<fmt:formatDate value="${review.reg_date}" type="date" dateStyle="short" />
						  		<c:if test="${review.writer==id}">
						  			<button class="btn btn-primary"  type="button" onclick="window.location='/notice/deleteReview?noticeNum=${review.noticeNum}&ref=${review.ref}&pageNum=${pageNum}'">댓글삭제</button>	
						  		</c:if>
						  	</li>
						  </c:forEach>
						</ul>
					</c:if>
                    <!-- Pagination-->
                    <c:if test="${rcount>0}">
	                    <nav aria-label="Pagination">
	                        <hr class="my-0" />
	                        <ul class="pagination justify-content-center my-4">
	                            <c:if test="${rstartPage>10}">
	                            	<li class="page-item"><a class="page-link" href="/notice/noticeContentPro?noticeNum=${dto.noticeNum}&pageNum=${pageNum}&ref=${dto.ref}&rpageNum=${rstartPage-10}">Newer</a></li>
	                            </c:if>
	                            <c:forEach var="i" begin="${rstartPage}" end="${rendPage}">
	                            	<li class="page-item"><a class="page-link" href="/notice/noticeContentPro?noticeNum=${dto.noticeNum}&pageNum=${pageNum}&ref=${dto.ref}&rpageNum=${i}">${i}</a></li>
	                            </c:forEach>
	                            <c:if test="${rendPage<rpageCount}">
	                            	<li class="page-item"><a class="page-link" href="/notice/noticeContentPro?noticeNum=${dto.noticeNum}&pageNum=${pageNum}&ref=${dto.ref}&rpageNum=${rstartPage+10}">Older</a></li>
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