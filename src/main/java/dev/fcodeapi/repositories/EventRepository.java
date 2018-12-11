package dev.fcodeapi.repositories;


import dev.fcodeapi.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Integer> {
    public List<EventEntity> findAllByPublishIsTrue();
    public List<EventEntity> findAllByType_TypeId(int id);
}
