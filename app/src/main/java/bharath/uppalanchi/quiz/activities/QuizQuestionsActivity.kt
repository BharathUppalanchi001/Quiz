package bharath.uppalanchi.quiz.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import bharath.uppalanchi.quiz.R
import bharath.uppalanchi.quiz.dataClasses.Question
import bharath.uppalanchi.quiz.dataObjects.Constants
import bharath.uppalanchi.quiz.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityQuizQuestionsBinding
    private var mCurrentPosition : Int = 1
    private var mQuestionList : ArrayList<Question>?= null
    private var mSelectedOptionPosition : Int = 0
    private var mUserName : String? = null
    private var mCorrectAnswers : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        binding.quizOption1.setOnClickListener(this)
        binding.quizOption2.setOnClickListener(this)
        binding.quizOption3.setOnClickListener(this)
        binding.quizOption4.setOnClickListener(this)
        binding.quizSubmit.setOnClickListener(this)
        mQuestionList = Constants.getQuestions()
        setQuestionAndOptions()
    }

    /**
    * Method to set the questions and options
    */
    @SuppressLint("SetTextI18n")
    private fun setQuestionAndOptions() {
        defaultOptionsView()
        val question: Question = mQuestionList!![mCurrentPosition - 1]
        binding.quizProgress.progress = mCurrentPosition
        binding.quizProgressCount.text = "$mCurrentPosition / ${binding.quizProgress.max}"
        binding.quizQuestion.text = question.question
        binding.quizImage.setImageResource(question.image)
        binding.quizOption1.text = question.optionOne
        binding.quizOption2.text = question.optionTwo
        binding.quizOption3.text = question.optionThree
        binding.quizOption4.text = question.optionFour

        if (mCurrentPosition == mQuestionList!!.size){
            binding.quizSubmit.text = "FINISH"
        }
        else{
            binding.quizSubmit.text = "SUBMIT"
        }
    }


    /**
     * Method to reset the options UI to default after moving to next question / selecting other option
     */
    private fun defaultOptionsView(){
         val options = ArrayList<TextView>()
        binding.quizOption1.let { options.add(0, it) }
        binding.quizOption2.let { options.add(1, it) }
        binding.quizOption3.let { options.add(2, it) }
        binding.quizOption4.let { options.add(3, it) }

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }

    }

    /**
     * Method to highlight the selected option
     */
    private fun selectedOptionView(quizAnswer : TextView, selectedOption : Int){
        defaultOptionsView() //resetting UI of options before highlighting selected option
        mSelectedOptionPosition = selectedOption
        quizAnswer.setTextColor(Color.parseColor("#363A43"))
        quizAnswer.setTypeface(quizAnswer.typeface, Typeface.BOLD)
        quizAnswer.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    /**
     * Method to handle clickListeners
     */
    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.quizOption1 -> {
                selectedOptionView(binding.quizOption1, 1)
            }
            R.id.quizOption2 -> {
                selectedOptionView(binding.quizOption2, 2)
            }
            R.id.quizOption3 -> {
                selectedOptionView(binding.quizOption3, 3)
            }
            R.id.quizOption4 -> {
                selectedOptionView(binding.quizOption4, 4)
            }
            R.id.quizSubmit -> {
                if (mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestionAndOptions()
                        }
                        else -> {
                            val intent = Intent(applicationContext, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else {
                    val question = mQuestionList?.get(mCurrentPosition-1)
                    if (question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)
                    if (mCurrentPosition == mQuestionList!!.size){
                        binding.quizSubmit.text = "FINISH"
                    }
                    else{
                        binding.quizSubmit.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0

                }
            }
        }
    }

    private fun answerView(answer:Int, drawableView : Int){
        when(answer){
            1 -> {
                binding.quizOption1.background = ContextCompat.getDrawable(this@QuizQuestionsActivity, drawableView)
            }
            2 -> {
                binding.quizOption2.background = ContextCompat.getDrawable(this@QuizQuestionsActivity, drawableView)
            }
            3 -> {
                binding.quizOption3.background = ContextCompat.getDrawable(this@QuizQuestionsActivity, drawableView)
            }
            4 -> {
                binding.quizOption4.background = ContextCompat.getDrawable(this@QuizQuestionsActivity, drawableView)
            }
        }
    }
}