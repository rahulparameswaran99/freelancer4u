package ch.zhaw.freelancer4u.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.freelancer4u.model.Job;
import ch.zhaw.freelancer4u.model.JobAssignDTO;
import ch.zhaw.freelancer4u.model.JobCompleteDTO;
import ch.zhaw.freelancer4u.service.JobService;

@RestController
@RequestMapping("/api/service")
public class ServiceController {
    @Autowired
    JobService jobService;

    @PostMapping("/jobassignment")
    public ResponseEntity<Job> assignJob(@RequestBody JobAssignDTO assignDTO) {
        Optional<Job> job = jobService.assignJob(assignDTO.getJobId(), assignDTO.getFreelancerId());
        if (job.isPresent()) {
            return new ResponseEntity<>(job.get(), HttpStatus.OK); 
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/jobcompletion")
    public ResponseEntity<Job> completeJob(@RequestBody JobCompleteDTO completeDTO) {
        Optional<Job> job = jobService.completeJob(completeDTO.getJobId(), completeDTO.getComment());
        if (job.isPresent()) {
            return new ResponseEntity<>(job.get(), HttpStatus.OK); 
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
