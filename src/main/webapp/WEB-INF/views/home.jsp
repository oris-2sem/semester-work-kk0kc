<div class="container">

    <div class="row">

        <div class="col-md-3">
            <%@include file="./share/sidebar.jsp" %>
        </div>

        <div class="col-md-9">

            <div class="row carousel-holder">

                <div class="col-md-12">
                    <div id="carousel-example-generic" class="carousel slide"
                         data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0"
                                class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                        </ol>
                        <div class="carousel-inner">

                            <div class="item active">
                                <img class="slide-image" height="500dp" width="1500dp" src="${contextRoot }/static/images/chainsawman.png"
                                     alt="">
                            </div>
                            <div class="item">
                                <img class="slide-image" height="500dp" width="1500dp" src="${contextRoot }/static/images/banner1.jpg"
                                     alt="">
                            </div>
                            <div class="item">
                                <img class="slide-image" height="500dp" width="1500dp" src="${contextRoot }/static/images/banner3.png"
                                     alt="">
                            </div>
                            <div class="item">
                                <img class="slide-image" height="500dp" width="1500dp" src="${contextRoot }/static/images/banner2.png"
                                     alt="">
                            </div>
                        </div>
                        <a class="left carousel-control" href="#carousel-example-generic"
                           data-slide="prev"> <span
                                class="glyphicon glyphicon-chevron-left"></span>
                        </a> <a class="right carousel-control"
                                href="#carousel-example-generic" data-slide="next"> <span
                            class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                    </div>
                </div>

            </div>
            <div class="container">
            <div class="row">
                <div class="col-sm">
                    <h3>Our Most Viewed Films</h3>
                    <a href="${contextRoot}/show/${top1anime.id }/anime">
                        ${top1anime.name }
                        <img src="${contextRoot }/static/images/${top1anime.code}.jpg" height="150dp" width="150dp"/></a>



                    <a href="${contextRoot}/show/${top2anime.id }/anime">
                        ${top2anime.name }
                        <img src="${contextRoot }/static/images/${top2anime.code}.jpg" height="150dp" width="150dp"/></a>



                    <a href="${contextRoot}/show/${top3anime.id }/anime">
                        ${top3anime.name }
                        <img src="${contextRoot }/static/images/${top3anime.code}.jpg" height="150dp" width="150dp"/></a>



                    <a href="${contextRoot}/show/${top4anime.id }/anime">
                        ${top4anime.name }
                        <img src="${contextRoot }/static/images/${top4anime.code}.jpg" height="150dp" width="150dp"/></a>
                </div>
            </div>
            </div>


            <div class="row is-table-row">


                <div class="col-sm-4 col-lg-4 col-md-4">
                    <h4>Checkout more anime!</h4>
                    <hr/>
                    <a class="btn btn-primary" href="${contextRoot}/show/all/anime">More Anime</a>
                </div>

            </div>

    </div>

</div>