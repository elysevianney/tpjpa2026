package rest;


import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<>();

        // Permet à Swagger de scanner votre projet et générer la doc
        resources.add(OpenApiResource.class);

        // Ajoutez ici vos futures classes de ressources (ex: UserResource.class)
        // resources.add(UserResource.class);

        return resources;
    }
}