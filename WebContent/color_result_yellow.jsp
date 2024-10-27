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
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="css/sidebar.css">
    <link rel="stylesheet" href="css/color.css">

    <link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
    <title>カラー診断</title>

</head>


<body>
    <div class="container-fluid customer color_result">
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
                                        <li><a href="color_check.jsp">診断スタート</a></li>
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
                        <h1>あなたのパーソナルカラーは<em class="yellow">イエローベース</em>です！</h1>
                        <div class="row mt-5">
                            <div class="col-4 p-0">
                                <img src="img/yellow.jpg" alt="人の写真" width="300px" height="600px" class="img1">
                            </div>
                            <div class="col-8 mt-4">
                                <h2 class="cp_h1title_yellow">イエローベースの特徴は？</h2>
                                <p class="m-3">イエローベースの色を取り入れることで、肌の色が生き生きと健康的に見えます！</p>

                                <div class="dl_yellow">
                                    <dl>
                                        <dt>アイシャドウ</dt>
                                        <dd>ブラウン、ゴールドなどのウォーム系が似合います。</dd>
                                    </dl>
                                    <dl>
                                        <dt>チーク</dt>
                                        <dd>少しだけ黄みの入ったピンク（ピーチなど）を使い、ハイライトで周りをなじませると、透明感のある、ほどよい血色の印象に仕上がります。</dd>
                                    </dl>
                                    <dl>
                                        <dt>口紅・リップ</dt>
                                        <dd>朱赤やオレンジなど「黄みニュアンス」のある口紅がしっくり似合い、明るく健康的なイメージに仕上がります。</dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                        <div class="check_yellow m-4">
                            <p class="m-3">＊こちらのカラーがおすすめです＊</p>
                            <img src="img/yellow_color.png" alt="イエローベースの色" width="600px" height="300px" class="img2">
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <script src="./js/jquery-3.3.1.min.js"></script>
    <script src="./js/popper.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
</body>

</html>