package com.moshahab.shopping_cart.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.moshahab.shopping_cart.exceptions.ProductNotFoundException;
import com.moshahab.shopping_cart.model.Category;
import com.moshahab.shopping_cart.model.Product;
import com.moshahab.shopping_cart.repository.CategoryRepository;
import com.moshahab.shopping_cart.repository.ProductRepository;
import com.moshahab.shopping_cart.request.AddProductRequest;
import com.moshahab.shopping_cart.request.UpdateProductRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProducts(AddProductRequest request) {
        // check if the category is found in the db
        // if yes, set it as the new product's category
        // if not, then save it as a new category
        // then set the new categ to the new product
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }

    // helper function to create the product before adding it in the db
    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not found!"));
    }

    @Override
    public void deleteProductById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException("Product Not Found!");
        }
    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long productId) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not found!"));
        return productRepository.save(updateExistingProduct(existingProduct, request));
    }

    private Product updateExistingProduct(Product existing, UpdateProductRequest request) {
        existing.setName(request.getName());
        existing.setBrand(request.getBrand());
        existing.setPrice(request.getPrice());
        existing.setInventory(request.getInventory());
        existing.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());

        existing.setCategory(category);
        return existing;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductByBrand(String brandName) {
        return productRepository.findByBrand(brandName);
    }

    @Override
    public List<Product> getProductByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

}
