<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<title>RSA Login Test</title>
<script src="lib/Scripts/jquery-1.4.1.js" type="text/javascript"></script>
<script src="lib/Scripts/jQuery.md5.js" type="text/javascript"></script>
<script src="lib/Scripts/BigInt.js" type="text/javascript"></script>
<script src="lib/Scripts/RSA.js" type="text/javascript"></script>
<script src="lib/Scripts/Barrett.js" type="text/javascript"></script>

<script type="text/javascript">
	function cmdEncrypt() {
		var password = document.getElementById("result").value;
		setMaxDigits(129);
		var key = new RSAKeyPair("${RSAPub}", '', "${RSAPKey}");
		var pwdRtn = encryptedString(key, password);
		document.getElementById("result").value = pwdRtn;
	}
</script>
<body>
	<h2>Hello World!</h2>
	<input type="hidden" id="RSAPKey" value="${RSAPKey}" />
	<form method="post" action="/RSAServlet">
		<input type="text" name="loginname" /> <input id="result"
			type="password" name="result" /> <input type="submit" />
	</form>
	<button onClick="cmdEncrypt()">Test</button>
</body>
</html>
