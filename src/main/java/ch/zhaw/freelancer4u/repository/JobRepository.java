package ch.zhaw.freelancer4u.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.freelancer4u.model.JobFreelancerAggregationDTO;
import ch.zhaw.freelancer4u.model.Job;
import ch.zhaw.freelancer4u.model.JobStateAggregationDTO;

public interface JobRepository extends MongoRepository<Job,String>{
    List<Job> findByEarningsGreaterThan(Double earnings);
    List<Job> findByEarningsBetween(Double min, Double max);

    @Aggregation("{$group: {_id: '$jobState',jobIds: {$push: '$_id'},count: {$count: {}}}}")
    List<JobStateAggregationDTO> getJobStateAggregation();

    @Aggregation("{$group: {_id: '$freelancerId',jobIds: {$push: '$_id'},totalEarnings: {$sum: '$earnings'}}}")
    List<JobFreelancerAggregationDTO> getJobFreelancerAggregation();

}
