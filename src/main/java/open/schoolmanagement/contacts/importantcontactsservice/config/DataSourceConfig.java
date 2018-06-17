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

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration of the data source.
 */
@Configuration
public class DataSourceConfig {
  /**
   * Bean getter for the data source.
   *
   * @param databaseUrl the database url
   * @param username    the username
   * @param password    the password
   * @return the data source
   */
  @Bean
  public DataSource dataSource(
      @Value("${spring.datasource.url}") String databaseUrl,
      @Value("${spring.datasource.username}") String username,
      @Value("${spring.datasource.password}") String password) {
    return DataSourceBuilder
        .create()
        .url(databaseUrl)
        .username(username)
        .password(password)
        .build();
  }
}
