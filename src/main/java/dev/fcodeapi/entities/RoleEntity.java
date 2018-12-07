package dev.fcodeapi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Role", schema = "dbo", catalog = "Fcode")
public class RoleEntity {
    private int roleId;
    private String roleName;
//    private Collection<AccountEntity> accountsByRoleId;

    @Id
    @Column(name = "RoleId")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "RoleName")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return roleId == that.roleId &&
                Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }
//
//    @OneToMany(mappedBy = "roleByRoleId")
//    public Collection<AccountEntity> getAccountsByRoleId() {
//        return accountsByRoleId;
//    }
//
//    public void setAccountsByRoleId(Collection<AccountEntity> accountsByRoleId) {
//        this.accountsByRoleId = accountsByRoleId;
//    }
}
