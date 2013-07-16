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
package com.mnxfst.basar.product.actor.cfg;

import com.typesafe.config.Config;

import akka.actor.Extension;

/**
 * @author mnxfst
 * @since 16.07.2013
 *
 * Revision Control Info $Id$
 */
public class BasarSettingsImpl implements Extension {
	
	public final String PRODUCT_CACHE_HOST;
	public final int PRODUCT_CACHE_PORT;
	public final int NUM_CREATE_CONNECTIONS;
	public final int NUM_READ_CONNECTIONS;
	public final int NUM_UPDATE_CONNECTIONS;
	public final int NUM_DELETE_CONNECTIONS;
	
	public BasarSettingsImpl(Config config) {		
		PRODUCT_CACHE_HOST = config.getString("basar.product.cache.host");
		PRODUCT_CACHE_PORT = config.getInt("basar.product.cache.port");
		NUM_CREATE_CONNECTIONS = config.getInt("basar.product.cache.connections.create");
		NUM_READ_CONNECTIONS = config.getInt("basar.product.cache.connections.read");
		NUM_UPDATE_CONNECTIONS = config.getInt("basar.product.cache.connections.update");
		NUM_DELETE_CONNECTIONS = config.getInt("basar.product.cache.connections.delete");
	}

}
