package com.lab9.cliente_web.service;

import com.lab9.cliente_web.model.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    private final WebClient webClient;

    public ProductService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Product obtenerProductosPorId(Integer id) {
        return webClient.get()
                .uri("/api/product/{id}", id)
                .retrieve()
                .bodyToMono(Product.class)
                .onErrorResume(ex -> Mono.empty())
                .block();
    }

    public List<Product> listarProductos() {
        Product[] arr = webClient.get()
                .uri("/api/product")
                .retrieve()
                .bodyToMono(Product[].class)
                .onErrorReturn(new Product[0])
                .block();
        return Arrays.asList(arr == null ? new Product[0] : arr);
    }
}