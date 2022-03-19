package com.example.artbook.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.artbook.MainCoroutineRule
import com.example.artbook.Util.Resource
import com.example.artbook.getOrAwaitValueTest
import com.example.artbook.repo.FakeArtRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by aarslan on 26/02/2022.
 */
@ExperimentalCoroutinesApi
class ArtViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup(){
        //Test Doubles
        viewModel = ArtViewModel(FakeArtRepository())
    }

    @Test
    fun `insert art without year returns Error`(){
        viewModel.makeArt("Mona Lisa","Da Vinci","")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Resource.Status.ERROR)
    }

    @Test
    fun `insert art without name returns Error`(){
        viewModel.makeArt("","Da Vinci","1970")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Resource.Status.ERROR)
    }

    @Test
    fun `insert art without artist name returns Error`(){
        viewModel.makeArt("Mona Lisa","","")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Resource.Status.ERROR)
    }

}