package com.lab9.cliente_web.controller;

import com.lab9.cliente_web.model.entity.Product;
import com.lab9.cliente_web.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String inicio() {
        return "home";
    }

    @GetMapping("/products")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productService.listarProductos());
        return "products";
    }

    @PostMapping("/products/find")
    public String buscarProductos(@RequestParam("id") String idString, Model model) {
        model.addAttribute("productos", productService.listarProductos());
        try {
            Integer id = Integer.valueOf(idString);
            Product p = productService.obtenerProductosPorId(id);
            if (p == null) {
                model.addAttribute("error", "Producto no encontrado");
            } else {
                model.addAttribute("resultado", p);
            }
        } catch (NumberFormatException e) {
            model.addAttribute("error", "ID inválido");
        }
        return "products";
    }
}