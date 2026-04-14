package com.session08_bai04.custom_validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Objects;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

//        BeanWrapperImpl làm việc với dữ liệu động giúp hệ thống không gây lỗi vì nó tự xử lí các lỗi gây sập hệ thôngs
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

        /* Dùng Objects.equals() của Java core
         * Nó xử lý an toàn mọi trường hợp:
         * - Nếu cả 2 null -> return true
         * - Nếu 1 trong 2 null -> return false
         * - Nếu cả 2 khác null -> tự động gọi equals()
         */

        boolean isValid = Objects.equals(fieldValue, fieldMatchValue);

        if (!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(fieldMatch)
                    .addConstraintViolation();
        }
        return isValid;
    }
}
