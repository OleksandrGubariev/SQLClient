package com.sqlclient.service;

import com.sqlclient.entity.QueryResult;

public interface QueryExecuteService {
    QueryResult queryExecuteSelect(String query);

    int queryExecute(String query);
}