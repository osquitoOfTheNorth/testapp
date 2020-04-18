package githubexplorer.oscarm.com.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class BaseAdapter<E : ViewHolderBinding> @Inject constructor(
    @LayoutRes private val layout: Int,
    private val layoutInflater: LayoutInflater
) : RecyclerView.Adapter<ViewHolder>() {

    private var list: List<E> = emptyList()
    private var map: Map<Any, ViewHolderBinding> = emptyMap()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(layoutInflater.inflate(layout, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].bind(holder.itemView)
    }

    fun setData(lst: List<E>) {
        map = lst.associateBy(ViewHolderBinding::viewType)
        notifyDistinctChanges(list, lst) { old: E, new: E -> old.id == new.id}
        list = lst
    }
}


private fun <E : ViewHolderBinding> BaseAdapter<E>.notifyDistinctChanges(
    old: List<E>,
    new: List<E>,
    sameItems: (E, E) -> Boolean
) {
    val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun getOldListSize() = old.size

        override fun getNewListSize() = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            sameItems(old[oldItemPosition], new[newItemPosition])

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            old[oldItemPosition] == new[newItemPosition]
    })

    diff.dispatchUpdatesTo(this)
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

interface ViewHolderBinding {
    val viewType: Int
    val id: Any
    fun bind(view: View)
}