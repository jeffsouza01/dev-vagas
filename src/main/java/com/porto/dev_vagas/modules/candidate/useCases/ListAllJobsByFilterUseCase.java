package com.porto.dev_vagas.modules.candidate.useCases;

import com.porto.dev_vagas.modules.company.entities.Job;
import com.porto.dev_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository repository;


    public List<Job> execute(String filter){
        return repository.findByDescriptionContainingIgnoreCase(filter);
    }
}
