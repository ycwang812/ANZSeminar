package tw.com.anz.seminar.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.springframework.web.bind.ServletRequestUtils;

import tw.com.anz.seminar.model.Question;
import tw.com.anz.seminar.model.Report;
import tw.com.anz.seminar.service.AdminService;
import tw.com.anz.seminar.util.DateUtil;

public class DownloadAction extends MappingDispatchAction {

	private Logger log = Logger.getLogger(getClass());
	
	private AdminService adminService;
	
	private char delimiter = '\t';
	private char textQualifier = '"';
	
	public ActionForward report(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long id = ServletRequestUtils.getLongParameter(request, "id");
		
		log.debug("id: " + id);
		
		List<Report> list = adminService.queryReport(id);
		
		String fileName = "ANZ_Semina_Report_" + System.currentTimeMillis() + ".csv";
		
		response.setContentType("application/csv; charset=utf-16le");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			out.write("\uFEFF");
			
			csvWriter(out, "觀看者", true);
			csvWriter(out, "觀看長度(秒)", false);
			csvWriter(out, "開啟時間", false);
			
			out.write("\n");

			for (Report report : list) {
				csvWriter(out, report.getUserId(), true);
				csvWriter(out, String.valueOf(report.getWatchTime()), false);
				csvWriter(out, report.getOpenTime(), false);
				
				out.write("\n");
			}

			out.flush();
		} catch (Exception ex) {
			log.error("download", ex);
		}
		
		return null;
	}
	
	public ActionForward question(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long id = ServletRequestUtils.getLongParameter(request, "id");
		
		log.debug("id: " + id);
		
		List<Question> list = adminService.queryQuestion(id);
		
		String fileName = "ANZ_Semina_Question_" + System.currentTimeMillis() + ".csv";
		
		response.setContentType("application/csv; charset=utf-16le");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			out.write("\uFEFF");
			
			csvWriter(out, "Time", true);
			csvWriter(out, "Asker", false);
			csvWriter(out, "Question", false);
			
			out.write("\n");

			for (Question question : list) {
				csvWriter(out, question.getTime(), true);
				csvWriter(out, question.getUserId(), false);
				csvWriter(out, question.getQuestion(), false);
				
				out.write("\n");
			}

			out.flush();
		} catch (Exception ex) {
			log.error("download", ex);
		}
		
		return null;
	}
	
	protected void csvWriter(PrintWriter writer, String content, boolean firstColumn) throws UnsupportedEncodingException {
		if (content == null) {
			content = " ";
		}

		if (!firstColumn) {
			writer.write(delimiter);
		}

		writer.write(textQualifier);
		content = replace(content, "" + textQualifier, "" + textQualifier + textQualifier);
		writer.write(new String(content.getBytes("utf-16le"), "utf-16le"));
		writer.write(textQualifier);
	}

	protected void csvWriter(PrintWriter writer, Date time, boolean firstColumn) throws UnsupportedEncodingException {
		String content = null;

		if (time != null) {
			content = DateUtil.formatSlashDateTime(time);;
		}

		csvWriter(writer, content, firstColumn);
	}

	protected String replace(String original, String pattern, String replace) {
		int len = pattern.length();
		int found = original.indexOf(pattern);

		if (found > -1) {
			StringBuffer sb = new StringBuffer();
			int start = 0;

			while (found != -1) {
				sb.append(original.substring(start, found));
				sb.append(replace);
				start = found + len;
				found = original.indexOf(pattern, start);
			}

			sb.append(original.substring(start));

			return sb.toString();
		} else {
			return original;
		}
	}
	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
}
