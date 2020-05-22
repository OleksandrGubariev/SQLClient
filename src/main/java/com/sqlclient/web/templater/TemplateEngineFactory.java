package com.sqlclient.web.templater;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.Writer;
import java.util.Map;

public class TemplateEngineFactory {
    private static final TemplateEngine TEMPLATE_ENGINE = new TemplateEngine();
    private static boolean isConfigured;

    public static void process(String template, Map<String, Object> paramsMap, Writer writer) {
        if (!isConfigured) {
            configTemplate();
        }
        Context context = new Context();
        context.setVariables(paramsMap);
        TEMPLATE_ENGINE.process(template, context, writer);
    }

    public static void process(String template, Writer writer) {
        process(template, null, writer);
    }

    private static void configTemplate() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        TEMPLATE_ENGINE.setTemplateResolver(templateResolver);
        isConfigured = true;
    }
}
