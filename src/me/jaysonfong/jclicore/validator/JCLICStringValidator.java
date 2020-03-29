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
package me.jaysonfong.jclicore.validator;

import me.jaysonfong.jclicore.exception.JCLICInputValidationException;
import me.jaysonfong.jclicore.language.JCLICPhrase;

/**
 *
 * @author Jayson Fong <fong.jayson@gmail.com>
 */
public class JCLICStringValidator implements JCLICArgumentValidators {
    
    private String objectValue;
    private final String regex;
    private final int minLength, maxLength;
    
    public JCLICStringValidator() {
        regex = "";
        minLength = 0;
        maxLength = 0;
    }
    
    public JCLICStringValidator(String regex) {
        this.regex = regex;
        minLength = 0;
        maxLength = 0;
    }
    
    public JCLICStringValidator(String regex, int minLength) {
        this.regex = regex;
        this.minLength = minLength;
        maxLength = 0;
    }
    
    public JCLICStringValidator(String regex, int minLength, int maxLength) {
        this.regex = regex;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }
    
    public JCLICStringValidator(int minLength, int maxLength) {
        regex = "";
        this.minLength = minLength;
        this.maxLength = maxLength;
    }
    
    public JCLICStringValidator(int minLength) {
        regex = "";
        this.minLength = minLength;
        maxLength = 0;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getConvertedObject() {
        return objectValue;
    }
    
    @Override
    public boolean validate(Object argument) throws JCLICInputValidationException {
        if (!checkType(argument)) return false;
        else if (!checkRegex()) return false;
        return checkLength();
    }
    
    private boolean checkType(Object argument) throws JCLICInputValidationException {
        boolean validString = false;
        if (argument instanceof String) {
            objectValue = String.valueOf(argument);
            validString = true;
        } else {
            throwJCLICInputValidationError(argument);
        }
        return validString;
    }
    
    private boolean checkRegex() throws JCLICInputValidationException {
        if (regex.length() == 0 || objectValue.matches(regex)) return true;
        else {
            throwJCLICInputValidationError(objectValue);
            return false;
        }
    }
    
    private boolean checkLength() throws JCLICInputValidationException {
        if ((minLength <= 0 || objectValue.length() >= minLength)
            && (maxLength <= 0 || objectValue.length() <= maxLength)) return true; else {
            throwJCLICInputValidationError(objectValue);
            return false;
        }
    }
    
    private void throwJCLICInputValidationError(Object argument) throws JCLICInputValidationException  {
        throw new JCLICInputValidationException(
            JCLICPhrase.INVALID_STRING,
            argument
        );
    }
    
}
