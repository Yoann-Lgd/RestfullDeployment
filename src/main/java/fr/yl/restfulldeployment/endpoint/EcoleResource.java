package fr.yl.restfulldeployment.endpoint;

import fr.yl.restfulldeployment.dao.DAOFactory;
import fr.yl.restfulldeployment.work.Ecole;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/ecoles")
public class EcoleResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<Ecole> ecoles = DAOFactory.getEcoleDAO().getAll(0);
        return Response.ok(ecoles).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id){
        Ecole ecole =DAOFactory.getEcoleDAO().getByID(id);
        return Response.ok(ecole).build();
    }

}
