package org.example.elastic.service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.example.elastic.dto.ProductDto;
import org.example.elastic.model.AuditLog;
import org.example.elastic.model.Product;
import org.example.elastic.model.ProductDetails;
import org.example.elastic.repository.AuditLogRepository;
import org.example.elastic.repository.ProductRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.support.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final AuditLogRepository auditLogRepository;
    private final RestTemplate restTemplate;

    public ProductService(ProductRepository productRepository,
                          AuditLogRepository auditLogRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.auditLogRepository = auditLogRepository;
        this.restTemplate = restTemplate;
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).get();
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name).get();
    }

    public void getProductWithRestTemplate() {
        final var headers = new HttpHeaders();
        headers.add("Authorization", "ApiKey Qi1EX1NaQUI1czF0cjdFOEdpTlM6elg4UmxORThSay03b1VUaXNGeS1LZw==");
        final var httpEntity = new HttpEntity<>(headers);
        final var result = restTemplate.exchange("https://e561ee0debf241148d894350f44d6d40.us-central1.gcp.cloud.es.io:443/products/_doc/100",
            HttpMethod.GET, httpEntity, String.class);
        System.out.println(result);
    }

    public List<Product> findAll() {
        final var page = Pageable.ofSize(2);
        final var res = productRepository.findAll(page).stream().toList();
        return res;
    }

    public List<AuditLog> findAllAudit() {
        final var page = Pageable.ofSize(1);
        final var res = auditLogRepository.findAll(page).stream().toList();
        return res;
    }

    public void createProduct(ProductDto productDto) {
        final var product = new Product(
            productDto.id(),
            productDto.name(),
            productDto.price(),
            productDto.inStock(),
            productDto.created(),
            productDto.attributes()
        );

        productRepository.save(product);
    }

    public void getPageable() {
        final var brandId = UUID.fromString("c70e454b-da18-4975-aaad-32639693abed");
        final var storeId = UUID.fromString("591a779e-baa3-445d-a354-a7d5f3bd6bd1");
//        final var from = LocalDateTime.of(2024, 6, 24, 0, 0);
//        final var to = LocalDateTime.of(2024, 6, 28, 0, 0);
        final var from = "2024-06-24T00:00:00";
        final var to = "2024-06-28T00:00:00";
        final var pageable = PageRequest.of(0, 1, Sort.by("name"));
        final var result = productRepository.findByBrandAndStore(brandId, storeId, from, to, pageable);
        System.out.println(result);
    }
}
