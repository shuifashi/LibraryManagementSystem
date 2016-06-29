<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>
  <title>Search</title>
  <!-- <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  -->
  <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
  <link rel="stylesheet" type="text/css" href="../power-search/psearch.css" />

</head>

<body>

  <div id="content" role="main">

    <div class="container">

      <div class="row">
        <div class="nav">
          <div class="logout col-md-1 col-md-offset-10"><a href="/LibraryManagementSys/WebPage/logout/logout.jsp">logout</a></div>
          <div class="userinfo col-md-1"><span class="glyphicon glyphicon-user" aria-hidden="true"><a href="/LibraryManagementSys/WebPage/userinfo/userInfo?record.userId =<%out.print(session.getAttribute("userId"));%>"><% if(session.getAttribute("userId")!= null) {out.print(session.getAttribute("userId"));} %></a></span></div>
        </div>
        <div class="logo col-md-4 col-md-offset-4 text-center">Search</div>
        <div class="searchArea col-md-12">
          <form action="superfindBook">
            <div class="input-group col-md-8 col-md-offset-2 form-group">
              <input type="text" name = "book.bookName" class="form-control" placeholder="please input" aria-describedby="basic-addon2">
              <span class="input-group-addon" id="basic-addon2">书名</span>
            </div>
            <div class=" input-group col-md-8 col-md-offset-2 form-group">
              <input type="text" name = "book.Author" class="form-control" placeholder="please input" aria-describedby="basic-addon2">
              <span class="input-group-addon" id="basic-addon2">作者</span>
            </div>
            <div class=" input-group col-md-8 col-md-offset-2 form-group">
              <input type="text" name = "book.Publisher" class="form-control" placeholder="please input" aria-describedby="basic-addon2">
              <span class="input-group-addon" id="basic-addon2">出版社</span>
            </div>
            <div class="col-md-offset-5 col-md-2 text-center">
             <input type="submit" class="btn btn-primary btn-block" id="pearch-submit-button" value="search">

            </div>

          </form>
        <div class="power-search col-md-2 col-md-offset-5">
                <a href="/LibraryManagementSys/WebPage/Booksearch/search.jsp"><button class="btn btn-primary btn-block" id="power-search-button">普通搜索</button></a>
       	</div>
      </div>
    </div>

  </div>
  <!-- close #content -->

  <script src="../jquery.js"></script>
  <!-- <script src="//api.filepicker.io/v2/filepicker.js"></script>
-->
<script src="../bootstrap/js/bootstrap.js"></script>
<script src="../common.js"></script>
<script src="../app.js"></script>

</body>
</html>