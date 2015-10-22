<%-- 
    Document   : modifycreate
    Created on : Sep 28, 2015, 9:34:59 PM
    Author     : user test
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Update or Add Author</title>
    </head>
    <body class="col-xs-offset-1">
        <h1>Update/Add Author</h1>
        <div class="row">
            <div class="panel panel-default  col-md-4 col-sm-4 col-xs-6">

                <div class="panel-heading"><h1>Author Form</h1></div>
                <div class="panel-body">
                    <table>
                        <form role="form" class="form-horizontal" id="authorForm" name="authorForm" method="POST" action="AuthorController">
                            <tr>
                            <div class="form-group">
                                <td>ID</td>
                                <td align="left"><input type="text" value="${author.authorId}" name="authorId" readonly/></td>
                            </div>
                            </tr>
                            <tr>
                            <div class="form-group">
                                <td><label for="authorId">Author Name</label></td>
                                <td align="left"><input type="text" value="" name="authorName" placeholder="${author.authorName}"/></td>
                            </div>
                            </tr>
                            <c:choose>
                                <c:when test="${not empty author.bookCollection}">
                                    <tr>
                                    <select id="bookSetDropDown" name="bookId">
                                        <c:forEach var="book" items="${author.bookCollection}" varStatus="rowCount">
                                            <option value="${book.bookId}">${book.title}</option>
                                        </c:forEach>
                                    </select>
                                    </tr>
                                </c:when>
                            </c:choose>
                            <tr>
                            <div class="form-group">
                                <td><label for="dateCreated">Author Added Date</label></td>
                                <td><input class="form-control" type="text" id="dateCreated" name="dateCreated" value="" placeholder="${author.dateCreated}"/></td>
                            </div>
                            </tr>
                            <tr>
                                <c:choose>
                                    <c:when test="${not empty author}">
                                    <button class="btn btn-default" type="submit" id="create" name="action" value="update">Submit</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-default" type="submit" id="submit" name="action" value="create">Submit</button>
                                </c:otherwise>
                            </c:choose>
                            </tr>
                        </form>
                    </table>    
                </div>
            </div>
        </div>
        <a class="btn btn-success" role="button" href="AuthorController?action=list">Back to List</a>       
    </body>
</html>
