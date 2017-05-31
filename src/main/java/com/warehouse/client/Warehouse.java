package com.warehouse.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.warehouse.client.i18n.I18N;
import com.warehouse.shared.Utils;

import java.util.logging.Logger;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Warehouse implements EntryPoint
{
  private static final Logger logger = Logger.getLogger("Warehouse");
  public static final I18N i18n = GWT.create(I18N.class);

  public static void info(String pattern, final Object ... args)
  {
     String string = (args.length == 0) ? pattern: Utils.format(pattern, args);
     logger.info(string);
  }

  public static void severe(String pattern, final Object ... args)
  {
    String string = (args.length == 0) ? pattern: Utils.format(pattern, args);
    logger.severe(string);
  }

    /**
   * This is the entry point method.
   */
  public void onModuleLoad()
  {
    Application.go();
  }

}
