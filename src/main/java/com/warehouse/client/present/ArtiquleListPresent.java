package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;
import com.warehouse.client.Warehouse;
import com.warehouse.client.constant.PriceType;
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

public class ArtiquleListPresent extends Present implements Dockable<Present>, CellInfo<Group> {

    static final String TAG = "com.warehouse.client.present.ArtiquleListPresent";

    @UiTemplate("com.warehouse.client.page.ArtiquleListPage.ui.xml")
    interface ArtiquleUIBinder extends UiBinder<Widget, ArtiquleListPresent>{}
    private static final ArtiquleUIBinder binder = GWT.create(ArtiquleUIBinder.class);

    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem newGroup;
    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem editGroup;
    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem newArtiqule;
    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem editArtiqule;
    @SuppressWarnings("WeakerAccess") @UiField Button search;
    @SuppressWarnings("WeakerAccess") @UiField(provided = true) DataGrid<Artiqule> artiquleGrid= new DataGrid<>();
    @SuppressWarnings("WeakerAccess") @UiField Tree groupTree;

   // private CellTree.Resources resources = GWT.create(CellTree.BasicResources.class);
    private final ProvidesKey<Artiqule> KEY_PROVIDER = artiqule -> artiqule == null ? null : artiqule.getId();
    private final SingleSelectionModel<Artiqule> selectedArtiqule = new SingleSelectionModel<>(KEY_PROVIDER);
    private DynamicTreeModel treeModel;
    private HashedDTO groups;
    private HashedDTO artiqules;
    private Group selectedGroup;


    public ArtiquleListPresent(){
        initWidget(binder.createAndBindUi(this));
        Server.setCallback(this::onReceiveGroups).findList(new Request(SQL.GROUPS, new Group()));

        newGroup.addClickHandler(clickEvent -> newGroup());
        editGroup.addClickHandler(clickEvent -> editGroup(selectedGroup));
        newArtiqule.addClickHandler(clickEvent -> newArtiqule());
        editArtiqule.addClickHandler(clickEvent -> editArtiqule(selectedArtiqule.getSelectedObject()));


        artiquleGrid.setWidth("100%");
        artiquleGrid.setHeight("100%");
        artiquleGrid.setAutoHeaderRefreshDisabled(true);
        artiquleGrid.setEmptyTableWidget(new Label("Empty"));
        artiquleGrid.setSelectionModel(selectedArtiqule);

        TextColumn<Artiqule> id = new TextColumn<Artiqule>() {
            @Override
            public String getValue(Artiqule artiqule) {
                return Long.toString(artiqule.getId());
            }
        };
        artiquleGrid.setColumnWidth(id, 40, Style.Unit.PX);
        artiquleGrid.addColumn(id, Warehouse.i18n.captionID());

        TextColumn<Artiqule> artiquleColumn = new TextColumn<Artiqule>() {
            @Override
            public String getValue(Artiqule artiqule) {
                return artiqule.name;
            }
        };
        artiquleGrid.setColumnWidth(artiquleColumn, 20, Style.Unit.PCT);
        artiquleGrid.addColumn(artiquleColumn, Warehouse.i18n.captionName());

        TextColumn<Artiqule> metricName = new TextColumn<Artiqule>() {
            @Override
            public String getValue(Artiqule artiqule) {
                return artiqule.metric.name;
            }
        };
        artiquleGrid.setColumnWidth(metricName, 20, Style.Unit.PCT);
        artiquleGrid.addColumn(metricName, Warehouse.i18n.captionMetricName());

        TextColumn<Artiqule> roznPrice = new TextColumn<Artiqule>() {
            @Override
            public String getValue(Artiqule artiqule) {
                return artiqule.prices.size() == 0 ? "-":
                        NumberFormat.getFormat("0.00").format(
                                artiqule.prices.stream()
                                        .filter(price -> price.typeId.equals(PriceType.ROZNICA.Id()))
                                        .collect(Collectors.toList()).get(0).price / 100F
                        );
            }
        };
        artiquleGrid.setColumnWidth(roznPrice, 10, Style.Unit.PCT);
        artiquleGrid.addColumn(roznPrice, Warehouse.i18n.captionPriceRozn());

        TextColumn<Artiqule> optPrice = new TextColumn<Artiqule>() {
            @Override
            public String getValue(Artiqule artiqule) {
                return artiqule.prices.size() == 0 ? "-":
                        NumberFormat.getFormat("0.00").format(
                                artiqule.prices.stream()
                                        .filter(price -> price.typeId.equals(PriceType.OPT.Id()))
                                        .collect(Collectors.toList()).get(0).price / 100F
                        );
            }
        };
        artiquleGrid.setColumnWidth(optPrice, 10, Style.Unit.PCT);
        artiquleGrid.addColumn(optPrice, Warehouse.i18n.captionPriceOpt());
    }

