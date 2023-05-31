<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <ol class="breadcrumb">
                <li><a href="/home">Home</a></li>
                <li><a href="/show/all/anime">Anime</a></li>
                <li class="active">${anime.name }</li>
            </ol>
        </div>

    </div>

    <div class="row">
        <div class="col-xs-12 col-sm-4">
            <div class="thumbnail">
                <img src="${contextRoot }/static/images/${anime.code}.jpg"
                     class="img img-responsive"/>
            </div>
        </div>
        <div class="col-xs-12 col-sm-8">
            <h3>${anime.name }</h3>
            <hr/>
            <p>${anime.description }</p>
            <hr/>

            <security:authorize access="hasAuthority('USER')">
                        <a href="${contextRoot }/viewed/add/${anime.id }/anime" class="btn btn-success"><span
                                class="glyphicon glyphicon-shopping-viewed"></span>Add to Viewed</a>
            </security:authorize>

            <security:authorize access="hasAuthority('ADMIN')">

                <a href="${contextRoot }/manage/${anime.id }/anime" class="btn btn-warning"><span
                        class="glyphicon glyphicon-pencil"></span> Edit</a>

            </security:authorize>

            <a href="/show/all/anime" class="btn btn-primary">Back</a>

        </div>
    </div>
</div>