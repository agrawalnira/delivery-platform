package com.social.resources;

import com.social.model.Donor;
import com.social.model.Requirement;
import com.social.services.RequirementDAO;
import com.social.services.RequirementDAOImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by niranjan.agrawal on 2/11/16.
 */

@Path("/requirements")
public class RequirementResource {

    private Logger log = Logger.getLogger(RequirementResource.class.getSimpleName());
    private RequirementDAO requirementDAO = new RequirementDAOImpl();

    @POST
    @Path("/{ngoid}/add")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addRequirement(@PathParam("ngoid") long ngoId, Requirement requirement){
        Requirement req = requirementDAO.addRequirement(ngoId,requirement);
        if(req!=null)
            return Response.status(200).entity(req).build();
        else
            return Response.status(500).entity("{}").build();
    }


    @GET
    @Path("/{ngoid}/getAll")
    @Produces("application/json")
    public List<Requirement> getAllRequirements(@PathParam("ngoid") long ngoId, @QueryParam("status") String status){

        if(status!=null && !status.isEmpty())
            return requirementDAO.getAllRequirementsForStatus(ngoId,status);
        return requirementDAO.getAllRequirements(ngoId);
    }

    @GET
    @Path("/{ngoid}/req/{reqid}")
    @Produces("application/json")
    public Response getRequirementForId(@PathParam("ngoid") long ngoId, @PathParam("reqid") long reqId){
        Requirement requirement = requirementDAO.getRequirement(ngoId,reqId);
        if(requirement!=null)
            return Response.status(200).entity(requirement).build();
        else
            return Response.status(404).entity("{}").build();
    }

    @PUT
    @Path("/{ngoid}/update/{reqid}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response updateRequirementForId(@PathParam("ngoid") long ngoId, @PathParam("reqid") long reqId, Requirement requirement){
        requirement.setId(reqId);
        Requirement req = requirementDAO.updateRequirement(ngoId,reqId,requirement);
        if(req!=null)
            return Response.status(200).entity(req).build();
        else
            return Response.status(404).entity("{}").build();
    }

    @GET
    @Path("/{ngoid}/donors/{reqid}")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Donor> getDonorsForRequirementId(@PathParam("ngoid") long ngoId, @PathParam("reqid") long reqId){
        return requirementDAO.getAllDonorsForRequirementId(ngoId,reqId);
    }
}
