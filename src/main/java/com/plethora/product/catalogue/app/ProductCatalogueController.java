package com.plethora.product.catalogue.app;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.plethora.product.catalogue.model.Product;
import com.plethora.product.catalogue.model.ServiceResponse;
import com.plethora.product.catalogue.service.CatalogueService;

@RestController
@RequestMapping("/api/v1/productcatalogue")
public class ProductCatalogueController {

	@Autowired
	@Qualifier("catalogueService")
	CatalogueService catalogueService;

	@GetMapping
	public ResponseEntity<List<Product>> getProducts(@RequestParam(value="type", required=false) String type){
		List<Product> products = catalogueService.getProducts(type);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@GetMapping("/{type}/{filter}")
	public ResponseEntity<List<Product>> getProductsWithGroup(@PathVariable(value="type", required=false) String type, @PathVariable(value="filter", required=false) String filter){
		List<Product> products = catalogueService.getProducts(type);
		List<Product> productsFilter = products.stream().filter(t ->t.getBrand().equalsIgnoreCase(filter)).collect(Collectors.toList());
		return new ResponseEntity<>(productsFilter, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ServiceResponse> addProduct(@RequestBody Product product){
		ServiceResponse serviceResponse = catalogueService.addProduct(product);
		return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id){
		Product product = catalogueService.getProduct(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}


	@PutMapping
	public ResponseEntity<ServiceResponse> updateProduct(@RequestBody Product product){
		ServiceResponse serviceResponse = catalogueService.updateProduct(product);
		return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ServiceResponse> deleteProduct(@PathVariable Long id){
		ServiceResponse serviceResponse = catalogueService.deleteProduct(id);
		return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
	}


}
