
package qa.com.solarSystemInformation;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import static org.junit.jupiter.api.Assertions.*;


public class SolarSystemInformationTest {

    private SolarSystemInformation cut;
    private String validUserID = "AB1234";
    private String validPassword = "Ab12!&CDe£80f";
    private String validAOC = "PEar150M";

    private IWebServiceMock webServiceMock;

    @BeforeEach
    public void setUp () {
        webServiceMock = createMock(IWebServiceMock.class);
    }


    @Test
    void userID_returned_when_valid_data_is_input() throws invalidUserInputException {
        //arrange
        cut = new SolarSystemInformation(validUserID, validPassword, webServiceMock);
        String expectedResult = validUserID;

        //act
        String actualResult = cut.getUserID();

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void invalid_user_input_exception_thrown_when_invalid_data_is_input() {
        //arrange
        String invalidUserID = "&%ad3R";
        cut = new SolarSystemInformation(invalidUserID, validPassword, webServiceMock);
        String expectedMessage = "Invalid userID format entered";

        //act
        Exception exception = assertThrows(invalidUserInputException.class, () -> {
            cut.getUserID();
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void invalid_user_input_exception_thrown_when_0000_found_in_user_input() {
        //arrange
        String invalidUserIDContaining0000 = "AB0000";
        cut = new SolarSystemInformation(invalidUserIDContaining0000, validPassword, webServiceMock);
        String expectedMessage = "Invalid userID format entered";

        //act
        Exception exception = assertThrows(invalidUserInputException.class, () -> {
            cut.getUserID();
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void password_returned_when_valid_data_is_input() throws invalidUserInputException {
        //arrange
        cut = new SolarSystemInformation(validUserID, validPassword, webServiceMock);
        String expectedResult = validPassword;

        //act
        String actualResult = cut.getUserPassword();

        //assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void invalid_user_input_exception_thrown_when_invalid_password_length_is_input() {
        //arrange
        String invalidPasswordLength = "xv4£1D";
        cut = new SolarSystemInformation(validUserID, invalidPasswordLength, webServiceMock);
        String expectedMessage = "Invalid password format entered";

        //act
        Exception exception = assertThrows(invalidUserInputException.class, () -> {
            cut.getUserPassword();
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void invalid_user_input_exception_thrown_when_invalid_password_format_is_input() {
        //arrange
        String invalidPasswordFormat = "aaaaaaaaaa"; //password must contain at least one upper case letter, number, and special character
        cut = new SolarSystemInformation(validUserID, invalidPasswordFormat, webServiceMock);
        String expectedMessage = "Invalid password format entered";

        //act
        Exception exception = assertThrows(invalidUserInputException.class, () -> {
            cut.getUserPassword();
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void AOC_returned_when_valid_data_input() throws invalidUserInputException {
        //arrange
        cut = new SolarSystemInformation(validUserID, validPassword, webServiceMock);
        cut.setAstronomicalObjectClassificationCode(validAOC);
        String expectedResult = validAOC;

        //act
        String actualResult = cut.getAstronomicalObjectClassificationCode();

        //assert
        assertEquals(expectedResult, actualResult);
    }



    @Test
    void invalid_web_service_data_format_exception_thrown_when_invalid_AOC_input() throws invalidUserInputException {
        //arrange
        String invalidAOC = "£4ty3135";
        cut = new SolarSystemInformation(validUserID, validPassword, webServiceMock);
        String expectedMessage = "Invalid AOC data format input";

        //act
        Exception exception = assertThrows(invalidUserInputException.class, () -> {
            cut.initialiseAOCDetails(invalidAOC);
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void invalid_web_service_data_format_exception_thrown_when_invalid_object_name_returned() {
        //arrange
        String invalidObjectName = "sirius B"; //object types should be Pascal cased
        cut = new SolarSystemInformation(validUserID, validPassword, webServiceMock);
        cut.setObjectName(invalidObjectName);
        String expectedMessage = "Invalid Object Name data format returned from web service";

        //act
        Exception exception = assertThrows(invalidWebServiceDataFormatException.class, () -> {
            cut.getObjectName();
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void initialiseAOCDetails_method_throws_invalid_format_exception_when_invalid_AOC_input() {
        //arrange
        String invalidAOC = "kjngfd3 fafd";
        cut = new SolarSystemInformation(validUserID, validPassword, webServiceMock);
        cut.setAstronomicalObjectClassificationCode(invalidAOC);
        String expectedMessage = "Invalid AOC data format input";

        //act
        Exception exception = assertThrows(invalidUserInputException.class, () -> {
            cut.initialiseAOCDetails(invalidAOC);
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

//    @Test
//    void initialiseAOC_returns_string_of_info_when_valid_AOC_input() throws invalidUserInputException {
//        //arrange
//        cut = new SolarSystemInformation(validUserID, validPassword);
//        String expectedResult = "PEar150M,Planet,Earth,265,6371,384400,5.972 × 10^24";
//
//        //act
//        String actualResult = cut.initialiseAOCDetails(validAOC);
//
//        //assert
//        assertTrue(actualResult.contains(expectedResult));
//
//    }

    @Test
    void initialiseAOC_throws_exception_when_invalid_userID_input() {
        //arrange
        String invalidUsername = "aaaaaaaaaa";
        cut = new SolarSystemInformation(invalidUsername, validPassword, webServiceMock);
        String expectedMessage = "Invalid userID format entered";

        //act
        Exception exception = assertThrows(invalidUserInputException.class, ()-> {
            cut.initialiseAOCDetails(validAOC);
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void initialiseAOC_throws_exception_when_invalid_password_input () {
        //arrange
        String invalidPassword = "bbbbbbb";
        cut = new SolarSystemInformation(validUserID,invalidPassword, webServiceMock);
        String expectedMessage = "Invalid password format entered";

        //act
        Exception exception = assertThrows(invalidUserInputException.class, ()-> {
            cut.initialiseAOCDetails(validAOC);
        });

        String actualMessage = exception.getMessage();

        //assert
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void set_object_type_returns_valid_data () throws invalidUserInputException, invalidWebServiceDataFormatException {
        //arrange
        expect(webServiceMock.authenticate(validUserID,validPassword)).andReturn(true);
        expect(webServiceMock.getStatusInfo(validAOC)).andReturn("PEar150M,Planet,Earth,365,6371,384400,5972000000000000000000000000");
        replay(webServiceMock);

        cut = new SolarSystemInformation(validUserID,validPassword,webServiceMock);
        cut.initialiseAOCDetails(validAOC);

        //act
        String actualResult = cut.getObjectType();

        //assert
        assertTrue("Planet".equals(actualResult));
        verify(webServiceMock);



    }


    @Test
    void toString_method_returns_correctly_formatted_string_when_valid_AOC_input () throws invalidUserInputException {
        //arrange
        cut = new SolarSystemInformation(validUserID,validPassword, webServiceMock);
        cut.initialiseAOCDetails(validAOC);
        String expectedResult = "Planet, Earth [PEar150M] 3.84E+5 km, 5.97E+27 kg";

        //act
        String actualResult = cut.toString();

        //assert
        assertTrue(actualResult.contains(expectedResult));

    }

    @Test
    void invalid_userID_sets_all_fields_correctly () throws invalidWebServiceDataFormatException, invalidUserInputException {
        //arrange
        String invalidUserID = "dsfafagd";
        cut = new SolarSystemInformation(invalidUserID,validPassword, webServiceMock);
        cut.initialiseAOCDetails(validAOC);
        String expectedResult = "Not allowed";

        //act
        String actualResult = cut.getObjectName();

        //assert
        assertEquals(expectedResult,actualResult);
    }






}
