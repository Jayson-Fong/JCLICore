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
package me.jaysonfong.jclicore.io.inputparse;

/**
 *
 * @author Jayson Fong <fong.jayson@gmail.com>
 */
public class JCLICInputParser implements JCLICInputParsers {
    
    private final JCLICArgumentLists argumentList;
    private final String[] args;
    private JCLICArgument[] arguments;
    
    public JCLICInputParser(String[] args, JCLICArgumentLists argumentList) {
        this.args = args;
        this.argumentList = argumentList;
    }
    
    /**
     *
     */
    @Override
    public void parse() {
        // To be continued...
    }
    
}
