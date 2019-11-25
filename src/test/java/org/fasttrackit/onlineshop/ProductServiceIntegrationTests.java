package org.fasttrackit.onlineshop;

import org.fasttrackit.onlineshop.domain.Product;
import org.fasttrackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshop.service.ProductService;
import org.fasttrackit.onlineshop.transfer.SaveProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceIntegrationTests {

	@Autowired      //pt teste se pune pe variabila
	private ProductService productService;


	@Test
	public void testCreateProduct_whenValidRequest_thenProductIsSaved() {   //auto test
		createProduct();
	}



	@Test(expected = TransactionSystemException.class)
	public void testCreateProduct_whenInvalidRequest_thenThrowException(){
		SaveProductRequest request = new SaveProductRequest();
		//leaving request properties with default null values
		//to validate the negative flow

		productService.createProduct(request);
	}

	@Test
	private void testGetProduct_whenExistingProduct_thenReturnProduct(){
		Product createdProduct = createProduct();

		Product retrievedProduct = productService.getProduct(createdProduct.getId());

		assertThat(retrievedProduct, notNullValue());
		assertThat(retrievedProduct.getId(), is(createdProduct.getId()));
		assertThat(retrievedProduct.getName(), is(createdProduct.getName()));
		assertThat(retrievedProduct.getPrice(), is(createdProduct.getPrice()));
		assertThat(retrievedProduct.getQuantity(), is(createdProduct.getQuantity()));
		assertThat(retrievedProduct.getDescription(), is(createdProduct.getDescription()));

	}

	@Test(expected = ResourceNotFoundException.class)
	public void testGetProduct_whenNonExistingProduct_thenThrowResourceNotFoundException(){

		productService.getProduct(9999999999999L);


	}

	private Product createProduct() {
		SaveProductRequest request = new SaveProductRequest();
		request.setName("Banana " + System.currentTimeMillis());
		request.setPrice(5.0);
		request.setQuantity(100);
		request.setDescription("Healthy food");


		Product createdProduct = productService.createProduct(request);

		assertThat(createdProduct, notNullValue());
		assertThat(createdProduct.getId(), notNullValue());
		assertThat(createdProduct.getId(), greaterThan(0L));
		assertThat(createdProduct.getName(), is(request.getName()));
		assertThat(createdProduct.getPrice(), is(request.getPrice()));
		assertThat(createdProduct.getQuantity(), is(request.getQuantity()));
		assertThat(createdProduct.getDescription(), is(request.getDescription()));

		return createdProduct;
	}
}
