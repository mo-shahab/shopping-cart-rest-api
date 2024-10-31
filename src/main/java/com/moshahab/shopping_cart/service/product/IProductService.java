package com.moshahab.shopping_cart.service.product;

import java.util.List;

import com.moshahab.shopping_cart.model.Product;
import com.moshahab.shopping_cart.request.AddProductRequest;

public interface IProductService {
    Product addProducts(AddProductRequest product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    void deleteProductById(Long id);

    void updateProduct(Product product, Long productId);

    List<Product> getProductsByCategory(String category);

    List<Product> getProductByBrand(String brandName);

    List<Product> getProductByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);
}
