package qa.com.solarSystemInformation;

import java.math.BigDecimal;

public class SolarSystemInformation {

    private String userID;
    private String userPassword;
    private String astronomicalObjectClassificationCode;
    private String objectType;
    private String objectName;
    private boolean exists;
    private int orbitalPeriod;
    private BigDecimal radius;
    private BigDecimal semiMajorAxis;
    private BigDecimal mass;

    public SolarSystemInformation(String userID, String userPassword) {
        this.userID = userID;
        this.userPassword = userPassword;
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
        if (userPassword.length() >= 10 && userPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^£&+=()*?><;:])(?=\\S+$).{10,}$")) {
            this.userPassword = userPassword;
        } else {
            throw new invalidUserInputException("Invalid password format entered");
        }
        return userPassword;
    }

    public String getAstronomicalObjectClassificationCode() throws invalidWebServiceDataFormatException {
        if (astronomicalObjectClassificationCode.matches("[A-Z][0-9]{0,8}[A-Z][a-z]{2}[0-9]{1,3}[T,M,B,L,TL]")) {
        } else {
            throw new invalidWebServiceDataFormatException("Invalid data format returned from web service");
        }

        return astronomicalObjectClassificationCode;
    }

    private void setAstronomicalObjectClassificationCode(String astronomicalObjectClassificationCode) {
        this.astronomicalObjectClassificationCode = astronomicalObjectClassificationCode;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public int getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(int orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public BigDecimal getRadius() {
        return radius;
    }

    public void setRadius(BigDecimal radius) {
        this.radius = radius;
    }

    public BigDecimal getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public void setSemiMajorAxis(BigDecimal semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }

    public BigDecimal getMass() {
        return mass;
    }

    public void setMass(BigDecimal mass) {
        this.mass = mass;
    }
}