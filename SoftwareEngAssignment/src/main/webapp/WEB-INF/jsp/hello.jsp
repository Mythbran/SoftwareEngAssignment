<%-- 
    Document   : hello
    Created on : Nov 1, 2017, 7:11:05 PM
    Author     : Oracle
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    
    <body>
        <main>
            <h1>Welcome to vehicle rental system</h1>
            <p>
                <form action="<c:url value="rentals.do"/>" method="post">
                    <input type="submit" value="Book a Rental" name="rental" />
                </form>
            </p>
            <p>
                <form action="<c:url value="membership.do"/>" method="post">
                    <input type="submit" value="Become a Member" name="membership" />
                </form>
            </p>  
            <p>
                <form action="<c:url value="memberLogin.do"/>" method="post">
                    <input type="submit" value="Member Login" name="memberLogin" />
                </form>
            </p>
            <p>
                <form action="<c:url value="adminLogin.do"/>" method="post">
                    <input type="submit" value="Administrator Login" name="admin" />
                </form>                
            </p>
        </main>
    </body>

</html>
