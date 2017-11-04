<%-- 
    Document   : addCar
    Created on : Nov 3, 2017, 5:29:30 PM
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
            
            
            <h1>Add a new Car</h1>
            <form method="post" action="memberLogin.do"/>
                <p>
                    <label for="model">Model of Car: </label>
                    <input id="model" type="text" name="model" value="<c:out value="${param.model}"/>" required/>
                   
              
                </p>
                <p>
                    <label for="Price">Price: </label>
                    <input id="price" type="text" name="price" value="<c:out value="${param.price}"/>" required/>
                     
                </p>
                
                <p>
                    <label for="availability">Availability: </label>
                    <input id="availability" type="text" name="availability" value="<c:out value="${param.availability}"/>" required/>
                     
                </p>                  
                
                <p>
                    <label for="location">Location: </label>
                    <input id="location" type="text" name="location" value="<c:out value="${param.location}"/>" required/>
                     
                </p>     
                <p>
                    <label>&nbsp;</label>
                    <input type="reset" value="Clear" name="Clear" />
                    <input type="submit" value="Register" name="Add" />     
                </p>
            </form>
            <form action="<c:url value="hello.do"/>" method="post"> 
                <input type="submit" value="Back" />
            </form>

        </main>
    </body>
</html>
