package rs.raf.jun.dimitrije_spasojevic_10820rn.data.repositories

import android.util.Log
import io.reactivex.Observable
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.datasources.remote.NewsService
import rs.raf.rafstudenthelper.data.models.Resource
import timber.log.Timber

class NewsRepoImpl (
    private val remoteDataSource: NewsService
) : NewsRepo {


    override fun fetchAllNews(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll()
            .doOnNext {
                Log.e("ZZZ",it.toString())
            }
            .map {
                Resource.Success(Unit)
            }
    }
}