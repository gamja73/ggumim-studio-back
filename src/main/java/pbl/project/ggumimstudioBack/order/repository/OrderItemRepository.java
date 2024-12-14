package pbl.project.ggumimstudioBack.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pbl.project.ggumimstudioBack.order.entity.Order;
import pbl.project.ggumimstudioBack.order.entity.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, OrderItemRepositoryCustom
{
    @Query("SELECT oi FROM OrderItem oi WHERE oi.order = :order AND oi.isDeleted = false ")
    List<OrderItem> findByOrder(Order order);
}
