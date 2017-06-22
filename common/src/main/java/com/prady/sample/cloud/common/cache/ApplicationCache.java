/**
 *
 */

package com.prady.sample.cloud.common.cache;

import java.util.Map;
import java.util.Optional;

import com.prady.sample.cloud.common.dto.user.PermissionDTO;

/**
 * @author Prady
 */
public final class ApplicationCache {

    private static ApplicationCache applicationCache = null;

    private ApplicationCache() {

    }

    public static ApplicationCache getInstance() {
        if (null == applicationCache) {
            applicationCache = new ApplicationCache();
        }
        return applicationCache;
    }

    private Map<String, PermissionDTO> permissionMap;

    public void updatePermission(Map<String, PermissionDTO> permissionMap) {
        this.permissionMap = permissionMap;
    }

    public PermissionDTO getPermissionForName(String permissionName) {
        if (null != permissionMap) {
            Optional<PermissionDTO> findFirst = permissionMap.values().stream()
                    .filter(permission -> permissionName.equals(permission.getPermissionName())).findFirst();
            return findFirst.get();
        }
        return null;
    }

}
