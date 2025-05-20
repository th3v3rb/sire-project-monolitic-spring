package com.dantesoft.siremono.internal.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    basePackages = "com.dantesoft.siremono",
    useDefaultFilters = false,
    includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = com.dantesoft.siremono.internal.commands.AbstractCommand.class
    )
)
public class CommandScanConfig {
}
