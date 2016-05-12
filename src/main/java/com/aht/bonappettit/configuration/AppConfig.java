package com.aht.bonappettit.configuration;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.data.neo4j.server.Neo4jServer;

@Configuration
@ComponentScan({"com.aht.bonappettit", "com.aht.api"})
@EnableTransactionManagement
@EnableNeo4jRepositories("com.aht.bonappettit.repository")
public class AppConfig extends Neo4jConfiguration {

	@Override
	@Bean
	public Neo4jServer neo4jServer() {
		return new RemoteServer("http://localhost:7474", "neo4j", "n0m3l0s3");
	}

	@Override
	@Bean
	public SessionFactory getSessionFactory() {
		return new SessionFactory("com.aht.bonappettit.domain");
	}
}
