package org.example.elastic.config;

import java.net.InetSocketAddress;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.support.HttpHeaders;
import org.springframework.http.MediaType;

@Configuration
public class ElasticConfig extends ElasticsearchConfiguration {
    @Override
    public ClientConfiguration clientConfiguration() {
        final var headers = new HttpHeaders();
        headers.add("Authorization", "ApiKey Qi1EX1NaQUI1czF0cjdFOEdpTlM6elg4UmxORThSay03b1VUaXNGeS1LZw==");
//        headers.add("Authorization", "ApiKey SWl3SFNwQUJwZDNsVnlzYmZsUnE6TWEzbEdpS2NSMHVPUFAyeTZsdTR6QQ==");
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Accept", "application/json");
        return ClientConfiguration.builder()
            .connectedTo("e561ee0debf241148d894350f44d6d40.us-central1.gcp.cloud.es.io:443")
//            .connectedTo("dynamika7.es.europe-west1.gcp.cloud.es.io:443")
            .usingSsl(true)
            .withConnectTimeout(10000)
            .withDefaultHeaders(headers)
            .build();
    }
}
