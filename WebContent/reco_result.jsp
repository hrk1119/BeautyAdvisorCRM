<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>

<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- Bootstrap CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <!-- アラート -->
    <link href="./css/docs.css" rel="stylesheet">
    <link href="./css/sweetalert.css" rel="stylesheet">

    <link rel="stylesheet" href="css/sidebar.css">
    <link rel="stylesheet" href="css/recommend.css">

    <link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
    <title>おすすめ商品</title>
</head>

<body>
    <div class="container-fluid customer reco_result">
        <div class="row mb-5">

            <!-- サイドバー -->
            <header>
                <div id="nav-drawer">
                    <input id="nav-input" type="checkbox" class="nav-unshown">
                    <label id="nav-open" for="nav-input"><span></span></label> <label class="nav-unshown" id="nav-close"
                        for="nav-input"></label>
                    <div id="nav-content">
                        <!--  中身  -->
                        <nav id="sidebar" class="col-12 d-none d-md-block main customer">
                            <p class="text-center pt-4 pb-4 mb-0">${sessionScope.checkInName}様</p>
                            <ul class="list-unstyled components">
                                <li><a class="return_icon" href="menu.jsp">トップへ</a></li>
                                <li><a href="#menu_Submenu" data-toggle="collapse" aria-expanded="false"
                                        class="diagnosis_icon dropdown-toggle">カラー診断</a>
                                    <ul class="collapse list-unstyled" id="menu_Submenu">
                                        <li><a href="#">どんなもの？</a></li>
                                        <li><a href="color_check.jsp">診断スタート</a></li>
                                    </ul>
                                </li>
                                <li class="active"><a class="reco_icon" href="#">おすすめ品</a></li>
                                <li><a class="search_icon" href="./ProductSearchServlet?page=c">商品検索</a>
                                </li>
                                <li><a class="employee_icon" href="./CustomerSearchServlet">従業員メニューへ</a>
                                </li>
                            </ul>
                        </nav>

                    </div>
                </div>
            </header>

            <!-- メイン -->
            <div class="col-10 mx-auto mb-5 main">
                <div class="col-4 mx-auto mt-5">
                    <div class="ribbon9">
                        <h1>おすすめ商品</h1>
                    </div>
                </div>
                <div class="col-8 mx-auto">
                    <div id="onebook" class="mx-auto"></div>
                </div>
            </div>

            <!-- モーダル -->
            <div class="modal fade" id="photo-modal" tabindex="-1" role="dialog" aria-labelledby="label1"
                aria-hidden="true">
                <div class="modal-dialog modal-xl" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="label1">商品詳細</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-4">
                                    <img src="" id="photo" width="60%" height="60%" alt="">
                                    <p id="manufacturer" class="mt-5 ml-2"></p>
                                    <p id="brand" class="ml-2"></p>
                                </div>
                                <div class="col-8">
                                    <div class="row ml-1">
                                        <p id="p_name" class="mr-5 mt-2"></p>
                                        <p id="pc1" class=""></p>
                                        <p id="pc2" class=""></p>
                                    </div>
                                    <div class="row ml-1">
                                        <p id="c_num" class="mr-3"></p>
                                        <p id="c_name"></p>
                                    </div>
                                    <p id="price"></p>
                                    <p id="date"></p>
                                    <p id="color"></p>
                                    <p class="mt-5" id="memo">商品説明</p>
                                    <p id="memo"></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <script src="./js/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="./js/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="./js/jquery.mousewheel.min.js"></script>
        <script type="text/javascript" src="./js/three.min.js"></script>
        <script type="text/javascript" src="./js/jquery.onebook3d-2.33.js"></script>
        <script src="./js/popper.min.js"></script>
        <script src="./js/bootstrap.min.js"></script>
        <!-- アラート -->
        <script src="./js/sweetalert.js"></script>

        <script>
            const src = ${ PHOTO };
            $('#onebook').onebook(src, {
                startPage: 1
            });

            function myfunction(aTag) {                         //aタグがクリックされたとき onclick属性
                const title = aTag.dataset.title;

                const data = {                                  //Servletに送信するデータ
                    method: 'POST',
                    headers: {
                        'Content-Type': "text/plain"
                    },
                    body: title         						//このbodyが変数dataの内容
                };

                fetch('./RecommendDetailServlet', data)         // ./FetchServletに接続　【第一引数にURL,第二引数に渡すデータ】
                    .then((response) => response.json())        //responseをjsonで受け取る
                    .then((json) => {
                        console.log(json);
                        document.querySelector('#p_name').innerHTML = json[0].productName;
                        document.querySelector('#c_num').innerHTML = json[0].colorNum;
                        document.querySelector('#c_name').innerHTML = json[0].colorName;
                        document.querySelector('#photo').src = json[0].photo;
                        document.querySelector('#photo').alt = json[0].productName;
                        document.querySelector('#price').innerHTML = '価格：' + json[0].outPrice + '円（税抜）';
                        document.querySelector('#date').innerHTML = '発売日：' + json[0].outDate;
                        document.querySelector('#memo').innerHTML = json[0].memo;
                        document.querySelector('#brand').innerHTML = json[0].brandName;
                        document.querySelector('#manufacturer').innerHTML = json[0].manufacturerName;
                        document.querySelector('#color').innerHTML = 'カラーカテゴリ：' + json[0].colorCategoryName;

                        const pc1 = document.querySelector('#pc1');
                        const pc2 = document.querySelector('#pc2');

                        console.log(pc1);
                        console.log(pc2);

                        console.log(json[0].pcName1);

                        if (json[0].pcName1 == 'イエローベース,ブルーベース') {
                            document.querySelector('#pc1').innerHTML = 'イエベ';
                            pc1.classList.remove('color', 'yellow', 'blue', 'col-2');
                            pc1.classList.add('color', 'yellow', 'col-2', 'mr-3');
                            document.querySelector('#pc2').innerHTML = 'ブルベ';
                            pc2.classList.remove('color', 'yellow', 'blue', 'col-2');
                            pc2.classList.add('color', 'blue', 'col-2');
                        } else if (json[0].pcName1 == 'イエローベース') {
                            document.querySelector('#pc1').innerHTML = 'イエベ';
                            pc1.classList.add('color', 'yellow', 'col-2');
                            document.querySelector('#pc2').innerHTML = '';
                            pc2.classList.remove('color', 'yellow', 'blue', 'col-2');
                        } else {
                            document.querySelector('#pc1').innerHTML = 'ブルベ';
                            pc1.classList.add('color', 'blue', 'col-2');
                            document.querySelector('#pc2').innerHTML = '';
                            pc2.classList.remove('color', 'yellow', 'blue', 'col-2');
                        }
                    });
            }

            //アラート
            if (${ LISTNUM } == 0) {
                swal({
                    title: "一致する商品がありませんでした",
                    text: "カラーかキーワードを変更してください",
                    type: "warning",
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "OK"
                });
            }

            const btn = document.querySelector('.btn-danger');
            btn.addEventListener('click', (event) => {
                window.location.href = 'reco_category.jsp';
            });


        </script>
</body>

</html>