package org.example.elastic.model;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = ".ds-metrics-apm.app.d7_api_dev-default-2024.04.24-000001")
public class AuditLog {
    @Id
    private String id;

    public AuditLog(String id) {
        this.id = id;
    }

    public AuditLog() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuditLog auditLog)) {
            return false;
        }
        return Objects.equals(id, auditLog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
