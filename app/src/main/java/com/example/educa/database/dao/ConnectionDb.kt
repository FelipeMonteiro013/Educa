package com.example.educa.database.dao

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.educa.model.AcademicEducation
import com.example.educa.model.Experience
import com.example.educa.model.Interest
import com.example.educa.model.Like
import com.example.educa.model.Skill
import com.example.educa.model.User
import java.util.concurrent.Executors
import kotlin.random.Random

@Database(
    entities = [User::class, Interest::class, AcademicEducation::class, Skill::class, Experience::class, Like::class],
    version = 2
)
abstract class ConnectionDb : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun interestDao(): InterestDao
    abstract fun academicEducationDao(): AcademicEducationDao
    abstract fun skillDao(): SkillDao

    abstract fun experienceDao(): ExperienceDao

    abstract fun likeDao(): LikeDao


    companion object {

        private lateinit var instance: ConnectionDb
        fun getDatabase(context: Context): ConnectionDb {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(context, ConnectionDb::class.java, "educa_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(DatabaseCallback(context))
                    .build()
            }
            return instance
        }

        private class DatabaseCallback(
            private val context: Context
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)


//              Cadastra lista de interesses
                Executors.newSingleThreadExecutor().execute {
                    val interestDao = getDatabase(context).interestDao()

                    val interests = listOf(
                        Interest(title = "Técnologia"),
                        Interest(title = "Inovação"),
                        Interest(title = "Negócios"),
                        Interest(title = "Artes"),
                        Interest(title = "Cultura"),
                        Interest(title = "Ciências"),
                        Interest(title = "Pesquisa"),
                        Interest(title = "Saúde"),
                        Interest(title = "Bem-estar"),
                        Interest(title = "Educação"),
                        Interest(title = "Ensino"),
                        Interest(title = "Sustentabilidade"),
                        Interest(title = "Meio Ambiente"),
                    )
                    interestDao.insertAll(interests)
                }

//              Cadastra lista de formação academica
                Executors.newSingleThreadExecutor().execute {
                    val academicEducationDao = getDatabase(context).academicEducationDao()
                    val listAcademicEducation = listOf(
                        AcademicEducation(title = "Engenharia de Software"),
                        AcademicEducation(title = "Administração"),
                        AcademicEducation(title = "Nenhuma"),
                        AcademicEducation(title = "Medicina"),
                        AcademicEducation(title = "Psicologia"),
                        AcademicEducation(title = "Arquitetura"),
                        AcademicEducation(title = "Economia"),
                        AcademicEducation(title = "Ciência da Computação"),
                        AcademicEducation(title = "Design Gráfico"),
                        AcademicEducation(title = "Marketing Digital"),
                        AcademicEducation(title = "Direito"),
                        AcademicEducation(title = "Engenharia Civil"),
                        AcademicEducation(title = "Meio Ambiente"),
                        AcademicEducation(title = "Biologia"),
                        AcademicEducation(title = "Outros"),
                    )

                    academicEducationDao.insertAll(listAcademicEducation)
                }

//                Cadastra lista de habilidades
                Executors.newSingleThreadExecutor().execute {
                    val skillDao = getDatabase(context).skillDao()
                    val listSkills = listOf(
                        Skill(title = "Comunicação"),
                        Skill(title = "Resolução de Problemas"),
                        Skill(title = "Liderança"),
                        Skill(title = "Nenhuma"),
                        Skill(title = "Pensamento Crítico"),
                        Skill(title = "Gerenciamento de Tempo"),
                        Skill(title = "Trabalho em Equipe"),
                        Skill(title = "Adaptação a Mudanças"),
                        Skill(title = "Outros"),
                    )

                    skillDao.insertAll(listSkills)
                }

//                Cadastra lista de experiencias
                Executors.newSingleThreadExecutor().execute {
                    val experienceDao = getDatabase(context).experienceDao()
                    val listExperience = listOf(
                        Experience(title = "Trabalho Voluntário"),
                        Experience(title = "Estágios"),
                        Experience(title = "Nenhuma"),
                        Experience(title = "Projetos de Pesquisa"),
                        Experience(title = "Intercâmbio"),
                        Experience(title = "Atividades Extracurriculares"),
                        Experience(title = "Seminários"),
                        Experience(title = "Publicações Acadêmicas"),
                        Experience(title = "Graduação"),
                        Experience(title = "Pós-graduação"),
                        Experience(title = "Iniciação científica"),
                        Experience(title = "Conferências"),
                        Experience(title = "Empresas"),
                        Experience(title = "Outros"),
                    )

                    experienceDao.insertAll(listExperience)
                }

//              Cadastra usuário mockado no banco
                Executors.newSingleThreadExecutor().execute {
                    val userDao = getDatabase(context).userDao()
                    val likeDao = getDatabase(context).likeDao()

                    val listMockUsers = listOf(
                        User(
                            name = "Fulano da Silva",
                            email = "fulano@email.com",
                            password = "123",
                            dtNasc = "Tue Oct 05 22:00:00 GMT 1999",
                            distance = 5,
                            gender = "H",
                            accountType = 0,
                            interest = listOf(0, 1, 2, 3),
                            academicEducation = listOf(4, 5, 6, 7),
                            skills = listOf(8, 9, 10, 11),
                            experiences = listOf(12, 13, 14, 15),
                            userPhoto = "https://www.psicologavalinhos.com.br/_libs/imgs/final/11.jpg",
                        ),
                        User(
                            name = "Ciclano de Souza",
                            email = "ciclano2@email.com",
                            password = "456",
                            dtNasc = "Mon Mar 12 15:30:00 GMT 1998",
                            distance = 10,
                            gender = "H",
                            accountType = 1,
                            interest = listOf(0, 2, 3, 4),
                            academicEducation = listOf(5, 6, 7, 8),
                            skills = listOf(9, 10, 11, 12),
                            experiences = listOf(13, 14, 15, 16),
                            userPhoto = "https://marista.edu.br/wp-content/uploads/2022/07/EF1.png"
                        ),
                        User(
                            name = "Beltrano Pereira",
                            email = "beltrano3@email.com",
                            password = "789",
                            dtNasc = "Fri Nov 23 08:45:00 GMT 1997",
                            distance = 15,
                            gender = "H",
                            accountType = 1,
                            interest = listOf(1, 2, 4, 5),
                            academicEducation = listOf(6, 7, 8, 9),
                            skills = listOf(10, 11, 12, 13),
                            experiences = listOf(14, 15, 16, 17),
                            userPhoto = "https://cer.sebrae.com.br/wp-content/uploads/2022/12/GettyImages-1408304021.jpg"
                        ),
                        User(
                            name = "Maria da Silva",
                            email = "maria4@email.com",
                            password = "abc",
                            dtNasc = "Sun Jan 14 20:00:00 GMT 2000",
                            distance = 20,
                            gender = "F",
                            accountType = 0,
                            interest = listOf(0, 3, 5, 6),
                            academicEducation = listOf(4, 6, 8, 10),
                            skills = listOf(8, 10, 12, 14),
                            experiences = listOf(12, 14, 16, 18),
                            userPhoto = "https://escolasexponenciais.com.br/wp-content/uploads/2022/04/menina-1.png"
                        ),
                        User(
                            name = "Ana de Souza",
                            email = "ana5@email.com",
                            password = "def",
                            dtNasc = "Thu May 17 11:15:00 GMT 1996",
                            distance = 25,
                            gender = "F",
                            accountType = 1,
                            interest = listOf(1, 3, 4, 6),
                            academicEducation = listOf(5, 7, 9, 11),
                            skills = listOf(9, 11, 13, 15),
                            experiences = listOf(13, 15, 17, 19),
                            userPhoto = "https://thumbs.dreamstime.com/b/retrato-da-professora-usando-laptop-sentada-%C3%A0-mesa-na-sala-de-aula-e-298200036.jpg"
                        )
                    )

                    val listCreatedUserId = userDao.insertAll(listMockUsers)

                    listCreatedUserId.forEachIndexed { _, userId ->

                        likeDao.insert(
                            Like(
                                0,
                                null,
                                null,
                                userId = userId,
                                userLike = Random.nextBoolean()
                            )
                        )
                    }





                }


            }
        }

    }

}