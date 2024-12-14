package pbl.project.ggumimstudioBack.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pbl.project.ggumimstudioBack.user.entity.ShoppingCart;
import pbl.project.ggumimstudioBack.user.entity.User;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>, ShoppingCartRepositoryCustom
{

    @Query("SELECT s FROM ShoppingCart s WHERE s.user = :user AND s.isDeleted = false")
    List<ShoppingCart> findByUser(User user);
}
