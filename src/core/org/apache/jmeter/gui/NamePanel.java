/*
 * ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2002 The Apache Software Foundation.  All rights
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
package org.apache.jmeter.gui;


import java.awt.FlowLayout;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import org.apache.jmeter.testelement.*;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jmeter.util.LocaleChangeEvent;
import org.apache.jmeter.gui.tree.JMeterTreeNode;


/****************************************
 * Title: JMeter Description: Copyright: Copyright (c) 2000 Company: Apache
 *
 * @author    Michael Stover
 * @author <a href="mailto:oliver@tuxerra.com">Oliver Rossmueller</a>
 * @created   $Date$
 * @version   1.0
 ***************************************/
public class NamePanel extends JPanel implements JMeterGUIComponent
{

    private JTextField nameField;
    private JLabel nameLabel;
    private NamedTestElement element;
    private boolean isConfigured = false;


    public NamePanel()
    {
        init();
    }


    public boolean isConfigured()
    {
        return isConfigured;
    }

    public void configure(TestElement testElement)
    {
        nameField.setText(((NamedTestElement)testElement).getName());
    }

    public JPopupMenu createPopupMenu(NamedTestElement testElement)
    {
        return null;
    }

    public String getStaticLabel()
    {
        return JMeterUtils.getResString("root");
    }

    public NamedTestElement createTestElement()
    {
        WorkBench wb = new WorkBench();
        wb.setProperty(NamedTestElement.NAME, getName());
        wb.setProperty(NamedTestElement.GUI_CLASS, this.getClass().getName());
        wb.setProperty(NamedTestElement.TEST_CLASS, WorkBench.class.getName());
        return wb;
    }


    private void init()
    {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        nameLabel = new JLabel(JMeterUtils.getResString("name"));
        nameLabel.setName("name");
        nameField = new JTextField(30);
        this.add(nameLabel);
        this.add(nameField);
        nameLabel.setLabelFor(nameField);
        nameField.setName("name");
        nameField.getDocument().addDocumentListener(new DocumentListener()
        {
            public void insertUpdate(DocumentEvent e)
            {
                if (isConfigured())
                {
                    updateName(nameField.getText());
                }
            }


            public void removeUpdate(DocumentEvent e)
            {
                if (isConfigured())
                {
                    updateName(nameField.getText());
                }
            }


            public void changedUpdate(DocumentEvent e)
            {
                // not for text fields
            }
        });
    }


    private void updateName(String newValue)
    {
        if (getElement() != null)
        {
            ((NamedTestElement)getElement()).setName(newValue);
        }
    }


    public void localeChanged(LocaleChangeEvent event)
    {
        nameLabel.setText(JMeterUtils.getResString(nameLabel.getName()));
    }

    public void setElement(TestElement element)
    {
        if (element instanceof NamedTestElement)
        {
            isConfigured = false;
            this.element = (NamedTestElement)element;
            configure(element);
            isConfigured = true;
        }
    }


    public TestElement getElement()
    {
        return element;
    }


    public Collection getMenuCategories()
    {
        return null;
    }
}
