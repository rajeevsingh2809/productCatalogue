package com.plethora.product.catalogue.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plethora.product.catalogue.model.Product;

@Transactional
public interface ProductCatalogueRepository extends JpaRepository<Product, String> {

	List<Product> findByProductType(String type);

	Product findByProductId(Long id);
	
	/*@Query("SELECT p.product_type FROM Product p WHERE p.product_type = :productType)")
	List<Product> groupByFilter(@Param("productType")String productType);*/
	
}
