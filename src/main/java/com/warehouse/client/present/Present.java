package com.warehouse.client.present;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.Warehouse;
import com.warehouse.shared.dto.RuleDTO;
import org.gwtbootstrap3.client.ui.base.HasId;
import org.gwtbootstrap3.client.ui.base.HasReadOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Дима on 16.04.2017.
 *
 */

public abstract class Present extends Composite
{
    List<Widget> widgets;
    public abstract void show();

    private void doApply(Widget widget, char action, int ruleNo) throws Exception
    {
        Warehouse.info("{%d} Applying action '{%s}' to {%s}", ruleNo, action, ((HasId) widget).getId());

        switch (action)
        {
            case '+': if(widget instanceof HasReadOnly) ((HasReadOnly) widget).setReadOnly(false);
                      else ((HasEnabled) widget).setEnabled(true); break;

            case '-': if(widget instanceof HasReadOnly) ((HasReadOnly) widget).setReadOnly(true);
                      else ((HasEnabled) widget).setEnabled(false); break;

            case 'v': widget.setVisible(true); break;
            case '/': widget.setVisible(false); break;
        }
    }

    void internalApply(List<RuleDTO> rules) throws Exception
    {
        Warehouse.info("Applying {%d} rule(s) for {%s}",  (rules == null? -1: rules.size()), this.getClass().getName());
        if(rules == null) return;

        int ruleNo = 0;
        for(RuleDTO rule: rules)
        {
            ruleNo++;
            List<String> widgetIDS = new ArrayList<>(Arrays.asList(rule.getWidgets().split(";")));

            for(String widgetID: widgetIDS)
            {
                for (Widget widget : widgets)
                {
                    if (((HasId) widget).getId().equals(widgetID))
                    {
                        doApply(widget, rule.getAction(), ruleNo);
                        break;
                    }
                }
            }
        }
    }
}
