package com.cruds.model;

public class Group {
    private int groupId; // Primary Key
    private String memberId; // Foreign Key referencing Member
    private int memberAge; // Changed to int to match the SQL table

    // Constructor
    public Group(int groupId, String memberId, int memberAge) {
        this.groupId = groupId;
        this.memberId = memberId;
        this.memberAge = memberAge;
    }

    // Getter and Setter for groupId
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    // Getter and Setter for memberId
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    // Getter and Setter for memberAge
    public int getMemberAge() {
        return memberAge;
    }

    public void setMemberAge(int memberAge) {
        this.memberAge = memberAge;
    }

    // Override toString method to display Group object information
    @Override
    public String toString() {
        return "Group [groupId=" + groupId + ", memberId=" + memberId + ", memberAge=" + memberAge + "]";
    }
}