    private void newGroup(){
        if(selectedGroup == null){
                Window.alert(Warehouse.i18n.alertChooseGroup());
                return;
        }
        new DialogBuilder<GroupDialog>()
                .setPresent(new GroupDialog().createAt(selectedGroup))
                .setTitle(Warehouse.i18n.titleCreate())
                .addPositiveButton(Warehouse.i18n.captionSave())
                .setCallback(dto -> Server.setCallback(this::onReceiveGroups).refresh(new Request(new Group(dto.getId()))))
                .addNegativeButton(Warehouse.i18n.captionCancel())
                .build().show();
    }

    private void editGroup(Group group) {
        if(group == null){
            Window.alert(Warehouse.i18n.alertChooseGroup());
            return;
        }
        new DialogBuilder<GroupDialog>()
                .setPresent(new GroupDialog().update(group))
                .setTitle(Warehouse.i18n.titleUpdate(group.name))
                .addPositiveButton(Warehouse.i18n.captionSave())
                .setCallback(dto -> Server.setCallback(this::onReceiveGroups).refresh(new Request(new Group(group.getId()))))
                .addNegativeButton(Warehouse.i18n.captionCancel())
                .build().show();
    }

    private void newArtiqule(){
        if(selectedGroup == null){
            Window.alert(Warehouse.i18n.alertChooseGroup());
            return;
        }
        new DialogBuilder<ArtiquleDialog>()
                .setTitle(Warehouse.i18n.titleCreate())
                .setPresent(new ArtiquleDialog().create(selectedGroup))
                .addPositiveButton(Warehouse.i18n.captionSave())
                .setCallback(dto-> Server.setCallback(this::onReceiveArtiqules)
                                    .refresh(new Request(new Artiqule().setIdent(dto.getId())))
                )
                .addNegativeButton(Warehouse.i18n.captionCancel())
                .build().show();
    }

    private void editArtiqule(Artiqule artiqule){
        if(artiqule == null){
            Window.alert(Warehouse.i18n.alertChooseArtiqule());
            return;
        }
        new DialogBuilder<ArtiquleDialog>()
                .setTitle(Warehouse.i18n.titleUpdate(artiqule.name))
                .setPresent(new ArtiquleDialog().update(artiqule))
                .addPositiveButton(Warehouse.i18n.captionSave())
                .setCallback(dto->  Server.setCallback(this::onReceiveArtiqules)
                                        .refresh(new Request(new Artiqule().setIdent(artiqule.getId())))
                )
                .addNegativeButton(Warehouse.i18n.captionCancel())
                .build().show();
    }

    private void onReceiveGroups(DTO dto) {

        if(dto instanceof HashedDTO) {
            groups = (HashedDTO) dto;
            treeModel = new DynamicTreeModel<>(groupTree, this, groups);
        }
        else if(dto instanceof Group){
            if(groups.contains(dto)){
                groups.replace(dto);
                treeModel.update(dto);
            } else {
                groups.put(dto);
                treeModel.add(dto);
            }
        }
    }

    private void refreshGrid(List<Artiqule> artiquleList){
        artiquleGrid.setRowCount(artiquleList.size());
        artiquleGrid.setRowData(artiquleList);
        artiquleGrid.redraw();
    }

    private void onReceiveArtiqules(DTO dto){
        if(dto instanceof HashedDTO) {
            artiqules = (HashedDTO) dto;
            refreshGrid((List<Artiqule>) artiqules.getList());
        }
        else if(dto instanceof Artiqule) {
            refreshGrid((List<Artiqule>) artiqules.merge(dto).getList());
        }
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
        Server.setCallback(this::onReceiveArtiqules)
                .findList(new Request(SQL.ARTIQULES_BY_GROUP, new Artiqule().setGroupId(selectedGroup.getId())));
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
