<%-- 
    Document   : viewAll
    Created on : Nov 3, 2017, 5:44:02 PM
    Author     : Oracle
    MEMBERS VIEW ALL !!! 
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
                <caption>All Members</caption>
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Phone Number</th>
                        <th>Credit Card</th>
                        <th>Username</th>
                        <th>Admin Privilege</th>
                        <th colspan="2">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${members}" varStatus="s">
                        <tr>
                            <td><c:out value="${s.count}"/></td>
                            <td><c:out value="${row.fName}"/></td>                                
                            <td><c:out value="${row.lName}"/></td>                            
                            <td><c:out value="${row.pNumber}"/></td>                            
                            <td><c:out value="${row.cCard}"/></td>
                            <td><c:out value="${row.uName}"/></td>   
                            <td><c:out value="${row.admin}"/></td>
                            <c:url value="editMember.do" var="update">
                                <c:param name="uid" value="${row.uid}"/>
                            </c:url>                                    
                            <td><a href="${update}">Update</a></td> 
                            <c:url value="delete.do" var="delete">
                                <c:param name="uid" value="${row.uid}"/>                                   
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