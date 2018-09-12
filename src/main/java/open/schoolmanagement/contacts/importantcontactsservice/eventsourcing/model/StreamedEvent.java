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

package open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.events.Event;

/**
 * An event that is streamed to the event store.
 */
@ToString
@EqualsAndHashCode(of = "eventId")
@Entity
@Table(name = "streamed_event")
public class StreamedEvent implements Serializable {
  @Getter
  @NonNull
  @Id
  @Column(name = "event_id", nullable = false)
  private UUID eventId;

  @Getter
  @NonNull
  @Column(name = "event_type", nullable = false)
  private String eventType;

  @Getter
  @NonNull
  @Column(name = "event_sent_at", nullable = false)
  private Timestamp eventSentAt;

  @Getter
  @NonNull
  @Column(name = "aggregate", nullable = false)
  private String aggregate;

  @Getter
  @NonNull
  @Column(name = "aggregate_id", nullable = false)
  private UUID aggregateId;

  @Getter
  @NonNull
  @Column(name = "event_data", nullable = false)
  private String eventData;

  private StreamedEvent(Event event) throws JsonProcessingException {
    eventId = event.getEventId();
    eventType = event.getEventType().toString();
    eventSentAt = event.getEventSentAt();
    aggregate = event.getAggregate();
    aggregateId = event.getAggregateId();
    eventData = convertEventToString(event);
  }

  private String convertEventToString(Event event) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(event);
  }

  /**
   * Create the StreamedEvebt from an event.
   * @param event the event
   * @return the new StreamedEvent
   */
  public static StreamedEvent from(Event event) throws JsonProcessingException {
    return new StreamedEvent(event);
  }
}
