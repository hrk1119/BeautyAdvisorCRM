

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Support;
import dao.SupportDAO;

/**
 * Servlet implementation class SupportEditServlet
 */
@WebServlet("/SupportEditServlet")
public class SupportEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupportEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//文字化け対策
		request.setCharacterEncoding("utf-8");

		//セッション
		HttpSession session = request.getSession(true);
		String c_code = (String) session.getAttribute("c_code");
		int employee  = (Integer) session.getAttribute("e_id");
		int suppId =  (Integer) session.getAttribute("suppEditId");

		//htmlから値を受け取る
//		String strCustomerCode = request.getParameter("customer_code");
		String strDate = request.getParameter("date");
		String memo = request.getParameter("memo");

		System.out.println(c_code);
		System.out.println(strDate);
		System.out.println(employee);
		System.out.println(memo);

		//int・date型に変換
		int customerCode = Integer.parseInt(c_code);
		Date date = Date.valueOf(strDate);
//		int employeeCode = Integer.parseInt(strEmployeeCode);

		//宣言
		RequestDispatcher disp;
		String page = "";

		//値をbeanに追加
		Support s = new Support();
		s.setSupportId(suppId);
		s.setCustomerCode(customerCode);
		s.setInDate(date);
		s.setEmployeeId(employee);
		s.setMemo(memo);

		//DAO
		try {
			SupportDAO dao = new SupportDAO();
			int line = dao.update(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("c_code", c_code);
		page = "/CustomerDetailServlet";
		disp = request.getRequestDispatcher(page);
		disp.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
