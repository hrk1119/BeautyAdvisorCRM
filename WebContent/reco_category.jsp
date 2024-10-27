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

	<!-- アラート -->
	<link href="./css/docs.css" rel="stylesheet">
	<link href="./css/sweetalert.css" rel="stylesheet">

	<link rel="stylesheet" href="css/sidebar.css">
	<link rel="stylesheet" href="css/recommend.css">

	<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
	<title>カテゴリー選択</title>

</head>


<body>
	<div class="container-fluid customer reco_category">
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
								<li><a href="#menu_Submenu" data-toggle="collapse" aria-expanded="false"
										class="diagnosis_icon dropdown-toggle">カラー診断</a>
									<ul class="collapse list-unstyled" id="menu_Submenu">
										<li><a href="#">どんなもの？</a></li>
										<li><a href="color_check.jsp">診断スタート</a></li>
									</ul>
								</li>
								<li class="active"><a class="reco_icon" href="#">おすすめ品</a></li>
								<li><a class="search_icon" href="./ProductSearchServlet?page=c">商品検索</a></li>
								<li><a class="employee_icon" href="./CustomerSearchServlet">従業員メニューへ</a>
								</li>
							</ul>
						</nav>

					</div>
				</div>
			</header>

			<!-- メイン -->
			<div class="col-md-10 mx-auto">
				<form action="./RecommendServlet" method="get">
					<!-- カテゴリー -->
					<div class="col-10 mt-5 mb-5 box">
						<div class="col-11 mt-4 ml-4">
							<p>カテゴリーを選択してください</p>
							<p class="category">ポイントメイク</p>
							<div class="col-12 cp_ipradio">
								<label for="d_rb1"> <input type="radio" id="d_rb1" name="category" value="1"
										onclick="change();" checked />
									<div>
										口紅・グロス<span>選択中</span>
									</div>
								</label> <label for="d_rb2"> <input type="radio" id="d_rb2" name="category" value="2"
										onclick="change();" />
									<div>
										チーク<span>選択中</span>
									</div>
								</label> <label for="d_rb3"> <input type="radio" id="d_rb3" name="category" value="3"
										onclick="change();" />
									<div>
										アイシャドウ<span>選択中</span>
									</div>
								</label>
							</div>
							<p class="category">ベースメイク</p>
							<div class="col-12 cp_ipradio">
								<label for="d_rb4"> <input type="radio" id="d_rb4" name="category" value="7"
										onclick="change();" />
									<div>
										リキッド<span>選択中</span>
									</div>
								</label> <label for="d_rb5"> <input type="radio" id="d_rb5" name="category" value="8"
										onclick="change();" />
									<div>
										パウダー<span>選択中</span>
									</div>
								</label> <label for="d_rb6"> <input type="radio" id="d_rb6" name="category" value="9"
										onclick="change();" />
									<div>
										クリーム・ジェル<span>選択中</span>
									</div>
								</label> <label for="d_rb7"> <input type="radio" id="d_rb7" name="category" value="10"
										onclick="change();" />
									<div>
										化粧下地<span>選択中</span>
									</div>
								</label>
							</div>
						</div>
					</div>

					<!-- カラー -->
					<div class="col-10 mt-5 mb-5 box check" id="firstBox">
						<p class="ml-5 my-4">カラーを選択してください</p>
						<div class="row mx-auto justify-content-between col-11">
							<input type="checkbox" class="check_box" id="point_check1" name="color" value="1" /> <label
								id="rose" class="check_label" for="point_check1">ローズ</label> <input type="checkbox"
								class="check_box" id="point_check2" name="color" value="2" /> <label id="pink"
								class="check_label" for="point_check2">ピンク</label> <input type="checkbox"
								class="check_box" id="point_check3" name="color" value="3" /> <label id="red"
								class="check_label" for="point_check3">レッド</label>
						</div>
						<div class="row mx-auto justify-content-between col-11">
							<input type="checkbox" class="check_box" id="point_check4" name="color" value="4" /> <label
								id="brown" class="check_label" for="point_check4">ブラウン</label> <input type="checkbox"
								class="check_box" id="point_check5" name="color" value="5" /> <label id="beige"
								class="check_label" for="point_check5">ベージュ</label> <input type="checkbox"
								class="check_box" id="point_check6" name="color" value="6" /> <label id="orange"
								class="check_label" for="point_check6">オレンジ</label>
						</div>
						<div class="row mx-auto justify-content-between col-11">
							<input type="checkbox" class="check_box" id="point_check7" name="color" value="7" /> <label
								id="purple" class="check_label" for="point_check7">バイオレット</label> <input type="checkbox"
								class="check_box" id="point_check8" name="color" value="8" /> <label id="green"
								class="check_label" for="point_check8">グリーン</label> <input type="checkbox"
								class="check_box" id="point_check9" name="color" value="9" /> <label id="blue"
								class="check_label" for="point_check9">ブルー</label>
						</div>
						<div class="row mx-auto justify-content-between col-11">
							<input type="checkbox" class="check_box" id="point_check10" name="color" value="10" />
							<label id="yellow" class="check_label" for="point_check10">イエロー</label> <input
								type="checkbox" class="check_box" id="point_check11" name="color" value="11" /> <label
								id="gold" class="check_label" for="point_check11">ゴールド</label> <input type="checkbox"
								class="check_box" id="point_check12" name="color" value="12" />
							<label id="silver" class="check_label" for="point_check12">シルバー</label>
						</div>
						<div class="row mx-auto justify-content-between col-11">
							<input type="checkbox" class="check_box" id="point_check13" name="color" value="13" />
							<label id="gray" class="check_label" for="point_check13">グレー</label> <input type="checkbox"
								class="check_box" id="point_check14" name="color" value="14" />
							<label id="Multi" class="check_label" for="point_check14">マルチカラー</label>
							<div class="none" style="width: 200px; height: 50px;"></div>
						</div>
						<div class="col-12 btn">
							<input type="submit" name="color_sb" value="おすすめ商品は？" class="button px-5 py-3">
						</div>
					</div>

					<!-- キーワード -->
					<div class="col-10 mt-5 mb-5 box check" id="secondBox">
						<p class="ml-5 my-4">キーワードを選択してください</p>
						<div class="row mx-auto justify-content-between col-11">
							<input type="checkbox" class="check_box" id="check1" name="key" value="1" /> <label
								class="check_label" for="check1">透明感がない</label>
							<input type="checkbox" class="check_box" id="check2" name="key" value="2" /> <label
								class="check_label" for="check2">毛穴</label>
							<input type="checkbox" class="check_box" id="check3" name="key" value="3" /> <label
								class="check_label" for="check3">乾燥肌</label>
						</div>
						<div class="row mx-auto justify-content-between col-11">
							<input type="checkbox" class="check_box" id="check4" name="key" value="4" /> <label
								class="check_label" for="check4">シワ</label>
							<input type="checkbox" class="check_box" id="check5" name="key" value="5" /> <label
								class="check_label" for="check5">ハリ</label>
							<input type="checkbox" class="check_box" id="check6" name="key" value="6" /> <label
								class="check_label" for="check6">薄づき</label>
						</div>
						<div class="row mx-auto justify-content-between col-11">
							<input type="checkbox" class="check_box" id="check7" name="key" value="7" /> <label
								class="check_label" for="check7">つや</label>
							<input type="checkbox" class="check_box" id="check8" name="key" value="8" /> <label
								class="check_label" for="check8">マット</label>
							<input type="checkbox" class="check_box" id="check9" name="key" value="9" /> <label
								class="check_label" for="check9">カバー力</label>

						</div>
						<div class="row mx-auto justify-content-between end col-11">
							<input type="checkbox" class="check_box ml-5" id="check10" name="key" value="10" /> <label
								class="check_label" for="check10">シミ ・ そばかす ・ 美白</label> <input type="checkbox"
								class="check_box" id="check11" name="key" value="11" /> <label class="check_label"
								for="check11">化粧崩れ ・ テカリ</label>
							<div class="none" style="width: 200px; height: 50px;"></div>
						</div>
						<div class="col-12 btn">
							<input type="submit" name="key_sb" value="おすすめ商品は？" id="btn" class="button px-5 py-3">
						</div>
				</form>
			</div>
		</div>
	</div>

	<script src="./js/jquery-3.3.1.min.js"></script>
	<script src="./js/popper.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>


	<script type="text/javascript">
		function change() {
			radio = document.getElementsByName('category')
			if (radio[0].checked || radio[1].checked category|| radio[2].checked) {
				document.getElementById('firstBox').style.display = "";
				document.getElementById('secondBox').style.display = "none";
			} else if (radio[3].checked || radio[4].checked || radio[5].checked
				|| radio[6].checked) {
				document.getElementById('firstBox').style.display = "none";
				document.getElementById('secondBox').style.display = "";
			}
		}
		//オンロードさせ、リロード時に選択を保持
		window.onload = change;

	</script>
</body>

</html>