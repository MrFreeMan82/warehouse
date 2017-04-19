package com.warehouse.client;


import com.google.gwt.event.shared.SimpleEventBus;
import com.warehouse.client.events.AppEvent;
import com.warehouse.client.events.EventAction;
import junit.framework.TestCase;

/**
 * Created by Дима on 18.04.2017.
 *
 */
public class SampleTest extends TestCase {

    private enum kindOfTest implements EventAction
    {
        TEST {
            @Override
            public void action(AppEvent event)
            {
                event.setHandled(true);
                assertFalse("Event not handled",!event.isHandled());
            }
        }
    }

    public void testSample()
    {
        String k="gf";

        assertFalse ("if empty fail", k.equals(""));
    }

    public void testEventBus()
    {
        SimpleEventBus eventBus = new SimpleEventBus();
        AppController controller = new AppController();
        eventBus.addHandler(AppEvent.TYPE, controller);
        eventBus.fireEvent(new AppEvent<>(kindOfTest.TEST, null, "testing"));
    }
}
