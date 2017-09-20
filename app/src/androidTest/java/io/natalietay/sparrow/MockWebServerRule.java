package io.natalietay.sparrow;

import org.junit.rules.ExternalResource;

import java.io.IOException;

import okhttp3.mockwebserver.MockWebServer;

public class MockWebServerRule extends ExternalResource {

    private MockWebServer mockWebServer = new MockWebServer();

    @Override
    protected void before() throws IOException {
        mockWebServer.start(8008);
    }

    @Override
    protected void after() {
        try {
            mockWebServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MockWebServer getMockWebServer() {
        return mockWebServer;
    }
}
