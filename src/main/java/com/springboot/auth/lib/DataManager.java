package com.springboot.auth.lib;

import java.time.LocalDateTime;

public class DataManager {
    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }
}
