package com.example.homework21.network

import com.example.homework21.model.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("register")
    @FormUrlEncoded
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("full_name") fullName: String
    ): Response<Register>


    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<Login>

    @POST("profile")
    @FormUrlEncoded
    suspend fun completeProfileStatus(
        @Field("user_id") userID:String
    ):Response<ComplateProfileStatus>

    @POST("/complete-profile")
    @Multipart
    suspend fun completeProfile(@Part("user_id") userId:Int
                                ,@Part("address") address:String
                                ,@Part("card_number") cardNumber:String
                                ,@Part("card_holder_name") cardHolderName:String
                                ,@Part("expiry_date") expiry:String
                                ,@Part("security_code") security:String
                                ,@Part("floor_apartment") floorApartment:String
                                ,@Part("file") file: MultipartBody.Part
    ): Response<ProfileCompleted>
}