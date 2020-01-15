package qa.com.solarSystemInformation;

public interface IWebServiceMock {
    boolean authenticate(String userID, String password);
    String getStatusInfo(String astronomicalObjectClassification);
}
