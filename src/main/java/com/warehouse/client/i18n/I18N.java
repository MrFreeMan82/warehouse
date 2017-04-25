package com.warehouse.client.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * Created by Дима on 19.04.2017.
 *
 */

public interface I18N extends Messages
{
    @Key("caption.save")    String captionSave();
    @Key("caption.ok")      String captionOK();
    @Key("caption.cancel")  String captionCancel();
    @Key("caption.yes")     String captionYes();
    @Key("caption.no")      String captionNo();
    @Key("caption.send")    String captionSend();
    @Key("caption.password")     String captionPassword();
    @Key("validator.required") String validatorRequired();
    @Key("validator.size")   String validatorSize(int min, int max);

    //                  LOGIN PAGE

    @Key("login.title")          String loginPageTitle();


    //                  USER INFORMATION PAGE

    @Key("user.title")                      String userTitle();
    @Key("user.label.type")                  String userTypeLabel();
    @Key("user.label.txName")                 String userNameLabel();
    @Key("user.txtName.placeholder")           String userTxtNamePlaceholder();
    @Key("user.txtPassword.placeholder")        String userTxtPasswordPlaceholder();
}
