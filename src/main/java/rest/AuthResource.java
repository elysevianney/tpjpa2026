package rest;



import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jpa.dao.generic.PeopleDao;
import jpa.domain.People;
import jpa.dto.LoginDTO;
import jpa.utils.JwtUtil;
import jpa.utils.PasswordUtil;

import java.util.Set;

@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    private final PeopleDao peopleDao = new PeopleDao();

    record LoginResponse(String token, Set<String> roles) {}

    @POST
    @Path("/login")
    public Response login(@Valid LoginDTO loginDTO) {
        People people = peopleDao.findByEmail(loginDTO.getEmail());

        if (people == null || !PasswordUtil.verify(loginDTO.getPassword(), people.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String roleName = people.getRole().name().toLowerCase();
        Set<String> roles = Set.of(roleName);
        String token = JwtUtil.generateToken(people.getEmail(), roles);

        return Response.ok(new LoginResponse(token, roles)).build();
    }
}
