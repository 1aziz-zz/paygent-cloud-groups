package org.aziz.paygent.groupservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aziz.paygent.groupservice.datasource.GroupRepo;
import org.aziz.paygent.groupservice.exceptions.GroupNotFoundException;
import org.aziz.paygent.groupservice.models.GroupMemberDTO;
import org.aziz.paygent.groupservice.models.PaymentGroupDTO;
import org.aziz.paygent.groupservice.models.entities.Group;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class GroupService extends GenericAbstractService<Group, GroupRepo> {

    private ObjectMapper objectMapper = new ObjectMapper();

    private boolean memberExistsInGroup(Group group, String memberId) {
        return group.getMembers().contains(memberId);
    }

    private boolean paymentExistsInGroup(Group group, String paymentID) {
        return group.getPaymentsList().contains(paymentID);
    }

    @JmsListener(destination = "addMemberToGroup", containerFactory = "myFactory")
    public void addMemberToGroup(final String message) throws IOException {
        System.out.println("GROUP-SERVICE: received: <" + message + ">");
        GroupMemberDTO groupMemberDTO = objectMapper.readValue(message, GroupMemberDTO.class);
        String memberId = groupMemberDTO.getMemberId();
        Group group = this.get(groupMemberDTO.getGroupId()).get();

        if (!this.memberExistsInGroup(group, memberId)) {
            if (group.getCreatedBy() == null) {
                group.setCreatedBy(memberId);
            }
            group.addGroupUser(memberId);
        }
        this.update(group);
    }

    @JmsListener(destination = "deleteMemberFromGroup", containerFactory = "myFactory")
    public void deleteMemberFromGroup(GroupMemberDTO message) {
        System.out.println("GROUP-SERVICE: received: <" + message.toString() + ">");
        Group foundGroup = this.get(message.getGroupId()).get();
        foundGroup.getMembers().removeIf(s -> s.equals(message.getMemberId()));
        if (foundGroup.getMembers().isEmpty()) {
            this.delete(foundGroup);
        }
        this.update(foundGroup);
    }

    @JmsListener(destination = "addPaymentIdToGroup", containerFactory = "myFactory")
    public void addPaymentIdToGroup(String message) throws IOException {
        System.out.println("GROUP-SERVICE: received: <" + message + ">");
        PaymentGroupDTO paymentGroupDTO = objectMapper.readValue(message, PaymentGroupDTO.class);
        String paymentId = paymentGroupDTO.getPaymentId();
        Group group = this.get(paymentGroupDTO.getGroupId()).get();

        if (!this.paymentExistsInGroup(group, paymentId)) {
            group.addPayment(paymentId);
        }
        this.update(group);
    }

    public Group validatedGroup(String id) {
        Optional<Group> group = get(id);
        group.orElseThrow(
                () -> new GroupNotFoundException(id));
        return group.get();
    }
}
