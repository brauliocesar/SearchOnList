package br.com.itbam.searchonlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.itbam.searchonlist.databinding.FragmentSearchListBinding

/**
 * Search List Fragment
 */
class SearchListFragment : Fragment() {
    private lateinit var adapter: NameListAdapter
    private lateinit var namesList: Array<String>
    private lateinit var viewBinding: FragmentSearchListBinding
    private var checkTypos =  CheckWordsTypos()
    private var jumbledLetters = JumbledLetters()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Load Names Data
        val stringNames = resources.getStringArray(R.array.names)
        namesList = stringNames.sortedArray()

        // Inflate the layout for this fragment
        viewBinding = FragmentSearchListBinding.inflate(inflater)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NameListAdapter(this.namesList)
        viewBinding.recyclerView.let {
            it.layoutManager = LinearLayoutManager(this.requireContext())
            it.setHasFixedSize(true)
            it.adapter = adapter
            it.adapter?.notifyDataSetChanged()
        }

        viewBinding.searchFab.setOnClickListener {
            activity?.supportFragmentManager?.let {
                val transaction = it.beginTransaction()
                val dialog = SearchDialog(
                    /* ConfirmClickListener */
                    { value: String, dialogFragment: DialogFragment ->
                        dialogFragment.dismiss()
                        checkTypos(value)
                    },
                    /* CancelClickListener */
                    {
                        adapter.dataList = namesList
                        adapter.notifyDataSetChanged()
                        it.dismiss()
                    }
                )

                dialog.show(transaction, "SearchDialog")
            }
        }
    }

    private fun checkTypos(value: String) {
        var filterValue = namesList.filter {
            checkTypos.checkTypos(value, it) xor jumbledLetters.verifyJumbled(value, it)
        }
        if (filterValue.isNotEmpty()) {
            adapter.dataList = filterValue.toTypedArray()
            adapter.notifyDataSetChanged()
        }
    }
}
