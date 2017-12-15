<%-- 
    Document   : rentals
    Created on : Nov 1, 2017, 7:19:51 PM
    Author     : Oracle
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>All Cars</title>
    </head>

        <form>    
            <table border="1" width="40%">
                <caption>All Rental Cars Available Cars</caption>
                <thead>
                    <tr>
                        <th>Make</th>
                        <th>Model</th>
                        <th>Price</th>
                        <th>Availability</th>
                        <th>Location</th>
                        <th colspan="1">Rent</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${cars}" varStatus="s">
                        <tr>
                            <td><c:out value="${row.make}"/></td>                                
                            <td><c:out value="${row.model}"/></td>                            
                            <td><c:out value="${row.price}"/></td>                            
                            <td><c:out value="${row.availability}"/></td>
                            <td><c:out value="${row.location}"/></td>   
                            <c:url value="rent.do" var="rent">
                                <c:param name="rent" value="${row.id}"/>                                   
                            </c:url>                          
                            <td><a href="${rent}">Rent</a></td>                            
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