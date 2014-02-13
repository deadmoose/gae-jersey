package com.deadmoose.gaejersey;

import java.net.URI;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.UriBuilder;

import com.google.appengine.tools.development.testing.BaseDevAppServerTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ApplicationHandler;
import org.glassfish.jersey.test.spi.TestContainer;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;

public class GaeTestContainerFactory implements TestContainerFactory
{
    @Override
    public TestContainer create (URI uri, ApplicationHandler appHandler)
        throws IllegalArgumentException
    {
        return new GaeTestContainer(appHandler);
    }

    protected static class GaeTestContainer implements TestContainer
    {
        protected GaeTestContainer(ApplicationHandler appHandler) {
            _appHandler = appHandler;
        }

        @Override
        public ClientConfig getClientConfig () {
            return null;
        }

        @Override
        public URI getBaseUri () {
            return _uri;
        }

        @Override
        public void start () {
            try {
                _helper = new LocalServiceTestHelper(new LocalUserServiceTestConfig()).
                    setEnvIsLoggedIn(true).
                    setEnvAuthDomain("localhost").
                    setEnvEmail("test@localhost");
                _helper.setUp();

                int port = Integer.parseInt(
                    System.getProperty(BaseDevAppServerTestConfig.DEFAULT_PORT_SYSTEM_PROPERTY));
                _uri = UriBuilder.fromUri("http://localhost/").port(port).build();
            } catch (ProcessingException e) {
                throw new TestContainerException(e);
            }
        }

        @Override
        public void stop () {
            _helper.tearDown();
        }

        protected final ApplicationHandler _appHandler;
        protected URI _uri;
        protected LocalServiceTestHelper _helper;
    }
}
