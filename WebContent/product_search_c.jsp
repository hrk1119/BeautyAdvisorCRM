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

	<!-- Select2.css -->
	<link rel="stylesheet" href="./css/select2.min.css">

	<!-- Pagination css -->
	<link rel="stylesheet" href="./css/simplePagination.css">

	<!-- CSS -->
	<link rel="stylesheet" href="css/product.css">
	<link rel="stylesheet" href="css/sidebar.css">

	<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
	<title>Document</title>

	<style>
	</style>

</head>


<body>
	<div class="container-fluid customer product_search_c">
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
								<li><a class="reco_icon" href="reco_category.jsp">おすすめ品</a></li>
								<li class="active"><a class="search_icon" href="./ProductSearchServlet?page=c">商品検索</a>
								</li>
								<li><a class="employee_icon" href="./CustomerSearchServlet">従業員メニューへ</a>
								</li>
							</ul>
						</nav>
					</div>
				</div>
			</header>

			<!-- メイン -->
			<div class="col-10 mx-auto">
				<!-- Acordion container -->
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading mt-5 col-12">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
								<h4 class="panel-title">
									商品を検索する <i class="indicator glyphicon glyphicon-chevron-down-custom  pull-right"><span
											class="sp-1"></span><span class="sp-2"></span></i>
								</h4>
							</a>
						</div>

						<div id="collapse1" class="panel-collapse collapse">
							<div class="panel-body box1">
								<ul class="nav nav-tabs pt-4">
									<li class="nav-item"><a href="#one" class="nav-link active" data-toggle="tab">特定の商品を調べる</a>
									</li>
									<li class="nav-item"><a href="#two" class="nav-link" data-toggle="tab">複数の条件から調べる</a></li>
								</ul>
								<form action="./ProductSearchServlet" method="get">
									<div class="tab-content">
										<div id="one" class="tab-pane active">
											<div class="mx-auto mt-4 col-8">
												<p>JANコードを入力してください</p>
												<input type="text" name="product_code" class="form-control" placeholder="例：4901872966646">
											</div>
										</div>
										<div id="two" class="tab-pane">
											<table class="table search mt-4 col-11 mx-auto">
												<tr>
													<th class="start">メーカー名</th>
													<td class="start"><input type="text" name="manufacturer" class="form-control col-10"
															placeholder="例：資生堂"></td>
												</tr>
												<tr>
													<th>ブランド名</th>
													<td><input type="text" name="brand" class="form-control col-10" placeholder="例：マキアージュ"></td>
												</tr>
												<tr>
													<th>商品名</th>
													<td><input type="text" name="product_name" class="form-control col-10"
															placeholder="例：ドラマティックルージュN">
													</td>
												</tr>
												<tr>
													<th>カラー名</th>
													<td><input type="text" name="product_color" class="form-control col-10"
															placeholder="例：グッドムードレッド"></td>
												</tr>
												<tr>
													<th>カラー番号</th>
													<td><input type="text" name="product_num" class="form-control col-10" placeholder="例：RD305">
													</td>
												</tr>
												<tr>
													<th>キーワード</th>
													<td><input type="text" name="product_word" class="form-control col-10" placeholder="例：シミ">
													</td>
												</tr>
												<tr>
													<th>パーソナルカラー</th>
													<td>
														<div class="checkbox">
															<label class="p"><input type="checkbox" name="pc_color" value="yellow">イエローベース</label>
															<label class="p"><input type="checkbox" name="pc_color" value="blue"
																	class="ml-3">ブルーベース</label> <label class="p"><input type="checkbox" name="pc_color"
																	value="unknown" class="ml-3">不明</label>
														</div>
													</td>
												</tr>
												<tr>
													<th>商品カテゴリー</th>
													<td><select name="category" class="form-control col-8">
															<option value="">選択してください</option>
															<optgroup label="ポイントメイク">
																<option value="口紅・リップ">口紅・リップ</option>
																<option value="チーク">チーク</option>
																<option value="アイシャドウ">アイシャドウ</option>
																<option value="アイライナー">アイライナー</option>
																<option value="アイブロウ">アイブロウ</option>
																<option value="マスカラ">マスカラ</option>
															</optgroup>
															<optgroup label="ベースメイク">
																<option value="リキッドファンデーション">リキッドファンデーション</option>
																<option value="パウダーファンデーション">パウダーファンデーション</option>
																<option value="クリーム・ジェルファンデーション">クリーム・ジェルファンデーション
																</option>
																<option value="化粧下地">化粧下地</option>
																<option value="コンシーラー">コンシーラー</option>
																<option value="フェイスパウダー">フェイスパウダー</option>
															</optgroup>
														</select></td>
												</tr>
												<tr>
													<th>カラーカテゴリー</th>
													<td><select name="c_category" class="form-control col-8">
															<option value="0">選択してください</option>
															<option value="1">ローズ</option>
															<option value="2">ピンク</option>
															<option value="3">レッド</option>
															<option value="4">ブラウン</option>
															<option value="5">ベージュ</option>
															<option value="6">オレンジ</option>
															<option value="7">バイオレット</option>
															<option value="8">グリーン</option>
															<option value="9">ブルー</option>
															<option value="10">イエロー</option>
															<option value="11">ゴールド</option>
															<option value="12">シルバー</option>
															<option value="13">グレー</option>
															<option value="14">マルチカラー</option>
														</select>
														<p class="m-0 pt-2">※ポイントメイクのみ</p>
													</td>
												</tr>
												<tr>
													<th>価格</th>
													<td>
														<div class="row ml-1">
															<div class="price">
																<input type="text" name="low_price" class="form-control pl-4">
															</div>
															<p class="ml-3 mr-3 mt-1">～</p>
															<div class="price">
																<input type="text" name="high_price" class="form-control pl-4">
															</div>
														</div>
													</td>
												</tr>
											</table>
										</div>
										<input type="hidden" name="page" value="c">
										<div class="button col-12 mt-5 pb-5">
											<input type="submit" name="sb" class="sb col-2" value="検索する">
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /アコーディオン -->

				<!-- 結果 -->

				<c:set var="page_num" value="${(listNum - 1) / 9+1}" />
				<c:set var="data_num" value="1" />
				<c:forEach var="i" begin="1" end="${page_num}" step="1">
					<div class="row mt-5 mb-5 content selection" id="page-${i}">
						<c:forEach items="${list}" var="l" begin="${data_num}" end="${i * 9}">
							<div class="card col-3 mx-1 mt-5">
								<img src="${l.photo}" class="card-img-top" alt="${l.productName}">
								<div class="card-body">
									<h5 class="card-title">${l.productName}</h5>
									<p class="card-text">
										${l.brandName}<br>${l.colorNum} ${l.colorName}<br>
										&yen; ${l.outPrice}
									</p>
									<form action="./ProductDetailServlet" method="get">
										<input type="hidden" name="product_code" value="${l.productId}"> <input type="submit"
											class="btn_pink" name="sb" value="詳しく見る" />
									</form>
								</div>
							</div>
							<c:set var="data_num" value="${data_num + 1}" />
						</c:forEach>
					</div>
				</c:forEach>
			</div>
			<div class="pagination-holder clearfix col-12 mb-5">
				<div id="light-pagination" class="pagination"></div>
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
	<!-- Select2 -->
	<script src="./js/select2.min.js"></script>
	<script src="./js/ja.js"></script>
	<!-- ページネーション -->
	<script src="./js/jquery.simplePagination.js"></script>

	<script type="text/javascript">
		/**セレクトボックスの検索**/
		$(function () {
			$('.select').select2({
				language: "ja" //日本語化
			});
		})

		/**入力チェック**/
		jQuery(document).ready(function () {
			jQuery("#form").validationEngine({ //formのidと合わせる
				promptPosition: "topRight"
			});
		});

		//Toggle chevrons

		function toggleChevron(e) {
			$(e.target)
				.prev('.panel-heading')
				.find("i.indicator")
				.toggleClass(
					'glyphicon-chevron-down-custom glyphicon-chevron-up-custom');
		}
		$('#accordion').on('hidden.bs.collapse', toggleChevron);
		$('#accordion').on('shown.bs.collapse', toggleChevron);

		//ページネーション
		$(function () {
			$('.selection').hide();
			$('#page-1').show();
			const page = Math.ceil((${ listNum } - 1) / 9);
		console.log(page);
		$(".pagination").pagination({
			items: page,
			prevText: "前へ",
			nextText: "次へ",
			cssStyle: 'light-theme',
			onPageClick: function (currentPageNumber) {
				showPage(currentPageNumber);
			}
		})
		});
		function showPage(currentPageNumber) {
			var page = "#page-" + currentPageNumber;
			$('.selection').hide();
			$(page).show();
		}
	</script>
</body>

</html>