package com.cruds.model;

public class Club {
    private int clubId; // Primary Key
    private String clubName; // 동아리 이름
    private String representativeName; // 대표자 이름
    private int averageAge; // 평균 나이
    private int headCount; // 동아리 인원 수

    // Constructor
    public Club(int clubId, String clubName, String representativeName, int averageAge, int headCount) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.representativeName = representativeName;
        this.averageAge = averageAge;
        this.headCount = headCount;
    }

    // Getter and Setter for clubId
    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    // Getter and Setter for clubName
    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    // Getter and Setter for representativeName
    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    // Getter and Setter for averageAge
    public int getAverageAge() {
        return averageAge;
    }

    public void setAverageAge(int averageAge) {
        this.averageAge = averageAge;
    }

    // Getter and Setter for headCount
    public int getHeadCount() {
        return headCount;
    }

    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }

    // Override toString method to display Club object information
    @Override
    public String toString() {
        return "Club [clubId=" + clubId + ", clubName=" + clubName + ", representativeName=" + representativeName
                + ", averageAge=" + averageAge + ", headCount=" + headCount + "]";
    }
}
