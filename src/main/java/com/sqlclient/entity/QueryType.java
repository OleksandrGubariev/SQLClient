package com.sqlclient.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum QueryType {
    SELECT("SELECT"),
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DROP("DROP"),
    CREATE("CREATE"),
    DELETE("DELETE");

    private final String keyWord;

    public static QueryType getQueryType(String keyWord){
        for (QueryType queryType: values()) {
            if(queryType.keyWord.equalsIgnoreCase(keyWord)){
                return queryType;
            }
        }
        return null;
    }

    public String getQueryType() {
        return keyWord;
    }
}
