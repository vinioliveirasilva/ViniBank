package com.example.workouttracker.presentation.add

class ExerciseRepository {
    fun getExercises(): List<ExerciseModel> {
        return exercises
    }

    companion object {
        val exercises = listOf(
            ExerciseModel(
                id = 1,
                name = "Squat",
                muscleGroup = listOf("Quadriceps", "Glutes", "Hamstrings"),
                equipment = "Barbell",
                description = "A compound exercise where you lower your hips from a standing position and then stand back up."
            ),
            ExerciseModel(
                id = 2,
                name = "Deadlift",
                muscleGroup = listOf("Hamstrings", "Glutes", "Lower Back"),
                equipment = "Barbell",
                description = "Involves lifting a loaded barbell off the ground to the level of the hips, then lowering it back down."
            ),
            ExerciseModel(
                id = 3,
                name = "Bench Press",
                muscleGroup = listOf("Chest", "Triceps", "Shoulders"),
                equipment = "Barbell",
                description = "Pressing a weight upwards while lying on a bench, targeting the upper body muscles."
            ),
            ExerciseModel(
                id = 4,
                name = "Pull-Up",
                muscleGroup = listOf("Back", "Biceps"),
                equipment = "Pull-Up Bar",
                description = "An upper-body strength exercise where you pull yourself up on a bar until your chin is above it."
            ),
            ExerciseModel(
                id = 5,
                name = "Plank",
                muscleGroup = listOf("Core"),
                equipment = "None",
                description = "Holding a position similar to a push-up for a duration of time to strengthen the core muscles."
            ),
            ExerciseModel(
                id = 6,
                name = "Lunges",
                muscleGroup = listOf("Quadriceps", "Glutes", "Hamstrings"),
                equipment = "None",
                description = "A single-leg bodyweight exercise that works your hips, glutes, quads, hamstrings, core, and inner thigh muscles."
            ),
            ExerciseModel(
                id = 7,
                name = "Bicep Curl",
                muscleGroup = listOf("Biceps"),
                equipment = "Dumbbells",
                description = "An exercise targeting the biceps by curling the weights in a controlled manner."
            ),
            ExerciseModel(
                id = 8,
                name = "Tricep Dip",
                muscleGroup = listOf("Triceps"),
                equipment = "Dip Bar or Bench",
                description = "A bodyweight exercise that targets the triceps by lowering and raising the body using the arms."
            ),
            ExerciseModel(
                id = 9,
                name = "Russian Twist",
                muscleGroup = listOf("Abdominals", "Obliques"),
                equipment = "None",
                description = "A core exercise that involves rotating the torso from side to side while in a seated position."
            ),
            ExerciseModel(
                id = 11,
                name = "Leg Press",
                muscleGroup = listOf("Quadriceps", "Glutes", "Hamstrings"),
                equipment = "Leg Press Machine",
                description = "A seated exercise where you push a weight away from your body using your legs."
            ),
            ExerciseModel(
                id = 12,
                name = "Leg Extension",
                muscleGroup = listOf("Quadriceps"),
                equipment = "Leg Extension Machine",
                description = "An isolation exercise targeting the quadriceps by extending the knee against resistance."
            ),
            ExerciseModel(
                id = 13,
                name = "Wall Sit",
                muscleGroup = listOf("Quadriceps"),
                equipment = "None",
                description = "A static exercise where you hold a seated position against a wall to engage the quadriceps."
            ),

            // Hamstrings Exercises
            ExerciseModel(
                id = 14,
                name = "Stiff-Legged Deadlift",
                muscleGroup = listOf("Hamstrings", "Glutes", "Lower Back"),
                equipment = "Barbell",
                description = "A variation of the deadlift focusing on the hamstrings by keeping the legs straight."
            ),
            ExerciseModel(
                id = 15,
                name = "Leg Curl",
                muscleGroup = listOf("Hamstrings"),
                equipment = "Leg Curl Machine",
                description = "An isolation exercise where you curl your legs against resistance to target the hamstrings."
            ),
            ExerciseModel(
                id = 16,
                name = "Bulgarian Split Squat",
                muscleGroup = listOf("Quadriceps", "Glutes", "Hamstrings"),
                equipment = "Dumbbells",
                description = "A single-leg squat with the rear foot elevated, emphasizing balance and leg strength."
            ),

            // Calves Exercises
            ExerciseModel(
                id = 17,
                name = "Standing Calf Raise",
                muscleGroup = listOf("Calves"),
                equipment = "Calf Raise Machine",
                description = "An exercise where you lift your heels to stand on your toes, targeting the calf muscles."
            ),
            ExerciseModel(
                id = 18,
                name = "Seated Calf Raise",
                muscleGroup = listOf("Calves"),
                equipment = "Seated Calf Raise Machine",
                description = "A seated variation focusing on the soleus muscle of the calves."
            ),

            // Chest Exercises
            ExerciseModel(
                id = 19,
                name = "Chest Fly",
                muscleGroup = listOf("Chest"),
                equipment = "Dumbbells",
                description = "An isolation exercise where you open and close your arms in a hugging motion to target the chest."
            ),
            ExerciseModel(
                id = 20,
                name = "Incline Bench Press",
                muscleGroup = listOf("Upper Chest", "Triceps", "Shoulders"),
                equipment = "Barbell",
                description = "A bench press performed on an inclined bench to emphasize the upper portion of the chest."
            ),
            ExerciseModel(
                id = 21,
                name = "Decline Bench Press",
                muscleGroup = listOf("Lower Chest", "Triceps", "Shoulders"),
                equipment = "Barbell",
                description = "A bench press performed on a declined bench to target the lower portion of the chest."
            ),

            // Back Exercises
            ExerciseModel(
                id = 22,
                name = "Bent-Over Row",
                muscleGroup = listOf("Back", "Biceps"),
                equipment = "Barbell",
                description = "A compound exercise where you pull a barbell towards your torso while bent over, targeting the back muscles."
            ),
            ExerciseModel(
                id = 23,
                name = "Lat Pulldown",
                muscleGroup = listOf("Back", "Biceps"),
                equipment = "Lat Pulldown Machine",
                description = "An exercise where you pull a bar down towards your chest to engage the latissimus dorsi muscles."
            ),
            ExerciseModel(
                id = 24,
                name = "Seated Cable Row",
                muscleGroup = listOf("Back", "Biceps"),
                equipment = "Cable Machine",
                description = "A seated exercise where you pull handles towards your torso, focusing on the mid-back muscles."
            ),

            // Shoulders Exercises
            ExerciseModel(
                id = 25,
                name = "Shoulder Press",
                muscleGroup = listOf("Shoulders", "Triceps"),
                equipment = "Dumbbells",
                description = "An overhead pressing movement targeting the deltoid muscles."
            ),
            ExerciseModel(
                id = 26,
                name = "Lateral Raise",
                muscleGroup = listOf("Shoulders"),
                equipment = "Dumbbells",
                description = "An isolation exercise where you lift weights out to the sides to target the lateral deltoids."
            ),
            ExerciseModel(
                id = 27,
                name = "Front Raise",
                muscleGroup = listOf("Shoulders"),
                equipment = "Dumbbells",
                description = "An exercise where you lift weights in front of you to engage the anterior deltoids."
            ),

            // Triceps Exercises
            ExerciseModel(
                id = 28,
                name = "Tricep Pushdown",
                muscleGroup = listOf("Triceps"),
                equipment = "Cable Machine",
                description = "An isolation exercise where you push a bar down to extend the arms and target the triceps."
            ),
            ExerciseModel(
                id = 29,
                name = "Overhead Tricep Extension",
                muscleGroup = listOf("Triceps"),
                equipment = "Dumbbell",
                description = "An exercise performed by extending the arms overhead to work the triceps."
            ),

            // Biceps Exercises
            ExerciseModel(
                id = 30,
                name = "Hammer Curl",
                muscleGroup = listOf("Biceps"),
                equipment = "Dumbbells",
                description = "A variation of the bicep curl with palms facing each other, targeting both the biceps and brachialis."
            ),
            ExerciseModel(
                id = 31,
                name = "Preacher Curl",
                muscleGroup = listOf("Biceps"),
                equipment = "Preacher Bench and Barbell",
                description = "An isolation exercise performed on a preacher bench to target the biceps."
            ),

            // Core Exercises
            ExerciseModel(
                id = 32,
                name = "Sit-Up",
                muscleGroup = listOf("Abdominals"),
                equipment = "None",
                description = "A core exercise where you lift your torso from a lying position to engage the abdominal muscles."
            ),
            ExerciseModel(
                id = 33,
                name = "Leg Raise",
                muscleGroup = listOf("Abdominals"),
                equipment = "None",
                description = "A core exercise where you lift your legs while lying down to engage the lower abs."
            ),
            ExerciseModel(
                id = 34,
                name = "Hanging Leg Raise",
                muscleGroup = listOf("Abdominals"),
                equipment = "Pull-Up Bar",
                description = "An advanced core exercise where you raise your legs while hanging from a bar."
            ),
            ExerciseModel(
                id = 35,
                name = "Cable Woodchopper",
                muscleGroup = listOf("Obliques", "Core"),
                equipment = "Cable Machine",
                description = "A rotational exercise that targets the oblique muscles using a cable machine."
            ),

            // Cardio Exercises
            ExerciseModel(
                id = 36,
                name = "Treadmill Running",
                muscleGroup = listOf("Full Body"),
                equipment = "Treadmill",
                description = "A cardiovascular exercise performed on a treadmill at various speeds and inclines."
            ),
            ExerciseModel(
                id = 37,
                name = "Rowing Machine",
                muscleGroup = listOf("Back", "Legs", "Core"),
                equipment = "Rowing Machine",
                description = "A full-body cardio workout that mimics rowing a boat."
            ),
            ExerciseModel(
                id = 38,
                name = "Jump Rope",
                muscleGroup = listOf("Cardio", "Legs"),
                equipment = "Jump Rope",
                description = "A high-intensity cardiovascular exercise that improves coordination and endurance."
            )
        )
    }
}