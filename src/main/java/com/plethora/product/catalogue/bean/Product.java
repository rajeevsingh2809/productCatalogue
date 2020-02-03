package com.plethora.product.catalogue.bean;


/**
 * 
 * @author rajeevsingh
 *
 */
public class Product {
	private Integer productId;
	private String productType;
	private String colour;
	private String size;
	private double price;
	private String brand;


	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Product(Integer productId, String productType, String colour, String size, double price, String brand) {
		super();
		this.productId = productId;
		this.productType = productType;
		this.colour = colour;
		this.size = size;
		this.price = price;
		this.brand = brand;
	}


	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productType=" + productType + ", colour=" + colour + ", size="
				+ size + ", price=" + price + ", brand=" + brand + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productType == null) ? 0 : productType.hashCode());
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		result = prime * result
				+ ((brand == null) ? 0 : brand.hashCode());
		result = prime * result
				+ ((colour == null) ? 0 : colour.hashCode());
		result = prime * result
				+ ((size == null) ? 0 : size.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (colour == null) {
			if (other.colour != null)
				return false;
		} else if (!colour.equals(other.colour))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		return true;
	} 
}
