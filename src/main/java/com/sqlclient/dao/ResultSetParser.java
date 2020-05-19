package com.sqlclient.dao;

import com.sqlclient.entity.QueryResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ResultSetParser {
    private ResultSet resultSet;

    public ResultSetParser(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @SneakyThrows
    public QueryResult parse() {
        log.info("Parse result set");
        List<String> columnName = new ArrayList<>();
        List<List<Object>> rows = new ArrayList<>();
        int columnCount = resultSet.getMetaData().getColumnCount();
        log.info("Column count is : {}", columnCount);
        for (int i = 0; i < columnCount; i++) {
            columnName.add(resultSet.getMetaData().getColumnName(i));
        }
        while (resultSet.next()) {
            List<Object> rowValues = new ArrayList<>();
            for (int i = 0; i < columnCount; i++) {
                rowValues.add(resultSet.getObject(i));
            }
            rows.add(rowValues);
        }
        return QueryResult.builder()
                .columnName(columnName)
                .columnValues(rows).build();
    }
}
