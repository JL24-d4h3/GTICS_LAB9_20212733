package com.lab9.servicio_web_restful.controller;

import com.lab9.servicio_web_restful.model.entity.Product;
import com.lab9.servicio_web_restful.repository.CategoryRepository;
import com.lab9.servicio_web_restful.repository.ProductRepository;
import com.lab9.servicio_web_restful.repository.SupplierRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    public ProductController(ProductRepository productRepository,
                             CategoryRepository categoryRepository,
                             SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Product newProduct) {
        try {
            if (newProduct.getCategory() != null) {
                int catId = newProduct.getCategory().getId();
                newProduct.setCategory(categoryRepository.findById(catId).orElse(null));
            }

            if (newProduct.getSupplier() != null) {
                int supId = newProduct.getSupplier().getId();
                newProduct.setSupplier(supplierRepository.findById(supId).orElse(null));
            }

            Product guardado = productRepository.save(newProduct);
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("estado", "creado");
            respuesta.put("producto", guardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("estado", "error");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizarProducto(@RequestBody Product updatedProduct) {
        if (updatedProduct.getId() == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("estado", "error");
            error.put("mensaje", "Debe proporcionar un ID para actualizar el producto");
            return ResponseEntity.badRequest().body(error);
        }

        Optional<Product> opt = productRepository.findById(updatedProduct.getId());

        if (opt.isPresent()) {
            Product existente = opt.get();
            existente.setProductName(updatedProduct.getProductName());
            existente.setUnit(updatedProduct.getUnit());
            existente.setPrice(updatedProduct.getPrice());

            if (updatedProduct.getCategory() != null) {
                existente.setCategory(categoryRepository.findById(
                        updatedProduct.getCategory().getId()).orElse(null));
            }

            if (updatedProduct.getSupplier() != null) {
                existente.setSupplier(supplierRepository.findById(
                        updatedProduct.getSupplier().getId()).orElse(null));
            }

            Product guardado = productRepository.save(existente);
            Map<String, Object> ok = new HashMap<>();
            ok.put("estado", "actualizado");
            ok.put("producto", guardado);

            return ResponseEntity.ok(ok);

        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("estado", "error");
            error.put("mensaje", "Producto no encontrado");
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Integer id) {
        Optional<Product> opt = productRepository.findById(id);

        if (opt.isPresent()) {
            productRepository.deleteById(id);
            Map<String, Object> ok = new HashMap<>();
            ok.put("estado", "ok");
            ok.put("mensaje", "Producto eliminado correctamente");
            return ResponseEntity.ok(ok);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("estado", "error");
            error.put("mensaje", "Producto no encontrado");
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping
    public List<Product> listarProductos() {
        return productRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Integer id) {
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("estado", "error");
            error.put("mensaje", "Producto no encontrado");
            return ResponseEntity.badRequest().body(error);
        }
    }
}
