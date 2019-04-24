package org.mintleaf.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author: MengchuZhang
 * @Date: 2018/8/3 16:04
 * @Version 1.0
 */

@Configuration
public class DataSourceConfig {
	@Bean(name = "core")
	public DataSource zmc(Environment env) {
		DruidDataSource ds =new DruidDataSource();
		ds.setUrl(env.getProperty("spring.core.url"));
		ds.setUsername(env.getProperty("spring.core.username"));
		ds.setPassword(env.getProperty("spring.core.password"));
		ds.setDriverClassName(env.getProperty("spring.core.driver-class-name"));
		//configuration
		ds.setInitialSize(Integer.valueOf(env.getProperty("spring.core.initialSize")));
		ds.setMinIdle(Integer.valueOf(env.getProperty("spring.core.minIdle")));
		ds.setMaxActive(Integer.valueOf(env.getProperty("spring.core.maxActive")));
		ds.setMaxWait(Integer.valueOf(env.getProperty("spring.core.maxWait")));
		ds.setTimeBetweenEvictionRunsMillis(Integer.valueOf(env.getProperty("spring.core.timeBetweenEvictionRunsMillis")));
		ds.setMinEvictableIdleTimeMillis(Integer.valueOf(env.getProperty("spring.core.minEvictableIdleTimeMillis")));
		ds.setValidationQuery(env.getProperty("spring.core.validationQuery"));
		ds.setTestWhileIdle(Boolean.valueOf(env.getProperty("spring.core.testWhileIdle")));
		ds.setTestOnBorrow(Boolean.valueOf(env.getProperty("spring.core.testOnBorrow")));
		ds.setTestOnReturn(Boolean.valueOf(env.getProperty("spring.core.testOnReturn")));
		ds.setPoolPreparedStatements(Boolean.valueOf(env.getProperty("spring.core.poolPreparedStatements")));
		ds.setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(env.getProperty("spring.core.maxPoolPreparedStatementPerConnectionSize")));
		try {
			ds.setFilters(env.getProperty("spring.core.filters"));
		} catch (SQLException e) {
			System.err.println("druid configuration initialization filter: " + e);
		}
		ds.setConnectionProperties(env.getProperty("spring.core.connectionProperties"));
		ds.setUseGlobalDataSourceStat(Boolean.valueOf(env.getProperty("spring.core.useGlobalDataSourceStat")));

		return ds;
	}

	@Bean(name = "video")
	public DataSource video(Environment env) {
		DruidDataSource ds =new DruidDataSource();
		ds.setUrl(env.getProperty("spring.video.url"));
		ds.setUsername(env.getProperty("spring.video.username"));
		ds.setPassword(env.getProperty("spring.video.password"));
		ds.setDriverClassName(env.getProperty("spring.video.driver-class-name"));
		//configuration
		ds.setInitialSize(Integer.valueOf(env.getProperty("spring.video.initialSize")));
		ds.setMinIdle(Integer.valueOf(env.getProperty("spring.video.minIdle")));
		ds.setMaxActive(Integer.valueOf(env.getProperty("spring.video.maxActive")));
		ds.setMaxWait(Integer.valueOf(env.getProperty("spring.video.maxWait")));
		ds.setTimeBetweenEvictionRunsMillis(Integer.valueOf(env.getProperty("spring.video.timeBetweenEvictionRunsMillis")));
		ds.setMinEvictableIdleTimeMillis(Integer.valueOf(env.getProperty("spring.video.minEvictableIdleTimeMillis")));
		ds.setValidationQuery(env.getProperty("spring.video.validationQuery"));
		ds.setTestWhileIdle(Boolean.valueOf(env.getProperty("spring.video.testWhileIdle")));
		ds.setTestOnBorrow(Boolean.valueOf(env.getProperty("spring.video.testOnBorrow")));
		ds.setTestOnReturn(Boolean.valueOf(env.getProperty("spring.video.testOnReturn")));
		ds.setPoolPreparedStatements(Boolean.valueOf(env.getProperty("spring.video.poolPreparedStatements")));
		ds.setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(env.getProperty("spring.video.maxPoolPreparedStatementPerConnectionSize")));
		try {
			ds.setFilters(env.getProperty("spring.video.filters"));
		} catch (SQLException e) {
			System.err.println("druid configuration initialization filter: " + e);
		}
		ds.setConnectionProperties(env.getProperty("spring.video.connectionProperties"));
		ds.setUseGlobalDataSourceStat(Boolean.valueOf(env.getProperty("spring.video.useGlobalDataSourceStat")));
		return ds;
	}

	@Bean(name = "zframe")
	public DataSource test(Environment env) {
		DruidDataSource ds =new DruidDataSource();
		ds.setUrl(env.getProperty("spring.zframe.url"));
		ds.setUsername(env.getProperty("spring.zframe.username"));
		ds.setPassword(env.getProperty("spring.zframe.password"));
		ds.setDriverClassName(env.getProperty("spring.zframe.driver-class-name"));
		return ds;
	}

}