package dev.fcodeapi.repositories;

import dev.fcodeapi.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {
    public List<AccountEntity> findByNameContaining(String search);
    public List<AccountEntity> findAllByRole_RoleIdNotLike(int id);
    public AccountEntity findByStudentId(String studentId);
    public AccountEntity findByStudentIdAndPassword(String studentId, String password);

}
