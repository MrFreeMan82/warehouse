package com.warehouse.server;

import com.warehouse.shared.dto.UserDetailDTO;
import com.warehouse.shared.dto.UserTypeDTO;
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
        UserTypeDTO type = new UserTypeDTO();
        type.setId(1L);
        type.setName("Admin");

        UserDetailDTO user = new UserDetailDTO();
        user.setUserType(type);
        user.setPassword("fdffdsr");
        user.setName("dima");
        Set<ConstraintViolation<UserDetailDTO>> constraint = validator.validate(user);
        assertEquals(0, constraint.size());
    }
}
