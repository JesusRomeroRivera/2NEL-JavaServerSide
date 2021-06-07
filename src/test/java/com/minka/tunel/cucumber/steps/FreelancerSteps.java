package com.minka.tunel.cucumber.steps;

import com.minka.tunel.domain.model.Freelancer;
import com.minka.tunel.domain.repository.FreelancerRepository;
import com.minka.tunel.domain.service.FreelancerService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FreelancerSteps {

    @MockBean
    private FreelancerRepository freelancerRepository;

    @Autowired
    private FreelancerService freelancerService;

    private List<Freelancer> expectedFreelancers;

    private List<Freelancer> actualFreelancers;

    @Before
    public void setup()
    {
        expectedFreelancers = new ArrayList<>();
        actualFreelancers = new ArrayList<>();
    }

    @Given("^the following freelancers$")
    public void givenTheFollowingFreelancers(final List<Freelancer> investors){
        expectedFreelancers.addAll(investors);
        freelancerRepository.saveAll(investors);
    }

    @When("^the user requests all the freelancers$")
    public void whenTheUserRequestsAllFreelancers(Pageable pageable)
    {
        Page<Freelancer> freelancers = freelancerService.getAllFreelancers(pageable);
        actualFreelancers = freelancers.getContent();
    }

    @Then(("^all the freelancers are returned$"))
    public void thenAllFreelancersAreReturned()
    {
        assertThat(actualFreelancers).isEqualTo(expectedFreelancers);
    }
}
