<%-- 
    Document   : memberPortal
    Created on : Dec 14, 2017, 6:48:48 PM
    Author     : Owner
--%>
  

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link rel="stylesheet" href="<c:url value="/css/main.css" />" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Control Panel</title>
    <h1>Member Portal</h1>
    </head>
    <body>
        <p>
            <form action="<c:url value="memberEdit.do"/>" method="post">
                <input type="submit" value="Edit Your Information" name="memberEdit" />
            </form>
        </p>
        <p>
            <form action="<c:url value="rentals.do"/>" method="post">
                <input type="submit" value="Place an Order" name="addOrder" />
            </form>
        
        <p>
         
        </p>
        <p>
            <form action="<c:url value="hello.do"/>" method="post">
                <input type="submit" value="Home" name="Home" />
            </form>
        </p>
    </body>
</html>
