package com.bitbucket.events.hook;

import com.atlassian.bitbucket.hook.repository.*;
import com.atlassian.bitbucket.repository.*;

import javax.annotation.Nonnull;

public class PreReceiveHookValidator implements PreRepositoryHook
{
    @Nonnull
    @Override
    public RepositoryHookResult preUpdate(@Nonnull PreRepositoryHookContext preRepositoryHookContext, @Nonnull RepositoryHookRequest repositoryHookRequest) {

        for (RefChange refChange : repositoryHookRequest.getRefChanges())
        {
            if (refChange.getType() == RefChangeType.DELETE)
            {
                repositoryHookRequest.getScmHookDetails().get().err().println("The ref '" + refChange.getRef().getId() + "' cannot be deleted.");
                return RepositoryHookResult.rejected("Rejected Delete for no reason","something happened");
            } else if (refChange.getType() == RefChangeType.ADD) {
                return RepositoryHookResult.rejected("Rejected Add for no reason","something happened");
            } else {
                return RepositoryHookResult.rejected("Rejected Update for no reason","something happened");
            }
        }
        return RepositoryHookResult.accepted();
    }
}
