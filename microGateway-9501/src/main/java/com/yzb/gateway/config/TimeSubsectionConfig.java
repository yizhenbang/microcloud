package com.yzb.gateway.config;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * ClassName: TimeSubsectionConfig
 * Description: 配置文件可以直接通过application直接配置实现
 * date: 2021/12/26 19:42
 *
 * @author ZhenBang-Yi
 * @version 1.0
 * @since JDK 1.8
 */
public class TimeSubsectionConfig {
    private Set<String> section = new LinkedHashSet<>();

    public Set<String> getSection() {
        return section;
    }

    public void setSection(List<String> section) {
        this.section.addAll(section);
    }
}
