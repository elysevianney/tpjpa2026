package rest;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;



import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jpa.domain.User;
import jpa.dto.UserCreateDto;
import jpa.service.UserService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("users")
@Produces({"application/json"})
public class UserResource {

    private final UserService service = new UserService();

    @GET
    @Path("/{id}")
    public User getTicketById(@PathParam("id") Long id)  {
        return service.findById(id);
    }

    @GET
    @Path("/")
    public List<User> getAllusers()  {
        return service.findAll();
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    public Response createUser(final @Valid UserCreateDto user) throws URISyntaxException {
        long id = service.create(user);
        URI uri = new URI("/users/" + id);
        return Response.created(uri).build();
    }
}