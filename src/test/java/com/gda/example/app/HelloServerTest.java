package com.gda.example.app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance;

import org.junit.jupiter.api.BeforeAll;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.stream.Collectors;
import java.nio.charset.StandardCharsets;


/**
 * Unit test for simple App.
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class HelloServerTest 
{
    MyServer httpserver;

    @BeforeAll                                      
    void setUp() {
        httpserver = new MyServer();
    }

    @Test
    @Order(1)
    public void shouldAnswerWithTrue()
    {
        int port = 9090;
        httpserver.Start(port);
        assertTrue(Utils.serverListening("localhost", 9090));
    }

    @Test
    @Order(2)
    public void shouldAnswerWithHello() {
        try
        {
            URL url = new URL("http://localhost:9090/hello");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            
            assertEquals(con.getResponseCode(), 200);
            
            InputStream inputStream = new ByteArrayInputStream(con.getInputStream().readAllBytes());
            String text = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                  .lines()
                  .collect(Collectors.joining("\n"));
            assertEquals(text, "<h1>Hello</h1>");

        }
        catch (Exception e)
        {
            fail("Unexpected exception was thrown:" + e);
        }
    }

    @Test
    @Order(3)
    public void shouldAnswerWithFalse()
    {
        httpserver.Stop();
        assertFalse(Utils.serverListening("localhost", 9090));
    }

}
