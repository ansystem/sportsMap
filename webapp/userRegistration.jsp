<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
	<!-- 共通部品の各種定義を呼び出す -->
	<c:import url="definition.jsp" charEncoding="UTF-8" />
	<meta charset="UTF-8">
	<title>新規登録 | SportsMap</title>
</head>
<body>
    <h2>新規登録</h2>
	    <!-- 共通部品のヘッダを呼び出す -->
		<c:import url="header.jsp" charEncoding="UTF-8" />
    
        <form action="checkMailAddress" method="post">
            <dl>
                <dt>ユーザID<span class="required">※</span></dt>
                <dd><input type="text" name="userId" maxlength="100" required></dd>
                <dt>ユーザ名<span class="required">※</span></dt>
                <dd><input type="text" name="userName" maxlength="100" required></dd>
                <dt>メールアドレス<span class="required">※</span></dt>
                <dd><input type="email" name="mailAddress" maxlength="100" required></dd>
                <dt>パスワード<span class="required">※</span></dt>
                <dd><input type="password" name="password" id="password" maxlength="100" required></dd>
                <dt>パスワード（確認用）<span class="required">※</span></dt>
                <dd><input type="password" name="password2" id="password2" maxlength="100" required></dd>
            </dl>
			<div class="errorMsg" style="display:none;" id="pwLenErrorMsg">パスワードは8文字以上にしてください</div>
			<div class="errorMsg" style="display:none;" id="pwErrorMsg">パスワードが一致していません</div>

            <button type="button" onclick="location.href='index.jsp'">Topへ</button>
            <input type="submit" value="登録する">
        </form>
        
       <c:if test="${ checkExistUser == 'duplicateUser' }">
        	<p>ユーザIDが既に使用されています</p>
       </c:if>
       <script src="js/passwordCheck.js"></script> 	
</body>
</html>