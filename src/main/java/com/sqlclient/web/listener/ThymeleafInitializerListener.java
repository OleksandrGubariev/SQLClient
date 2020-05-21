package com.sqlclient.web.listener;

import com.sqlclient.web.templater.TemplateEngineFactory;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Slf4j
public class ThymeleafInitializerListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Initializing thymeleaf processor");
        ServletContext servletContext = sce.getServletContext();
        TemplateEngineFactory.configTemplate(servletContext);
    }
}
