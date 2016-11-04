<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script> 
var isEnd = false;
var page=0;
var render = function(vo){
	//
	// 현업에서는 이부분을 template library 로 쓰임 ex) ejs
	//
	var htmls=
		"<li id='gb-"+vo.no+"'>"+
		"<strong>" +vo.name +"</strong>"+
		"<p>"+vo.content.replace(/\n/gi,"<br>")+"</p>"+
		"<strong>"+vo.req_date+"</strong>"+
		"<a href='' data-no='"+vo.no+"'+>삭제</a>"+
		"</li>";
		
		$("#list-guestbook").append(htmls);
		
}
var fetchList = function(){
	if(isEnd ==true){
		return;
	}
	++page;
	$.ajax({
		url :"${pageContext.request.contextPath }/api/guestbook?a=ajax-list&p="+page,
		type : "get",
		dataType: "json",
		success:function(response){ //response.result="success" or "fail" 
									//response.data 가 배열로 나옴 =  [{}, {},{} ...]
			if(response.result != "success"){
				console.error(response.message);
				isEnd=true;
				return;
			}
			//redering
			$(response.data).each(function(index, vo){
				render(vo);				
			});
			
			if(response.data.length<5){ 
				isEnd=true;
				$("#btn-fetch").prop("disabled",true);
			}
		}, error: function(jqXHR, status, e){
			console.error(status +":"+e);
		}
	})
}

  
$(function(){
	
	//삭제버튼 클릭 이벤트 (live event)
	$(document).on("click","#list-guestbook li a",function(event){
		event.preventDefault();
		$("#password-id").val($(this).attr("data-no"));
		console.log("테스트중");
		
		dialog = $( "#delete-form" ).dialog({
		    autoOpen: false,
		    height: 200,
		    width: 350,
		    modal: true,
		    buttons: {
		      "삭제": function(){
		    	  var no =$("#password-id").val();
		    	  var password =$("#password").val();
		    	  console.log(no);
		    	  console.log(password);
		    	  $.ajax({
		    			url :"${pageContext.request.contextPath }/api/guestbook?a=ajax-delete&no="+no+"&password="+password,
		    			type : "get",
		    			dataType: "json",
		    			data:"",
		    			success:function(response){ //response.result="success" or "fail" 
		    										//response.data 가 배열로 나옴 =  [{}, {},{} ...]
		    				if(response.result != "success"){
		    					console.error(response.message);
		    					isEnd=true;
		    					return;
		    				}
		    				dialog.dialog( "close" );
		    				$(response.data).each(function(index, vo){
		    					render(vo);				
		    				});
		    				
		    				$("#gb-"+no).remove(); // html에 li 를 삭제 자동적으로 해주는것. 
		    			   	
		    			}, error: function(jqXHR, status, e){
		    				console.error(status +":"+e);
		    			}
		    		})
		      },
		      Cancel: function() {
		        dialog.dialog( "close" );
		      }
		    },
		    close: function() {
		    // $(this).dialog("close");
		    return;
		     
		    }
		  });
		dialog.dialog("open");
		
	})
	/* 
	$("#list-guestbook li").click(function(){
		console.log("테스트중");
	})
	 */
	$("#add-form").submit(function(event){
		event.preventDefault();
		
		//ajax insert
		/*
		url => api/guestbook
		type=>post
		dataType=>json
		data=>a=ajax-insert&name=?&content=?&password=?
				
		
		response={
				result="success"
				data{
					no: ? ,
					name: ?,
					content:?   ......
				}
		}
		*/
		
	})
	$(window).scroll(function(){
		var $window = $(this);
		var scrollTop = $window.scrollTop(); // 브라우저에서 위에서 부터 scroll회색 나타나기 전까지 그부분 길이 
		var windowHeight=$window.height(); // 브라우저 height 길이
		var documentHeight=$(document).height();
		//스크롤 바가 바닥까지 왔을 때
		if(scrollTop+windowHeight +10 > documentHeight){
			fetchList();
		}
		//console.log(scrollTop+":"+windowHeight+":"+documentHeight);
	})
	$("#btn-fetch").click(function(){ //버튼이 있을 경우에 눌릴때 5개씩 보여주는거 하지만 삭제 했으므로 필요없어짐.
		fetchList();
	}) 
		//1번째 리스트 가져오기
		fetchList();
})

</script>
</head>
<body>
	<div id="container">
	<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook" class="">
				<form  id ="add-form" action=" " method="post">
					<input type="hidden" name="a" value="insert">
					<table class = "write-form">
						<tr>
						</tr>
						<tr>
							<td><input type="text" name="name" placeholder="이름을 입력하세요"></td>
						</tr>
						<tr>
							<td><input type="password" name="pass" placeholder="비밀번호를 입력하세요"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content" placeholder="소감을 작성하시오"></textarea></td>
						</tr>
						<tr >
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul id="list-guestbook">

				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
<div id="delete-form" title="방명록 삭제" style="display:none">
  <form>
    <fieldset>
      <label >Password</label>
      <input type="hidden" name="password-id" id="password-id" value="" >
      <input type="password" name="password" id="password" value="" >
		<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
    </fieldset>
  </form>
</div>
 
</body>
</html>