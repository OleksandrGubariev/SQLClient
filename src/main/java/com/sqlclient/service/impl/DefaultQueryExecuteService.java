package com.sqlclient.service.impl;

import com.sqlclient.dao.QueryExecuteDao;
import com.sqlclient.entity.QueryResult;
import com.sqlclient.service.QueryExecuteService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultQueryExecuteService implements QueryExecuteService {
    private final QueryExecuteDao queryExecutorDao;

    public QueryResult queryExecuteSelect(String query) {
        return queryExecutorDao.queryExecuteSelect(query);
    }

    public int queryExecute(String query) {
        return queryExecutorDao.queryExecute(query);
    }
}
