package fr.ot.restfulldeployment.endpoints;

import fr.ot.restfulldeployment.dto.ClassificationDto;
import fr.ot.restfulldeployment.repository.ClassificationRepository;
import fr.ot.restfulldeployment.models.ClassificationEntity;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/classifications")
@Tag(name = "classifications")
public class ClassificationResource {
    private ClassificationRepository classificationRepository= new ClassificationRepository();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<ClassificationDto> classifications = classificationRepository.getAll();
        return Response.ok(classifications).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id){
        ClassificationDto classification = classificationRepository.getById(id);
        return Response.ok(classification).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "204", description = "Modifié")
    @ApiResponse(responseCode = "404", description = "non trouvée !")
    public Response update(ClassificationEntity classification){
        if(classification == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (classificationRepository.update(classification))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "204", description = "Créé")
    @ApiResponse(responseCode = "404", description = "non créée !")
    public Response create(ClassificationEntity classification){
        if(classification == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (classificationRepository.create(classification) != 0)
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
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
        if (classificationRepository.delete(id))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
