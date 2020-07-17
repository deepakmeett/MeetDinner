package com.example.MeetDinner;
import com.example.MeetDinner.modals.ItemDetailsModel;
public interface ItemDetailsListener {
    void onItemDetailsFetchedSuccess(ItemDetailsModel dto);
    void onItemDetailsFetchedFailure(String msg);

}
