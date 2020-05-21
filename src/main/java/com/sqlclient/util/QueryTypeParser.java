package com.sqlclient.util;

import com.sqlclient.entity.QueryType;

public class QueryTypeParser {

    public QueryType queryParse(String query) {
        String keyWord = query.substring(0, query.indexOf(" "));
        return QueryType.getQueryType(keyWord);
    }
}
