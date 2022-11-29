package fr.yl.restfulldeployment.endpoint;

import fr.yl.restfulldeployment.dao.DAOFactory;
import fr.yl.restfulldeployment.work.Departement;
import fr.yl.restfulldeployment.work.Ville;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/departements")
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
}
