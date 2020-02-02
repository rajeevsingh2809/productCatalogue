package com.plethora.product.catalogue.service;

import java.util.List;
import com.plethora.product.catalogue.model.Product;
import com.plethora.product.catalogue.model.ServiceResponse;

public interface CatalogueService {

	List<Product> getProducts(String productType);

	ServiceResponse addProduct(Product product);

	Product getProduct(Long productId);

	ServiceResponse updateProduct(Product product);

	ServiceResponse deleteProduct(Long productId);

}
