package com.rafa.supermercado.Service;

import com.rafa.supermercado.Dto.ProductDto;
import com.rafa.supermercado.Model.Product;
import com.rafa.supermercado.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDto save(ProductDto productDto) {
        Product product = dtoToProduct(productDto);
        Product savedProduct = productRepository.save(product);

        ProductDto productResult = productToDto(savedProduct);
        return productResult;
    }

    private static ProductDto productToDto(Product savedProduct) {
        ProductDto productResult = new ProductDto();
        productResult.setId(savedProduct.getId());
        productResult.setName(savedProduct.getName());
        productResult.setPrice(savedProduct.getPrice());
        return productResult;
    }

    private static Product dtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        return product;
    }

    public ProductDto get(Long id) {
        Optional<Product> resultRepository = productRepository.findById(id);
        if (resultRepository.isPresent()) {
            Product resultProduct = resultRepository.get();
            ProductDto resultProductDto = productToDto(resultProduct);
            return resultProductDto;
        } else {
            return null;
        }
    }

    public ProductDto update(Long id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = dtoToProduct(productDto);
            product.setId(id);
            Product savedProduct = productRepository.save(product);
            ProductDto resultProductDto = productToDto(savedProduct);
            return resultProductDto;
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
        }
    }
}
