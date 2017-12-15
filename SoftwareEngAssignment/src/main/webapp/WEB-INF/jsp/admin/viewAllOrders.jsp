<%-- 
    Document   : viewAllOrders
    Created on : Dec 15, 2017, 2:30:20 PM
    Author     : Owner
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>All Orders</title>
    </head>

        <form>    
            <table border="1" width="40%">
                <caption>All Orders</caption>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>User ID</th>
                        <th>Date Rented</th>
                        <th>Date Returned</th>
                        <th>Active</th>
                        <th colspan="2">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${orders}" varStatus="s">
                        <tr>
                            <td><c:out value="${row.oid}"/></td>
                            <td><c:out value="${row.uid}"/></td>                                
                            <td><c:out value="${row.dateRented}"/></td>                            
                            <td><c:out value="${row.dateReturned}"/></td>                            
                            <td><c:out value="${row.active}"/></td>   
                            <c:url value="editOrder.do" var="update">
                                <c:param name="oid" value="${row.oid}"/>
                            </c:url>                                    
                            <td><a href="${update}">Update</a></td> 
                            <c:url value="deleteOrder.do" var="delete">
                                <c:param name="oid" value="${row.oid}"/>                                   
                            </c:url>                          
                            <td><a href="${delete}">Delete</a></td>                            
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
         
            <form action="<c:url value="hello.do"/>" method="post"> 
                <input type="submit" value="home" />
            </form>
        
    </body>
</html>
