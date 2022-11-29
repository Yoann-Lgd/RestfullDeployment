package fr.yl.restfulldeployment.endpoint;

import fr.yl.restfulldeployment.dao.DAOFactory;
import fr.yl.restfulldeployment.work.Famille;
import fr.yl.restfulldeployment.work.Instrument;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/familles")
public class FamilleResource {

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAll(){
            List<Famille> familles = DAOFactory.getFamilleDAO().getAll(0);
            return Response.ok(familles).build();
        }

        @GET
        @Path("{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getById(@PathParam("id") Integer id){
            Famille famille  = DAOFactory.getFamilleDAO().getByID(id);
            return Response.ok(famille).build();
        }

}
