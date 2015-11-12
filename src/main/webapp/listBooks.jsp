
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Book List</title>
    </head>
    <body class="col-xs-offset-1 col-lg-5 col-md-5 col-sm-5 col-xs-6">
        <sec:csrfInput />
        <h1>Book List</h1>
        <sec:authorize access="hasAnyRole('ROLE_MGR')">
        <a class="btn btn-info" role="button" href="bookController?action=add">Add Book</a>
        </sec:authorize>
        <table width="500" border="1" cellspacing="0" cellpadding="4">
            <tr style="background-color: black;color:white;">
                <th align="left" class="tableHead">ID</th>
                <th align="left" class="tableHead">Author Name</th>
                <th align="right" class="tableHead">Date Added</th>
                <th align="left" class="tableHead">Books</th>
            </tr>
            <c:forEach var="b" items="${books}" varStatus="rowCount">
                <c:choose>
                    <c:when test="${rowCount.count % 2 == 0}">
                        <tr style="background-color: white;">
                        </c:when>
                        <c:otherwise>
                        <tr style="background-color: #ccffff;">
                        </c:otherwise>
                    </c:choose>
                    <td align="left">${b.bookId}</td>
                    <td align="left">${b.bookName}</td>
                    <td align="right">
                        <fmt:formatDate pattern="M/d/yyyy" value="${a.dateCreated}"></fmt:formatDate>
                        </td>
                    <sec:authorize access="hasAnyRole('ROLE_MGR')">
                        <td>
                            <p><a class="btn btn-danger" role="button" href="BookController?action=delete&authorId=${b.bookId}">Delete</a> 
                                <a class="btn btn-success" role="button" href="BookController?action=modify&authorId=${b.bookId}">Update</a>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${errMsg != null}">
            <p style="font-weight: bold;color: red;width:500px;">Sorry, data could not be retrieved:<br>
                ${errMsg}</p>
            </c:if>
    </body>
</html>
