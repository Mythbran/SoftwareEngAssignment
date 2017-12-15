<%-- 
    Document   : adminPortal
    Created on : Nov 1, 2017, 9:23:51 PM
    Author     : Oracle
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Portal</title>
    </head>
    
    <body>
        <main>
            <h1>Welcome to the Admin Portal</h1>
            <p>
                <form action="<c:url value="addCar.do"/>" method="post">
                    <input type="submit" value="Add a new vehicle" name="addCar" />
                </form>
            </p>
            <p>
                <form action="<c:url value="viewAll.do"/>" method="post">
                    <input type="submit" value="View All Members" name="viewAll" />
                </form>
            </p>  
            <p>
                <form action="<c:url value="viewAllCars.do"/>" method="post">
                    <input type="submit" value="View All Cars" name="viewAllCars" />
                </form>
            </p>
            <p>
                <form action="<c:url value="hello.do"/>" method="post">
                    <input type="submit" value="Home" name="Home" />
                </form>
            </p>
        </main>
    </body>

</html>