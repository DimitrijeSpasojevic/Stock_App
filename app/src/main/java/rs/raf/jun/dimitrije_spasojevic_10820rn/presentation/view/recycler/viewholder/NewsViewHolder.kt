package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.News
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.LayoutItemNewsBinding
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class NewsViewHolder(private val itemBinding: LayoutItemNewsBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    var title = itemBinding.title
    var date = itemBinding.newsDate
    var imgView = itemBinding.imgView

    fun bind(news: News) {
        with(itemBinding){
            title.text = news.title
            imgView.load(news.image)
            newsDate.text = news.date.subSequence(0,10)
        }
    }

}