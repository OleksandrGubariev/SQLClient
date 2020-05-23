package com.sqlclient.entity;

import lombok.*;

import java.util.List;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class QueryResult {
    private final String systemInfo;
    private final List<String> columnNames;
    private final List<List<Object>> columnValues;
}
