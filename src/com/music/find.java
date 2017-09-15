package com.music;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class find
 */
@WebServlet("/find.do")
public class find extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public find() {
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
		String ID = request.getParameter("ID");
		String url = "http://music.163.com/api/song/lyric?" + "id=" + ID + "&lv=1&kv=1&tv=-1";
		String urlname = "http://music.163.com/api/song/detail/?id="+ID+"&ids=%5B"+ID+"%5D";
		String patternStr = "\\"+"["+".*"+"\\"+"\\n"+"\""+"},"+"\"klyric\"";
		String songpattern = "\"songs\":"+"\\"+"["+"\\"+"{\"name\":\".*\"";
		String artistpattern = "\"artists\":"+"\\"+"["+"\\"+"{\"name\":\".*\""; 
		String targetStr = null;
		String nametargetStr = null;
		String[] lys = null;
		String result = null;
		String songname = null;
		String artistname = null;
		String lianjie = "http://music.163.com/song/media/outer/url?id=";
		try{
			targetStr = Main.SendGet(url);
			lys = Main.RegexString(targetStr, patternStr);
			if(lys != null){
				for(String x:lys){
					if(result == null){
						result = x + "\r\n";
					}
					else{
						result = result + x + "\r\n";
					}
				}
			}
			nametargetStr = Main.SendGet(urlname);
			songname = Main.Regexname(nametargetStr, songpattern);
			artistname = Main.Regexname(nametargetStr, artistpattern);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("songname", songname);
		request.setAttribute("artistname", artistname);
		request.setAttribute("lianjie", lianjie + ID + ".mp3");
		request.setAttribute("result", result);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/result.jsp");
		rd.forward(request, response);
	}

}
