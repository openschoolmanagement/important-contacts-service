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

import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.events.Event;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.model.EventProcessingResult;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.service.EventProcessingResultService;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.service.EventSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
class EventSinkImpl implements ApplicationListener<EventSourcingSpringEvent>, EventSink {
  private EventProcessingResultService eventProcessingResultService;

  @Autowired
  EventSinkImpl(EventProcessingResultService eventProcessingResultService) {
    this.eventProcessingResultService = eventProcessingResultService;
  }

  @Override
  public void accept(Event event) {
    // TODO save event and process aggregate for read layer
    eventProcessingResultService.addEventProcessingResult(
        event.getEventId(),
        EventProcessingResult
            .builder()
            .eventId(event.getEventId())
            .successful(true)
            .message("Test").build());
  }

  @Override
  public void onApplicationEvent(EventSourcingSpringEvent eventSourcingSpringEvent) {
    accept(eventSourcingSpringEvent.getEvent());
  }
}
