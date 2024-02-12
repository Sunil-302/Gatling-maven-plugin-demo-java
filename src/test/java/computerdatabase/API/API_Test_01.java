package computerdatabase.API;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class API_Test_01 extends Simulation {
    //protocol
    private HttpProtocolBuilder httpProtocol = http.baseUrl("https://reqres.in/api/users");

    //scenario

    private ScenarioBuilder scn = scenario("API_RequestDemo_ReqRes")
            .exec(http("GET_SingleUSer_Reqres")
                            .get("/2")
                            .check(
                                    status().is(200),
                                    jsonPath("$.data.first_name").is("Janet"))
                    , pause(1));

    //setUp
    {
        setUp(
                scn.injectOpen(rampUsers(10).during(5))).protocols(httpProtocol);
    }


}
