package id.haweje.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asFlow
import id.haweje.weatherapp.core.source.WeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@FlowPreview
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: MainViewModel

    @Mock
    private var repository = Mockito.mock(WeatherRepository::class.java)

    @Before
    fun setUp() {
        viewModel = MainViewModel(repository)
    }

    @Test
    fun getWeather() = coroutineTestRule.testDispatcher.runBlockingTest {

        val weatherDummyData = DataDummy.getWeatherLiveData()
        `when`(repository.getWeatherData()).thenReturn(weatherDummyData.asFlow())

        assertNotNull(viewModel)

    }
}