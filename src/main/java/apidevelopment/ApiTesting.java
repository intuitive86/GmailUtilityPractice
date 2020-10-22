package apidevelopment;

/*public class ApiTesting {
    public static class AuthBearerTokenTest extends Base {
        public WebDriver driver;

        private static final String baseUri = "https://qa-mobile-app-auth-ext.clearcollateral.com";
        private static final String basePath = "/api/v1";
        private static String authSessionId;
        private static String authUserName;

        private static String verifiedCode;

        static {
            try {
                verifiedCode = GmailUtility.getVerificationCode();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public AuthBearerTokenTest() {
        }

        public static void getInitialAuthSignIn() {
            String res = given()
                    .header("X-API-KEY", Config.API_KEY)
                    .queryParam("challengeMethod", "EMAIL")
                    .header("alias", Config.CI_CREDENTIAL)
                    .when()
                    .log().all()
                    .get(baseUri + basePath + "/users/signin")
                    .then()
                    .log().all()
                    .extract().asString();

            JsonPath authApi = new JsonPath(res);
            authSessionId = authApi.get("session").toString();
            authUserName = authApi.get("username").toString();
        }

        public static String getToken(String key) {
            String res = given()
                    .header("X-API-KEY", Config.API_KEY)
                    .header("session", authSessionId)
                    .header("username", authUserName)
                    .queryParam("code", verifiedCode)
                    .log().all()
                    .get(baseUri + basePath + "/users/verifyCode")
                    .then()
                    .log().all()
                    .extract().asString();

            JsonPath js = new JsonPath(res);
            return js.get(key).toString();
        }

        @Test
        public void testAuthValidator() throws InterruptedException, IOException, GeneralSecurityException {
            getInitialAuthSignIn();
            sleep(15000);
            String sentCode = GmailUtility.getVerificationCode();
            System.out.println(sentCode);
            sleep(2000);
            String bearerToken = getToken("accessToken");
            System.out.println(bearerToken);
            driver = initializeDriver();
            driver.get(Config.QA_MOBILE);
            QaMobileAuth qaMobile = new QaMobileAuth(driver);
            sleep(3000);
            qaMobile.getAuthorizeButton().click();
            sleep(3000);
            qaMobile.getValueField().sendKeys("Bearer " + bearerToken);
            sleep(7000);
            qaMobile.getAuthorizeFinal().click();
            sleep(5000);
            qaMobile.getCloseAuthWindow();
        }
    }
}*/
