package com.plethora.product.catalogue.app;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.plethora.product.catalogue.bean.Product;
import com.plethora.product.catalogue.service.CatalogueService;

@RestController
@RequestMapping("/api/v1/productcatalogue")
public class ProductCatalogueController {

	@Autowired
	@Qualifier("catalogueService")
	CatalogueService catalogueService;

	@RequestMapping(value="/getProducts",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getProducts(@RequestParam(value="type", required=false) String type){
		List<Product> products = catalogueService.getProducts(type);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getProductsWithBrand/{type}/{filter}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getProductsWithBrand(@PathVariable(value="type", required=false) String type, @PathVariable(value="filter", required=false) String filter){
		List<Product> products = catalogueService.getProducts(type);
		List<Product> productsFilter = products.stream().filter(t ->t.getBrand().equalsIgnoreCase(filter)).collect(Collectors.toList());
		return new ResponseEntity<>(productsFilter, HttpStatus.OK);
	}

	@RequestMapping(value="/addProduct",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> addProduct(@RequestBody Product product){
		int productId = catalogueService.addProduct(product);
		return new ResponseEntity<String>("Product added successfully with Productid:"+productId,HttpStatus.OK);
	}

	@RequestMapping(value="/getProductById/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProduct(@PathVariable int id){
		Product product = catalogueService.getProduct(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}


	@RequestMapping(value="/updateProduct",
			method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		Product product2= catalogueService.updateProduct(product);
		if(product2==null){
			return new ResponseEntity<Product>(product2,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Product>(product2,HttpStatus.OK);
	}

	@RequestMapping(value="/deleteProduct/{id}",
	method=RequestMethod.DELETE,
	produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> deleteProduct(@PathVariable int id){
		Product product= catalogueService.deleteProduct(id);
		if(product==null){
			return new ResponseEntity<Product>(product,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getProductTypeCount",method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getProductTypeAndCountOfProducts(){
		List<Product> listProduct = catalogueService.getProductTypeAndCountOfProducts();
		return new ResponseEntity<>(listProduct, HttpStatus.OK);
	}


}
