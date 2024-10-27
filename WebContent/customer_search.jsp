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

	<!-- table css-->
	<link href="./css/semantic.min.css" rel="stylesheet">
	<link href="./css/dataTables.semanticui.min.css" rel="stylesheet">

	<!-- CSS -->
	<link rel="stylesheet" href="css/sidebar.css">
	<link rel="stylesheet" href="css/customer.css">

	<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
	<title>顧客検索</title>
</head>


<body>
	<div class="container-fluid customer_search">
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
			<div class="col-md-2"></div>






			<!-- メイン -->
			<div class="col-md-10">
				<div class="row">
					<div class="col-10 mt-5 mb-5 box">

						<div class="col-12 mt-3 mb-3 title">
							<h1>顧客検索</h1>
						</div>

						<ul class="nav nav-tabs">
							<li class="nav-item"><a href="#one" class="nav-link active" data-toggle="tab">顧客番号から調べる</a>
							</li>
							<li class="nav-item"><a href="#two" class="nav-link" data-toggle="tab">基本の情報から調べる</a></li>
						</ul>
						<form action="./CustomerSearchServlet" method="get" id="form">
							<div class="tab-content">
								<div id="one" class="tab-pane active">
									<div class="mx-auto mt-4 col-7">
										会員番号を入力してください
										<input type="text" name="customer_code"
											class="form-control col-12 validate[custom[onlyLetterNumber]]" placeholder="12345678">
									</div>
								</div>
								<div id="two" class="tab-pane">
									<table class="table search col-11 mx-auto">
										<tr>
											<th class="start">名前</th>
											<td class="start"><input type="text" name="customer_name1" class="form-control col-9"
													placeholder="山田花子"></td>
										</tr>
										<tr>
											<th>フリガナ</th>
											<td><input type="text" name="customer_name2" class="form-control col-9" placeholder="ヤマダハナコ"></td>
										</tr>
										<!-- <tr>
									<th>生年月日</th>
									<td>
										<div class="row col-6">
											<input type="date" name="birthday" class="form-control col-8">
										</div>
									</td>
								</tr> -->
										<tr>
											<th>性別</th>
											<td>
												<div class="radio">
													<label class="p"><input type="radio" name="gender" value="men">男性</label>
													<label class="p"><input type="radio" name="gender" value="women" class="ml-3">女性</label>
												</div>
											</td>
										</tr>
										<tr>
											<th>職業</th>
											<td><select name="work" class="form-control col-9">
													<option value="">選択してください</option>
													<option value="会社員">会社員</option>
													<option value="専業主婦">専業主婦</option>
													<option value="パート・アルバイト">パート・アルバイト</option>
													<option value="学生">学生</option>
													<option value="無職">無職</option>
													<option value="その他">その他</option>
												</select></td>
										</tr>
										<tr>
											<th>パーソナルカラー</th>
											<td>
												<div class="radio">
													<label class="p"><input type="radio" name="color" value="yellow">イエローベース</label> <label
														class="p"><input type="radio" name="color" value="blue" class="ml-3">ブルーベース</label>
												</div>
											</td>
										</tr>
										<tr>
											<th>購入履歴</th>
											<td><input type="text" name="buy_code"
													class="form-control col-9 validate[custom[onlyLetterNumber]]" placeholder="JANコードを入力してください">
											</td>
										</tr>
										<tr>
											<th>購入日</th>
											<td>
												<div class="row col-12">
													<input type="date" name="buy_date"
														class="form-control validate[custom[date],past[NOW]] col-6">
												</div>
											</td>
										</tr>
										<tr>
											<th>店舗名</th>
											<td><select name="store" class="form-control col-9">
													<option value="">選択してください</option>
													<option value="1">千本今出川店</option>
													<option value="2">西賀茂店</option>
												</select></td>
										</tr>
										<tr>
											<th>応対者</th>
											<td><select name="employee" class="form-control col-9">
													<option value="">選択してください</option>
													<option value="7777">中田詩音</option>
													<option value="8888">田中麻衣子</option>
													<option value="9999">矢野真梨</option>
												</select></td>
										</tr>
									</table>
								</div>
								<div class="button col-12 mt-5 mb-5">
									<input type="submit" name="sb" class="btn_pink py-2 px-4" value="検索する">
								</div>
							</div>
						</form>
					</div>

					<div class="col-10 mt-5 mb-5 table result">
						<table id="example" class="result_table mb-5 ui celled table" style="width:100%">
							<thead>
								<tr>
									<th>会員番号</th>
									<th>名前</th>
									<th>生年月日</th>
									<th>パーソナルカラー</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="l">
									<tr>
										<td>${l.customerCode}</td>
										<td>${l.customerName1}</td>
										<td>${l.outBirthday}</td>
										<td>${l.color}</td>
										<td>
											<form action="./CustomerDetailServlet" method="get">
												<input type="hidden" name="customer_code" value="${l.customerCode}"> <input type="submit"
													class="detail" name="sb" value="詳細" />
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