package ch.zhaw.freelancer4u.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.freelancer4u.model.Freelancer;
import ch.zhaw.freelancer4u.model.FreelancerCreateDTO;
import ch.zhaw.freelancer4u.repository.FreelancerRepository;

@RestController
@RequestMapping("/api/freelancer")
public class FreelancerController {

    @Autowired
    FreelancerRepository freelancerRepository;

    @PostMapping("")
    public ResponseEntity<Freelancer> createFreelancer(
        @RequestBody FreelancerCreateDTO fDTO) {
        Freelancer fDAO = new Freelancer(fDTO.getEmail(), fDTO.getName());
        Freelancer f = freelancerRepository.save(fDAO);
        return new ResponseEntity<>(f, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Page<Freelancer>> getAllFreelancer(
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer pageSize) {
        if (page == null) {
            page = 1;
            }
        if (pageSize == null) {
            pageSize = 4;
            }
            Page<Freelancer> allFreelancers = freelancerRepository.findAll(PageRequest.of(page-1, pageSize));
            return new ResponseEntity<>(allFreelancers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Freelancer> getFreelancerById(@PathVariable String id) {
        Optional<Freelancer> optFreelancer = freelancerRepository.findById(id);
        if (optFreelancer.isPresent()) {
            return new ResponseEntity<>(optFreelancer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

