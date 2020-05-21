package com.sqlclient.entity;

public enum QueryType {
    SELECT("SELECT"),
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DROP("DROP"),
    CREATE("CREATE"),
    DELETE("DELETE");

    private String keyWord;

    QueryType(String keyWord){
        this.keyWord = keyWord;
    }

    public static QueryType getQueryType(String keyWord){
        for (QueryType queryType: values()) {
            if(queryType.keyWord.equalsIgnoreCase(keyWord)){
                return queryType;
            }
        }
        throw new IllegalArgumentException("Error query type");
    }

    public String getQueryType() {
        return keyWord;
    }
}
