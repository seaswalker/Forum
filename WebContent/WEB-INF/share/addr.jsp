<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!--位置-->
<div style="line-height: 30px;border-top: 1px #9d9d9d solid;">
   <table>
       <tr>
           <td>
               <img src="images/home.png" style="margin-top:3px;margin-left:10px;vertical-align:top;width:16px;height:16px;">
           </td>
           <td>
               &gt;&gt;
           </td>
           <td class="main-font">
                <a href="index.html">skywalker</a>
                <!-- 显示位置项 -->
                <c:if test="${addr != null}">
                	<c:if test="${addr.psid > 0}">
                		&nbsp;&gt;&gt;
                		<a href="index.html?psid=${addr.psid}">${addr.pname}</a>
                	</c:if>
                	&nbsp;&gt;&gt;
                	<c:choose>
                		<c:when test="${addr.title == null}">
		               		<span>${addr.name}</span>
                		</c:when>
                		<c:otherwise>
                			<a href="forum.html?sid=${addr.sid}">${addr.name}</a>
                			&nbsp;&gt;&gt;
                			<span>${addr.title}</span>
                		</c:otherwise>
                	</c:choose>
                </c:if>
           </td>
       </tr>
   </table>
</div>