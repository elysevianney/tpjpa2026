package rest;


import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class RestApplication extends Application {


    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<>();


        // SWAGGER endpoints
        resources.add(OpenApiResource.class);
        resources.add(UserResource.class);
        //NEW LINE TO ADD
        resources.add(SwaggerResource.class);
        resources.add(AuthResource.class);
        resources.add(ElementResource.class);
        resources.add(BorrowResource.class);

        resources.add(JacksonConfig.class);


        return resources;
    }
}