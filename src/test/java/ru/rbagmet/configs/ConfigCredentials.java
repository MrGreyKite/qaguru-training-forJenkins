package ru.rbagmet.configs;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/credentials.properties"})
public interface ConfigCredentials extends Config {

    String login();
    String password();
    String remoteUrl();
}
