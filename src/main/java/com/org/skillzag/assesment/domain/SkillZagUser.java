package com.org.skillzag.assesment.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A SkillZagUser.
 */
@Entity
@Table(name = "skill_zag_user")
public class SkillZagUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "question_set_id")
    private Long questionSetId;

    @Column(name = "others")
    private String others;

    @Column(name = "last_complete_test")
    private Instant lastCompleteTest;

    @Column(name = "last_in_complete_test")
    private Instant lastInCompleteTest;

    @OneToMany(mappedBy = "skillZagUser")
    private Set<UserCompleteTest> userCompleteTests = new HashSet<>();

    @OneToMany(mappedBy = "skillZagUser")
    private Set<UserInCompleteTest> userInCompleteTests = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public SkillZagUser isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getUserName() {
        return userName;
    }

    public SkillZagUser userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public SkillZagUser userRole(String userRole) {
        this.userRole = userRole;
        return this;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Long getQuestionSetId() {
        return questionSetId;
    }

    public SkillZagUser questionSetId(Long questionSetId) {
        this.questionSetId = questionSetId;
        return this;
    }

    public void setQuestionSetId(Long questionSetId) {
        this.questionSetId = questionSetId;
    }

    public String getOthers() {
        return others;
    }

    public SkillZagUser others(String others) {
        this.others = others;
        return this;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public Instant getLastCompleteTest() {
        return lastCompleteTest;
    }

    public SkillZagUser lastCompleteTest(Instant lastCompleteTest) {
        this.lastCompleteTest = lastCompleteTest;
        return this;
    }

    public void setLastCompleteTest(Instant lastCompleteTest) {
        this.lastCompleteTest = lastCompleteTest;
    }

    public Instant getLastInCompleteTest() {
        return lastInCompleteTest;
    }

    public SkillZagUser lastInCompleteTest(Instant lastInCompleteTest) {
        this.lastInCompleteTest = lastInCompleteTest;
        return this;
    }

    public void setLastInCompleteTest(Instant lastInCompleteTest) {
        this.lastInCompleteTest = lastInCompleteTest;
    }

    public Set<UserCompleteTest> getUserCompleteTests() {
        return userCompleteTests;
    }

    public SkillZagUser userCompleteTests(Set<UserCompleteTest> userCompleteTests) {
        this.userCompleteTests = userCompleteTests;
        return this;
    }

    public SkillZagUser addUserCompleteTest(UserCompleteTest userCompleteTest) {
        this.userCompleteTests.add(userCompleteTest);
        userCompleteTest.setSkillZagUser(this);
        return this;
    }

    public SkillZagUser removeUserCompleteTest(UserCompleteTest userCompleteTest) {
        this.userCompleteTests.remove(userCompleteTest);
        userCompleteTest.setSkillZagUser(null);
        return this;
    }

    public void setUserCompleteTests(Set<UserCompleteTest> userCompleteTests) {
        this.userCompleteTests = userCompleteTests;
    }

    public Set<UserInCompleteTest> getUserInCompleteTests() {
        return userInCompleteTests;
    }

    public SkillZagUser userInCompleteTests(Set<UserInCompleteTest> userInCompleteTests) {
        this.userInCompleteTests = userInCompleteTests;
        return this;
    }

    public SkillZagUser addUserInCompleteTest(UserInCompleteTest userInCompleteTest) {
        this.userInCompleteTests.add(userInCompleteTest);
        userInCompleteTest.setSkillZagUser(this);
        return this;
    }

    public SkillZagUser removeUserInCompleteTest(UserInCompleteTest userInCompleteTest) {
        this.userInCompleteTests.remove(userInCompleteTest);
        userInCompleteTest.setSkillZagUser(null);
        return this;
    }

    public void setUserInCompleteTests(Set<UserInCompleteTest> userInCompleteTests) {
        this.userInCompleteTests = userInCompleteTests;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SkillZagUser)) {
            return false;
        }
        return id != null && id.equals(((SkillZagUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SkillZagUser{" +
            "id=" + getId() +
            ", isActive='" + isIsActive() + "'" +
            ", userName='" + getUserName() + "'" +
            ", userRole='" + getUserRole() + "'" +
            ", questionSetId=" + getQuestionSetId() +
            ", others='" + getOthers() + "'" +
            ", lastCompleteTest='" + getLastCompleteTest() + "'" +
            ", lastInCompleteTest='" + getLastInCompleteTest() + "'" +
            "}";
    }
}
