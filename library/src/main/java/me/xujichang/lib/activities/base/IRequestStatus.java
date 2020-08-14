package me.xujichang.lib.activities.base;

import androidx.lifecycle.LiveData;

import me.xujichang.lib.common.status.RequestStatus;

public interface IRequestStatus {
    void bindRequest(LiveData<RequestStatus> pLiveData);
}
