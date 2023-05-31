<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${contextRoot }/home">View Assistant</a>
        </div>
        <div class="collapse navbar-collapse"
             id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                    <li id="listAnime"><a
                            href="${contextRoot }/show/all/anime">All Films</a></li>

                    <security:authorize access="hasAuthority('USER')">
                    <li id="watchlater"><a href="${contextRoot}/watchlater/show">Want to watch</a></li>
                    </security:authorize>

                    <security:authorize access="hasAuthority('USER')">
                        <li id="viewed"><a href="${contextRoot}/viewed/show">Viewed</a></li>
                    </security:authorize>

                    <security:authorize access="isAnonymous()">
                        <li id="about"><a href="${contextRoot }/about">About</a></li>
                    </security:authorize>
                <security:authorize access="hasAuthority('ADMIN')">
                    <li id="manageAnime"><a
                            href="${contextRoot }/manage/anime">Manage Films</a></li>
                </security:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <security:authorize access="isAnonymous()">
                    <li id="manageAnime"><a href="${contextRoot }/register">Sign
                        Up</a></li>


                    <li id="manageAnime"><a href="${contextRoot }/login">Login</a></li>

                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <li class="dropdown" id="userViewed"><a
                            class="btn btn-default dropdown-toggle" href="javascript:void(0)"
                            id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="true"> ${userModel.fullName } <span
                            class="caret"></span>
                    </a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li id="logout"><a href="${contextRoot}/login?logout=true">Logout</a></li>


<%--                            <form id="logoutForm" action="/logout" method="post">--%>
<%--                                <!-- Добавьте другие поля формы, если необходимо -->--%>
<%--                                <button type="button" onclick="logout()" form="logoutForm">Logout</button>--%>
<%--                            </form>--%>

<%--                            <script>--%>
<%--                                function logout() {--%>
<%--                                    document.getElementById("logoutForm").submit();--%>
<%--                                    window.location.href = '/login?logout=true';--%>
<%--                                }--%>
<%--                            </script>--%>
                        </ul>
                    </li>
                </security:authorize>
            </ul>
        </div>
    </div>
</nav>


<script type="text/javascript">

    window.userRole = '${userModel.role }';

</script>






