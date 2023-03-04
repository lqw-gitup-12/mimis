<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
		<title></title>
		
	</head>
<%
    String  valuename="";
	String valuepwd="";
	Cookie[] cookies = request.getCookies();
	if(cookies!=null){
		for(Cookie a:cookies)
		{
			if("name".equals(a.getName()))
			{
				valuename=a.getValue();
			}
			if("pwd".equals(a.getName()))
			{
				valuepwd=a.getValue();
			}
		}
	}
%>
	<body>
		<div id="login">
			<div id="top">
				<img src="${pageContext.request.contextPath}/images/cloud.jpg" /><span>LOGIN</span>
			</div>
			<div id="bottom">
				<form  action="${pageContext.request.contextPath}/admin/login.action" method="post">
					<table border="0px" id="table">
						<tr>
							<td class="td1">用户名：</td>
							<td><input type="text" value="<%=valuename%>" placeholder="Username" class="td2" name="name"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="nameerr"></span></td>
						</tr>
						<tr>
							<td class="td1">密码：</td>
							<td><input type="password" value="<%=valuepwd%>" placeholder="Password" class="td2" name="pwd"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="pwderr"></span></td>
						</tr>
						<tr>
							<td  id="td5" colspan="2" align="center">
								记住账户和密码10天<input type="radio" name="sign" value="1">
								<%-- 如果选了这个键将来通过request通过name获得的值就是valuse的值--%>
							</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="登录" class="td3">
								<a href="${pageContext.request.contextPath}/regist.jsp"><input type="button" value="注册" class="td3	"></a>
							</td>
						</tr>
					</table>
				</form>
				${errmsg}
			</div>

		</div>
	</body>

</html>