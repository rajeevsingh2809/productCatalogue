package com.plethora.product.catalogue.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plethora.product.catalogue.exception.ProductNotFoundException;
import com.plethora.product.catalogue.model.Product;
import com.plethora.product.catalogue.model.ServiceResponse;
import com.plethora.product.catalogue.repository.ProductCatalogueRepository;

@Service("catalogueService")
public class CatalogueServiceImpl implements CatalogueService{

	@Autowired
	ProductCatalogueRepository productCatalogueRepository;


	@Override
	public List<Product> getProducts(String type) {
		List<Product> products;
		if (type != null){
			products  = productCatalogueRepository.findByProductType(type);
		}else{
			products = productCatalogueRepository.findAll();
		}

		if(products == null || products.isEmpty())
			throw new ProductNotFoundException("No products found!");

		return products;
	}

	@Override
	public ServiceResponse addProduct(Product product) {
		productCatalogueRepository.save(product);
		return new ServiceResponse("Successful","Product added successfully");
	}

	@Override
	public Product getProduct(Long id) {
		Product product  = productCatalogueRepository.findByProductId(id);
		if(product == null)
			throw new ProductNotFoundException("Invalid product id!");

		return product;
	}

	@Override
	public ServiceResponse updateProduct(Product product) {
		productCatalogueRepository.save(product);
		return new ServiceResponse("Successful","Product updated successfully");
	}

	@Override
	public ServiceResponse deleteProduct(Long id) {
		Product product  = productCatalogueRepository.findByProductId(id);
		if(product != null)
			productCatalogueRepository.delete(product);
		else
			throw new ProductNotFoundException("Invalid product id!");
		return new ServiceResponse("Successful","Product deleted successfully");
	}

}
