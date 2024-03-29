import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Value;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

public class Communicator
{
    private static Sheets sheetService; 
;
    private static String applicationName = "Sheets Test";
    private static String ssID = "16K4S_jWzB4VRaAj69wYqFYZetamQH4i9lDjjWp98O6U";

    public static Credential authorize() throws IOException, GeneralSecurityException
    {
        InputStream in = Communicator.class.getResourceAsStream("credentials.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(),
                new InputStreamReader(in));

        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
                clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline").build();

        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

        return credential;
    }

    public static Sheets getSheetService() throws IOException, GeneralSecurityException
    {
        Credential credential = authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), credential).setApplicationName(applicationName)
                .build();
    }

    // get value in cells for range
    // probably wont use as your just uploading lol — good to have tho
    public static void getItems() throws IOException, GeneralSecurityException
    {
        sheetService = getSheetService();
        String range = "A2:D5";

        ValueRange response = sheetService.spreadsheets().values().get(ssID, range).execute();

        List<List<Object>> values = response.getValues();

        if (values == null || values.isEmpty())
        {
            System.out.println("No data found.");
        }
        else
        {
            for (List row : values)
            {
                System.out.printf("%s, %s, %s, %s\n", row.get(0), row.get(1), row.get(2), row.get(3));
            }
        }
    }

    // upload order — input is string array
    // th bread and butter of this class
    public static void uploadOrder(String[] order) throws IOException, GeneralSecurityException
    {
        sheetService = getSheetService();

        ValueRange appendBody = new ValueRange().setValues(Arrays.asList(Arrays.asList(order)));

        AppendValuesResponse appendResult = sheetService.spreadsheets().values()
                .append(ssID, "A:E", appendBody).setValueInputOption("USER_ENTERED")
                .setInsertDataOption("INSERT_ROWS").setIncludeValuesInResponse(true)
                .execute();
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException
    {
        String[] order = {"Kathryn", "14/12/19", "Cotton", "11", "18/12/19"};
        uploadOrder(order);
    }
}
