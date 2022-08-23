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
            ClassNotFoundException, IOException {
        this.properties = properties;
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("class"));
        connection = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"), properties.getProperty("password"));
    }

    public void createTable(String tableName) throws Exception {
        Statement statement = connection.createStatement();
        String create = "create table if not exists " + tableName + "();";
        statement.execute(create);
    }

    public void dropTable(String tableName) throws SQLException {
        Statement statement = connection.createStatement();
        String delete = "drop table " + tableName + ";";
        statement.execute(delete);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        Statement statement = connection.createStatement();
        String add = "ALTER TABLE " + tableName + " ADD COLUMN " + columnName + " " + type;
        statement.execute(add);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        Statement statement = connection.createStatement();
        String drop = "ALTER TABLE " + tableName + " DROP COLUMN " + columnName + ";";
        statement.execute(drop);
    }

    public void renameColumn(String tableName,
                             String columnName, String newColumnName) throws SQLException {
        Statement statement = connection.createStatement();
        String rename = "ALTER TABLE " + tableName + " RENAME COLUMN "
                + columnName + " TO " + newColumnName + ";";
        statement.execute(rename);
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
        TableEditor tableEditor = new TableEditor(new Properties());
        tableEditor.renameColumn("test", "name", "newname");
        System.out.println(getTableScheme(tableEditor.connection, "test"));
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
