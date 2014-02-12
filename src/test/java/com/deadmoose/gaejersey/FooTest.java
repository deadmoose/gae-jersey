package com.deadmoose.gaejersey;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FooTest extends JerseyTest
{
    @Override
    protected Application configure ()
    {
        return new ResourceConfig(Foo.class);
    }

    @Test
    public void testFoo ()
    {
        final String responseMsg = target().path("/foo").request().get(String.class);
        assertEquals("Foo!", responseMsg);
    }

    @Test
    public void testBar ()
    {
        final String responseMsg = target().path("/foo/bar").request().get(String.class);
        assertEquals("Bar!", responseMsg);
    }

    @Test
    public void testBaz ()
    {
        final String responseMsg = target().path("/foo/bar/baz").request().get(String.class);
        assertEquals("Bar: baz!", responseMsg);
    }
}
