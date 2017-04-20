package com.warehouse.client.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * Created by Дима on 19.04.2017.
 *
 */

public interface I18N extends Messages
{
    @Key("login.title")
    String loginPageTitle();

    @Key("caption.send")
    String captionSend();

    @Key("caption.password")
    String captionPassword();
}
