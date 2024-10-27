<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">

<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- Bootstrap CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Validation CSS -->
    <link rel="stylesheet" href="./css/validationEngine.jquery.css">

    <link rel="stylesheet" href="css/sidebar.css">
    <link rel="stylesheet" href="css/color.css">

    <link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
    <title>カラー診断</title>

</head>


<body>
    <div class="container-fluid customer color_check">
        <div class="row">

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
                                <li class="active"><a href="#menu_Submenu" data-toggle="collapse" aria-expanded="false"
                                        class="diagnosis_icon dropdown-toggle">カラー診断</a>
                                    <ul class="collapse list-unstyled" id="menu_Submenu">
                                        <li><a href="#">どんなもの？</a></li>
                                        <li><a href="#">診断スタート</a></li>
                                    </ul>
                                </li>
                                <li><a class="reco_icon" href="reco_category.jsp">おすすめ品</a></li>
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
            <div class="col-md-10 mx-auto">
                <div class="row">
                    <div class="col-11 mt-5 mb-5 box">
                        <h1>カラー診断をしてみよう！</h1>
                        <form action="./ColorServlet" method="get" id="form">

                            <div class="q1">
                                <h2 class="col-8 m-auto">01 美容院で勧められるヘアカラーは？</h2>
                                <div class="row cp_ipradio">
                                    <div class="col-12 mt-5 img">
                                        <img src="img/color.JPG" alt="" width="300px" height="140px" class="q1_img">
                                    </div>
                                    <div class="col-md-12 text row">
                                        <label>
                                            <input type="radio" class="option-input radio mt-5 validate[required]"
                                                name="q1" value="yellow" checked />
                                            明るめのカラー
                                        </label>
                                        <label class="ml-5">
                                            <input type="radio" class="option-input radio mt-5 validate[required]"
                                                name="q1" value="blue" />
                                            暗めのカラー
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="q2">
                                <h2 class="col-8 m-auto">02 肌に映えるアクセサリーの色は？</h2>
                                <div class="row cp_ipradio">
                                    <div class="col-12 mt-5 img">
                                        <img src="img/color2.JPG" alt="" width="300px" height="140px" class="q2_img">
                                    </div>
                                    <div class="col-12 text row">
                                        <label>
                                            <input type="radio" class="option-input radio mt-5 validate[required]"
                                                name="q2" value="yellow" checked />
                                            ゴールド
                                        </label>
                                        <label>
                                            <input type="radio" class="option-input radio ml-5 mt-5 validate[required]"
                                                name="q2" value="blue" />
                                            シルバー
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="q3">
                                <h2 class="col-8 m-auto">03 手首の血管の色はどちらが多い？</h2>
                                <div class="row cp_ipradio">
                                    <div class="col-12 mt-5 img">
                                        <img src="img/color3.png" alt="" width="300px" height="140px" class="q3_img">
                                    </div>
                                    <div class="col-12 text row">
                                        <label>
                                            <input type="radio" class="option-input radio mt-5 validate[required]"
                                                name="q3" value="yellow" checked />
                                            緑っぽい色
                                        </label>
                                        <label>
                                            <input type="radio" class="option-input radio ml-5 mt-5 validate[required]"
                                                name="q3" value="blue" />
                                            青もしくは紫っぽい色
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="q4">
                                <h2 class="col-8 m-auto">04 スカーフを選ぶなら？</h2>
                                <div class="row cp_ipradio">
                                    <div class="col-12 mt-5 img">
                                        <img src="img/color4.JPG" alt="" width="300px" height="140px" class="q4_img">
                                    </div>
                                    <div class="col-12 text row">
                                        <label>
                                            <input type="radio" class="option-input radio mt-5 validate[required]"
                                                name="q4" value="yellow" checked />
                                            オレンジ系統の色
                                        </label>
                                        <label>
                                            <input type="radio" class="option-input radio ml-5 mt-5 validate[required]"
                                                name="q4" value="blue" />
                                            パープル系統の色
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="q5">
                                <h2 class="col-8 m-auto">05 リップカラーで評判の良い色は？</h2>
                                <div class="row cp_ipradio">
                                    <div class="col-12 mt-5 img">
                                        <img src="img/color5.JPG" alt="" width="300px" height="140px" class="q5_img">
                                    </div>
                                    <div class="col-12 text row">
                                        <label>
                                            <input type="radio" class="option-input radio mt-5 validate[required]"
                                                name="q5" value="yellow" checked />
                                            コーラルやオレンジ
                                        </label>
                                        <label>
                                            <input type="radio" class="option-input radio ml-5 mt-5 validate[required]"
                                                name="q5" value="blue" />
                                            ローズや青みピンク
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="button col-12 mb-5">
                                <input type="submit" class="sb" value="診断する" name="sb" />
                            </div>

                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <script src="./js/jquery-3.3.1.min.js"></script>
    <!-- bootstrap -->
    <script src="./js/popper.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <!-- 入力チェック -->
    <script src="./js/jquery.validationEngine.js"></script>
    <script src="./js/jquery.validationEngine-ja.js"></script>

    <script type="text/javascript">
        /**入力チェック**/
        jQuery(document).ready(function () {
            jQuery("#form").validationEngine({ //formのidと合わせる
                promptPosition: "topRight"
            });
        });
    </script>
</body>

</html>