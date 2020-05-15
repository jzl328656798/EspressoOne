package two.example.shen.yue.espressoprojectone.test14

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import two.example.shen.yue.espressoprojectone.databinding.FragmentTest141Binding

/**
 * Author: Queen
 * Date: 2020/5/14 5:33 PM
 * Describe: Test14Fragment1
 */
class Test14Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTest141Binding.inflate(inflater, container, false)
        return binding.root
    }
}