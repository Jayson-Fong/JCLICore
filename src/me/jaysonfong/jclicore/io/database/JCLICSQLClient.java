/*
 * The MIT License
 *
 * Copyright 2020 Jayson Fong <fong.jayson@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package me.jaysonfong.jclicore.io.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import me.jaysonfong.jclicore.exception.JCLICSQLException;
import me.jaysonfong.jclicore.language.JCLICPhrase;

/**
 *
 * @author Jayson Fong <fong.jayson@gmail.com>
 */
public class JCLICSQLClient {
    
    private final String url, username, password;
    private Connection connection;
    private boolean connected;
    
    public JCLICSQLClient(String url, String username) {
        this.url = url;
        this.username = username;
        this.password = "";
        connected = false;
    }
    
    public JCLICSQLClient(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        connected = false;
    }
    
    public String getUsername() {
        return username;
    }
    
    public boolean isConnected() {
        return connected;
    }
    
    public boolean connect() throws JCLICSQLException {
        try {
            connection = DriverManager.getConnection(
                url, username, password
            );
            connected = true;
            return connected;
        } catch (SQLException e) {
            throw new JCLICSQLException(
                    JCLICPhrase.SQL_CONNECT_FAIL, url, username, e.getLocalizedMessage()
            );
        }
    }
    
    public boolean close() throws JCLICSQLException {
        if (connected == false) {
            throw new JCLICSQLException(
                    JCLICPhrase.SQL_NOT_CONNECTED
            );
        }
        try {
            connection.close();
            connected = false;
            return true;
        } catch (SQLException e) {
            throw new JCLICSQLException(
                    JCLICPhrase.SQL_CLOSE_FAIL
            );
        }
    }
    
    public boolean query(String query) throws JCLICSQLException {
        if (connected == false) {
            throw new JCLICSQLException(
                    JCLICPhrase.SQL_NOT_CONNECTED
            );
        }
        Statement statement = getStatement();
        try {
            statement.executeQuery(query);
            return true;
        } catch (SQLException e) {
            throw new JCLICSQLException(
                    JCLICPhrase.SQL_EXECUTE_FAIL,
                    (query.length() < 15 ?
                        String.format("%s...", query) :
                        query.substring(0, 15)
                    )
            );
        }
    }
    
    public boolean query(String query, String... params) throws JCLICSQLException {
        if (connected == false) {
            throw new JCLICSQLException(
                    JCLICPhrase.SQL_NOT_CONNECTED
            );
        }
        PreparedStatement preparedStatement = getPreparedStatement(query);
        int i = 1;
        try {
            for (String param : params)
            preparedStatement.setString(i, param);
        } catch (SQLException e) {
            throw new JCLICSQLException(
                    JCLICPhrase.SQL_PREPARE_FAIL
            );
        }
        try {
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new JCLICSQLException(
                    JCLICPhrase.SQL_PREPARED_EXECUTE_FAIL
            );
        }
    }
    
    private Statement getStatement() throws JCLICSQLException {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            throw new JCLICSQLException(
                    JCLICPhrase.SQL_GET_STATEMENT_FAIL, url, username
            );
        }
    }
    
    private PreparedStatement getPreparedStatement(String query) throws JCLICSQLException {
        try {
            return connection.prepareStatement(query);
        } catch (Exception e) {
            throw new JCLICSQLException(
                    JCLICPhrase.SQL_GET_STATEMENT_FAIL, url, username
            );
        }
    }
    
}
