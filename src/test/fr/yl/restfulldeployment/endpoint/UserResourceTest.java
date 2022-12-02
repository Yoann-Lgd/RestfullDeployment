package fr.yl.restfulldeployment.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.yl.restfulldeployment.security.Tokened;
import fr.yl.restfulldeployment.security.User;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.jboss.resteasy.spi.Dispatcher;

import java.io.IOException;
import java.net.URISyntaxException;
import static org.junit.jupiter.api.Assertions.*;


class UserResourceTest {

    MockHttpRequest request;
    MockHttpResponse response;
    Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
    POJOResourceFactory noDefaults = new POJOResourceFactory(UserResource.class);

    @Test
    void isUnauthorized() throws URISyntaxException, IOException {
        dispatcher.getRegistry().addResourceFactory(noDefaults);
        request = MockHttpRequest.post("/user/login");
        request.accept(MediaType.APPLICATION_JSON_TYPE);
        request.contentType(MediaType.APPLICATION_JSON_TYPE);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        user.setLogin("fail");
        user.setPassword("fail");
        byte[] userJson = objectMapper.writeValueAsBytes(user);
        request.content(userJson);
        response = new MockHttpResponse();
        dispatcher.invoke(request, response);
        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
    }

   /* @Test
    void isAuthorized() throws URISyntaxException, IOException {
        dispatcher.getRegistry().addResourceFactory(noDefaults);
        request = MockHttpRequest.post("/user/login");
        request.accept(MediaType.APPLICATION_JSON_TYPE);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        byte[] userJson = objectMapper.writeValueAsBytes(user);
        request.content(userJson);
        response = new MockHttpResponse();
        dispatcher.invoke(request, response);
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        assertTrue(response.getOutputHeaders().containsKey(Tokened.TOKEN));
        assertTrue(response.getOutputHeaders().get(Tokened.TOKEN).toString().contains("Bearer ey"));
    }*/
}