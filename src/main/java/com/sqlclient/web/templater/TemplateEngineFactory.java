package com.sqlclient.web.templater;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class TemplateEngineFactory {
    private static final TemplateEngine TEMPLATE_ENGINE = new TemplateEngine();
    private static boolean isConfig;

    public static void configTemplate(ServletContext servletContext) {
        if (isConfig) {
            return;
        }
        isConfig = true;
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        TEMPLATE_ENGINE.addDialect(new Java8TimeDialect());
        TEMPLATE_ENGINE.setTemplateResolver(templateResolver);
    }

    @SneakyThrows
    public static void process(HttpServletRequest request, HttpServletResponse response, String template,
                               Map<String, Object> parameters) {
        IContext context = new WebContext(request, response, request.getServletContext(), Locale.ENGLISH,
                parameters);
        TEMPLATE_ENGINE.process(template, context, response.getWriter());
    }
}
