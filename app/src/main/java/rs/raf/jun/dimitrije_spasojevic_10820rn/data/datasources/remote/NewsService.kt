package rs.raf.jun.dimitrije_spasojevic_10820rn.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.rafstudenthelper.data.models.NewsResponse

interface NewsService {

    @GET("kkk")
    fun getAll(): Observable<List<NewsResponse>>
}