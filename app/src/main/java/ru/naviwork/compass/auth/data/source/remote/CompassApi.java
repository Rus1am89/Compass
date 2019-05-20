package ru.naviwork.compass.auth.data.source.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.naviwork.compass.Const;
import ru.naviwork.compass.auth.data.User;

public interface CompassApi {

    @GET(Const.ENTITY_COMPASS_SOCIAL_USER)
    Call<User> getCurrentUser();

}
