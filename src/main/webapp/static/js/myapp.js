$(function () {

    switch (menu) {
        case 'About Us':
            $('#about').addClass('active');
            break;
        case 'All Films':
            $('#listAnime').addClass('active');
            break;
        case 'Manage Anime':
            $('#manageAnime').addClass('active');
            break;
        case 'User Viewed List':
            $('#userViewed').addClass('active');
            break;
        case 'User Watch Later':
            $('#userViewed').addClass('active');
            break;
        default:
            if (menu == "Home") {
                $('#home').addClass('active');
                break;
            }
            $('#listAnime').addClass('active');
            $('#a_' + menu).addClass('active');
            break;
    }

    // to tackle the csrf token
    var token = $('meta[name="_csrf"]').attr('content');
    var header = $('meta[name="_csrf_header"]').attr('content');

    if (token.length > 0 && header.length > 0) {
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    }


    var $table = $('#animeListTable');

    if ($table.length) {
        var jsonUrl = '';
        if (window.categoryId == '') {
            jsonUrl = window.contextRoot + '/json/data/all/anime'
        } else {
            jsonUrl = window.contextRoot + '/json/data/category/'
                + window.categoryId + '/anime'
        }

        $table
            .DataTable({
                lengthMenu: [
                    [3, 5, 10, -1],
                    ['3 Records', '5 Records', '10 Records',
                        'All Records']],
                pageLength: 5,
                ajax: {
                    url: jsonUrl,
                    dataSrc: '',
                },
                columns: [
                    {
                        data: 'code',
                        mRender: function (data, type, row) {
                            return '<img src="' + window.contextRoot
                                + '/static/images/' + data
                                + '.jpg" class="dataTableImg"/>'
                        }
                    },
                    {
                        data: 'name',
                    },
                    {
                        data: 'brand',
                    },
                    {
                        data: 'id',
                        bSortable: false,

                        mRender: function (data, type, row) {
                            var str = '';
                            str += '<a href="'
                                + window.contextRoot
                                + '/show/'
                                + data
                                + '/anime" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a>&#160;';


                                if (userRole == 'ADMIN') {
                                    str += '<a href="'
                                        + window.contextRoot
                                        + '/manage/'
                                        + data
                                        + '/anime" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';

                                }
                                else {


                                    str += '<a href="'
                                        + window.contextRoot
                                        + '/viewed/add/'
                                        + data
                                        + '/anime" class="btn btn-success" id="myButton"><span class="glyphicon glyphicon-shopping-cart"></span></a>  ';

                                    str += '<a href="'
                                        + window.contextRoot
                                        + '/watchlater/add/'
                                        + data
                                        + '/anime" class="btn btn-success" id="myButton"><span class="glyphicon glyphicon-check"></span></a>';

                                }
                            return str;
                        }
                    },]
            });
    }


    var $alert = $('.alert');
    if ($alert.length) {
        setTimeout(function () {
            $alert.fadeOut('slow');
        }, 3000)
    }

    var $adminAnimeTable = $('#adminAnimeTable');

    if ($adminAnimeTable.length) {
        var jsonUrl = window.contextRoot + '/json/data/admin/all/anime';

        $adminAnimeTable
            .DataTable({
                lengthMenu: [
                    [10, 30, 50, -1],
                    ['10 Records', '30 Records', '50 Records',
                        'All Records']],
                pageLength: 30,
                ajax: {
                    url: jsonUrl,
                    dataSrc: '',
                },
                columns: [
                    {
                        data: 'id'
                    },
                    {
                        data: 'code',
                        mRender: function (data, type, row) {
                            return '<img src="'
                                + window.contextRoot
                                + '/static/images/'
                                + data
                                + '.jpg" class="adminDataTableImg"/>'
                        }
                    },
                    {
                        data: 'name',
                    },
                    {
                        data: 'brand',
                    },
                    {
                        data: 'active',
                        bSortable: false,
                        mRender: function (data, type, row) {
                            var str = '';

                            str += '<label class="switch">';
                            if (data) {
                                str += '<input type="checkbox" checked="checked" value="'
                                    + row.id + '" />'
                            } else {
                                str += '<input type="checkbox" value="'
                                    + row.id + '" />'
                            }

                            str += '<div class="slider"></div></label>';

                            return str;

                        }

                    },

                    {
                        data: 'id',
                        bSortable: false,
                        mRender: function (data, type, row) {
                            var str = '';
                            str += '<a href="'
                                + window.contextRoot
                                + '/manage/'
                                + data
                                + '/anime" class="btn btn-warning">';
                            str += '<span class="glyphicon glyphicon-pencil"></span></a>';

                            return str;

                        }

                    },],
                initComplete: function () {
                    var api = this.api();
                    api
                        .$('.switch input[type="checkbox"]')
                        .on(
                            'change',
                            function () {
                                var checkbox = $(this);
                                var checked = checkbox
                                    .prop('checked');

                                var dMsg = (checked) ? 'You want to activate the Film?'
                                    : 'You want to deactivate the Film?';
                                var value = checkbox.prop('value');

                                bootbox
                                    .confirm({
                                        size: 'medium',
                                        title: 'Film Activation & Deactivation',
                                        message: dMsg,
                                        callback: function (
                                            confirmed) {
                                            if (confirmed) {
                                                var activationUrl = window.contextRoot
                                                    + '/manage/anime/'
                                                    + value
                                                    + '/activation';
                                                $
                                                    .post(
                                                        activationUrl,
                                                        function (
                                                            data) {
                                                            bootbox
                                                                .alert({
                                                                    size: 'medium',
                                                                    title: 'Information',
                                                                    message: data
                                                                });
                                                        });

                                            } else {
                                                checkbox
                                                    .prop(
                                                        'checked',
                                                        !checked);
                                            }
                                        }
                                    });

                            });
                }
            });
    }

    // --------------------------------------------------
    // validation code for category

    var $categoryForm = $('#categoryForm');

    if ($categoryForm.length) {
        $categoryForm
            .validate({
                rules: {
                    name: {
                        required: true,
                        minlength: 2
                    },
                    description: {
                        required: true
                    }
                },
                massage: {
                    name: {
                        required: 'Please Add the category name!',
                        minlength: 'The category name should not be less than 2 characters'
                    },
                    description: {
                        required: 'Please add a description for this category'
                    }
                },
                errorElement: 'em',
                errorPlacement: function (error, element) {
                    // add the class of help-block
                    error.addClass('help-block');
                    error.insertAfter(element);
                }
            });
    }
    // ---------------------------------------------------------
    // end

    // --------------------------------------------------
    // validation code for Login

    var $loginForm = $('#loginForm');

    if ($loginForm.length) {
        $loginForm.validate({
            rules: {
                username: {
                    required: true,
                    email: true
                },
                password: {
                    required: true
                }
            },
            massage: {
                username: {
                    required: 'Please Enter the username',
                    email: 'Please Enter Valid Email Address'
                },
                description: {
                    required: 'Please Enter Password'
                }
            },
            errorElement: 'em',
            errorPlacement: function (error, element) {
                // add the class of help-block
                error.addClass('help-block');
                error.insertAfter(element);
            }
        });
    }
});
