<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
    <meta charset="utf-8"/>
    <title>Login</title>
    <!-- <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"> -->
    <link rel="stylesheet" type="text/css" href="../common.css" />
    <link rel="stylesheet" type="text/css" href="./login.css" />


</head>

<body class="">
    


<div id="content" role="main">
    
    <div class="container">
        <div class="row">
            <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 m-t-3">
                <div class="card">
                    <div class="card-block">
                       <!--  <div class="m-a-2" style="display: block;text-align: center;">
                            login
                        </div> -->
                            <div class="p-a-1">
                                <form method="post" accept-charset="utf-8" action="login" novalidate>
                                   <!--  <div style="display:none;">
                                        <input type="hidden" name="_method" value="POST" class="form-control">
                                        <input type="hidden" name="_csrfToken" class="form-control">
                                    </div> -->
                                    <div class="form-group email">
                                        <label for="username">UserID</label>
                                        <input type="username" name="user.userId" placeholder="UserId" tabindex="1" class="form-control form-control-lg" id="username">
                                    </div>        
                                    <div class="userid-tip tip"><%if(request.getAttribute("error")!= null) {out.print(request.getAttribute("error"));} %></div>    
                                    <div class="form-group password">
                                        <label for="password">Password</label>
                                        <input type="password" name="user.password" placeholder="Password" tabindex="2" class="form-control form-control-lg" id="password">
                                    </div>                    
                                    <a href="/users/reset_password" tabindex="4" class="pull-xs-right">Forgot password?</a>
                                    
                                    <div class="form-group">
                                        <div class="checkbox-fancy-wrapper">
                                            <!-- <input type="hidden" name="remember_me" value="0" class="form-control"> -->
                                            <input type="checkbox" name="remember_me" value="1" checked="checked" class="eta" id="remember-me" class="checkbox-fancy-item">
                                            <label for="remember-me" class="radio-fancy-title">Remember me</label>
                                        </div>
                                    </div>
                
                                    <!-- <input type="hidden" name="redirect" id="redirect" value="/" class="form-control">   -->
                                    <input type="submit" value="Log In" class="btn btn-primary btn-block" tabindex="3">
                
                               <!--  <div style="display:none;">
                                    <input type="hidden" name="_Token[fields]" class="form-control">
                                    <input type="hidden" name="_Token[unlocked]" value="" class="form-control">
                                </div> -->
                                </form>
                            </div>
                        <div class="text-xs-center m-a-1">
                            <small>Don't have an account?</small>
                            <p class="small"><a href="/LibraryManagementSys/WebPage/register/register.jsp" tabindex="5" class="xs-padding-right-small">Create Your Account</a> or <a href="/LibraryManagementSys/WebPage/adminLogin/adminLogin.jsp" tabindex="5" class="xs-padding-left-small">管理员登陆</a></p>
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
    <script src="../common.js"></script>
    <script src="../app.js"></script>

</body>
</html>
