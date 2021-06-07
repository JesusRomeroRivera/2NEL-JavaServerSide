package com.minka.tunel.cucumber.steps;

import com.minka.tunel.domain.model.Investor;
import com.minka.tunel.domain.repository.InvestorRepository;
import com.minka.tunel.domain.service.InvestorService;
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

public class InvestorSteps {

    @MockBean
    private InvestorRepository investorRepository;

    @Autowired
    private InvestorService investorService;

    private List<Investor> expectedInvestors;

    private List<Investor> actualInvestors;

    @Before
    public void setup()
    {
        expectedInvestors = new ArrayList<>();
        actualInvestors = new ArrayList<>();
    }

    @Given("^the following investors$")
    public void givenTheFollowingInvestors(final List<Investor> investors){
        expectedInvestors.addAll(investors);
        investorRepository.saveAll(investors);
    }

    @When("^the user requests all the investors$")
    public void whenTheUserRequestsAllInvestors(Pageable pageable)
    {
        Page<Investor> investors = investorService.getAllInvestors(pageable);
        actualInvestors = investors.getContent();
    }

    @Then(("^all the investors are returned$"))
    public void thenAllInvestorsAreReturned()
    {
        assertThat(actualInvestors).isEqualTo(expectedInvestors);
    }
}
