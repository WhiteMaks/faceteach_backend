package ru.faceteach.microservice.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import ru.faceteach.microservice.domains.Subject;

@Repository
public interface SubjectRepository extends ReactiveCrudRepository<Subject, Long> {

}
