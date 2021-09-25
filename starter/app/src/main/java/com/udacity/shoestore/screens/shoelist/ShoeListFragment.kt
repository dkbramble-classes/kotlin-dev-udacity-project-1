package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import android.view.*
import android.view.Gravity.CENTER
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.TEXT_ALIGNMENT_TEXT_START
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.ShoeListFragmentBinding


class ShoeListFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    private lateinit var fragment: ShoeListFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: ShoeListFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_list_fragment,
            container,
            false
        )

        setHasOptionsMenu(true)

        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        fragment = this

        viewModel.shoeList.observe(viewLifecycleOwner, { shoeList ->
            for (shoe in shoeList) {
                val myLayout: LinearLayout = requireView().findViewById(R.id.shoeListContainer)

                val newLayout = LinearLayout(fragment.context)
                newLayout.orientation = HORIZONTAL

                val name = TextView(fragment.context)
                name.text = getString(R.string.shoe_name_list, shoe.name)
                name.textAlignment = TEXT_ALIGNMENT_TEXT_START

                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.weight = 1F
                layoutParams.setMargins(0, 50, 0, 50)
                layoutParams.width = 200
                layoutParams.gravity = CENTER

                name.layoutParams = layoutParams
                newLayout.addView(name)

                val company = TextView(fragment.context)
                company.text = getString(R.string.shoe_company_list, shoe.company)
                company.textAlignment = TEXT_ALIGNMENT_TEXT_START

                company.layoutParams = layoutParams
                newLayout.addView(company)

                val size = TextView(fragment.context)
                size.text = getString(R.string.shoe_size_list, shoe.size.toString())
                size.textAlignment = TEXT_ALIGNMENT_TEXT_START
                size.layoutParams = layoutParams
                newLayout.addView(size)

                val description = TextView(fragment.context)
                description.text = getString(R.string.shoe_description_list, shoe.description)
                description.textAlignment = TEXT_ALIGNMENT_TEXT_START
                layoutParams.width = 300
                description.layoutParams = layoutParams
                newLayout.addView(description)

                myLayout.addView(newLayout)
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu_item, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        return false
    }
}