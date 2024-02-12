package computerdatabase.API;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class API_Test_02 extends Simulation {
    //protocol
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
                    , pause(1));

    //setUp
    {
        setUp(
                scn1.injectOpen(atOnceUsers(10)),
                scn2.injectOpen(rampUsers(10).during(5))
        ).protocols(httpProtocol);
    }


}
