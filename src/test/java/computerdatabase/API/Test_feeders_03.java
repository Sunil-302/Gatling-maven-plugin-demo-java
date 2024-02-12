package computerdatabase.API;

import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class Test_feeders_03 extends Simulation {
/*    //protocol
    private HttpProtocolBuilder httpProtocol = http.baseUrl("https://reqres.in/api");

    //scenario
    private ScenarioBuilder scn1 = scenario("API_RequestDemo_ReqRes1")
            .exec(http("POST_creatingUser")
                            .post("/users")
                            .header("content-type", "application/json")
                            .body(RawFileBody("data/API_Test_02.json"))
                            .check(
                                    status().is(201),
                                    jsonPath("$.name").is("morpheus"))
                    , pause(1));

    private ScenarioBuilder scn2 = scenario("API_RequestDemo_ReqRes2")
            .exec(http("PUT_updateUSer")
                            .put("/users/2")
                            .header("content-type", "application/json")
                            .body(RawFileBody("data/API_Test_02.json"))
                            .check(
                                    status().is(200),
                                    jsonPath("$.job").is("zion resident"))
                    , pause(1));*/


    FeederBuilder.Batchable<String> feeder = csv("data/API_Test_feeders_03.csv").circular();

    private ScenarioBuilder scn = scenario("API_feeder")
            .repeat(2).on(
            feed(feeder)
            .exec(session -> {
                System.out.println("The Name is "+session.getString("NAME"));
                System.out.println("The Job is "+session.getString("JOB"));
                return session;
            })
            .pause(1)
            );





    //setUp

    {
        setUp(
                scn.injectOpen(atOnceUsers(2))

        );
    }


//    {
//        setUp(
//                scn1.injectOpen(atOnceUsers(10)),
//                scn2.injectOpen(rampUsers(10).during(5))
//        ).protocols(httpProtocol);
//    }


}
