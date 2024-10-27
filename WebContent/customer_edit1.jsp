<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">

<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- Bootstrap CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <!-- Validation CSS -->
    <link rel="stylesheet" href="./css/validationEngine.jquery.css">

    <!-- CSS -->
    <link rel="stylesheet" href="css/sidebar.css">
    <link rel="stylesheet" href="css/product.css">

    <link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
    <title>顧客編集</title>

</head>


<body>
    <div class="container-fluid customer customer_edit">
        <div class="row">

            <!-- サイドバー -->
            <nav id="sidebar" class="col-md-2 d-none d-md-block employee">
                <p class="text-center pt-4 pb-4 mb-0">${sessionScope.e_name}さん</p>
                <ul class="list-unstyled components">
                    <a href="menu.jsp" class="menu_icon">顧客メニュー</a>

                    <li><a class="dropdown-toggle employee_icon" data-toggle="collapse" aria-expanded="false"
                            href="#employee_Submenu">従業員管理</a>
                        <ul class="collapse list-unstyled" id="employee_Submenu">
                            <li><a href="#">従業員登録</a></li>
                            <li><a href="#">従業員検索</a></li>
                        </ul>
                    </li>
                    <li><a href="#customer_Submenu" data-toggle="collapse" aria-expanded="false"
                            class="dropdown-toggle customer_icon">顧客管理</a>
                        <ul class="collapse list-unstyled" id="customer_Submenu">
                            <li><a href="customer_regist.jsp">顧客登録</a></li>
                            <li><a href="./CustomerSearchServlet">顧客検索</a></li>
                            <li><a href="#">顧客編集</a></li>
                        </ul>
                    </li>
                    <li><a href="#product_Submenu" data-toggle="collapse" aria-expanded="false"
                            class="dropdown-toggle product_icon">商品管理</a>
                        <ul class="collapse list-unstyled" id="product_Submenu">
                            <li><a href="product_regist.jsp">商品登録</a></li>
                            <li><a href="./ProductSearchServlet">商品検索</a></li>
                            <li><a href="./product_edit1.jsp">商品編集</a></li>
                        </ul>
                    </li>
                    <li><a class="mail_icon" href="./ProductSearchServlet?page=m">メール作成</a></li>
                </ul>
            </nav>
            <div class="col-md-2"></div>

            <!-- メイン -->
            <div class="col-md-10 mx-auto">
                <div class="row">
                    <div class="col-10 mt-5 box">
                        <form action="./CustomerDetailServlet" method="get" id="form">
                            <div class="col-12 mt-3 mb-5 title">
                                <h1>顧客情報の編集</h1>
                                <p class="mt-4 text-center">編集したい顧客の会員番号を入力してください</p>
                                <input type="text" name="customer_code"
                                    class="form-control col-8 mt-4 mx-auto validate[custom[onlyLetterNumber]]"
                                    placeholder="12345678">
                                <input type="hidden" name="page" value="e">
                            </div>
                            <div class="button col-12 mt-5 mb-5">
                                <input type="submit" name="sb" class="sb col-2" value="検索する">
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