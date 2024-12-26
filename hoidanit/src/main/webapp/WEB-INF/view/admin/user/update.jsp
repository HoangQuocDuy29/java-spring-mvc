<%@page contentType = "text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update User</title>
    <!--Latest complied and minified CSS-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!--Latest complied JavaScript-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" ></script>
    <!--Latest complied JQuery-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- <link href="/css/demo.css" rel="stylesheet" > -->
</head>

<body>
   <div class="container mt-5">
        <div class="row">
            <div class="col-md-6 col-12 mx-auto">
                <h3>Update User</h3>
                <hr />
                <form:form method="post" action="/admin/user/update" modelAttribute="newUser">
                    <div class="mb-3" style="display: none;">
                        <label class="form-label" >Id:</label>
                        <form:input type="text" class="form-control" path="id" />
                    </div>
                    <div class="mb-3">
                        <label class="form-label" >Email:</label>
                        <form:input type="email" class="form-control" 
                        path="email" disabled ="true" /> <!--disabled ="true"  để cho người dùng không thay đổi được thuộc tính email này-->
                    </div>
                    <div class="mb-3">
                        <label class="form-label" >Password:</label>
                        <form:input type="password" class="form-control" 
                        path="password" />
                    </div>
                    <div class="mb-3">
                        <label class="form-label" >Phone Number:</label>
                        <form:input type="text" class="form-control" 
                        path="phone" />
                    </div>
                    <div class="mb-3">
                        <label class="form-label" >Full Name:</label>
                        <form:input type="text" class="form-control"
                        path="fullName" />
                    </div>
                    <div class="mb-3">
                        <label class="form-label" >Address:</label>
                        <form:input type="text" class="form-control"
                        path="address" />
                    </div>
                    <button type="submit" class="btn btn-warning" >Update</button>
                </form:form>
            </div>
        </div>
   </div>
</body>
</html>