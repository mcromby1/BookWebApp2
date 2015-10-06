<%-- 
    Document   : listAuthors
    Created on : Sep 21, 2015, 9:36:05 PM
    Author     : jlombardo
    Purpose    : display list of author records and (in the future) provide
                 a way to add/edit/delete records
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Author List</title>
    </head>
    <body class="col-xs-offset-1 col-lg-5 col-md-5 col-sm-5 col-xs-6">
        <h1>Author List</h1>
        <a class="btn btn-info" role="button" href="AuthorController?action=add">Add Author</a>
        <table width="500" border="1" cellspacing="0" cellpadding="4">
            <tr style="background-color: black;color:white;">
                <th align="left" class="tableHead">ID</th>
                <th align="left" class="tableHead">Author Name</th>
                <th align="right" class="tableHead">Date Added</th>
            </tr>
            <c:forEach var="a" items="${authors}" varStatus="rowCount">
                <c:choose>
                    <c:when test="${rowCount.count % 2 == 0}">
                        <tr style="background-color: white;">
                        </c:when>
                        <c:otherwise>
                        <tr style="background-color: #ccffff;">
                        </c:otherwise>
                    </c:choose>
                    <td align="left">${a.authorId}</td>
                    <td align="left">${a.authorName}</td>
                    <td align="right">
                        <fmt:formatDate pattern="M/d/yyyy" value="${a.dateCreated}"></fmt:formatDate>
                        </td>
                        <td>
                            <p><a class="btn btn-danger" role="button" href="AuthorController?action=delete&authorId=${a.authorId}">Delete</a> 
                            <a class="btn btn-success" role="button" href="AuthorController?action=modify&authorId=${a.authorId}">Update</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${errMsg != null}">
            <p style="font-weight: bold;color: red;width:500px;">Sorry, data could not be retrieved:<br>
                ${errMsg}</p>
            </c:if>
    </body>
</html>
