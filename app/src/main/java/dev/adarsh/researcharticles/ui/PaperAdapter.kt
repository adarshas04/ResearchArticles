package dev.adarsh.researcharticles.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.adarsh.researcharticles.R
import dev.adarsh.researcharticles.model.Paper
import kotlinx.android.synthetic.main.research_items.view.*


class PaperAdapter(private val docs: List<Paper.Response.Doc>) : RecyclerView.Adapter<PaperAdapter.PaperHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaperHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.research_items, parent, false)
        return PaperHolder(view)
    }

    override fun getItemCount(): Int {
        return docs.size
    }

    override fun onBindViewHolder(holder: PaperHolder, position: Int) {
        holder.bind(docs[position])
    }


    inner class PaperHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(docs: Paper.Response.Doc) {
            itemView.title.text = docs.titleDisplay
            itemView.journal.text = docs.id
            var author = docs.authorDisplay
            var all = ""
            for (i in author) {
                all += "$i "
            }
            itemView.date.text = docs.publicationDate
            itemView.authors.text = all
            var abstract = docs.abstract
            var abs = ""
            var a = 1
            for (i in abstract) {
                abs += "$a. $i \n"
                a++
            }
            itemView.abstracts.text = abs
        }

    }
}