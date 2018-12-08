package dev.fcodeapi.repositories;

import dev.fcodeapi.entities.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Integer> {
    public List<AttendanceEntity> findByStudentId(String studentId);
}
