package com.warehouse.client.present;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.Warehouse;
import com.warehouse.shared.dto.DTO;
import com.warehouse.shared.dto.DTOEnum;
import com.warehouse.shared.dto.Rule;
import org.gwtbootstrap3.client.ui.base.HasReadOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Дима on 16.04.2017.
 *
 */

public abstract class Present extends Composite
{
    HashMap<String, Widget> widgets = new HashMap<>();
    public abstract void show();


    private boolean condition(Rule rule)
    {
        Warehouse.info("Check condition ");

        DTO dto = DTOEnum.getDTO(rule.getIfCondition());
        int condVal = rule.getValue();
        int id = dto.getId().intValue();

        Warehouse.info("Condition: {%d} {%s} {%d}", id, rule.getCondition(), condVal);

        switch (rule.getCondition())
        {
            case "=":  return id == condVal;
            case "!=": return id != condVal;
            case ">":  return id > condVal;
            case "<":  return id < condVal;
            case ">=": return id >= condVal;
            case "<=": return id <= condVal;
            default: return false;
        }
    }

    private void doApply(String id, Rule rule) throws Exception
    {
        Warehouse.info("#{%d} apply '{%s}' to {%s}", rule.getId(), rule.getApply(), id);

        if((rule.getIfCondition() != null) && !condition(rule)) return;
        Widget widget = widgets.get(id);
        switch (rule.getApply())
        {
            case '+':
                if(widget instanceof HasReadOnly) ((HasReadOnly) widget).setReadOnly(false);
                else if (widget instanceof HasEnabled) ((HasEnabled) widget).setEnabled(true);
                break;

            case '-':
                if (widget instanceof HasReadOnly) ((HasReadOnly) widget).setReadOnly(true);
                else if (widget instanceof HasEnabled) ((HasEnabled) widget).setEnabled(false);
                break;

            case 'v': widget.setVisible(true);break;
            case '/': widget.setVisible(false); break;

            case 's':
                if(widget instanceof HasText){
                    Warehouse.info("Updating text with "+ rule.getSetValue());
                    ((HasText) widget).setText(rule.getSetValue());
                }
        }
    }

    void internalApply(List<Rule> rules) throws Exception
    {
        Warehouse.info("Applying {%d} rule(s) for {%s}",  rules.size(), this.getClass().getName());
        if(rules.size() == 0) return;

        for(Rule rule: rules)
        {
            List<String> widgetIDS = new ArrayList<>(Arrays.asList(rule.getWidgets().split(";")));

            for(String widgetID: widgetIDS)
            {
                if(widgets.containsKey(widgetID))
                    doApply(widgetID, rule);
            }
        }
    }
}
