package com.warehouse.client.present;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Дима on 22.04.2017.
 *
 */

public class UserListPresent extends Present
{
    @UiTemplate("com.warehouse.client.view.UserListView.ui.xml")
    interface UserListUIBinder extends UiBinder<Widget, UserListPresent> {}
    private static final UserListUIBinder binder = GWT.create(UserListUIBinder.class);

    @UiField VerticalPanel listPanel;
    @UiField AnchorListItem newUser;

    /**
     * A simple data type that represents a contact.
     */
    private static class Contact {
        private final String address;
        private final Date birthday;
        private final String name;

        Contact(String name, Date birthday, String address) {
            this.name = name;
            this.birthday = birthday;
            this.address = address;
        }
    }

    /**
     * The list of data to display.
     */
    private static final List<Contact> CONTACTS = Arrays.asList(
            new Contact("John", new Date(80, 4, 12), "123 Fourth Avenue"),
            new Contact("Joe", new Date(85, 2, 22), "22 Lance Ln"),
            new Contact("George",new Date(46, 6, 6),"1600 Pennsylvania Avenue"));

    UserListPresent()
    {
        initWidget(binder.createAndBindUi(this));

        CellTable<Contact> table = new CellTable<>();
        table.setWidth("100%", true);
        table.setAutoHeaderRefreshDisabled(true);
        table.setAutoFooterRefreshDisabled(true);
        table.setCondensed(true);
        table.setStriped(true);

        // Add a selection model so we can select cells.
        final SelectionModel<Contact> selectionModel = new SingleSelectionModel<>();
        table.setSelectionModel(selectionModel, DefaultSelectionEventManager.createCheckboxManager());

        Column<Contact, Boolean> checkColumn = new Column<Contact, Boolean>(
                new CheckboxCell(true, false)) {
            @Override
            public Boolean getValue(Contact object) {
                // Get the value from the selection model.
                return selectionModel.isSelected(object);
            }
        };
        table.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
        table.setColumnWidth(checkColumn, 40, Style.Unit.PX);

        TextColumn<Contact> nameColumn =
                new TextColumn<Contact>() {
                    @Override
                    public String getValue(Contact object) {
                        return object.name;
                    }
                };
        table.addColumn(nameColumn, "Name");

        DateCell dateCell = new DateCell();
        Column<Contact, Date> dateColumn
                = new Column<Contact, Date>(dateCell) {
            @Override
            public Date getValue(Contact object) {
                return object.birthday;
            }
        };
        table.addColumn(dateColumn, "Birthday");

        TextColumn<Contact> addressColumn
                = new TextColumn<Contact>() {
            @Override
            public String getValue(Contact object) {
                return object.address;
            }
        };
        table.addColumn(addressColumn, "Address");

        table.setRowCount(CONTACTS.size(), true);
        table.setRowData(0, CONTACTS);

        newUser.addClickHandler(clickEvent ->
        {
            newUser.setActive(true);

            UserDetailPresent.showDialog();

            newUser.setActive(false);
        });

        listPanel.add(table);
    }
}
