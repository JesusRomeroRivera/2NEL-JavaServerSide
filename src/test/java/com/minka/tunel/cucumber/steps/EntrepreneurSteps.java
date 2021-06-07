package com.minka.tunel.cucumber.steps;

import com.minka.tunel.domain.model.Entrepreneur;
import com.minka.tunel.domain.repository.EntrepreneurRepository;
import com.minka.tunel.domain.service.EntrepreneurService;
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

public class EntrepreneurSteps {

    @MockBean
    private EntrepreneurRepository entrepreneurRepository;

    @Autowired
    private EntrepreneurService entrepreneurService;

    private List<Entrepreneur> expectedEntrepreneurs;

    private List<Entrepreneur> actualEntrepreneurs;

    @Before
    public void setup()
    {
        expectedEntrepreneurs = new ArrayList<>();
        actualEntrepreneurs = new ArrayList<>();
    }

    @Given("^the following entrepreneurs$")
    public void givenTheFollowingEntrepreneurs(final List<Entrepreneur> investors){
        expectedEntrepreneurs.addAll(investors);
        entrepreneurRepository.saveAll(investors);
    }

    @When("^the user requests all the entrepreneurs$")
    public void whenTheUserRequestsAllEntrepreneurs(Pageable pageable)
    {
        Page<Entrepreneur> freelancers = entrepreneurService.getAllEntrepreneurs(pageable);
        actualEntrepreneurs = freelancers.getContent();
    }

    @Then(("^all the entrepreneurs are returned$"))
    public void thenAllEntrepreneursAreReturned()
    {
        assertThat(actualEntrepreneurs).isEqualTo(expectedEntrepreneurs);
    }
}
