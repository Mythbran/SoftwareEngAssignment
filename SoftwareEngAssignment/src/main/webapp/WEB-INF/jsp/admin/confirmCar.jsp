<%-- 
    Document   : confirmCar
    Created on : Dec 15, 2017, 3:20:24 AM
    Author     : Owner
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Car Confirmation</title>
    </head>
    <body>
        <main>
            <h1>Confirm Car Data is Accurate</h1>
            <p>
                <label>Model:  </label>
                <span class="output"><c:out value="${car.model}"/></span>
            </p>
            <p>
                <label>Make: </label>
                <span class="output"><c:out value="${car.make}"/></span>
            </p>
            <p>
                <label>Price: </label>
                <span class="output"><c:out value="${car.price}"/></span>
            </p>
            <p>
                <label>Availability: </label>
                <span class="output"><c:out value="${car.availability}"/></span>
            </p>
            <p>
                <label>Location: </label>
                <span class="output"><c:out value="${car.location}"/></span>
            </p>
            
          
                <form method="post" action="<c:url value="carConfirm.do"/>">
                    <input type="submit" value="Submit"/>
                </form>
                
                <form method="post" action="<c:url value="membership.do"/>">
                    <input type="submit" value="Go Back"/>
                </form>  
          
        </main>
    </body>