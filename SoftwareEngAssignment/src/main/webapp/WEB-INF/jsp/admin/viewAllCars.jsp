<%-- 
    Document   : vewAllCars
    Created on : Dec 15, 2017, 4:12:55 AM
    Author     : Owner
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>All Records</title>
    </head>

        <form>    
            <table border="1" width="40%">
                <caption>All Cars</caption>
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Make</th>
                        <th>Model</th>
                        <th>Price</th>
                        <th>Availability</th>
                        <th>Location</th>
                        <th colspan="2">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${cars}" varStatus="s">
                        <tr>
                            <td><c:out value="${row.id}"/></td>
                            <td><c:out value="${row.make}"/></td>                                
                            <td><c:out value="${row.model}"/></td>                            
                            <td><c:out value="${row.price}"/></td>                            
                            <td><c:out value="${row.availability}"/></td>
                            <td><c:out value="${row.location}"/></td>   
                            <c:url value="editCar.do" var="update">
                                <c:param name="id" value="${row.id}"/>
                            </c:url>                                    
                            <td><a href="${update}">Update</a></td> 
                            <c:url value="deleteCar.do" var="delete">
                                <c:param name="id" value="${row.id}"/>                                   
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