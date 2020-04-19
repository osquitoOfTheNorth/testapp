package githubexplorer.oscarm.com.myapplication.detail

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestManager
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import githubexplorer.oscarm.com.api.persistence.PersistedGif
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Answers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class GifDetailPopulationFeatureTest {
    @Rule @JvmField
    val rule: MockitoRule = MockitoJUnit.rule()

    @Mock private lateinit var viewModel: DetailFragmentViewModel
    @Mock private lateinit var nameView: TextView
    @Mock private lateinit var ratingView: TextView
    @Mock private lateinit var imageView: ImageView
    @Mock private lateinit var gifCreated: TextView
    @Mock private lateinit var ownerView: TextView
    @Mock private lateinit var priceView: TextView
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var glide: RequestManager

    private lateinit var feature: GifDetailPopulationFeature

    @Before
    fun setUp() {
        `when`(glide.asGif().load("test")).thenReturn(mock())
        feature = GifDetailPopulationFeature(viewModel,
                nameView,
                ratingView,
                imageView,
                gifCreated,
                ownerView,
                priceView,
            glide
        )
    }

    @Test
    fun `when model emits a gif item, title is set correctly`() {
        `when`(viewModel.gif).thenReturn(Observable.just(PersistedGif("id", "title", gifUrl = "test")))

        feature.start()

        verify(nameView).text = "title"
    }

    @Test
    fun `when model emits a gif item, rating is set correctly`() {
        `when`(viewModel.gif).thenReturn(Observable.just(PersistedGif("id", "title", gifUrl = "test", price = "1")))

        feature.start()

        verify(priceView).text = "1"
    }

    @Test
    fun  `when model emits a gif item, date is set correctly`() {
        `when`(viewModel.gif).thenReturn(Observable.just(PersistedGif("id", "title", gifUrl = "test")))

        feature.start()

        verify(gifCreated).text = "2020 04 19"
    }
     /*
        Would write similar tests for the other views as well... in the same fashion
      */
}