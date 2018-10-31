<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shoppingbasket</title>
<link href="resources/bootstrap-3.3.2/css/bootstrap.min.css"
	rel="stylesheet">

<style type="text/css">
body {
	padding-bottom: 40px;
	color: #5a5a5a;
}

.marketing .col-lg-4 {
	margin-bottom: 20px;
	text-align: center;
}

.marketing h2 {
	font-weight: normal;
}

.marketing .col-lg-4 p {
	margin-right: 10px;
	margin-left: 10px;
}

.top1 {
	text-align: right;
	color: #5a5a5a;
}

.title {
	text-align: center;
}
</style>
</head>

<body>
	<div class="title">
		<a href=/board><img alt="a" src="resources/logo.png"></a>
		<!-- ログインする場合/ログアウト、個人情報修正、脱退ができる-->
		<c:if test="${loginid != null }">
			<p align="right">${loginid }님 환영합니다!</p>
			<div class="top1">
				<a href="logout" style="color: #5a5a5a;">로그아웃</a> | <a href="#"
					onclick="checkpassword()" style="color: #5a5a5a;">개인정보수정</a> | <a
					href="#" onclick="deletemember()" style="color: #5a5a5a;">회원탈퇴</a>
			</div>
		</c:if>

		<!-- ログインしない場合/ログイン、メンバー登録ができる -->
		<c:if test="${loginid == null }">
			<div class="top1">
				<a href="gologinform" style="color: #5a5a5a;">로그인</a> | <a
					href="gojoinform" style="color: #5a5a5a;">회원가입</a>
			</div>
		</c:if>

	</div>

	<br>
	<br>

	<!-- メニューバー -->
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
	<!-- メニューバーエンド -->

	<!-- 買い物かごに入れた商品の情報 -->
	<table border="1px" align="center">
		<tr>
			<th>제품번호</th>
			<th>제품명</th>
			<th>수량</th>
			<th>가격</th>
			<th></th>
		</tr>
		<c:forEach var="list" items="${slList}" varStatus="status">
			<form action="deleteshoppingbasket" method="get">
				<tr>
					<td>${list.productserialnumber }<input type="hidden"
						value="${list.productserialnumber }" name="productserialnumber"></td>
					<td>${list.productname }<input type="hidden"
						value="${list.productname }" name="productname"></td>
					<td>${list.amount }<input type="hidden"
						value="${list.amount }" name="amount"></td>
					<td>${list.price }<input type="hidden" value="${list.price }"
						name="price"></td>
					<input type="hidden" value="${list.custid }" name="custid">
					<td><input type="submit" class="btn btn-primary" value="삭제"></td>
				</tr>
			</form>
		</c:forEach>
	</table>
	<!-- 買い物かごに入れた商品の情報エンド -->

</body>
</html>