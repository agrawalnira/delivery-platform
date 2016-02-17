package com.social.resources;

import com.social.database.SocialServiceDAO;
import com.social.model.Profile;
import com.social.services.ProfileDAO;
import com.social.services.ProfileDAOImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/service")
public class ProfileResource {

    private static final Logger log = Logger.getLogger(ProfileResource.class.getSimpleName());
    private ProfileDAO profileDAO = new ProfileDAOImpl();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    @POST
    @Path("/authenticateUser")
    @Consumes("application/json")
    public Response getUserAuthenticate(Profile profile){

        log.info("In user Authentication..");

        if(profile!=null) {
            log.info("Credentials:"+profile.getUsername()+" "+profile.getPassword());
            boolean isAuthentic = SocialServiceDAO.authenticateUser(profile.getUsername(),profile.getPassword());
            if(isAuthentic)
               return Response.status(200).entity("Authentication Passed").build();
            else
                return Response.status(401).entity("Authentication Failed!").build();
        }

        return Response.status(401).entity("User not found").build();
    }

    @POST
    @Path("/addProfile")
    @Consumes("application/json")
    public Response addProfile(Profile profile){
        log.info("In Add Profile service");
        if(profile!=null){
            boolean isAdded = profileDAO.addProfile(profile);
            if(isAdded)
                return Response.status(201).entity("Profile added successfully").build();
            else
                return Response.status(500).entity("Server encountered an error while adding the entry to the database").build();
        }
        else
            return Response.status(400).entity("Post message body couldn't be understood by server due to malformed syntax").build();
    }

    @GET
    @Path("/getProfile/{profileName}")
    @Produces("application/json")
    public Response getProfile(@PathParam("profileName") String profileName){
        log.info("In getProfile service for the profile name:"+profileName);

        Profile profile = profileDAO.getProfile(profileName);

        if(profile!=null) {
            return Response.status(200).entity(profile).build();
        }
        else {
            return Response.status(404).entity("{}").build();
        }
    }
}
