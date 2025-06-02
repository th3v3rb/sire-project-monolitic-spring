package com.dantesoft.siremono.modules.actor._rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/actor/{actorId}/contacts")
@RequiredArgsConstructor
public class ActorContactController {
}
