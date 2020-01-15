package qa.com.solarSystemInformation;

public class WebServiceStub {
    private boolean isAuthenticated;

    public void authenticate(String userID, String password) {
        isAuthenticated = true; //the format of the userID and password are checked when initialiseAOC() is called, the authentication is handled by the web service
                                //this method just ensures that a user is authenticated before the other methods in the web service class are called
    }

    public String getStatusInfo (String astronomicalObjectClassificationCode) {
        String statusInfo = "";
        if (isAuthenticated) {
            statusInfo = "PEar150M,Planet,Earth,265,6371,384400,5.972 × 10^24";
        }
        return statusInfo;
    }
}
