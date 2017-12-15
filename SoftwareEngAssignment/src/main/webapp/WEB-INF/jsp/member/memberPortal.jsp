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
            <form action="<c:url value="addOrder.do"/>" method="post">
                <input type="submit" value="Place an Order" name="addOrder" />
            </form>
                <!-- needs to be coded in -->
        </p>
        <p>
            <form action="<c:url value="viewOrders.do"/>" method="post">
                <input type="submit" value="View Orders" name="viewOrders" />
            </form>
                <!-- needs to be coded in-->
        </p>
        
        <p>
            <!-- might add password change -->
        </p>
    </body>
</html>
