<%-- 
    Document   : editCar
    Created on : Nov 3, 2017, 5:43:47 PM
    Author     : Oracle
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link rel="stylesheet" href="<c:url value="/css/main.css" />" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Car</title>
    </head>      
        <main>
            <c:if test="${not empty errors}">
                <ul>
                    <c:forEach items="${errors}" var="e">
                        <li class="error">${e.message}</li>
                    </c:forEach>
                </ul>
            </c:if>
            <h1>Update Car Information</h1>
            <form method="post" action="<c:url value="confirmCarEdit.do"/>"/>
                <input type="hidden" name="id" value="${car.id}"/>
                <p>
                    <label for="make">Car Make:</label>
                    <input id="make" type="text" name="make" value="<c:out value="${car.make}"/>" required/>
                   
              
                </p>
                <p>
                    <label for="model">Car Model:</label>
                    <input id="model" type="text" name="model" value="<c:out value="${car.model}"/>" required/>
                     
                </p>
                <p>
                    <label for="price">Price: </label>
                    <input id="price" type="text" name="price" value="<c:out value="${car.price}"/>" required/>
                </p>
                <p>
                    <label for="availability">Availability: </label>
                    <input id="availability" type="text" name="availability" value="<c:out value="${car.availability}"/>" required/>
                </p>
                <p>
                    <label for="location">Location:  </label>
                    <input id="location" type="text" name="location" value="<c:out value="${car.location}"/>" />            
                </p>
                                

               
                <p>
                    <label>&nbsp;</label>
                    <input type="submit" value="Send"/>            
                    <form action="<c:url value="viewAllCars.do"/>" method="post"> 
                        <input type="submit" value="View All" />
                    </form>
                </p>
            </form>
                    
</html>
