package rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jpa.domain.Borrow;
import jpa.domain.BorrowStatus;
import jpa.domain.Element;
import jpa.domain.User;
import jpa.service.BorrowService;
import jpa.service.ElementService;
import jpa.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Path("borrow")
@Produces({"application/json"})
@Consumes(MediaType.APPLICATION_JSON)
public class BorrowResource {

    private final BorrowService borrowService = new BorrowService();
    private final UserService userService = new UserService();
    private final ElementService elementService = new ElementService();

    @GET
    @Path("/")
    public List<Borrow> getAllBorrows() {
        return borrowService.findAll();
    }

    @GET
    @Path("/{id}")
    public Borrow getBorrow(@PathParam("id") Long id) {
        return borrowService.findById(id);
    }

    @GET
    @Path("/user/{userId}")
    public List<Borrow> getBorrowsByUser(@PathParam("userId") Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return borrowService.findByUserId(userId);
    }

    @POST
    @Path("/")
    public Response createBorrow(BorrowRequest request) {
        User user = userService.findById(request.getUserId());
        Element item = elementService.findById(request.getItemId());
        if (user == null || item == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("User or Item not found").build();
        }
        Long id = borrowService.createBorrow(user, item);
        return Response.status(Response.Status.CREATED).entity(id).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateBorrow(@PathParam("id") Long id, BorrowUpdateRequest request) {
        borrowService.updateBorrow(id, request.getDueDate(), request.getBorrowDate(), request.getStatus());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBorrow(@PathParam("id") Long id) {
        borrowService.deleteBorrow(id);
        return Response.noContent().build();
    }

    // Classes internes pour les requêtes
    public static class BorrowRequest {
        private Long userId;
        private Long itemId;

        // Getters and setters
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public Long getItemId() { return itemId; }
        public void setItemId(Long itemId) { this.itemId = itemId; }

    }

    public static class BorrowUpdateRequest {
        private LocalDate dueDate;
        private BorrowStatus status;
        private LocalDate BorrowDate;

        // Getters and setters
        public LocalDate getDueDate() { return dueDate; }
        public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
        public BorrowStatus getStatus() { return status; }
        public void setStatus(BorrowStatus status) { this.status = status; }
        public LocalDate getBorrowDate() { return BorrowDate; }
        public void setBorrowDate(LocalDate borrowDate) { BorrowDate = borrowDate; }
    }
}
