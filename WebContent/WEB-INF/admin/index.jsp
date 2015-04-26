<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
	pageContext.setAttribute("basePath",basePath); 
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" href="ico/icon.ico">
	<title>论坛后台</title>
	<link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/head.css">
    <link rel="stylesheet" href="css/tips.css">
    <link rel="stylesheet" href="css/admin/admin.css">
    <link rel="stylesheet" href="css/treeview/jquery.treeview.css">
	<script src="script/jquery-1.11.1.min.js"></script>
	<script src="script/treeview/jquery.treeview.js"></script>
	<script src="script/admin/index.js"></script>
	<script src="script/tips.js"></script>
</head>
<body>
	<!--主题区域-->
    <div class="main">
        <!--头部区域-->
		<jsp:include page="share/head.jsp"></jsp:include>
        
        <!--主体区域-->
        <div class="admin_main">
        	<!--左侧选项卡-->
        	<div class="tabs">
        		<!--标题-->
        		<div class="tab_title">
        			&nbsp;&nbsp;管理中心:
        		</div>

        		<!--一个选项标题，选项卡迭代从此开始-->
        		<div class="tab">
        			板块管理:
        		</div>
        		<!--tab详细地址-->
        		<ul class="tab_detail">
        		  <li name="s_l">
	                <a href="javascript:void(0);">板块列表</a>
	              </li>
        		</ul>
	            <!--选项迭代结束-->
	            <!-- 动态网页删除 -->
	            <div class="tab">
	              		帖子类别管理:
	            </div>
	            <ul class="tab_detail">
	              <li>
	                <a href="javascript:void(0);">类别列表</a>
	              </li>
	              <li>
	                <a href="javascript:void(0);">添加类别</a>
	              </li>
	            </ul>
	            <div class="tab">
	              	用户管理:
	            </div>
	            <ul class="tab_detail">
	              <li>
	                <a href="javascript:void(0);">用户列表</a>
	              </li>
	            </ul>
            	<!--动态删除结束-->
        	</div>

          <!--右侧面板-->
          <div class="r_d">
            <!--欢迎界面-->
            <div class="start">
              	欢迎使用后台管理
            </div>

            <!--板块列表面板-->
            <div class="s_l" id="s_l">
               <div class="s_l_tree">
                 <ul id="tree" class="treeview-red">
                 </ul>
               </div>
              <!--按钮-->
              <div class="s_l_op">
                <div class="btns">
                  <input type="button" onclick="add_top_section(this);" value="添加顶级版块">
                  <button name="add_child" disabled>添加子版块</button>
                  <button disabled onclick="delete_section();">删除版块</button>
                  <button name="edit_section" disabled>修改版块</button>
                  <button name="reomve_manager" disabled>删除版主</button>
                  <button name="add_manager" disabled>增加版主</button>
                </div>
                
                <!--功能面板默认界面-->
                <div class="op_default op_area">
                 	 请选择操作
                </div>
                <!--操作区，添加子版块-->
                <div class="op_area" id="add_child">
                  <div></div>
                  <table class="section_table" align="center">
                  	<tr>
                  		<td>板块名称:</td>
                  		<td><input type="text" name="name" id="s_c_n"></td>
                  	</tr>
                  	<tr>
                  		<td>版主id:</td>
                  		<td><input type="text" name="manager" id="s_c_m">(可选)</td>
                  	</tr>
                  	<tr>
                  		<td class="btn_td" colspan="2">
	                        <br>
	                        <button onclick="save_child_section(this);">保存</button>
	                        &nbsp;
	                        <span class="error"></span>
                      	</td>
                  	</tr>
                  </table>
                </div>
                <!--编辑板块-->
                <div class="op_area" id="edit_section">
                  <table class="section_table" align="center">
                    <tr>
                      <td>
                        	  请输入板块名称:
                      </td>
                      <td>
                          <input type="text" id="m_s_n">
                      </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="btn_td">
                            <input type="button" value="保存" onclick="modify_section(this);">
                            &nbsp;
                            <span class="error"></span>
                        </td>
                    </tr>
                  </table>
                </div>
                <!-- 删除版主 -->
                <div class="op_area" id="reomve_manager">
               	 <table class="section_table" align="center">
                   	<tr>
                   		<td>
                   			请选择需要删除的版主:
                   		</td>
                   	</tr>
                   	<tr>
                   		<td>
                   			<select id="managers" multiple="multiple">
                   				<option value="0">---版主---</option>
                   			</select>
                   		</td>
                   	</tr>
                    <tr>
                        <td class="btn_td">
                            <input type="button" value="删除" onclick="remove_manager(this);">
                            &nbsp;
                            <span class="error"></span>
                        </td>
                    </tr>
                  </table>
                </div>
                <!--增加版主-->
                <div class="op_area" id="add_manager">
                    <table class="section_table" align="center">
                        <tr>
                            <td>
                             	   版主id:
                            </td>
                            <td>
                                <input type="text" id="add_manager_value">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="btn_td">
                                <input type="button" value="保存" onclick="add_manager(this);">
                                &nbsp;
                                <span class="error"></span>
                            </td>
                        </tr>
                    </table>
                </div>
                <!--右侧功能区面板结束-->
                
              </div>
            </div>
          </div>
		</div>        
	</div>   
   <!-- 引入尾巴 -->
   <jsp:include page="../share/foot.jsp"></jsp:include>
</body>
</html>