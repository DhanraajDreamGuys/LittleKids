package co.in.dreamguys.littlekids.Network;

import co.in.dreamguys.littlekids.Model.CategoryResponse;
import co.in.dreamguys.littlekids.Model.LanguageResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by chris on 6/1/16.
 */
interface GitHubService {
    @FormUrlEncoded
    @POST("languages")
    Observable<LanguageResponse> getLangauge(@Field("last_updated_time") String last_updated);

    @FormUrlEncoded
    @POST("categories")
    Observable<CategoryResponse> getCategory(@Field("lang_id") String lang_id, @Field("last_updated_time") String last_updated);


}
