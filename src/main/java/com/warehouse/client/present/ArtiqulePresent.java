package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.Warehouse;
import com.warehouse.client.utils.CellInfo;
import com.warehouse.client.utils.Dockable;
import com.warehouse.shared.dto.ArtGroup;
import com.warehouse.shared.dto.ArtiquleView;
import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;

import java.util.List;

/**
 * Created by Дима on 26.05.2017.
 *
 */

public class ArtiqulePresent extends Present implements Dockable<Present>, CellInfo<ArtGroup> {

    static final String TAG = "com.warehouse.client.present.ArtiqulePresent";


    @UiTemplate("com.warehouse.client.page.ArtiqulePage.ui.xml")
    interface ArtiquleUIBinder extends UiBinder<Widget, ArtiqulePresent>{}
    private static final ArtiquleUIBinder binder = GWT.create(ArtiquleUIBinder.class);

    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem newGroup;
    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem newArtiqule;
    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem editArtiqule;
    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem deleteArtiqule;
    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem viewArtiqule;
    @SuppressWarnings("WeakerAccess") @UiField Button search;
    @SuppressWarnings("WeakerAccess") @UiField(provided = true) DataGrid<ArtiquleView> dataGrid= new DataGrid<>();

    public ArtiqulePresent(){
        initWidget(binder.createAndBindUi(this));

        dataGrid.setWidth("100%");
        dataGrid.setHeight("100%");
        dataGrid.setAutoHeaderRefreshDisabled(true);
        dataGrid.setEmptyTableWidget(new Label("Empty"));

        TextColumn<ArtiquleView> id = new TextColumn<ArtiquleView>() {
            @Override
            public String getValue(ArtiquleView artiquleView) {
                return Long.toString(artiquleView.getId());
            }
        };
        dataGrid.setColumnWidth(id, 40, Style.Unit.PX);
        dataGrid.addColumn(id, Warehouse.i18n.columnID());

        TextColumn<ArtiquleView> group = new TextColumn<ArtiquleView>() {
            @Override
            public String getValue(ArtiquleView artiquleView) {
                return artiquleView.groupName;
            }
        };
        dataGrid.setColumnWidth(group, 20, Style.Unit.PCT);
        dataGrid.addColumn(group, Warehouse.i18n.columnName());
    }

    @Override
    public String cellText(ArtGroup artGroup) {
        return null;
    }

    @Override
    public List<ArtGroup> getChildren(ArtGroup parent) {
        return null;
    }

    @Override
    public boolean isLeaf(ArtGroup artGroup) {
        return false;
    }

    @Override
    public void onClick(ArtGroup artGroup) {

    }

    @Override
    public Present dockable() {
        return this;
    }

    @Override
    public void show() {
        RootLayoutPanel.get().clear();
        RootLayoutPanel.get().add(this);
    }
}
