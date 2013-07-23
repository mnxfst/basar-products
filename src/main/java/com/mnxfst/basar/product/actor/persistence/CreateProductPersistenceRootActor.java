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
package com.mnxfst.basar.product.actor.persistence;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;

import com.lambdaworks.redis.RedisClient;
import com.mnxfst.basar.product.actor.cfg.BasarSettings;
import com.mnxfst.basar.product.actor.cfg.BasarSettingsImpl;
import com.mnxfst.basar.product.actor.message.CreateProductMsg;
import com.mnxfst.basar.product.model.Product;

/**
 * Provides the root entry point to all actors which persist {@link Product products} in
 * one or another way (persistent datastore, cache) 
 * @author mnxfst
 * @since 16.07.2013
 *
 * Revision Control Info $Id$
 */
public class CreateProductPersistenceRootActor extends UntypedActor {

	/** client required for accessing redis database instance */
	private final RedisClient redisClient;
	
	private final ActorRef createProductCacheElementActorRef;

	/**
	 * Initializes the actor instance
	 */
	public CreateProductPersistenceRootActor() {
		final BasarSettingsImpl settings = BasarSettings.SettingsProvider.get(getContext().system());		
		this.redisClient = new RedisClient(settings.PRODUCT_CACHE_HOST, settings.PRODUCT_CACHE_PORT);
		this.createProductCacheElementActorRef = context().actorOf(Props.create(new CreateProductCacheElementActorCreator(redisClient)).withRouter(new RoundRobinRouter(settings.NUM_CREATE_CONNECTIONS)));
	}
	
	/**
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	public void onReceive(Object message) throws Exception {
		
		if(message instanceof CreateProductMsg) {		
			this.createProductCacheElementActorRef.tell(message, getSelf());
		}
		
	}

}
