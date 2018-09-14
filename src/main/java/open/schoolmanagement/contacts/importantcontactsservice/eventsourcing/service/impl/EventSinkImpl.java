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

import com.fasterxml.jackson.core.JsonProcessingException;
import open.schoolmanagement.contacts.importantcontactsservice.database.Transaction;
import open.schoolmanagement.contacts.importantcontactsservice.database.TransactionManager;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.events.Event;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.model.EventProcessingResult;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.model.StreamedEvent;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.repository.StreamedEventRepository;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.service.EventProcessingResultService;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.service.EventSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
class EventSinkImpl implements ApplicationListener<EventSourcingSpringEvent>, EventSink {
  private final static Logger LOG = LoggerFactory.getLogger(EventSinkImpl.class);

  private final EventProcessingResultService eventProcessingResultService;
  private final StreamedEventRepository streamedEventRepository;
  private final TransactionManager transactionManager;

  @Autowired
  EventSinkImpl(EventProcessingResultService eventProcessingResultService,
      StreamedEventRepository streamedEventRepository,
      TransactionManager transactionManager) {

    this.eventProcessingResultService = eventProcessingResultService;
    this.streamedEventRepository = streamedEventRepository;
    this.transactionManager = transactionManager;
  }

  @Override
  public void accept(Event event) {
    try {
      storeEvent(event);
      updateReadModel(event);
    } catch (JsonProcessingException ex) {
      LOG.error("Error while persisting event", ex);

      eventProcessingResultService
          .addEventProcessingResult(
              event.getEventId(),
              EventProcessingResult
                  .builder()
                  .eventId(event.getEventId())
                  .successful(false)
                  .message("Error while persisting event. This results in dataloss!")
                  .build());
    }
  }

  private void storeEvent(Event event) throws JsonProcessingException {
    Transaction ta = transactionManager.begin();

    streamedEventRepository
        .save(
            StreamedEvent
                .from(
                    event));

    transactionManager.commit(ta);
  }

  private void updateReadModel(Event event) {
    Transaction ta = transactionManager.begin();

    // TODO

    transactionManager.commit(ta);
  }

  @Override
  public void onApplicationEvent(EventSourcingSpringEvent eventSourcingSpringEvent) {
    accept(eventSourcingSpringEvent.getEvent());
  }
}
