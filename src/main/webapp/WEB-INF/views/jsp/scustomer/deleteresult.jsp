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
	/*loginidがない場合、脱退ができたら、
	ウィンドウが閉じられてホームページの初期画面に移動*/
	alert('탈퇴하였습니다');
	opener.location.href="../board";
	window.close();
}
</script>
</head>

<body>

</body>
</html>