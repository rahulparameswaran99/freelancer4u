package ch.zhaw.freelancer4u.model;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JobFreelancerAggregationDTO {
    private String id;
    private List<String> jobIds;
    private Double totalEarnings;
}

