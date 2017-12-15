<%-- 
    Document   : rent
    Created on : Dec 15, 2017, 6:27:24 AM
    Author     : Owner
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
                        <th colspan="1"> Confirm </th>
                    </tr>
                </thead>
                <tbody>
                        <tr>
                            <td><c:out value="${car.make}"/></td>                                
                            <td><c:out value="${car.model}"/></td>                            
                            <td><c:out value="${car.price}"/></td>                            
                            <td><c:out value="${car.availability}"/></td>
                            <td><c:out value="${car.location}"/></td>   
                            
                            <c:url value="placeOrder.do" var="placeOrder">
                                <c:param name="id" value="${car.id}"/>                                   
                            </c:url>                          
                            <td><a href="${placeOrder}">Place Order</a></td>       
                        </tr>
                </tbody>
            </table>
        </form>
         
            <form action="<c:url value="hello.do"/>" method="post"> 
                <input type="submit" value="home" />
            </form>
        
    </body>
</html>


