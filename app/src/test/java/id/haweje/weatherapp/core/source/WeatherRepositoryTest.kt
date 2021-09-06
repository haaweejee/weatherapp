package id.haweje.weatherapp.core.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.constraintlayout.helper.widget.Flow
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.haweje.weatherapp.CoroutineTestRule
import id.haweje.weatherapp.DataDummy
import id.haweje.weatherapp.core.source.local.LocalDataSource
import id.haweje.weatherapp.core.source.local.entity.WeatherEntity
import id.haweje.weatherapp.core.source.remote.RemoteDataSource
import id.haweje.weatherapp.core.utils.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.mock
import org.mockito.stubbing.OngoingStubbing


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var repo : WeatherRepository

    @Test
    fun getWeatherData() = coroutineTestRule.testDispatcher.runBlockingTest {
        val weatherDummyData = DataDummy.getDummyWeather()
        val weatherDummyFlow = flow {emit(Resource.success(weatherDummyData))}

        assertNotNull(weatherDummyData)
        assertEquals(weatherDummyData, weatherDummyFlow.first().data)

    }
}