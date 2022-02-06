# Mengelmoestuintjes backend

onderdeel van het project mengelmoestuintjes.
- [anoukdriessen/mengelmoestuintjes-app](https://github.com/anoukdriessen/mengelmoestuintjes-app)

Main class: GardeningApplication.java

Dit project is opgesteld met behulp van Springboot waardoor de bestandstructuur opgedeeld is in de verschillende lagen.

## Bestanden
- main klasse GardeningApplication
- CustomApplicationListener, voor het uitlezen van de endpoints

### /config
configuratie bestanden
- GlobalCorsConfiguration
- WebSecurityConfig

### /controller
De controller laag zorgt voor de endpoints van de API
- map met custom Exceptions
- ApiController, eerste testbestand
- AuthenticationController, zorgt voor de authenticatie
- GardenController, zorgt voor de functionaliteiten van de tuintjes
- PlantController, zorgt voor de functionaliteiten van de planten
- PostController, zorgt voor de functionaliteiten van de berichten
- QuoteController, zorgt voor de functionaliteiten van de quotes
- TaskController, zorgt voor de functionaliteiten van de taken
- UserController, zorgt voor de functionaliteiten van de gebruikers

### /dto
de DTO's dienen als Data Transfer Object tussen Request en Response, er is niet voor ieder object een Dto gebruikt
de DTO's zijn onderverdeeld in mappen Request en Response

### /model
de data objecten van de applicatie, ook wel POJO's (Plain Old Java Objects)
op basis van deze objecten zijn de rest van de lagen opgebouwd

### /repository
de repositories zijn verantwoordelijk voor het gemakkelijk filteren van data

### /security
in de security map staan twee bestanden de JwtRequestFilter en JwtUtil die beiden verantwoordelijk zijn voor de authenticatie door middel van een JWT token

### /service
de service laag dient voor de filtering van de dataobjecten voordat deze verzonden worden naar de controller
De service laag communiceert met de repository.

Het klassendiagram van dit project is te zien in het Technisch Ontwerp.