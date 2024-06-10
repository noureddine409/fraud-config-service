package com.example.configurationservice.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CoreConstant {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ExceptionMessage {
        public static final String DEFAULT = "message.exception.default";
        public static final String NOT_FOUND = "message.exception.not.found.element";
        public static final String ALREADY_EXISTS = "message.exception.already.exists.element";
        public static final String DATA_INTEGRITY_VIOLATION_SAVE = "message.exception.data.integrity.violation.save";
        public static final String DATA_INTEGRITY_VIOLATION_UPDATE = "message.exception.data.integrity.violation.update";
        public static final String OPTIMISTIC_LOCKING_FAILURE = "message.exception.optimistic.locking.failure";
        public static final String DELETE_ELEMENT = "message.exception.delete.element";
        public static final String SAVE_ELEMENT = "message.exception.save.element";
        public static final String FIND_ELEMENTS = "message.exception.find.elements";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Pagination {
        public static final int DEFAULT_PAGE_NUMBER = 0;
        public static final int DEFAULT_PAGE_SIZE = 20;
        public static final int MAX_PAGE_SIZE = 20;
        public static final String DEFAULT_SORT_PROPERTY = "createdAt";
        public static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.DESC;
    }



}
