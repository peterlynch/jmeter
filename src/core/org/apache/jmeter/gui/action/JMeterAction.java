/*
 * ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2003 The Apache Software Foundation.  All rights
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
package org.apache.jmeter.gui.action;


import javax.swing.*;

import org.apache.jmeter.util.JMeterUtils;


/**
 * @author <a href="mailto:oliver@tuxerra.com">Oliver Rossmueller</a>
 * @version $Revision$
 */
public abstract class JMeterAction extends javax.swing.AbstractAction
{

    public static final String ROLLOVER_ICON = "rollOverIcon";
    public static final String PRESSED_ICON = "pressedIcon";

    private String resourceKey;

    public JMeterAction(String resourceKey)
    {
        super(JMeterUtils.getResString(resourceKey));
        this.resourceKey = resourceKey;
        setProperties();
    }

    public JMeterAction(String resourceKey, int mnemonic)
    {
        this(resourceKey);
        putValue(MNEMONIC_KEY, new Integer(mnemonic));
        setProperties();
    }

    public JMeterAction(String resourceKey, int mnemonic, KeyStroke accelerator)
    {
        this(resourceKey, mnemonic);
        putValue(ACCELERATOR_KEY, accelerator);
        setProperties();
    }

    public String getResourceKey()
    {
        return resourceKey;
    }

    protected void setProperties()
    {
        ImageIcon icon = createIcon();

        if (icon != null)
        {
            putValue(Action.SMALL_ICON, icon);
        }

        icon = createRollOverIcon();
        if (icon != null)
        {
            putValue(ROLLOVER_ICON, icon);
        }

        icon = createPressedIcon();
        if (icon != null)
        {
            putValue(PRESSED_ICON, icon);
        }
        putValue(Action.SHORT_DESCRIPTION, getToolTipText());
    }

    protected ImageIcon createIcon()
    {
        return null;
    }

    protected ImageIcon createRollOverIcon()
    {
        return null;
    }

    protected ImageIcon createPressedIcon()
    {
        return null;
    }

    protected String getToolTipText() {
        return JMeterUtils.getResString(getResourceKey());
    }

}
