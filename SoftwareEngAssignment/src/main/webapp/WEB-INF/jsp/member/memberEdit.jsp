<%-- 
    Document   : memberEdit
    Created on : Dec 14, 2017, 6:56:10 PM
    Author     : Owner
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="<c:url value="/css/main.css" />" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Edit</title>
    </head>      
        <main>
            <c:if test="${not empty errors}">
                <ul>
                    <c:forEach items="${errors}" var="e">
                        <li class="error">${e.message}</li>
                    </c:forEach>
                </ul>
            </c:if>
            <h1>Edit Your Information</h1>
            <form method="post" action="<c:url value="memberConfirmEdit.do"/>"/>
                <input type="hidden" name="uid" value="${member.uid}"/>
                <p>
                    <label for="first">First Name:</label>
                    <input id="first" type="text" name="fName" value="<c:out value="${member.fName}"/>" required/>
                   
              
                </p>
                <p>
                    <label for="last">Last Name:</label>
                    <input id="last" type="text" name="lName" value="<c:out value="${member.lName}"/>" required/>
                     
                </p>
                <p>
                    <label for="pNumber">Phone Number: </label>
                    <input id="pNumber" type="text" name="pNumber" value="<c:out value="${member.pNumber}"/>" required/>
                </p>
                <p>
                    <label for="cCard">Credit Card: </label>
                    <input id="cCard" type="text" name="cCard" value="<c:out value="${member.cCard}"/>" required/>
                </p>
      
                <p>
                    <label>&nbsp;</label>
                    <input type="submit" value="Send"/>            

                </p>

        </main>
        <footer>
               
        </footer>
</html>
