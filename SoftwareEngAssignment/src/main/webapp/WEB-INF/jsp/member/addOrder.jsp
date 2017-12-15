<%-- 
    Document   : addOrder
    Created on : 13-Dec-2017, 11:04:53
    Author     : Dan
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/main.css">       
        <title>Rent Car</title>
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
            
            
            <h1>Add a new Order</h1>
            <form method="post" action="submitOrder.do"/>
                <p>
                    <label for="model">User ID: </label>
                    <input id="model" type="text" name="model" value="<c:out value="${member.uid}"/>" readonly/>
                   
              
                </p>
                <p>
                    <label for="id">Car ID: </label>
                    <input id="id" type="text" name="id" value="<c:out value="${order.id}"/>" required/>
                     
                </p>
                
                <p>
                    <label for="dateRented">Date of Rental(YYYY-MM-DD): </label>
                    <input id="dateRented" type="text" name="dateRented" value="<c:out value="${order.dateRented}"/>" required/>
                     
                </p>                     
                <p>
                    <label>&nbsp;</label>
                    <input type="reset" value="Clear" name="Clear" />
                    <input type="submit" value="Add" name="Add" />     
                </p>
            </form>
            <form action="<c:url value="hello.do"/>" method="post"> 
                <input type="submit" value="Back" />
            </form>

        </main>
    </body>
</html>
