package tasks

import contributors.MockGithubService
import contributors.concurrentProgressResults
import contributors.testRequestData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

class Request7ChannelsKtTest {
    @UseExperimental(ExperimentalCoroutinesApi::class)
    @Test
    fun testChannels() = runBlockingTest {
        val startTime = currentTime
        var index = 0
        loadContributorsChannels(MockGithubService, testRequestData) {
                users, _ ->
            val expected = concurrentProgressResults[index++]
            val time = currentTime - startTime
            /*
            // TODO: uncomment this assertion
            Assert.assertEquals("Expected intermediate result after virtual ${expected.timeFromStart} ms:",
                expected.timeFromStart, virtualTime)
            */
            Assert.assertEquals("Wrong intermediate result after $time:", expected.users, users)
        }
    }
}