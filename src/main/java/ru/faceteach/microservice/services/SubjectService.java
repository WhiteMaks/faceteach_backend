package ru.faceteach.microservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.faceteach.microservice.domains.Subject;
import ru.faceteach.microservice.repositories.SubjectRepository;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Subject> create(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Flux<Subject> getAll() {
        return subjectRepository.findAll();
    }
}
