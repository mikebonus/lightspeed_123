package com.lightspeed.data.api.implementation

import com.lightspeed.data.api.SecondApi
import com.lightspeed.domain.data.Lightspeed
import com.lightspeed.util.Constants
import org.json.JSONArray
import java.io.BufferedInputStream
import java.net.URL
import kotlin.random.Random

class SecondParsingImpl : SecondApi {

    var jsonArraySize = ""

    // RANDOM-ITEM()
    // (at BOTTOM)
    override suspend fun getLightspeed(): List<Lightspeed> {

        val url = URL(Constants.BASE_URL)
        val connection = url.openConnection()
        connection.connect()

        val bufferedInputStream = BufferedInputStream(connection.getInputStream())
        val bufferedReader = bufferedInputStream.bufferedReader()

        val stringBuffer = StringBuffer()
        for (line in bufferedReader.readLines()) {
            stringBuffer.append(line)
        }

        bufferedReader.close()
        val fullJson = stringBuffer.toString()

        // JSON-Parsing
        val jsonObjectLightspeed = JSONArray(fullJson)
        jsonArraySize = jsonObjectLightspeed.length().toString()
        val listOne = arrayListOf<Lightspeed>()

        for (i in 0 until jsonObjectLightspeed.length()) {
            // ID
            var id = jsonObjectLightspeed.get(i).toString()
                .substringBefore("author")
                .substring(7, jsonObjectLightspeed.get(i).toString()
                    .substringBefore("author").length - 3)


            // AUTHOR-NAME
            var author = jsonObjectLightspeed.get(i).toString()
                .substringBefore("width")
                .substringAfter("author")
                .substring(3, jsonObjectLightspeed.get(i).toString()
                    .substringBefore("width")
                    .substringAfter("author").length - 3)


            // DOWNLOAD-URL
            var refinedDownloadURL = refineImageLink(
                jsonObjectLightspeed.get(i).toString()
                    .substringAfter("download_url")
                    .substring(3, jsonObjectLightspeed.get(i).toString()
                        .substringAfter("download_url").length - 2)).toString()


            // OBJECT
            val thisLightspeed = Lightspeed(id, author, refinedDownloadURL)
            listOne.add(thisLightspeed)

        }

        // RANDOM-ITEM AT THE BOTTOM
        var randomNo = randomNumberGenerator(0, Integer.parseInt(jsonArraySize))
        var thisFetchedNo = listOne.get(randomNo)
            listOne.remove(listOne.get(randomNo))
            listOne.add(thisFetchedNo)

        return listOne
    }


    private fun randomNumberGenerator (start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(start, Integer.parseInt(jsonArraySize))
    }


    private fun refineImageLink(oldString: String): String? {
        val sb = StringBuilder()
        val oldStrChar = oldString.toCharArray()
        for (i in oldStrChar.indices) {
            if (oldStrChar[i] == '\\') {
                oldStrChar[i] = ' '
            }
        }
        for (i in oldStrChar.indices) {
            sb.append(oldStrChar[i])
        }
        return sb.toString().replace("\\s".toRegex(), "")
    }
}