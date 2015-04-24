<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>分页部分</title>
	<script src="script/page.js"></script>
</head>
<body>
	<!--分页以及发帖-->
     <div class="page">
         <ul class="page_code">
             <li>
             	<c:if test="${pageBean.currentPage == 1}">
             		首页
             	</c:if>
             	<c:if test="${pageBean.currentPage > 1}">
             		<a href="javascript:jump(1);">首页</a>
             	</c:if>
             </li>
             <!-- 迭代页码 -->
             <c:if test="${pageBean.pageCount >= 1}">
	             <c:forEach begin="${pageBean.pageBeginIndex}" end="${pageBean.pageEndIndex - 1}" var="index">
	              <li>
	             	 <c:if test="${index == pageBean.currentPage}">
	             	 	<span class="active">${index}</span>
	             	 </c:if>
	             	 <c:if test="${index != pageBean.currentPage}">
	              	 <a href="javascript:jump(${index});">${index}</a>
	             	 </c:if>
	              </li>
	             </c:forEach>
	          </c:if>
             <!-- 尾页 -->
             <li>
             	<c:if test="${pageBean.currentPage == pageBean.pageCount}">
             		<span class="active">...${pageBean.pageCount}</span>
             	</c:if>
             	<c:if test="${pageBean.currentPage < pageBean.pageCount}">
             		<a href="javascript:jump(${pageBean.pageCount});">...${pageBean.pageCount}</a>
             	</c:if>
             </li>
             <li>
                 <input type="text" id="pagecode" style="width:30px;"> / ${pageBean.pageCount}
                 &nbsp;
                 <span style="color:#8BC9D5;cursor:pointer;" onclick="go(${pageBean.pageCount})">Go</span>
              </li>
             <li>
             	 <c:if test="${pageBean.currentPage == pageBean.pageCount}">
             	 	下一页
             	 </c:if>
             	 <c:if test="${pageBean.currentPage < pageBean.pageCount}">
                	 <a href="javascript:jump(${pageBean.currentPage < pageBean.pageCount - 1 ? pageBean.currentPage + 1 : pageBean.pageCount});">
                	 	下一页
                	 </a>
                 </c:if>
             </li>
         </ul>
     </div>
</body>
</html>