package com.sqlclient.dao.jdbc;

import com.sqlclient.dao.parser.ResultSetParser;
import com.sqlclient.entity.QueryResult;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class JdbcQueryExecuteDao {
    private final DataSource dataSource;

    public QueryResult queryExecuteSelect(String query) throws SQLException {
        log.info("Query SELECT execute");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            ResultSetParser resultSetParser = new ResultSetParser(resultSet);
            return resultSetParser.parse();
        }
    }

    public int queryExecute(String query) throws SQLException {
        log.info("Query execute");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return preparedStatement.executeUpdate();
        }
    }
}
