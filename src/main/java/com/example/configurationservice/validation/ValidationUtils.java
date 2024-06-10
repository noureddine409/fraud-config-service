package com.example.configurationservice.validation;


import com.example.configurationservice.dto.BaseQuery;
import lombok.experimental.UtilityClass;

import static com.example.configurationservice.common.CoreConstant.Pagination.*;

@UtilityClass
public class ValidationUtils {

    public static void validateQuery(BaseQuery query) {
        if (query.getPage() < 0) {
            query.setPage(DEFAULT_PAGE_NUMBER);
        }
        if (query.getSize() <= 0 || query.getSize() > MAX_PAGE_SIZE) {
            query.setSize(DEFAULT_PAGE_SIZE);
        }
        if (query.getSortProperty() == null) {
            query.setSortProperty(DEFAULT_SORT_PROPERTY);
        }
        if (query.getSortDirection() == null) {
            query.setSortDirection(DEFAULT_SORT_DIRECTION.name());
        }
    }
}
