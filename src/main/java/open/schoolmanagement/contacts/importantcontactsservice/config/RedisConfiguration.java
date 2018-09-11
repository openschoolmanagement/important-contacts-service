/*
   Copyright 2018 Open School Management

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package open.schoolmanagement.contacts.importantcontactsservice.config;

import java.util.UUID;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.model.EventProcessingResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

/**
 * Redis configuration
 */
@Configuration
public class RedisConfiguration {
  @Bean
  JedisConnectionFactory jedisConnectionFactory(
      @Value("${importantcontacts.redis.hostname}") String hostname,
      @Value("${importantcontacts.redis.port}") int port,
      @Value("${importantcontacts.redis.password}") String password) {
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();

    configuration.setHostName(hostname);
    configuration.setPort(port);
    configuration.setPassword(RedisPassword.of(password));

    return new JedisConnectionFactory(configuration);
  }

  @Bean
  public RedisTemplate<UUID, EventProcessingResult> eventProcessingResultRedisTemplate(
      JedisConnectionFactory connectionFactory) {

    final RedisTemplate<UUID, EventProcessingResult> template =
        new RedisTemplate<UUID, EventProcessingResult>();

    template.setConnectionFactory(connectionFactory);
    // TODO That's not working, create a class that implements RedisSerializer<T>
    template.setValueSerializer(
        new GenericToStringSerializer<EventProcessingResult>(EventProcessingResult.class));

    return template;
  }
}
