package liugangc.appspot.example;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.xml.sax.InputSource;

import com.google.appengine.repackaged.org.apache.commons.logging.Log;
import com.google.appengine.repackaged.org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class GaeExampleServlet extends HttpServlet {
	
	static Log log = LogFactory.getLog(GaeExampleServlet.class);
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String urlStr="http://www.csindex.com.cn/sseportal/ps/zhs/hqjt/csi/indexinfo.xls";
		log.info(urlStr);
		System.out.println("===="+urlStr);
		
		try {
			
            URL url = new URL(urlStr);
            InputStream is=url.openStream();
            POIFSFileSystem fs = new POIFSFileSystem(is);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			//System.out.println(sheet.getLastRowNum());
			for (int i = 4; i < 11 ; i++) {
				HSSFRow row = sheet.getRow(i);
				for (int j = 0; j <= 13; j++) {
					HSSFCell cell=row.getCell(j);
					
					if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC)
						System.out.println(cell.getNumericCellValue());
					else if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING)
						System.out.println(cell.getRichStringCellValue().getString());
						
					//System.out.println(cell.getCellType());
				}
				
				//log.info("=====================");
			}
			
			is.close();
			
			

        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
		
//		StIndex entry=new StIndex();
//		entry.setIndexNum(3000);
//		entry.setPb(3.2);
//		entry.setPe(32.3);
//		
//		
//		PersistenceManager pm = PMF.get().getPersistenceManager();
//	    pm.makePersistent(entry);
//		
//		resp.setContentType("text/plain");
//		resp.getWriter().println("inser a entry("+entry.getId()+")");
//		
//		System.out.println("gaeExampleServlet("+entry.getId()+") end!");
	}
}
