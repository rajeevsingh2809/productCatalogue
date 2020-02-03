package com.plethora.product.catalogue.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
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

public class GetAndGetAllTest {

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
	public void getAndGetAllTest() throws Exception{
		String uri="/api/v1/productcatalogue/getProducts";
		MockHttpServletRequestBuilder request= MockMvcRequestBuilders.get(uri);

		when(catalogueServiceIMPL.getProducts(null)).thenReturn(getProductStubData());

		ResultActions rest= mockMVC.perform(request);
		MvcResult mvcREsult= rest.andReturn();

		String result= mvcREsult.getResponse().getContentAsString();
		int actualStatus= mvcREsult.getResponse().getStatus();

		List<Product> listPrd= JSONUtils.covertFromJsonToObject(result, List.class);

		verify(catalogueServiceIMPL,times(1)).getProducts(null);

		assertTrue(listPrd!=null);
		assertTrue(actualStatus==HttpStatus.OK.value());

	}
	
	@Test
    public void getAllProductByIdTest() throws Exception{
    	  String uri="/api/v1/productcatalogue/getProductById/10";
    	  
    	  MockHttpServletRequestBuilder request= MockMvcRequestBuilders.get(uri);
    	  
    	  when(catalogueServiceIMPL.getProduct(10)).thenReturn( new Product(10, "Pants", "Grey", "M", 2990.90, "LP"));
    	  
    	  ResultActions rest= mockMVC.perform(request);
    	  
    	  MvcResult mvcREsult= rest.andReturn();
		  String result= mvcREsult.getResponse().getContentAsString();
		  //actual status and productType
		  int statusAct= mvcREsult.getResponse().getStatus();
		  Product product= JSONUtils.covertFromJsonToObject(result, Product.class);
		  
		  //expected status and productType
		  String expectedType="Pants";
		  int statusExp =200;
		  	  
		  verify(catalogueServiceIMPL,times(1)).getProduct(10);
  	  
    	  assertTrue(product.getProductType().equals(expectedType));
    	  assertTrue(statusAct==statusExp);
    }

	public List<Product> getProductStubData(){
		return Arrays.asList(new Product(9, "Pants", "Black", "M", 2890.90, "ZARA"),new Product(10, "Pants", "Grey", "M", 2990.90, "LP"),
				new Product(11, "Shorts", "White", "M", 1990.90, "FM"),new Product(12, "Shirt", "Yelloe", "M", 3990.90, "ZARA"));
	}

}