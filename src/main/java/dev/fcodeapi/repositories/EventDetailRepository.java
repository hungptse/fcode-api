package dev.fcodeapi.repositories;

import dev.fcodeapi.entities.EventDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventDetailRepository extends JpaRepository<EventDetailEntity, Integer> {
    public EventDetailEntity findByEventDetail(int detail);
//    public List<EventDetailEntity> findByEvent_EventId(int eventId);
}
