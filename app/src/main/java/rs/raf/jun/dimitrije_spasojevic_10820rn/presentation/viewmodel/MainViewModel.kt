package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.*
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.repositories.NewsRepo
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.repositories.PortfolioRepo
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.contract.MainContract
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.states.*
import rs.raf.rafstudenthelper.data.models.Resource
import timber.log.Timber

class MainViewModel(
    private val newsRepository: NewsRepo,
    private val portfolioRepo: PortfolioRepo,
) : ViewModel(),MainContract.ViewModel {


    override val usersState: MutableLiveData<UsersState> = MutableLiveData()
    override val portfolioItemState: MutableLiveData<PortfolioState> = MutableLiveData()
    override val portfolioHistoryState: MutableLiveData<PortfolioHistoryState> = MutableLiveData()
    override val portfolioUsersItemState: MutableLiveData<PortfolioUsersItemState> = MutableLiveData()
    override val portfolioUpdateItemState: MutableLiveData<PortfolioStateUpdate> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    init {

    }

    override fun fetchAllNews() {
        val subscription = newsRepository
            .fetchAllNews()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> Timber.e("Loading")
                        is Resource.Success -> Timber.e("Success")
                        is Resource.Error -> Timber.e("Error")
                    }
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun addUser(user: User) {
        val subscription = portfolioRepo
            .insertUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("uspeh odat user")
//                    addDone.value = AddNoteState.Success
                },
                {
//                    addDone.value = AddNoteState.Error("Error happened while adding movie")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun updateUser(updateUser: UserUpdateDto) {
        val subscription = portfolioRepo
            .updateUser(updateUser)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("uspeh update USer user")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getUserByUserName(userName: String) {
        val subscription = portfolioRepo
            .getUserByUserName(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    var user = UserFull(it.id, it.username,it.email,it.accountValue,it.portfolioValue)
                    usersState.value = UsersState.Success(user)
                },
                {
                    usersState.value = UsersState.Error("Error sa userom")
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByUserIdAndSymbol(userId: Long, symbol: String) {
        val subscription = portfolioRepo
            .getAllByUserIdAndSymbol(userId, symbol)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    var list = it.map {
                        PortfolioItem(it.sym,it.userId)
                    }
                    portfolioItemState.value = PortfolioState.Success(list)
                },
                {
                    portfolioItemState.value = PortfolioState.Error("Error sa portfoliom")
                }
            )
        subscriptions.add(subscription)
    }


    override fun deleteByUserIdAndSym(userId: Long, symbol: String) {
        val subscription = portfolioRepo
            .deleteByUserIdAndSym(userId, symbol)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    portfolioUpdateItemState.value = PortfolioStateUpdate.Success("delOne")
                },
                {
                    portfolioUpdateItemState.value = PortfolioStateUpdate.Error("Error")
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByUserId(userId: Long) {
        val subscription = portfolioRepo
            .getAllByUserId(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    var portfolioItems = it.map {
                        PortfolioItem(it.sym,it.userId)
                    }
                    portfolioUsersItemState.value = PortfolioUsersItemState.Success(portfolioItems)
                },
                {
                    portfolioUsersItemState.value = PortfolioUsersItemState.Error("Error sa portfoliom")
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllPortfolioHistoryByUserId(userId: Long) {
        val subscription = portfolioRepo
            .getAllPortfolioHistoryByUserId(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    portfolioHistoryState.value = PortfolioHistoryState.Success(it)
                },
                {
                    portfolioHistoryState.value = PortfolioHistoryState.Error("Error")
                }
            )
        subscriptions.add(subscription)
    }

    override fun insertPortfolioHistoryEntity(item: PortfolioHistoryItem) {
        val subscription = portfolioRepo
            .insertPortfolioHistoryEntity(PortfolioHistoryEntity(value = item.value, userId = item.userId, date = item.date))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Dodatooo")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteAllByUserIdAndSym(userId: Long, sym: String) {
        val subscription = portfolioRepo
            .deleteAllByUserIdAndSym(userId, sym)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    portfolioUpdateItemState.value = PortfolioStateUpdate.Success("deleteAll")
                },
                {
                    portfolioUpdateItemState.value = PortfolioStateUpdate.Error("err pri brisanju svih")
                }
            )
        subscriptions.add(subscription)
    }


    override fun insertPortfolioItem(portfolioItem: PortfolioItem) {
        val subscription = portfolioRepo
            .insertPortfolioItem(PortfolioEntity(sym = portfolioItem.sym, userId = portfolioItem.userId))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    portfolioUpdateItemState.value = PortfolioStateUpdate.Success("Insert")
                },
                {
                    portfolioUpdateItemState.value = PortfolioStateUpdate.Error("Error sa portfoliom")
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}