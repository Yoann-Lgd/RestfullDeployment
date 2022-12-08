package fr.yl.restfulldeployment.endpoint;

import fr.yl.restfulldeployment.dao.DAOFactory;
import fr.yl.restfulldeployment.work.Departement;
import fr.yl.restfulldeployment.work.Ville;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/departements")
@Tag(name = "departements")
public class DepartementResource{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<Departement> departements = DAOFactory.getDepartementDAO().getAll(0);
        return Response.ok(departements).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id){
        Departement departement = DAOFactory.getDepartementDAO().getByID(id);
        return Response.ok(departement).build();
    }
    @GET
    @Path("villes/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVillesByDepartement(@PathParam("id") Integer idOfDepartement){
        List<Ville> villeList = DAOFactory.getDepartementDAO().getVilleByDepartement(idOfDepartement);
        return Response.ok(villeList).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "204", description = "Créé")
    @ApiResponse(responseCode = "404", description = "non créée !")
    public Response create(Departement departement){
        if(departement == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getDepartementDAO().insert(departement) != 0){
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
    public Response update(Departement departement){
        if(departement == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getDepartementDAO().update(departement)){
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
        if (DAOFactory.getDepartementDAO().delete(DAOFactory.getDepartementDAO().getByID(id)))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
