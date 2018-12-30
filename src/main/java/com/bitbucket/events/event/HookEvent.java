package com.bitbucket.events.event;

import com.atlassian.bitbucket.event.hook.RepositoryHookDisabledEvent;
import com.atlassian.bitbucket.event.hook.RepositoryHookEnabledEvent;
import com.atlassian.bitbucket.event.hook.RepositoryHookEvent;
import com.atlassian.bitbucket.event.hook.RepositoryHookSettingsChangedEvent;
import com.atlassian.bitbucket.hook.repository.RepositoryHookService;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventListenerRegistrar;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import javax.inject.Inject;
import javax.inject.Named;

@Named("HookEventSomeUniqueName")
public class HookEvent {
    private static Logger log = LoggerFactory.getLogger(HookEvent.class);
    @ComponentImport
    EventListenerRegistrar eventListener;
    @ComponentImport
    RepositoryHookService hookService;
    @ComponentImport
    EventPublisher eventPublisher;


    @Inject
    private HookEvent(RepositoryHookService hookService, EventListenerRegistrar eventListener,
                      EventPublisher eventPublisher) {
        log.error("Registered Hook Event");
        System.out.println("Something constructor");
        this.hookService = hookService;
        this.eventListener = eventListener;
        this.eventPublisher = eventPublisher;
        this.eventListener.register(this);
        this.eventPublisher.publish(this);
    }

    public void finalize() {
        eventListener.unregister(this);
    }

    @EventListener(scope = "Work 1!")
    public void onHookModified(RepositoryHookSettingsChangedEvent event) {
        System.out.println("Something");
    }

    @EventListener(scope = "Work 2!")
    public void onHookModified(RepositoryHookEvent event) {
        log .error("\nEvent Scope = " + event.getScope());
        log .error("\nEvent Source =  " + event.getSource());
        log.error("\nEvent Date = " + event.getDate().toString());
        log .error("\nEvent User = " + event.getUser().toString());
        System.out.println("Something hook event");
    }

    @EventListener(scope = "Work 3!")
    public void onHookDisabled(RepositoryHookDisabledEvent event) {
        log .error("\nEvent Scope = " + event.getScope());
        log .error("\nEvent Source = " + event.getSource());
        log.error("\nEvent Date = " + event.getDate().toString());
        log .error("\nEvent User = " + event.getUser().toString());
        System.out.println("Something disabled event");
        hookService.enable(event.getRepository(),event.getRepositoryHookKey());
    }

    @EventListener(scope = "Work!")
    public void onRepositoryHookEnabledEvent(RepositoryHookEnabledEvent enabledEvent) {
        log.info("Repo enabled");
        log.error("Error stuff");
        log.debug("Debug stuff");
        log.debug("Event to string: " + enabledEvent.toString());
        log.warn("Warning from enabling\n\n");
    }
}
