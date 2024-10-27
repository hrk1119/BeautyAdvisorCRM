<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

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

	<!-- アラート -->
	<link href="./css/docs.css" rel="stylesheet">
	<link href="./css/sweetalert.css" rel="stylesheet">

	<link rel="stylesheet" href="css/login.css">

	<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
	<title>メニュー</title>

	<style>
		.top{
			margin-top: 100px;
		}
	</style>

</head>


<body>
	<div class="container-fluid start">
		<div class="row">
			<!-- <c:choose>
				<c:when test="${sessionScope.checkInName != null}">
					<p>${sessionScope.checkInName}様</p>
				</c:when>
				<c:when test="${sessionScope.checkInName == ''}">
					<p></p>
				</c:when>
			</c:choose> -->
			<div class="col-12 top">
				<div class="input-group input-group-lg col-5 customer" id="check">
					<input type="text" name="customer_code" id="name"
						class="form-control validate[required,custom[onlyLetterNumber]]" placeholder="会員番号を入力してください"
						onChange="check(this)"> <input class="btn btn-outline-secondary sweet-1" type="submit"
						value="チェック" id="btn" onclick="_gaq.push(['_trackEvent', 'example', 'try', 'sweet-1']);">
				</div>
			</div>
		</div>

		<div class="col-12 row menu">
			<a href="color_check.jsp" class="btn_main">カラー診断</a> <a href="reco_category.jsp" class="btn_main">おすすめ品</a>
			<a href="./ProductSearchServlet?page=c" class="btn_main">商品検索</a>
		</div>

		<div class="col-12 employee">
			<a href="./CustomerSearchServlet">管理メニューへ</a>
		</div>
	</div>

	<script src="./js/jquery-3.3.1.min.js"></script>
	<!-- bootstrap -->
	<script src="./js/popper.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<!-- アラート -->
	<script src="./js/sweetalert.js"></script>
	<!-- 入力チェック -->
	<script src="./js/jquery.validationEngine.js"></script>
	<script src="./js/jquery.validationEngine-ja.js"></script>

	<!-- <script src="./js/menu.js"></script> -->

	<script>
		/**入力チェック**/
		jQuery(document).ready(function () {
			jQuery("#check").validationEngine({ //formのidと合わせる
				promptPosition: "topRight"
			});
		});

		const name = document.querySelector('#name');
		const btn = document.querySelector('#btn');

		//コンソールに表示
		console.log(name);
		console.log(btn);

		if (name.value == "") {
			btn.disabled = true;
		}

		function check($this) {
			if (name.value != "") {
				btn.disabled = false;
			}
		};

		btn.addEventListener('click', fetchServlet);        //btnがクリックされたとき、fetchServletを実行

		function fetchServlet() {
			const data = {                                  //Servletに送信するデータ
				method: 'POST',
				headers: {
					'Content-Type': "text/plain"
				},
				body: name.value							//このbodyが変数dataの内容
			};

			fetch('./CheckInServlet', data)                   // ./FetchServletに接続　【第一引数にURL,第二引数に渡すデータ】
				.then((response) => response.text())        //responseをjsonで受け取る
				.then((json) => {
					console.log(json);
					if (json == "") {
						swal({
							title: "会員情報がありません",
							text: "会員番号を確認してください",
							type: "warning",
							confirmButtonClass: "btn-danger",
							confirmButtonText: "OK"
						},
							function () {
								location.reload();
							});
					} else {
						swal({
							title: json + " 様",
							text: "チェックインが完了しました！",
							type: "success",
							confirmButtonClass: "btn-danger",
							confirmButtonText: "OK"
						},
							function () {
								location.reload();
							});
					}
				});
		}
	</script>

</body>

</html>