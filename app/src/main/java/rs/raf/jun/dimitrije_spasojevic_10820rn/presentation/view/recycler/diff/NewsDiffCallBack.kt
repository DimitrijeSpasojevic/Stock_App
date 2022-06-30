package rs.raf.jun.dimitrije_spasojevic_10820rn.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jun.dimitrije_spasojevic_10820rn.data.models.News

class NewsDiffCallBack : DiffUtil.ItemCallback<News>() {


    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.title == newItem.title

    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.title == newItem.title
    }

}