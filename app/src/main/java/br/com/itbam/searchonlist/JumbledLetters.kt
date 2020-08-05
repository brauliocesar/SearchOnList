package br.com.itbam.searchonlist

class JumbledLetters {

    private val STR_MIN_LEN = 3

    /**
     * Verify Jumbled Letters In Strings
     *
     * @param strOne
     * @param strTwo
     * @return
     */
    @Throws(Exception::class)
    fun verifyJumbled(strOne: String?, strTwo: String?): Boolean {
        if (null == strOne || null == strTwo) {
            throw Exception("Error: String One and Two cannot be null!")
        }
        val iStrOneLength = strOne.length
        val iStrTwoLength = strTwo.length
        if (iStrOneLength < STR_MIN_LEN
            || iStrTwoLength < STR_MIN_LEN
        ) {
            throw Exception("Error: String One and Two has more than 3 letters!")
        }
        var bResult = false
        val szStringOne = strOne.toLowerCase().toCharArray()
        val szStringTwo = strTwo.toLowerCase().toCharArray()

        // Verify when strings with 3 letters only
        bResult = if (STR_MIN_LEN == iStrOneLength
            && STR_MIN_LEN == iStrTwoLength
        ) {
            (szStringOne[0] == szStringTwo[0]
                    && szStringOne[1] == szStringTwo[2])
        } else {
            szStringOne[0] == szStringTwo[0] && szStringOne[iStrOneLength - 1] == szStringTwo[iStrTwoLength - 1]
                    && verifyShuffledLetters(
                szStringOne,
                iStrOneLength,
                szStringTwo,
                iStrTwoLength
            ) && szStringOne.size == szStringTwo.size
        }
        return bResult
    }

    private fun verifyShuffledLetters(
        strShuffledOne: CharArray, iStrOneLength: Int,
        strShuffledTwo: CharArray, iStrTwoLength: Int
    ): Boolean {
        var iCount = 0
        var bResult = true
        var iIndexArray = 1
        while (iIndexArray < iStrOneLength - 2) {
            if (strShuffledOne.size > iIndexArray
                && strShuffledTwo.size > iIndexArray + 2
                && (strShuffledOne[iIndexArray] == strShuffledTwo[iIndexArray]
                || strShuffledOne[iIndexArray] == strShuffledTwo[iIndexArray + 1]
                || strShuffledOne[iIndexArray] == strShuffledTwo[iIndexArray + 2])
            ) {
                iCount = 0
                if (strShuffledOne[iIndexArray] == strShuffledTwo[iIndexArray]) {
                    iIndexArray++
                    continue
                }
                if (strShuffledOne[iIndexArray] == strShuffledTwo[iIndexArray + 1]
                    && strShuffledOne[iIndexArray + 1] == strShuffledTwo[iIndexArray]
                ) {
                    iIndexArray += 2
                    continue
                }
                if (strShuffledOne[iIndexArray] == strShuffledTwo[iIndexArray + 2]
                    && strShuffledOne[iIndexArray + 2] == strShuffledTwo[iIndexArray]
                ) {
                    iIndexArray += 3
                    continue
                }

            }
            iIndexArray++
            iCount++
            if (iCount == 2) {
                bResult = false
                break
            }
        }
        return bResult
    }
}