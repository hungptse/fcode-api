package dev.fcodeapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Account", schema = "dbo", catalog = "Fcode")
@JsonIgnoreProperties({"password"})
public class AccountEntity{
    private String email;
    private String password;
    private String name;
    private Boolean isActive;
    private boolean gender;
    private Integer phone;
    private Timestamp dayOfBirth;
    private String avatar;
    private String address;
    private String aboutMe;
    private String studentId;
    private Timestamp dayJoin;
    private String linkFb;
    private RoleEntity role;
    private CourseEntity course;
    private MajorEntity major;

    @Basic
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//    private Collection<AccountEventEntity> accountEventsByStudentId;

    @Basic
    @Column(name = "Password")
//    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "IsActive")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Basic
    @Column(name = "Gender")
    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "Phone")
    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "DayOfBirth")
    public Timestamp getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Timestamp dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }


    @Basic
    @Column(name = "Avatar")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "AboutMe")
    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    @Id
    @Column(name = "StudentID")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "DayJoin")
    public Timestamp getDayJoin() {
        return dayJoin;
    }

    public void setDayJoin(Timestamp dayJoin) {
        this.dayJoin = dayJoin;
    }


    @Basic
    @Column(name = "LinkFB")
    public String getLinkFb() {
        return linkFb;
    }

    public void setLinkFb(String linkFb) {
        this.linkFb = linkFb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return gender == that.gender &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(name, that.name) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(dayOfBirth, that.dayOfBirth) &&
                Objects.equals(avatar, that.avatar) &&
                Objects.equals(address, that.address) &&
                Objects.equals(aboutMe, that.aboutMe) &&
                Objects.equals(studentId, that.studentId) &&
                Objects.equals(dayJoin, that.dayJoin) &&
                Objects.equals(linkFb, that.linkFb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, name, isActive, gender, phone, dayOfBirth, avatar, address, aboutMe, studentId, dayJoin, linkFb);
    }

    @ManyToOne
    @JoinColumn(name = "RoleId", referencedColumnName = "RoleId")
    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity roleByRoleId) {
        this.role = roleByRoleId;
    }

    @ManyToOne
    @JoinColumn(name = "CourseId", referencedColumnName = "CourseId")
    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity courseByCourseId) {
        this.course = courseByCourseId;
    }

    @ManyToOne
    @JoinColumn(name = "MajorId", referencedColumnName = "MajorId")
    public MajorEntity getMajor() {
        return major;
    }

    public void setMajor(MajorEntity majorByMajorId) {
        this.major = majorByMajorId;
    }

//    @OneToMany(mappedBy = "accountByStudentId")
//    public Collection<AccountEventEntity> getAccountEventsByStudentId() {
//        return accountEventsByStudentId;
//    }
//
//    public void setAccountEventsByStudentId(Collection<AccountEventEntity> accountEventsByStudentId) {
//        this.accountEventsByStudentId = accountEventsByStudentId;
//    }



}
