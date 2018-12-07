package dev.fcodeapi.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Major", schema = "dbo", catalog = "Fcode")
public class MajorEntity {
    private int majorId;
    private String majorName;
//    private Collection<AccountEntity> accountsByMajorId;

    @Id
    @Column(name = "MajorId")
    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    @Basic
    @Column(name = "MajorName")
    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MajorEntity that = (MajorEntity) o;
        return majorId == that.majorId &&
                Objects.equals(majorName, that.majorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(majorId, majorName);
    }

//    @OneToMany(mappedBy = "majorByMajorId")
//    public Collection<AccountEntity> getAccountsByMajorId() {
//        return accountsByMajorId;
//    }
//
//    public void setAccountsByMajorId(Collection<AccountEntity> accountsByMajorId) {
//        this.accountsByMajorId = accountsByMajorId;
//    }
}
