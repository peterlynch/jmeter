/*
 * ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001-2002 The Apache Software Foundation.  All rights
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
package org.apache.jmeter.control.gui;


import java.awt.Font;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.apache.jmeter.gui.JMeterGUIComponent;
import org.apache.jmeter.gui.NamePanel;
import org.apache.jmeter.gui.tree.JMeterTreeNode;
import org.apache.jmeter.gui.util.MenuFactory;
import org.apache.jmeter.testelement.*;
import org.apache.jmeter.util.JMeterUtils;

import org.apache.jorphan.gui.layout.VerticalLayout;


/****************************************
 * Title: JMeter Description: Copyright: Copyright (c) 2000 Company: Apache
 *
 *@author    Kevin Hammond
 *@created   $Date$
 *@version   1.0
 ***************************************/

public class WorkBenchGui extends JPanel implements JMeterGUIComponent
{

    NamePanel namePanel;
    private JMeterTreeNode node;
    private boolean isConfigured = false;

    public WorkBenchGui()
    {
        namePanel = new NamePanel();
        setName(getStaticLabel());
        init();
    }

    public boolean isConfigured()
    {
        return isConfigured;
    }

    public Collection getMenuCategories()
    {
        return null;
    }



    public NamedTestElement createTestElement()
    {
        WorkBench wb = new WorkBench();
        wb.setProperty(NamedTestElement.NAME, namePanel.getName());
        wb.setProperty(NamedTestElement.GUI_CLASS, this.getClass().getName());
        wb.setProperty(NamedTestElement.TEST_CLASS, wb.getClass().getName());
        return wb;
    }

    protected void configure(TestElementConfiguration element)
    {
        namePanel.configure(element);
    }

    public JPopupMenu createPopupMenu()
    {
        JPopupMenu menu = new JPopupMenu();
        JMenu addMenu = MenuFactory.makeMenus(new String[]{MenuFactory.CONTROLLERS,
                                                           MenuFactory.SAMPLERS, MenuFactory.CONFIG_ELEMENTS,
                                                           MenuFactory.NON_TEST_ELEMENTS}, JMeterUtils.getResString("Add"),
                                              "Add");
        menu.add(addMenu);
        MenuFactory.addEditMenu(menu, false);
        MenuFactory.addFileMenu(menu);
        return menu;
    }

    public String getStaticLabel()
    {
        return JMeterUtils.getResString("workbench");
    }

    private void init()
    {
        this.setLayout(new VerticalLayout(5, VerticalLayout.LEFT, VerticalLayout.TOP));

        // MAIN PANEL
        JPanel mainPanel = new JPanel();
        Border margin = new EmptyBorder(10, 10, 5, 10);
        mainPanel.setBorder(margin);
        mainPanel.setLayout(new VerticalLayout(5, VerticalLayout.LEFT));

        // TITLE
        JLabel panelTitleLabel = new JLabel(JMeterUtils.getResString("workbench_title"));

        Font curFont = panelTitleLabel.getFont();
        int curFontSize = curFont.getSize();
        curFontSize += 4;
        panelTitleLabel.setFont(new Font(curFont.getFontName(), curFont.getStyle(), curFontSize));
        mainPanel.add(panelTitleLabel);

        // NAME
        mainPanel.add(namePanel);

        this.add(mainPanel);
    }


    public void setElement(TestElementConfiguration config)
    {
        isConfigured = false;
        namePanel.setElement(config);
        isConfigured = true;
    }


    public TestElementConfiguration getElement()
    {
        return namePanel.getElement();
    }
}
