package computerdatabase;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class RecordedSimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocol = http.baseUrl("https://computer-database.gatling.io")
            .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
            .acceptEncodingHeader("gzip, deflate, br")
            .acceptLanguageHeader("en-IN,en-GB;q=0.9,en-US;q=0.8,en;q=0.7")
            .upgradeInsecureRequestsHeader("1")
            .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
    ChainBuilder addComputer = exec(http("clickCreateNew").get("/computers/new"), pause(8), http("addNewComputer").post("/computers")

            .formParam("name", "LifeTime")
            .formParam("introduced", "")
            .formParam("discontinued", "")
            .formParam("company", ""), pause(6)
    );
    ChainBuilder searchComputer = exec(http("searchForComputer").get("/computers?f=LifeTime"), pause(2)

    );
    private final ScenarioBuilder scn = scenario("RecordedSimulation").exec(addComputer, searchComputer);

    ScenarioBuilder users = scenario("Users").exec(addComputer);
    ScenarioBuilder admins = scenario("Admin").exec(addComputer, searchComputer);


    {
        setUp(
                users.injectOpen(rampUsers(10).during(10)),
                admins.injectOpen(rampUsers(3).during(10))).protocols(httpProtocol);
    }
}
