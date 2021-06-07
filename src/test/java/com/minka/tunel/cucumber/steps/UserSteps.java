package com.minka.tunel.cucumber.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minka.tunel.domain.model.User;
import com.minka.tunel.domain.repository.UserRepository;
import com.minka.tunel.domain.service.UserService;
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
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

public class UserSteps {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private List<User> expectedUsers;

    private List<User> actualUsers;

    @Before
    public void setup()
    {
        expectedUsers = new ArrayList<>();
        actualUsers = new ArrayList<>();
    }

    @Given("^the following users$")
    public void givenTheFollowingUsers(final List<User> users){
        expectedUsers.addAll(users);
        userRepository.saveAll(users);
    }

    @When("^the user requests all the users$")
    public void whenTheUserRequestsAllUsers(Pageable pageable)
    {
        Page<User> users = userService.getAllUsers(pageable);
        actualUsers = users.getContent();
    }

    @Then(("^all the users are returned$"))
    public void thenAllUsersAreReturned()
    {
        assertThat(actualUsers).isEqualTo(expectedUsers);
    }

}
