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

public class SizeValidator implements Validator<String>
{
    private int min, max;

    public SizeValidator(int min, int max){this.min = min; this.max = max;}

    @Override
    public int getPriority() {return Priority.HIGHEST; }

    @Override
    public List<EditorError> validate(Editor<String> editor, String s)
    {
        List<EditorError> result = new ArrayList<>();

        if((s == null) || (s.length() < min) || (s.length() > max))
                result.add(new BasicEditorError(editor, s, Warehouse.i18n.validatorSize(min, max)));

        return result;
    }
}
