package com.example.educa.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.educa.database.repository.AcademicEducationRepository
import com.example.educa.database.repository.ExperienceRepository
import com.example.educa.database.repository.InterestRepository
import com.example.educa.database.repository.SkillRepository
import com.example.educa.database.repository.UserRepository
import com.example.educa.model.AcademicEducation
import com.example.educa.model.Experience
import com.example.educa.model.Interest
import com.example.educa.model.Skill
import com.example.educa.model.User
import com.example.educa.ui.theme.BackgroundColor
import com.example.educa.ui.theme.Primary
import com.example.educa.ui.theme.Secondary
import com.example.educa.ui.theme.TextColor


@Composable

fun PopulateDatabase(context: Context) {
    val interestRepository = InterestRepository(context)
    val academicEducationRepository = AcademicEducationRepository(context)
    val skillRepository = SkillRepository(context)
    val experienceRepository = ExperienceRepository(context)
    val userRepository = UserRepository(context)

    val verificationInterest = interestRepository.listInterests()
    val verificationAcademicEducation = academicEducationRepository.listAcademicEducation()
    val verificationSkill = skillRepository.listSkills()
    val verificationExperience = experienceRepository.listExperience()
    val verificationUser = userRepository.listUsers()

    if (verificationInterest.isEmpty()) {
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

        interestRepository.insertAll(interests)
    }

    if (verificationAcademicEducation.isEmpty()) {
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

        academicEducationRepository.insertAll(listAcademicEducation)
    }

    if (verificationSkill.isEmpty()) {
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

        skillRepository.insertAll(listSkills)
    }

    if (verificationExperience.isEmpty()) {
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

        experienceRepository.insertAll(listExperience)
    }

    if (verificationUser.isEmpty()) {
        val listMockUsers = listOf(
            User(
                name = "Ana Maria",
                email = "ana.maria@gmail.com",
                password = "senha123",
                dtNasc = "Wed Mar 10 10:00:00 GMT 1985",
                distance = 10,
                gender = "F",
                accountType = 1,
                interest = listOf(1, 4, 5),
                academicEducation = listOf(3, 4, 7),
                skills = listOf(1, 6, 7),
                experiences = listOf(5, 6, 10),
                userPhoto = "https://img.freepik.com/fotos-gratis/retrato-de-uma-jovem-bonita-em-pe-na-parede-cinza_231208-10760.jpg",
            ),
            User(
                name = "Bruno Lima",
                email = "bruno.lima@gmail.com",
                password = "bruno321",
                dtNasc = "Mon Jul 15 12:00:00 GMT 1992",
                distance = 15,
                gender = "H",
                accountType = 0,
                interest = listOf(2, 3, 6),
                academicEducation = listOf(1, 2, 5),
                skills = listOf(3, 8, 10),
                experiences = listOf(1, 4, 7),
                userPhoto = "https://img.freepik.com/fotos-gratis/retrato-de-estudante-do-sexo-masculino-encantado-hipster-com-cabelos-nitidos_176532-8157.jpg",
            ),
            User(
                name = "Carla Souza",
                email = "carla.souza@yahoo.com",
                password = "carla456",
                dtNasc = "Fri Nov 25 14:00:00 GMT 1988",
                distance = 20,
                gender = "F",
                accountType = 1,
                interest = listOf(1, 5, 7),
                academicEducation = listOf(2, 3, 6),
                skills = listOf(4, 5, 9),
                experiences = listOf(3, 8, 12),
                userPhoto = "https://img.freepik.com/fotos-gratis/mulher-de-tiro-medio-com-papelada_23-2150379181.jpg",
            ),
            User(
                name = "Diego Martins",
                email = "diego.martins@hotmail.com",
                password = "diego789",
                dtNasc = "Sat Feb 20 08:00:00 GMT 1990",
                distance = 25,
                gender = "H",
                accountType = 0,
                interest = listOf(2, 4, 8),
                academicEducation = listOf(1, 5, 7),
                skills = listOf(2, 7, 10),
                experiences = listOf(6, 9, 11),
                userPhoto = "https://img.freepik.com/fotos-gratis/cara-positivo-em-jaqueta-jeans-piscando-na-parede-rosa_197531-23607.jpg",
            ),
            User(
                name = "Elena Santos",
                email = "elena.santos@gmail.com",
                password = "elena101",
                dtNasc = "Thu Dec 15 16:00:00 GMT 1995",
                distance = 30,
                gender = "F",
                accountType = 1,
                interest = listOf(3, 6, 9),
                academicEducation = listOf(4, 6, 8),
                skills = listOf(1, 4, 10),
                experiences = listOf(5, 7, 13),
                userPhoto = "https://img.freepik.com/fotos-gratis/estudante-de-sorriso-novo-ou-estagiario-nos-monoculos-que-estao-com-uma-pasta-na-parede-vermelha_158595-4958.jpg",
            ),
            User(
                name = "Fabio Oliveira",
                email = "fabio.oliveira@outlook.com",
                password = "fabio123",
                dtNasc = "Tue Aug 02 09:00:00 GMT 1993",
                distance = 35,
                gender = "H",
                accountType = 0,
                interest = listOf(4, 5, 10),
                academicEducation = listOf(3, 5, 7),
                skills = listOf(2, 5, 9),
                experiences = listOf(1, 8, 12),
                userPhoto = "https://img.freepik.com/fotos-gratis/adolescente-atraente-e-alegre-posando-contra-a-parede-branca_176420-32502.jpg",
            ),
            User(
                name = "Gabriela Silva",
                email = "gabriela.silva@gmail.com",
                password = "gabriela456",
                dtNasc = "Sun May 18 07:00:00 GMT 1991",
                distance = 40,
                gender = "F",
                accountType = 1,
                interest = listOf(1, 3, 7),
                academicEducation = listOf(2, 4, 6),
                skills = listOf(3, 6, 8),
                experiences = listOf(2, 9, 10),
                userPhoto = "https://img.freepik.com/fotos-gratis/professora-jovem-sentada-na-mesa-da-escola-na-frente-do-quadro-negro-na-sala-de-aula-verificando-a-licao-de-casa-dos-alunos-olhando-para-a-camera-feliz-e-satisfeito-sorrindo_141793-139226.jpg",
            ),
            User(
                name = "Henrique Costa",
                email = "henrique.costa@live.com",
                password = "henrique789",
                dtNasc = "Thu Jan 12 15:00:00 GMT 2003",
                distance = 45,
                gender = "H",
                accountType = 0,
                interest = listOf(2, 6, 9),
                academicEducation = listOf(1, 3, 7),
                skills = listOf(4, 7, 10),
                experiences = listOf(1, 5, 11),
                userPhoto = "https://img.freepik.com/fotos-gratis/garoto-alegre-em-oculos-elegantes-em-pe-contra-o-fundo-branco_259150-59784.jpg",
            ),
            User(
                name = "João Almeida",
                email = "joao.almeida@gmail.com",
                password = "joao321",
                dtNasc = "Mon Apr 17 11:00:00 GMT 1999",
                distance = 55,
                gender = "H",
                accountType = 0,
                interest = listOf(2, 5, 7),
                academicEducation = listOf(1, 4, 7),
                skills = listOf(3, 5, 8),
                experiences = listOf(1, 6, 10),
                userPhoto = "https://img.freepik.com/fotos-gratis/jovem-hispanico-sorrindo-usando-fones-de-ouvido-no-campus-da-universidade_839833-11696.jpg",
            ),
            User(
                name = "Lucas Pereira",
                email = "lucas.pereira@gmail.com",
                password = "lucas456",
                dtNasc = "Sun Sep 30 17:00:00 GMT 1997",
                distance = 65,
                gender = "H",
                accountType = 0,
                interest = listOf(4, 5, 8),
                academicEducation = listOf(1, 6, 7),
                skills = listOf(2, 5, 10),
                experiences = listOf(1, 7, 11),
                userPhoto = "https://img.freepik.com/fotos-gratis/jovem-hispanico-sorrindo-feliz-sentado-no-sofa-em-casa_839833-15776.jpg",
            ),
            User(
                name = "Mariana Rocha",
                email = "mariana.rocha@gmail.com",
                password = "mariana789",
                dtNasc = "Fri Jul 14 20:00:00 GMT 1998",
                distance = 70,
                gender = "F",
                accountType = 1,
                interest = listOf(1, 3, 7),
                academicEducation = listOf(2, 4, 6),
                skills = listOf(3, 6, 8),
                experiences = listOf(2, 9, 10),
                userPhoto = "https://img.freepik.com/fotos-gratis/vista-frontal-da-sorridente-empresaria-falando-ao-telefone-enquanto-toma-um-cafe_23-2148788855.jpg",
            ),
            User(
                name = "Nicolas Fernandes",
                email = "nicolas.fernandes@outlook.com",
                password = "nicolas101",
                dtNasc = "Mon Feb 05 12:00:00 GMT 1996",
                distance = 75,
                gender = "H",
                accountType = 0,
                interest = listOf(2, 4, 6),
                academicEducation = listOf(1, 5, 7),
                skills = listOf(4, 7, 9),
                experiences = listOf(1, 6, 11),
                userPhoto = "https://img.freepik.com/fotos-gratis/adolescente-hispanico-usando-mochila-de-estudante-e-segurando-livros-sorrindo-olhando-para-o-lado-e-olhando-para-longe-pensando_839833-3181.jpg",
            ),
        )

        userRepository.insertAll(listMockUsers)
    }
}

