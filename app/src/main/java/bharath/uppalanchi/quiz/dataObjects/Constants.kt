package bharath.uppalanchi.quiz.dataObjects

import bharath.uppalanchi.quiz.R
import bharath.uppalanchi.quiz.dataClasses.Question

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String  = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"

    fun getQuestions() : ArrayList<Question>{
      val questionsList:  ArrayList<Question> = ArrayList()
        questionsList.add(Question(1, "What country does this flag belong too?", R.drawable.ic_flag_of_argentina,"Argentina","Australia","Armenia","India", 1))
        questionsList.add(Question(2, "What country does this flag belong to?", R.drawable.ic_flag_of_australia, "Angola", "Austria", "Australia", "Armenia", 3))
        questionsList.add(Question(3, "What country does this flag belong to?", R.drawable.ic_flag_of_brazil, "Belarus", "Belize", "Brunei", "Brazil", 4))
        questionsList.add(Question(4, "What country does this flag belong to?", R.drawable.ic_flag_of_belgium, "Bahamas", "Belgium", "Barbados", "Belize", 2))
        questionsList.add(Question(5, "What country does this flag belong to?", R.drawable.ic_flag_of_fiji, "Gabon", "France", "Fiji", "Finland", 3))
        questionsList.add(Question(6, "What country does this flag belong to?", R.drawable.ic_flag_of_germany, "Germany", "Georgia", "Greece", "none of these", 1))
        questionsList.add(Question(7, "What country does this flag belong to?", R.drawable.ic_flag_of_denmark, "Dominica", "Egypt", "Denmark", "Ethiopia", 3))
        questionsList.add(Question(8, "What country does this flag belong to?", R.drawable.ic_flag_of_india, "Ireland", "Iran", "Hungary", "India", 4))
        questionsList.add(Question(9, "What country does this flag belong to?", R.drawable.ic_flag_of_new_zealand, "Australia", "New Zealand", "Tuvalu", "United States of America", 2))
        questionsList.add(Question(10, "What country does this flag belong to?", R.drawable.ic_flag_of_kuwait, "Kuwait", "Jordan", "Sudan", "Palestine", 1))
        return questionsList
    }
}