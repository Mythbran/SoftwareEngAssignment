<%-- 
    Document   : adminLogin
    Created on : Nov 1, 2017, 8:07:50 PM
    Author     : Oracle

    Description of class 
    This class will handle all admin logins
    It will take a user name and password 
    Compare it with a database 
    If yes, will pass to adminPortal 
    
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head> 
        <title>Admin Login</title>
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
            
            <h1> Admin login</h1>
            
            <form method="post" action="<c:url value="adminPortal.do"/>"/>
                    
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
