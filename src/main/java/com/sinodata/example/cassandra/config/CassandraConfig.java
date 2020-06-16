package com.sinodata.example.cassandra.config;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.session.Session;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.AsyncCassandraTemplate;

/**
 * FileName: ApplicationConfig
 * Author:  liuyang
 * Date:    2020/6/911:38
 * Description: 配置类
 * History:
 * <author>     <time>      <version>       <desc>
 */

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        return "pl";
    }

    @Override
    protected String getContactPoints() {
        return "10.6.136.83";
    }

    @Override
    protected int getPort() {
        return 9042;
    }

    @Override
    protected String getLocalDataCenter() {
        return "datacenter1";
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[] {"com.sinodata.example.cassandra.entity"};
    }

    @Bean
    AsyncCassandraTemplate asyncCassandraTemplate(Session session) {
        return new AsyncCassandraTemplate((CqlSession) session);
    }

}
