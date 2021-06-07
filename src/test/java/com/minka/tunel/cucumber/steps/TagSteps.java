package com.minka.tunel.cucumber.steps;

import com.minka.tunel.domain.model.Tag;
import com.minka.tunel.domain.repository.TagRepository;
import com.minka.tunel.domain.service.TagService;
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

public class TagSteps {

    @MockBean
    private TagRepository tagRepository;

    @Autowired
    private TagService tagService;

    private List<Tag> expectedTags;

    private List<Tag> actualTags;

    @Before
    public void setup()
    {
        expectedTags = new ArrayList<>();
        actualTags = new ArrayList<>();
    }

    @Given("^the following tags$")
    public void givenTheFollowingTags(final List<Tag> tags){
        expectedTags.addAll(tags);
        tagRepository.saveAll(tags);
    }

    @When("^the user requests all tags$")
    public void whenTheUserRequestsAllTags(Pageable pageable)
    {
        Page<Tag> tags = tagService.getAllTags(pageable);
        actualTags = tags.getContent();
    }

    @Then(("^all tags are returned$"))
    public void thenAllTagsAreReturned()
    {
        assertThat(actualTags).isEqualTo(expectedTags);
    }

}
