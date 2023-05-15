package ru.org.translationservice.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.org.translationservice.entity.SentenceEntity;

@Repository
public interface SentenceRepo extends CrudRepository<SentenceEntity, Long> {
}
