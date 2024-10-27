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
	<link href="css/bootstrap.min.css" rel="stylesheet">

	<!-- table css-->
	<link href="./css/semantic.min.css" rel="stylesheet">
	<link href="./css/dataTables.semanticui.min.css" rel="stylesheet">

	<link rel="stylesheet" href="css/sidebar.css">
	<link rel="stylesheet" href="css/recommend.css">

	<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
	<title>商品詳細</title>
</head>


<body>
	<div class="container-fluid customer product_detail">
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




			<!----- 値の受け取り ----->
			<c:forEach items="${pList}" var="p">
				<!-- メイン -->
				<div class="col-11 mx-auto mb-5">

					<div class="col-10 mt-3 box">
						<div class="row">
							<div class="col-4 mt-5">
								<c:choose>
									<c:when test="${p.groupPhoto == './img/products/'}">
										<img id="main" src="${p.photo }" width="250px" height="250px" alt="${p.productName }">
									</c:when>
									<c:when test="${p.groupPhoto != './img/products/'}">
										<img id="main" src="${p.groupPhoto }" width="250px" height="250px" alt="${p.productName }">
									</c:when>
								</c:choose>
								<p id="review_ave" class="mt-3 ml-2"></p>
								<p class="manufacturer mt-3 ml-2">${p.manufacturerName }</p>
								<p class="brand ml-2">${p.brandName }</p>
								<a class="url  ml-2" href="${p.url }" target="_blank">公式サイトへ</a>
							</div>

							<div class="col-8">
								<div class="row">
									<p class="name mt-5">${p.productName }</p>
									<c:choose>
										<c:when test="${p.pcName1=='イエローベース,ブルーベース'}">
											<div id="main_pc_color" class="col-6 row">
												<p class="color yellow col-4 ml-4 mt-5">イエベ</p>
												<p class="color blue col-4 ml-1 mt-5">ブルベ</p>
											</div>
										</c:when>
										<c:when test="${p.pcName1=='イエローベース'}">
											<div id="main_pc_color" class="col-6 row">
												<p class="color yellow col-4 ml-4 mt-5">イエベ</p>
											</div>
										</c:when>
										<c:when test="${p.pcName1 =='ブルーベース'}">
											<div id="main_pc_color" class="col-6 row">
												<p class="color blue col-4 ml-4 mt-5">ブルベ</p>
											</div>
										</c:when>
									</c:choose>
								</div>
								<div class="row">
									<p class="my-3 ml-3" id="main_color_name">${p.colorName }</p>
									<c:choose>
										<c:when test="${p.colorName != p.colorNum}">
											<p class="my-3 ml-3" id="main_color_name">${p.colorNum }</p>
										</c:when>
									</c:choose>
								</div>
								<div class="row">
									<p class="price mt-3 ml-3">&yen; ${p.outPrice }（税抜）</p>
									<p class="and mt-3 ml-3">/</p>
									<p class="category mt-3 ml-3">${p.categoryName }</p>
								</div>
								<div class="row">
									<p class="mt-3 ml-3">発売日：${p.outDate }</p>
									<p id="product_id" class="mt-3 ml-3" data-jan="${p.productId}">JANコード：${p.productId }</p>
								</div>
								<h3 class="mt-3 mr-2">商品説明</h3>
								<p class="comment mr-2">${p.memo }</p>

							</div>

						</div>

						<!--カラー詳細-->
						<div class="row color_detail ml-2 mr-2">
							<div class="col-12">
								<h2 class="mt-5 col-4">COLORS</h2>
							</div>

							<div class="col-12 mt-5">

								<ul class="color_ul">

									<!----- 値の受け取り ----->
									<c:forEach var="c" items="${cList}">
										<li class="col-4">
											<div class="color_content">
												<a href="#"><img src="${c.photo }" alt="${c.colorName }" width="150px" height="150px"
														class="change"></a>
												<p class="sub_color_name" data-jan="${c.productId}">${c.colorNum}${c.colorName }</p>
												<div class="row ml-3">

													<c:choose>
														<c:when test="${c.pcName1=='イエローベース'}">
															<div class="sub_pc_color col-12 row">
																<p class="color yellow col-5 mr-2 pc_one">イエベ</p>
															</div>
														</c:when>
														<c:when test="${c.pcName1 =='ブルーベース'}">
															<div class="sub_pc_color col-12 row">
																<p class="color blue col-5 pc_one">ブルベ</p>
															</div>
														</c:when>
														<c:when test="${c.pcName1 =='イエローベース,ブルーベース'}">
															<div class="sub_pc_color col-12 row">
																<p class="color yellow col-5 mr-2 pc_one">イエベ</p>
																<p class="color blue col-5">ブルベ</p>
															</div>
														</c:when>
													</c:choose>

												</div>
											</div>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>

						<table id="review" class="ui celled table" style="width:100%">
							<thead>
								<th>点数</th>
								<th>内容</th>
							</thead>
							<tbody id="review_content">
							</tbody>
						</table>

					</div>
			</c:forEach>

		</div>
	</div>
	<script src="./js/jquery-3.3.1.min.js"></script>
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
	<script src="./js/popper.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<!-- table -->
	<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.20/js/dataTables.semanticui.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.1/semantic.min.js"></script>


	<script>
		const JAN = document.querySelector('#product_id').dataset.jan;
		console.log(JAN);
		let APPID = 'dj00aiZpPW1KQ3gzYk1EUkZPdCZzPWNvbnN1bWVyc2VjcmV0Jng9ODY-'; //アプリID
		var array = ["point", "re"];
		var point_sum = 0;
		var point_ave = 0;

		function search_yahoo(page) {
			console.log(JAN);
			$.ajax({
				url: 'https://shopping.yahooapis.jp/ShoppingWebService/V1/json/reviewSearch?appid=' + APPID + '&jan=' + JAN + '&sort=-review_rate',
				type: 'post',
				dataType: 'jsonp',
			})

				.done(function (data) {

					for (let i = 0; i < data.ResultSet.Result.length; i++) {
						var point = data.ResultSet.Result[i].Ratings.Rate;
						var review = data.ResultSet.Result[i].Description;
						var sub_array = [point, review];
						array.push(sub_array);

						point_sum += parseFloat(point);
					}
					console.log(point_sum);
					point_ave = point_sum / array.length;
					point_ave = Math.round(point_ave * 10) / 10;
					console.log("平均→" + point_ave);
					document.querySelector('#review_ave').innerHTML = "口コミ評価：" + point_ave + "（" + array.length + "件）";
				})
				.fail(function (data) {
					//読み込み失敗時の処理
					console.log('読み込みエラー');
				});
		}


		search_yahoo();
		array.shift();
		array.shift();

		console.log(array);

		//table
		setTimeout(timerl, 1000);
		function timerl() {
			$(document).ready(function () {
				console.log(array);
				$('#review').DataTable({
					data: array,
					searching: false,
					lengthChange: false,
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
					},
					columnDefs: [
						{ targets: 0, width: 50 }
					]
				});
			});
		};

		//選択したものに変更
		const targets = document.getElementsByClassName('change');
		const subColor = document.getElementsByClassName('sub_color');
		const colorName = document.getElementsByClassName('sub_color_name');
		const pcColor = document.getElementsByClassName('sub_pc_color');
		const pcOne = document.getElementsByClassName('pc_one');

		//パーソナルカラー
		two = '<p class="color yellow col-4 ml-4 mt-5">イエベ</p>';
		two += '<p class="color blue col-4 ml-2 mt-5" >ブルベ</p>';
		b_one = '<p class="color blue col-4 ml-4 mt-5">ブルベ</p>';
		y_one = '<p class="color yellow col-4 ml-4 mt-5">イエベ</p>';

		//for分で要素数分ループ処理
		for (let i = 0; i < targets.length; i++) {
			targets[i].addEventListener('click', () => {
				document.querySelector('#main').src = targets[i].src;
				document.querySelector('#main_color_name').textContent = colorName[i].textContent;

				if (pcOne[i] != null) {
					if (pcColor[i].childElementCount == 2 && pcOne[i].innerHTML == 'イエベ') {
						document.querySelector("#main_pc_color").innerHTML = two;
					} else if (pcOne[i].innerHTML == 'ブルベ') {
						document.querySelector("#main_pc_color").innerHTML = b_one;
					} else {
						document.querySelector("#main_pc_color").innerHTML = y_one;
					}
				}

				//クリックされた商品のJANコード
				var JAN = colorName[i].dataset.jan;

				location.href = './ProductDetailServlet?product_code=' + JAN;

			}, false);
		}
	</script>
</body>

</html>