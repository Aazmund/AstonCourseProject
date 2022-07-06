package com.example.astoncourseproject

import android.app.Application
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.example.astoncourseproject.data.repository.character.CharacterDbRepository
import com.example.astoncourseproject.domain.models.Character
import com.example.astoncourseproject.domain.usecase.character.GetCharacterFilterListUseCase
import com.example.astoncourseproject.domain.usecase.character.GetCharacterListUseCase
import com.example.astoncourseproject.presentation.viewmodels.character.CharacterViewModel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val getCharacterListUseCase = mock<GetCharacterListUseCase>()
    val characterDbRepository = mock<CharacterDbRepository>()
    val getCharacterFilterListUseCase = mock<GetCharacterFilterListUseCase>()
    val app = mock<Application>()

    @AfterEach
    fun afterEach() {
        Mockito.reset(getCharacterListUseCase)
        Mockito.reset(characterDbRepository)
        Mockito.reset(getCharacterFilterListUseCase)
    }

    @BeforeEach()
    fun beforeEach() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }
        })
    }


    @Test
    fun testExample() = runBlocking{

        val character = Character(
            "id",
            "name",
            "status",
            "species",
            "gender",
            mapOf("name" to "name", "url" to "url"),
            mapOf("name" to "name", "url" to "url"),
            "url",
            listOf("str", "str")
        )

        val characterViewModel = CharacterViewModel(
            app,
            getCharacterListUseCase,
            characterDbRepository,
            getCharacterFilterListUseCase
        )

        Mockito.`when`(getCharacterListUseCase.execute(1)).thenReturn(listOf(character))

        characterViewModel.liveData.value = getCharacterListUseCase.execute(1)

        val expected = listOf(character)
        val actual = characterViewModel.liveData.value

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun dbRepositoryTest() = runBlocking {
        val character = Character(
            "id",
            "name",
            "status",
            "species",
            "gender",
            mapOf("name" to "name", "url" to "url"),
            mapOf("name" to "name", "url" to "url"),
            "url",
            listOf("str", "str")
        )

        val characterViewModel = CharacterViewModel(
            app,
            getCharacterListUseCase,
            characterDbRepository,
            getCharacterFilterListUseCase
        )

        Mockito.`when`(characterDbRepository.getCharacterByName("name")).thenReturn(listOf(character))

        characterViewModel.liveData.value = characterDbRepository.getCharacterByName("name")

        val expected = listOf(character)
        val actual = characterViewModel.liveData.value

        Assertions.assertEquals(expected, actual)
    }
}