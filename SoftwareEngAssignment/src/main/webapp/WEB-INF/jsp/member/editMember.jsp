<%-- 
    Document   : editMember
    Created on : Nov 3, 2017, 5:44:23 PM
    Author     : Oracle
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update data</title>
    </head>      
        <main>
            <c:if test="${not empty errors}">
                <ul>
                    <c:forEach items="${errors}" var="e">
                        <li class="error">${e.message}</li>
                    </c:forEach>
                </ul>
            </c:if>
            <h1>Editing Member</h1>
            <form method="post" action="<c:url value="update.do"/>"/>
                <input type="hidden" name="uid" value="${member.uid}"/>
                <h2>Edit your information: </h2>
                <p>
                    <label for="first">First Name:</label>
                    <input id="first" type="text" name="fName" value="<c:out value="${member.fName}"/>" required/>
                   
              
                </p>
                <p>
                    <label for="last">Last Name:</label>
                    <input id="last" type="text" name="lName" value="<c:out value="${member.lName}"/>" required/>
                     
                </p>
                <p>
                    <label for="pNumber">Phone Number</label>
                    <input id="pNumber" type="text" name="pNumber" value="<c:out value="${member.pNumber}"/>" required/>
                </p>
                <p>
                    <label for="cCard">Credit Card</label>
                    <input id="cCard" type="text" name="cCard" value="<c:out value="${member.cCard}"/>" required/>
                </p>
                <p>
                    <label>Admin</label>
                    <input id="admin" type="text" name="admin" value="<c:out value="${member.admin}"/>" required/>            
                </p>

                <p>
                    <label>&nbsp;</label>
                    <input type="reset" value="Clear"/>
                    <input type="submit" value="Send"/>
                </p>
            </form>
                    
            <form action="<c:url value="displayAll.do"/>" method="post"> 
                <input type="submit" value="List All" />
            </form>
        
                
                   
              
        </main>
        <footer>
               
        </footer>
</html>
