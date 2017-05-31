package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.warehouse.client.Warehouse;
import com.warehouse.client.utils.*;
import com.warehouse.shared.dto.*;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.request.SQL;
import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Дима on 26.05.2017.
 *
 */

public class ArtiqulePresent extends Present implements Dockable<Present>, CellInfo<Group> {

    static final String TAG = "com.warehouse.client.present.ArtiqulePresent";


    @UiTemplate("com.warehouse.client.page.ArtiqulePage.ui.xml")
    interface ArtiquleUIBinder extends UiBinder<Widget, ArtiqulePresent>{}
    private static final ArtiquleUIBinder binder = GWT.create(ArtiquleUIBinder.class);

    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem newGroup;
  //  @SuppressWarnings("WeakerAccess") @UiField AnchorListItem newArtiqule;
  //  @SuppressWarnings("WeakerAccess") @UiField AnchorListItem editGroup;
 //   @SuppressWarnings("WeakerAccess") @UiField AnchorListItem editArtiqule;
 //   @SuppressWarnings("WeakerAccess") @UiField AnchorListItem deleteGroup;
  //  @SuppressWarnings("WeakerAccess") @UiField AnchorListItem deleteArtiqule;
  //  @SuppressWarnings("WeakerAccess") @UiField AnchorListItem viewArtiqule;
    @SuppressWarnings("WeakerAccess") @UiField Button search;
    @SuppressWarnings("WeakerAccess") @UiField(provided = true) DataGrid<Artiqule> artiquleGrid= new DataGrid<>();
    @SuppressWarnings("WeakerAccess") @UiField ScrollPanel groupPanel;
    private HashedDTO groups;
    private Group selectedGroup;


    public ArtiqulePresent(){
        initWidget(binder.createAndBindUi(this));
        Server.setCallback(this::onReceiveGroups).findList(new Request(SQL.GROUPS_WITH_ARTIQULES, new Group()));

        newGroup.addClickHandler(clickEvent -> newGroup());

        artiquleGrid.setWidth("100%");
        artiquleGrid.setHeight("100%");
        artiquleGrid.setAutoHeaderRefreshDisabled(true);
        artiquleGrid.setEmptyTableWidget(new Label("Empty"));

        TextColumn<Artiqule> id = new TextColumn<Artiqule>() {
            @Override
            public String getValue(Artiqule artiqule) {
                return Long.toString(artiqule.getId());
            }
        };
        artiquleGrid.setColumnWidth(id, 40, Style.Unit.PX);
        artiquleGrid.addColumn(id, Warehouse.i18n.columnID());

        TextColumn<Artiqule> artiquleColumn = new TextColumn<Artiqule>() {
            @Override
            public String getValue(Artiqule artiqule) {
                return artiqule.name;
            }
        };
        artiquleGrid.setColumnWidth(artiquleColumn, 20, Style.Unit.PCT);
        artiquleGrid.addColumn(artiquleColumn, Warehouse.i18n.columnName());
    }

    private void newGroup(){
        if(selectedGroup == null){
                Window.alert(Warehouse.i18n.alertChooseGroup());
                return;
        }
        new DialogBuilder<GroupDialog>()
                .setPresent(new GroupDialog(selectedGroup))
                .setTitle(Warehouse.i18n.groupTitle())
                .addPositiveButton(Warehouse.i18n.captionSave())
                .addNegativeButton(Warehouse.i18n.captionCancel())
                .build()
                .show();
    }

    private void onReceiveGroups(DTO dto) {

        if(dto instanceof HashedDTO) {
            groups = (HashedDTO) dto;
            Warehouse.info("Receive " + groups.getList().size() + " groups");

            CellTree.Resources res = GWT.create(CellTree.BasicResources.class);
            TreeModel treeModel = new TreeModel<>(this);
            CellTree groupTree = new CellTree(treeModel, null, res);
            groupTree.setAnimationEnabled(true);
            groupTree.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
            groupPanel.add(groupTree);
        }
        else if(dto instanceof Empty) Warehouse.severe(((Empty) dto).getMsg());
    }

    @Override
    public String cellText(Group group) {
        return group.name;
    }

    @Override
    public List<Group> getChildren(Group parent) {
        long id = parent == null ? 0: parent.getId();
        List<Group> groupList = (List<Group>) groups.getList();
        return groupList.stream()
                .filter(item->id==item.groupId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isLeaf(Group group) {
        return group.isLeaf;
    }

    @Override
    public void onClick(Group group) {
        selectedGroup = group;
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
