package com.warehouse.client.present;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.Warehouse;
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
    public HashMap<String, Widget> widgets = new HashMap<>();
    public abstract void show();


    private void doApply(String id, Rule rule)
    {
        Warehouse.info("#{%d} apply '{%s}' to {%s}", rule.getId(), rule.getApply(), id);

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
        }
    }

    void lock(){

        Warehouse.info("Set readonly");
        List<String> widgetIDList = new ArrayList<>(widgets.keySet());
        Rule rule = new Rule();
        rule.setId(0L);
        rule.setApply('-');
        widgetIDList.forEach(id->doApply(id, rule));
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
