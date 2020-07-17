<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>readBoard</title>
<link href="resources/bootstrap-3.3.2/css/bootstrap.min.css"
	rel="stylesheet">

<style>
#container {
	width: 70%;
	margin: 0 auto;
	padding-top: 10%;
}

#list {
	text-align: center;
}

#write {
	text-align: right;
}

.table>thead {
	background-color: #b3c6ff;
}

.table>thead>tr>th {
	text-align: center;
}

.table-hover>tbody>tr:hover {
	background-color: #e6ecff;
}

.table>tbody>tr>td {
	text-align: center;
}

.table>tbody>tr>#title {
	text-align: left;
}

div>#paging {
	text-align: center;
}

.hit {
	animation-name: blink;
	animation-duration: 1.5s;
	animation-timing-function: ease;
	animation-iteration-count: infinite;
}

@
keyframes blink {
	from {color: white;
}

30%
{
color
:
 
yellow
;
}
to {
	color: red;
	font-weight: bold;
}

}
.top1 {
	text-align: right;
	color: #5a5a5a;
}

.title {
	text-align: center;
}
</style>

<script type="text/javascript">
  //비밀번호를 한번 더 입력해서 게시판의 글을 삭제
  function deletecheck(){
	  
	  var inputpw = prompt('비밀번호를 입력해주세요');
	  var pw = document.getElementById("pw").value;
	  
	  if(inputpw == pw){
		  alert('삭제되었습니다');
		  return true;
	  }else{
		  alert('비밀번호가 맞지않습니다');
		  return false;
	  }
	  
  }
  
//비밀번호를 한번 더 입력해서 게시판의 글을 업데이트
  function updatecheck(){
	  
	  var inputpw = prompt('비밀번호를 입력해주세요');
	  var pw = document.getElementById("pw").value;
	  
	  if(inputpw == pw){
		  return true;
	  }else{
		  alert('비밀번호가 맞지않습니다');
		  return false;
	  }
	  
  }
  </script>
</head>

<body>
	<div class="title">
		<a href=/board><img alt="a" src="resources/logo.png"></a>
		<!-- 로그인 했을 경우/로그아웃, 개인정보수정, 탈퇴가 가능-->
		<c:if test="${loginid != null }">
			<p align="right">${loginid }님 환영합니다!</p>
			<div class="top1">
				<a href="logout" style="color: #5a5a5a;">로그아웃</a> | <a href="#"
					onclick="checkpassword()" style="color: #5a5a5a;">개인정보수정</a> | <a
					href="#" onclick="deletemember()" style="color: #5a5a5a;">회원탈퇴</a>
			</div>
		</c:if>

		<!-- 로그인 안했을 경우/로그인, 회원등록이 가능 -->
		<c:if test="${loginid == null }">
			<div class="top1">
				<a href="gologinform" style="color: #5a5a5a;">로그인</a> | <a
					href="gojoinform" style="color: #5a5a5a;">회원가입</a>
			</div>
		</c:if>

	</div>

	<br>
	<br>

	<!-- 메뉴바 -->
	<div class="container" style="width: 100%">
		<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>

			<div class="navbar-collapse collapse" id="navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="menu1">소파/의자/책상</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li><a href="menu2">침대/매트리스</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li><a href="menu3">조명</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li><a href="visit">고객문의</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li><a href="checkcheck">제품재고확인</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="goshoppingbasket"><img align="right"
							height="20" alt="장바구니" src="resources/2.png">장바구니</a></li>
				</ul>
			</div>
		</div>
		</nav>
	</div>
	<!-- 메뉴바 끝 -->

	<!-- 게시글 -->
	<div class="container">
		<table class="table table-bordered">
			<tbody>
				<form action="goupdateform" method="post"
					onsubmit="return updatecheck()" style="display: inline;">
					<c:forEach var="list" items="${sList}" varStatus="status">
						<tr>
							<th>제목</th>
							<td><input type="text" value="${list.title }"
								readonly="readonly" class="form-control" /></td>
						</tr>
						<tr>
							<th>아이디</th>
							<td><input type="text" value="${list.custid }"
								readonly="readonly" class="form-control" /></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" value="${list.pw }" id="pw"
								readonly="readonly" class="form-control" /></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea rows="20" cols="30" readonly="readonly"
									class="form-control">${list.content}</textarea></td>
						</tr>
						<tr>
							<td colspan="2"><input type="button" value="글 목록"
								class="btn btn-primary pull-left"
								onclick="javascript:location.href='visit'" /> <c:if
									test="${loginid == list.custid}">
									<input type="submit" value="수정"
										class="btn btn-primary pull-right">
				</form>
				</c:if>
				<c:if test="${loginid == list.custid}">
					<form action="deleteBoard" method="post"
						onsubmit="return deletecheck()" style="display: inline;">
						<input type="hidden" value="${list.pw }" name="pw" id="pw" /> <input
							type="hidden" value="${list.boardnum }" name="boardnum"
							id="boardnum"> <input type="submit" value="삭제"
							class="btn btn-primary pull-right">
					</form>
				</c:if>
				</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 게시글 정보  -->
</body>
</html>
