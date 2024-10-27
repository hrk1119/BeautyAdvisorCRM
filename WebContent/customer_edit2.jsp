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

<!-- CSS -->
<link rel="stylesheet" href="css/sidebar.css">
<link rel="stylesheet" href="css/customer.css">

<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
	rel="stylesheet">
<title>顧客編集</title>

</head>


<body>
	<div class="container-fluid customer_regist">
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
			<div class="col-md-10 mb-5">
				<div class="row">
					<div class="col-10 mt-5 box">


						<div class="col-12 mt-3 mb-3 title">
							<h1>顧客編集</h1>
						</div>
						<c:forEach items="${cList}" var="c">
							<form action="./CustomerEditServlet" method="get" id="form">
								<table class="table search">
									<tr>
										<th class="start">名前</th>
										<td class="start"><input type="text"
											name="customer_name1"
											class="col-8 form-control validate[required]"
											value="${c.customerName1}"></td>
									</tr>
									<tr>
										<th>フリガナ</th>
										<td><input type="text" name="customer_name2"
											class="col-8 form-control validate[required]"
											value="${c.customerName2}"></td>
									</tr>
									<tr>
										<th>会員番号</th>
										<td><input type="text" name="customer_code"
											class="col-8 form-control validate[required,custom[onlyLetterNumber]]"
											value="${c.customerCode}"></td>
									</tr>
									<tr>
										<th>生年月日</th>
										<td><input type="date" name="birthday"
											class="col-8 form-control validate[custom[date],past[NOW]]"
											value="${c.outBirthday}"></td>

									</tr>
									<tr>
										<th>性別</th>
										<td>
											<div class="radio">
												<label class="p"><input type="radio" name="gender"
													value="男性">男性</label> <label class="p"><input
													type="radio" name="gender" value="女性" class="ml-3" checked>女性</label>
											</div>
										</td>
									</tr>
									<tr>
										<th>電話番号</th>
										<td><input type="text" name="tel"
											class="col-8 form-control validate[custom[phone]]"
											value="${c.tel}"></td>
									</tr>
									<tr>
										<th>メールアドレス</th>
										<td><input type="text" name="email"
											class="col-8 form-control validate[custom[email]]"
											value="${c.mail}"></td>
									</tr>
									<tr>
										<th>郵便番号</th>
										<td><input type="text" name="zip"
											onKeyUp="AjaxZip3.zip2addr(this,'','address','address');"
											class="col-8 form-control validate[required]"
											value="${c.postalcode}"></td>
									</tr>
									<tr>
										<th class="control-th">住所</th>
										<td><input type="text" name="address"
											class="col-8 form-control" value="${c.address}">
										</td>
									</tr>
									<tr>
										<th>職業</th>
										<td><select name="work" class="form-control col-5">
												<option value="">---選択してください---</option>
												<option value="会社員">会社員</option>
												<option value="専業主婦">専業主婦</option>
												<option value="パート・アルバイト">パート・アルバイト</option>
												<option value="学生">学生</option>
												<option value="無職">無職</option>
												<option value="その他">その他</option>
										</select></td>
									</tr>
								</table>
								<div class="text-center">
									<input type="submit" name="sb" class="btn_pink mt-3 mb-5"
										value="変更する">
								</div>
							</form>
						</c:forEach>
					</div>

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
	<script src="js/ajaxzip3.js"></script>

	<script type="text/javascript">
		/**入力チェック**/
		jQuery(document).ready(function() {
			jQuery("#form").validationEngine({ //formのidと合わせる
				promptPosition : "topRight"
			});
		});
	</script>

</body>

</html>