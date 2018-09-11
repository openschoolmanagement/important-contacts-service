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

package open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.service.impl;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.model.EventProcessingResult;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.service.EventProcessingResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class EventProcessingResultServiceImpl implements EventProcessingResultService {
  private RedisTemplate<UUID, EventProcessingResult> eventProcessingResultRedisTemplate;

  @Autowired
  EventProcessingResultServiceImpl(
      RedisTemplate<UUID, EventProcessingResult> eventProcessingResultRedisTemplate) {

    this.eventProcessingResultRedisTemplate = eventProcessingResultRedisTemplate;
  }

  @Override
  public void addEventProcessingResult(UUID eventId, EventProcessingResult result) {
    ValueOperations<UUID, EventProcessingResult> ops =
        eventProcessingResultRedisTemplate.opsForValue();

    ops.set(eventId, result);
    eventProcessingResultRedisTemplate
        .expire(
            eventId,
            EventProcessingResultService.RESULT_AVAILABILITY_IN_MINUTES,
            TimeUnit.MINUTES);
  }

  @Override
  public Optional<EventProcessingResult> getEventProcessingResult(UUID eventId) {
    return Optional
        .ofNullable(
            eventProcessingResultRedisTemplate
                .opsForValue()
                .get(eventId));
  }
}
