package com.deadmoose.gaejersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("foo")
public class Foo
{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String foo ()
    {
        return "Foo!";
    }

    @GET
    @Path("bar")
    @Produces(MediaType.TEXT_PLAIN)
    public String bar ()
    {
        return bar(null);
    }

    @GET
    @Path("bar/{baz}")
    @Produces(MediaType.TEXT_PLAIN)
    public String bar (@PathParam("baz") String baz)
    {
        return baz == null ? "Bar!" : "Bar: " + baz + "!";
    }
}
