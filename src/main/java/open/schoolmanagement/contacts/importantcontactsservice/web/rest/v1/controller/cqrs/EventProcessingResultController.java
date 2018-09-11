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

package open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.controller.cqrs;

import java.util.UUID;
import lombok.Getter;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.model.EventProcessingResult;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.service.EventProcessingResultService;
import open.schoolmanagement.contacts.importantcontactsservice.web.rest.exception.NotFoundExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * This REST controller provides access to the operation results. If the service returns HTTP status
 * NOT_FOUND, the event hasn't been processed yet or the result is not available anymore.
 */
@RestController
public class EventProcessingResultController {
  private EventProcessingResultService eventProcessingResultService;

  @Autowired
  EventProcessingResultController(EventProcessingResultService eventProcessingResultService) {
    this.eventProcessingResultService = eventProcessingResultService;
  }

  public ResponseEntity<EventProcessingResultEntity> getEventProcessingResult(UUID eventId) {
    return eventProcessingResultService
        .getEventProcessingResult(eventId)
        .map(EventProcessingResultEntity::new)
        .map(ResponseEntity::ok)
        .orElseThrow(NotFoundExecption::eventProcessingResultNotFound);
  }

  class EventProcessingResultEntity {
    @Getter
    private UUID operationId;

    @Getter
    private Boolean successful;

    @Getter
    private String message;

    EventProcessingResultEntity(EventProcessingResult result) {
      operationId = result.getEventId();
      successful = result.getSuccessful();
      message = result.getMessage();
    }
  }
}
