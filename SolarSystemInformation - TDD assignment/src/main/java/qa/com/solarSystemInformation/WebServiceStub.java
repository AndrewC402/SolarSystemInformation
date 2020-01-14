package qa.com.solarSystemInformation;

public class WebServiceStub {
    private boolean isAuthenticated;

    public boolean authenticate(String userID, String password) {
        return true;
    }

    public String getStatusInfo () {
        String statusInfo = "";
        if (isAuthenticated) {
            statusInfo = "PEar150M,Planet,Earth,265,6371,384400,5.972 × 10^24";
        }
        return statusInfo;
    }
}
