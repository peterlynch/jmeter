/*
 * ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001 - 2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 * if any, must include the following acknowledgment:
 * "This product includes software developed by the
 * Apache Software Foundation (http://www.apache.org/)."
 * Alternately, this acknowledgment may appear in the software itself,
 * if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Apache" and "Apache Software Foundation" and
 * "Apache JMeter" must not be used to endorse or promote products
 * derived from this software without prior written permission. For
 * written permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 * "Apache JMeter", nor may "Apache" appear in their name, without
 * prior written permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package org.apache.jmeter.assertions;


import java.io.*;
import java.text.MessageFormat;

import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.testelement.AbstractNamedTestElement;
import org.apache.jmeter.testelement.category.AssertionCategory;
import org.apache.jmeter.util.JMeterUtils;


/**
 * Checks if an Sample is sampled within a specified time-frame. If the
 * duration is larger than the timeframe the Assertion is considered
 * a failure.
 *
 * @author <a href="mailto:wolfram.rittmeyer@web.de">Wolfram Rittmeyer</a>
 * @author <a href="mailto:oliver@tuxerra.com">Oliver Rossmueller</a>
 * @version $Revision$, $Date$
 */
public class DurationAssertion extends AbstractNamedTestElement implements Serializable, Assertion, AssertionCategory
{

    public static final String DURATION = "duration";


    private long duration = 0L;

    /**
     * Returns the result of the Assertion. Here it checks wether the
     * Sample took to long to be considered successful. If so an AssertionResult
     * containing a FailureMessage will be returned. Otherwise the returned
     * AssertionResult will reflect the success of the Sample.
     */
    public AssertionResult getResult(SampleResult response)
    {
        AssertionResult result = new AssertionResult();
        result.setFailure(false);

        // has the Sample lasted to long?
        if (((response.getTime() > getDuration()) && (getDuration() > 0)))
        {
            result.setFailure(true);
            Object[] arguments = {new Long(response.getTime()), new Long(getDuration())};
            String message = MessageFormat.format(JMeterUtils.getResString("duration_assertion_failure"), arguments);
            result.setFailureMessage(message);
        }
        return result;
    }

    /**
     * Returns the duration to be asserted. A duration of 0 indicates this assertion is to
     * be ignored.
     */
    public long getDuration()
    {
        return duration;
    }

    /**
     * Set the duration that shall be asserted.
     *
     * @param duration A period of time in milliseconds. Is not allowed to be negative. Use Double.MAX_VALUE to indicate illegal or empty inputs. This will result to not checking the assertion.
     *
     * @throws IllegalArgumentException If <code>duration</code> is negative.
     */
    public void setDuration(long duration) throws IllegalArgumentException
    {
        if (duration < 0L)
        {
            throw new IllegalArgumentException(JMeterUtils.getResString("argument_must_not_be_negative"));
        }
        this.duration = duration;
    }
}