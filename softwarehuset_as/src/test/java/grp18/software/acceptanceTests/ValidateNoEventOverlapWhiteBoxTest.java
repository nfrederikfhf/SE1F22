package grp18.software.acceptanceTests;
import grp18.software.*;
import grp18.software.app.RegistrationApp;
import grp18.software.domain.Worker;
import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidateNoEventOverlapWhiteBoxTest extends TestCase{

    private Worker worker;
    private ErrorMessageHolder errorMessage;

    @Test
    @DisplayName("Case A")
    public void testValidateNoEventOverlapCaseA(){

        worker.registerHours();
    }

}
