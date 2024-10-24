package com.porto.dev_vagas.modules.company.useCases;

import com.porto.dev_vagas.modules.company.entities.Job;
import com.porto.dev_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository repository;

    public Job execute(Job job){
        return repository.save(job);
    }

}
