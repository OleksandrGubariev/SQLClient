package com.sqlclient.service;

import com.sqlclient.dao.QueryExecutor;
import com.sqlclient.entity.QueryResult;

public class QueryExecuteService {
    private QueryExecutor queryExecutor;

    public QueryExecuteService(QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    public QueryResult queryExecuteSelect(String query) {
        return queryExecutor.queryExecuteSelect(query);
    }

    public int queryExecute(String query) {
        return queryExecutor.queryExecute(query);
    }
}
