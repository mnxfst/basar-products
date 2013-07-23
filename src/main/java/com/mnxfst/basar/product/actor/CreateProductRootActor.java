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
package com.mnxfst.basar.product.actor;

import com.mnxfst.basar.product.actor.message.CreateProductMsg;
import com.mnxfst.basar.product.actor.persistence.CreateProductPersistenceRootActor;
import com.mnxfst.basar.product.model.Product;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Provides the root entry point for {@link CreateProductMsg product creation} requests. The incoming
 * messages are forwarded to the validating actor first before they are assigned to the hierarchy of 
 * persisting actors. These will take care of writing the {@link Product product} entity to cache as 
 * well as to the persistent datastore. 
 * @author mnxfst
 * @since 16.07.2013
 *
 * Revision Control Info $Id$
 */
public class CreateProductRootActor extends UntypedActor {

	private final ActorRef createProductPersistenceRootActorRef;
	
	public CreateProductRootActor() {
		this.createProductPersistenceRootActorRef = context().actorOf(Props.create(CreateProductPersistenceRootActor.class));		
	}
	
	/**
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	public void onReceive(Object message) throws Exception {
		
		if(message instanceof CreateProductMsg) {
			this.createProductPersistenceRootActorRef.tell(message, getSelf());
		}
		
	}

}
