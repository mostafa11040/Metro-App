package com.example.metroapp.domain.usecase

import com.example.metroapp.domain.model.LineStations

class GetTripStationsUseCase {
    private val lines = listOf(LineStations.line1, LineStations.line2, LineStations.line3)

    operator fun invoke(start: String, end: String): List<String> {
        if (start == end) return listOf(start)

        // تحديد الخطوط التي تحتوي على محطة البداية والنهاية
        val startLines = lines.indices.filter { lines[it].contains(start) }
        val endLines = lines.indices.filter { lines[it].contains(end) }

        if (startLines.isEmpty() || endLines.isEmpty()) return emptyList()

        // إذا كانت المحطتان على نفس الخط
        val commonLine = startLines.intersect(endLines.toSet()).firstOrNull()
        if (commonLine != null) {
            return getSublist(lines[commonLine], start, end)
        }

        // البحث عن محطة تبديل بين الخطوط المختلفة
        for (sLineIdx in startLines) {
            for (eLineIdx in endLines) {
                val interchange = findInterchange(sLineIdx, eLineIdx)
                if (interchange != null) {
                    val part1 = getSublist(lines[sLineIdx], start, interchange)
                    val part2 = getSublist(lines[eLineIdx], interchange, end)
                    // دمج الجزئين مع حذف المحطة المتكررة في المنتصف
                    return part1 + part2.drop(1)
                }
            }
        }

        return emptyList()
    }

    private fun getSublist(line: List<String>, start: String, end: String): List<String> {
        val sIdx = line.indexOf(start)
        val eIdx = line.indexOf(end)
        return if (sIdx <= eIdx) {
            line.subList(sIdx, eIdx + 1)
        } else {
            line.subList(eIdx, sIdx + 1).reversed()
        }
    }

    private fun findInterchange(lineAIdx: Int, lineBIdx: Int): String? {
        // Line indices: 0 -> Line 1, 1 -> Line 2, 2 -> Line 3
        val pair = setOf(lineAIdx, lineBIdx)
        return when {
            pair == setOf(0, 1) -> "Sadat" // تبديل بين الخط الأول والثاني
            pair == setOf(0, 2) -> "Nasser" // تبديل بين الخط الأول والثالث
            pair == setOf(1, 2) -> "Attaba" // تبديل بين الخط الثاني والثالث
            else -> null
        }
    }
}
