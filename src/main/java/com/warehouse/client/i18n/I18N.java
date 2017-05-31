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
    @Key("caption.new")     String captionNew();
    @Key("caption.edit")    String captionEdit();
    @Key("caption.delete")  String captionDelete();
    @Key("caption.view")    String captionView();
    @Key("caption.search")  String captionSearch();
    @Key("validator.required") String validatorRequired();
    @Key("validator.size")   String validatorSize(int min, int max);

    @Key("alert.choose.group") String alertChooseGroup();

    //                  LOGIN PAGE

    @Key("login.title")          String loginPageTitle();


    //                  USER INFORMATION PAGE

    @Key("user.title")                      String userTitle();
    @Key("user.label.type")                  String userTypeLabel();
    @Key("user.label.txName")                 String userNameLabel();
    @Key("user.txtName.placeholder")           String userTxtNamePlaceholder();
    @Key("user.txtPassword.placeholder")        String userTxtPasswordPlaceholder();

    //              GROUP   PAGE
    @Key("group.title") String groupTitle();


    @Key("column.name")    String columnName();
    @Key("column.password") String columnPassword();
    @Key("column.ID") String columnID();
    @Key("column.type") String columnType();
    @Key("column.status") String columnStatus();

    @Key("status.new") String statusNew();
    @Key("status.user.active") String statusUserActive();

    @Key("column.group")    String columnGroup();
    @Key("column.group1")   String columnGroup1();
    @Key("column.artiqule") String columnArtiqule();
}
