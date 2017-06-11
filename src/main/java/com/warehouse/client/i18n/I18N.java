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
    @Key("caption.prices") String captionPrices();
    @Key("caption.empty") String captionEmpty();
    @Key("validator.required") String validatorRequired();
    @Key("validator.size")   String validatorSize(int min, int max);

    @Key("alert.choose.group") String alertChooseGroup();
    @Key("alert.choose.artiqule") String alertChooseArtiqule();
    @Key("alert.invalid.number") String alertInvalidNumber();

    //                  LOGIN PAGE

    @Key("login.title")          String loginPageTitle();


    //                  USER INFORMATION PAGE

    @Key("user.title")                      String userTitle();
    @Key("user.label.type")                  String userTypeLabel();
    @Key("user.label.txName")                 String userNameLabel();
    @Key("user.txtName.placeholder")           String userTxtNamePlaceholder();
    @Key("user.txtPassword.placeholder")        String userTxtPasswordPlaceholder();


    @Key("title.create") String titleCreate();
    @Key("title.update") String titleUpdate(String name);


    @Key("caption.name")    String captionName();
    @Key("caption.short.name") String captionShortName();
    @Key("caption.ID") String captionID();
    @Key("caption.type") String captionType();
    @Key("caption.status") String captionStatus();
    @Key("caption.metric.name") String captionMetricName();
    @Key("caption.price.rozn") String captionPriceRozn();
    @Key("caption.price.opt") String captionPriceOpt();
    @Key("caption.price") String captionPrice();
    @Key("caption.metric") String captionMetric();

    @Key("status.new") String statusNew();
    @Key("status.user.active") String statusUserActive();

    @Key("caption.group")    String captionGroup();
    @Key("caption.group1")   String captionGroup1();
    @Key("caption.artiqule") String captionArtiqule();
}
