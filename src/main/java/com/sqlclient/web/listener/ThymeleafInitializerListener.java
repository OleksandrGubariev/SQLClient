package com.sqlclient.web.listener;

import com.sqlclient.web.templater.TemplateEngineFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ThymeleafInitializerListener implements ServletContextListener {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("Initializing thymeleaf processor");
        ServletContext servletContext = sce.getServletContext();
        TemplateEngineFactory.configTemplate(servletContext);
    }
}
