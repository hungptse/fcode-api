package dev.fcodeapi.repositories;

import dev.fcodeapi.entities.MajorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorRepository extends JpaRepository<MajorEntity, Integer> {
}
