package com.example.astoncourseproject.presentation.viewmodels.character

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.astoncourseproject.data.repository.character.CharacterDbRepository
import com.example.astoncourseproject.domain.usecase.character.GetCharacterListUseCase
import com.example.astoncourseproject.domain.models.Character
import com.example.astoncourseproject.domain.usecase.character.GetCharacterFilterListUseCase
import com.example.astoncourseproject.utils.NetworkConnection
import kotlinx.coroutines.*

class CharacterViewModel(
    application: Application,
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val characterDbRepository: CharacterDbRepository,
    private val getCharacterFilterListUseCase: GetCharacterFilterListUseCase
) : AndroidViewModel(application) {

    val liveData = MutableLiveData<List<Character>>()
    private val listOfCharacter = mutableListOf<Character>()

    private var job: Job? = null
    private var page = 1
    private val context = application

    private fun checkConnection(): Boolean {
        return NetworkConnection.isNetworkConnected(context)
    }

    fun update() {
        if (checkConnection()) {
            updateFromNetwork()
        } else {
            Toast.makeText(context, "No internet connection! Data loaded from local database", Toast.LENGTH_SHORT).show()
            updateFromLocal()
        }
    }

    private fun updateFromNetwork() {
        page = 1
        listOfCharacter.clear()
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                listOfCharacter += getCharacterListUseCase.execute(page)
                liveData.value = listOfCharacter
                characterDbRepository.addListOfCharacters(listOfCharacter)
                if (listOfCharacter.isEmpty()){
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
            this.cancel()
        }
    }

    fun addNewPage() {
        page++
        if (checkConnection()){
            job = CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    listOfCharacter += getCharacterListUseCase.execute(page)
                    liveData.value = listOfCharacter
                    characterDbRepository.addListOfCharacters(listOfCharacter)
                }
                this.cancel()
            }
        }else{
            job = CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    listOfCharacter += characterDbRepository.getCharacterByPage(page)
                    liveData.value = listOfCharacter
                }
                this.cancel()
            }
        }
    }

    private fun updateFromLocal() {
        page = 1
        listOfCharacter.clear()
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                listOfCharacter += characterDbRepository.getCharacterByPage(page)
                liveData.value = listOfCharacter
            }
            this.cancel()
        }
    }

    fun registerFilterChanged(filters: Map<String, String>){
        if (checkConnection()){
            filterNetwork(filters)
        }else{
            filterLocal(filters["name"].toString())
        }
    }

    private fun filterNetwork(filters: Map<String, String>){
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                val list = getCharacterFilterListUseCase.execute(filters)
                if (list.isEmpty()){
                    Toast.makeText(context, "Nothing found", Toast.LENGTH_SHORT).show()
                }else {
                    liveData.value = list
                }
            }
            this.cancel()
        }
    }

    private fun filterLocal(name: String){
        job = CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                val list = characterDbRepository.getCharacterByName(name)
                if (list.isEmpty()){
                    Toast.makeText(context, "Nothing found", Toast.LENGTH_SHORT).show()
                }else {
                    liveData.value = list
                }
            }
            this.cancel()
        }
    }
}