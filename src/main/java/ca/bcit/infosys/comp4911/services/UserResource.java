package ca.bcit.infosys.comp4911.services;

import ca.bcit.infosys.comp4911.access.UserDao;
import ca.bcit.infosys.comp4911.application.UserTokens;
import ca.bcit.infosys.comp4911.domain.User;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {


    @EJB
    private UserTokens userTokens;

    @EJB
    private UserDao userDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAuthenticatedUserInfo(
      @HeaderParam("Authorization") final String token) {
        int userId = userTokens.verifyTokenAndReturnUserID(token);

        return Response.ok().entity(userDao.read(userId)).header(SH.cors, "*").build();
    }

    @Path("/token")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveToken(
      @HeaderParam("Authorization") final String authorization) {
        if (Strings.isNullOrEmpty(authorization)) {
            throw new WebApplicationException(
              Response.status(Response.Status.UNAUTHORIZED).header(SH.cors, "*").build());
        }

        String[] credentials = authorization.split(":");

        if (credentials.length != 2) {
            throw new WebApplicationException(
              Response.status(Response.Status.BAD_REQUEST).header(SH.cors, "*").build());
        }

        Optional<User> authenticatedUser = userDao.authenticate(credentials[0], credentials[1]);

        if (!authenticatedUser.isPresent()) {
            throw new WebApplicationException(
              Response.status(Response.Status.UNAUTHORIZED).header(SH.cors, "*").build());
        }

        // Create a response with userId and token
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", authenticatedUser.get().getId());
        jsonObject.put("token", userTokens.generateToken(authenticatedUser.get().getId()));

        return Response.ok().entity(jsonObject.toString()).header(SH.cors, "*").build();
    }

    @Path("/token")
    @DELETE
    public Response invalidateToken(
      @HeaderParam("Authorization") final String authorization) {
        userTokens.clearToken(authorization);

        return Response.status(Response.Status.NO_CONTENT).header(SH.cors, "*").build();
    }
}
