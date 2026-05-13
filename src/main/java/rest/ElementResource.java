package rest;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jpa.domain.Element;
import jpa.dto.BookDto;
import jpa.dto.MagazineDto;
import jpa.service.ElementService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("element")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ElementResource {

    private final ElementService elementService = new ElementService();

    @GET
    public List<Element> getAllElements() {
        return elementService.findAll();
    }

    @GET
    @Path("/{id}")
    public Element getElement(@PathParam("id") Long id) {
        Element element = elementService.findById(id);
        if (element == null) {
            throw new NotFoundException("Element introuvable avec l'id " + id);
        }
        return element;
    }

    @POST
    @Path("/book")
    public Response createBook(@Valid BookDto bookDto) throws URISyntaxException {
        Long id = elementService.createBook(bookDto);
        return Response.created(new URI("/element/" + id)).entity(id).build();
    }

    @POST
    @Path("/magazine")
    public Response createMagazine(@Valid MagazineDto magazineDto) throws URISyntaxException {
        Long id = elementService.createMagazine(magazineDto);
        return Response.created(new URI("/element/" + id)).entity(id).build();
    }

    @PUT
    @Path("/book/{id}")
    public Response updateBook(@PathParam("id") Long id, @Valid BookDto bookDto) {
        ensureElementExists(id);
        elementService.updateBook(id, bookDto);
        return Response.ok().build();
    }

    @PUT
    @Path("/magazine/{id}")
    public Response updateMagazine(@PathParam("id") Long id, @Valid MagazineDto magazineDto) {
        ensureElementExists(id);
        elementService.updateMagazine(id, magazineDto);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteElement(@PathParam("id") Long id) {
        ensureElementExists(id);
        elementService.deleteElement(id);
        return Response.noContent().build();
    }

    private void ensureElementExists(Long id) {
        if (elementService.findById(id) == null) {
            throw new NotFoundException("Element introuvable avec l'id " + id);
        }
    }
}
