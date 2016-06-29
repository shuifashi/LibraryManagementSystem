<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="org.apache.struts2.ServletActionContext"
	import="com.opensymphony.xwork2.ActionContext"
	import="com.LMS.model.beans.Book"
	import="com.LMS.model.beans.Record"
	import="com.LMS.model.beans.User"
	import="java.util.List"
	import="java.util.Map"
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
  <meta charset="utf-8"/>
  <title>userinfo</title>
  <!-- <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  -->
  <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
  <link rel="stylesheet" type="text/css" href="../userinfo/userinfo.css" />
  <link rel="stylesheet" type="text/css" href="../navbar.css" />

</head>

<body>
  <nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">图书馆书籍管理系统</a>
      </div>
    </div>
  </nav>
<% User user =  (User)ActionContext.getContext().getSession().get("userinfo");%>
  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-3 col-md-2 sidebar z-depth-1 sidebar-font">
        <ul class="nav nav-sidebar">
        <% String userType = (String)ActionContext.getContext().getSession().get("userType"); 
        	if(userType.equals("user")) {
        		String userId = (String)session.getAttribute("userId");
        		out.print("<li><a href='/LibraryManagementSys/WebPage/userinfo/userInfo?record.userId ="+userId+"'>个人详细</a></li>");
        		out.print("<li><a href='/LibraryManagementSys/WebPage/power-search/psearch.jsp'>高级搜索</a></li>");
        		out.print("<li><a href='/LibraryManagementSys/WebPage/Booksearch/search.jsp'>搜索主页</a></li>");
        	} else {
				out.print("<li><a href='/LibraryManagementSys/WebPage/BorrowAndReturn/BorrowAndReturn.jsp'>登记借书/还书</a></li>");
				out.print("<li><a href='/LibraryManagementSys/WebPage/manageList/findBorrowRecord'>查询借书记录</a></li>");
				out.print("<li><a href='/LibraryManagementSys/WebPage/manageList/findReserveRecord'>查询预约记录</a></li>");
				out.print("<li><a href='/LibraryManagementSys/WebPage/addNewBook/addNewBook.jsp'>添加书籍</a></li>");
        	}
        %> 
        </ul>

        <ul class="nav nav-sidebar">
          <li>
            <a href="/LibraryManagementSys/WebPage/logout/logout.jsp">Logout</a>
          </li>

        </ul>
      </div>
      <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">



            <div class="row">

              <!-- 个人信息面板 -->
              <div class="col-md-8 col-md-offset-2 userinfo-content ">
                <div class="panel panel-default panel-equal z-depth-1">
                  <div class="panel-body text-center">
                    <div class="col-md-3">
                      <img src="../userinfo/photo.jpg" alt="..." class="img-circle"></div>
                    <div class="col-md-8">
                      <form class="form-horizontal">
                        <div class="form-group">
                          <label class="col-sm-2 control-label">Email</label>
                          <div class="col-sm-10">
                            <p class="form-control-static"><%= user.getEmail() %></p>
                          </div>
                        </div>
                        <div class="form-group">
                          <label for="inputPassword" class="col-sm-2 control-label">username</label>
                          <div class="col-sm-10">
                            <p class="form-control-static"><%= user.getUsername() %></p>
                          </div>
                        </div>
                        <div class="form-group">
                          <label for="inputPassword" class="col-sm-2 control-label">userid</label>
                          <div class="col-sm-10">
                            <p class="form-control-static"><%= user.getUserId() %></p>
                          </div>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
              
               <%    List<Object> resultRecord = (List<Object>)ActionContext.getContext().getSession().get("Recordlist");
                     Map<String, Book> bookmap = (Map<String, Book>)ActionContext.getContext().getSession().get("bookmap");
               %>

              <!-- 书籍信息列表 -->
              <div class="col-md-8 col-md-offset-2 userinfo-content">
                <ul id="userinfo-myTab" class="nav nav-tabs">
                  <li class="active">
                    <a href="#userinfo-booking-tab" data-toggle="tab">预订</a>
                  </li>
                  <li>
                    <a href="#userinfo-booked-tab" data-toggle="tab">已借</a>
                  </li>

                </ul>
                <!-- tab框 -->
                <div id="user-info-myTabContent" class="tab-content">
                  <!-- 预订框 -->
                  <div class="tab-pane fade in active" id="userinfo-booking-tab">
                  
                  <% if(resultRecord != null && bookmap != null) {
                	  for(int i = 0; i < resultRecord.size();i++) {
                		Record record = (Record)resultRecord.get(i);
                		Book book = bookmap.get(record.getBookId());
                		if(record.getType() == 1) {
                	  		out.println("<div class='panel panel-default'>");
                	  		out.println("<div class='userinfo-booked tab-pane'>");
                	  		out.println("<div class='media user-booinfo'>");
                	  		out.println("<div class='media-body'>");
                	  		out.println("<div class='col-md-6'>");
                	  		out.println("<h4 class='media-heading'>"+book.getBookName()+"</h4>");
                	  		out.println("</div>");
                	  		out.println("<div class='col-md-3'>");
                	  		out.println("<h6 >开始日期:"+record.getBeginTime()+"</h6>");
                	  		out.println("</div>");
                	  		if(record.getEndTime() != null)
                	  			out.println(" <div class='col-md-3'><h6>结束日期:"+record.getEndTime()+"</h6></div>");
                	  		else
                	  			out.println(" <div class='col-md-3'><h6>结束日期:还未预约成功</h6></div>");
                	  		out.println(book.getBookAbstract());
                	  		out.println("</div>");
                	  		out.println("<div class='col-md-2 col-md-offset-10'><form action='delreserveBook'>");
                	  		out.println("<input type='text' name='record.userId' value='"+record.getUserId()+"' style='display:none'>");
                	  		out.println("<input type='text' name='record.bookId' value='"+record.getBookId()+"' style='display:none'>");
                	  		out.println("<input type='submit' class='btn btn-primary' value='取消预约' ></form></div>");
                	  		out.println("</div>");
                	  		out.println("</div>");
                	  		out.println("</div>");
                	  		//out.println("</div>");
                		}
                	  }  
                  }
                	%>   
                  </div>

                  <!-- 已借框 -->
                  <div class="tab-pane fade" id="userinfo-booked-tab">
                    
                    <% if(resultRecord != null && bookmap != null) {
                	  for(int i = 0; i < resultRecord.size();i++) {
                		Record record = (Record)resultRecord.get(i);
                		Book book = bookmap.get(record.getBookId());
                		if(record.getType() == 0) {
                	  		out.println("<div class='panel panel-default '>");
                	  		out.println("<div class='userinfo-booking tab-pane active' id='userinfo-booking-tab'>");
                	  		out.println("<div class='media user-booinfo'>");
                	  		out.println("<div class='media-body'>");
                	  		out.println("<div class='col-md-6'>");
                	  		out.println("<h4 class='media-heading'>"+book.getBookName()+"</h4>");
                	  		out.println("</div>");
                	  		out.println("<div class='col-md-3'>");
                	  		out.println("<h6 >开始日期:"+record.getBeginTime()+"</h6>");
                	  		out.println("</div>");
                	  		if(record.getEndTime() != null)
                	  			out.println(" <div class='col-md-3'><h6>结束日期:"+record.getEndTime()+"</h6></div>");
                	  		else
                	  			out.println(" <div class='col-md-3'><h6>结束日期:还未预约成功</h6></div>");
                	  		out.println(book.getBookAbstract());
                	  		out.println("</div>");
                	  		out.println("</div>");
                	  		out.println("</div>");
                	  		out.println("</div>");
                		}
                	  }  
                  }
                	%>  
                  </div>
                </div>

              </div>
            </div>


      </div>
    </div>
  </div>

  <!-- close #content -->

  <script src="../jquery.js"></script>
  <!-- <script src="//api.filepicker.io/v2/filepicker.js"></script>
-->
<script src="../bootstrap/js/bootstrap.js"></script>
<script src="../userinfo/userinfo.js"></script>

</body>
</html>