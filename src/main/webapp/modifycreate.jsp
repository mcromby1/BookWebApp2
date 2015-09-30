<%-- 
    Document   : modifycreate
    Created on : Sep 28, 2015, 9:34:59 PM
    Author     : user test
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Update or Add Author</title>
    </head>
    <body>
        <h1>Update/Add Author</h1>
        <div class="row">

            <div class="panel panel-default col-md-4 col-sm-4 col-xs-6">
                <div class="panel-heading"><h1>Author Form</h1></div>
                <div class="panel-body">
                    <table>
                        <form role="form" class="form-horizontal" id="authorForm" name="authorForm" method="POST" action="calcmain" value="Circle">
                            <tr>
                            <div class="form-group">
                                <td>ID></td>
                                <td>${author.authorId}</td>
                            </div>
                            </tr>
                            <tr>
                            <div class="form-group">
                                <td><label for="authorId">Author Name</label></td>
                                <td><input class="form-control" type="text" id="authorId" name="authorId" value="" placeholder="${author.authorName}"/></td>
                            </div>
                            </tr>
                            <tr>
                            <div class="form-group">
                                <td><label for="dateCreated">Author Added Date</label></td>
                                <td><input class="form-control" type="text" id="dateCreated" name="dateCreated" value="" placeholder="${author.dateCreated}"/></td>
                            </div>
                            </tr>
                            <c:choose>
                                <c:when test="${author.authorId >= 0}">
                                    <button class="btn-default" type="submit" id="submit" name="submit" value="update">Submit</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn-default" type="submit" id="submit" name="submit" value="create">Submit</button>
                                </c:otherwise>
                            </form>
                        </table>
                    </div>
                </div>
            </div>

        </body>
    </html>
