package ApiTesting;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static BaseTest.ApiPaths.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class TestingApis {

    private static RequestSpecification requestSpecification;
    private static ResponseSpecification responseSpecification;

    @BeforeClass
    public static void createRequestSpecification() {

        requestSpecification = new RequestSpecBuilder().
                setBaseUri("http://deckofcardsapi.com/api/deck").
                build();
    }

    @BeforeClass
    public static void createResponseSpecification() {

        responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectBody("success", equalTo(true)).
                build();
    }

//Tested URL = "https://deckofcardsapi.com/api/deck/new/"
    @Test
    public void getNewDeck() {

                given().
                        spec(requestSpecification).
                        when().
                        get(Get_New_Deck).
                        then().
                        spec(responseSpecification).
                        and().
                        assertThat().body("shuffled", equalTo(false));
    }

/*  for hitting post simply replace .get() to .post(), for the instance I was writing post has server side redirecting issue so did with get
    Tested URL = "http://deckofcardsapi.com/api/deck/new/?jokers_enabled=true"
 */
    @Test
    public void addingJokers() {

        try {
            with().
                    spec(requestSpecification).
                    when().
                    queryParam("jokers_enabled",true).
                    get(Post_Adding_Jokers).
                    then().
                    spec(responseSpecification).
                    and().
                    assertThat().body("remaining", equalTo(54));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

//Tested URL = "https://deckofcardsapi.com/api/deck/<<deck_id>>/draw/?count=2"
    @Test
    public void drawCard() {

        try {
            //getting new deck_id
            String deck_id = given().spec(requestSpecification).when().get(Get_New_Deck).then().extract().path("deck_id");

            given().
                         spec(requestSpecification).
                         when().
                         pathParam("deck_id", deck_id).
                         queryParam("count",2).
                         get(Draw_Card).
                         then().
                         spec(responseSpecification).
                         and().
                         assertThat().body("remaining", equalTo(50));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

