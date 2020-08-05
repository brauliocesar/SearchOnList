package br.com.itbam.searchonlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import br.com.itbam.searchonlist.databinding.SearchDialogLayoutBinding

class SearchDialog(
    var confirmClickListener: ((String, DialogFragment) -> Unit)? = null,
    var cancelClickListener: ((DialogFragment) -> Unit)? = null) : DialogFragment() {

    private lateinit var binding : SearchDialogLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchDialogLayoutBinding.inflate(inflater)

        binding.btnConfirm.setOnClickListener {
            confirmClickListener?.let {
                it(binding.edtSearchName.text.toString(), this)
            }
        }
        binding.btnCancel.setOnClickListener {
            cancelClickListener?.let { it(this) }
        }
        return binding.root
    }

    override fun onStart() {
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        super.onStart()
    }

    override fun onResume() {
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        super.onResume()
    }
}