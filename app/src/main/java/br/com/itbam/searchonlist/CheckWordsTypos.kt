package br.com.itbam.searchonlist

class CheckWordsTypos {

    /**
     * Make sure that two strings are typing strings
     *
     * @param strOne
     * @param strTwo
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun checkTypos(strOne: String?, strTwo: String?): Boolean {
        if (strOne == null || strOne.length == 0) {
            throw Exception("String has can not null or empty")
        }
        if (strTwo == null || strTwo.length == 0) {
            throw Exception("String has can not null or empty")
        }

        // Convert strings to char array
        val szStringOne = strOne.toLowerCase().toCharArray()
        val szStringTwo = strTwo.toLowerCase().toCharArray()
        // Get Length of Strings
        val iLenStringOne = szStringOne.size
        val iLenStringTwo = szStringTwo.size
        var iCount = 0
        var bResult = true
        var iIndexOne = 0
        var iIndexTwo = 0
        while (iIndexOne < iLenStringOne && iIndexTwo < iLenStringTwo) {

            // Check when two character are differents
            if (szStringOne[iIndexOne] != szStringTwo[iIndexTwo]) {
                if (iLenStringOne > iLenStringTwo) {
                    iIndexOne++
                } else if (iLenStringTwo > iLenStringOne) {
                    iIndexTwo++
                }
                iCount++
            }
            if (iCount > 1) {
                bResult = false
                break
            }
            iIndexOne++
            iIndexTwo++
        }
        return bResult
    }
}