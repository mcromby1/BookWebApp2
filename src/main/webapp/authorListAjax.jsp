<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap.min.js" rel="script"/>
        <title>Author List</title>
    </head>
    <body class="authorList">
        <sec:csrfInput/>
        <h1>Author List</h1>
        <sec:authorize access="hasAnyRole('ROLE_MGR')">
            <a class="btn btn-info" role="button" href="AuthorController?action=add">Add Author</a>
        </sec:authorize>
        <table class="col-xs-offset-1 col-lg-5 col-md-5 col-sm-5 col-xs-6" width="500" border="1" cellspacing="0" cellpadding="4">
            <thead class="tableHeader" style="background-color: black;color:white;">
            <th align="left" class="tableHead">ID</th>
            <th align="left" class="tableHead">Author Name</th>
            <th align="right" class="tableHead">Date Added</th>
            <th align="left" class="tableHead">Books</th>
            <th align="left" class="Tablehead">Actions</th>
        </thead>
        <tbody class="tableData">

        </tbody>

    </table>

</body>
</html>
