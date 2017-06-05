package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.Warehouse;
import com.warehouse.client.present.constant.PriceType;
import com.warehouse.client.utils.Dialog;
import com.warehouse.client.utils.RequestCallBack;
import com.warehouse.shared.dto.Artiqule;
import com.warehouse.shared.dto.Group;
import com.warehouse.shared.dto.Price;
import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

/**
 * Created by Дима on 05.06.2017.
 *
 */
public class ArtiquleDialog extends Present implements Dialog {

    @UiTemplate("com.warehouse.client.page.ArtiqulePage.ui.xml")
    interface ArtiquleUIBinder extends UiBinder<Widget, ArtiquleDialog>{}
    private static final ArtiquleUIBinder binder = GWT.create(ArtiquleUIBinder.class);

    @SuppressWarnings("WeakerAccess") @UiField Form form;
    @SuppressWarnings("WeakerAccess") @UiField(provided = true) CellTable<Price> priceGrid = new CellTable<>();

    private Mode mode;
    private Group selectedGroup;
    private Artiqule editableArtiqule = new Artiqule();

    public ArtiquleDialog(){
        initWidget(binder.createAndBindUi(this));

        priceGrid.setWidth("100%");
        priceGrid.setHeight("100%");
        priceGrid.setAutoHeaderRefreshDisabled(true);
        priceGrid.setEmptyTableWidget(new Label("Empty"));

        TextColumn<Price> id = new TextColumn<Price>() {
            @Override
            public String getValue(Price price) {
                return Long.toString(price.getId());
            }
        };
        priceGrid.setColumnWidth(id, 10, Style.Unit.PX);
        priceGrid.addColumn(id, Warehouse.i18n.captionID());

        TextColumn<Price> type = new TextColumn<Price>() {
            @Override
            public String getValue(Price price) {
                return PriceType.caption(price.typeId);
            }
        };
        priceGrid.setColumnWidth(type, 20, Style.Unit.PCT);
        priceGrid.addColumn(type, Warehouse.i18n.captionType());

        TextColumn<Price> price = new TextColumn<Price>() {
            @Override
            public String getValue(Price price) {
                return NumberFormat.getFormat("0.00").format(price.price / 100F);
            }
        };
        priceGrid.setColumnWidth(price, 10, Style.Unit.PCT);
        priceGrid.addColumn(price, Warehouse.i18n.captionPrice());
    }

    ArtiquleDialog create(Group group) {
        selectedGroup = group;
        mode = Mode.INSERT;
        return this;
    }

    ArtiquleDialog update(Artiqule artiqule) {
        editableArtiqule = artiqule;
        mode = Mode.EDIT;
        return this;
    }


    @Override
    public void onPositive(Modal dialog, RequestCallBack callBack) {

    }

    @Override
    public void onNeutral(Modal dialog) {}

    @Override
    public void onNegative(Modal dialog) {
        dialog.hide();
    }

    @Override
    public void setReadOnly() {

    }

    @Override
    public void show() {
        RootLayoutPanel.get().clear();
        RootLayoutPanel.get().add(this);
    }
}
