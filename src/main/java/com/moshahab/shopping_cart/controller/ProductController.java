package com.moshahab.shopping_cart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moshahab.shopping_cart.dto.ProductDto;
import com.moshahab.shopping_cart.exceptions.AlreadyExistsException;
import com.moshahab.shopping_cart.exceptions.ResourceNotFoundException;
import com.moshahab.shopping_cart.model.Product;
import com.moshahab.shopping_cart.request.AddProductRequest;
import com.moshahab.shopping_cart.request.UpdateProductRequest;
import com.moshahab.shopping_cart.response.ApiResponse;
import com.moshahab.shopping_cart.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${apiPrefix}")
public class ProductController {

    private final IProductService productService;

    @GetMapping("/product/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Success!", convertedProducts));
    }

    @GetMapping("/products/product/{id}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            ProductDto productDto = productService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("Success", productDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("product/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        try {
            Product theProduct = productService.addProducts(product);
            return ResponseEntity.ok(new ApiResponse("Add Product Success", theProduct));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/product/{id}/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateProductRequest product, @PathVariable Long id) {
        try {
            Product theProduct = productService.updateProduct(product, id);
            return ResponseEntity.ok(new ApiResponse("Update Product Success", theProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/product/{id}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok(new ApiResponse("Delete Product Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName,
            @RequestParam String productName) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brandName, productName);

            if (products.isEmpty()) {
                return ResponseEntity.status(500).body(new ApiResponse("Not found", null));
            }
            List<ProductDto> productDtos = productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Get product by name and brand name", productDtos));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(@RequestParam String brandName,
            @RequestParam String category) {
        try {
            List<Product> products = productService.getProductByCategoryAndBrand(category, brandName);
            if (products.isEmpty()) {
                return ResponseEntity.status(500).body(new ApiResponse("Not found", null));
            }
            List<ProductDto> productDtos = productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Get product by name and brand name", productDtos));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/{name}/products")
    public ResponseEntity<ApiResponse> getProductsByName(@PathVariable String name) {
        try {
            List<Product> products = productService.getProductsByName(name);
            if (products.isEmpty()) {
                return ResponseEntity.status(500).body(new ApiResponse("Not found", null));
            }
            List<ProductDto> productDtos = productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Get product by name and brand name", productDtos));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/by-brand")
    public ResponseEntity<ApiResponse> getProductsByBrand(@RequestParam String name) {
        try {
            List<Product> products = productService.getProductByBrand(name);
            if (products.isEmpty()) {
                return ResponseEntity.status(500).body(new ApiResponse("Not found", null));
            }
            List<ProductDto> productDtos = productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Get product by name and brand name", productDtos));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/{category}/all/products")
    public ResponseEntity<ApiResponse> getAllProductsByCategory(@PathVariable String category) {
        try {
            List<Product> products = productService.getProductsByCategory(category);
            if (products.isEmpty()) {
                return ResponseEntity.status(500).body(new ApiResponse("Not found", null));
            }
            List<ProductDto> productDtos = productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Get product by name and brand name", productDtos));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/count/by/brand-and-name")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand,
            @RequestParam String name) {
        try {
            var productCount = productService.countProductsByBrandAndName(brand, name);
            return ResponseEntity.ok(new ApiResponse("Product Count", productCount));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
