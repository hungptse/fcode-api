package dev.fcodeapi.repositories;

import dev.fcodeapi.entities.AccountEntity;
import dev.fcodeapi.entities.AccountEventEntity;
import dev.fcodeapi.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountEventRepository extends JpaRepository<AccountEventEntity, Integer> {
    List<AccountEventEntity> findAllByAccount_StudentId(String studentId);
    AccountEventEntity findByEvent_EventIdAndAccount_StudentId(int event, String studentId);
    List<AccountEventEntity> findAllByEvent_EventIdAndStatusIsNull(int event);
    List<AccountEventEntity> findAllByEvent_EventId(int event);

}
