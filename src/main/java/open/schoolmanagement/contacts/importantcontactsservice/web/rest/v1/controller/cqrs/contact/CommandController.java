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

package open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.controller.cqrs.contact;

import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.service.EventSource;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.service.UsedKeyService;
import org.springframework.beans.factory.annotation.Autowired;

abstract class CommandController {
  @Autowired
  protected UsedKeyService usedKeyService;

  @Autowired
  protected EventSource eventSource;
}
