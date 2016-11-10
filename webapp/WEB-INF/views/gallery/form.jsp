<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<style>
#center { position:absolute; top:50%; left:50%; width:300px; height:200px; overflow:hidden;}
</style>
</head>
<body>
<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="gallery">
				<div>
					<h1>갤러리</h1>
					<a href="${pageContext.request.contextPath }/gallery" id="upload-image">리스트 가기 </a>
				</div>
				<br><br><br>
					<form  method="post" action="upload" enctype="multipart/form-data">
							<label>파일 :</label>
							<input type="file" name="file">
							<br><br>

							<label>코멘트 :</label>
							<input type="text" name="comments" value="">
							<br><br>
							
							<input type="submit" value="업로드">
					</form>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="gallery"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>