package com.example.metroapp.domain.model

object LineStations {
    val line1 = listOf(
        "Helwan", "Ain Helwan", "Helwan University", "Wadi Hof", "Hadayek Helwan",
        "El-Maasara", "Tora El-Asmant", "Kotsika", "Tora El-Balad", "Thakanat El-Maadi",
        "Maadi", "Hadayek El-Maadi", "Dar El-Salam", "El-Zahraa", "Mar Girgis",
        "El-Malek El-Saleh", "Al-Sayeda Zeinab", "Saad Zaghloul", "Sadat", "Nasser",
        "Orabi", "Al-Shohadaa", "Ghamra", "El-Demerdash", "Manshiet El-Sadr",
        "Kobri El-Qobba", "Hammamat El-Qobba", "Saray El-Qobba", "Hadayek El-Zaitoun",
        "Helmeyet El-Zaitoun", "El-Matareyya", "Ain Shams", "Ezbet El-Nakhl", "El-Marg", "New El-Marg"
    )

    val line2 = listOf(
        "Shobra El Kheima", "Kolleyet El Zeraa", "Mezallat", "Khalafawy", "St. Teresa",
        "Rod El Farag", "Massara", "Al-Shohadaa", "Attaba", "Mohamed Naguib",
        "Sadat", "Opera", "Dokki", "El Behoos", "Cairo University",
        "Faisal", "Giza", "Omm El Misryeen", "Sakiat Mekki", "El Mounib"
    )

    val line3 = listOf(
        "Adly Mansour", "El Haykestep", "Omar Ibn El-Khattab", "Qobaa", "Hesham Barakat",
        "El Noozha", "Nadi El Shams", "Alf Maskan", "Heliopolis", "Haroun",
        "Al-Ahram", "Koleyet El-Banat", "Stadium", "Fair Zone", "Abbassia",
        "Abdou Pasha", "El Geish", "Bab El Shaariya", "Attaba", "Nasser",
        "Maspero", "Safaa Hegazy", "Kit Kat"
    )

    val interchanges = listOf("Sadat", "Al-Shohadaa", "Attaba", "Nasser", "Cairo University")

    fun isInterchange(station: String): Boolean = interchanges.contains(station)
}
