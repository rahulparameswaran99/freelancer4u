package ch.zhaw.freelancer4u.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.freelancer4u.model.JobFreelancerAggregationDTO;
import ch.zhaw.freelancer4u.model.Job;
import ch.zhaw.freelancer4u.model.JobCreateDTO;
import ch.zhaw.freelancer4u.model.JobStateAggregationDTO;
import ch.zhaw.freelancer4u.repository.JobRepository;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    JobRepository jobRepository;

    @PostMapping("")
    public ResponseEntity<Job> createJob(@RequestBody JobCreateDTO cDTO) {
        Job jDAO = new Job(cDTO.getDescription(), cDTO.getJobType(), cDTO.getEarnings());
        Job j = jobRepository.save(jDAO);
        return new ResponseEntity<>(j, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Page<Job>> getAllJob(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Double min,
            @RequestParam(required = false) Double max) {

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 2;
        }

        Page<Job> allJobs;

        if (min != null && max != null) {
            allJobs = jobRepository
                    .findByEarningsBetween(min, max, PageRequest.of(page - 1, pageSize));
        } else if (min != null) {
            allJobs = jobRepository
                    .findByEarningsGreaterThan(min, PageRequest.of(page - 1, pageSize));
        } else {
            allJobs = jobRepository
                    .findAll(PageRequest.of(page - 1, pageSize));
        }

        return new ResponseEntity<>(allJobs, HttpStatus.OK);
    }

    @GetMapping("/earningsabove")
    public ResponseEntity<List<Job>> getJobMinEarning(@RequestParam Double min) {
        return new ResponseEntity<>(jobRepository.findByEarningsGreaterThan(min), HttpStatus.OK);
    }

    @GetMapping("/earningsinrange")
    public ResponseEntity<List<Job>> getJobMinMaxEarning(@RequestParam Double min, @RequestParam Double max) {
        return new ResponseEntity<>(jobRepository.findByEarningsBetween(min, max), HttpStatus.OK);
    }

    @GetMapping("/bystate")
    public ResponseEntity<List<JobStateAggregationDTO>> getJobStateAggregation() {
        return new ResponseEntity<>(jobRepository.getJobStateAggregation(), HttpStatus.OK);
    }

    @GetMapping("/byfreelancer")
    public ResponseEntity<List<JobFreelancerAggregationDTO>> getJobFreelancerAggregation() {
        return new ResponseEntity<>(jobRepository.getJobFreelancerAggregation(), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteAllJob() {
        jobRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("DELETED");
    }
}
