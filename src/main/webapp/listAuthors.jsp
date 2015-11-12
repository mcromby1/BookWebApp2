
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
        <title>Author List</title>
    </head>
    <body class="col-xs-offset-1 col-lg-5 col-md-5 col-sm-5 col-xs-6">
        <sec:csrfInput />
        <h1>Author List</h1>
        <sec:authorize access="hasAnyRole('ROLE_MGR')">
            <a class="btn btn-info" role="button" href="AuthorController?action=add">Add Author</a>
        </sec:authorize>
        <table width="500" border="1" cellspacing="0" cellpadding="4">
            <tr style="background-color: black;color:white;">
                <th align="left" class="tableHead">ID</th>
                <th align="left" class="tableHead">Author Name</th>
                <th align="right" class="tableHead">Date Added</th>
                <th align="left" class="tableHead">Books</th>
                <th align="left" class="Tablehead">Actions</th>
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
                        <td align="center">
                       <!--<button class="btn btn-default dropdown-toggle" 
                                                   type="button" id="dropdownbutton" 
                                                   data-toggle="dropdown" aria-haspopup="true" aria-expended="true">Books<span class="caret"></span></button>
                            <ul class="dropdown-menu" aria-labeledby="dropdownbutton">
                            <c:forEach var="b" items="a.bookSet.bookCollection.Book">
                                <li>${b.title}</li>
                                </c:forEach> 
                        </ul>-->
                    </td>
                    <sec:authorize access="hasAnyRole('ROLE_MGR')"> 
                        <td>
                            <p><a class="btn btn-danger" role="button" href="AuthorController?action=delete&authorId=${a.authorId}">Delete</a>
                                <a class="btn btn-success" role="button" href="AuthorController?action=modify&authorId=${a.authorId}">Update</a></p>
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
