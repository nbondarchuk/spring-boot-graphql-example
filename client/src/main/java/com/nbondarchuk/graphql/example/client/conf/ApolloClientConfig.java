package com.nbondarchuk.graphql.example.client.conf;

import com.apollographql.apollo.ApolloClient;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApolloClientConfig {

    @Value("${server.url}")
    private String serverUrl;

    @Bean
    public ApolloClient apolloClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        return ApolloClient.builder()
                .serverUrl(serverUrl)
                .okHttpClient(okHttpClient)
                .build();
    }
}
