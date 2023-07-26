package com.gda.example.app;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class HelloMainTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        HelloMain hm = new HelloMain();
        hm.main(null);
        assertTrue(Utils.serverListening("localhost", 8080));
    }
}
