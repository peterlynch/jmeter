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
import java.io.Serializable;
import java.util.*;

import org.apache.jmeter.util.JMeterUtils;
import org.apache.jmeter.testelement.property.ListProperty;
import org.apache.jmeter.testelement.AbstractTestElement;

// Mark Walsh, 2002-08-03 add method addArgument(String name, Object value, Object metadata)
// modify methods toString(), addEmptyArgument(), addArgument(String name, Object value)
/****************************************
 * Title: Apache JMeter Description: Copyright: Copyright (c) 2000 Company:
 * Apache Foundation
 *
 * @author    Michael Stover
 * @author    <a href="mailto:oliver@tuxerra.com">Oliver Rossmueller</a>
 * @created   $Date$
 * @version   1.0
 ***************************************/

public class Arguments extends AbstractTestElement implements Serializable
{
	public static String[] COLUMN_NAMES = {
			JMeterUtils.getResString("name_column"),
			JMeterUtils.getResString("value_column"),
                        JMeterUtils.getResString("metadata")
			};

	public final static String ARGUMENTS = "arguments";


    private ListProperty arguments = new ListProperty(this);


	public Arguments()
	{
	}


    public List getArguments()
    {
        return arguments;
    }


    public void setArguments(List arguments)
    {
        this.arguments.setValue(arguments);
    }


	public Map getArgumentsAsMap()
	{
		Iterator iter = getArguments().iterator();
		Map argMap = new HashMap();
		while(iter.hasNext())
		{
			Argument arg = (Argument)iter.next();
			argMap.put(arg.getName(),arg.getValue());
		}
		return argMap;
	}

	public void addArgument(String name, String value)
	{
		getArguments().add(new Argument(name, value, null));
	}

	public void addArgument(Argument arg)
	{
		getArguments().add(arg);
	}

	public void addArgument(String name, String value, Object metadata)
	{
		getArguments().add(new Argument(name, value, metadata));
	}

	public Iterator iterator()
	{
		return getArguments().iterator();
	}

	public String toString()
	{
		StringBuffer str = new StringBuffer();
		Iterator iter = getArguments().iterator();
		while(iter.hasNext())
		{
			Argument arg = (Argument)iter.next();
			if (arg.getMetaData() == null) {
			    str.append(arg.getName() + "=" + arg.getValue());
			} else {
			    str.append(arg.getName() + arg.getMetaData() + arg.getValue());
			}
			if(iter.hasNext())
			{
				str.append("&");
			}
		}
		return str.toString();
	}

	public void removeArgument(int row)
	{
		if(row < getArguments().size())
		{
			getArguments().remove(row);
		}
	}

	public void removeArgument(Argument arg)
	{
		getArguments().remove(arg);
	}

	public void removeArgument(String argName)
	{
		Iterator iter = getArguments().iterator();
		while(iter.hasNext())
		{
			Argument arg = (Argument)iter.next();
			if(arg.getName().equals(argName))
			{
				iter.remove();
			}
		}
	}


	public void removeAllArguments()
	{
		if(getArguments().size() > 0)
		{
			getArguments().clear();
		}
	}


	public void addEmptyArgument()
	{
		getArguments().add(new Argument("", "",null));
	}


	public int getArgumentCount()
	{
		return getArguments().size();
	}


	public Argument getArgument(int row)
	{
		Argument argument = null;

		if(row < getArguments().size())
		{
			argument = (Argument)getArguments().get(row);
		}

		return argument;
	}
}
