<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<div class="head-title">
	  <!-- 错误提示窗体 -->
	  <div id="e_w">
	  </div>
	
      <div class="title-logo">
           <img src="images/logo.png">
      </div>
      <div class="title-font">
          Forum of skywalker
      </div>
      
      <!--显示个人信息(已登录)-->
      <div class="p_info">
          <table align="right" style="width: 80%;">
              <tr>
                  <td>
                  	<c:choose>
                  		<c:when test="sessionScopse.user.isAdmin">
		                    <span class="strong-font">欢迎管理员: ${sessionScope.user.username}</span>
                  		</c:when>
                  		<c:otherwise>
		                    <span class="strong-font">欢迎版主: ${sessionScope.user.username}</span>
                  		</c:otherwise>
                  	</c:choose>
                  </td>
                  <td class="main-font">
                      &nbsp;|&nbsp;
                  </td>
                  <td>
                      <a href="logout.html" class="main-font">退出</a>
                  </td>
                  <td>
                      &nbsp;
                  </td>
                  <td rowspan="2">
                      <img src="${sessionScope.user.avatar}" style="width:80xp;height:80px;">
                  </td>
              </tr>
              <tr>
                <td colspan="3" class="tr_set main-font">
               	  <!-- 后台出口-->
            	  <a href="index.html">论坛主页</a>
            	  &nbsp;|&nbsp;
                  <a href="user/set.html">设置</a>
                </td>
              </tr>
          </table>
      </div>
   </div>
