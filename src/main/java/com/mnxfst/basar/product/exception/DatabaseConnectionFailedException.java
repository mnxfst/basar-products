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
package com.mnxfst.basar.product.exception;

/**
 * Thrown in case the database connection is not present when trying
 * to read or write data
 * @author mnxfst
 * @since 23.07.2013
 *
 * Revision Control Info $Id$
 */
public class DatabaseConnectionFailedException extends Exception {

	private static final long serialVersionUID = -5948992901307330092L;
	
	public DatabaseConnectionFailedException() {
		super();
	}
	
	public DatabaseConnectionFailedException(String msg) {
		super(msg);
	}
	
	public DatabaseConnectionFailedException(Throwable cause) {
		super(cause);
	}
	
	public DatabaseConnectionFailedException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
