package com.example.annotationmvp.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiService {

    @GET
    Observable<ResponseBody> getHttp(@Url() String url);

    @GET
    Observable<ResponseBody> getHttp(@Url() String url, @QueryMap Map<String, Object> map);

    @GET
    Observable<ResponseBody> getHttpObject(@Url() String url, @QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST
    <T> Observable<ResponseBody> postHttps1(@Url String url, @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST
    <T> Observable<ResponseBody> postHttps(@Url String url, @FieldMap Map<String, Object> map);

    @POST
    <T> Observable<ResponseBody> postHttps(@Url String url, @Body RequestBody requestBody, @Header("header") String header);


    @Multipart
    @POST
    Observable<ResponseBody> uploadSingleVideo(@Url String url, @PartMap Map<String, String> params, @Part("description") RequestBody description, @Part MultipartBody.Part file);

    @POST
    <T> Observable<ResponseBody> postHttps(@Url String url);

    /**
     * 下载文件
     *
     * @param fileUrl
     * @return
     */
    @Streaming //大文件时要加不然会OOM
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);
}
