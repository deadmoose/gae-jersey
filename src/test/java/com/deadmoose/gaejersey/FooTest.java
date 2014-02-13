package com.deadmoose.gaejersey;

import java.io.File;
import java.net.URL;
import java.util.List;

import javax.ws.rs.core.Application;

import com.google.appengine.tools.development.testing.BaseDevAppServerTestConfig;
import com.google.appengine.tools.development.testing.DevAppServerTest;
import com.google.appengine.tools.development.testing.DevAppServerTestRunner;
import com.google.common.collect.Lists;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(DevAppServerTestRunner.class)
@DevAppServerTest(FooTest.TestConfig.class)
public class FooTest extends JerseyTest
{
    public static class TestConfig extends BaseDevAppServerTestConfig {
        @Override public File getSdkRoot () {
            return new File("");
        }

        @Override public File getAppDir () {
            return new File("");
        }

        @Override public List<URL> getClasspath () {
            return Lists.newArrayList();
        }
      }

    public FooTest ()
    {
        super(new GaeTestContainerFactory());
    }

    @Override
    protected Application configure ()
    {
        return new ResourceConfig(Foo.class);
    }

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
