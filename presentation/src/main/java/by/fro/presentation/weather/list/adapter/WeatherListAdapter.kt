package by.fro.presentation.weather.list.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import by.fro.presentation.R
import by.fro.presentation.databinding.FragmentWeatherListItemBinding
import by.fro.presentation.weather.list.model.WeatherModel

class WeatherListAdapter(private val items: List<WeatherModel>,
                         private val callbacks: Callbacks? = null,
                         private val recyclerView: RecyclerView)
    : RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {


    interface Callbacks {
        fun onItemClick(view: View, item: WeatherModel)

        fun onDeleteClick(view: View, item: WeatherModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: FragmentWeatherListItemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.weather = items[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: FragmentWeatherListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { callbacks?.onItemClick(it, items[adapterPosition]) }
            binding.deleteClick = View.OnClickListener { callbacks?.onDeleteClick(it, items[adapterPosition]) }
        }
    }
}