package fr.yl.restfulldeployment.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.yl.restfulldeployment.dao.CRKFConnect;
import fr.yl.restfulldeployment.dao.DAOFactory;
import fr.yl.restfulldeployment.security.SecurityTools;
import fr.yl.restfulldeployment.security.Tokened;
import fr.yl.restfulldeployment.security.User;
import fr.yl.restfulldeployment.work.Compte;
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
import java.security.Security;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;


class UserResourceTest {

    MockHttpRequest request;
    MockHttpResponse response;
    Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
    POJOResourceFactory noDefaults = new POJOResourceFactory(UserResource.class);

    @Test
    void unauthorizedLogin() throws URISyntaxException, IOException {
        dispatcher.getRegistry().addResourceFactory(noDefaults);
        request = MockHttpRequest.post("/user/login");
        request.accept(MediaType.APPLICATION_JSON_TYPE);
        request.contentType(MediaType.APPLICATION_JSON_TYPE);
        User user = new User();
        user.setLogin("wrongLogin");
        user.setPassword("wrongPassword");
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] userJson = objectMapper.writeValueAsBytes(user);
        request.content(userJson);
        response = new MockHttpResponse();
        dispatcher.invoke(request, response);
        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
    }

    @Test
    void authorizedLogin() throws URISyntaxException, JsonProcessingException {

        try (Connection connection = CRKFConnect.getInstance()) {

            try (Statement statement = connection.createStatement()) {

                connection.setAutoCommit(false);
                dispatcher.getRegistry().addResourceFactory(noDefaults);
                request = MockHttpRequest.post("/user/login");
                request.accept(MediaType.APPLICATION_JSON_TYPE);
                request.contentType(MediaType.APPLICATION_JSON_TYPE);

                Compte compte = new Compte();
                compte.setEmail("admin@admin.com");
                compte.setPassword(SecurityTools.hash("admin"));
                statement.executeUpdate("DELETE FROM Compte");
                statement.executeUpdate("DBCC CHECKIDENT ('Compte', RESEED, 0)");
                statement.executeUpdate("INSERT INTO Compte (email,password) VALUES ('" + compte.getEmail() + "','" + compte.getPassword() + "')");

                User user = new User();
                user.setLogin("admin@admin.com");
                user.setPassword("admin");
                ObjectMapper objectMapper = new ObjectMapper();
                byte[] userJson = objectMapper.writeValueAsBytes(user);
                request.content(userJson);
                response = new MockHttpResponse();
                dispatcher.invoke(request, response);
                assertEquals(HttpServletResponse.SC_OK, response.getStatus());

            } finally {
                connection.rollback();
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

}