package com.sqlclient.dao.jdbc;

import com.sqlclient.dao.QueryExecuteDao;
import com.sqlclient.dao.parser.ResultSetParser;
import com.sqlclient.entity.QueryResult;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Slf4j
@RequiredArgsConstructor
public class JdbcQueryExecuteDao implements QueryExecuteDao {
    private final DataSource dataSource;

    @SneakyThrows
    public QueryResult queryExecuteSelect(String query) {
        log.info("Query SELECT execute");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            ResultSetParser resultSetParser = new ResultSetParser(resultSet);
            return resultSetParser.parse();
        }
    }

    @SneakyThrows
    public int queryExecute(String query) {
        log.info("Query execute");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return preparedStatement.executeUpdate();
        }
    }
}
