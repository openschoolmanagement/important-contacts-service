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

package open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.model.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.model.EventProcessingResult;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * This is the Redis serializer for EventProcessingResult objects.
 */
public class EventProcessingResultRedisSerializer
    implements RedisSerializer<EventProcessingResult> {

  @Override
  public byte[] serialize(EventProcessingResult eventProcessingResult) throws SerializationException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      String jsonString = mapper.writeValueAsString(eventProcessingResult);

      return jsonString.getBytes(Charset.defaultCharset());
    } catch (JsonProcessingException e) {
      throw new SerializationException(e.getMessage(), e);
    }
  }

  @Override
  public EventProcessingResult deserialize(byte[] bytes) throws SerializationException {
    if (Objects.isNull(bytes)) {
      return null;
    }

    try {
      String jsonString = new String(bytes, Charset.defaultCharset());
      ObjectMapper mapper = new ObjectMapper();
      EventProcessingResult result = mapper.readValue(jsonString, EventProcessingResult.class);

      return result;
    } catch (IOException e) {
      throw new SerializationException(e.getMessage(), e);
    }
  }
}
