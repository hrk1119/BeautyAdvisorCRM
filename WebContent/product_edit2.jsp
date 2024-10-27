<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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


	<!-- CSS -->
	<link rel="stylesheet" href="css/sidebar.css">
	<link rel="stylesheet" href="css/product.css">

	<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
	<title>商品編集</title>
</head>

<body>
	<div class="container-fluid product_regist">
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
						</ul>
					</li>
					<li class="active"><a href="#product_Submenu" data-toggle="collapse" aria-expanded="false"
							class="dropdown-toggle product_icon">商品管理</a>
						<ul class="collapse list-unstyled" id="product_Submenu">
							<li><a href="#">商品登録</a></li>
							<li><a href="./ProductSearchServlet">商品検索</a></li>
						</ul>
					</li>
					<li><a class="mail_icon" href="./ProductSearchServlet?page=m">メール作成</a></li>
				</ul>
			</nav>
			<div class="col-md-2"></div>

			<!-- メイン -->
			<div class="col-md-10 mb-5">
				<div class="row">

					<div class="col-10 mt-5 box">
						<div class="col-12 mt-3 mb-3 title">
							<h1>商品編集</h1>
						</div>
						<c:forEach items="${pList}" var="p">
							<form action="./ProductEditServlet" method="get" id="form">
								<div class="col-11 mx-auto ">
									<table class="table search">
										<tr>
											<th class="start">名前</th>
											<td class="start"><input type="text" name="product_name"
													class="col-8 form-control validate[required]"
													value="${p.productName }"></td>
										</tr>
										<tr>
											<th>ブランド</th>
											<td><select name="brand" class="form-control col-6">
													<option value="">選択してください</option>
													<option value="1">マキアージュ</option>
													<option value="2">マジョリカマジョルカ</option>
													<option value="3">インテグレート グレイシィ</option>
													<option value="4">インテグレート</option>
													<option value="5">ケイト</option>
													<option value="6">コフレドール</option>
													<option value="7">メディア</option>
													<option value="8">ヴィセ</option>
													<option value="9">ヒロインメイク</option>
												</select></td>
										</tr>
										<tr>
											<th>価格(税抜)</th>
											<td><input type="text" name="price"
													class="col-8 form-control validate[custom[onlyLetterNumber]]"
													value="${p.outPrice }"></td>
										</tr>
										<tr>
											<th>発売日</th>
											<td><input type="date" name="date"
													class="col-8 form-control validate[custom[date]]"
													value="${p.outDate }"></td>

										</tr>
										<tr>
											<th>URL</th>
											<td><input type="url" name="url"
													class="col-8 form-control validate[custom[url]]" value="${p.url }">
											</td>
										</tr>
										<tr>
											<th>商品説明</th>
											<td><textarea class="form-control col-8" rows="5"
													name="memo">${p.memo }</textarea></td>
										</tr>
										<tr>
											<th>カテゴリ</th>
											<td><select name="category" class="form-control col-6">
													<option value="">選択してください</option>
													<option value="1">口紅・リップ</option>
													<option value="2">チーク</option>
													<option value="3">アイシャドウ</option>
													<option value="4">アイライナー</option>
													<option value="5">アイブロウ</option>
													<option value="6">マスカラ</option>
													<option value="7">リキッドファンデーション</option>
													<option value="8">パウダーファンデーション</option>
													<option value="9">クリーム・ジェルファンデーション</option>
													<option value="10">化粧下地</option>
													<option value="11">コンシーラー</option>
													<option value="12">フェイスパウダー</option>
												</select></td>
										</tr>
										<tr>
											<th class="start">JANコード</th>
											<td class="start"><input type="text" name="product_code0"
													class="col-8 form-control validate[required]"
													value="${p.productId }">
											</td>
										</tr>
										<tr>
											<th>カラー番号</th>
											<td><input type="text" name="color_num0"
													class="col-8 form-control validate[required]"
													value="${p.colorNum }">
											</td>
										</tr>
										<tr>
											<th>カラー名</th>
											<td><input type="text" name="color_name0"
													class="col-8 form-control validate[required]"
													value="${p.colorName }">
											</td>
										</tr>
										<tr>
											<th>写真</th>
											<td><input type="file" name="photo0" class="col-8">
											</td>
										</tr>
										<tr>
											<th>カラーカテゴリ</th>
											<td><select name="color_category0" class="form-control col-6">
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
												<p>※ポイントメイクのみ</p>
											</td>
										</tr>
										<tr>
											<th>パーソナルカラー</th>
											<td>
												<div class="checkbox">
													<label class="p"><input type="checkbox" name="pc_color0"
															value="1">イエローベース</label> <label class="p"><input
															type="checkbox" name="pc_color0" value="2"
															class="ml-3">ブルーベース</label> <label class="p"><input
															type="checkbox" name="pc_color0" value="3"
															class="ml-3">不明</label>
												</div>
											</td>
										</tr>
									</table>
								</div>
								<div class="text-center">
									<input type="submit" name="sb" class="btn_pink mt-3 mb-5 px-3" value="変更する">
								</div>
							</form>
						</c:forEach>
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
	<!-- 住所 -->
	<script src="./js/ajaxzip3.js"></script>

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