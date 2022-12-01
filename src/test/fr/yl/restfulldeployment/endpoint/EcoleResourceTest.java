package fr.yl.restfulldeployment.endpoint;

import jakarta.servlet.http.HttpServletResponse;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.jboss.resteasy.spi.Dispatcher;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class EcoleResourceTest {

    MockHttpRequest request;
    MockHttpResponse response;
    Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
    POJOResourceFactory noDefaults = new POJOResourceFactory(EcoleResource.class);

    @Test
    void getById() throws URISyntaxException, UnsupportedEncodingException {
        dispatcher.getRegistry().addResourceFactory(noDefaults);
        request = MockHttpRequest.get("/ecoles/1");
        response = new MockHttpResponse();
        dispatcher.invoke(request, response);
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        assertTrue(response.getContentAsString().contains("\"ecoleId\":1"));
    }


}