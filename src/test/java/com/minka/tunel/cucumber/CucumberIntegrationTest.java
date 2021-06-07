package com.minka.tunel.cucumber;

import com.minka.tunel.TunelApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest(classes = { TunelApplication.class,
                            CucumberIntegrationTest.class},
                            webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberOptions(plugin = {"pretty"}, features = "src/test/resources/features")
public class CucumberIntegrationTest
{

}
