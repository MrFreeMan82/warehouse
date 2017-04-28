package com.warehouse.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.warehouse.client.i18n.I18N;
import com.warehouse.client.present.MainPresent;

import java.util.logging.Logger;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Warehouse implements EntryPoint
{
  public static final Logger logger = Logger.getLogger("Warehouse");
  public static final I18N i18n = GWT.create(I18N.class);

    /**
   * This is the entry point method.
   */
  public void onModuleLoad()
  {
    go();
  }

  private void go()
  {
    new MainPresent();
  }
}
