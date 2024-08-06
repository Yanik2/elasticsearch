package org.example.elastic.controller;

import org.example.elastic.dto.ProductDto;
import org.example.elastic.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elastic")
public class ElasticController {
    private final ProductService productService;

    public ElasticController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> get(@PathVariable String id) {
        final var product = productService.getProductById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/template")
    public ResponseEntity<Void> getWithTemplate() {
        productService.getProductWithRestTemplate();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page")
    public ResponseEntity<Void> getPage() {
        final var res = productService.findAll();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/audit")
    public ResponseEntity<Void> getAudit() {
        final var res = productService.findAllAudit();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Void> getByName(@PathVariable String name) {
        final var product = productService.getProductByName(name);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pageable")
    public ResponseEntity<Void> getPageable() {
        productService.getPageable();
        return ResponseEntity.ok().build();
    }
}
