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
import com.plethora.product.catalogue.custom.utils.JSONUtils;
import com.plethora.product.catalogue.service.CatalogueServiceImpl;


@SpringBootTest(classes=ProductCatalogueApplication.class)

public class AddProductTest {

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
	public void addProductTest() throws Exception{
		String uri="/api/v1/productcatalogue/addProduct";
		Product product = new Product(0, "Pants", "Black", "M", 290.90, "ZARA");
		String employeeJsonFormat =JSONUtils.covertFromObjectToJson(product);
		MockHttpServletRequestBuilder request= MockMvcRequestBuilders.post(uri)
				.accept(MediaType.TEXT_HTML) 
				.content(employeeJsonFormat) 
				.contentType(MediaType.APPLICATION_JSON);

		when(catalogueServiceIMPL.addProduct(product)).thenReturn(9); //4545

		ResultActions rest= mockMVC.perform(request);

		MvcResult mvcREsult= rest.andReturn();

		String finalResult= mvcREsult.getResponse().getContentAsString();
		int actualStatus = mvcREsult.getResponse().getStatus();

		verify(catalogueServiceIMPL,times(1)).addProduct(product); 
		assertTrue(finalResult!=null);
		assertTrue(finalResult.contains("9"));
		assertTrue(actualStatus==HttpStatus.OK.value());

	}

}