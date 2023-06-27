package com.usuario.service.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
	
	@Bean
	public NewTopic generateTopic() {
		Map<String,String> configurations = new HashMap<>();
		//Elige la forma en la que borra el mensaje
		configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG,TopicConfig.CLEANUP_POLICY_DELETE);
		//Tiempo en ms que va a mantener kafka los mensajes, para este caso 1 día
		configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
		//Tamaño máximo del segmento 1GB
		configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); 
		//Tamaño máximo de cada mensaje para el caso 1MB
		configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000000");
		return TopicBuilder.name("unProgramadorNace-Topic")
				.partitions(2)
				.replicas(2)
				.build();
	}

}
