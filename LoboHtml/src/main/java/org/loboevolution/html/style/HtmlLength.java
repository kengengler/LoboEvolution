/*
    GNU LESSER GENERAL PUBLIC LICENSE
    Copyright (C) 2006 The Lobo Project. Copyright (C) 2014 Lobo Evolution

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

    Contact info: lobochief@users.sourceforge.net; ivan.difrancesco@yahoo.it
*/
/*
 * Created on Nov 19, 2005
 */
package org.loboevolution.html.style;

public final class HtmlLength {
	public static final HtmlLength[] EMPTY_ARRAY = new HtmlLength[0];
	public static final int LENGTH = 2;
	public static final int MULTI_LENGTH = 0;

	// Note: Preferred type has higher value
	public static final int PIXELS = 1;

	private final int lengthType;
	private volatile int value;

	public HtmlLength(int pixels) {
		this.lengthType = PIXELS;
		this.value = pixels;
	}

	public HtmlLength(String spec) throws IndexOutOfBoundsException, NumberFormatException {
		spec = spec.trim();
		final int length = spec.length();
		final char lastChar = spec.charAt(length - 1);
		String parseable;
		if (lastChar == '%') {
			this.lengthType = LENGTH;
			parseable = spec.substring(0, length - 1).trim();
		} else if (lastChar == '*') {
			this.lengthType = MULTI_LENGTH;
			if (length <= 1) {
				parseable = "1";
			} else {
				parseable = spec.substring(0, length - 1).trim();
			}
		} else {
			this.lengthType = PIXELS;
			parseable = spec;
		}
		this.value = Integer.parseInt(parseable);
	}

	public void divideBy(int denominator) {
		int val = this.value;
		val = val / denominator;
		this.value = val;
	}

	public int getLength(int availLength) {
		final int lt = this.lengthType;
		if (lt == LENGTH) {
			return availLength * this.value / 100;
		} else {
			return this.value;
		}
	}

	/**
	 * @return Returns the lengthType.
	 */
	public int getLengthType() {
		return this.lengthType;
	}

	/**
	 * @return Returns the spec.
	 */
	public int getRawValue() {
		return this.value;
	}

	public boolean isPreferredOver(HtmlLength otherLength) {
		if (otherLength == null) {
			return true;
		}
		if (this.lengthType > otherLength.lengthType) {
			return true;
		}
		return this.value > otherLength.value;
	}
}