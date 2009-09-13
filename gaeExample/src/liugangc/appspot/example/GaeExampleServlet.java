package liugangc.appspot.example;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class GaeExampleServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, this is my first google app");
	}
}