@Composable
fun simpleToastMessage() {
    val context = LocalContext.current
    Toast.makeText(context, "This is a Toast Message !", Toast.LENGTH_SHORT).show()
}

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    PopulateDatabase(context = context)

    val userRepository = UserRepository(context = context)


    var emailField by remember {
        mutableStateOf("")
    }

    var passwordField by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp).background(BackgroundColor)
    ) {
        Row {
            Text(
                text = "Educa",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Primary
            )
            Text(text = "+", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Secondary)
        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        OutlinedTextField(
            value = emailField,
            singleLine = true,
            placeholder = { Text(text = "Digite o seu e-mail") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = {
                emailField = it
            },
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        OutlinedTextField(
            value = passwordField,
            singleLine = true,
            placeholder = { Text(text = "Digite a sua senha") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = {
                passwordField = it
            },
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisible = !passwordVisible
                }) {
                    if (passwordVisible) {
                        Icon(
                            imageVector = Icons.Outlined.VisibilityOff,
                            contentDescription = "Tornar senha visivel"
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Visibility,
                            contentDescription = "Tornar senha visivel"
                        )
                    }

                }
            }
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Button(
            onClick = {
                try {
                    val loggedUserId = userRepository.login(emailField, passwordField)

                    if (loggedUserId > 0) {
                        navController.navigate("home/${loggedUserId}")
                    } else {
                        Toast.makeText(
                            context,
                            "Login inválido. Tente novamente.",
                            Toast.LENGTH_LONG,
                        ).show()
                    }

                } catch (e: Exception) {
                    Log.e("TESTE", e.message.toString())
                }
            },
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Entrar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Divider()
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(
            text = "Ainda não possui um cadastro ?",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = TextColor
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(
            text = "Cadastrar",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Primary,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                navController.navigate("welcome")
            }
        )
    }

}
