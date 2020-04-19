package githubexplorer.oscarm.com.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class BaseAdapter<E : ViewHolderBinding> @Inject constructor(
    @LayoutRes private val layout: Int,
    private val layoutInflater: LayoutInflater
) : RecyclerView.Adapter<ViewHolder>() {

    private val disposables = CompositeDisposable()
    private val clicksSubject = PublishSubject.create<Any>()
    private var list: List<E> = emptyList()

    val clicks: Observable<*> = clicksSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(layoutInflater.inflate(layout, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        disposables.add(list[position].bind(holder.itemView).subscribe { clicksSubject.onNext(it) })
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        disposables.dispose()
    }

    fun setData(newList: List<E>) {
        notifyDistinctChanges(list, newList) { old: E, new: E -> old.id == new.id}
        list = newList
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
    fun bind(view: View): Observable<*>
}