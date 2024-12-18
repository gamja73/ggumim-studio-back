package pbl.project.ggumimstudioBack.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pbl.project.ggumimstudioBack.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom
{
    @Query("SELECT COUNT(p) FROM Product p")
    Integer getProductCount();
}
