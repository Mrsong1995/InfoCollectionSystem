package com.key.common.base.action;

import com.key.common.utils.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
@RequestMapping("/up")
@AllowedMethods({"saveimage","saveFile","saveUpFile"})
public class UploadController {

	private File[] files;
	private String[] fileNames;
	private String[] uploadContentTypes;

	/***
	 * 上传图片数据，非安全存储
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveimage")
	public String saveimage(HttpServletResponse response,HttpServletRequest request) throws Exception {
		StringBuffer sb = new StringBuffer();
		try {
			String rootPath = request.getSession().getServletContext()
					.getRealPath("/");
			String savePath = "file" + File.separator + "images" + File.separator;
			if (files != null && files.length > 0) {
				String[] newNames = null;
				newNames = FileUtils.transferFile2( rootPath+File.separator+savePath, files, fileNames);
				sb.append("{\"success\":\"true\"");
				sb.append(",\"filename\":\"");
				for (int i = 0; i < files.length; i++) {
					sb.append(fileNames[i]);
					sb.append("  ");
				}
				sb.append("\"");
				sb.append(",\"location\":\"");
				for (int i = 0; i < newNames.length; i++) {
					sb.append("/file/images/" + newNames[i]+ ";");
				}
				if(sb!=null&& StringUtils.isNotBlank(sb.toString())&&sb.toString().endsWith(";")){
					sb.deleteCharAt(sb.length()-1);
				}
				sb.append("\"");
			}
			sb.append("}");
		} catch (Exception e) {
			sb.append("{\"success\":\"false\",\"error\":\"上传失败\"}");
			e.printStackTrace();
		}
		response.setHeader("Content-Type", "text/plain;charset=UTF-8");
		response.getWriter().print(sb.toString());
		response.getWriter().close();
		return null;
	}

	/**
	 * 上传文件数据，非安全存储
	 **/
	@RequestMapping("/saveFile")
	public String saveFile(HttpServletResponse response,HttpServletRequest request) throws Exception {
		StringBuffer sb = new StringBuffer();
		try {
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			String basePath=request.getParameter("basepath");
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String savePath = "file" + File.separator + basePath + File.separator;
			if (files != null && files.length > 0) {
				String[] newNames;
				newNames = FileUtils.transferFile2( rootPath+File.separator+savePath, files, fileNames);
				sb.append("{\"success\":\"true\"");
				sb.append(",\"filename\":\"");
				for (int i = 0; i < files.length; i++) {
					sb.append(fileNames[i]);
					sb.append("  ");
				}
				sb.append("\"");
				sb.append(",\"location\":\"");
				for (int i = 0; i < newNames.length; i++) {
					sb.append("/file/"+basePath+"/" + newNames[i]+ ";");
				}
				if(sb!=null&& StringUtils.isNotBlank(sb.toString())&&sb.toString().endsWith(";")){
					sb.deleteCharAt(sb.length()-1);
				}
				sb.append("\"");
			}
			sb.append("}");
		} catch (Exception e) {
			sb.append("{\"success\":\"false\",\"error\":\"上传失败\"}");
			e.printStackTrace();
		}
		response.setHeader("Content-Type", "text/plain;charset=UTF-8");
		response.getWriter().print(sb.toString());
		response.getWriter().close();
		return null;
	}
	
	/**
	 * 上传文件数据，安全存储
	 * /WebRoot/WEB-INF/upfile
	 */
	@RequestMapping("/saveUpFile")
	public String saveUpFile(HttpServletResponse response,HttpServletRequest request) throws Exception {
		StringBuffer sb = new StringBuffer();
		try {
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			String basePath=request.getParameter("basepath");
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String savePath = "WEB-INF"+File.separator+"upfile" + File.separator;
			if (files != null && files.length > 0) {
				String[] newNames;
				newNames = FileUtils.transferFile2(rootPath+savePath, files, fileNames);
				sb.append("{\"success\":\"true\"");
				sb.append(",\"filename\":\"");
				for (int i = 0; i < files.length; i++) {
					sb.append(fileNames[i]);
					sb.append("  ");
				}
				sb.append("\"");
				sb.append(",\"location\":\"");
				for (int i = 0; i < newNames.length; i++) {
					sb.append("/WEB-INF/upfile/" + newNames[i]+ ";");
				}
				if(sb!=null&& StringUtils.isNotBlank(sb.toString())&&sb.toString().endsWith(";")){
					sb.deleteCharAt(sb.length()-1);
				}
				sb.append("\"");
			}
			sb.append("}");
		} catch (Exception e) {
			sb.append("{\"success\":\"false\",\"error\":\"上传失败\"}");
			e.printStackTrace();
		}
		response.setHeader("Content-Type", "text/plain;charset=UTF-8");
		response.getWriter().print(sb.toString());
		return null;
	}


	public File[] getUploadify() {
		return this.files;
	}
	public void setUploadify(File[] upload) {
		this.files = upload;
	}
	public String[] getUploadifyFileName() {
		return this.fileNames;
	}
	public void setUploadifyFileName(String[] uploadFileName) {
		this.fileNames = uploadFileName;
	}
	public String[] getUploadifyContentType() {
		return this.uploadContentTypes;
	}
	public void setUploadifyContentType(String[] uploadContentType) {
		this.uploadContentTypes = uploadContentType;
	}


}
