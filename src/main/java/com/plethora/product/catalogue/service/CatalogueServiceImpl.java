package com.plethora.product.catalogue.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plethora.product.catalogue.bean.Product;
import com.plethora.product.catalogue.entity.ProductEntity;
import com.plethora.product.catalogue.exception.ProductNotFoundException;
import com.plethora.product.catalogue.repository.ProductCatalogueRepository;

@Service("catalogueService")
public class CatalogueServiceImpl implements CatalogueService{

	@Autowired
	ProductCatalogueRepository productCatalogueRepository;


	@Override
	public List<Product> getProducts(String type) {
		List<ProductEntity> productEntities;
		List<Product> listProduct = new ArrayList<Product> ();
		if (type != null){
			productEntities  = productCatalogueRepository.findByProductType(type);
		}else{
			productEntities = productCatalogueRepository.findAll();
		}

		if(productEntities == null || productEntities.isEmpty())
			throw new ProductNotFoundException("No products found!");
		else {
			for (ProductEntity productEntity : productEntities) {
				Product product=new Product();
				BeanUtils.copyProperties(productEntity, product);
				listProduct.add(product);
			}
		}
		return listProduct;
	}

	@Override
	public int addProduct(Product product) {
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(product, productEntity);
		ProductEntity prd= (ProductEntity)productCatalogueRepository.save(productEntity);
		return prd.getProductId();
	}

	@Override
	public Product getProduct(int id) {
		Product product = null;
		ProductEntity productEntity  = productCatalogueRepository.findByProductId(id);
		if(productEntity == null) {
			throw new ProductNotFoundException("Invalid product id!");
		} else {
			product = new Product();
			BeanUtils.copyProperties(productEntity, product);
		}
		return product;
	}

	@Override
	public Product updateProduct(Product product) {
		Product product2 =null;
		ProductEntity productEntity= productCatalogueRepository.findByProductId(product.getProductId());
		if(productEntity!=null){
			//update operation
			BeanUtils.copyProperties(product, productEntity);	
			productCatalogueRepository.save(productEntity);
			
			product2= new Product();
			BeanUtils.copyProperties(productEntity, product2);
		} else {
			throw new ProductNotFoundException("Invalid product id!");
		}
		return product2;
	}

	@Override
	public Product deleteProduct(int id) {
		Product product =null;
		ProductEntity productEntity= productCatalogueRepository.findByProductId(id);
		if(productEntity!=null){
			productCatalogueRepository.delete(productEntity);
			product= new Product();
			BeanUtils.copyProperties(productEntity, product);
		} else {
			throw new ProductNotFoundException("product id not found!");
		}
		return product;
	}
	
	@Override
	public List<Product> getProductTypeAndCountOfProducts(){
		List<Product> listProduct = new ArrayList<Product> ();
		listProduct  = productCatalogueRepository.getProductTypeAndCountOfProducts();
		if(listProduct == null || listProduct.isEmpty())
			throw new ProductNotFoundException("No products found!");
		return listProduct;
	}
}
