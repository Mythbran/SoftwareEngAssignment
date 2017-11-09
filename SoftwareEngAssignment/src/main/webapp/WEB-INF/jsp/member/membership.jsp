<%-- 
    Document   : membership
    Created on : Nov 1, 2017, 8:06:35 PM
    Author     : Oracle
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/main.css">       
        <title>Member signup</title>
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
            
            
            <h1>Membership Registration Form</h1>
            <form method="post" action="memberConfirm.do"/>
                <p>
                    <label for="first">First Name:</label>
                    <input id="first" type="text" name="fName" value="<c:out value="${param.fName}"/>" required/>
                   
              
                </p>
                <p>
                    <label for="last">Last Name:</label>
                    <input id="last" type="text" name="lName" value="<c:out value="${param.lName}"/>" required/>
                     
                </p>
                
                <p>
                    <label for="pNumber">Phone Number: </label>
                    <input id="pNumber" type="text" name="pNumber" value="<c:out value="${param.pNumber}"/>" required/>
                     
                </p>                  
                
                <p>
                    <label for="cCard">Credit Card: </label>
                    <input id="cCard" type="text" name="cCard" value="<c:out value="${param.cCard}"/>" required/>
                     
                </p>     
                
                <h2>Enter your account details</h2>
                <p>
                    <label for="uName">Username:  </label>
                    <input id="uName" type="text" name="uName" value="<c:out value="${param.uName}"/>" required/>
                     
                </p>  
                <p>
                    <label for="password">Password:  </label>
                    <input id="password" type="password" name="password" value="<c:out value="${param.password}"/>" required/>
                     
                </p>    
              

            </p>
                <p>
                    <label>&nbsp;</label>
                    <input type="reset" value="Clear" name="Clear" />
                    <input type="submit" value="Register" name="Register" />     
                </p>
            </form>
            <form action="<c:url value="hello.do"/>" method="post"> 
                <input type="submit" value="Back" />
            </form>
                    
        </main>
        
        
    </body>
    
    
</html>
