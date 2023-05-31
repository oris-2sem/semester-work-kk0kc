<div class="container">


    <c:if test="${not empty message }">
        <div class="alert alert-info">
            <h3 class="text-center">${message }</h3>
        </div>
    </c:if>


    <div class="row">

        <div class="col-md-3">
            <%@include file="share/sidebar.jsp" %>

        </div>

        <div class="col-md-9">

            <div class="row">

                <div class="col-lg-12">
                    <c:if test="${userClickAllAnime == true}">
                        <script>
                            window.categoryId = '';
                        </script>

                        <ol class="breadcrumb">
                            <li><a href="/home">Home</a></li>
                            <li class="active"><span>All Films</span></li>
                        </ol>


                    </c:if>

                    <c:if test="${userClickCategoryAnime == true}">
                        <script>
                            window.categoryId = '${category.id}';
                        </script>
                        <ol class="breadcrumb">
                            <li><a href="/home">Home</a></li>
                            <li class="active"><a href="#">Category</a></li>
                            <li class="active"><span>${category.name }</span></li>

                        </ol>
                    </c:if>
                </div>
            </div>


            <div class="row">

                <div class="col-xs-12">
                    <div class="container-fluid">
                        <div class="table-responsive">


                            <table id="animeListTable"
                                   class="table table-striped table-borderd">


                                <thead>
                                <tr>
                                    <th></th>
                                    <th>Name</th>
                                    <th>Brand</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th></th>
                                    <th>Name</th>
                                    <th>Brand</th>
                                    <th></th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
