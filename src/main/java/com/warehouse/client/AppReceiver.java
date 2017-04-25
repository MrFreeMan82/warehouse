package com.warehouse.client;

import com.google.gwt.user.client.Window;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Created by Дима on 24.04.2017.
 *
 */

public abstract class AppReceiver<T> extends Receiver<T>
{
    @Override
    public abstract void onSuccess(T result);

    @Override
    public void onFailure(ServerFailure error)
    {
        Warehouse.logger.severe(new LogEvent(error).toString());
        Window.alert("Server Error. Try later.");
    }

    @Override
    public void onConstraintViolation(Set<ConstraintViolation<?>> violations)
    {
        Warehouse.logger.severe(
            new LogEvent(new Exception(
                    "Constraint violation on field '" +
                        violations.iterator().next().getPropertyPath().
                                    toString().toUpperCase() + "'")).toString());
    }
}
