package org.aziz.paygent.groupservice.models;

public class PaymentGroupDTO  {
    private String paymentId, groupId;

    public PaymentGroupDTO() {
    }

    public PaymentGroupDTO(String paymentId, String groupId) {
        this.paymentId = paymentId;
        this.groupId = groupId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getGroupId() {
        return groupId;
    }
}
