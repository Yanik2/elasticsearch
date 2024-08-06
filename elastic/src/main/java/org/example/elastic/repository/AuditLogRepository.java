package org.example.elastic.repository;

import org.example.elastic.model.AuditLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AuditLogRepository extends ElasticsearchRepository<AuditLog, String> {
}
