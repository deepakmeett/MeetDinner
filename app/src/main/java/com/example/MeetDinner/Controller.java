package com.example.MeetDinner;
import android.content.Context;

import com.example.MeetDinner.API.ApiInterface;
import com.example.MeetDinner.API.RetrofitClientInstance;
//import com.example.dinnersUser.modals.ItemDetailsListener;
import retrofit2.Call;
public class Controller {
    
    Context context;

    public Controller(Context context) {
        this.context = context;
    }

    public void getItemDetails(Context context, final ItemDetailsListener listeners, String id) {
        ApiInterface apiInterface = RetrofitClientInstance.getRetrofitInstanceDetails().create( ApiInterface.class );
        Call callTask = apiInterface.getItemDetails( id );
//        new WebAPICall( context ).doGetResponse( "Please Wait...", callTask, true, new WebAPICall.Response() {
//            @Override
//            public void onSuccessResponse(Object object) {
//                try {
//                    if (object instanceof ItemDetailsModel) {
//                        ItemDetailsModel dto = (ItemDetailsModel) object;
//                        listeners.onItemDetailsFetchedSuccess( dto );
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println( "Error === " + e.getMessage() );
//                }
//            }
//
//            @Override
//            public void onFailureResponse(String message) {
//                listeners.onItemDetailsFetchedFailure( message );
//            }
//        } );
//        new WebAPICall( context ).doGetResponse( "Please wait...", callTask, true, new WebAPICall.Response<Object>() {
//        }
//        {
//        } );
    }

}
