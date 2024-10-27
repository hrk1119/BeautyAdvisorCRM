<%@page import="bean.Sale"%>
<%@page import="bean.Support"%>
<%@page import="java.util.List"%>
<%@page import="bean.Customer"%>
<%@page import="java.util.ArrayList"%>
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

	<!-- table css-->
	<link href="./css/semantic.min.css" rel="stylesheet">
	<link href="./css/dataTables.semanticui.min.css" rel="stylesheet">

	<!-- CSS -->
	<link rel="stylesheet" href="css/sidebar.css">
	<link rel="stylesheet" href="css/customer.css">

	<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
	<title>顧客詳細</title>

</head>


<body>
	<div class="container-fluid customer_detail">
		<div class="row mt-5">

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
					<li class="active"><a href="#customer_Submenu" data-toggle="collapse" aria-expanded="false"
							class="dropdown-toggle customer_icon">顧客管理</a>
						<ul class="collapse list-unstyled" id="customer_Submenu">
							<li><a href="customer_regist.jsp">顧客登録</a></li>
							<li><a href="./CustomerSearchServlet">顧客検索</a></li>
							<li><a href="./customer_edit1.jsp">顧客編集</a></li>
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
			<div class="none"></div>

			<!-- メイン -->
			<div class="col-md-10 mb-5 mx-auto main">

				<!-- タブ -->
				<ul class="nav nav-tabs">
					<li class="nav-item"><a href="#one" class="nav-link active" data-toggle="tab">基本情報</a></li>
					<li class="nav-item"><a href="#two" class="nav-link" data-toggle="tab">購入履歴</a></li>
					<li class="nav-item"><a href="#three" class="nav-link" data-toggle="tab">対応履歴</a></li>
				</ul>

				<!-- 内容 -->
				<!----- 値の受け取り ----->
				<c:forEach items="${cList}" var="c">
					<div class="tab-content">

						<!-- 1つ目 -->
						<div id="one" class="tab-pane active pt-3 pb-5">
							<div class="col-11 tab_name">
								<div class="row">
									<p class="name2 ml-3">${c.customerName2}</p>
								</div>
								<div class="row">
									<p class="col-8">${c.customerName1}様</p>
								</div>
							</div>
							<div class="tab1_data mt-4 ml-3">
								<div class="row">
									<div class="col-10 ml-5">
										<table class="table tb">
											<tr>
												<th class="start">会員番号</th>
												<td class="start" id="c_code">${c.customerCode}</td>
											</tr>
											<tr>
												<th>生年月日</th>
												<td>${c.outBirthday}</td>
											</tr>
											<tr>
												<th>性別</th>
												<td>${c.gender}</td>
											</tr>
											<tr>
												<th>電話番号</th>
												<td>${c.tel}</td>
											</tr>
											<tr>
												<th>メールアドレス</th>
												<td>${c.mail}</td>
											</tr>
											<tr>
												<th>郵便番号</th>
												<td>${c.postalcode}</td>
											</tr>
											<tr>
												<th>住所</th>
												<td>${c.address}</td>
											</tr>
											<tr>
												<th>職業</th>
												<td>${c.work}</td>
											</tr>
											<tr>
												<th>パーソナルカラー</th>
												<td>${c.color}</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>

						<!-- 2つ目 -->
						<div id="two" class="tab-pane pt-3 pb-3">
							<div class="col-11 tab_name">
								<div class="row">
									<p class="name2 ml-3">${c.customerName2}</p>
								</div>
								<div class="row">
									<p class="col-8">${c.customerName1}様</p>
									<button type="button" class="px-2 py-2 mb-4 btn_pink" data-toggle="modal" data-target="#sale_regist">
										購入履歴を登録する</button>
								</div>
							</div>

							<div class="tab2_data mt-4 col-11 mx-auto">
								<div class="table">
									<table id="sale_table" class="table_result ui celled table text-center" style="width:100%">
										<thead>
											<tr>
												<th>日付</th>
												<th>店舗名</th>
												<th>ブランド</th>
												<th>商品名</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<!----- 値の受け取り ----->
											<c:forEach items="${saList}" var="sa">
												<tr>
													<td>${sa.outSaleDate}</td>
													<td>${sa.storeName}</td>
													<td>${sa.brandName}</td>
													<td class="name">${sa.productName}</td>
													<td>
														<button type="button" class="detail sale_detail_btn" data-toggle="modal"
															data-target="#sale_detail_modal" data-saleid="${sa.saleId}"
															data-jan="${sa.productId}">詳細</button>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>

						<!-- 3つ目 -->
						<div id="three" class="tab-pane pt-3 pb-3">
							<div class="col-11 tab_name">
								<div>
									<p class="name2">${c.customerName2}</p>
								</div>
								<div class="row">
									<p class="col-8">${c.customerName1}様</p>
									<button type="button" class="px-2 py-2 mb-4 btn_pink" data-toggle="modal"
										data-target="#support_regist">
										対応履歴を登録する</button>
								</div>
							</div>

							<div class="tab3_data mt-4 col-11 mx-auto">
								<div class="table">
									<table id="supp_table" class="table_result ui celled table text-center" style="width:100%">
										<thead>
											<tr>
												<th>日付</th>
												<th>店舗名</th>
												<th>対応者</th>
												<th>対応メモ</th>
												<th></th>
											</tr>
										</thead>
										<tbody>

											<!----- 値の受け取り ----->
											<c:forEach items="${suList}" var="su">
												<tr>
													<td>${su.outDate}</td>
													<td>${su.storeName }</td>
													<td>${su.employeeName }</td>
													<td class="memo">${su.memo }</td>
													<td>
														<button type="button" id="supp_btn" class="detail supp_detail_btn" data-toggle="modal"
															data-target="#su_detail_modal" data-supportid="${su.supportId}">詳細</button>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>

						<!-- sale詳細モーダル -->
						<div class="modal fade" id="sale_detail_modal" tabindex="-1" role="dialog"
							aria-labelledby="exampleModalLongTitle" aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<!-- ヘッダー -->
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLongTitle">
											購入履歴の詳細</h5>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body" id="modal2">
										<!-- タブ -->
										<div class="tabs">
											<div class="tab-buttons">
												<span class="content1">詳細</span> <span class="content2" id="sale_content2_tab">編集</span>
												<div id="sale_lamp" class="content1"></div>
											</div>
											<!-- 内容 -->
											<div class="tab-content2 col-12">

												<div class="content1" id="sale_content1_main">
													<div class="row">
														<div class="col-4">
															<img src="" alt="${sa.productName}" width="150px" height="150px" id="sale_detail_img">
														</div>
														<div class="col-8 mt-3">
															<p id="sale_detail_Date"></p>
															<p id="sale_detail_store"></p>
															<p id="sale_detail_saleNum"></p>
														</div>
													</div>
													<div class="col-8 mt-4 mx-auto p-2 product">
														<p id="sale_detail_manufacturer"></p>
														<p id="sale_detail_brand"></p>
														<p id="sale_detail_productName"></p>
														<p id="sale_detail_colorName"></p>
														<p id="sale_detail_price"></p>
														<p id="sale_detail_productId"></p>
													</div>
												</div>
												<div class="content2" id="sale_content2_main">
													<form action="./SaleEditServlet" method="get" class="form">
														<div class="form-group col-8">
															<label>日付</label> <input type="date" name="date" id="sale_edit_date"
																class="form-control validate[required,custom[date],past[NOW]]" value="">
														</div>
														<div class="form-group col-8">
															<label>JANコード</label> <input type="text" name="product_id" id="sale_edit_jan"
																class="none form-control validate[required,custom[onlyLetterNumber]]" value="">
														</div>
														<div class="form-group col-8 mb-5">
															<label>個数</label> <input type="text" name="num" id="sale_edit_num"
																class="none form-control validate[required,custom[onlyLetterNumber]]" value="">
														</div>

														<input type="hidden" name="b_id" id="sale_edit_b" value="">
														<input type="hidden" name="id" id="sale_edit_id" value="">

														<button class="btn btn-secondary">削除する</button>
														<input type="submit" class="btn btn-danger btn_right" value="変更する" name="sb">
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- /詳細モーダル -->

						<!--supp詳細モーダル -->
						<div class="modal fade" id="supp_detail_modal" tabindex="-1" role="dialog"
							aria-labelledby="exampleModalLongTitle" aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<!-- ヘッダー -->
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLongTitle">
											対応履歴の詳細</h5>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<!-- タブ -->
										<div class="tabs">
											<div class="tab-buttons">
												<span class="content1" id="supp_content1_tab">詳細</span> <span class="content2"
													id="supp_content2_tab">編集</span>
												<div id="supp_lamp" class="content1"></div>
											</div>
											<!-- 内容 -->
											<div class="tab-content2 col-12">

												<div class="content1" id="supp_content1_main">
													<dl class="mb-3">
														<dt>日付</dt>
														<dd id="supp_detail_Date"></dd>
														<dt>店舗</dt>
														<dd id="supp_detail_store"></dd>
														<dt>従業員</dt>
														<dd id="supp_detail_employee"></dd>
														<dt>対応メモ</dt>
														<dd id="supp_detail_memo"></dd>
														<dt>購入商品</dt>
														<dd id="supp_detail_product">なし</dd>
													</dl>
												</div>
												<div class="content2" id="supp_content2_main">
													<form action="./SupportEditServlet" method="get" class="form">
														<div class="form-group col-10">
															<label>日付</label> <input type="date" name="date" id="supp_edit_date"
																class="form-control validate[required,custom[date],past[NOW]]" value="">
														</div>
														<div class="form-group col-10">
															<label>対応メモ</label>
															<textarea class="form-control validate[required]" rows="4" name="memo"
																id="supp_edit_memo"></textarea>
														</div>

														<input type="hidden" name="id" id="supp_edit_id" value="">

														<button class="btn btn-secondary">削除する</button>
														<input type="submit" class="btn btn-danger btn_right" value="変更する" name="sb">
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- 購入履歴の登録　モーダル -->
						<div class="modal fade" id="sale_regist" role="dialog" aria-labelledby="exampleModalScrollableTitle"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog-scrollable modal-lg" role="document">
								<div class="modal-content">
									<!-- 内容 -->
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalScrollableTitle">購入履歴を登録する</h5>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<form action="./SaleRegistServlet" method="get" class="form">
										<div class="modal-body col-12">
											<table class="table search">
												<tr>
													<th class="start">日付</th>
													<td class="start">
														<div class="row col-10">
															<input type="date" name="date"
																class="form-control validate[required,custom[date],past[NOW]]">
														</div>
													</td>
												</tr>
												<tr>
													<th>商品</th>
													<td id="product_codes">
														<div class="row">
															<input type="text" name="product_code0"
																class="form-control col-7 ml-3 product_code validate[required,custom[onlyLetterNumber]]"
																placeholder="JANコードを入力してください"> <input type="text" name="num0"
																class="form-control col-2 ml-4 validate[required,custom[onlyLetterNumber]]"
																placeholder="個数">
														</div>
														<div class="row mt-3">
															<input type="text" name="product_code1"
																class="form-control col-7 ml-3 product_code validate[custom[onlyLetterNumber]]"
																placeholder="JANコードを入力してください"> <input type="text" name="num1"
																class="form-control col-2 ml-4 validate[custom[onlyLetterNumber]]" placeholder="個数">
														</div>
														<div class="row mt-3">
															<input type="text" name="product_code2"
																class="form-control col-7 ml-3 product_code validate[custom[onlyLetterNumber]]"
																placeholder="JANコードを入力してください"> <input type="text" name="num2"
																class="form-control col-2 ml-4 validate[custom[onlyLetterNumber]]" placeholder="個数">
														</div>
														<div class="row mt-3">
															<input type="text" name="product_code3"
																class="form-control col-7 ml-3 product_code validate[custom[onlyLetterNumber]]"
																placeholder="JANコードを入力してください"> <input type="text" name="num3"
																class="form-control col-2 ml-4 validate[custom[onlyLetterNumber]]" placeholder="個数">
														</div>
														<div class="row mt-3">
															<input type="text" name="product_code4"
																class="form-control col-7 ml-3 product_code validate[custom[onlyLetterNumber]]"
																placeholder="JANコードを入力してください"> <input type="text" name="num4"
																class="form-control col-2 ml-4 validate[custom[onlyLetterNumber]]" placeholder="個数">
														</div>

													</td>
												</tr>
												<tr>
													<th class="start"></th>
													<td class="start"><button class="btn btn-danger" id="btn-add">追加</button></td>
												</tr>
											</table>
										</div>
										<input type="hidden" name="customer_code" value="${c.customerCode}">
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary" data-dismiss="modal">キャンセル</button>
											<input type="submit" class="btn btn-danger col-2" name="sb" value="登録"></input>
										</div>
									</form>
								</div>
							</div>
						</div>
						<!-- /購入履歴の登録　モーダル -->

						<!-- 対応履歴の登録　モーダル -->
						<div class="modal fade" id="support_regist" role="dialog" aria-labelledby="exampleModalScrollableTitle"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog-scrollable modal-lg" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalScrollableTitle">対応履歴を登録する</h5>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<form action="./SupportRegistServlet" method="get" class="form">
										<div class="modal-body col-12">
											<table class="table">
												<tr>
													<th class="start">日付</th>
													<td class="start">
														<div class="row col-10">
															<input type="date" name="date"
																class="form-control validate[required,custom[date],past[NOW]]">
														</div>
													</td>
												</tr>
												<th>対応メモ</th>
												<td><textarea class="form-control validate[required]" rows="5" name="memo"></textarea></td>
												</tr>
											</table>
										</div>
										<input type="hidden" name="customer_code" value="${c.customerCode}">
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary" data-dismiss="modal">キャンセル</button>
											<input type="submit" class="btn btn-danger col-2" name="sb" value="登録"></input>
										</div>
									</form>
								</div>
							</div>
						</div>
						<!-- /対応履歴の登録　モーダル -->

						<!-- 購入履歴の削除　モーダル -->
						<div class="modal fade" id="sale_delete" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Modal
											title</h5>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">...</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
										<button type="button" class="btn btn-primary">Save
											changes</button>
									</div>
								</div>
							</div>
						</div>

					</div>
				</c:forEach>
			</div>
		</div>
	</div>

	<script src="./js/jquery-3.3.1.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
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

	<!-- js -->
	<script src="./js/customer_detail.js"></script>

	<script>
		/**セレクトボックスの検索**/
		$(function () {
			$('.select').select2({
				language: "ja" //日本語化
			});
		})

		/**入力チェック**/
		jQuery(document).ready(function () {
			jQuery(".form").validationEngine({ //formのidと合わせる
				promptPosition: "topRight"
			});
		});

		//購入詳細モーダル
		const store = "${ sessionScope.s_name }";
		const saleDetailBtns = document.querySelectorAll('.sale_detail_btn');
		saleDetailBtns.forEach((saleDetail) => {
			saleDetail.addEventListener('click', () => {
				saleDetail.dataset.target = "#sale_detail_modal";

				document.querySelector('#sale_content1_main').style.display = "block";
				document.querySelector('#sale_content2_main').style.display = "none";
				document.querySelector('#sale_lamp').classList.remove("content2");
				document.querySelector('#sale_lamp').classList.add("content1");

				//ボタンのタ－ゲットを取得
				const saleId = saleDetail.dataset.saleid;
				const jan = saleDetail.dataset.jan;

				var request = {
					"saleid": saleId,
					"jan": jan
				};

				const data = {                                  //Servletに送信するデータ
					method: 'POST',
					headers: {
						'Content-Type': "text/plain"
					},
					body: JSON.stringify(request)            //このbodyが変数dataの内容
				};

				fetch('./SaleSearchServlet', data)                   // ./FetchServletに接続　【第一引数にURL,第二引数に渡すデータ】
					.then((response) => response.json())        //responseをjsonで受け取る
					.then((json) => {
						console.log(json);

						//詳細
						document.querySelector('#sale_detail_img').src = json[0].colorPhoto;
						document.querySelector('#sale_detail_manufacturer').innerHTML = json[0].manufacturerName;
						document.querySelector('#sale_detail_brand').innerHTML = json[0].brandName;
						document.querySelector('#sale_detail_productName').innerHTML = json[0].productName;
						document.querySelector('#sale_detail_colorName').innerHTML = json[0].colorName, json[0].colorNum;
						document.querySelector('#sale_detail_price').innerHTML = json[0].outPrice + "円（税抜）";
						document.querySelector('#sale_detail_Date').innerHTML = "購入日：" + json[0].outSaleDate;
						document.querySelector('#sale_detail_store').innerHTML = "店舗：" + json[0].storeName;
						document.querySelector('#sale_detail_productId').innerHTML = json[0].productId;
						document.querySelector('#sale_detail_saleNum').innerHTML = "個数：" + json[0].saleNum;

						//編集
						const sale_store = json[0].storeName;
						if (store == sale_store) {
							console.log("一緒");
							document.querySelector('#sale_edit_date').Value = json[0].saleDate;
							document.querySelector('#sale_edit_jan').value = json[0].productId;
							document.querySelector('#sale_edit_num').value = json[0].saleNum;
							document.querySelector('#sale_edit_b').value = json[0].productId;
							document.querySelector('#sale_edit_id').value = json[0].saleId;

							//display
							document.querySelector('#sale_content2_tab').style.display = "inline-block";
							document.querySelector('#sale_content2_main').style.display = "none";
							document.querySelector('#sale_lamp').style.display = "block";

						} else {
							console.log("違う");

							//display
							document.querySelector('#sale_content2_tab').style.display = "none";
							document.querySelector('#sale_content2_main').style.display = "none";
							document.querySelector('#sale_lamp').classList.remove("content2");
							document.querySelector('#sale_lamp').classList.add("content1");


						}
					});
			});
		});

		//対応詳細モーダル
		const employee = "${ sessionScope.e_name }";
		const suppDetailBtns = document.querySelectorAll('.supp_detail_btn');
		suppDetailBtns.forEach((suppDetail) => {
			suppDetail.addEventListener('click', () => {
				suppDetail.dataset.target = "#supp_detail_modal";

				document.querySelector('#supp_content1_main').style.display = "block";
				document.querySelector('#supp_content2_main').style.display = "none";
				document.querySelector('#supp_lamp').classList.remove("content2");
				document.querySelector('#supp_lamp').classList.add("content1");

				//ボタンのタ－ゲットを取得
				const suppId = suppDetail.dataset.supportid;
				console.log(suppId);

				const data = {                                  //Servletに送信するデータ
					method: 'POST',
					headers: {
						'Content-Type': "text/plain"
					},
					body: suppId
				};

				fetch('./SupportSearchServlet', data)                   // ./FetchServletに接続　【第一引数にURL,第二引数に渡すデータ】
					.then((response) => response.json())        //responseをjsonで受け取る
					.then((json) => {
						console.log(json);

						//詳細の表示
						document.querySelector('#supp_detail_Date').innerHTML = json[0].outDate;
						document.querySelector('#supp_detail_store').innerHTML = json[0].storeName;
						document.querySelector('#supp_detail_employee').innerHTML = json[0].employeeName;
						document.querySelector('#supp_detail_memo').innerHTML = json[0].memo;

						//購入商品
						const c_code = document.querySelector('#c_code').textContent;
						console.log(c_code);
						console.log(json[0].storeId);

						var request = {
							"c_code": c_code,
							"date": json[0].outDate,
							"store": json[0].storeId
						};

						console.log(request);

						const data = {                                  //Servletに送信するデータ
							method: 'POST',
							headers: {
								'Content-Type': "text/plain"
							},
							body: JSON.stringify(request)            //このbodyが変数dataの内容
						};

						fetch('./SupportProductServlet', data)                   // ./FetchServletに接続　【第一引数にURL,第二引数に渡すデータ】
							.then((response) => response.json())        //responseをjsonで受け取る
							.then((json) => {
								console.log(json);
								console.log(json.length);

								//購入商品の表示
								if (json.length != 0) {
									document.querySelector('#supp_detail_product').innerHTML = '【' + json[0].productId + '】' + json[0].productName;
									for (var i = 1; i < json.length; i++) {
										document.querySelector('#supp_detail_product').insertAdjacentHTML('afterbegin', '<dd>【' + json[i].productId + '】' + json[i].productName + '<dd>');
									}
								} else {
									document.querySelector('#supp_detail_product').innerHTML = 'なし';
								}
							});



						//編集の表示
						const supp_employee = json[0].employeeName;
						console.log(employee);
						console.log(supp_employee);
						if (employee == supp_employee) {
							console.log("一緒");

							const date = json[0].outDate;
							var result_date = date.replace('/', '-');

							for (var i = 0; i < 2; i++) {
								result_date = result_date.replace('/', '-');
							}

							console.log(result_date);


							document.querySelector('#supp_edit_date').Value = result_date;
							document.querySelector('#supp_edit_id').Value = json[0].supportId;
							document.querySelector('#supp_edit_memo').innerHTML = json[0].memo;

							//display
							document.querySelector('#supp_content2_tab').style.display = "inline-block";
							document.querySelector('#supp_content2_main').style.display = "none";
							document.querySelector('#supp_lamp').style.display = "block";

						} else {
							console.log("違う");

							//display
							document.querySelector('#supp_content2_tab').style.display = "none";
							document.querySelector('#supp_content2_main').style.display = "none";
							document.querySelector('#supp_lamp').classList.remove("content2");
							document.querySelector('#supp_lamp').classList.add("content1");


						}
					});
			});
		});

		//table(sale)
		$(document).ready(function () {
			$('#sale_table').DataTable({
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

		//table(supp)
		$(document).ready(function () {
			$('#supp_table').DataTable({
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