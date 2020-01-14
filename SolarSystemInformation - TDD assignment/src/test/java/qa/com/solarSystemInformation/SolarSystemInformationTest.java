package qa.com.solarSystemInformation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SolarSystemInformationTest {

    private SolarSystemInformation cut;
    private String validUserID = "AB1234";
    private String validPassword = "Ab12!&CDe£80f";
    private String validAOC = "SSun27";


    @Test
    void userID_returned_when_valid_data_is_input () throws invalidUserInputException {
        //arrange
        cut = new SolarSystemInformation(validUserID,validPassword);
        String expectedResult = validUserID;

        //act
        String actualResult = cut.getUserID();

        //assert
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void invalid_user_input_exception_thrown_when_invalid_data_is_input () {
        //arrange
        String invalidUserID = "&%ad3R";
        cut = new SolarSystemInformation(invalidUserID,validPassword);
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
    void invalid_user_input_exception_thrown_when_0000_found_in_user_input () {
        //arrange
        String invalidUserIDContaining0000 = "AB0000";
        cut = new SolarSystemInformation(invalidUserIDContaining0000,validPassword);
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
    void password_returned_when_valid_data_is_input () throws invalidUserInputException {
        //arrange
        cut = new SolarSystemInformation(validUserID,validPassword);
        String expectedResult = validPassword;

        //act
        String actualResult = cut.getUserPassword();

        //assert
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void invalid_user_input_exception_thrown_when_invalid_password_length_is_input () {
        //arrange
        String invalidPasswordLength = "xv4£1D";
        cut = new SolarSystemInformation(validUserID,invalidPasswordLength);
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
    void invalid_user_input_exception_thrown_when_invalid_password_format_is_input () {
        //arrange
        String invalidPasswordFormat = "aaaaaaaaaa"; //password must contain at least one upper case letter, number, and special character
        cut = new SolarSystemInformation(validUserID,invalidPasswordFormat);
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
    void AOC_returned_when_valid_data_input () {
        //arrange
        cut = new SolarSystemInformation(validUserID,validPassword);
        cut.setAstronomicalObjectClassificationCode(validAOC);
        String expectedResult = validAOC;

        //act
        String actualResult = cut.getAstronomicalObjectClassificationCode();

        //assert
        assertEquals(expectedResult,actualResult);
        

    }
    }
