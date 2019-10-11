package com.khalej.helpmeAPP.model;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface apiinterface_home {

    @FormUrlEncoded
    @POST("montag/ihelpu/HelpMe_login.php")
    Call<List<contact_userinfo>> getcontacts_login(@Field("phonee") String phone , @Field("passw") String password);
    @FormUrlEncoded
    @POST("montag/ihelpu/HelpMe_Registraion.php")
    Call<ResponseBody> getcontacts_newaccount(@Field("name") String name, @Field("password") String password, @Field("address")String address,
                                              @Field("phone") String phone, @Field("country") String country, @Field("date") String date);
    @GET("montag/ihelpu/HelpMe_annonce.php")
    Call<List<contact_annonce>> getcontacts_annonce();
    @GET("montag/ihelpu/HelpMe_first_category.php")
    Call<List<contact_home>> getcontacts_first();
    @FormUrlEncoded
    @POST("montag/ihelpu/HelpMe_second_category.php")
    Call<List<contact_second_home>> getcontacts(@Field("id") int id);

    @FormUrlEncoded
    @POST("montag/ihelpu/HelpMe_second_category_Search.php")
    Call<List<contact_second_home>>  getcontacts_Search(@Field("name") String text);
    @FormUrlEncoded
    @POST("montag/ihelpu/HelpMe_add_order.php")
    Call<ResponseBody> getcontacts_order(@Field("name") String name, @Field("address") String address,
                                         @Field("phone") String phone,
                                         @Field("details") String details,
                                         @Field("date")String date,
                                         @Field("price")double price,
                                         @Field("charge")String charge
    );
    @FormUrlEncoded
    @POST("montag/ihelpu/HelpMe_Myorders.php")
    Call<List<contact_order>> get_all_neworders(@Field("name") String name,@Field("address") String address,@Field("phone") String phone);

    @FormUrlEncoded
    @POST("montag/ihelpu/HelpMe_second_category_Images.php")
    Call<List<contact_annonce>> getcontacts_Image(@Field("id") int id);
}

