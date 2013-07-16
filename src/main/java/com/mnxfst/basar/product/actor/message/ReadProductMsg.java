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
package com.mnxfst.basar.product.actor.message;

import java.io.Serializable;

import com.mnxfst.basar.product.model.Product;

/**
 * Represents a message issued by a caller that wishes to read the referenced {@link Product product}
 * @author mnxfst
 * @since 16.07.2013
 *
 * Revision Control Info $Id$
 */
public class ReadProductMsg implements Serializable {

	private static final long serialVersionUID = 7920050950584188360L;

}
