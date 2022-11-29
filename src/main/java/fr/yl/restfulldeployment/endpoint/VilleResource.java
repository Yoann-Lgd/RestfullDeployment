package fr.yl.restfulldeployment.endpoint;

import fr.yl.restfulldeployment.dao.DAOFactory;
import fr.yl.restfulldeployment.work.Adresse;
import fr.yl.restfulldeployment.work.Personne;
import fr.yl.restfulldeployment.work.Ville;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/villes")
@Tag(name = "villes")
public class VilleResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<Ville> villes = DAOFactory.getVilleDAO().getAll(0);
        return Response.ok(villes).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id){
        Ville ville = DAOFactory.getVilleDAO().getByID(id);
        return Response.ok(ville).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "204", description = "Créé")
    @ApiResponse(responseCode = "404", description = "non créée !")
    public Response create(Ville ville){
        if(ville == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getVilleDAO().insert(ville) != 0){
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
    public Response update(Ville ville){
        if(ville == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getVilleDAO().update(ville)){
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
        if (DAOFactory.getVilleDAO().delete(DAOFactory.getVilleDAO().getByID(id)))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
