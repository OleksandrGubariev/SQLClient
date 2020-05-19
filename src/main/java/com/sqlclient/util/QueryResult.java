package com.sqlclient.util;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class QueryResult {
    private List<String> columnName;
    private List<List<Object>> columnValues;
}
