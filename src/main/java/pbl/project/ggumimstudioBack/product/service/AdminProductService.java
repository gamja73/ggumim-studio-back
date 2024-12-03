package pbl.project.ggumimstudioBack.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pbl.project.ggumimstudioBack.product.dto.request.CreateProductRequestDto;
import pbl.project.ggumimstudioBack.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class AdminProductService
{
    private final ProductRepository productRepository;

    public String createProduct(CreateProductRequestDto requestDto)
    {
        productRepository.save(requestDto.toEntity());
        return "등록이 완료되었습니다.";
    }
}
