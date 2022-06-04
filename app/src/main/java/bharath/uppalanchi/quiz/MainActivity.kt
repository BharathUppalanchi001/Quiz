package bharath.uppalanchi.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bharath.uppalanchi.quiz.activities.QuizQuestionsActivity
import bharath.uppalanchi.quiz.dataObjects.Constants
import bharath.uppalanchi.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.startQuiz.setOnClickListener{
            if (binding.userName.text?.isEmpty() == true){
                binding.userName.error = "Please enter your username"
            }
            else{
                val intent = Intent(applicationContext, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, binding.userName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}