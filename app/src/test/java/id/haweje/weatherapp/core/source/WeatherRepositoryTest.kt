package id.haweje.weatherapp.core.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.haweje.weatherapp.CoroutineTestRule
import id.haweje.weatherapp.DataDummy
import id.haweje.weatherapp.core.utils.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()


    @Test
    fun getWeatherData() = coroutineTestRule.testDispatcher.runBlockingTest {
        val weatherDummyData = DataDummy.getDummyWeather()
        val weatherDummyFlow = flow { emit(Resource.success(weatherDummyData)) }

        assertNotNull(weatherDummyData)
        assertEquals(weatherDummyData, weatherDummyFlow.first().data)

    }
}