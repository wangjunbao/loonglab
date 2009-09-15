package liugangc.appspot.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.org.apache.commons.logging.Log;
import com.google.appengine.repackaged.org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class GaeExampleServlet extends HttpServlet {
	
	static Log log = LogFactory.getLog(GaeExampleServlet.class);
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String webSite="http://www.csindex.com.cn/sseportal/csiportal/zs/jbxx/daily_index_info.jsp";
		
		try {
            URL url = new URL(webSite);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

        } catch (MalformedURLException e) {
            log.error(e.getMessage(),e);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
		
		StIndex entry=new StIndex();
		entry.setIndexNum(3000);
		entry.setPb(3.2);
		entry.setPe(32.3);
		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    pm.makePersistent(entry);
		
		resp.setContentType("text/plain");
		resp.getWriter().println("inser a entry("+entry.getId()+")");
		
		System.out.println("gaeExampleServlet("+entry.getId()+") end!");
	}
}
