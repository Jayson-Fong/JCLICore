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

import me.jaysonfong.jclicore.exception.JCLICInputValidationException;

/**
 *
 * @author Jayson Fong <fong.jayson@gmail.com>
 */
public enum JCLICArgumentList implements JCLICArgumentLists {
    
    // Argument_Name Argument_Type Required Default_Value
    JClICVERSION (JCLICArgumentType.BOOLEAN, false, true);
    
    private final JCLICArgumentType argumentType;
    private final boolean required;
    private final Object defaultValue;
    
    private JCLICArgumentList(JCLICArgumentType argumentType, boolean required, Object defaultValue) {
        this.argumentType = argumentType;
        this.required = required;
        this.defaultValue = defaultValue;
    }
    
    private JCLICArgumentList(JCLICArgumentType argumentType, boolean required) {
        this.argumentType = argumentType;
        this.required = required;
        this.defaultValue = null;
    }
    
    private JCLICArgumentList(JCLICArgumentType argumentType) {
        this.argumentType = argumentType;
        this.required = false;
        this.defaultValue = null;
    }
    
    private JCLICArgumentList() {
        this.argumentType = JCLICArgumentType.OBJECT;
        this.required = false;
        this.defaultValue = false;
    }
    
    @Override
    public boolean isValidArgument(Object argument) throws JCLICInputValidationException {
        return argumentType.getArgumentValidator().validate(argument);
    }
    
    public Object getConvertedObject(Object argument) {
        return argumentType.getArgumentValidator().getConvertedObject();
    }
    
    public Object getDefaultValue() {
        return defaultValue;
    }
    
}
