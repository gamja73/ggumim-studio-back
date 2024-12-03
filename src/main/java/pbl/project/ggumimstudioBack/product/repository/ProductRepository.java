package pbl.project.ggumimstudioBack.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pbl.project.ggumimstudioBack.file.repository.FileRepositoryCustom;
import pbl.project.ggumimstudioBack.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, FileRepositoryCustom
{

}
