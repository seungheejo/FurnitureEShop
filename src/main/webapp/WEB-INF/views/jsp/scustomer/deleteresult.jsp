<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>deleteresult</title>

<script type="text/javascript">
if(!${empty loginid}){ 
	alert('비밀번호를 다시 확인해주세요');
	location.href="deletemember";
}else{
	/*loginid가 아닌 경우, 탈퇴가 되었다면 
	윈도우가 닫히고 홈페이지의 초기화면으로 이동*/
	alert('탈퇴하였습니다');
	opener.location.href="../board";
	window.close();
}
</script>
</head>

<body>

</body>
</html>
