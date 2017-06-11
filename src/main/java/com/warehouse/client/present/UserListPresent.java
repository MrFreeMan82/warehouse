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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;
import com.warehouse.client.Warehouse;
import com.warehouse.client.utils.DialogBuilder;
import com.warehouse.client.utils.Dockable;
import com.warehouse.client.utils.Server;
import com.warehouse.shared.dto.*;
import com.warehouse.shared.dto.ServerException;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.request.SQL;
import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.gwt.DataGrid;

import java.util.List;

/**
 * Created by Дима on 22.04.2017.
 *
 */

public class UserListPresent extends Present implements Dockable<Present>, Comparable<UserDetail>
{
    static final String TAG = "com.warehouse.client.present.UserListPresent";

    @UiTemplate("com.warehouse.client.page.UserListPage.ui.xml")
    interface UserListUIBinder extends UiBinder<Widget, UserListPresent> {}
    private static final UserListUIBinder binder = GWT.create(UserListUIBinder.class);

    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem newUser;
    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem editUser;
    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem deleteUser;
    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem viewUser;
    @SuppressWarnings("WeakerAccess") @UiField(provided = true) DataGrid<UserDetail> dataGrid = new DataGrid<>();

    private final ProvidesKey<UserDetail> KEY_PROVIDER = userDetail -> userDetail == null ? null : userDetail.getId();
    private final SingleSelectionModel<UserDetail> selectionModel = new SingleSelectionModel<>(KEY_PROVIDER);

    private void newUser() {
        new DialogBuilder<UserDetailDialog>()
                .setPresent(new UserDetailDialog())
                .setTitle(Warehouse.i18n.userTitle())
                .addPositiveButton(Warehouse.i18n.captionSave())
                .addNegativeButton(Warehouse.i18n.captionCancel())
                .build()
                .show();
    }

    private void editUser(UserDetail userDetail){
        if(userDetail != null) {
            new DialogBuilder<UserDetailDialog>()
                    .setPresent(new UserDetailDialog(userDetail))
                    .setTitle(Warehouse.i18n.userTitle())
                    .addPositiveButton(Warehouse.i18n.captionSave())
                    .addNegativeButton(Warehouse.i18n.captionCancel())
                    .build()
                    .show();
        }
    }

    private void viewUser(UserDetail userDetail) {
        if(userDetail != null) {
            new DialogBuilder<UserDetailDialog>()
                    .setPresent(new UserDetailDialog(userDetail))
                    .setLocked()
                    .setTitle(Warehouse.i18n.userTitle())
                    .addNegativeButton(Warehouse.i18n.captionCancel())
                    .build()
                    .show();
        }
    }

    private void deleteUser(UserDetail userDetail){
        if(userDetail != null) Server.setCallback(null).delete(new Request(userDetail));
    }

    private void onReceiveUserList(DTO list){

        if(list instanceof Hashed) {

            Hashed userList = ((Hashed) list);
            dataGrid.setRowCount(userList.getList().size());
            dataGrid.setRowData(0, (List<UserDetail>) userList.getList());
            dataGrid.redraw();
        }
        else if (list instanceof ServerException) Warehouse.severe(((ServerException) list).getMsg());
    }

    UserListPresent() {

        initWidget(binder.createAndBindUi(this));
        Server.setCallback(this::onReceiveUserList).findList(new Request(SQL.USER_LIST, new UserDetail()));

        newUser.setText(Warehouse.i18n.captionNew());
        newUser.addClickHandler(clickEvent -> newUser());
        editUser.setText(Warehouse.i18n.captionEdit());
        editUser.addClickHandler(clickEvent -> editUser(selectionModel.getSelectedObject()));
        deleteUser.setText(Warehouse.i18n.captionDelete());
        deleteUser.addClickHandler(clickEvent -> deleteUser(selectionModel.getSelectedObject()));
        viewUser.setText(Warehouse.i18n.captionView());
        viewUser.addClickHandler(clickEvent -> viewUser(selectionModel.getSelectedObject()));

        dataGrid.setWidth("100%");
        dataGrid.setHeight("100%");
        dataGrid.setAutoHeaderRefreshDisabled(true);
        dataGrid.setEmptyTableWidget(new Label("ServerException"));
        dataGrid.setSelectionModel(selectionModel, DefaultSelectionEventManager.createCheckboxManager());

        Column<UserDetail, Boolean> checkColumn =
                new Column<UserDetail, Boolean>(new CheckboxCell(true, false)) {
                    @Override
                    public Boolean getValue(UserDetail object) {
                        return selectionModel.isSelected(object);
                    }
                };
        dataGrid.setColumnWidth(checkColumn, 40, Style.Unit.PX);
        dataGrid.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));

        TextColumn<UserDetail> id = new TextColumn<UserDetail>() {
            @Override
            public String getValue(UserDetail userDetail) {
                return Long.toString(userDetail.getId());
            }
        };
        dataGrid.setColumnWidth(id, 40, Style.Unit.PX);
        dataGrid.addColumn(id, Warehouse.i18n.captionID());

        TextColumn<UserDetail> type = new TextColumn<UserDetail>() {
            @Override
            public String getValue(UserDetail userDetail) {
                return String.valueOf(userDetail.getType());
            }
        };
        dataGrid.setColumnWidth(type, 20, Style.Unit.PCT);
        dataGrid.addColumn(type, Warehouse.i18n.captionType());

        TextColumn<UserDetail> name = new TextColumn<UserDetail>() {
            @Override
            public String getValue(UserDetail userDetail) {
                return userDetail.getName();
            }
        };
        dataGrid.setColumnWidth(name, 20, Style.Unit.PCT);
        dataGrid.addColumn(name, Warehouse.i18n.captionName());

        TextColumn<UserDetail> password = new TextColumn<UserDetail>() {
            @Override
            public String getValue(UserDetail userDetail) {
                return userDetail.getPassword();
            }
        };
        dataGrid.setColumnWidth(password, 20, Style.Unit.PCT);
        dataGrid.addColumn(password, Warehouse.i18n.captionPassword());
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
