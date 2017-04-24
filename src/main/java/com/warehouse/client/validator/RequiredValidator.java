package com.warehouse.client.validator;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.warehouse.client.Warehouse;
import org.gwtbootstrap3.client.ui.form.error.BasicEditorError;
import org.gwtbootstrap3.client.ui.form.validator.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 24.04.2017.
 *
 */

public class RequiredValidator implements Validator<String>
{

    @Override public int getPriority() {return Priority.HIGHEST; }

    @Override
    public List<EditorError> validate(Editor<String> editor, String t)
    {
        List<EditorError> result = new ArrayList<>();
        if((t == null) || (t.equals("")))
        {
            result.add(new BasicEditorError(editor, t, Warehouse.i18n.validatorRequired()));
        }
        return result;
    }
}
