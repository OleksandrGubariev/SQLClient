package com.sqlclient.dao;

import com.sqlclient.entity.QueryResult;

public interface QueryExecuteDao {
    QueryResult queryExecuteSelect(String query);

    int queryExecute(String query);
}
