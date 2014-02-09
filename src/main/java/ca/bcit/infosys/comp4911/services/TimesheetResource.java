package ca.bcit.infosys.comp4911.services;

import ca.bcit.infosys.comp4911.access.TimesheetDao;
import ca.bcit.infosys.comp4911.application.UserTokens;
import ca.bcit.infosys.comp4911.domain.Timesheet;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Graeme on 2/8/14.
 */
@Path("/timesheets")
public class TimesheetResource {

    @EJB
    TimesheetDao timesheetDao;

    @EJB
    UserTokens userTokens;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllTimesheets(
            @HeaderParam("Authorization") final String token)
    {
        int userId = userTokens.verifyTokenAndReturnUserID((token));

        return Response.ok().entity(timesheetDao.getAll()).header(SH.cors, "*")
                .header(SH.auth, token).build();

    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveTimesheet(
            @HeaderParam("Authorization") final String token,
            @PathParam("id") Integer id)
    {
        int userId = userTokens.verifyTokenAndReturnUserID((token));

        Timesheet timesheet = timesheetDao.read(id);
        if(timesheet == null)
        {
            return Response.status(404).header(SH.cors, "*")
                    .header(SH.auth, token).build();
        }
        return Response.ok().entity(timesheet).header(SH.cors, "*")
                .header(SH.auth, token).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPayRate(
            @HeaderParam("Authorization") final String token,
            Timesheet timesheet)
    {
        int userId = userTokens.verifyTokenAndReturnUserID((token));

        timesheetDao.create(timesheet);
        return Response.status(201).header(SH.cors, "*")
                .header(SH.auth, token).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTimesheet(
            @HeaderParam("Authorization") final String token,
            @PathParam("id") Integer id, Timesheet timesheet)
    {
        int userId = userTokens.verifyTokenAndReturnUserID((token));

        Timesheet update = timesheetDao.read(id);
        if(update == null)
        {
            return Response.status(404).header(SH.cors, "*")
                    .header(SH.auth, token).build();
        }
        timesheetDao.update(timesheet);
        return Response.ok().header(SH.cors, "*")
                .header(SH.auth, token).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteTimesheet(
            @HeaderParam("Authorization") final String token,
            @PathParam("id") Integer id)
    {
        int userId = userTokens.verifyTokenAndReturnUserID((token));

        Timesheet timesheet = timesheetDao.read(id);
        if(timesheet == null)
        {
            return Response.status(404).header(SH.cors, "*")
                    .header(SH.auth, token).build();
        }

        timesheetDao.delete(timesheet);
        return Response.status(404).header(SH.cors, "*")
                .header(SH.auth, token).build();
    }


}
