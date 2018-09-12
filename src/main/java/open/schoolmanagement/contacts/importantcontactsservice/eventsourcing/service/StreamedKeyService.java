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

/**
 * This service is providing the logic to check, save and delete used keys. All method are thread
 * save.
 */
public interface StreamedKeyService {
  /**
   * Validates whether a key is used or not.
   *
   * @param key the key to validate
   * @return true if the key is not used, otherwise false
   */
  Optional<UUID> validateKeyIsNotUsed(UUID key);

  /**
   * Mark the given key as used.
   *
   * @param key the key to be marked as used
   */
  void markKeyAsUsed(UUID key);

  /**
   * Mark the given key as not used.
   *
   * @param key the key to be marked as not used
   */
  void markKeyAsNotUsed(UUID key);
}
