package com.plethora.product.catalogue.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.plethora.product.catalogue.ProductCatalogueApplication;
import com.plethora.product.catalogue.app.ProductCatalogueController;
import com.plethora.product.catalogue.bean.Product;
import com.plethora.product.catalogue.service.CatalogueServiceImpl;


@SpringBootTest(classes=ProductCatalogueApplication.class)

public class DeleteProductTest {

	@Mock
	CatalogueServiceImpl catalogueServiceIMPL;

	@InjectMocks
	ProductCatalogueController controller;

	protected MockMvc mockMVC;

	@BeforeEach
	public void mySetup(){

		MockitoAnnotations.initMocks(this); 
		mockMVC = MockMvcBuilders.standaloneSetup(controller).build();
	}


	@Test
	public void deleteProductTest() throws Exception{
		String uri="/api/v1/productcatalogue/deleteProduct/{id}";
		MockHttpServletRequestBuilder request= MockMvcRequestBuilders.delete(uri,2)
				.accept(MediaType.APPLICATION_JSON);
		
		Product product = new Product(2, "Trousers", "Black", "M", 2990.90, "ZARA");
		when(catalogueServiceIMPL.deleteProduct(2)).thenReturn(product);

		ResultActions rest= mockMVC.perform(request);	 
		MvcResult mvcREsult= rest.andReturn();

		String finalResult= mvcREsult.getResponse().getContentAsString();
		int actualStatus = mvcREsult.getResponse().getStatus();

		verify(catalogueServiceIMPL,times(1)).deleteProduct(2); 
		assertTrue(finalResult!=null);
		assertTrue(actualStatus==HttpStatus.OK.value());
	}

	@Test
	public void deleteInvalidProductTest() throws Exception{
		String uri="/api/v1/productcatalogue/deleteProduct/{id}";
		MockHttpServletRequestBuilder request= MockMvcRequestBuilders.delete(uri,2000)
				.accept(MediaType.APPLICATION_JSON);

		when(catalogueServiceIMPL.deleteProduct(2000)).thenReturn(null);

		ResultActions rest= mockMVC.perform(request);	 
		MvcResult mvcREsult= rest.andReturn();

		String finalResult= mvcREsult.getResponse().getContentAsString();
		int actualStatus = mvcREsult.getResponse().getStatus();

		verify(catalogueServiceIMPL,times(1)).deleteProduct(2000); 
		assertTrue(finalResult!=null);
		assertTrue(finalResult.length()==0);
		assertTrue(actualStatus==HttpStatus.INTERNAL_SERVER_ERROR.value());

	}
}
