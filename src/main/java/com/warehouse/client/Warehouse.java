package com.warehouse.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.warehouse.client.i18n.I18N;
import com.warehouse.client.present.LoginPresent;
import com.warehouse.client.present.MainPresent;

import java.util.logging.Logger;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Warehouse implements EntryPoint
{
  private static final Logger logger = Logger.getLogger("Warehouse");
  public static final I18N i18n = GWT.create(I18N.class);
  public static String  sessionKey = "132";

  private Application application = new Application();

  private static String format (String pattern, final Object ... args)
  {
    for (Object arg : args) {
      String part1 = pattern.substring(0,pattern.indexOf('{'));
      String part2 = pattern.substring(pattern.indexOf('}') + 1);
      pattern = part1 + arg + part2;
    }
    return pattern;
  }

  public static void info(String pattern, final Object ... args)
  {
     String string = (args.length == 0) ? pattern: format(pattern, args);
     logger.info(string);
  }

  public static void severe(String pattern, final Object ... args)
  {
    String string = (args.length == 0) ? pattern: format(pattern, args);
    logger.severe(string);
  }

    /**
   * This is the entry point method.
   */
  public void onModuleLoad()
  {
    application.go(sessionKey);
  }

}
