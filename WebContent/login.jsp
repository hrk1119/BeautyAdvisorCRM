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

    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/bootstrap.css">

    <title>ログイン</title>

</head>

<body>

    <div class="container-fluid login">
        <div class="row justify-content-center">
            <div class="col-md-4 col-sm-6 text-center box">
                <div class="fdb-box">
                    <div class="row">
                        <div class="col">
                            <h1>Login</h1>
                        </div>
                    </div>
                    <div class="row mt-4">
                        <div class="col">
                            <form action="./LoginServlet" method="get">
                                <input type="text" class="form-control" placeholder="従業員ID" name="id" value="" />
                        </div>
                    </div>
                    <div class="row mt-4">
                        <div class="col">
                            <input type="password" class="form-control mb-1" placeholder="パスワード" name="pass" value="" />
                        </div>
                    </div>
                    <div class="row mt-4">
                        <div class="col">
                            <input type="submit" name="sb" value="ログイン" class="button mb-3">
                            </form>
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