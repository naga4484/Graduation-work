package tool;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns={"*.action"})
@MultipartConfig(
		maxFileSize = 104857600,  // 最大ファイルサイズ: 100MB
	    maxRequestSize = 524288000,  // 最大リクエストサイズ: 500MB
	    fileSizeThreshold = 0  // ファイルのサイズの閾値（0は即時保存）
)
public class FrontController extends HttpServlet {
	
	public void doPost (
			HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		try {
			String path=request.getServletPath().substring(1);
			String name=path.replace(".a","A").replace('/', '.');
			Action action=(Action)Class.forName(name).
					getDeclaredConstructor().newInstance();
			String url=action.execute(request, response);
			request.getRequestDispatcher(url).
				forward(request, response);
		} catch (Exception e) {
			e.printStackTrace(out);
		}			
	}
	
	public void doGet (
			HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		doPost(request, response);
	}
}