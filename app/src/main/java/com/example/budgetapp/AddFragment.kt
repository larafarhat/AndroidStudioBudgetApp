package com.example.budgetapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.Observer
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.budgetapp.databinding.FragmentAddBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Calendar
import java.util.Calendar.DAY_OF_MONTH

class AddFragment : Fragment() {

    private lateinit var datePicker: DatePicker
    private var year = 0
    private var month = 0
    private var day = 0
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        // application -> database -> dao
        val application = requireNotNull(this.activity).application
        val database = RecordDatabase.getInstance(application)
        val dao = database.recordDao


        val view = inflater.inflate(R.layout.fragment_add, container, false)
        requireActivity().title = "Add Record"
        val plusminus = view.findViewById<ChipGroup>(R.id.chip_plusminus)
        val addChip = view.findViewById<Chip>(R.id.chip_add)
        val subChip = view.findViewById<Chip>(R.id.chip_subtract)
        val foodChip = view.findViewById<Chip>(R.id.chip_food)
        val fuelChip = view.findViewById<Chip>(R.id.chip_fuel)
        val rentChip = view.findViewById<Chip>(R.id.chip_rent)
        val giftChip = view.findViewById<Chip>(R.id.chip_gift)
        val incomeChip = view.findViewById<Chip>(R.id.chip_Income)
        val investChip = view.findViewById<Chip>(R.id.chip_Investment)
        val doneButton = view.findViewById<Button>(R.id.done_button)
        val chipGroup = view.findViewById<ChipGroup>(R.id.chip_categorie)
        val amount = view.findViewById<EditText>(R.id.amountInput)
        val sign = view.findViewById<TextView>(R.id.sign)
        val note = view.findViewById<EditText>(R.id.record_note)
        plusminus.setSingleSelection(true)
        chipGroup.setSingleSelection(true)

        val datePicker = view.findViewById<DatePicker>(R.id.date_picker)
        val datebutton = view.findViewById<Button>(R.id.button_date_picker)
        datebutton.setOnClickListener {
            if (datePicker.isGone) {
                datePicker.visibility = View.VISIBLE
            } else {
                datePicker.visibility = View.GONE
            }
        }
        val calendar = Calendar.getInstance()
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
        datePicker.init(year, month, day, null)

        addChip.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                foodChip.visibility = View.GONE
                fuelChip.visibility = View.GONE
                rentChip.visibility = View.GONE
                giftChip.visibility = View.GONE
                incomeChip.visibility = View.VISIBLE
                investChip.visibility = View.VISIBLE

                sign.setText("+")
            }
        }
        subChip.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                foodChip.visibility = View.VISIBLE
                fuelChip.visibility = View.VISIBLE
                rentChip.visibility = View.VISIBLE
                giftChip.visibility = View.VISIBLE
                incomeChip.visibility = View.GONE
                investChip.visibility = View.GONE
                sign.setText("-")
            }
        }


        doneButton.setOnClickListener {

            year = datePicker.year
            month = datePicker.month + 1
            day = datePicker.dayOfMonth
            Log.d("DatePicker", "Selected date 1: $year-$month-$day")


            if (!addChip.isChecked && !subChip.isChecked) {
                Toast.makeText(
                    requireContext(), "Please select the type of transaction", Toast.LENGTH_SHORT
                ).show()

            } else if (amount.text.isBlank()) {
                Toast.makeText(requireContext(), "Please enter an amount ", Toast.LENGTH_SHORT)
                    .show()

            } else if (chipGroup.checkedChipId == View.NO_ID) {
                Toast.makeText(requireContext(), "Please select a category", Toast.LENGTH_SHORT)
                    .show()

            } else if (addChip.isChecked) {

                var balance = arguments?.getFloat("balance", 0.0f) ?: 0.0f
                Log.d("Tag", "Current balance: $balance")

                val amountText = amount.text.toString()
                val amount = amountText.toFloatOrNull() ?: 0.0f
                Log.d("Tag", "Amount: $amount")

                val updatedBalance = balance + amount
                Log.d("Tag", "Updated balance: $updatedBalance")

                val action = AddFragmentDirections.actionAddFragment3ToHomeFragment(updatedBalance)
                view.findNavController().navigate(action)

                val record = Record()
                record.recordAmount = amountText
                record.recordDate = "$year-$month-$day"
                record.recordType=  "+"

                this.lifecycleScope.launch {
                    dao.insert(record)
                }

                Toast.makeText(
                    requireContext(), "Balance has been successfully changed!", Toast.LENGTH_SHORT
                ).show()
            } else if (subChip.isChecked) {

                var balance = arguments?.getFloat("balance", 0.0f) ?: 0.0f
                Log.d("Tag", "Current balance: $balance")

                val amountText = amount.text.toString()
                val amount = amountText.toFloatOrNull() ?: 0.0f
                Log.d("Tag", "Amount: $amount")

                val updatedBalance = balance - amount
                Log.d("Tag", "Updated balance: $updatedBalance")



                val action = AddFragmentDirections.actionAddFragment3ToHomeFragment(updatedBalance)
                view.findNavController().navigate(action)


                val record = Record()
                record.recordAmount = amountText
                record.recordDate = "$year-$month-$day"
                record.recordType=  "-"
                record.recordNote = note.text.toString()
                this.lifecycleScope.launch {
                    dao.insert(record)
                }

                Toast.makeText(
                    requireContext(), "Balance has been successfully changed!", Toast.LENGTH_SHORT
                ).show()
            }

        }

        return view
    }

}