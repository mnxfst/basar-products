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

import java.util.UUID;

import akka.actor.UntypedActor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaworks.redis.RedisConnection;
import com.mnxfst.basar.product.actor.message.CreateProductMsg;
import com.mnxfst.basar.product.exception.DatabaseConnectionFailedException;
import com.mnxfst.basar.product.model.Product;

/**
 * Handles the creation of a cache entry for the contained {@link Product}. In case there exists another
 * element for the same identifier, the former element will be overridden.
 * @author mnxfst
 * @since 16.07.2013
 *
 * Revision Control Info $Id$
 */
public class CreateProductCacheElementActor extends UntypedActor {

	/** the product catalog uses redis as cache layer as it is extremely fast on write and (still) fast on read operations */
	private final RedisConnection<String, String> redisConnection;
	
	/** required factory for mapping product entities to their json representation */
	private final ObjectMapper productJSONMapper = new ObjectMapper();
	
	/**
	 * Initializes the instance using the provided information
	 * @param redisConnection
	 */
	public CreateProductCacheElementActor(final RedisConnection<String, String> redisConnection) {
		this.redisConnection = redisConnection;
	}
	
	// TODO implement error handler and shutdown handler
	
	/**
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	public void onReceive(Object message) throws Exception {

		// ensure that only messages are processed which request the creation of a product 
		if(message instanceof CreateProductMsg) {
			CreateProductMsg cpm = (CreateProductMsg)message;
			createProductCacheElement(cpm.getProduct());
		} else  {
			unhandled(message);
		}
	}
	
	/**
	 * Create cache element for provided {@link Product product} 
	 * @param product
	 * @return returns the provided product having the newly created identifier set
	 * @throws JsonProcessingException
	 */
	protected Product createProductCacheElement(Product product) throws JsonProcessingException, DatabaseConnectionFailedException {		
		if(product != null) {
			
			if(this.redisConnection == null)
				throw new DatabaseConnectionFailedException("Missing required database connection");
			
			product.setProductId(UUID.randomUUID().toString());
			this.redisConnection.set(product.getProductId(), productJSONMapper.writeValueAsString(product));
			return product;
		}		
		return null;
	}
}
