package ch.zhaw.freelancer4u.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zhaw.freelancer4u.model.Job;
import ch.zhaw.freelancer4u.model.JobState;
import ch.zhaw.freelancer4u.repository.FreelancerRepository;
import ch.zhaw.freelancer4u.repository.JobRepository;

@Service
public class JobService {
    @Autowired
    JobRepository jobRepository;

    @Autowired
    FreelancerRepository freelancerRepository;
    
    public Optional<Job> assignJob(String jobId, String freelancerId) {
        if (freelancerRepository.findById(freelancerId).isPresent()) {
            Optional<Job> jobToAssign = jobRepository.findById(jobId);
            if (jobToAssign.isPresent()) {
                Job job = jobToAssign.get();
                if (job.getJobState() == JobState.NEW) {
                    job.setJobState(JobState.ASSIGNED);
                    job.setFreelancerId(freelancerId);
                    jobRepository.save(job);
                    return Optional.of(job);
                }
            }
        }
        return Optional.empty();
    }

    public Optional<Job> completeJob(String jobId, String comment) {
        Optional<Job> jobToComplete = jobRepository.findById(jobId);
        if (jobToComplete.isPresent()) {
            Job job = jobToComplete.get();
            if (job.getJobState() == JobState.ASSIGNED) {
                job.setJobState(JobState.DONE);
                job.setComment(comment);
                jobRepository.save(job);
                return Optional.of(job);
            }
        }
        return Optional.empty();
    }
}
