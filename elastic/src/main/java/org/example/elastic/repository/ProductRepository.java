package org.example.elastic.repository;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.example.elastic.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    Optional<Product> findByName(String name);

    @Query("""
        {
            "bool": {
                "must": [
                    {
                        "term": {
                            "attributes.brandId.keyword": {
                                "value": "#{#brandId}"
                            }
                        }
                    },
                    {
                        "term": {
                            "attributes.storeId.keyword": {
                                "value": "#{#storeId}"
                            }
                        }
                    },
                    {
                         "range": {
                             "created": {
                                "gte": "#{#from}",
                                "lte": "#{#to}"
                                }
                             }
                        }
                ]
            }
        }
        """)
    Page<Product> findByBrandAndStore(UUID brandId,
                                      UUID storeId,
                                      String from,
                                      String to,
                                      Pageable pageable);
}
