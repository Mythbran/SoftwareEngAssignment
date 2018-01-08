<%-- 
    Document   : editOrder
    Created on : 13-Dec-2017, 11:05:27
    Author     : Dan
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link rel="stylesheet" href="<c:url value="/css/main.css" />" type="text/css">
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
                <input type="hidden" name="uid" value="${order.uid}"/>
                <input type="hidden" name="id" value="${order.id}"/>
                <h2>Edit your information: </h2>
                <p>
                    <label for="uid">UID: </label>
                    <input id="uid" type="text" name="uid" value="<c:out value="${order.uid}"/>" required/>
                   
              
                </p>
                <p>
                    <label for="uid">ID: </label>
                    <input id="uid" type="text" name="uid" value="<c:out value="${order.id}"/>" required/>
                     
                </p>
                <p>
                    <label for="dateRemted">Date Rented: </label>
                    <input id="dateRemted" type="text" name="dateRemted" value="<c:out value="${order.dateRemted}"/>" required/>
                </p>
                <p>
                    <label for="dateReturned">Date Returned</label>
                    <input id="dateReturned" type="text" name="dateReturned" value="<c:out value="${order.dateReturned}"/>" required/>
                </p>
                <p>
                    <fieldset>
                    <legend>Active:</legend>
                    <p>
                        <label>Active Order</label>
                        <select id = "active">
                        <option value = "true">Active</option>
                        <option value = "false">Not Active</option>
                    </select>
          </p>
       </fieldset>       
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
