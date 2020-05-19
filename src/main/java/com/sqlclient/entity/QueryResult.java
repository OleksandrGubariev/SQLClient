package com.sqlclient.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class QueryResult {
    private List<String> columnName;
    private List<List<Object>> columnValues;
}
