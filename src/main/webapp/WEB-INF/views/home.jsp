<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Home</title>
<link href="resources/bootstrap-3.3.2/css/bootstrap.min.css"
	rel="stylesheet">

<style type="text/css">
body {
	padding-bottom: 40px;
	color: #5a5a5a;
}

/*메뉴바*/
.navbar-wrapper {
	position: absolute;
	top: 0;
	right: 0;
	left: 0;
	z-index: 20;
}

.navbar-wrapper>.container {
	padding-right: 0;
	padding-left: 0;
}

.navbar-wrapper .navbar {
	padding-right: 15px;
	padding-left: 15px;
}

.navbar-wrapper .navbar .container {
	width: auto;
}

/*메뉴 아래에서 움직이는 이미지*/
.carousel {
	height: 500px;
	margin-bottom: 60px;
}

.carousel-caption {
	z-index: 10;
}

.carousel .item {
	height: 500px;
	background-color: #777;
}

.carousel-inner>.item>img {
	position: absolute;
	top: 0;
	left: 0;
	min-width: 100%;
	height: 500px;
}

/*이미지, 문장*/
.featurette-divider {
	margin: 80px 0;
}

.featurette-heading {
	font-weight: 300;
	line-height: 1;
	letter-spacing: -1px;
}

@media ( min-width : 768px) {
	.navbar-wrapper {
		margin-top: 20px;
	}
	.navbar-wrapper .container {
		padding-right: 15px;
		padding-left: 15px;
	}
	.navbar-wrapper .navbar {
		padding-right: 0;
		padding-left: 0;
	}
	.navbar-wrapper .navbar {
		border-radius: 4px;
	}
	.carousel-caption p {
		margin-bottom: 20px;
		font-size: 21px;
		line-height: 1.4;
	}
	.featurette-heading {
		font-size: 50px;
	}
}

@media ( min-width : 992px) {
	.featurette-heading {
		margin-top: 120px;
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
	//비밀번호 확인을 위하여 새로운 윈도우창을 만듬
	function checkpassword() {
		window.open("checkpassword", "checkpw", "width=500px, height=300px");
	}
	
	//회원탈퇴를 위해 새로운 윈도우창을 만듬
	function deletemember() {
		window.open("deletemember", "deletem", "width=500px, height=300px");
	}
</script>

<link rel="stylesheet" type="text/css" href="resources/style.css">

</head>

<body>
	<div class="title">
		<!-- 가장 위에 있는 회사로고 -->
		<img alt="a" src="resources/logo.png">
		<!-- 로그인했을 경우/로그아웃、개인정보수정、탈퇴가 가능-->
		<c:if test="${loginid != null }">
			<p align="right">${loginid }님 환영합니다!</p>
			<div class="top1">
				<a href="logout" style="color: #5a5a5a;">로그아웃</a> | <a href="#"
					onclick="checkpassword()" style="color: #5a5a5a;">개인정보수정</a> | <a
					href="#" onclick="deletemember()" style="color: #5a5a5a;">회원탈퇴</a>
			</div>
		</c:if>

		<!-- 로그인 안했을 경우/로그인、회원등록이 가능 -->
		<c:if test="${loginid == null }">
			<div class="top1">
				<a href="gologinform" style="color: #5a5a5a;">로그인</a> | <a
					href="gojoinform" style="color: #5a5a5a;">회원가입</a>
			</div>
		</c:if>
	</div>

	<br>
	<br>

	<!-- Navbar 메뉴바 -->
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
	<!-- Navbar 메뉴바 끝 -->

	<!-- Carousel 메뉴 아래에 움직이는 이미지 -->
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img class="first-slide" src="resources/carousel/carousel1.png"
					alt="First slide">
			</div>
			<div class="item">
				<img class="second-slide" src="resources/carousel/carousel2.png"
					alt="Second slide">
			</div>
			<div class="item">
				<img class="third-slide" src="resources/carousel/carousel3.jpg"
					alt="Third slide">
			</div>
		</div>
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<!-- Carousel 메뉴 아래에 움직이는 이미지 끝 -->

	<!-- Featurette 이미지, 문장 -->
	<div class="container marketing">
		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading">
					<span class="text-muted" style="font-size: 45px">도시적 스타일과
						합리적인 예산</span>
				</h2>
			</div>
			<div class="col-md-5">
				<img class="featurette-image img-responsive center-block"
					src="resources/featurette/f1.jpg" alt="Generic placeholder image">
			</div>
		</div>

		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-7 col-md-push-5">
				<h2 class="featurette-heading">
					<span class="text-muted" style="font-size: 45px">따뜻하고 모던한
						스타일로 휴식 공간을 꾸며보세요</span>
				</h2>
			</div>
			<div class="col-md-5 col-md-pull-7">
				<img class="featurette-image img-responsive center-block"
					src="resources/featurette/f2.jpg" alt="Generic placeholder image">
			</div>
		</div>

		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading">
					<span class="text-muted" style="font-size: 45px">안락함 쉼터,
						합리적인 가격</span>
				</h2>
			</div>
			<div class="col-md-5">
				<img class="featurette-image img-responsive center-block"
					src="resources/featurette/f3.jpg" alt="Generic placeholder image">
			</div>
		</div>

		<hr class="featurette-divider">
		<!-- Featurette 이미지, 문장 끝 -->


		<!-- FOOTER 가장 아래에 있는 것/위로 돌아가는 기능, 회사 정보 -->
		<footer>
			<p class="pull-right">
				<a href="#">상위로 올라가기</a>
			</p>
			<p>&copy; 2018 Joseunghee Company, Inc. &middot;</p>
		</footer>
		<!-- FOOTER 가장 아래에 있는 것 끝 -->

	</div>

	<!-- Bootstrap core JavaScript -->
	<!-- 페이지 읽기를 빠르게 하기 위해 문서의 끝에 배치-->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="resources/bootstrap-3.3.2/js/bootstrap.min.js"></script>

</body>
</html>
