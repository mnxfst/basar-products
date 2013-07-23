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


import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.mnxfst.basar.product.exception.DatabaseConnectionFailedException;
import com.mnxfst.basar.product.model.Product;
import com.mnxfst.basar.product.model.ProductDelivery;
import com.mnxfst.basar.product.model.ProductDeliveryType;
import com.mnxfst.basar.product.model.ProductDescription;
import com.mnxfst.basar.product.model.ProductDescriptionType;
import com.mnxfst.basar.product.model.ProductExternalLinkType;
import com.mnxfst.basar.product.model.ProductPrice;
import com.mnxfst.basar.product.model.ProductPriceType;

/**
 * Test case for {@link CreateProductCacheElementActor}
 * @author mnxfst
 * @since 23.07.2013
 *
 * Revision Control Info $Id$
 */
public class CreateProductCacheElementActorTest {

	private final ObjectMapper productJSONMapper = new ObjectMapper();
	
	/**
	 * Provides null as parameter value to {@link CreateProductCacheElementActor(com.lambdaworks.redis.RedisConnection) constructor}
	 * and sends a valid product {@link Product product} entity towards {@link CreateProductCacheElementActor#createProductCacheElement(Product)}.
	 * The method should throw an {@link DatabaseConnectionFailedException} 
	 */
	@Test	
	public void testCreateProductCacheElementActorWithNullConstructorParam() throws Exception {		
		RedisClient redisClient = null;
		ActorSystem system = ActorSystem.create("junit");
		TestActorRef<CreateProductCacheElementActor> actorRef = TestActorRef.create(system, Props.create(CreateProductCacheElementActor.class, redisClient));
		try {
			actorRef.underlyingActor().createProductCacheElement(new Product());
			Assert.fail("Missing required database connection");
		} catch(DatabaseConnectionFailedException e) {
			//
		}
	}

	/**
	 * Test case for {@link CreateProductCacheElementActor#createProductCacheElement(Product)} being provided null as input
	 */
	@Test	
	@SuppressWarnings("unchecked")
	public void testCreateProductCacheElementWithNullInput() throws Exception {
		RedisConnection<String, String> mockedRedisClientConnection = Mockito.mock(RedisConnection.class);
		ActorSystem system = ActorSystem.create("junit");
		TestActorRef<CreateProductCacheElementActor> actorRef = TestActorRef.create(system, Props.create(CreateProductCacheElementActor.class, mockedRedisClientConnection));
		Product result = actorRef.underlyingActor().createProductCacheElement(null);
		Assert.assertNull("The returned product must be null", result);		
	}

	/**
	 * Test case for {@link CreateProductCacheElementActor#createProductCacheElement(Product)} being provided a valid product entity
	 */
	@Test	
	@SuppressWarnings("unchecked")
	public void testCreateProductCacheElementWithValidInput() throws Exception {
		RedisConnection<String, String> mockedRedisClientConnection = Mockito.mock(RedisConnection.class);
		ActorSystem system = ActorSystem.create("junit");
		TestActorRef<CreateProductCacheElementActor> actorRef = TestActorRef.create(system, Props.create(CreateProductCacheElementActor.class, mockedRedisClientConnection));
		Product input = new Product();
		input.setAgeLimit(18);
		input.setBrand("test-brand");
		input.addColor("red");
		input.addColor("green");
		input.setCountryOfOrigin("Germany");
		input.setCreated(new Date());
		input.setDefaultOrderQuantity(Integer.valueOf(10));
		input.addDefaultPrice(new ProductPrice(ProductPriceType.MSRP, 1.23, Currency.getInstance(Locale.getDefault())));
		input.addDeliveryCondition(new ProductDelivery(ProductDeliveryType.EXPRESS, "express delivery", 1.44, "test-carrier", "DE"));
		input.addDescription(new ProductDescription(ProductDescriptionType.SHORT, "short-description", "DE"));
		input.addExternalLink(ProductExternalLinkType.PRODUCT_DOC, "test-doc-url");
		input.setFirstOrderingDate(new Date());
		input.setGtin("gtin:1234");
		Product result = actorRef.underlyingActor().createProductCacheElement(input);
		
		Mockito.verify(mockedRedisClientConnection, Mockito.atLeast(1)).set(result.getProductId(), productJSONMapper.writeValueAsString(input));
		Mockito.verify(mockedRedisClientConnection, Mockito.atMost(1)).set(result.getProductId(), productJSONMapper.writeValueAsString(input));		
	}
	
}
