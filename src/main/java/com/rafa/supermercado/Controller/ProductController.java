package com.rafa.supermercado.Controller;

import com.rafa.supermercado.Dto.ProductDto;
import com.rafa.supermercado.Model.Product;
import com.rafa.supermercado.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class    ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productsDto) {
        ProductDto result = productService.save(productsDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public @ResponseBody ProductDto getProductById(@PathVariable Long id) {
        ProductDto productDto = productService.get(id);
        return productDto;
    }

    @PutMapping("/{id}")
    public @ResponseBody ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productUpdated) {
        ProductDto productDto = productService.update(id, productUpdated);
        return productDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
