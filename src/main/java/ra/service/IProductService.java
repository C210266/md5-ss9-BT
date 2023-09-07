package ra.service;

import ra.model.domain.Product;
import ra.model.dto.request.ProductRequest;
import ra.model.dto.response.ProductResponse;

import java.util.List;

public interface IProductService {
    List<ProductResponse> findAll();

    ProductResponse findById(Long id);

    ProductResponse save(ProductRequest productRequest);

    ProductResponse delete(Long id);

    ProductResponse update(ProductRequest productRequest, Long id);
}
