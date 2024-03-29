package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException,
            ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("class"));
        connection = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"), properties.getProperty("password"));
    }

    public void useStatement(String command) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(command);
        }
    }

    public void createTable(String tableName) throws SQLException {
        String create = String.format("create table if not exists %s ();", tableName);
        useStatement(create);
    }

    public void dropTable(String tableName) throws SQLException {
        String delete = String.format("drop table %s ;", tableName);
        useStatement(delete);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String add = String.format("ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type);
        useStatement(add);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String drop = String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName);
        useStatement(drop);
    }

    public void renameColumn(String tableName,
                             String columnName, String newColumnName) throws SQLException {
        String rename = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName,
                columnName, newColumnName);
        useStatement(rename);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("test");
            tableEditor.createTable("test2");
            tableEditor.dropTable("test2");
            tableEditor.addColumn("test", "name", "text");
            tableEditor.renameColumn("test", "name", "newname");
            tableEditor.dropColumn("test", "newname");
            System.out.println(getTableScheme(tableEditor.connection, "test"));
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
