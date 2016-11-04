<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
	<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook" class="">
				<form action="${pageContext.request.contextPath }/guestbook/insert" method="post">
					<table class = "write-form">
						<tr>
						</tr>
						<tr>
							<td><input type="text" name="name" placeholder="이름을 입력하세요"></td>
						</tr>
						<tr>
							<td><input type="password" name="password" placeholder="비밀번호를 입력하세요"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content" placeholder="소감을 작성하시오"></textarea></td>
						</tr>
						<tr >
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
				<c:set var="count" value="${fn:length(list) }"/> 
				<c:forEach items="${list }" var="vo" varStatus="status">
					<li>
					<li>
							<strong>${vo.name }</strong>
							<p>
								${vo.content }
							</p>
							<strong>${vo.req_date }</strong>
							<a href="${pageContext.request.contextPath }/guestbook/deleteform/${vo.no }" title="삭제">삭제</a>
						</li>
				
						<br>
					</li>
				</c:forEach>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>