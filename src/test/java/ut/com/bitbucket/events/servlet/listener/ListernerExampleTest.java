package ut.com.bitbucket.events.servlet.listener;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.bitbucket.events.servlet.listener.ListernerExample;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import static org.mockito.Mockito.*;

/**
 * @since 3.5
 */
public class ListernerExampleTest {

    ServletContextEvent contextEvent;
    ServletContext mockContext;

    @Before
    public void setup() {
        mockContext = mock(ServletContext.class);
        contextEvent = new ServletContextEvent(mockContext);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testSomething() {

        ListernerExample contextListener = new ListernerExample();
        contextListener.contextInitialized(contextEvent);
        contextListener.contextDestroyed(contextEvent);

        verify(mockContext).setAttribute("myservletcontextlistener","original");
        verify(mockContext).setAttribute("myservletcontextlistener","destroyed");

    }

}
