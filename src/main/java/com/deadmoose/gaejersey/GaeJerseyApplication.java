package com.deadmoose.gaejersey;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class GaeJerseyApplication extends ResourceConfig
{
    public GaeJerseyApplication ()
    {
        register(Root.class);
        register(Foo.class);
    }
}
