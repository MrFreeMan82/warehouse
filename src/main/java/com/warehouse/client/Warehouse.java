package com.warehouse.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.warehouse.client.event.AppEvent;
import com.warehouse.client.event.ErrorEvent;
import com.warehouse.client.i18n.I18N;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Warehouse implements EntryPoint
{
  private static AppController appController = new AppController();
  private static AppLog appLog = new AppLog();

  public static final SimpleEventBus eventBus = new SimpleEventBus();
  public static final AppExternal external = new AppExternal();
  public static final I18N i18n = GWT.create(I18N.class);

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