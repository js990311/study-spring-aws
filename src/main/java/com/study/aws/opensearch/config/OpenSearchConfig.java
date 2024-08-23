package com.study.aws.opensearch.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.data.client.orhlc.AbstractOpenSearchConfiguration;
import org.opensearch.data.client.orhlc.ClientConfiguration;
import org.opensearch.data.client.orhlc.OpenSearchRestTemplate;
import org.opensearch.data.client.orhlc.RestClients;
import org.opensearch.data.core.OpenSearchOperations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@Configuration
public class OpenSearchConfig extends AbstractOpenSearchConfiguration {

    @Value("${opensearch.username}")
    private String username;

    @Value("${opensearch.password}")
    private String password;

    @Value("${opensearch.host}")
    private String host;

    @Value("${opensearch.port}")
    private int port;

//    @Bean
//    public HttpHost openSearchHost(){
//        return new HttpHost(host, port, "https");
//    }
//
//    @Bean
//    public UsernamePasswordCredentials credentials(){
//        return new UsernamePasswordCredentials(username, password);
//    }
//
//    @Bean
//    public BasicCredentialsProvider basicCredentialsProvider(){
//        BasicCredentialsProvider provider = new BasicCredentialsProvider();
//        provider.setCredentials(new AuthScope(openSearchHost()), credentials());
//        return provider;
//    }
//
//    @Bean
//    public RestClient restClient(){
//        return RestClient.builder(openSearchHost()).setHttpClientConfigCallback(
//                httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(basicCredentialsProvider())
//        ).build();
//    }
//
//    @Bean
//    public RestHighLevelClient restHighLevelClient(){
//        return new RestHighLevelClient(RestClient.builder(openSearchHost()).setHttpClientConfigCallback(
//                httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(basicCredentialsProvider())
//        ));
//    }
//

    @Override
    public RestHighLevelClient opensearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(host + ":" + port)
                .usingSsl()
                .withBasicAuth(username, password)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
