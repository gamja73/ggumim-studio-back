package pbl.project.ggumimstudioBack.order.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pbl.project.ggumimstudioBack.common.error.CustomErrorCode;
import pbl.project.ggumimstudioBack.common.error.CustomException;
import pbl.project.ggumimstudioBack.common.util.CommonUtil;
import pbl.project.ggumimstudioBack.common.util.JwtUtil;
import pbl.project.ggumimstudioBack.order.constants.OrderStatus;
import pbl.project.ggumimstudioBack.order.dto.request.OrderRequestDto;
import pbl.project.ggumimstudioBack.order.dto.response.OrderPaymentDetailResponseDto;
import pbl.project.ggumimstudioBack.order.entity.Order;
import pbl.project.ggumimstudioBack.order.entity.OrderItem;
import pbl.project.ggumimstudioBack.order.repository.OrderItemRepository;
import pbl.project.ggumimstudioBack.order.repository.OrderRepository;
import pbl.project.ggumimstudioBack.product.entity.Product;
import pbl.project.ggumimstudioBack.product.repository.ProductRepository;
import pbl.project.ggumimstudioBack.user.entity.ShoppingCart;
import pbl.project.ggumimstudioBack.user.entity.User;
import pbl.project.ggumimstudioBack.user.repository.ShoppingCartRepository;
import pbl.project.ggumimstudioBack.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService
{

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Transactional
    public String order(OrderRequestDto requestDto)
    {
        Long userUID = jwtUtil.getUserUID();

        User user = userRepository.findById(userUID)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        if (requestDto.getOrderItemList() == null || requestDto.getOrderItemList().isEmpty())
        {
            throw new CustomException(CustomErrorCode.ORDER_PRODUCT_NOT_FOUND);
        }

        String orderID = null;

        while (true)
        {
            orderID = CommonUtil.generateOrderID();
            if (orderRepository.findByOrderId(orderID) == null)
            {
                break;
            }
        }

        Order newOrder = orderRepository.save(Order.builder()
                .orderId(orderID)
                .user(user)
                .orderStatus(OrderStatus.RECEIVED)
                .build());

        List<OrderItem> orderItemList = new ArrayList<>();

        for (OrderRequestDto.OrderItemDto dto : requestDto.getOrderItemList())
        {
            Product product = productRepository.findById(dto.getProductUID())
                    .orElseThrow(() -> new CustomException(CustomErrorCode.PRODUCT_NOT_FOUND));

            orderItemList.add(OrderItem.builder()
                    .order(newOrder)
                    .product(product)
                    .quantity(dto.getQuantity())
                    .build());
        }

        orderItemRepository.saveAll(orderItemList);

        return orderID;
    }

    public OrderPaymentDetailResponseDto getOrderDetailPageData(String orderID)
    {
        Order order = orderRepository.findByOrderID(orderID)
                .orElseThrow(() -> new CustomException(CustomErrorCode.ORDER_NOT_FOUND));

        List<OrderItem> orderItemList = orderItemRepository.findByOrder(order);

        return new OrderPaymentDetailResponseDto(orderItemList);
    }
}
