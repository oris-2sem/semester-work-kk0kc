<div class="container">

    <c:if test="${not empty message }">
        <div class="alert alert-info">
            <h3 class="text-center">${message }</h3>
        </div>
    </c:if>

    <c:choose>
        <c:when test="${not empty watchLaterEntity }">
            <table id="watchlater" class="table table-hover table-condensed">
                <thead>
                <tr>
                    <th style="width: 50%">Want to watch</th>
                    <th style="width: 10%"></th>
                </tr>
                </thead>


                <tbody>
                <c:forEach items="${watchLaterEntity }" var="watchLaterEntity">

                    <tr>
                        <td data-th="Anime">F
                            <div class="row">
                                <div class="col-sm-2 hidden-xs">
                                    <img
                                            src="${rootContext }/static/images/${watchLaterEntity.anime.code}.jpg"
                                            alt="${watchLaterEntity.anime.name }"
                                            class="img-responsive viewedImg"/>
                                </div>
                                <div class="col-sm-10">
                                    <h4 class="nomargin">${watchLaterEntity.anime.name }
                                        <c:if test="${watchLaterEntity.available == false }">
                                            <strong class="unavailable">(Not Available)</strong>
                                        </c:if>

                                    </h4>
                                    <p>Brand - ${watchLaterEntity.anime.brand }</p>
                                    <p>Description - ${watchLaterEntity.anime.description }</p>
                                </div>
                            </div>
                        </td>
                        <td class="actions" data-th="">
                            <a href="${contextRoot }/watchlater/${watchLaterEntity.id}/delete" class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-trash"></span>
                            </a>

                            <a href="${contextRoot }/viewed/add/${watchLaterEntity.anime.id}/anime" class="btn btn-success">
                                <span class="glyphicon glyphicon-shopping-viewed"></span></a>
                        </td>
                    </tr>

                </c:forEach>


                </tbody>
                <tfoot>
                <tr>
                    <td><a href="${contextRoot }/show/all/anime" class="btn btn-warning"><span
                            class="glyphicon glyphicon-chevron-left"></span> Continue
                        Searching</a></td>
                    <td colspan="2" class="hidden-xs"></td>
                </tr>
                </tfoot>

            </table>
        </c:when>
        <c:otherwise>
            <div class="jumbotron">
                <div class="text-center">
                    <h1>Your Watch Later is empty!</h1>

                </div>

            </div>
        </c:otherwise>

    </c:choose>


</div>