package tw.com.anz.seminar.action;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import tw.com.anz.seminar.util.FileUtil;

public class UploadAction extends MappingDispatchAction {
	
	private Logger log = Logger.getLogger(getClass());
	
	public ActionForward image(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	    
	    String fileName = uploadFile(request, FileUtil.IMAGE_PATH);
		
		log.debug("Upload image name: " + fileName);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(fileName);
		response.getWriter().close();
		
		return null;
	}
	
	public ActionForward file(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	    
	    String fileName = uploadFile(request, FileUtil.FILE_PATH);
		
		log.debug("Upload file name: " + fileName);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(fileName);
		response.getWriter().close();
		
		return null;
	}
	
	private String uploadFile(HttpServletRequest request, String filePath) throws Exception {
		String path = request.getSession().getServletContext().getRealPath(filePath);
		
		DiskFileUpload fu = new DiskFileUpload();
	    fu.setSizeThreshold(4096);
//	    fu.setSizeMax(1000000);
	    fu.setRepositoryPath(path);
	    
	    String fileName = "";                                                                        
		Iterator itr = fu.parseRequest(request).iterator();

		while (itr.hasNext()) {
			FileItem fi = (FileItem) itr.next();
			fileName = FileUtil.getFilename(fi.getName());
			File file = new File(path, fileName);
			fi.write(file);
		}
		
		return fileName;
	}

}
