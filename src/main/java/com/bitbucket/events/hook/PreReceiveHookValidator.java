package com.bitbucket.events.hook;

import com.atlassian.bitbucket.hook.repository.*;
import com.atlassian.bitbucket.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

public class PreReceiveHookValidator implements PreRepositoryHook<RepositoryHookRequest>
{
    private static Logger log = LoggerFactory.getLogger(PreReceiveHookValidator.class);

    @Nonnull
    @Override
    public RepositoryHookResult preUpdate(@Nonnull PreRepositoryHookContext preRepositoryHookContext,
                                          @Nonnull RepositoryHookRequest repositoryHookRequest) {
//
//        for (RefChange refChange : repositoryHookRequest.getRefChanges()) {
//            log.error("Ref Change: " + refChange.toString());
//            if (refChange.getType() == RefChangeType.DELETE) {
//                return RepositoryHookResult.rejected("Rejected Delete for no reason",
//                        "something happened");
//            } else if (refChange.getType() == RefChangeType.ADD) {
//                return RepositoryHookResult.rejected("Rejected Add for no reason",
//                        "something happened");
//            } else {
//                return RepositoryHookResult.rejected("Rejected Update for no reason",
//                        "something happened");
//            }
//        }
        return RepositoryHookResult.accepted();
    }
}
