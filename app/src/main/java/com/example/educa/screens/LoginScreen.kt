package com.example.educa.screens

import android.content.Context
import android.util.Log
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
import com.example.educa.ui.theme.Primary
import com.example.educa.ui.theme.Secondary
import com.example.educa.ui.theme.TextColor
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@Composable
fun CheckDb() {
    val context = LocalContext.current
    val userRepository = UserRepository(context)
    userRepository.checkDb()
}

fun populateDatabase(context: Context) {
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
                userPhoto = "https://img.freepik.com/fotos-gratis/retrato-de-uma-jovem-bonita-em-pe-na-parede-cinza_231208-10760.jpg?t=st=1716268593~exp=1716272193~hmac=95d026c1abcfc0474670cd581a9440ee7c0ac93c7b17a3766c0ec3ca0e75cff6&w=1380",
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
                userPhoto = "https://img.freepik.com/fotos-gratis/retrato-de-estudante-do-sexo-masculino-encantado-hipster-com-cabelos-nitidos_176532-8157.jpg?t=st=1716268746~exp=1716272346~hmac=33cd8bd0d02bf09860e96477f99c546d5f50e84d7deacc080756ad797e22a209&w=1380",
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
                userPhoto = "https://img.freepik.com/fotos-gratis/mulher-de-tiro-medio-com-papelada_23-2150379181.jpg?t=st=1716268852~exp=1716272452~hmac=2c86a87d444defa0abfda631adaa890eb741d37e2b92b341e95089c66aedb6e6&w=1380",
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
                userPhoto = "https://img.freepik.com/fotos-gratis/cara-positivo-em-jaqueta-jeans-piscando-na-parede-rosa_197531-23607.jpg?t=st=1716269155~exp=1716272755~hmac=237b13bf91545c9ba4a2e501f2b38520407a4faad1f4eb567539889b6f51320e&w=1380",
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
                userPhoto = "https://img.freepik.com/fotos-gratis/estudante-de-sorriso-novo-ou-estagiario-nos-monoculos-que-estao-com-uma-pasta-na-parede-vermelha_158595-4958.jpg?t=st=1716269223~exp=1716272823~hmac=68a961feff1cff11f8ffa731975c11d0c40f13717dd6f4c972a9917ee91e2ca3&w=1380",
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
                userPhoto = "https://img.freepik.com/fotos-gratis/adolescente-atraente-e-alegre-posando-contra-a-parede-branca_176420-32502.jpg?t=st=1716269373~exp=1716272973~hmac=0b081af98ea635157aadba59e8c536f01d5f38e3a0c75ac567967f1e3de38043&w=1380",
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
                userPhoto = "https://img.freepik.com/fotos-gratis/professora-jovem-sentada-na-mesa-da-escola-na-frente-do-quadro-negro-na-sala-de-aula-verificando-a-licao-de-casa-dos-alunos-olhando-para-a-camera-feliz-e-satisfeito-sorrindo_141793-139226.jpg?t=st=1716269463~exp=1716273063~hmac=be51b3c0bb5dc96e2397b650ff0e202ab00c88f17660c6347cc47f0445ea863b&w=1380",
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
                userPhoto = "https://img.freepik.com/fotos-gratis/garoto-alegre-em-oculos-elegantes-em-pe-contra-o-fundo-branco_259150-59784.jpg?t=st=1716269630~exp=1716273230~hmac=e37b1eeeefd530b56a43ec187f6370eca07cb33edab5f18c1aeacbee98e20f23&w=740",
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
                userPhoto = "https://img.freepik.com/fotos-gratis/jovem-hispanico-sorrindo-usando-fones-de-ouvido-no-campus-da-universidade_839833-11696.jpg?t=st=1716269762~exp=1716273362~hmac=c5430a6ccb27c85b071a5c32de172ef9a332617ac3d865ac70dbf49a75ad1803&w=1380",
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
                userPhoto = "https://img.freepik.com/fotos-gratis/jovem-hispanico-sorrindo-feliz-sentado-no-sofa-em-casa_839833-15776.jpg?t=st=1716269843~exp=1716273443~hmac=0b753d0d1e80d5f5df1b5f1d0845dc15dfcefacae962e8dafe7ce2c0f52ccb49&w=1380",
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
                userPhoto = "https://img.freepik.com/fotos-gratis/vista-frontal-da-sorridente-empresaria-falando-ao-telefone-enquanto-toma-um-cafe_23-2148788855.jpg?t=st=1716269934~exp=1716273534~hmac=2383b2b9389206412fe005436c261767ab3bfa23f8e28cb227c9d02d6b666053&w=740",
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
                userPhoto = "https://img.freepik.com/fotos-gratis/adolescente-hispanico-usando-mochila-de-estudante-e-segurando-livros-sorrindo-olhando-para-o-lado-e-olhando-para-longe-pensando_839833-3181.jpg?t=st=1716269992~exp=1716273592~hmac=0c6599c0968a5144d33661023928d58620e65550d794a33eec25965c7382328a&w=1380",
            ),
        )

        userRepository.insertAll(listMockUsers)
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    populateDatabase(context = context)
    CheckDb()

    val userRepository = UserRepository(context = context)


    var emailField by remember {
        mutableStateOf("bruno.lima@gmail.com")
    }

    var passwordField by remember {
        mutableStateOf("bruno321")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
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
