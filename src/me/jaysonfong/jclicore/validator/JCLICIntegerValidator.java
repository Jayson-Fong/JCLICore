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
public class JCLICIntegerValidator implements JCLICArgumentValidators {
    
    int objectValue;
    public final int minValue, maxValue;
    
    public JCLICIntegerValidator() {
        minValue = 0;
        maxValue = 0;
    }
    
    public JCLICIntegerValidator(int minValue) {
        this.minValue = minValue;
        maxValue = 0;
    }
    
    public JCLICIntegerValidator(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    
    /**
     *
     * @return
     */
    @Override
    public Integer getConvertedObject() {
        return objectValue;
    }
    
    /**
     *
     * @param argument
     * @return
     * @throws JCLICInputValidationException
     */
    @Override
    public boolean validate(Object argument) throws JCLICInputValidationException {
        if (!checkType(argument)) return false;
        else return checkValue();
    }
    
    public boolean checkType(Object argument) throws JCLICInputValidationException {
        try {
            objectValue = Integer.parseInt((String) argument);
            return true;
        } catch (Exception e) {
            throwJCLICInputValidationError(argument);
            return false;
        }
    }
    
    private boolean checkValue() throws JCLICInputValidationException {
        if ((minValue <= 0 || objectValue >= minValue)
            && (maxValue <= 0 || objectValue <= maxValue)) return true; else {
            throwJCLICInputValidationError(objectValue);
            return false;
        }
    }
    
    private void throwJCLICInputValidationError(Object argument) throws JCLICInputValidationException  {
        throw new JCLICInputValidationException(
            JCLICPhrase.INVALID_BOOLEAN,
            argument
        );
    }
    
}
