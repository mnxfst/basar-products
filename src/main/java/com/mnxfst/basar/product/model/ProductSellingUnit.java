/**
 * basar - enhanced electronic marketplace
 * Copyright (C) 2013 Christian Kreutzfeldt
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mnxfst.basar.product.model;

import java.io.Serializable;

/**
 * Selling units of a product
 * @author mnxfst
 * @since 09.04.2013
 *
 * Revision Control Info $Id$
 */
public enum ProductSellingUnit implements Serializable {

	GCBC, // gram per cubic centimeter used for gas density
	GSQM, // gram per square meter
	KGSQM, // kg per square meter
	OCSFT, // ounce per square foot
	PPMIO, // part per million
	PPSQI, // pound per square inch 
	BARL, // barrel	
	DEZ, // decible
	MPM, // meter per minute
	MA, // milli-ampere
	MB, // megabyte
	GHZ, // giga hertz
	BIT, // bit 
	ACR, // acre
	BYTE, // byte
	ANN, // year
	ASM, // alcoholic strength by mass
	ASU, // alcoholic strength by volume
	BAR, // bar
	YDK, // square yard
	YRD, // yard
	ZE, // milliampere hour
	ZP // page
	
}
