package com.example;

import com.example.model.Fruit;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    private static final Logger LOGGER = Logger.getLogger(FruitResource.class);

    private final FruitService fruitService;

    @Inject
    public FruitResource(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GET
    @Operation(
            summary = "Get all available fruits",
            description = "Retrieve available fruits")
    @APIResponse(responseCode = "200", description = "Provides available fruits")
    @APIResponse(responseCode = "500", description = "Internal Server Error")
    public Response getAllFruits() {
        final Set<Fruit> fruits;

        try {
            fruits = fruitService.getAllFruits();

        } catch (Exception e) {
            LOGGER.error("Exception caught: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).entity(fruits).build();

    }

    @POST
    @Operation(
            summary = "Add a fruit",
            description = "Add given fruit")
    @APIResponse(responseCode = "201", description = "Add given fruit")
    @APIResponse(responseCode = "500", description = "Internal Server Error")
    public Response addFruit(@Valid Fruit fruit) {
        final Set<Fruit> fruits;

        try {
            fruits = fruitService.addFruit(fruit);

        } catch (Exception e) {
            LOGGER.error("Exception caught: ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.CREATED).entity(fruits).build();
    }
}