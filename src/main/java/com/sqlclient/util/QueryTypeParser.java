package com.sqlclient.util;

import com.sqlclient.entity.QueryType;

public class QueryTypeParser {
    private String query;

    public QueryTypeParser(String query) {
        this.query = query;
    }

    public QueryType queryParse() {
        String keyWord = query.substring(0, query.indexOf(" "));
        return QueryType.getQueryType(keyWord);
    }
}
