package com.plethora.product.catalogue.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plethora.product.catalogue.entity.ProductEntity;

@Transactional
public interface ProductCatalogueRepository extends JpaRepository<ProductEntity, String> {

	List<ProductEntity> findByProductType(String type);

	ProductEntity findByProductId(Integer id);
	
	@Query("select count(k),k.productType from ProductEntity k group by k.productType") 
	List getProductTypeAndCountOfProducts();

	@Query("select k from ProductEntity k where k.price>=:price1 and k.price=:price2")
	List<ProductEntity> getAllProductsByPrice(@Param("price1")Double price1, @Param("price2")Double price2);
	
}
