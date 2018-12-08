package dev.fcodeapi.repositories;

import dev.fcodeapi.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    public RoleEntity findByRoleId(int id);
}
