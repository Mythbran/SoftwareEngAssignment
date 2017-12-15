<%-- 
    Document   : date
    Created on : Dec 15, 2017, 12:53:41 PM
    Author     : Owner
--%>
<%@tag import="java.util.*" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:formatDate value="<%= new Date()%>" type="date" dateStyle="medium"/>

