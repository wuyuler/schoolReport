package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.Result;
import entity.ScoreOfStudent;
import utils.StuDBHelper;

/**
 * Servlet implementation class PartSlectServlet
 */
@WebServlet("/PartSlectServlet")
public class PartSlectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartSlectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		
		
		
		String sql = request.getParameter("sql");
		
		//≤‚ ‘
		//String sql="select * from schoolreport";
		PrintWriter out = response.getWriter();
		System.out.println(sql);
		
		List<ScoreOfStudent> list = StuDBHelper.executeSQL(sql);
//		for(ScoreOfStudent stu:list){
//			System.out.println(stu.getName());
//		}
		
		Result result1 = new Result();
		result1.setResult(1);
		result1.setStudentsData(list);
		Gson gson = new Gson();
		System.out.println(gson.toJson(result1));
		
		out.write(gson.toJson(result1));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
