<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextRoot" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta name="_csrf" content="${_csrf.token }">
    <meta name="_csrf_header" content="${_csrf.headerName }">


    <title>View Assistant - ${title }</title>
    <script type="text/javascript">
        window.menu = '${title }';
        window.contextRoot = '${contextRoot}'
    </script>
    <!-- Bootstrap core CSS -->

    <link href="${contextRoot }/static/css/bootstrap.min.css"
          rel="stylesheet">

    <!-- Bootstrap Readable Theme -->
    <link href="${contextRoot }/static/css/bootstrap-readable-theme.css"
          rel="stylesheet">


    <!-- Bootstrap DataTables -->
    <link href="${contextRoot }/static/css/dataTables.bootstrap.css"
          rel="stylesheet">


    <!-- Custom CSS -->
    <link href="${contextRoot }/static/css/myapp.css" rel="stylesheet">

</head>

<body>


<div class="wrapper">
    <%@include file="share/navbar.jsp" %>
    <div class="content">

        <c:if test="${userClickHome == true }">
            <%@include file="home.jsp" %>
        </c:if>

        <c:if test="${userClickAbout == true }">
            <%@include file="about.jsp" %>
        </c:if>

        <c:if
                test="${userClickAllAnime == true or  userClickCategoryAnime == true}">
            <%@include file="listAnime.jsp" %>
        </c:if>

        <c:if test="${userClickShowAnime == true }">
            <%@include file="singleAnime.jsp" %>
        </c:if>

        <c:if test="${userClickManageAnime == true }">
            <%@include file="manageAnime.jsp" %>
        </c:if>

        <c:if test="${userClickShowViewed == true }">
            <%@include file="viewed.jsp" %>
        </c:if>
        <c:if test="${userClickShowWatchLater == true }">
            <%@include file="watchLater.jsp" %>
        </c:if>


    </div>

    <!-- Footer -->
    <%@include file="share/footer.jsp" %>

    <!-- jQuery -->
    <script src="${contextRoot }/static/js/jquery.js"></script>

    <!-- jQuery validator -->
    <script src="${contextRoot }/static/js/jquery.validate.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${contextRoot }/static/js/bootstrap.min.js"></script>

    <!-- DataTable Plugin -->
    <script src="${contextRoot }/static/js/jquery.dataTables.js"></script>

    <!-- DataTable Bootstrap Script -->
    <script src="${contextRoot }/static/js/dataTables.bootstrap.js"></script>

    <!-- Boot Box  Script -->
    <script src="${contextRoot }/static/js/bootbox.min.js"></script>

    <!-- Self coded javascript -->
    <script src="${contextRoot }/static/js/myapp.js"></script>
</div>
</body>

</html>

