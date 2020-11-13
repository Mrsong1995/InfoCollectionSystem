<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../../common/taglibs.jsp" %>
<%@include file="other.jsp"%>
<div id="header" >
		<div id="headerCenter"  class="bodyCenter">
			<div class="header_Item header_logo">
			<%@ include file="logo-img.jsp"%>
			</div>
			<shiro:guest>
				<div class="header_Item header_menu">
				<ul>
				<%--<li><a href="/" class="active dw-menu-a" id="indexMenu">首页</a></li>--%>
				<%--<li><a href="/feature.jsp" class="dw-menu-a" id="featureMenu">功能</a></li>--%>
				<%--<li><a href="${ctx }/survey-model.controller" class="dw-menu-a">下载</a></li>--%>
				<%--<li><a href="${ctx }/survey-model.controller" class="dw-menu-a">帮助</a></li>--%>
				<%--<li><a href="${ctx }/survey-model.controller" class="dw-menu-a">GITHUB</a></li>--%>
				<!--
				<li><a href="http://support.diaowen.net/" class="dw-menu-a" id="helpMenu">帮助</a></li>
				-->
				</ul>
				</div>
				<div class="header_Item header_user" style="float: right;">
					<a href="${ctx }/login.jsp" class="btn-a-1">登录</a>
				</div>
			</shiro:guest>
			
			<shiro:user>
				<div class="header_Item header_menu">
					<ul>
					<%-- <li><a href="${ctx }/" >首页</a></li> --%>
					<li><a href="${ctx }/design/my-survey/list" id="mysurvey">模板</a></li>
						<shiro:hasRole name="admin" >
					<li><a href="${ctx }/sy/user/user-admin/list" id="usermanager">用户</a></li>
					<li><a href="${ctx }/sy/system/sys-property/input" id="systemset">设置</a></li>
						</shiro:hasRole>
					<%--http://www.diaowen.net/--%>
					</ul>
				</div>
				<div class="header_Item header_user" style="float: right;margin-top: 12px;position: relative;zoom: 1;z-index: 165;">
					<a href="#" class="clickHideUserMenu">
						<span class="head_use_name">
						<shiro:principal></shiro:principal>
						</span>
						<span class="header_user_icon">&nbsp;</span>
					</a>
					<div class="a-w-sel a-w-sel-head" style="">
		            	<div class="w-sel" style="margin-top: 16px;">
		                	<div class="selc">
		                    	<div class="selcc tbtag">
		                            <div class="seli"><a class="nx-1" href="${ctx }/ic/user/myaccount">修改密码</a></div>
		                            <div class="seli"><a class="nx-8" href="${ctx }/logout">退出</a></div>
		                        </div>
		                    </div>
		                </div>
		            </div>
				</div>
			</shiro:user>
		</div>
		<div style="clear: both;"></div>
	</div>