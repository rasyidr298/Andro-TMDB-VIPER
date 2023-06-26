package id.rrdev.tmdb_viper.feature.movie

import id.rrdev.tmdb_viper.utilities.enum.CallSelector


interface MovieContract {
    interface View{
        fun setupRecyclerView(selector: CallSelector, adapter: MovieAdapter)
    }
    interface Interactor{
        fun fetchMovies(idGenre: Int, page: Int, interactorOutPut: InteractorOutput)
    }
    interface InteractorOutput{
        fun onSuccess(result : List<Movie>, selector: CallSelector)
        fun onFailure(message : String)
    }
    interface Presenter{
        fun onActivityCreated()
        fun onDestroy()
        interface MovieClickListener {
            fun onMovieClick(result: Movie)
        }
    }
    interface Router{
        fun goToDetailActivity(results: Movie)
    }
}