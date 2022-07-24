package com.lightspeed.data.implementation

import com.lightspeed.data.remote.FirstApi
import com.lightspeed.domain.model.Lightspeed
import com.lightspeed.util.Constants
import org.json.JSONArray
import java.io.BufferedInputStream
import java.net.URL

class FirstParsingImpl : FirstApi {

    var jsonArraySize = ""

    // GET-DATA()
    override suspend fun getNormalData(): List<Lightspeed> {

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

        return listOne
    }

    // to remove 'back-slash' in the link
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