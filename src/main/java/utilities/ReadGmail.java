package utilities;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.StringUtils;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import config.Config;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReadGmail {


    private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final String user = "me";
    static Gmail service = null;
    private static File filePath = new File(System.getProperty("user.dir") + "/credentials.json");

        public static void main (String[]args) throws IOException, GeneralSecurityException {

            // Read credentials.json
            InputStream in = new FileInputStream(filePath);
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

            // Credential builder
            // Still looking for updated method of GoogleCredential.Builder
            // Need to look into Gmail.builder
            Credential authorize = new GoogleCredential.Builder().setTransport(GoogleNetHttpTransport.newTrustedTransport())
                    .setJsonFactory(JSON_FACTORY)
                    .setClientSecrets(clientSecrets.getDetails().getClientId(),
                            clientSecrets.getDetails().getClientId())
                    .build()
                    .setAccessToken(getAccessToken()) // Call getAccessToken
                    .setRefreshToken(Config.REFRESH_TOKEN); // Refresh token never expires

            // Create Gmail service
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, authorize).setApplicationName(ReadGmail.APPLICATION_NAME).build();

            // Access Gmail inbox
            Gmail.Users.Messages.List request = service.users().messages().list(user).setQ("from:" + "intuitivehunter@gmail.com");
            ListMessagesResponse messagesResponse = request.execute();
            request.setPageToken(messagesResponse.getNextPageToken());

            // Get the ID of the email you are looking for
            String messageId = messagesResponse.getMessages().get(0).getId(); // get(0) is reading the latest email. If 1, then it'll read the second latest, etc.
            Message message = service.users().messages().get(user, messageId).execute();

            // Print email body - Still testing
            String emailBody = StringUtils
                    .newStringUtf8(Base64.decodeBase64(message.getPayload().getParts().get(0).getBody().getData()));
            System.out.println("Email body : " + emailBody);

        }

        private static String getAccessToken() {
            try {
                Map<String, Object> params = new LinkedHashMap<>();
                params.put("grant_type", "refresh_token");
                params.put("client_id", Config.CLIENT_ID);
                params.put("client_secret", Config.CLIENT_SECRET);
                params.put("refresh_token", Config.REFRESH_TOKEN);

                StringBuilder postData = new StringBuilder();
                for (Map.Entry<String, Object> param : params.entrySet()) {
                    if (postData.length() != 0) {
                        postData.append('&');
                    }
                    postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                    postData.append('=');
                    postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                }
                byte[] postDataBytes = postData.toString().getBytes("UTF-8");

                URL url = new URL(Config.TOKEN_URI);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setDoOutput(true);
                con.setUseCaches(false);
                con.setRequestMethod("POST");
                con.getOutputStream().write(postDataBytes);

                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer buffer = new StringBuffer();
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    buffer.append(line);
                }

                JSONObject json = new JSONObject(buffer.toString());
                String accessToken = json.getString("access_token");
                return accessToken;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Test
        public void testTokenAccess() {
            String bearerToken = getAccessToken();
            System.out.println("This is the bearer token: " + bearerToken);
        }
    }
}
