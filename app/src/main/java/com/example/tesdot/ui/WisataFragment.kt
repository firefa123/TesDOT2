import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tesdot.R
import com.example.tesdot.model.dataItem
import com.example.tesdot.ui.WisataViewModel

class WisataFragment : Fragment() {

    companion object {
        fun newInstance() = WisataFragment()
    }

    private lateinit var viewModel: WisataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.wisata_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WisataViewModel::class.java)

        viewModel.getStatus().observe(this, Observer {
                t ->
            if(t ?: true){
                list.visibility = View.GONE
                textStatus.visibility = View.VISIBLE
            }else
            {
                list.visibility = View.VISIBLE
                textStatus.visibility = View.GONE
            }

        })

        viewModel.setData().observe(this, Observer {
                t ->
            t?.data?.let { showData(it) }
        })
    }

    private fun showData(data: List<dataItem?>) {
        list.adapter = WisataAdapter(data)

    }

}