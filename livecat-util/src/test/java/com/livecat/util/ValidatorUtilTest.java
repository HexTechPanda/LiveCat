package com.livecat.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorUtilTest {
    @Test
    void isMobile() {
        assertTrue(ValidatorUtil.isMobile("88886666"));
        assertFalse(ValidatorUtil.isMobile("188886666"));
        assertFalse(ValidatorUtil.isMobile("abcd8888"));
        assertFalse(ValidatorUtil.isMobile("1"));
    }
}