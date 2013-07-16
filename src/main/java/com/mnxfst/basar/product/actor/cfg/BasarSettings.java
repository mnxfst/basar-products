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

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.ExtensionIdProvider;

/**
 * @author mnxfst
 * @since 16.07.2013
 *
 * Revision Control Info $Id$
 */
public class BasarSettings extends AbstractExtensionId<BasarSettingsImpl> implements ExtensionIdProvider {

	/** singleton access to settings */
	public final static BasarSettings SettingsProvider = new BasarSettings(); 
	
	/**
	 * Ensure that there is just one instance of this class
	 */
	private BasarSettings() {		
	}
	
	public BasarSettings lookup() {
		return BasarSettings.SettingsProvider;
	}
	
	public BasarSettingsImpl createExtension(ExtendedActorSystem system) {
		return new BasarSettingsImpl(system.settings().config());
	}
}
