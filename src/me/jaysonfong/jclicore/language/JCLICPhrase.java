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
package me.jaysonfong.jclicore.language;

/**
 *
 * @author Jayson Fong <fong.jayson@gmail.com>
 */
public enum JCLICPhrase implements JCLICPhrases {
    
    TERMINATING ("Terminating..."),
    INVALID_STRING ("Invalid String '%s'"),
    INVALID_BOOLEAN ("Invalid Boolean '%s'"),
    INVALID_PORT ("Invalid Port '%s'"),
    SQL_CONNECT_FAIL ("Unable to Connect to Database With Username '%s' and URL '%s': %s"),
    SQL_GET_STATEMENT_FAIL ("Unable to create statement with username '%s' and URL '%s'"),
    SQL_NOT_CONNECTED ("No database connection present."),
    SQL_EXECUTE_FAIL ("Unable to execute query '%s'"),
    SQL_CLOSE_FAIL ("Failed to close SQL connection."),
    SQL_PREPARE_FAIL ("Failed to prepare SQL query."),
    SQL_PREPARED_EXECUTE_FAIL ("Failed to execute prepared statement.");
    
    
    private final String phraseText;
    
    private JCLICPhrase(String phraseText) {
        this.phraseText = phraseText;
    }
    
    @Override
    public String toString() {
        return phraseText;
    }
    
    /**
     *
     * @param params
     * @return
     */
    @Override
    public String toString(Object... params) {
        return String.format(phraseText, params);
    }
    
}
