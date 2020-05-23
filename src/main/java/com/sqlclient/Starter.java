package com.sqlclient;

import com.sqlclient.config.DataSourceFactory;
import com.sqlclient.config.PropertyReader;
import com.sqlclient.dao.jdbc.JdbcQueryExecuteDao;
import com.sqlclient.service.QueryExecuteService;
import com.sqlclient.web.servlet.GetStaticResourcesServlet;
import com.sqlclient.web.servlet.QueryExecuteServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.sql.DataSource;
import java.util.Properties;

public class Starter {
    public static void main(String[] args) throws Exception {

        Properties properties = new PropertyReader("application.properties").getProperties();
        DataSource dataSource = new DataSourceFactory(properties).getDataSource();
        JdbcQueryExecuteDao queryExecuteDao = new JdbcQueryExecuteDao(dataSource);
        QueryExecuteService queryExecuteService = new QueryExecuteService(queryExecuteDao);

        //servlets
        QueryExecuteServlet queryExecuteServlet = new QueryExecuteServlet(queryExecuteService);

        ServletContextHandler handler = new ServletContextHandler();

        ServletHolder queryExecuteHolder = new ServletHolder(queryExecuteServlet);
        handler.addServlet(queryExecuteHolder, "");

        ServletHolder getStaticResourcesHolder = new ServletHolder(new GetStaticResourcesServlet());
        handler.addServlet(getStaticResourcesHolder, "/assets/*");

//      server config
        int port = Integer.parseInt(properties.getProperty("port"));

        Server server = new Server(port);
        server.setHandler(handler);
        server.start();

        server.join();

    }
}
