package com.key.common.plugs.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.jsp.PageContext;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerTest {
	public void execute(PageContext pageContext) throws Exception {
		Configuration cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(pageContext.getServletContext(), "WEB-INF/templates");

		Map root = new HashMap();
		root.put("name", "Tom");

		Template t = cfg.getTemplate("test.ftl");

		Writer out = pageContext.getResponse().getWriter();

		t.process(root, out);
	}
}