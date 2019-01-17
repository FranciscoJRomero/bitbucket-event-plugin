package com.bitbucket.events.hook;

import com.atlassian.bitbucket.event.branch.BranchCreationHookRequest;
import com.atlassian.bitbucket.hook.repository.PreRepositoryHook;
import com.atlassian.bitbucket.hook.repository.PreRepositoryHookContext;
import com.atlassian.bitbucket.hook.repository.RepositoryHookResult;
import com.atlassian.bitbucket.repository.RefChange;
import com.atlassian.bitbucket.repository.RefChangeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

public class MyPreReceiveBranchCreationHook implements PreRepositoryHook<BranchCreationHookRequest> {
    private static Logger log = LoggerFactory.getLogger(MyPreReceiveBranchCreationHook.class);

    @Nonnull
    @Override
    public RepositoryHookResult preUpdate(@Nonnull PreRepositoryHookContext preRepositoryHookContext,
                                          @Nonnull BranchCreationHookRequest branchCreationHookRequest) {
        log.error("\r\nRef Change from branch hook:");
        for (RefChange refChange : branchCreationHookRequest.getRefChanges()) {
            if (refChange.getType() == RefChangeType.DELETE) {
                return RepositoryHookResult.rejected("rejected",
                        "every commit will be rejected");
            } else if (refChange.getType() == RefChangeType.ADD) {
                return RepositoryHookResult.rejected("Add from branch creation",
                        "something happened");
            } else {
                return RepositoryHookResult.rejected("Rejected from branch creation",
                        "something happened");
            }
        }
        return RepositoryHookResult.accepted();
    }
}
