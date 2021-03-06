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
package com.jtattoo.plaf.graphite;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.plaf.ComponentUI;

import com.jtattoo.plaf.AbstractLookAndFeel;
import com.jtattoo.plaf.BaseMenuUI;
import com.jtattoo.plaf.ColorHelper;
import com.jtattoo.plaf.JTattooUtilities;

/**
 * <p>GraphiteMenuUI class.</p>
 *
 * @author Michael Hagen
 * @version $Id: $Id
 */
public class GraphiteMenuUI extends BaseMenuUI {

	/** {@inheritDoc} */
	public static ComponentUI createUI(JComponent c) {
		return new GraphiteMenuUI();
	}

	/** {@inheritDoc} */
	@Override
	protected void paintBackground(Graphics g, JComponent c, int x, int y, int w, int h) {
		JMenuItem b = (JMenuItem) c;
		ButtonModel model = b.getModel();
		if (c.getParent() instanceof JMenuBar) {
			if (model.isRollover() || model.isArmed() || c instanceof JMenu && model.isSelected()) {
				JTattooUtilities.fillHorGradient(g, AbstractLookAndFeel.getTheme().getMenuSelectionColors(), x, y, w,
						h);
			}
			if (model.isRollover() && !model.isSelected()) {
				Color[] colArr = AbstractLookAndFeel.getTheme().getMenuSelectionColors();
				Color frameColor = ColorHelper.darker(colArr[colArr.length - 1], 5);
				g.setColor(frameColor);
				g.drawRect(x, y, w - 1, h - 1);
			}
		} else {
			if (model.isArmed() || c instanceof JMenu && model.isSelected()) {
				JTattooUtilities.fillHorGradient(g, AbstractLookAndFeel.getTheme().getMenuSelectionColors(), x, y, w,
						h);
			} else if (!AbstractLookAndFeel.getTheme().isMenuOpaque()) {
				Graphics2D g2D = (Graphics2D) g;
				Composite composite = g2D.getComposite();
				AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
						AbstractLookAndFeel.getTheme().getMenuAlpha());
				g2D.setComposite(alpha);
				g.setColor(AbstractLookAndFeel.getMenuBackgroundColor());
				g.fillRect(x, y, w, h);
				g2D.setComposite(composite);
			} else {
				g.setColor(AbstractLookAndFeel.getMenuBackgroundColor());
				g.fillRect(x, y, w, h);
			}
		}
		if (menuItem.isSelected() && menuItem.isArmed()) {
			g.setColor(AbstractLookAndFeel.getMenuSelectionForegroundColor());
		} else {
			g.setColor(AbstractLookAndFeel.getMenuForegroundColor());
		}
	}

} // end of class GraphiteMenuUI
