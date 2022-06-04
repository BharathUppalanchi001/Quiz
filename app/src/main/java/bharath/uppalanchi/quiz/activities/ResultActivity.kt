package bharath.uppalanchi.quiz.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bharath.uppalanchi.quiz.MainActivity
import bharath.uppalanchi.quiz.dataObjects.Constants
import bharath.uppalanchi.quiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.tvName.text = intent.getStringExtra(Constants.USER_NAME)
        val totalQuestions : Int = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers : Int = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        binding.tvScore.text = "Your score is $correctAnswers out of $totalQuestions"

        binding.btnFinish.setOnClickListener{
            startActivity(Intent(applicationContext,MainActivity::class.java ))
            finish()
        }
    }
}