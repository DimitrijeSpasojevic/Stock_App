package rs.raf.jun.dimitrije_spasojevic_10820rn.data.repositories

import io.reactivex.Observable
import rs.raf.rafstudenthelper.data.models.Resource

interface NewsRepo {
    fun fetchAllNews(): Observable<Resource<Unit>>
}