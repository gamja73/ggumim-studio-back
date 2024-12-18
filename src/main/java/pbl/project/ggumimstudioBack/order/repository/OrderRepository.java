package pbl.project.ggumimstudioBack.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pbl.project.ggumimstudioBack.order.constants.OrderStatus;
import pbl.project.ggumimstudioBack.order.entity.Order;
import pbl.project.ggumimstudioBack.order.entity.Payment;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom
{
    @Query("SELECT o FROM Order o WHERE o.orderId = :orderId AND o.isDeleted = false")
    Order findByOrderId(String orderId);

    @Query("SELECT o FROM Order o WHERE o.orderId = :orderID AND o.isDeleted = false")
    Optional<Order> findByOrderID(String orderID);

    Optional<Order> findByPayment(Payment payment);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.orderStatus = :orderStatus")
    Integer countByOrderStatus(OrderStatus orderStatus);
}
