package qa.com.solarSystemInformation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SolarSystemInformationTest {

    private SolarSystemInformation cut;

    @Test
    void userID_returned_when_valid_data_is_input () throws invalidUserInputException {
        //arrange
        String validUserID = "AB1234";
        cut = new SolarSystemInformation(validUserID);
        String expectedResult = "AB1234";

        //act
        String actualResult = cut.getUserID();

        //assert
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void invalid_user_input_exception_thrown_when_invalid_data_is_input () {
        //arrange
        String invalidUserID = "&%ad3R";
        cut = new SolarSystemInformation(invalidUserID);
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
        cut = new SolarSystemInformation(invalidUserIDContaining0000);
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
    void password_returned_when_valid_data_is_input () {
        //arrange
        String validUserPassword = "AB1234";
        cut = new SolarSystemInformation(validUserPassword);
        String expectedResult = "AB1234";

        //act
        String actualResult = cut.getUserPassword();

        //assert
        assertEquals(expectedResult,actualResult);
    }
    }
}
