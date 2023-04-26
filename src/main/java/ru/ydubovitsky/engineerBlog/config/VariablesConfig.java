package ru.ydubovitsky.engineerBlog.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@NoArgsConstructor
@Getter @Setter
@Configuration
public class VariablesConfig {

    @Value("${application.jwt.securityKey}")
    private String securityKey;

    @Value("${application.jwt.tokenPrefix}")
    private String tokenPrefix;

    @Value("${application.admin.name}")
    private String initAdminName;

    @Value("${application.admin.password}")
    private String initAdminPassword;

}
