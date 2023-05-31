<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="contextRoot" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>View Assistant - ${title }</title>
    <script type="text/javascript">
        window.menu = '${title }';
        window.contextRoot = '${contextRoot}'
    </script>
    <!-- Bootstrap core CSS -->

    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Readable Theme -->
    <link href="/css/bootstrap-readable-theme.css" rel="stylesheet">


    <!-- Bootstrap DataTables -->
    <link href="/css/dataTables.bootstrap.css" rel="stylesheet">


    <!-- Custom CSS -->
    <link href="/css/myapp.css" rel="stylesheet">

</head>

<body>


<div class="wrapper">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="/home">Home</a>
            </div>
        </div>
    </nav>


    <div class="content">
        <div class="container">
            <div class="row">
                <div class="col-xs-12">
                    <div class="jumbotron">
                        <h1>${errorTitle }</h1>
                        <hr/>
                        <blockquote>${errorDescription }</blockquote>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="share/footer.jsp" %>
</div>
</body>

</html>

