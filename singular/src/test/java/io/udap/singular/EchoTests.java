package io.udap.singular;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class EchoTests {

    ContractsRepository repository = ContractsRepository.getInstance();
    Echo echo = repository.submitEcho();

    @Test
    public void testGreeter(){
        echo.newGreeting("oops");
        String greet = (String) echo.greet();
        System.out.println(greet);
        echo.newGreeting("oops3");
        greet = (String) echo.greet();
        System.out.println(greet);

    }

    @Test
    public void testLogging(){

        Object log = echo.log();
        assertNull(log);
    }
}
