<%@page import="java.util.ArrayList"%>
<%@page import="bean.Product"%>
<%@page import="java.util.List"%>
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

	<!-- table css-->
	<link href="./css/semantic.min.css" rel="stylesheet">
	<link href="./css/dataTables.semanticui.min.css" rel="stylesheet">

	<link rel="stylesheet" href="css/sidebar.css">
	<link rel="stylesheet" href="css/product.css">

	<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
	<title>商品詳細</title>

</head>


<body>
	<div class="container-fluid customer product_search">
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
							<li><a href="./customer_edit1.jsp">顧客編集</a></li>
						</ul>
					</li>
					<li class="active"><a href="#product_Submenu" data-toggle="collapse" aria-expanded="false"
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

						<div class="col-12 mt-3 mb-5 title">
							<h1>商品検索</h1>
						</div>

						<ul class="nav nav-tabs">
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
									<table class="table search mt-4">
										<tr>
											<th class="start">メーカー名</th>
											<td class="start"><input type="text" name="manufacturer" class="form-control col-8"
													placeholder="例：資生堂"></td>
										</tr>
										<tr>
											<th>ブランド名</th>
											<td><input type="text" name="brand" class="form-control col-8" placeholder="例：マキアージュ"></td>
										</tr>
										<tr>
											<th>商品名</th>
											<td><input type="text" name="product_name" class="form-control col-8"
													placeholder="例：ドラマティックルージュN"></td>
										</tr>
										<tr>
											<th>カラー名</th>
											<td><input type="text" name="product_color" class="form-control col-8" placeholder="例：グッドムードレッド">
											</td>
										</tr>
										<tr>
											<th>カラー番号</th>
											<td><input type="text" name="product_num" class="form-control col-8" placeholder="例：RD305"></td>
										</tr>
										<tr>
											<th>キーワード</th>
											<td><input type="text" name="product_word" class="form-control col-8" placeholder="例：シミ"></td>
										</tr>
										<tr>
											<th>パーソナルカラー</th>
											<td>
												<div class="checkbox">
													<label class="p"><input type="checkbox" name="pc_color" value="yellow">イエローベース</label> <label
														class="p"><input type="checkbox" name="pc_color" value="blue" class="ml-3">ブルーベース</label>
													<label class="p"><input type="checkbox" name="pc_color" value="unknown"
															class="ml-3">不明</label>
												</div>
											</td>
										</tr>
										<tr>
											<th>商品カテゴリー</th>
											<td><select name="category" class="form-control col-6">
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
														<option value="クリーム・ジェルファンデーション">クリーム・ジェルファンデーション</option>
														<option value="化粧下地">化粧下地</option>
														<option value="コンシーラー">コンシーラー</option>
														<option value="フェイスパウダー">フェイスパウダー</option>
													</optgroup>
												</select></td>
										</tr>
										<tr>
											<th>カラーカテゴリー</th>
											<td><select name="c_category" class="form-control col-6">
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
								<div class="button col-12 mt-5 mb-5">
									<input type="submit" name="sb" class="sb col-2" value="検索する">
								</div>
							</div>
						</form>
					</div>

					<div class="col-11 mt-5 mb-5 table result">
						<table id="example" class="result_table mb-5 ui celled table" style="width:100%">
							<thead>
								<tr>
									<th>JANコード</th>
									<th>メーカー</th>
									<th>ブランド</th>
									<th>商品名</th>
									<th>カラー</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="l">
									<tr>
										<td>${l.productId}</td>
										<td>${l.manufacturerName}</td>
										<td>${l.brandName}</td>
										<td>${l.productName}</td>
										<td>${l.colorNum}</td>
										<td>
											<form action="./ProductDetailServlet" method="get">
												<input type="hidden" name="product_code" value="${l.productId}">
												<input type="submit" class="detail" name="sb" value="詳細" />
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
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
		<!-- Select2 -->
		<script src="./js/select2.min.js"></script>
		<script src="./js/ja.js"></script>
		<!-- table -->
		<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
		<script src="https://cdn.datatables.net/1.10.20/js/dataTables.semanticui.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.1/semantic.min.js"></script>

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

			//table
			$(document).ready(function () {
				$('#example').DataTable({
					searching: false,
					language: {
						"decimal": ".",
						"thousands": ",",
						"sProcessing": "処理中...",
						"sLengthMenu": "_MENU_&ensp;件表示",
						"sZeroRecords": "データはありません。",
						"sInfo": " _TOTAL_ 件中 _START_ から _END_ まで表示",
						"sInfoEmpty": " 0 件中 0 から 0 まで表示",
						"sInfoFiltered": "（全 _MAX_ 件より抽出）",
						"sInfoPostFix": "",
						"sSearch": "検索&emsp;",
						"sUrl": "",
						"oPaginate": {
							"sFirst": "先頭",
							"sPrevious": "前",
							"sNext": "次",
							"sLast": "最終"
						}
					}
				});
			});
		</script>
</body>

</html>