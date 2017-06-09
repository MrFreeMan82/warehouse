package com.warehouse.client.present;

import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.Warehouse;
import com.warehouse.client.constant.PriceType;
import com.warehouse.client.utils.Dialog;
import com.warehouse.client.utils.RequestCallBack;
import com.warehouse.client.utils.Server;
import com.warehouse.client.validator.RequiredValidator;
import com.warehouse.client.validator.SizeValidator;
import com.warehouse.shared.dto.*;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.request.SQL;
import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 05.06.2017.
 *
 */
public class ArtiquleDialog extends Present implements Dialog {

    @UiTemplate("com.warehouse.client.page.ArtiqulePage.ui.xml")
    interface ArtiquleUIBinder extends UiBinder<Widget, ArtiquleDialog>{}
    private static final ArtiquleUIBinder binder = GWT.create(ArtiquleUIBinder.class);

    @SuppressWarnings("WeakerAccess") @UiField Form form;
    @SuppressWarnings("WeakerAccess") @UiField TextBox nameText;
    @SuppressWarnings("WeakerAccess") @UiField TextBox shortNameText;
    @SuppressWarnings("WeakerAccess") @UiField ListBox metricListBox;
    @SuppressWarnings("WeakerAccess") @UiField(provided = true) CellTable<Price> priceGrid = new CellTable<>();

    private Mode mode;
    private Artiqule editableArtiqule = new Artiqule();

    ArtiquleDialog(){
        initWidget(binder.createAndBindUi(this));

        priceGrid.setWidth("100%");
        priceGrid.setHeight("100%");
        priceGrid.setAutoHeaderRefreshDisabled(true);
        priceGrid.setEmptyTableWidget(new Label("Empty"));

        TextColumn<Price> type = new TextColumn<Price>() {
            @Override
            public String getValue(Price price) {
                return PriceType.caption(price.typeId);
            }
        };
        priceGrid.setColumnWidth(type, 20, Style.Unit.PCT);
        priceGrid.addColumn(type, Warehouse.i18n.captionType());

        Column<Price, String> price = new Column<Price, String>(new TextInputCell()) {
            @Override
            public String getValue(Price price) {
                return NumberFormat.getFormat("0.00").format(price.price / 100F);
            }
        };

        price.setFieldUpdater((i, editable, s) -> {
            try {
                editable.price = (long) (Float.valueOf(s) * 100);
            } catch (Exception e){
                Window.alert(Warehouse.i18n.alertInvalidNumber());
            }
        });
        priceGrid.setColumnWidth(price, 10, Style.Unit.PCT);
        priceGrid.addColumn(price, Warehouse.i18n.captionPrice());

        nameText.addValueChangeHandler(valueChangeEvent -> editableArtiqule.name = valueChangeEvent.getValue());
        shortNameText.addValueChangeHandler(valueChangeEvent -> editableArtiqule.shortName = valueChangeEvent.getValue());
        metricListBox.addChangeHandler(changeEvent ->
            editableArtiqule.metricId = Long.parseLong(metricListBox.getValue(metricListBox.getSelectedIndex()))
        );

        addValidators();
        Server.setCallback(this::onReceiveMetric).findList(new Request(SQL.METRIC_LIST, new Metric()));
    }

    private void addValidators(){
        nameText.addValidator(new RequiredValidator());
        nameText.addValidator(new SizeValidator(2, 255));
        shortNameText.addValidator(new RequiredValidator());
        shortNameText.addValidator(new SizeValidator(2, 64));
    }

    private void onReceiveMetric(DTO dto){

        if(dto instanceof HashedDTO){
            HashedDTO metrics = (HashedDTO) dto;
            List<Metric> metricList = (List<Metric>) metrics.getList();
            metricList.forEach(metric -> metricListBox.addItem(metric.name, String.valueOf(metric.getId())));
        }

        String id = String.valueOf(editableArtiqule.metricId);
        for(int i = 0; i < metricListBox.getItemCount(); i++){
            if(metricListBox.getValue(i).equals(id)){
                metricListBox.setSelectedIndex(i);
                return;
            }
        }
    }

    private void updateTable(){
        priceGrid.setRowCount(editableArtiqule.prices.size());
        priceGrid.setRowData(editableArtiqule.prices);
        priceGrid.redraw();
    }

    ArtiquleDialog create(Group group) {
        mode = Mode.INSERT;
        editableArtiqule.groupId = group.getId();
        editableArtiqule.prices = new ArrayList<>();
        for(PriceType type: PriceType.values()) {
            editableArtiqule.prices.add(new Price(type.Id(), 0L));
        }
        updateTable();
        return this;
    }

    ArtiquleDialog update(Artiqule artiqule) {
        editableArtiqule = artiqule;
        mode = Mode.EDIT;

        nameText.setText(editableArtiqule.name);
        shortNameText.setText(editableArtiqule.shortName);
        updateTable();
        return this;
    }

    @Override
    public void onPositive(Modal dialog, RequestCallBack callBack) {
        if(! form.validate()) return;

        if (mode == Mode.INSERT){
                Server.setCallback(callBack).insert(new Request(editableArtiqule));
        }
        else if(mode == Mode.EDIT) {
                Server.setCallback(callBack).update(new Request(editableArtiqule));
        }
        dialog.hide();
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
