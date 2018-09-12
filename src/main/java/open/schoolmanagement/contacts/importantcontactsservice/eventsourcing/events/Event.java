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

package open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * This is the base class for all CQRS events
 */
public abstract class Event {
  @Getter
  @JsonIgnore
  private Type eventType;

  @Getter
  @JsonIgnore
  private Timestamp eventSentAt;

  @Getter
  @JsonIgnore
  private UUID eventId;

  @Getter
  @Setter
  @JsonIgnore
  private String aggregate;

  @Getter
  @Setter
  @JsonIgnore
  private UUID aggregateId;

  /**
   * Initialize the event with a type.
   *
   * @param eventType the event type
   */
  public Event(Type eventType, String aggregate, UUID aggregateId) {
      this.eventType = eventType;
      this.eventSentAt = new Timestamp(new Date().getTime());
      this.eventId = UUID.randomUUID();
      this.aggregate = aggregate;
      this.aggregateId = aggregateId;
  }

  /**
   * Enumeration for the event type.
   */
  public enum Type {
    Create,
    Update,
    Delete;
  }
}
