package ru.org.translationservice.repo;

import org.springframework.data.repository.CrudRepository;
import ru.org.translationservice.entity.WordEntity;

public interface WordRepo extends CrudRepository<WordEntity, Long> {
}
