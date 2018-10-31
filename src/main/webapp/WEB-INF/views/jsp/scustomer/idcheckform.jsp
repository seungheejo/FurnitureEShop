<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>idcheckform</title>
<link href="resources/bootstrap-3.3.2/css/bootstrap.min.css"
	rel="stylesheet">
	
<script type="text/javascript">
function useThisId(custid){
	//利用できるcustidを持ってopener(customerJoinForm)に戻る
	opener.document.getElementById("custid").value=custid;
	window.close();
	
}
</script>

<style type="text/css">
body{
	background-color: #f5f5f5;
}
</style>
</head>

<body>
<div align="center"><h4>아이디 중복확인</h4></div>
<br>

<c:if test="${idok == true }">
	<p>${custid }는 사용가능합니다.</p>
	<input type="button" class="btn btn-primary" value="사용하기" onclick="useThisId('${custid}')">
</c:if>

<c:if test="${idok == false }">
	<p>${custid }는 사용불가</p>
</c:if>

<form action="idcheck" method="post">
	<div class="row" align="center">
		<input type="text" name="custid"> <input class="btn btn-primary" type="submit" value="확인">
	</div>
</form>
</body>
</html>