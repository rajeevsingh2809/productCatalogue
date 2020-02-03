package com.plethora.product.catalogue.service;

import java.util.List;

import com.plethora.product.catalogue.bean.Product;

public interface CatalogueService {

	List<Product> getProducts(String productType);

	int addProduct(Product product);

	Product getProduct(int productId);

	Product updateProduct(Product product);

	Product deleteProduct(int productId);

	List<Product> getProductTypeAndCountOfProducts();

}
