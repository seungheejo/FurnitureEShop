<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>stockcheck</title>
<link href="resources/style.css" type="text/css" rel="stylesheet">
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

<script type="text/javascript">
	//有効するかのチェック
	function check() {

		var productserialnumber = $('#productserialnumber').val();
		var location = $('#location').val();

		if (productserialnumber == "") {
			alert('제품 번호를 입력해주세요');
			return false;
		}

		if (location == "") {
			alert('매장을 선택해 주세요');
			return false;
		}

		return true;
	}
</script>
</head>

<body>
	<div class="title">
		<a href=/board><img alt="a" src="resources/logo.png"></a>
		<!-- ログインする場合/ログアウト、個人情報修正、脱退ができる-->
		<c:if test="${loginid != null }">
			<p align="right">${loginid }님환영합니다!</p>
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

	<!-- 商品在庫の確認のフォーム -->
	<form action="gostockcheck" onsubmit="return check()">
		<div align="center">
			<h1>제품재고 확인</h1>
		</div>
		<hr>
		<div align="center">
			<h3>제품검색</h3>
		</div>
		<br>
		<br>
		<div align="center">
			<label>제품 번호 </label><input type="text" name="productserialnumber"
				id="productserialnumber"> <label>매장 선택 </label> <select
				id="location" name="location">
				<option value="s">서울</option>
				<option value="b">부천</option>
			</select> <input type="submit" value="검색" class="btn btn-primary">
		</div>
	</form>

	<c:forEach var="list" items="${pList}" varStatus="status">
		<c:if test="${pList != null }">
			<div align="center">
				<label>제품 번호 : </label>${list.productserialnumber}</div>
			<c:if test="${list.location == 'b'}">
				<div align="center">
					<label>매장 : </label>부천
				</div>
			</c:if>
			<c:if test="${list.location == 's'}">
				<div align="center">
					<label>매장 : </label>서울
				</div>
			</c:if>
			<div align="center">
				<label>제품명 : </label>${list.productname}</div>
			<div align="center">
				<label>재고 : </label>${list.stock}</div>
		</c:if>
	</c:forEach>
	<!-- 商品在庫の確認のフォームエンド -->
</body>
</html>