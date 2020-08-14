package me.xujichang.lib.activities.base;

import androidx.lifecycle.LiveData;
import androidx.paging.LoadState;
import androidx.paging.PagingData;
import androidx.paging.PagingDataAdapter;

import me.xujichang.lib.common.status.ListStatus;

public interface IPaging3 {
    void onAppend(LoadState pLoadState);

    void onPrepend(LoadState pLoadState);

    void onRefresh(LoadState pLoadState);

    void attachListStatus(LiveData<ListStatus> pStatusLiveData);

    <T> void bindPagingData(PagingDataAdapter<T, ?> pAdapter, LiveData<PagingData<T>> pListing);
}