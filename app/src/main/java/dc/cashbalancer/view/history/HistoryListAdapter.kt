package dc.cashbalancer.view.history

import android.annotation.SuppressLint
import android.icu.text.DateFormat
import android.icu.text.DateFormat.getDateInstance
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dc.cashbalancer.R
import dc.cashbalancer.dao.OperationEntity
import java.time.ZoneId
import java.util.*
import kotlin.collections.ArrayList

class HistoryListAdapter() : RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {
    private lateinit var historyOperations: List<OperationEntity>
    var df: DateFormat = getDateInstance()


    fun updateAdapter(operations: List<OperationEntity>) {
        historyOperations = operations
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
        val operationDay = historyOperations[position].date
        var operationsSum: Double = 0.0
        val operationsPerDay: ArrayList<OperationEntity> = ArrayList()
        historyOperations.forEach {
            if (df.format(it.date).equals(df.format(operationDay))) {
                if (it.type == OperationType.WITHDRAWAL) {
                    operationsSum -= it.sum
                }
                operationsPerDay.add(it)
            }
        }

        holder.bind(operationDay, operationsSum, operationsPerDay)

    }

    inner class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val operationDay = view.findViewById<TextView>(R.id.operation_day)
        private val operationSummary = view.findViewById<TextView>(R.id.operation_summary)
        private val operationsList = view.findViewById<RecyclerView>(R.id.operations_list)

        @SuppressLint("SetTextI18n")
        fun bind(historyDay: Date, operationsSum: Double, operations: List<OperationEntity>) {
            operationDay.text = df.format(historyDay)
            operationSummary.text = "$operationsSum руб"
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
            var operation = operations[position]
            holder.bind(operation)

        }

        override fun getItemCount(): Int = operations.size

        inner class OperationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val operationInfo = view.findViewById<TextView>(R.id.operation_info)
            private val operationSum = view.findViewById<TextView>(R.id.operation_sum)

            @SuppressLint("SetTextI18n")
            fun bind(operation: OperationEntity) {
                operationInfo.text = "${operation.cardID} -> ${operation.category}"
                operationSum.text = "${operation.sum} руб"
            }

        }

    }
}