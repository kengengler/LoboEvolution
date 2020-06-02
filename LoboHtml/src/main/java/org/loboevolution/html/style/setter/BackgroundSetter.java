package org.loboevolution.html.style.setter;

import org.loboevolution.html.CSSValues;
import org.loboevolution.html.style.AbstractCSSProperties;
import org.loboevolution.html.style.HtmlValues;
import org.loboevolution.laf.ColorFactory;

import com.gargoylesoftware.css.dom.CSSStyleDeclarationImpl;

public class BackgroundSetter implements SubPropertySetter {
	
	public void changeValue(AbstractCSSProperties properties, String newValue, CSSStyleDeclarationImpl declaration) {
		this.changeValue(properties, newValue, declaration, true);
	}

	@Override
	public void changeValue(AbstractCSSProperties properties, String newValue, CSSStyleDeclarationImpl declaration,
			boolean important) {
		properties.setPropertyValueLCAlt(BACKGROUND, newValue, important);
		if (newValue != null && newValue.length() > 0) {
			final String[] tokens = HtmlValues.splitCssValue(newValue);
			boolean hasXPosition = false;
			boolean hasYPosition = false;
			String color = null;
			String image = null;
			String backgroundRepeat = null;
			String position = null;
			for (final String token : tokens) {
				if (ColorFactory.getInstance().isColor(token) || CSSValues.INITIAL.equals(CSSValues.get(token))) {
					color = token;
				} else if (HtmlValues.isUrl(token) || HtmlValues.isGradient(token)) {
					image = token;
				} else if (HtmlValues.isBackgroundRepeat(token)) {
					backgroundRepeat = token;
				} else if (HtmlValues.isBackgroundPosition(token)) {
					if (hasXPosition && !hasYPosition) {
						position += " " + token;
						hasYPosition = true;
					} else {
						hasXPosition = true;
						position = token;
					}
				}
			}
			if (color != null) {
				if(CSSValues.INITIAL.equals(CSSValues.get(color))){
					color = "white";
				}
				properties.setPropertyValueLCAlt(BACKGROUND_COLOR, color, important);
			}
			if (image != null) {
				properties.setPropertyValueProcessed(BACKGROUND_IMAGE, image, declaration, important);
			}
			if (backgroundRepeat != null) {
				properties.setPropertyValueLCAlt(BACKGROUND_REPEAT, backgroundRepeat, important);
			}
			if (position != null) {
				properties.setPropertyValueLCAlt(BACKGROUND_POSITION, position, important);
			}
		}
	}

}