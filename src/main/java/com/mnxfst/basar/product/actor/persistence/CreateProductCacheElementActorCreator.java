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

import akka.japi.Creator;

import com.lambdaworks.redis.RedisClient;

/**
 * Creates new instances of type {@link CreateProductCacheElementActor} and assigns them a new {@link RedisClient client} connection with the redis database
 * @author mnxfst
 * @since 16.07.2013
 *
 * Revision Control Info $Id$
 */
public class CreateProductCacheElementActorCreator implements Creator<CreateProductCacheElementActor> {

	private static final long serialVersionUID = 1002659006280169784L;

	private final RedisClient redisClient;
	
	public CreateProductCacheElementActorCreator(final RedisClient redisClient) {
		this.redisClient = redisClient;
	}
	
	/**
	 * @see akka.japi.Creator#create()
	 */
	public CreateProductCacheElementActor create() throws Exception {
		return new CreateProductCacheElementActor(this.redisClient.connect());
	}

}
