package com.warehouse.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.warehouse.client.event.AppEvent;
import com.warehouse.client.event.ErrorEvent;
import com.warehouse.client.i18n.I18N;
import com.warehouse.client.present.MainPresent;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Warehouse implements EntryPoint
{
  private static AppLog appLog = new AppLog();

  public static final SimpleEventBus eventBus = new SimpleEventBus();
  public static final I18N i18n = GWT.create(I18N.class);
  public static AppRequestFactory requestFactory = GWT.create(AppRequestFactory.class);

    /**
   * This is the entry point method.
   */
  public void onModuleLoad()
  {
    requestFactory.initialize(eventBus);
    eventBus.addHandler(AppEvent.TYPE, appLog);
    eventBus.addHandler(ErrorEvent.TYPE, appLog);
    go();
  }

  private void go()
  {
    new MainPresent();
  }
}
