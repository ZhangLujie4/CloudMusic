package com.music;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class findqq
 */
@WebServlet("/findqq.do")
public class findqq extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findqq() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String m4a = request.getParameter("m4a");
		String patternStr = "playurl.*m4a";
		String targetStr = null;
		String qqresult = null;
		try{
			targetStr = Main.SendGet(m4a);
			qqresult = Main.RegexMp3(targetStr, patternStr);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("qqresult", qqresult);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/qqresult.jsp");
		rd.forward(request, response);
	}

}
