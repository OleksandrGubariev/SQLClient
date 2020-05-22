package com.sqlclient.service;

import com.sqlclient.dao.jdbc.JdbcQueryExecuteDao;
import com.sqlclient.entity.QueryResult;
import com.sqlclient.entity.QueryType;
import com.sqlclient.util.QueryTypeParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class QueryExecuteService {
    private final JdbcQueryExecuteDao queryExecutorDao;

    public QueryResult queryExecute(String query) {
        log.info("Started query {}", query);
        QueryTypeParser queryTypeParser = new QueryTypeParser();
        QueryType queryType = queryTypeParser.queryParse(query);
        try {
            return tryExecute(query, queryType);
        } catch (SQLException e) {
            return QueryResult.builder()
                    .systemInfo(e.getMessage())
                    .build();
        }
    }

    private QueryResult tryExecute(String query, QueryType queryType) throws SQLException {
        if (queryType == null) {
            return QueryResult.builder()
                    .systemInfo("ERROR: syntax error")
                    .build();
        } else if (queryType == QueryType.SELECT) {
            return queryExecutorDao.queryExecuteSelect(query);
        } else {
            int countAffected = queryExecutorDao.queryExecute(query);
            String systemInfo = String.format(queryType.getMessage(), countAffected);
            return QueryResult.builder()
                    .systemInfo(systemInfo)
                    .build();
        }
    }
}
