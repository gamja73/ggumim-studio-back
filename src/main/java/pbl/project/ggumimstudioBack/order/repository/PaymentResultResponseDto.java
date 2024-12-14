package pbl.project.ggumimstudioBack.order.repository;

import lombok.Data;

@Data
public class PaymentResultResponseDto
{
    private String status;
    private String id;
    private String transactionId;
    private String merchantId;
    private String storeId;
    private Method method;
    private Channel channel;
    private String version;
    private String requestedAt;
    private String updatedAt;
    private String statusChangedAt;
    private String orderName;
    private Amount amount;
    private String currency;
    private Customer customer;
    private String promotionId;
    private boolean isCulturalExpense;
    private String paidAt;
    private String pgTxId;
    private String pgResponse;
    private String receiptUrl;

    @Data
    public static class Method {
        private String type;
        private String provider;
        private EasyPayMethod easyPayMethod;

        @Data
        public static class EasyPayMethod {
            private String type;
            private Card card;
            private String approvalNumber;
            private Installment installment;
            private boolean pointUsed;

            @Data
            public static class Card {
                private String publisher;
                private String issuer;
                private String brand;
                private String type;
                private String ownerType;
                private String bin;
                private String name;
                private String number;
            }

            @Data
            public static class Installment {
                private int month;
                private boolean isInterestFree;
            }
        }
    }

    @Data
    public static class Channel {
        private String type;
        private String id;
        private String key;
        private String name;
        private String pgProvider;
        private String pgMerchantId;
    }

    @Data
    public static class Amount {
        private int total;
        private int taxFree;
        private int vat;
        private int supply;
        private int discount;
        private int paid;
        private int cancelled;
        private int cancelledTaxFree;
    }

    @Data
    public static class Customer {
        private String id;
    }
}
