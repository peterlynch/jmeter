/*
 * ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
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
package org.apache.jmeter.config;


import java.io.*;

import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.testelement.property.StringProperty;
import org.apache.jmeter.testelement.property.ObjectProperty;


//Mark Walsh, 2002-08-03, add metadata attribute
// add constructor Argument(String name, Object value, Object metadata)
// add MetaData get and set methods

/****************************************
 * Title: Apache JMeter Description: Copyright: Copyright (c) 2000 Company:
 * Apache Foundation
 *
 * @author    Michael Stover
 * @author    <a href="mailto:oliver@tuxerra.com">Oliver Rossmueller</a>
 * @created   March 13, 2001
 * @version   1.0
 ***************************************/

public class Argument extends AbstractTestElement implements Serializable
{

    public final static String NAME = "name";
    public final static String VALUE = "value";
    public final static String METADATA = "metaData";


    private StringProperty name;
    private StringProperty value;
    private Object metaData;


    public Argument(String name, String value, Object metaData)
    {
        this(name, value);
        // todo: how to handle
//        this.metaData = new ObjectProperty(metaData);
    }


    public Argument(String name, String value)
    {
        this.name = new StringProperty(name);
        this.value = new StringProperty(value);
    }


    public Argument()
    {
        this("", null, null);
    }


    public String getName()
    {
        return name.getStringValue();
    }


    public void setName(String name)
    {
        this.name.setValue(name);
    }


    public String getValue()
    {
        return value.getStringValue();
    }


    public void setValue(String value)
    {
        this.value.setValue(value);
    }


    public void setMetaData(Object metaData)
    {
        this.metaData = metaData;
    }


    public Object getMetaData()
    {
        return metaData;
    }
}
