package com.dantesoft.siremono.modules.actor.actor.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActorService {
  private final ActorRepository actorRepository;

  public ActorEntity save(ActorEntity actorEntity) {
    return actorRepository.save(actorEntity);
  }
}
