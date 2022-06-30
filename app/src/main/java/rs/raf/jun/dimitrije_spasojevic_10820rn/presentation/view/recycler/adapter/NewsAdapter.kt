package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.News
import rs.raf.jun.dimitrije_spasojevic_10820rn.databinding.LayoutItemNewsBinding
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.consumer.ClickConsumer
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.diff.NewsDiffCallBack
import rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.viewholder.NewsViewHolder

class NewsAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<News, NewsViewHolder>(NewsDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding = LayoutItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        holder.imgView.setOnClickListener{
            onClickListener.onClick(ClickConsumer(news = news))
        }
        holder.bind(news)
    }

    class OnClickListener(val clickListener: (click: ClickConsumer) -> Unit) {
        fun onClick(click: ClickConsumer) = clickListener(click)
    }

}