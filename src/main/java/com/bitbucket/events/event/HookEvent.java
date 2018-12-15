package com.bitbucket.events.event;

import com.atlassian.bitbucket.event.hook.RepositoryHookDisabledEvent;
import com.atlassian.bitbucket.event.hook.RepositoryHookEnabledEvent;
import com.atlassian.bitbucket.event.hook.RepositoryHookEvent;
import com.atlassian.bitbucket.event.hook.RepositoryHookSettingsChangedEvent;
import com.atlassian.bitbucket.hook.repository.RepositoryHookService;
import com.atlassian.event.api.EventListener;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import javax.inject.Inject;
import javax.inject.Named;

@Named("HookEvent")
public class HookEvent {
    private static Logger log = LoggerFactory.getLogger(HookEvent.class);

    @ComponentImport
    RepositoryHookService hookService;


    @Inject
    private HookEvent(RepositoryHookService hookService) {
        log.error("Registered Hook Event");
        System.out.println("Something constructor");
        this.hookService = hookService;
    }

    @EventListener
    public void onHookModified(RepositoryHookSettingsChangedEvent event) {
        System.out.println("Something");
    }

    @EventListener
    public void onHookModified(RepositoryHookEvent event) {
        log .error("\nEvent Scope = " + event.getScope());
        log .error("\nEvent Source = " + event.getSource());
        log.error("\nEvent Date = " + event.getDate().toString());
        log .error("\nEvent User = " + event.getUser().toString());
        System.out.println("Something hook event");
    }

    @EventListener
    public void onHookDisabled(RepositoryHookDisabledEvent event) {
        log .error("\nEvent Scope = " + event.getScope());
        log .error("\nEvent Source = " + event.getSource());
        log.error("\nEvent Date = " + event.getDate().toString());
        log .error("\nEvent User = " + event.getUser().toString());
        System.out.println("Something disabled event");
        hookService.enable(event.getRepository(),event.getRepositoryHookKey());
    }

    @EventListener
    public void onRepositoryHookEnabledEvent(RepositoryHookEnabledEvent enabledEvent) {
        log.info("Repo enabled");
        log.error("Error stuff");
        log.debug("Debug stuff");
        log.debug("Event to string: " + enabledEvent.toString());
        log.warn("Warning from enabling\n\n");
    }

}
