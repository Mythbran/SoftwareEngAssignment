<%-- 
    Document   : memberConfirm
    Created on : Nov 8, 2017, 11:53:10 PM
    Author     : Owner
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Your Information</title>
    </head>
    <body>
        <main>
            <h1>Entered Data:</h1>
            <p>
                <label>First Name: </label>
                <span class="output"><c:out value="${member.fName}"/></span>
            </p>
            <p>
                <label>Last Name: </label>
                <span class="output"><c:out value="${member.lName}"/></span>
            </p>
            <p>
                <label>Phone Number: </label>
                <span class="output"><c:out value="${member.pNumber}"/></span>
            </p>
            <p>
                <label>Credit Card: </label>
                <span class="output"><c:out value="${member.cCard}"/></span>
            </p>
            <p>
                <label>Username: </label>
                <span class="output"><c:out value="${member.uName}"/></span>
            </p>
            
            <form method="post" action="<c:url value="memberSubmit.do"/>">
                <input type="submit" value="Submit"/>
            </form>
                
            <form method="post" action="<c:url value="membership.do"/>">
                <input type="submit" value="Go Back"/>
            </form>  
        </main>
    </body>
</html>
