package qa.com.solarSystemInformation;

public class SolarSystemInformation {

    private String userID;
    private String password;


    public SolarSystemInformation(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public String getUserID() throws invalidUserInputException {
            if (!userID.contains("0000") && userID.matches("[A-Z]{2}[0-9]{4}")) {
                this.userID = userID;
            } else {
                throw new invalidUserInputException("Invalid userID format entered");
            }
        return userID;
    }

    public String getUserPassword() throws invalidUserInputException {
        if (password.length() >= 10) {
            this.password = password;
        } else {
            throw new invalidUserInputException("Invalid password format entered");
        }
        return password;
    }
}
