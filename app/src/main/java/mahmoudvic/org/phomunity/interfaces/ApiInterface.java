package mahmoudvic.org.phomunity.interfaces;

import java.util.List;

import mahmoudvic.org.phomunity.pojo.AssiatantsPOJO;
import mahmoudvic.org.phomunity.pojo.CategoriesAndBrandsPOJO;
import mahmoudvic.org.phomunity.pojo.CommentPOJO;
import mahmoudvic.org.phomunity.pojo.CoursePOJO;
import mahmoudvic.org.phomunity.pojo.EvaluationPostPOJO;
import mahmoudvic.org.phomunity.pojo.EventPOJO;
import mahmoudvic.org.phomunity.pojo.GetHelpPOJO;
import mahmoudvic.org.phomunity.pojo.GetProfilePOJO;
import mahmoudvic.org.phomunity.pojo.GroupPOJO;
import mahmoudvic.org.phomunity.pojo.LabsPOJO;
import mahmoudvic.org.phomunity.pojo.LoginRsponse;
import mahmoudvic.org.phomunity.pojo.NotificationsPOJO;
import mahmoudvic.org.phomunity.pojo.PhotographyPOJO;
import mahmoudvic.org.phomunity.pojo.RentPojos;
import mahmoudvic.org.phomunity.pojo.ReturnedCommentPOJO;
import mahmoudvic.org.phomunity.pojo.ReturnedLikePOJO;
import mahmoudvic.org.phomunity.pojo.ReturnedPostPOJO;
import mahmoudvic.org.phomunity.pojo.SellPOJO;
import mahmoudvic.org.phomunity.pojo.ShootersPOJO;
import mahmoudvic.org.phomunity.pojo.StudiosPOJO;
import mahmoudvic.org.phomunity.pojo.TipPOJO;
import mahmoudvic.org.phomunity.pojo.VideoPOJO;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //////////////////////////// Sabri//////////////////////////////////////
    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginRsponse> login(@Field("email") String email,
                             @Field("password") String password);

    //    @FormUrlEncoded // annotation used in POST type requests
    @FormUrlEncoded
    @POST("/api/register")
    Call<String> registration(@Field("first_name") String first_name,
                              @Field("last_name") String last_name,
                              @Field("email") String email,
                              @Field("password") String password,
                              @Field("mobile") String mobile,
                              @Field("level") String level,
                              @Field("camera_brand") String camera_brand,
                              @Field("camera_model") String camera_model,
                              @Field("interested_in") String interested_in,
                              @Field("photo") String photo);


    @GET("/api/studio")
    Call<StudiosPOJO> getStudios();

    @GET("/api/labs")
    Call<LabsPOJO> getLabs();

    @FormUrlEncoded
    @POST("/api/orders/labOption")
    Call<Integer> rentLab(@Field("lab_option_id") int labOptionId,
                          @Field("client_name ") String clientName,
                          @Field("client_phone_number") String clientPhoneNumber,
                          @Field("client_email_address") String clientEmailAddress,
                          @Field("notes") String clientNotes);

    @FormUrlEncoded
    @POST("/api/orders/studioOption")
    Call<Integer> rentStudio(@Field("studio_option_id") int studio_option_id,
                             @Field("title") String title,
                             @Field("client_name ") String client_name,
                             @Field("client_phone_number") String client_phone_number,
                             @Field("client_email_address") String client_email_address,
                             @Field("notes") String notes,
                             @Field("selected") String selected);


    @GET("/api/courses")
    Call<List<CoursePOJO>> getCourses();

    @GET("/api/course/{course_id}")
    Call<List<CoursePOJO>> getCourse(@Path("course_id") int courseID);


    @FormUrlEncoded
    @POST("/api/orders/course")
    Call<Integer> orderCourse(@Field("course_id") int courseId,
                              @Field("client_name") String clientName, @Field("client_phone_number") String clientPhoneNumber,
                              @Field("client_email_address") String clientEmail, @Field("notes") String clientNotes);


    @FormUrlEncoded
    @POST("/api/feedback")
    Call<Integer> sendFeedback(@Field("user_id") int user_id,
                               @Field("body") String body);

    @FormUrlEncoded
    @POST("/api/storeMsg")
    Call<Integer> sendHelpMessage(@Field("user_id") int user_id,
                                  @Field("body") String body);


    //@FormUrlEncoded
    @GET("/api/profile/{id}")
    Call<List<GetProfilePOJO>> getProfile(@Path("id") int id);

    @FormUrlEncoded
    @POST("/api/updateProfile")
    Call<String> updateProfile(@Field("user_id") int user_id,
                               @Field("first_name") String first_name,
                               @Field("last_name") String last_name,
                               @Field("email") String email,
                               @Field("mobile") String mobile,
                               @Field("level") String level,
                               @Field("camera_brand") String camera_brand,
                               @Field("camera_model") String camera_model,
                               @Field("interested_in") String interested_in,
                               @Field("password") String password,
                               @Field("password_confirmation") String password_confirmation,
                               @Field("photo") String photo);

    @GET("/api/messages/{id}")
    Call<List<GetHelpPOJO>> getHeloMessages(@Path("id") int id);

    @GET("/api/evaluation/{category}")
    Call<PhotographyPOJO> getPhotographyOuestions(@Path("category") String category);

    @FormUrlEncoded
    @POST("/api/evaluation/{category}/review")
    Call<EvaluationPostPOJO> saveEvaluationReview(@Path("category") String category, @Field("data") String data);

    ////////////////////////////////////Seusy///////////////////////////////////////

    @FormUrlEncoded
    @POST("/api/irent/order")
    Call<Integer> rentCamera(@Field("product_id") int product_id,
                             @Field("frontend_user_id") int frontend_user_id,
                             @Field("client_name") String client_name,
                             @Field("client_phone_number") String client_phone_number,
                             @Field("client_email_address") String client_email_address,
                             @Field("notes") String notes,
                             @Field("more") String more,
                             @Field("branch") String branch,
                             @Field("duration") String duration,
                             @Field("rent_date") String rent_date);

    @GET("/api/irent")
    Call<RentPojos> displayCameraRent();

    @GET("/api/irent/categoryBrands")
    Call<CategoriesAndBrandsPOJO> getIrentCategoriesAndBrands();

    @GET("/api/irent")
    Call<RentPojos> displayCameraRentByBrand(@Query("brand") int brand);

    @GET("/api/irent")
    Call<RentPojos> displayCameraRentByCategory(@Query("category") int category_id);

    @GET("/api/isell")
    Call<SellPOJO> displayCameraSell();

    @GET("/api/isell")
    Call<SellPOJO> displayCameraSellByBrand(@Query("brand") int brand);

    @GET("/api/isell")
    Call<SellPOJO> displayCameraSellByCategory(@Query("category") int category_id);

    @FormUrlEncoded
    @POST("/api/isell/order")
    Call<Integer> sellCamera(@Field("product_id") int product_id,
                             @Field("client_name") String client_name,
                             @Field("client_phone_number") String client_phone_number,
                             @Field("client_email_address") String client_email_address,
                             @Field("notes") String notes);

    @GET("/api/isell/categoryBrands")
    Call<CategoriesAndBrandsPOJO> getCtaegoriesAndBrands();

    @FormUrlEncoded
    @POST("/api/join")
    Call<Integer> joinOurTeam(@Field("name") String name,
                              @Field("email") String email,
                              @Field("job") String job,
                              @Field("note") String note,
                              @Field("cv") String cv);

    @FormUrlEncoded
    @POST("/api/forget-password")
    Call<String> forgetPassword(@Field("email") String email);

    @GET("/api/notifications/unread/{user_id}")
    Call<Integer> getNotificationCount(@Path("user_id") int user_id);

    @GET("/api/notifications/{user_id}")
    Call<List<NotificationsPOJO>> getNotifications(@Path("user_id") int id);

    /////////////////////////////////////Mahmoud//////////////////////////////
    @FormUrlEncoded
    @POST("/api/orders/second-shooter")
    Call<Integer> orderSecondShooter(@Field("notes") String message,
                                     @Field("frontend_user_id") int frontendUserId
    );

    @FormUrlEncoded
    @POST("/api/orders/assistant")
    Call<Integer> orderAssistant(@Field("notes") String message,
                                 @Field("frontend_user_id") int frontendUserId
    );

    @POST("/api/requests/{id}/accept")
    Call<String> orderAcceptAssistant(@Path("id") int id);

    @POST("/api/requests/{id}/reject")
    Call<String> orderRejectAssistant(@Path("id") int id);

    @POST("/api/requests/{id}/cancel/{user_id}")
    Call<String> orderCancelAssistant(@Path("id") int id,@Path("user_id") int userId);

    @POST("/api/requests/{id}/finish")
    Call<String> orderFinishAssistant(@Path("id") int id);

    @POST("/api/requests/{id}/review")
    Call<String> orderReviewAssistant(@Path("id") int id);

    @FormUrlEncoded
    @POST("/api/joinSecondShooter")
    Call<String> joinSecondShooter(@Field("about") String about,
                                   @Field("area") String last_name,
                                   @Field("frontend_user_id") int frontendUserId
    );


    @FormUrlEncoded
    @POST("/api/joinAssistant")
    Call<Integer> joinAssistant(@Field("about") String about,
                                @Field("area") String area,
                                @Field("frontend_user_id") int frontendUserId
    );


    @FormUrlEncoded
    @POST("/api/orders/event")
    Call<Integer> orderEvent(@Field("event_id") int eventId,
                             @Field("client_name") String clientName, @Field("client_phone_number") String clientPhoneNumber,
                             @Field("client_email_address") String clientEmail, @Field("notes") String clientNotes);


    @GET("/api/assistants")
    Call<AssiatantsPOJO> getAssistants();

    @GET("/api/assistants")
    Call<AssiatantsPOJO> getAssistants(@Query("level") String level);

    @GET("/api/secondShooters")
    Call<ShootersPOJO> getSecondShooter();


    @GET("/api/secondShooters")
    Call<ShootersPOJO> getSecondShooter(@Query("level") String level);

    @GET("/api/events")
    Call<List<EventPOJO>> getEvents();

    @GET("/api/event/{event_id}")
    Call<EventPOJO> getEvent(@Path("event_id") int eventId);


    @GET("/api/videos")
    Call<List<VideoPOJO>> getVideos();

    @GET("/api/groups/{user_id}")
    Call<List<GroupPOJO>> getGroups(@Path("user_id") int userId);

    @GET("/api/tips")
    Call<List<TipPOJO>> getTips();

    @GET("/api/group/{group_id}")
    Call<List<GroupPOJO>> getPosts(@Path("group_id") int groupId);

    @POST("/api/groups/posts/{postId}/likes/{userId}")
    Call<ReturnedLikePOJO> storeLike(@Path("postId") int postId, @Path("userId") int userId);

    @FormUrlEncoded
    @POST("/api/groups/posts/{id}/comments")
    Call<ReturnedCommentPOJO> storeComment(@Path("id") int postId, @Field("frontend_user_id") int userId, @Field("body") String body);

    @FormUrlEncoded
    @POST("/api/groups/{id}/posts")
    Call<ReturnedPostPOJO> storePost(@Path("id") int groupId, @Field("frontend_user_id") int userId, @Field("body") String body, @Field("images[]") List<String> image);

//    @FormUrlEncoded
//    @GET("/api/profile/{id}")
//    Call<LoginRsponse> registration(@Path("id") int id);

}
