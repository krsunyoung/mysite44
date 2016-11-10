<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<%pageContext.setAttribute( "newLine", "\n" );%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="gallery">
				<div>
					<h1>갤러리</h1>
				<a href="${pageContext.request.contextPath }/gallery/form" id="upload-image">이미지 올리기</a>
				</div>
				<ul>
					<li>
						<a href="" class="image" style="background-image:url('${pageContext.request.contextPath }/${url }')">&nbsp;</a>
						<a href="" class="del-button"	title="삭제">삭제</a>
					</li>	
					<li>
						<a href="" class="image" style="background-image:url('${pageContext.request.contextPath }/assets/gallery/im2.jpg')">im2.jpg</a>
						<a href="" class="del-button"	title="삭제">삭제</a>

					</li>	
				</ul>	
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="gallery"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>