package ut.com.bitbucket.events;

import org.junit.Test;
import com.bitbucket.events.api.MyPluginComponent;
import com.bitbucket.events.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}