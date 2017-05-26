package com.warehouse.server;

import com.warehouse.shared.dto.UserDetail;
import com.warehouse.shared.dto.UserType;
import junit.framework.TestCase;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by Дима on 25.04.2017.
 *
 */

public class UserDetailConstraintTest extends TestCase
{
    private static Validator validator;

    public void setUp()
    {
        try {
            super.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public void testUserDetailConstraint()
    {
        UserType type = new UserType();
        type.setId(1L);
        type.setName("Admin");

        UserDetail user = new UserDetail();
        user.setType(type.getId());
        user.setPassword("fdffdsr");
        user.setName("dima");
        Set<ConstraintViolation<UserDetail>> constraint = validator.validate(user);
        assertEquals(0, constraint.size());
    }
}
