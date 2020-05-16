package com.sqlclient;

import com.sqlclient.config.PropertyReader;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;
import java.util.Properties;

public class Starter {
    public static void main(String[] args) throws ServletException, LifecycleException {
        PropertyReader propertyReader = new PropertyReader("application.properties");
        Properties properties = propertyReader.getProperties();

//      server config
        int port = Integer.parseInt(properties.getProperty("port"));
        String contextPath = "/";
        String webappDirLocation = "src/main/webapp/";
        String baseDirectory = new File(webappDirLocation).getAbsolutePath();

//      tomcat config
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        Context standardContext = tomcat.addWebapp(contextPath, baseDirectory);

//      Web servlet annotation config
        String buildPath = "target/classes";
        String webAppMount = "/WEB-INF/classes";

        File additionalWebInfClasses = new File(buildPath);
        WebResourceRoot resources = new StandardRoot(standardContext);
        resources.addPreResources(new DirResourceSet(resources, webAppMount, additionalWebInfClasses.getAbsolutePath(),
                contextPath));
        standardContext.setResources(resources);

//      start server
        tomcat.start();
        tomcat.getServer().await();
    }
}
