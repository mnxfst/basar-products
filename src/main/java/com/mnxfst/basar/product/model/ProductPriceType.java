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
 * Available price types, eg. MSRP (manufacturer suggested retail price) or selling price
 * @author mnxfst
 *
 */
public enum ProductPriceType implements Serializable {
	
	CAL, // base price to be used for line item calculations -- no charges or taxes are included
	OLD_CAL, // former CAL value
	MSRP // manufacturer suggested retail price (CAL of manufacturer) -- no charges or taxes are included	
	
}
