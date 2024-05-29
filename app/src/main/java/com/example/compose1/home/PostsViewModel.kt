import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose1.home.api.Post
import com.example.compose1.home.api.RetrofitInstance
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {
    private val _posts: MutableState<List<Post>> = mutableStateOf(emptyList())
    val posts: State<List<Post>> = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                val postsList = RetrofitInstance.api.getPosts()
                _posts.value = postsList
            } catch (e: Exception) {
                e
                // Handle the error
            }
        }
    }
}
