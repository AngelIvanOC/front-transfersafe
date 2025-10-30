package utez.edu.mx.TransferSave.modules.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.TransferSave.modules.product.model.Product;
import utez.edu.mx.TransferSave.modules.product.model.Product.ProductStatus;
import utez.edu.mx.TransferSave.modules.product.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Product> getAvailableProducts() {
        return productRepository.findByStatus(ProductStatus.AVAILABLE);
    }

    @Transactional(readOnly = true)
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product createProduct(Product product) {
        product.setStatus(ProductStatus.AVAILABLE);
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setCategory(productDetails.getCategory());
        product.setImage(productDetails.getImage());

        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        product.setStatus(ProductStatus.INACTIVE);
        productRepository.save(product);
    }

    @Transactional
    public void deleteProductPermanently(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryAndStatus(category, ProductStatus.AVAILABLE);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsBySeller(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findAvailableProductsByPriceRange(
                minPrice, maxPrice, ProductStatus.AVAILABLE
        );
    }

    @Transactional(readOnly = true)
    public List<Product> searchProducts(String searchTerm) {
        return productRepository.searchByName(searchTerm, ProductStatus.AVAILABLE);
    }

    @Transactional(readOnly = true)
    public List<Product> searchWithFilters(String category, BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findProductsWithFilters(
                category, minPrice, maxPrice, ProductStatus.AVAILABLE
        );
    }

    @Transactional
    public Product updateProductStatus(Long id, ProductStatus newStatus) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        product.setStatus(newStatus);
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public boolean isProductAvailable(Long id) {
        return productRepository.existsByIdAndStatus(id, ProductStatus.AVAILABLE);
    }
}