package com.music;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class findcb
 */
@WebServlet("/findcb.do")
public class findcb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findcb() {
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
		String cbmp3 = request.getParameter("cbmp3");
		String patternStr = "a=\"http.*mp3\",";
		String targetStr = null;
		String cbresult = null;
		try{
			targetStr = Main.SendGet(cbmp3);
			cbresult = Main.RegexCBMp3(targetStr, patternStr);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("cbresult", cbresult);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/cbresult.jsp");
		rd.forward(request, response);
	}

}
