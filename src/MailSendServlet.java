
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Product;
import dao.CustomerDAO;
import dao.productDAO;

/**
 * Servlet implementation class MailSendServlet
 */
@WebServlet("/MailSendServlet")
public class MailSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MailSendServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字化け対策
		request.setCharacterEncoding("utf-8");

		//セッション
		HttpSession session = request.getSession(true);
		String p_code = (String) session.getAttribute("p_code");

		//htmlから値を受け取る
		ArrayList<String> checks = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			if (!(request.getParameter("check" + i) == null
					|| request.getParameter("check" + i).equals(""))) {
				checks.add(request.getParameter("check" + i));
			}
		}

		//宣言
		RequestDispatcher disp;
		String page = "";
		ArrayList<String> mails = new ArrayList<String>();

		/**メールアドレスを取り出す**/
		//DAO
		try {
			CustomerDAO dao = new CustomerDAO();
			mails = dao.mail(checks);

			for (String s : mails) {
				System.out.println(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**商品情報を取り出す**/
		List<Product> product = new ArrayList<>();
		//DAO
		try {
			productDAO dao = new productDAO();
			product = dao.Search(p_code);

			for (Product p : product) {
				System.out.println(p.getProductName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**メール送信**/
		//InternetAddress型の配列を作成
		InternetAddress[] address = new InternetAddress[mails.size()];

		try {
			//メールの型に変換
			for (int i = 0; i < address.length; i++) {
				address[i] = new InternetAddress(mails.get(i));
			}

			Properties property = new Properties();
			property.put("mail.smtp.host", "smtp.gmail.com");

			// GmailのSMTPを使うときの設定
			property.put("mail.smtp.auth", "true");
			property.put("mail.smtp.starttls.enable", "true");
			property.put("mail.smtp.host", "smtp.gmail.com");
			property.put("mail.smtp.port", "587");
			property.put("mail.smtp.debug", "true");

			Session mailSession = Session.getInstance(property, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					// 利用するSMTPサーバのメールアドレスとパスワードを設定(自分のgmailのアカウントとパスワード)
					return new PasswordAuthentication("sgmrhrk19@gmail.com", "haruka19");
				}
			});

			// メール本文を作るためのインスタンス
			MimeMessage msg = new MimeMessage(mailSession);

			//題名
			msg.setSubject("商品のご案内", "shift-jis");

			//受信者
			msg.setRecipients(Message.RecipientType.TO, address);

			//送信者
			InternetAddress fromAddress = new InternetAddress("sgmrhrk19@gmail.com", "haruka");

			msg.setFrom(fromAddress);

			// mixed
			Multipart mixedPart = new MimeMultipart("mixed");

			// html mail
			String text = "";
			for (Product p : product) {
				text = "<!DOCTYPE html\r\n" +
						"	PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
						+
						"\r\n" +
						"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"\r\n"
						+
						"	xmlns:v=\"urn:schemas-microsoft-com:vml\">\r\n" +
						"\r\n" +
						"<head>\r\n" +
						"	<!--[if gte mso 9]><xml><o:OfficeDocumentSettings><o:AllowPNG/><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml><![endif]-->\r\n"
						+
						"	<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\r\n" +
						"	<meta content=\"width=device-width\" name=\"viewport\" />\r\n" +
						"	<!--[if !mso]><!-->\r\n" +
						"	<meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\" />\r\n" +
						"	<!--<![endif]-->\r\n" +
						"	<title></title>\r\n" +
						"	<!--[if !mso]><!-->\r\n" +
						"	<!--<![endif]-->\r\n" +
						"	<style type=\"text/css\">\r\n" +
						"		body {\r\n" +
						"			margin: 0;\r\n" +
						"			padding: 0;\r\n" +
						"		}\r\n" +
						"\r\n" +
						"		table,\r\n" +
						"		td,\r\n" +
						"		tr {\r\n" +
						"			vertical-align: top;\r\n" +
						"			border-collapse: collapse;\r\n" +
						"		}\r\n" +
						"\r\n" +
						"		* {\r\n" +
						"			line-height: inherit;\r\n" +
						"		}\r\n" +
						"\r\n" +
						"		a[x-apple-data-detectors=true] {\r\n" +
						"			color: inherit !important;\r\n" +
						"			text-decoration: none !important;\r\n" +
						"		}\r\n" +
						"	</style>\r\n" +
						"	<style id=\"media-query\" type=\"text/css\">\r\n" +
						"		@media (max-width: 685px) {\r\n" +
						"\r\n" +
						"			.block-grid,\r\n" +
						"			.col {\r\n" +
						"				min-width: 320px !important;\r\n" +
						"				max-width: 100% !important;\r\n" +
						"				display: block !important;\r\n" +
						"			}\r\n" +
						"\r\n" +
						"			.block-grid {\r\n" +
						"				width: 100% !important;\r\n" +
						"			}\r\n" +
						"\r\n" +
						"			.col {\r\n" +
						"				width: 100% !important;\r\n" +
						"			}\r\n" +
						"\r\n" +
						"			.col>div {\r\n" +
						"				margin: 0 auto;\r\n" +
						"			}\r\n" +
						"\r\n" +
						"			img.fullwidth,\r\n" +
						"			img.fullwidthOnMobile {\r\n" +
						"				max-width: 100% !important;\r\n" +
						"			}\r\n" +
						"\r\n" +
						"			.no-stack .col {\r\n" +
						"				min-width: 0 !important;\r\n" +
						"				display: table-cell !important;\r\n" +
						"			}\r\n" +
						"\r\n" +
						"			.no-stack.two-up .col {\r\n" +
						"				width: 50% !important;\r\n" +
						"			}\r\n" +
						"\r\n" +
						"			.no-stack .col.num4 {\r\n" +
						"				width: 33% !important;\r\n" +
						"			}\r\n" +
						"\r\n" +
						"			.no-stack .col.num8 {\r\n" +
						"				width: 66% !important;\r\n" +
						"			}\r\n" +
						"\r\n" +
						"			.no-stack .col.num4 {\r\n" +
						"				width: 33% !important;\r\n" +
						"			}\r\n" +
						"\r\n" +
						"			.no-stack .col.num3 {\r\n" +
						"				width: 25% !important;\r\n" +
						"			}\r\n" +
						"\r\n" +
						"			.no-stack .col.num6 {\r\n" +
						"				width: 50% !important;\r\n" +
						"			}\r\n" +
						"\r\n" +
						"			.no-stack .col.num9 {\r\n" +
						"				width: 75% !important;\r\n" +
						"			}\r\n" +
						"\r\n" +
						"			.video-block {\r\n" +
						"				max-width: none !important;\r\n" +
						"			}\r\n" +
						"\r\n" +
						"			.mobile_hide {\r\n" +
						"				min-height: 0px;\r\n" +
						"				max-height: 0px;\r\n" +
						"				max-width: 0px;\r\n" +
						"				display: none;\r\n" +
						"				overflow: hidden;\r\n" +
						"				font-size: 0px;\r\n" +
						"			}\r\n" +
						"\r\n" +
						"			.desktop_hide {\r\n" +
						"				display: block !important;\r\n" +
						"				max-height: none !important;\r\n" +
						"			}\r\n" +
						"		}\r\n" +
						"	</style>\r\n" +
						"</head>\r\n" +
						"\r\n" +
						"<body class=\"clean-body\" style=\"margin: 0; padding: 0; -webkit-text-size-adjust: 100%; background-color: #EFC7C4;\">\r\n"
						+
						"	<table bgcolor=\"#EFC7C4\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\"\r\n"
						+
						"		style=\"table-layout: fixed; vertical-align: top; min-width: 320px; Margin: 0 auto; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; "
						+ "background-color: #EFC7C4; width: 100%;\"\r\n"
						+
						"		valign=\"top\" width=\"100%\">\r\n" +
						"		<tbody>\r\n" +
						"			<tr style=\"vertical-align: top;\" valign=\"top\">\r\n" +
						"				<td style=\"word-break: break-word; vertical-align: top;\" valign=\"top\">\r\n"
						+
						"					<div style=\"background-color:transparent;\">\r\n" +
						"						<div class=\"block-grid\"\r\n" +
						"							style=\"Margin: 0 auto; min-width: 320px; max-width: 665px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; "
						+ "background-color: transparent;\">\r\n"
						+
						"							<div\r\n" +
						"								style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\r\n"
						+
						"								<div class=\"col num12\"\r\n" +
						"									style=\"min-width: 320px; max-width: 665px; display: table-cell; vertical-align: top; width: 665px;\">\r\n"
						+
						"									<div style=\"width:100% !important;\">\r\n" +
						"										<div\r\n" +
						"											style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; "
						+ "padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\r\n"
						+
						"										</div>\r\n" +
						"									</div>\r\n" +
						"								</div>\r\n" +
						"							</div>\r\n" +
						"						</div>\r\n" +
						"					</div>\r\n" +
						"					<div style=\"background-color:transparent;\">\r\n" +
						"						<div class=\"block-grid\"\r\n" +
						"							style=\"Margin: 0 auto; min-width: 320px; max-width: 665px; overflow-wrap: break-word; word-wrap: break-word; word-break: "
						+ "break-word; background-color: transparent;\">\r\n"
						+
						"							<div\r\n" +
						"								style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\r\n"
						+
						"								<div class=\"col num12\"\r\n" +
						"									style=\"min-width: 320px; max-width: 665px; display: table-cell; vertical-align: top; width: 665px;\">\r\n"
						+
						"									<div style=\"width:100% !important;\">\r\n" +
						"										<div\r\n" +
						"											style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; "
						+ "padding-top:0px; padding-bottom:10px; padding-right: 10px; padding-left: 10px;\">\r\n"
						+
						"											<div\r\n" +
						"												style=\"color:#ff776d;font-family:メイリオ, Meiryo, ＭＳ Ｐゴシック, MS PGothic, ヒラギノ角ゴ Pro W3, Hiragino Kaku Gothic Pro,Osaka, "
						+ "sans-serif;line-height:1.2;padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;\">\r\n"
						+
						"												<div\r\n" +
						"													style=\"line-height: 1.2; font-size: 12px; color: #ff776d; font-family: メイリオ, Meiryo, ＭＳ Ｐゴシック, MS PGothic, ヒラギノ角ゴ Pro W3, "
						+ "Hiragino Kaku Gothic Pro,Osaka, sans-serif; mso-line-height-alt: 14px;\">\r\n"
						+
						"													<p\r\n" +
						"														style=\"text-align: center; line-height: 1.2; word-break: break-word; mso-line-height-alt: NaNpx; margin: 0;\">\r\n"
						+
						"														<strong><span style=\"font-size: 64px;\">はる薬局</span></strong></p>\r\n"
						+
						"												</div>\r\n" +
						"											</div>\r\n" +
						"										</div>\r\n" +
						"									</div>\r\n" +
						"								</div>\r\n" +
						"							</div>\r\n" +
						"						</div>\r\n" +
						"					</div>\r\n" +
						"					<div style=\"background-color:transparent;\">\r\n" +
						"						<div class=\"block-grid\"\r\n" +
						"							style=\"Margin: 0 auto; min-width: 320px; max-width: 665px; overflow-wrap: break-word; word-wrap: break-word; word-break: "
						+ "break-word; background-color: transparent;\">\r\n"
						+
						"							<div\r\n" +
						"								style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\r\n"
						+
						"								<div class=\"col num12\"\r\n" +
						"									style=\"min-width: 320px; max-width: 665px; display: table-cell; vertical-align: top; width: 665px;\">\r\n"
						+
						"									<div style=\"width:100% !important;\">\r\n" +
						"										<div\r\n" +
						"											style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; "
						+ "padding-top:0px; padding-bottom:0px; padding-right: 10px; padding-left: 10px;\">\r\n"
						+
						"											<div\r\n" +
						"												style=\"color:#ffffff;font-family:メイリオ, Meiryo, ＭＳ Ｐゴシック, MS PGothic, ヒラギノ角ゴ Pro W3, Hiragino Kaku Gothic Pro,Osaka, "
						+ "sans-serif;line-height:1.2;padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;\">\r\n"
						+
						"												<div\r\n" +
						"													style=\"line-height: 1.2; font-size: 12px; color: #ffffff; font-family: メイリオ, Meiryo, ＭＳ Ｐゴシック, MS PGothic, ヒラギノ角ゴ Pro W3, "
						+ "Hiragino Kaku Gothic Pro,Osaka, sans-serif; mso-line-height-alt: 14px;\">\r\n"
						+
						"													<p\r\n" +
						"														style=\"text-align: center; line-height: 1.2; word-break: break-word; font-size: 46px; mso-line-height-alt: 55px; margin: 0;\">\r\n"
						+
						"														<span style=\"font-size: 46px;\">＊おすすめ商品のご案内です＊</span></p>\r\n"
						+
						"												</div>\r\n" +
						"											</div>\r\n" +
						"										</div>\r\n" +
						"									</div>\r\n" +
						"								</div>\r\n" +
						"							</div>\r\n" +
						"						</div>\r\n" +
						"					</div>\r\n" +
						"					<div style=\"background-color:transparent;\">\r\n" +
						"						<div class=\"block-grid\"\r\n" +
						"							style=\"Margin: 0 auto; min-width: 320px; max-width: 665px; overflow-wrap: break-word; word-wrap: break-word; word-break: "
						+ "break-word; background-color: transparent;\">\r\n"
						+
						"							<div\r\n" +
						"								style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\r\n"
						+
						"								<div class=\"col num12\"\r\n" +
						"									style=\"min-width: 320px; max-width: 665px; display: table-cell; vertical-align: top; width: 665px;\">\r\n"
						+
						"									<div style=\"width:100% !important;\">\r\n" +
						"										<div\r\n" +
						"											style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; "
						+ "padding-top:0px; padding-bottom:0px; padding-right: 0px; padding-left: 0px;\">\r\n"
						+
						"											<div align=\"center\" class=\"img-container center fixedwidth\"\r\n"
						+
						"												style=\"padding-right: 10px;padding-left: 10px;\">\r\n"
						+
						"												<div style=\"font-size:1px;line-height:10px\"> </div><img align=\"center\"\r\n"
						+
						"													alt=\"Image\" border=\"0\" class=\"center fixedwidth\"\r\n"
						+
						"													src=\"cid:img\"\r\n" + //画像
						"													style=\"text-decoration: none; -ms-interpolation-mode: bicubic; border: 0; height: auto; width: 100%; max-width: 432px; display: block;\"\r\n"
						+
						"													title=\"Image\" width=\"432\" />\r\n" +
						"												<div style=\"font-size:1px;line-height:10px\"> </div>\r\n"
						+
						"											</div>\r\n" +
						"											<div align=\"center\" class=\"img-container center fixedwidth\"\r\n"
						+
						"												style=\"padding-right: 0px;padding-left: 0px;\">\r\n"
						+
						"												<div style=\"font-size:1px;line-height:10px\"> </div><img align=\"center\"\r\n"
						+
						"													alt=\"heart\" border=\"0\" class=\"center fixedwidth\"\r\n"
						+
						"													src=\"cid:mail\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; border: 0; height: auto; width: 100%; "
						+ "max-width: 166px; display: block;\"\r\n"
						+
						"													title=\"heart\" width=\"166\" />\r\n" +
						"												<div style=\"font-size:1px;line-height:10px\"> </div>\r\n"
						+
						"											</div>\r\n" +
						"											<div\r\n" +
						"												style=\"color:#4e4d4d;font-family:メイリオ, Meiryo, ＭＳ Ｐゴシック, MS PGothic, ヒラギノ角ゴ Pro W3, Hiragino Kaku Gothic "
						+ "Pro,Osaka, sans-serif;line-height:1.8;padding-top:40px;padding-right:0px;padding-bottom:20px;padding-left:10px;\">\r\n"
						+
						"												<div\r\n" +
						"													style=\"line-height: 1.8; font-size: 12px; color: #4e4d4d; font-family: メイリオ, Meiryo, ＭＳ Ｐゴシック, MS PGothic, ヒラギノ角ゴ Pro W3, "
						+ "Hiragino Kaku Gothic Pro,Osaka, sans-serif; mso-line-height-alt: 22px;\">\r\n"
						+
						"													<p\r\n" +
						"														style=\"line-height: 1.8; word-break: break-word; font-size: 20px; mso-line-height-alt: 36px; margin: 0;\">\r\n"
						+
						"														<span style=\"font-size: 20px;\">ブランド："
						+ p.getManufacturerName() + "<p>  </p>" + p.getBrandName() + "</span></p>\r\n"
						+
						"													<p\r\n" +
						"														style=\"line-height: 1.8; word-break: break-word; font-size: 20px; mso-line-height-alt: 36px; margin: 0;\">\r\n"
						+
						"														<span style=\"font-size: 20px;\">商品名："
						+ p.getProductName() + "</span></p>\r\n"
						+
						"													<p\r\n" +
						"														style=\"line-height: 1.8; word-break: break-word; font-size: 20px; mso-line-height-alt: 36px; margin: 0;\">\r\n"
						+
						"														<span style=\"font-size: 20px;\">カラー名："
						+ p.getColorName() + "  " + p.getColorNum() + "</span></p>\r\n"
						+
						"													<p\r\n" +
						"														style=\"line-height: 1.8; word-break: break-word; font-size: 20px; mso-line-height-alt: 36px; margin: 0;\">\r\n"
						+
						"														<span style=\"font-size: 20px;\">価格："
						+ p.getOutPrice() + "円（税抜）</span></p>\r\n"
						+
						"													<p\r\n" +
						"														style=\"line-height: 1.8; word-break: break-word; font-size: 20px; mso-line-height-alt: 36px; margin: 0;\">\r\n"
						+
						"														<span style=\"font-size: 20px;\">パーソナルカラー："
						+ p.getPcName1() + "</span></p><br>\r\n"
						+
						"													<p\r\n" +
						"														style=\"line-height: 1.8; word-break: break-word; font-size: 20px; mso-line-height-alt: 36px; margin: 0;\">\r\n"
						+
						"														<span style=\"font-size: 20px;\">商品説明</span><br /><span\r\n"
						+
						"															style=\"font-size: 20px;\">"
						+ p.getMemo() + "</span>\r\n"
						+
						"													</p><br>\r\n" +
						"													<p style=\"line-height: 1.8; word-break: break-word; font-size: 20px; mso-line-height-alt: 36px; margin: 0;\">\r\n"
						+
						"														<span style=\"font-size: 20px;\"><a href=\""
						+ p.getUrl() + "\">公式サイトへ</a></span></p>\r\n"
						+
						"												</div>\r\n" +
						"											</div>\r\n" +
						"										</div>\r\n" +
						"									</div>\r\n" +
						"								</div>\r\n" +
						"							</div>\r\n" +
						"						</div>\r\n" +
						"					</div>\r\n" +
						"					<div style=\"background-color:#ff776d;\">\r\n" +
						"						<div class=\"block-grid\"\r\n" +
						"							style=\"Margin: 0 auto; min-width: 320px; max-width: 665px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; "
						+ "background-color: transparent;\">\r\n"
						+
						"							<div\r\n" +
						"								style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\r\n"
						+
						"								<div class=\"col num12\"\r\n" +
						"									style=\"min-width: 320px; max-width: 665px; display: table-cell; vertical-align: top; width: 665px;\">\r\n"
						+
						"									<div style=\"width:100% !important;\">\r\n" +
						"										<div\r\n" +
						"											style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; "
						+ "padding-top:0px; padding-bottom:0px; padding-right: 0px; padding-left: 0px;\">\r\n"
						+
						"											<div\r\n" +
						"												style=\"color:#FFFFFF;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;line-height:1.2;padding-top:20px;padding-right:10px;"
						+ "padding-bottom:20px;padding-left:10px;\">\r\n"
						+
						"												<div\r\n" +
						"													style=\"font-size: 12px; line-height: 1.2; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; color: #FFFFFF; "
						+ "mso-line-height-alt: 14px;\">\r\n"
						+
						"													<p\r\n" +
						"														style=\"font-size: 11px; line-height: 1.2; text-align: center; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; "
						+ "word-break: break-word; mso-line-height-alt: 13px; margin: 0;\">\r\n"
						+
						"														<span style=\"font-size: 11px;\">COPYRIGHT@LOREM IPSUM DOLOR SIT\r\n"
						+
						"															AMET</span></p>\r\n" +
						"												</div>\r\n" +
						"											</div>\r\n" +
						"										</div>\r\n" +
						"									</div>\r\n" +
						"								</div>\r\n" +
						"							</div>\r\n" +
						"						</div>\r\n" +
						"					</div>\r\n" +
						"				</td>\r\n" +
						"			</tr>\r\n" +
						"		</tbody>\r\n" +
						"	</table>\r\n" +
						"</body>\r\n" +
						"\r\n" +
						"</html>";
				MimeBodyPart htmlBodyPart = new MimeBodyPart();
				htmlBodyPart.setText(text, "shift-jis", "html");
				htmlBodyPart.setHeader("Content-Transfer-Encoding", "base64");
				mixedPart.addBodyPart(htmlBodyPart);

				// inline image
				String b_img = p.getPhoto();
				String a_img = b_img.substring(1, b_img.length());
				System.out.println(a_img);
				MimeBodyPart imageBodyPart1 = new MimeBodyPart();
				DataSource dataSource1 = new FileDataSource(
						"C:/pleiades/workspace/cosume/WebContent" + a_img);
				DataHandler dataHandler1 = new DataHandler(dataSource1);
				imageBodyPart1.setDataHandler(dataHandler1);
				imageBodyPart1.setFileName(MimeUtility.encodeWord("cosme"));
				imageBodyPart1.setDisposition("inline"); // inline指定しておく
				imageBodyPart1.setContentID("img"); // インライン画像を指定
				mixedPart.addBodyPart(imageBodyPart1);
			}

			// inline image
			MimeBodyPart imageBodyPart2 = new MimeBodyPart();
			DataSource dataSource2 = new FileDataSource("C:/pleiades/workspace/cosume/WebContent/img/mail.png");
			DataHandler dataHandler2 = new DataHandler(dataSource2);
			imageBodyPart2.setDataHandler(dataHandler2);
			imageBodyPart2.setFileName(MimeUtility.encodeWord("mail.png"));
			imageBodyPart2.setDisposition("inline"); // inline指定しておく
			imageBodyPart2.setContentID("mail"); // インライン画像を指定
			mixedPart.addBodyPart(imageBodyPart2);

			// set misedPart
			msg.setContent(mixedPart);

			Transport.send(msg);

			System.out.println("送信しました。");

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("メール送信エラー");

		}

		page = "/CustomerSearchServlet?page=m";
		disp = request.getRequestDispatcher(page);
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
