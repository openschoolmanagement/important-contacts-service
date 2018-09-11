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

package open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.service;

import java.util.Optional;
import java.util.UUID;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.model.EventProcessingResult;

/**
 * This is the interface of the OperationResultService. This service holds operation results,
 * i.e. the results of the event processing, in memory for {@link #RESULT_AVAILABILITY_IN_MINUTES}.
 */
public interface EventProcessingResultService {
  /**
   * The constant RESULT_AVAILABILITY_IN_MINUTES.
   */
  public static final long RESULT_AVAILABILITY_IN_MINUTES = 15;

  /**
   * Add operation result.
   *
   * @param eventId     the event id
   * @param result      the result
   */
  void addEventProcessingResult(UUID eventId, EventProcessingResult result);

  /**
   * Gets operation result.
   *
   * @param eventId the event id
   * @return the event processing result
   */
  Optional<EventProcessingResult> getEventProcessingResult(UUID eventId);
}
