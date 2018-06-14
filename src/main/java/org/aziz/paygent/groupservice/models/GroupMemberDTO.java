package org.aziz.paygent.groupservice.models;


public class GroupMemberDTO  {
    public GroupMemberDTO(String memberId, String groupId) {
        this.memberId = memberId;
        this.groupId = groupId;
    }

    public GroupMemberDTO() {
    }

    private String memberId, groupId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
