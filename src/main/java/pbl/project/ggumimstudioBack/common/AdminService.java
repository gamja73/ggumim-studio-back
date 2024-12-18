package pbl.project.ggumimstudioBack.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pbl.project.ggumimstudioBack.common.dto.response.AdminDashBoardResponseDto;
import pbl.project.ggumimstudioBack.order.constants.OrderStatus;
import pbl.project.ggumimstudioBack.order.entity.Order;
import pbl.project.ggumimstudioBack.order.entity.OrderItem;
import pbl.project.ggumimstudioBack.order.repository.OrderItemRepository;
import pbl.project.ggumimstudioBack.order.repository.OrderRepository;
import pbl.project.ggumimstudioBack.product.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService
{
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public AdminDashBoardResponseDto getDashBoardData()
    {
        Integer orderAmountMonth = 0;
        Integer orderAmountDay = 0;
        Integer orderCompletePercent = 0;
        Integer productCount = 0;

        List<Order> orderMonthList = orderRepository.getDashBoardOrderMonth();
        for (Order order : orderMonthList)
        {
            List<OrderItem> orderItemList = orderItemRepository.findByOrder(order);
            orderAmountMonth += getTotalPrice(orderItemList);
        }

        List<Order> orderDayList = orderRepository.getDashBoardOrderDay();
        for (Order order : orderDayList)
        {
            List<OrderItem> orderItemList = orderItemRepository.findByOrder(order);
            orderAmountDay += getTotalPrice(orderItemList);
        }

        List<Order> orderList = orderRepository.findAll();

        Integer orderTotalCount = 0;
        Integer orderCompleteCount = 0;

        for (Order order : orderList)
        {
            orderTotalCount += 1;
            if (order.getOrderStatus().equals(OrderStatus.DELIVERED))
            {
                orderCompleteCount += 1;
            }
        }

        orderCompletePercent = (int) (((double) orderCompleteCount / orderTotalCount) * 100);

        productCount = productRepository.getProductCount();

        Integer receivedOrderCount = orderRepository.countByOrderStatus(OrderStatus.RECEIVED);
        Integer paymentCompletedOrderCount = orderRepository.countByOrderStatus(OrderStatus.PAYMENT_COMPLETED);
        Integer preparingOrderCount = orderRepository.countByOrderStatus(OrderStatus.PREPARING);
        Integer shippingPreparingOrderCount = orderRepository.countByOrderStatus(OrderStatus.SHIPPING_PREPARING);
        Integer shippedOrderCount = orderRepository.countByOrderStatus(OrderStatus.SHIPPED);
        Integer deliveredOrderCount = orderRepository.countByOrderStatus(OrderStatus.DELIVERED);

        return new AdminDashBoardResponseDto(orderAmountMonth, orderAmountDay, orderCompletePercent, productCount, receivedOrderCount, paymentCompletedOrderCount, preparingOrderCount, shippingPreparingOrderCount, shippedOrderCount, deliveredOrderCount);
    }

    private Integer getTotalPrice(List<OrderItem> orderItemList)
    {
        Integer totalPrice = 0;

        for (OrderItem item : orderItemList)
        {
            totalPrice += item.getProduct().getProductPrice().intValue() * item.getQuantity();
        }

        return totalPrice;
    }
}