package fr.ot.restfulldeployment.endpoints;

import fr.ot.restfulldeployment.dto.FamilleDto;
import fr.ot.restfulldeployment.models.FamilleEntity;
import fr.ot.restfulldeployment.repository.FamilleRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/famille")
@Tag(name = "familles")
public class FamilleResource {
    private FamilleRepository familleRepository= new FamilleRepository();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<FamilleDto> familles = familleRepository.getAll();
        return Response.ok(familles).build();
    }
}
