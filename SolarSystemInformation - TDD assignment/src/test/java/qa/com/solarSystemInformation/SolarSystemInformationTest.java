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
    void userID_exception_thrown_when_invalid_data_is_input () {
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
}
