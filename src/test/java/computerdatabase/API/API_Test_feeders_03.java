package computerdatabase.API;

import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class API_Test_feeders_03 extends Simulation {
    //protocol
    private HttpProtocolBuilder httpProtocol = http.baseUrl("https://reqres.in/api");

    //scenario
    FeederBuilder.Batchable<String> feeder = csv("data/API_Test_feeders_03.csv").circular();

    private ScenarioBuilder scn = scenario("API_Test_feeder")
            .repeat(2).on(
                    feed(feeder)
                            .exec(session -> {
                                System.out.println("The Name is " + session.getString("NAME"));
                                System.out.println("The Job is " + session.getString("JOB"));
                                return session;
                            })
                            .pause(1)
                            .exec(http("POST_creatingUser")
                                            .post("/users")
                                            .header("content-type", "application/json")
                                            .body(StringBody("{\n" +
                                                    "  \"name\": \"#{NAME}\",\n" +
                                                    "  \"job\": \"AutomationTEster\"\n" +
                                                    "}"))
                                            .check(
                                                    status().is(201)
                                                    , jsonPath("$.name").is(session -> session.getString("NAME"))
                                            )
                                    , pause(1))
            );

    //setUp
    {
        setUp(
                scn.injectOpen(rampUsers(10).during(5))
        ).protocols(httpProtocol);
    }


}
