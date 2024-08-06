package org.example.elastic.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ProductDto(
    String id,
    String name,
    Integer price,
    Integer inStock,
    LocalDateTime created,
    Map<String, Object> attributes
) {
}
