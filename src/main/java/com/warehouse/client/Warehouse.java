package com.warehouse.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.warehouse.client.events.AppEvent;
import com.warehouse.client.events.ErrorEvent;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Warehouse implements EntryPoint
{
  private static AppController appController = new AppController();
  private static SimpleEventBus eventBus = new SimpleEventBus();
  private static AppLog appLog = new AppLog();
  private static AppExternal external = new AppExternal();

  public static SimpleEventBus getEventBus(){return eventBus;}
  public static AppExternal getExternal(){return external;}

    /**
   * This is the entry point method.
   */
  public void onModuleLoad()
  {
    eventBus.addHandler(AppEvent.TYPE, appController);
    eventBus.addHandler(AppEvent.TYPE, appLog);
    eventBus.addHandler(ErrorEvent.TYPE, appLog);
    appController.go();
  }
}
