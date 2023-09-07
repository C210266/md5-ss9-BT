package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.domain.Product;
import ra.model.dto.request.ProductRequest;
import ra.model.dto.response.ProductResponse;
import ra.repository.IProductRepository;
import ra.service.IProductService;
import ra.service.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(p -> productMapper.toResponse(p))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse findById(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            return productMapper.toResponse(optional.get());
        }
        return null;
    }

    @Override
    public ProductResponse save(ProductRequest productRequest) {
        Product product = productRepository.save(productMapper.toEntity(productRequest));
        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse delete(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            productRepository.deleteById(id);
            return productMapper.toResponse(optional.get());
        }
        return null;
    }

    @Override
    public ProductResponse update(ProductRequest productRequest, Long id) {
        Product p = productMapper.toEntity(productRequest);
        p.setId(id);
        return productMapper.toResponse(productRepository.save(p));
    }
}
