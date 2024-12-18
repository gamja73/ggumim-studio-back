package pbl.project.ggumimstudioBack.common.dto.response;

import lombok.Data;

@Data
public class AdminDashBoardResponseDto
{
    public String orderAmountMonth;
    private String orderAmountDay;
    private Integer orderCompletePercent;
    private String productCount;
    private OrderChartResponseDto orderChartData;

    @Data
    public static class OrderChartResponseDto
    {
        private Integer receivedOrderCount;
        private Integer paymentCompletedOrderCount;
        private Integer preparingOrderCount;
        private Integer shippingPreparingOrderCount;
        private Integer shippedOrderCount;
        private Integer deliveredOrderCount;

        public OrderChartResponseDto(Integer receivedOrderCount, Integer paymentCompletedOrderCount, Integer preparingOrderCount, Integer shippingPreparingOrderCount, Integer shippedOrderCount, Integer deliveredOrderCount)
        {
            this.receivedOrderCount = receivedOrderCount != null ? receivedOrderCount : 0;
            this.paymentCompletedOrderCount = paymentCompletedOrderCount != null ? paymentCompletedOrderCount : 0;
            this.preparingOrderCount = preparingOrderCount != null ? preparingOrderCount : 0;
            this.shippingPreparingOrderCount = shippingPreparingOrderCount != null ? shippingPreparingOrderCount : 0;
            this.shippedOrderCount = shippedOrderCount != null ? shippedOrderCount : 0;
            this.deliveredOrderCount = deliveredOrderCount != null ? deliveredOrderCount : 0;
        }
    }
    public AdminDashBoardResponseDto(Integer orderAmountMonth, Integer orderAmountDay, Integer orderCompletePercent, Integer productCount, Integer receivedOrderCount, Integer paymentCompletedOrderCount, Integer preparingOrderCount, Integer shippingPreparingOrderCount, Integer shippedOrderCount, Integer deliveredOrderCount)
    {
        this.orderAmountMonth = orderAmountMonth.toString();
        this.orderAmountDay = orderAmountDay.toString();
        this.orderCompletePercent = orderCompletePercent;
        this.productCount = productCount.toString();
        this.orderChartData = new OrderChartResponseDto(receivedOrderCount, paymentCompletedOrderCount, preparingOrderCount, shippingPreparingOrderCount, shippedOrderCount, deliveredOrderCount);
    }
}