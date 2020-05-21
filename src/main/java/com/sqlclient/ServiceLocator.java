package com.sqlclient;

import com.sqlclient.config.PropertyReader;
import com.sqlclient.config.DataSourceFactory;
import com.sqlclient.dao.QueryExecuteDao;
import com.sqlclient.dao.jdbc.JdbcQueryExecuteDao;
import com.sqlclient.service.QueryExecuteService;
import com.sqlclient.service.impl.DefaultQueryExecuteService;
import com.sqlclient.util.QueryTypeParser;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {

    private static final Map<Class<?>, Object> SERVICES = new HashMap<>();

    static {
        //config property reader util class
        PropertyReader propertyReader = new PropertyReader("application.properties");
        register(PropertyReader.class, propertyReader);

        DataSource dataSource = new DataSourceFactory(propertyReader.getProperties()).getDataSource();
        QueryExecuteDao queryExecuteDao = new JdbcQueryExecuteDao(dataSource);
        register(QueryExecuteDao.class, queryExecuteDao);

        QueryExecuteService queryExecuteService = new DefaultQueryExecuteService(queryExecuteDao);
        register(QueryExecuteService.class,  queryExecuteService);

        QueryTypeParser queryTypeParser = new QueryTypeParser();
        register(QueryTypeParser.class, queryTypeParser);
    }
    public static <T> T getService(Class<T> serviceClass) {
        T service = serviceClass.cast(SERVICES.get(serviceClass));
        return service;
    }

    private static void register(Class<?> serviceClass, Object service) {
        SERVICES.put(serviceClass, service);
    }


}
