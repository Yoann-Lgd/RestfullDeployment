package fr.yl.restfulldeployment.endpoint;

import fr.yl.restfulldeployment.dao.DAOFactory;
import fr.yl.restfulldeployment.work.Ecole;
import fr.yl.restfulldeployment.work.Personne;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/personnes")
@Tag(name = "personnes")
public class PersonneResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<Personne> personnes = DAOFactory.getPersonneDAO().getAll(0);
        return Response.ok(personnes).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id){
        Personne personne = DAOFactory.getPersonneDAO().getByID(id);
        return Response.ok(personne).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "204", description = "Créé")
    @ApiResponse(responseCode = "404", description = "non créée !")
    public Response create(Personne personne){
        if(personne == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getPersonneDAO().insert(personne) != 0){
            return Response.status(204).build();
        }
        else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "204", description = "Créé")
    @ApiResponse(responseCode = "404", description = "non créée !")
    public Response update(Personne personne){
        if(personne == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getPersonneDAO().update(personne)){
            return Response.status(204).build();
        }
        else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "204", description = "Créé")
    @ApiResponse(responseCode = "404", description = "non créée !")
    public Response delete(@PathParam("id") Integer id){
        if(id == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getPersonneDAO().delete(DAOFactory.getPersonneDAO().getByID(id)))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
