<%-- 
    Document   : memberLogin
    Created on : Nov 1, 2017, 8:07:19 PM
    Author     : Oracle
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head> 
         <link rel="stylesheet" href="<c:url value="/css/main.css" />" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    
    <body>
        <main>
            <c:if test="${not empty errors}">
                <ul>
                    <c:forEach items="${errors}" var="e">
                        <li class="error">${e.message}</li>
                    </c:forEach>
                </ul>
            </c:if>
            
            <h1>Login</h1>
            <!Checks if a user/pass cookie is made, and if not it makes one->
            <%
    Cookie[] cookies = request.getCookies();
    if(cookies == null){
   String username=request.getParameter("uName");
    String password=request.getParameter("password");
        Cookie uNameCookie = new Cookie("uName-cookie", username);
        Cookie passwordCookie = new Cookie("password-cookie", password);
        uNameCookie.setMaxAge(24*60*60);
        passwordCookie.setMaxAge(24*60*60);
        response.addCookie(uNameCookie);
        response.addCookie(passwordCookie);
    }
   %>
            <form method="post" action="<c:url value="loginCheck.do"/>"/>
                    
                <p>
                    <label for="uName"> Username: </label>
                    <input id="uName" type="text" name="uName" value="<c:out value="${param.uName}"/>" required/>                   
                </p>
                
                <p>
                    <label for="password"> Password: </label>
                    <input id="password" type="password" name="password" value="<c:out value="${param.password}"/>" required/>   
                </p>
                <p>
                    <label>&nbsp;</label>
                    <input type="reset" value="Clear" name="Clear" />
                    <input type="submit" value="Login" name="Login" />     
                </p>                
                
            </form>
            <form action="<c:url value="hello.do"/>" method="post"> 
                <input type="submit" value="Back" />
            </form>            
            
        </main>
        
    </body>

</html>
