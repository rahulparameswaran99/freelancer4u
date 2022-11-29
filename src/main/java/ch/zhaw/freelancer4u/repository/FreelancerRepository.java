package ch.zhaw.freelancer4u.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.freelancer4u.model.Freelancer;

public interface FreelancerRepository extends MongoRepository<Freelancer,String>{

    List<Freelancer> findByEmail(String email);

    
}