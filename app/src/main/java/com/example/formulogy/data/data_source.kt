package com.example.formulogy.data

data class track(
    val name: String,
    val location: String,
    val length: String,
    val corners: String,
    val lap_record: String,
    val driver:String,
    val image: String,
    val laps: String,
    val startedIn: String
)

object TrackRep {
    val tracks = listOf(
        track(
            name = "Bahrain Grand Prix",
            location = "Bahrain",
            length = "5.412 km",
            corners = "15",
            lap_record = "1:31.447",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Bahrain_Circuit.png.transform/7col/image.png",
            laps = "57",
            startedIn = "2004",
            driver = "Pedro de la Rosa (2005)"
        ),
        track(
            name = "Australian Grand Prix",
            location = "Melbourne",
            length = "5.278 km",
            corners = "16",
            lap_record = "1:19.813",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Australia_Circuit.png.transform/7col/image.png",
            laps = "58",
            startedIn = "1996",
            driver = "Charles Leclerc (2024)"
        ),
        track(
            name = "Saudi Arabian Grand Prix",
            location = "Jeddah",
            length = "6.175 km",
            corners = "27",
            lap_record = "1:30.734",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Saudi_Arabia_Circuit.png.transform/7col/image.png",
            laps = "50",
            startedIn = "2021",
            driver = "Lewis Hamilton (2021)"
        ),
        track(
            name = "Japanese Grand Prix",
            location = "Suzuka",
            length = "5.807 km",
            corners = "18",
            lap_record = "1:30.983",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Japan_Circuit.png.transform/7col/image.png",
            laps = "53",
            startedIn = "1987",
            driver = "Lewis Hamilton (2019)"
        ),
        track(
            name = "Chinese Grand Prix",
            location = "Shanghai",
            length = "5.451 km",
            corners = "16",
            lap_record = "1:32.238",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/China_Circuit.png.transform/7col/image.png",
            laps = "56",
            startedIn = "2004",
            driver = "Michael Schumacher (2004)"
        ),
        track(
            name = "Miami Grand Prix",
            location = "Miami",
            length = "5.41 km",
            corners = "19",
            lap_record = "1:29.708",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Miami_Circuit.png.transform/7col/image.png",
            laps = "57",
            startedIn = "2022",
            driver = "Max Verstappen (2023)"
        ),
        track(
            name = "Emilia Romagna Grand Prix",
            location = "Imola",
            length = "4.909 km",
            corners = "19",
            lap_record = "1:15.484",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Emilia_Romagna_Circuit.png.transform/7col/image.png",
            laps = "63",
            startedIn = "1980",
            driver = "Lewis Hamilton (2021)"
        ),
        track(
            name = "Monaco Grand Prix",
            location = "Monte Carlo",
            length = "3.337 km",
            corners = "19",
            lap_record = "1:12.909",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Monaco_Circuit.png.transform/7col/image.png",
            laps = "78",
            startedIn = "1950",
            driver = "Lewis Hamilton (2021)"
        ),
        track(
            name = "Canadian Grand Prix",
            location = "Montreal",
            length = "4.361 km",
            corners = "14",
            lap_record = "1:13.078",
            driver = "Valtteri Bottas (2019)",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Canada_Circuit.png.transform/7col/image.png",
            laps = "70",
            startedIn = "1978"
        ),
        track(
            name = "Spanish Grand Prix",
            location = "Barcelona",
            length = "4.675 km",
            corners = "16",
            lap_record = "1:16.330",
            driver = "Max Verstappen (2023)",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Spain_Circuit.png.transform/7col/image.png",
            laps = "66",
            startedIn = "1991"
        ),
        track(
            name = "Austria Grand Prix",
            location = "Spielberg",
            length = "4.318 km",
            corners = "10",
            lap_record = "1:05.619",
            driver = "Carlos Sainz (2020)",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Austria_Circuit.png.transform/7col/image.png",
            laps = "71",
            startedIn = "1970"
        ),
        track(
            name = "British Grand Prix",
            location = "Silverstone",
            length = "5.891 km",
            corners = "18",
            lap_record = "1:27.097",
            driver = "Max Verstappen (2020)",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Britain_Circuit.png.transform/7col/image.png",
            laps = "52",
            startedIn = "1950"
        ),
        track(
            name = "Hungarian Grand Prix",
            location = "Budapest",
            length = "4.381 km",
            corners = "14",
            lap_record = "1:16.627",
            driver = "Lewis Hamilton (2020)",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Hungary_Circuit.png.transform/7col/image.png",
            laps = "70",
            startedIn = "1986"
        ),
        track(
            name = "Belgian Grand Prix",
            location = "Spa-Francorchamps",
            length = "7.004 km",
            corners = "19",
            lap_record = "1:46.286",
            driver = "Valtteri Bottas (2018)",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Belgium_Circuit.png.transform/7col/image.png",
            laps = "44",
            startedIn = "1950"
        ),
        track(
            name = "Dutch Grand Prix",
            location = "Zandvoort",
            length = "4.259 km",
            corners = "14",
            lap_record = "1:11.097",
            driver = "Lewis Hamilton (2021)",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Netherlands_Circuit.png.transform/7col/image.png",
            laps = "72",
            startedIn = "1952"
        ),
        track(
            name = "Italian Grand Prix",
            location = "Monza",
            length = "5.793 km",
            corners = "11",
            lap_record = "1:21.046",
            driver = "Rubens Barrichello (2004)",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Italy_Circuit.png.transform/7col/image.png",
            laps = "53",
            startedIn = "1950"
        ),
        track(
            name = "Azerbaijan Grand Prix",
            location = "Baku",
            length = "6.003 km",
            corners = "20",
            lap_record = "1:43.009",
            driver = "Charles Leclerc (2019)",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Azerbaijan_Circuit.png.transform/7col/image.png",
            laps = "51",
            startedIn = "2016"
        ),
        track(
            name = "Singapore Grand Prix",
            location = "Singapore",
            length = "4.94 km",
            corners = "23",
            lap_record = "1:35.867",
            driver = "Lewis Hamilton (2023)",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Singapore_Circuit.png.transform/7col/image.png",
            laps = "62",
            startedIn = "2008"
        ),
        track(
            name = "United States Grand Prix",
            location = "Austin",
            length = "5.513 km",
            corners = "20",
            lap_record = "1:36.169",
            driver = "Charles Leclerc (2019)",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/United_States_Circuit.png.transform/7col/image.png",
            laps = "56",
            startedIn = "2012"
        ),
        track(
            name = "Mexican Grand Prix",
            location = "Mexico City",
            length = "4.304 km",
            corners = "17",
            lap_record = "1:17.774",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Mexico_Circuit.png.transform/7col/image.png",
            laps = "71",
            startedIn = "1963",
            driver = "Valtteri Bottas (2021)"
        ),
        track(
            name = "Brazilian Grand Prix",
            location = "Sao Paulo",
            length = "4.309 km",
            corners = "15",
            lap_record = "1:10.540",
            driver = "Valtteri Bottas (2018)",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Brazil_Circuit.png.transform/7col/image.png",
            laps = "71",
            startedIn = "1973"
        ),
        track(
            name = "Las Vegas Grand Prix",
            location = "Las Vegas",
            length = "6.201 km",
            corners = "17",
            lap_record = "1:35.490",
            driver = "Oscar Piastri (2023)",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Las_Vegas_Circuit.png.transform/7col/image.png",
            laps = "50",
            startedIn = "2023"
        ),
        track(
            name = "Qatar Grand Prix",
            location = "Lusail",
            length = "5.419 km",
            corners = "16",
            lap_record = "1:24.319",
            driver = "Max Verstappen (2023)",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Qatar_Circuit.png.transform/7col/image.png",
            laps = "57",
            startedIn = "2021"
        ),
        track(
            name = "Abu Dhabi Grand Prix",
            location = "Abu Dhabi",
            length = "5.281 km",
            corners = "21",
            lap_record = "1:26.103",
            driver = "Max Verstappen (2021)",
            image = "https://media.formula1.com/image/upload/f_auto/q_auto/v1677244985/content/dam/fom-website/2018-redesign-assets/Circuit%20maps%2016x9/Abu_Dhabi_Circuit.png.transform/7col/image.png",
            laps = "58",
            startedIn = "2009"
        )
    )
}