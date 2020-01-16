package qa.com.solarSystemInformation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private IWebServiceMock webServiceMock;

    private List<String> astroInfoList;

    private MathContext precision = new MathContext(3);

    public SolarSystemInformation(String userID, String userPassword, IWebServiceMock webServiceMock) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.webServiceMock = webServiceMock;
    }

    public void initialiseAOCDetails (String astronomicalObjectClassificationCode) throws invalidUserInputException {
        getUserID(); //validates userID format
        getUserPassword(); //validates user password format

        webServiceMock.authenticate(getUserID(),getUserPassword()); //web service authenticates the correctly formatted details

        String info;
        if (astronomicalObjectClassificationCode.matches("[A-Z][0-9]{0,8}[A-Z][a-z]{2}[0-9]{1,3}[T,M,B,L,TL]")) {
            this.astronomicalObjectClassificationCode = astronomicalObjectClassificationCode;

            info = webServiceMock.getStatusInfo(astronomicalObjectClassificationCode);

//            WebServiceStub stub = new WebServiceStub();
//            stub.authenticate(getUserID(),getUserPassword());
//            info = stub.getStatusInfo(astronomicalObjectClassificationCode);

            astroInfoList = new ArrayList<>(Arrays.asList(info.split(",")));

            astronomicalObjectClassificationCode = astroInfoList.get(0);
            objectType = astroInfoList.get(1);
            objectName = astroInfoList.get(2);
            orbitalPeriod = Integer.parseInt(astroInfoList.get(3));
            radius = new BigDecimal(astroInfoList.get(4),precision);
            semiMajorAxis = new BigDecimal(astroInfoList.get(5),precision);
            mass = new BigDecimal(astroInfoList.get(6),precision);

        } else {
            throw new invalidUserInputException("Invalid AOC data format input");
        }
    }

    public String getUserID() throws invalidUserInputException {
        if (!userID.contains("0000") && userID.matches("[A-Z]{2}[0-9]{4}")) {
            this.userID = userID;
        } else {
            astronomicalObjectClassificationCode = null;
            objectType = "Not allowed";
            objectName = "Not allowed";
            orbitalPeriod = 0;
            radius = BigDecimal.ZERO;
            semiMajorAxis = BigDecimal.ZERO;
            mass = BigDecimal.ZERO;

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

    public String getAstronomicalObjectClassificationCode() {
        return astronomicalObjectClassificationCode; //validation occurs in initialiseAOC()
    }

    void setAstronomicalObjectClassificationCode(String astronomicalObjectClassificationCode) {
        this.astronomicalObjectClassificationCode = astronomicalObjectClassificationCode;
    }

    public String getObjectType() throws invalidWebServiceDataFormatException {
        if (objectType.matches("[A-Z][a-z]* ?[A-Z]?[a-z]*")) {
        } else {
            throw new invalidWebServiceDataFormatException("Invalid Object Type data format returned from web service");
        }
        return objectType;
    }

    void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectName() throws invalidWebServiceDataFormatException {
        if (objectName.matches("[0-9]*([A-Z][a-z]*)+") || objectName.equals("Not Allowed")) {
        } else {
            throw new invalidWebServiceDataFormatException("Invalid Object Name data format returned from web service");
        }
        return objectName;
    }

    void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public boolean isExists() { return exists; }

    void setExists(boolean exists) {
        this.exists = exists;
    }

    public int getOrbitalPeriod() { return orbitalPeriod; }

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

    @Override
    public String toString() {
        return objectType + ", " + objectName + " [" + astronomicalObjectClassificationCode + "] " +
                semiMajorAxis + " km, " + mass + " kg";
    }
}