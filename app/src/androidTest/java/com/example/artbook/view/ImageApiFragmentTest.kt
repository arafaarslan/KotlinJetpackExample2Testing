package com.example.artbook.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.artbook.R
import com.example.artbook.adapter.ImageRecyclerAdapter
import com.example.artbook.launchFragmentInHiltContainer
import com.example.artbook.repo.FakeAndroidTestArtRepository
import com.example.artbook.roomdb.getOrAwaitValue
import com.example.artbook.viewmodel.ArtViewModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

/**
 * Created by aarslan on 27/02/2022.
 */
@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ImageApiFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun selectImage(){
        val navController = Mockito.mock(NavController::class.java)

        val selectedImageUrl = "aarslan.com"

        val testViewModel = ArtViewModel(FakeAndroidTestArtRepository())

        launchFragmentInHiltContainer<ImageApiFragment>(factory = fragmentFactory){
            Navigation.setViewNavController(requireView(),navController)
            viewModel = testViewModel
            imageRecyclerAdapter.images = listOf(selectedImageUrl)
        }

        Espresso.onView(withId(R.id.imageRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageRecyclerAdapter.ImageRecyclerViewHolder>(0,
            click())
        )

        Mockito.verify(navController).popBackStack()

        assertThat(testViewModel.selectedImageUrl.getOrAwaitValue()).isEqualTo(selectedImageUrl)
    }
}