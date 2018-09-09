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
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.model.UsedKey;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.repository.UsedKeyRepository;
import open.schoolmanagement.contacts.importantcontactsservice.eventsourcing.service.UsedKeyService;
import org.springframework.stereotype.Service;

/**
 * Implementation of the UsedKeyService.
 */
@Service
class UsedKeyServiceImpl implements UsedKeyService {
  private final UsedKeyRepository usedKeyRepository;

  private Object lockObject = new Object();

  UsedKeyServiceImpl(UsedKeyRepository usedKeyRepository) {
    this.usedKeyRepository = usedKeyRepository;
  }

  @Override
  public Optional<UUID> validateKeyIsNotUsed(UUID key) {
    synchronized (lockObject) {
      return usedKeyRepository.existsById(key) ? Optional.empty() : Optional.of(key);
    }
  }

  @Override
  public void markKeyAsUsed(UUID key) {
    synchronized (lockObject) {
      usedKeyRepository
          .save(
              UsedKey
                  .builder()
                  .key(key)
                  .build());
    }
  }

  @Override
  public void markKeyAsNotUsed(UUID key) {
    synchronized (lockObject) {
      usedKeyRepository
          .delete(
              UsedKey
                  .builder()
                  .key(key)
                  .build());
    }
  }
}
