package dc.cashbalancer.view.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dc.cashbalancer.R
import dc.cashbalancer.dao.OperationEntity
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

class HistoryListAdapter() : RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {
    private var historyOperations = ArrayList<HistoryItem>()

    @SuppressLint("SimpleDateFormat")
    var df: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")


    fun updateAdapter(operations: List<OperationEntity>) {
        val operationsByDay = HashMap<String, ArrayList<OperationEntity>>()
        operations.forEach {
            val day = df.format(it.date)
            if (operationsByDay.containsKey(day)) {
                operationsByDay[day]!!.add(it)
            } else {
                operationsByDay[day] = ArrayList()
                operationsByDay[day]!!.add(it)
            }
        }

        historyOperations.clear()
        operationsByDay.forEach {
            historyOperations.add(
                HistoryItem(
                    it.key,
                    it.value.filter { it.type == OperationType.WITHDRAWAL }.sumOf { it.sum },
                    it.value
                )
            )
        }

        historyOperations.sortByDescending { it.operationDate }


        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return historyOperations.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyOperations[position]
        holder.bind(item.operationDate, item.operationSum, item.operations)
    }

    inner class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val operationDay = view.findViewById<TextView>(R.id.operation_day)
        private val operationSummary = view.findViewById<TextView>(R.id.operation_summary)
        private val operationsList = view.findViewById<RecyclerView>(R.id.operations_list)

        @SuppressLint("SetTextI18n")
        fun bind(day: String, operationsSum: Double, operations: List<OperationEntity>) {
            val current = df.format(Date())
            val yesterday = df.format(Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000))

            if (current.equals(day)) {
                operationDay.text = "Сегодня"
            } else if (yesterday.equals(day)) {
                operationDay.text = "Вчера"
            } else {
                operationDay.text = day
            }

            operationSummary.text = "-$operationsSum руб"
            operationsList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = OperationsAdapter(operations)
            }
        }
    }

    inner class OperationsAdapter(private val operations: List<OperationEntity>) :
        RecyclerView.Adapter<OperationsAdapter.OperationViewHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): OperationsAdapter.OperationViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_operations, parent, false)
            return OperationViewHolder(view)
        }

        override fun onBindViewHolder(
            holder: OperationsAdapter.OperationViewHolder,
            position: Int
        ) {
            val operation = operations[position]
            holder.bind(operation)

        }

        override fun getItemCount(): Int = operations.size

        inner class OperationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val operationInfo = view.findViewById<TextView>(R.id.operation_info)
            private val operationSum = view.findViewById<TextView>(R.id.operation_sum)

            @SuppressLint("SetTextI18n")
            fun bind(operation: OperationEntity) {
                operationInfo.text = "${operation.cardID} -> ${operation.category}"
                operationSum.text = "-${operation.sum} руб"
            }

        }

    }
}