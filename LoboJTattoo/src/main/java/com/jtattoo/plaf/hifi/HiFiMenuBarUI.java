/*
* Copyright (c) 2002 and later by MH Software-Entwicklung. All Rights Reserved.
*
* JTattoo is multiple licensed. If your are an open source developer you can use
* it under the terms and conditions of the GNU General Public License version 2.0
* or later as published by the Free Software Foundation.
*
* see: gpl-2.0.txt
*
* If you pay for a license you will become a registered user who could use the
* software under the terms and conditions of the GNU Lesser General Public License
* version 2.0 or later with classpath exception as published by the Free Software
* Foundation.
*
* see: lgpl-2.0.txt
* see: classpath-exception.txt
*
* Registered users could also use JTattoo under the terms and conditions of the
* Apache License, Version 2.0 as published by the Apache Software Foundation.
*
* see: APACHE-LICENSE-2.0.txt
 */
package com.jtattoo.plaf.hifi;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuBarUI;

import com.jtattoo.plaf.BaseBorders;

/**
 * @author Michael Hagen
 */
public class HiFiMenuBarUI extends BasicMenuBarUI {

	public static ComponentUI createUI(JComponent c) {
		return new HiFiMenuBarUI();
	}

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		if (c != null && c instanceof JMenuBar) {
			((JMenuBar) c).setBorder(BaseBorders.getMenuBarBorder());
		}
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		HiFiUtils.fillComponent(g, c);
	}

} // end of class HiFiMenuBarUI