package org.aziz.paygent.groupservice.models.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
public class Group extends DBEntity {
    @NotNull
    private String title, description;
    private String createdBy;
    private List<String> members = new ArrayList<>();
    private List<String> paymentsList = new ArrayList<>();
    private Date createdTime;

    public Group() {
        createdTime = new Date();
    }

    public Group(String title, String description) {
        this.title = title;
        this.description = description;
        createdTime = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void addGroupUser(String member) {
        members.add(member);
    }

    public List<String> getPaymentsList() {
        return paymentsList;
    }

    public void setPaymentsList(List<String> paymentsList) {
        this.paymentsList = paymentsList;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void addPayment(String payment) {
        this.paymentsList.add(payment);
    }
}
