package com.warehouse.client.present;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;
import com.warehouse.client.Warehouse;
import com.warehouse.client.utils.DialogBuilder;
import com.warehouse.client.utils.Dockable;
import com.warehouse.client.utils.Server;
import com.warehouse.shared.dto.DTO;
import com.warehouse.shared.dto.Empty;
import com.warehouse.shared.dto.ListDTO;
import com.warehouse.shared.dto.UserDetail;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.request.Type;
import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;


import java.util.List;

/**
 * Created by Дима on 22.04.2017.
 *
 */

public class UserListPresent extends Present implements Dockable<Present>, Comparable<UserDetail>
{
    static final String TAG = "com.warehouse.client.page.UserListPresent";

    @UiTemplate("com.warehouse.client.page.UserListPage.ui.xml")
    interface UserListUIBinder extends UiBinder<Widget, UserListPresent> {}
    private static final UserListUIBinder binder = GWT.create(UserListUIBinder.class);

    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem newUser;
    @SuppressWarnings("WeakerAccess") @UiField(provided = true) DataGrid<UserDetail> dataGrid = new DataGrid<>();

    private static final ProvidesKey<UserDetail> KEY_PROVIDER = userDetail -> userDetail == null ? null : userDetail.getId();

    private void newUser() {
        new DialogBuilder<UserDetailPresent>()
                .setPresent(new UserDetailPresent())
                .setTitle(Warehouse.i18n.userTitle())
                .addPositiveButton(Warehouse.i18n.captionSave())
                .addNegativeButton(Warehouse.i18n.captionCancel())
                .build()
                .show();
    }

    private void onReceiveUserList(DTO list){

        if(list instanceof ListDTO) {

            ListDTO listDTO = ((ListDTO) list);
            Warehouse.info("Receive List " + listDTO.getList().size());
            List<UserDetail> users = (List<UserDetail>) listDTO.getList();
            dataGrid.setRowCount(users.size());
            dataGrid.setRowData(0, users);
            dataGrid.redraw();
        }
        else if (list instanceof Empty) Warehouse.severe(((Empty) list).getMsg());
    }

    UserListPresent() {

        initWidget(binder.createAndBindUi(this));
        Server.setCallback(this::onReceiveUserList).findList(new Request(Type.USER_LIST, new UserDetail()));

        newUser.addClickHandler(clickEvent -> newUser());

        dataGrid.setWidth("100%");
        dataGrid.setHeight("100%");
        dataGrid.setAutoHeaderRefreshDisabled(true);
        dataGrid.setEmptyTableWidget(new Label("Empty"));
        final SingleSelectionModel<UserDetail> selectionModel = new SingleSelectionModel<>(KEY_PROVIDER);
        selectionModel.addSelectionChangeHandler(selection -> selectionModel.getSelectedObject());
        dataGrid.setSelectionModel(selectionModel, DefaultSelectionEventManager.createCheckboxManager());

        Column<UserDetail, Boolean> checkColumn =
                new Column<UserDetail, Boolean>(new CheckboxCell(true, false)) {
                    @Override
                    public Boolean getValue(UserDetail object) {
                        return selectionModel.isSelected(object);
                    }
                };
        dataGrid.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
        dataGrid.setColumnWidth(checkColumn, 40, Style.Unit.PX);

        TextColumn<UserDetail> id = new TextColumn<UserDetail>() {
            @Override
            public String getValue(UserDetail userDetail) {
                return userDetail.getId().toString();
            }
        };
        dataGrid.addColumn(id, "ID");
        dataGrid.setColumnWidth(id, 40, Style.Unit.PX);

        TextColumn<UserDetail> name = new TextColumn<UserDetail>() {
            @Override
            public String getValue(UserDetail userDetail) {
                return userDetail.getName();
            }
        };
        dataGrid.addColumn(name, "Name");
        dataGrid.setColumnWidth(name, 20, Style.Unit.PCT);

        TextColumn<UserDetail> password = new TextColumn<UserDetail>() {
            @Override
            public String getValue(UserDetail userDetail) {
                return userDetail.getPassword();
            }
        };
        dataGrid.addColumn(password, "Password");
        dataGrid.setColumnWidth(password, 20, Style.Unit.PCT);
    }

    @Override
    public int compareTo(UserDetail userDetail) {
        return 0;
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
