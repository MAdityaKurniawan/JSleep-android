package com.adityaJSleepFN.request;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.adityaJSleepFN.model.*;

import java.util.ArrayList;
import java.util.Date;

public interface BaseApiService {
    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id,
                              @Query("email")String email,
                              @Query("password") String password);

    @GET("renter/{id}")
    Call<Renter> getRenter (@Path("id") int id,
                            @Query("phoneNumber") String phoneNumber,
                                    @Query("address") String address,
                                    @Query("username")String username);

    @GET("room/{id}")
    Call<Room> getRoom (@Path("id") int id,
                        @Query("price") Price price,
                        @Query("address")       String address,
                        @Query("accountId")       int accountId,
                        @Query("size")       int size,
                        @Query("facility")     Facility facility,
                        @Query("bedType")     BedType bedType,
                        @Query("city")     City city,
                        @Query("name")     String name,
                        @Query("booked")   ArrayList<Date>booked);

}
