package com.sqlclient.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum QueryType {
    SELECT("SELECT", null),
    INSERT("INSERT", "%d row(s) inserted"),
    UPDATE("UPDATE", "%d row(s) updated"),
    DROP("DROP", "Table dropped"),
    CREATE("CREATE", "Table created"),
    DELETE("DELETE", "%d row(s) deleted");

    private final String keyWord;
    private final String message;

    public static QueryType getQueryType(String keyWord) {
        for (QueryType queryType : values()) {
            if (queryType.keyWord.equalsIgnoreCase(keyWord)) {
                return queryType;
            }
        }
        return null;
    }

    public String getMessage() {
        return message;
    }

    public String getQueryType() {
        return keyWord;
    }
}
