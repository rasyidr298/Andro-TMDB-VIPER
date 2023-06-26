package id.rrdev.tmdb_viper.feature.genres

interface GenreContract {
    interface View{
        fun setupRecyclerView(adapter: GenreAdapter)
    }
    interface Interactor{
        fun fetchGenres(interactorOutPut: InteractorOutput)
    }
    interface InteractorOutput{
        fun onSuccess(genres : List<Genre>)
        fun onFailure(message : String)
    }
    interface Presenter{
        fun onActivityCreated()
        fun onDestroy()
        interface MovieClickListener {
            fun onMovieClick(genres: Genre)
        }
    }
    interface Router{
        fun goToDetailActivity(genres: Genre)
    }
}