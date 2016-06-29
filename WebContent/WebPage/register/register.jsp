<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Login</title>

    <!-- <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"> -->
    <link rel="stylesheet" type="text/css" href="../common.css" />
    <link rel="stylesheet" type="text/css" href="./register.css" />


</head>

<body class="">
    


<div id="content" role="main">
    
    <div class="container">
        <div class="row">
            <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 m-t-3">
                <div class="card">
                    <div class="card-block">
                      <!--   <a href="/" class="image-link m-a-2" style="display: block;text-align: center;">
                            <img style="width: 100px;" src="" alt="Our Logo">
                        </a> -->
                            <div class="p-a-1">

                                <form method="post" accept-charset="utf-8" action="register" action="login" id="myform">
  
                                    <div class="form-group userid">
                                        <label for="userId">UserId</label>                                 
                                        <input type="text" name="user.userId"  placeholder="UserId" tabindex="1" class="form-control form-control-lg" id="usernId" required="true" pattern="\d{8}" title="请输入8位学号">
                                    </div>  
                                    <div class="userid-tip tip"><%if(request.getAttribute("status")!= null) {out.print(request.getAttribute("status"));} %></div>
                                    <div class="form-group email">
                                        <label for="useremail">Email</label>
                                        <input type="email" name="user.email" required="true" placeholder="Email" tabindex="2" class="form-control form-control-lg" id="useremail">
                                    </div>
                                    <div class="form-group username">
                                        <label for="username">Username</label>
                                        <input type="text" name="user.username" required="true" placeholder="Username" tabindex="3" class="form-control form-control-lg" id="username" >
                                    </div>           
                                    <div class="form-group password">
                                        <label for="password">Password</label>
                                        <input type="password" name="user.password" required="true" placeholder="Password" tabindex="4" class="form-control form-control-lg" id="password" pattern="\d{6,15}" title="密码长度为6~15位">
                                    </div>
                                    <div class="form-group repassword">
                                        <label for="password">Repassword</label>
                                        <input type="password" name="repassword" required="true" placeholder="Repassword" tabindex="5" class="form-control form-control-lg" id="repassword" pattern="\d{6,15}" title="密码长度为6~15位">
                                    </div>                    
                
                                    <!-- <input type="hidden" name="redirect" id="redirect" value="/" class="form-control">   -->
                                    <input type="submit" value="Create a new account" class="btn btn-primary btn-block" tabindex="6" id="submitButton">
                    
                                </form>
                            </div>
                        <div class="text-xs-center m-a-1">
                            <small>Already have an account?</small>
                            <p class="small"><a href="/LibraryManagementSys/WebPage/login/login.jsp" tabindex="5" class="xs-padding-right-small">Login in</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div> <!-- close #content -->


    <script src="../jquery.js"></script>
    <!-- <script src="//api.filepicker.io/v2/filepicker.js"></script> -->
    <script src="../bootstrap/js/bootstrap.js"></script>
    <script src="./register.js"></script>
    <script src="../common.js"></script>
    <script src="../app.js"></script>

</body>
</html>

