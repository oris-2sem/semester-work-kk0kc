var app = angular.module('ViewAssistant', []);

app.controller('AnimeController', function ($http) {

    var me = this;

    me.mvAnime = [];
    me.mpAnime = [];

    me.fetchAnime = function () {


        $http.get('/animeservice/json/data/mv/anime')
            .then(function (response) {
                me.mvAnime = response.data;
            });


        $http.get('/animeservice/json/data/mp/anime')
            .then(function (response) {
                me.mpAnime = response.data;
            });

    }

});